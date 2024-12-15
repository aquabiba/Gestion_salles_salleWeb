package Servlet;

import EJB.CoordinateurService;
import EJB.ProfesseurService;
import EJB.ResponsableService;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.*;
import EJB.SalleService ;
import EJB.MatiereService ;
import java.io.IOException;
import java.util.List;
import EJB.ReservationService ;
import EJB.CreneauService ;
import EJB.FiliereService ;
import static java.lang.System.out;

@WebServlet("/log")
public class AuthServlet extends HttpServlet {

    @EJB
    private ProfesseurService professeurService;
    @EJB
    private CoordinateurService coordinateurService;
    @EJB
    private ResponsableService responsableService;
    @EJB
    private SalleService salleService;
    @EJB
    private MatiereService matiereService;
    @EJB
    private FiliereService filiereService;
    @EJB
    private ReservationService reservationService;
    @EJB
    private CreneauService creneauService;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("/shared/Auth.jsp").forward(req, resp);

    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login_email = req.getParameter("login_email");
        String login_password = req.getParameter("password_login");

        HttpSession session = req.getSession(); // Crée une nouvelle session si elle n'existe pas
        List<Salle> salles = salleService.getAllSalles();
        List<Matiere> matiereList = matiereService.getAllMatieres();
        List<Filiere> filiereList = filiereService.getAllFilieres();
//        String creneau_choisis=session.getAttribute("creneau_choisis").toString();
        List<Creneau> creneaux=creneauService.getCreneaux() ;

