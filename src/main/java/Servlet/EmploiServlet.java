package Servlet;

import EJB.ReservationService;
import EJB.FiliereService;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//@WebServlet("/empl")
public class EmploiServlet extends HttpServlet {

    @EJB
    private ReservationService reservationService;
    @EJB
    private FiliereService filiereService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        req.getRequestDispatcher("Emploi.jsp").forward(req, resp);
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null) {
            // Pas de session existante
            resp.sendRedirect("/log");
            return;
        }
        String importer = req.getParameter("uploadEmploi");

    if (importer != null) {

        String filiereName = req.getParameter("filiere");
        Filiere fil = filiereService.getFiliereByName(filiereName);


        if (filiereName == null || filiereName.isEmpty()) {
            resp.getWriter().write("<script>alert('Nom de filière manquant');window.location='Emploi.jsp';</script>");
            return;
        }
        session.setAttribute("filiereName", filiereName);
        session.setAttribute("niveau",fil.getNiveau_fil());


        // Récupérer les données de l'emploi du temps
        List<Object[]> timetableData = reservationService.getReservationsWithSalleByFiliere(filiereName);

        if (timetableData == null || timetableData.isEmpty()) {
            resp.getWriter().write("<script>alert('Aucune donnée trouvée pour cette filière');window.location='/empl';</script>");
            return;
        }

        // Initialiser la structure timetable comme une Map
        Map<String, Map<String, Object[]>> timetable = new HashMap<>();

        // Organiser les données par jour et créneau
        for (Object[] row : timetableData) {
            // Vérification et extraction des données
            Creneau creneau = (Creneau) row[0];
            Matiere matiere = (Matiere) row[1]; // Matière liée au professeur
            Professeur professeur = (Professeur) row[2];
            Salle salle = (Salle) row[3];
            InfosRes infoRes = (InfosRes) row[4]; // Informations contenant le jour

            // Récupérer le jour et le créneau
            String jour = infoRes.getJour_res(); // Assurez-vous que cette méthode existe
            String descCreneau = creneau.getDesc_creneau(); // Assurez-vous que cette méthode existe

            // Organisation des données dans la structure timetable
            timetable
                    .computeIfAbsent(jour, k -> new HashMap<>())
                    .put(descCreneau, new Object[]{matiere, professeur, salle});
        }

        // Passer les données organisées à la JSP
        req.setAttribute("timetable", timetable);
        }
    }
}
