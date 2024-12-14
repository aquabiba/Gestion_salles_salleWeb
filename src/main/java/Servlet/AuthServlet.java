package Servlet;

import EJB.CoordinateurService;
import EJB.ProfesseurService;
import EJB.ResponsableService;
import EJB.FiliereService;
import jakarta.ejb.EJB;
import jakarta.inject.Scope;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.*;
import EJB.SalleService ;

import java.io.IOException;
import java.util.List;

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
    private FiliereService filiereService;


    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("Auth.jsp").forward(req, resp);

    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login_email = req.getParameter("login_email");
        String login_password = req.getParameter("password_login");

        HttpSession session = req.getSession(); // Crée une nouvelle session si elle n'existe pas
        List<Salle> salles = salleService.getAllSalles();
        List<Filiere> filieres = filiereService.getAllFilieres();

        try {
            Professeur professeur = professeurService.getProfesseurByEmail(login_email);

            if (professeur != null) {
                if (professeur.getPassword_Ut().equals(login_password)) {
                    session.setAttribute("professeur", professeur.getId_prof());

                    session.setAttribute("NomProf",professeur.getNom_Ut());

                    session.setAttribute("userRole","professeur");
                    resp.sendRedirect("Prof.jsp");

                } else {
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
                    session.setAttribute("coordinateurid", coordinateur.getId_coord());
                    session.setAttribute("coordinateurnom",coordinateur.getNom_Ut());
                    session.setAttribute("coorMail",coordinateur.getEmail_Ut());

                    // Récupération de l'identifiant de la filière et le niveau depuis la requête
                    session.setAttribute("filieres", filieres);

                    resp.sendRedirect("coord.jsp");
                }
                else {
                    out.println("<h2>Mot de Passe Incorrecte !</h2>");
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            out.println("<h2>Somethink is wrong !</h2>");
        }
        try {
            ResponsableSalle responsableSalle=responsableService.getResponsableByEmail(login_email);
            if(responsableSalle!=null) {
                if(responsableSalle.getPassword_Ut().equals(login_password)) {
                    session.setAttribute("salles",salles);
                    session.setAttribute("responsableId",responsableSalle.getId_resp());
                    session.setAttribute("responsableNom",responsableSalle.getNom_Ut());

                    session.setAttribute("userRole","responsableSalle");

                    resp.sendRedirect("Respo.jsp");
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


