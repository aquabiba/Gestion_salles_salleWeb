package Servlet;

import EJB.ReservationService;
import EJB.FiliereService;
import EJB.EmploiService;
import EJB.CoordinateurService;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.*;
import java.io.IOException;

@WebServlet("/empl")
public class EmploiServlet extends HttpServlet {

    @EJB
    private ReservationService reservationService;
    @EJB
    private FiliereService filiereService;
    @EJB
    private EmploiService emploiService;
    @EJB
    private CoordinateurService coordinateurService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        req.getRequestDispatcher("/coordinateur/Emploi.jsp").forward(req, resp);
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        String filiere = req.getParameter("filiere");
        session.setAttribute("filiere", filiere);

        String afficherEmploi = req.getParameter("afficherEmploiFiliere");
        if (afficherEmploi != null) {
            String[] jours = {"Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi"};
            String[] creneaux = {"8:30 - 10:20", "10:40 - 12:30", "2:30 - 4:20", "4:40 - 6:30"};

            // Parcourir les créneaux et jours pour remplir les réservations
            for (int i = 0; i < creneaux.length; i++) {
                for (int j = 0; j < jours.length; j++) {
                    String sessionKey = "reservation" + (i + 1) + "-" + (j + 1);
                    Reservation reservation = reservationService.getReservationByCreneauJourFiliere(creneaux[i], jours[j], filiere);
                    session.setAttribute(sessionKey, reservation);
                }
            }

            // Ajouter un nouvel emploi si nécessaire
            int idcoord = (int) session.getAttribute("coordinateurid");
            Coordinateur coord = coordinateurService.getCoordinateurById(idcoord);
            Emploi existingEmploi = emploiService.getEmploiByName(filiere);
            if (existingEmploi == null) {
                Emploi emploi = new Emploi(filiere, coord);
                emploiService.ajouterEmploi(emploi);
            }
            // Redirection pour recharger les données
            resp.sendRedirect(req.getContextPath() + "/empl");
        }
    }


}
