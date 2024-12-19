package Servlet;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import EJB.*;
import model.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/ListR")
public class ProfServlet extends HttpServlet {

    @EJB
    ReservationService reservationService;
    @EJB
    FiliereService filiereService;
    @EJB
    ProfesseurService professeurService;
    @EJB
    CreneauService creneauService;
    @EJB
    SalleService salleService;
    @EJB
    MatiereService matiereService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        // Charger les données nécessaires pour le formulaire
        List<Filiere> filieres = filiereService.getAllFilieres();
        List<Matiere> matieres = matiereService.getAllMatieres();
        List<Salle> salles = salleService.getAllSalles();

        session.setAttribute("filieres", filieres);
        session.setAttribute("matieres", matieres);
        session.setAttribute("salles", salles);

        req.getRequestDispatcher("/professeur/Prof.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        // Récupération des paramètres du formulaire
        String ajouter = req.getParameter("ajouter");
        String chercher = req.getParameter("Chercher");
        String filiere_name = req.getParameter("filiere");
        String matiere = req.getParameter("matiere");
        String creneaux = req.getParameter("creneaux");
        String dateDebut = req.getParameter("dateDebut");
        String dateFin = req.getParameter("dateFin");
        String salle = req.getParameter("salle");
        String sujet = req.getParameter("sujet");
        String jour = req.getParameter("jour");
        LocalDate datD = LocalDate.parse(dateDebut);
        LocalDate datF = LocalDate.parse(dateFin);

        if (datD.isBefore(datF)) {
            // date1 est avant date2


            // Recherche des salles disponibles
            if (chercher != null) {
                if (creneaux != null && jour != null) {
                    List<Salle> sallesDispo = creneauService.getSallesByDisponibilite(creneaux, jour);

                    if (sallesDispo == null || sallesDispo.isEmpty()) {
                        sallesDispo = new ArrayList<>();
                        session.setAttribute("message", "Aucune salle disponible pour ce créneau et jour.");

                    } else {
                        session.setAttribute("message", "Salles disponibles trouvées.");
                    }

                    session.setAttribute("salleDispo", sallesDispo);
                    resp.sendRedirect(req.getContextPath() + "/ListR");

                } else {
                    session.setAttribute("message", "Veuillez renseigner le créneau et le jour.");
                    resp.sendRedirect(req.getContextPath() + "/ListR");
                    return;
                }
            }

            // Ajouter une réservation
            if (ajouter != null) {
                try {
                    Reservation res = reservationService.getReservationByCreneauJourFiliere(jour,creneaux,filiere_name);
                    if (res != null) {
                        session.setAttribute("message", "Cette salle est deja reservée par un autre professeur de cette filière.");
                        resp.sendRedirect(req.getContextPath() + "/ListR");
                        return;
                    }
                    Salle salle1 = salleService.getSalleByName(salle);
                    Creneau creneau = new Creneau(creneaux, false, salle1);
                    creneauService.addCreneau(creneau);

                    InfosRes infosRes = new InfosRes(jour, LocalDate.parse(dateDebut), LocalDate.parse(dateFin));
                    Filiere filiere = filiereService.getFiliereByName(filiere_name);
                    int id_professeur = (int) session.getAttribute("professeur");
                    Professeur professeur = professeurService.getProfesseurById(id_professeur);

                    Reservation reservation = new Reservation(filiere, infosRes, professeur, creneau);
                    reservationService.ajouterReservation(reservation);

                    session.setAttribute("reservations", reservationService.getReservationByProf(professeur));
                    session.setAttribute("message", "Réservation ajoutée avec succès !");
                    resp.sendRedirect(req.getContextPath() + "/ListR");
                    //il faut faire une fonction qui retourne une salle selon un jour une salle et un creneau


                } catch (Exception e) {
                    e.printStackTrace();
                    session.setAttribute("message", "Erreur lors de l'ajout de la réservation.");
                    resp.sendRedirect(req.getContextPath() + "/ListR");
                }
            }
        } else if (datD.isAfter(datF)) {
            // date1 est après date2
            session.setAttribute("message", "Votre date est illogique !");
            resp.sendRedirect(req.getContextPath() + "/ListR");
        } else {
            // les dates sont égales.
            session.setAttribute("message", "Vous avez selectionner la meme date pour date fin et date debut !");
            resp.sendRedirect(req.getContextPath() + "/ListR");
        }
    }
}