        try {
            Professeur professeur = professeurService.getProfesseurByEmail(login_email);
            List<Reservation> reservations = reservationService.getReservationByProf(professeur);
            if (professeur != null) {
                if (professeur.getPassword_Ut().equals(login_password)) {
                    session.setAttribute("salles",salles);
                    session.setAttribute("creneaux",creneaux);
                    session.setAttribute("matieres",matiereList);
                    session.setAttribute("filieres",filiereList);
                    session.setAttribute("reservations",reservations);
                    session.setAttribute("professeur", professeur.getId_prof());
                    session.setAttribute("prof_name",professeur.getNom_Ut());
                    session.setAttribute("userRole","Professeur");
                    req.getRequestDispatcher("/professeur/ListReservation.jsp").forward(req, resp);
                    //resp.sendRedirect(req.getContextPath()+"/professeur/ListReservation.jsp");
                } else {
                    out.println("<h2>Mot de Passe Incorrecte !</h2>");
                }
            }
        } catch (Exception e) {
            out.println("<h2>Somethink is wrong !</h2>");
        }
        try{
            Coordinateur coordinateur=coordinateurService.getCoordinateurByEmail(login_email);
//            Reservation reservation1_1=reservationService.getReservationByCreaneauJour("8:30 - 10:20","Lundi") ;
//            Reservation reservation1_2=reservationService.getReservationByCreaneauJour("8:30 - 10:20","Mardi") ;
//            Reservation reservation1_3=reservationService.getReservationByCreaneauJour("8:30 - 10:20","Mercredi") ;
//            Reservation reservation1_4=reservationService.getReservationByCreaneauJour("8:30 - 10:20","Jeudi") ;
//            Reservation reservation1_5=reservationService.getReservationByCreaneauJour("8:30 - 10:20","Vendredi") ;
//            Reservation reservation1_6=reservationService.getReservationByCreaneauJour("8:30 - 10:20","Samedi") ;
            if(coordinateur!=null) {
                if(coordinateur.getPassword_Ut().equals(login_password)) {

                    session.setAttribute("reservattions",reservationService.getAllReservations());
                    session.setAttribute("filieres",filiereList);
//                    session.setAttribute("reservation1-1",reservation1_1);
//                    session.setAttribute("reservation1-2",reservation1_2);
//                    session.setAttribute("reservation1-3",reservation1_3);
//                    session.setAttribute("reservation1-4",reservation1_4);
//                    session.setAttribute("reservation1-5",reservation1_5);
//                    session.setAttribute("reservation1-6",reservation1_6);
//
//                    session.setAttribute("reservation2-1",reservationService.getReservationByCreaneauJour("10:40 - 12:30","Lundi") );
//                    session.setAttribute("reservation2-2",reservationService.getReservationByCreaneauJour("10:40 - 12:30","Mardi"));
//                    session.setAttribute("reservation2-3",reservationService.getReservationByCreaneauJour("10:40 - 12:30","Mercredi"));
//                    session.setAttribute("reservation2-4",reservationService.getReservationByCreaneauJour("10:40 - 12:30","Jeudi"));
//                    session.setAttribute("reservation2-5",reservationService.getReservationByCreaneauJour("10:40 - 12:30","Vendredi"));
//                    session.setAttribute("reservation2-6",reservationService.getReservationByCreaneauJour("10:40 - 12:30","Samedi"));
//
//                    session.setAttribute("reservation3-1",reservationService.getReservationByCreaneauJour("2:30 - 4:20","Lundi") );
//                    session.setAttribute("reservation3-2",reservationService.getReservationByCreaneauJour("2:30 - 4:20","Mardi"));
//                    session.setAttribute("reservation3-3",reservationService.getReservationByCreaneauJour("2:30 - 4:20","Mercredi"));
//                    session.setAttribute("reservation3-4",reservationService.getReservationByCreaneauJour("2:30 - 4:20","Jeudi"));
//                    session.setAttribute("reservation3-5",reservationService.getReservationByCreaneauJour("2:30 - 4:20","Vendredi"));
//                    session.setAttribute("reservation3-6",reservationService.getReservationByCreaneauJour("2:30 - 4:20","Samedi"));
//
//                    session.setAttribute("reservation4-1",reservationService.getReservationByCreaneauJour("4:40 - 6:30","Lundi") );
//                    session.setAttribute("reservation4-2",reservationService.getReservationByCreaneauJour("4:40 - 6:30","Mardi"));
//                    session.setAttribute("reservation4-3",reservationService.getReservationByCreaneauJour("4:40 - 6:30","Mercredi"));
//                    session.setAttribute("reservation4-4",reservationService.getReservationByCreaneauJour("4:40 - 6:30","Jeudi"));
//                    session.setAttribute("reservation4-5",reservationService.getReservationByCreaneauJour("4:40 - 6:30","Vendredi"));
//                    session.setAttribute("reservation4-6",reservationService.getReservationByCreaneauJour("4:40 - 6:30","Samedi"));

                    session.setAttribute("coordinateurid", coordinateur.getId_coord());
                    session.setAttribute("coordinateurname", coordinateur.getNom_Ut());
                    session.setAttribute("coorMail",coordinateur.getEmail_Ut());
                    session.setAttribute("userRole","Coordinateur");
                    //req.getRequestDispatcher("/coordinateur/coord.jsp").forward(req, resp);
                    resp.sendRedirect(req.getContextPath()+"/coordinateur/coord.jsp");
                }
                else {
                    out.println("<h2>Mot de Passe Incorrecte !</h2>");
                }
            }
        }
        catch (Exception e) {
            out.println("<h2>Somethink is wrong !</h2>");
        }
        try {
            ResponsableSalle responsableSalle=responsableService.getResponsableByEmail(login_email);
            if(responsableSalle!=null) {
                if(responsableSalle.getPassword_Ut().equals(login_password)) {
                    session.setAttribute("salles",salles);
                    session.setAttribute("responsableId",responsableSalle.getId_resp());
                    session.setAttribute("responsableNom",responsableSalle.getNom_Ut());
                    session.setAttribute("userRole","Responsable");
                    req.getRequestDispatcher("/responsable/Respo.jsp").forward(req, resp);
                   // resp.sendRedirect(req.getContextPath()+"/responsable/Respo.jsp");
                }
                else {
                    out.println("<h2>Mot de Passe Incorrecte !</h2>");
                }
            }
        }
        catch (Exception e) {
            out.println("<h2>Somethink is wrong !</h2>");
        }

    }

}