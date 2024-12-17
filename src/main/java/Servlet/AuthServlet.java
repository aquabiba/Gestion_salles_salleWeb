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
                    Matiere mat = professeur.getMatiere();
                    String matiere = mat.getLibelle_mat();

                    session.setAttribute("salles",salles);
                    session.setAttribute("creneaux",creneaux);
                    session.setAttribute("matiere",matiere);
                    session.setAttribute("filieres",filiereList);
                    session.setAttribute("reservations",reservations);
                    session.setAttribute("professeur", professeur.getId_prof());
                    session.setAttribute("NomProf",professeur.getNom_Ut());
                    session.setAttribute("userRole","Professeur");
                    req.getRequestDispatcher("/professeur/ListReservation.jsp").forward(req, resp);
                    //resp.sendRedirect(req.getContextPath()+"/professeur/ListReservation.jsp");
                } else {
                    String message =  "Mot de passe incorrecte";
                    session.setAttribute("message",message);
                    resp.sendRedirect(req.getContextPath()+"/log");
                    out.println("<h2>Mot de Passe Incorrecte !</h2>");
                }
            }
        } catch (Exception e) {
            out.println("<h2>Somethink is wrong !</h2>");
        }
        try{
            Coordinateur coordinateur=coordinateurService.getCoordinateurByEmail(login_email);

            if(coordinateur!=null) {
                if(coordinateur.getPassword_Ut().equals(login_password)) {

                    session.setAttribute("reservattions",reservationService.getAllReservations());
                    session.setAttribute("filieres",filiereList);


                    session.setAttribute("coordinateurid", coordinateur.getId_coord());
                    session.setAttribute("coordinateurname", coordinateur.getNom_Ut());
                    session.setAttribute("coorMail",coordinateur.getEmail_Ut());
                    session.setAttribute("userRole","Coordinateur");
                    req.getRequestDispatcher("/coordinateur/coord.jsp").forward(req, resp);
                    //  oiu resp.sendRedirect(req.getContextPath()+"/coordinateur/coord.jsp");
                }
                else {
                    String message =  "Mot de passe incorrecte";
                    session.setAttribute("message",message);
                    resp.sendRedirect(req.getContextPath()+"/log");
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
                    req.getRequestDispatcher("/responsable/Respo_home.jsp").forward(req, resp);
                   // resp.sendRedirect(req.getContextPath()+"/responsable/Respo.jsp");
                }
                else {
                    String message =  "Mot de passe incorrecte";
                    session.setAttribute("message",message);
                    resp.sendRedirect(req.getContextPath()+"/log");
                    out.println("<h2>Mot de Passe Incorrecte !</h2>");
                }
            }
        }
        catch (Exception e) {
            out.println("<h2>Somethink is wrong !</h2>");
        }


    }

}