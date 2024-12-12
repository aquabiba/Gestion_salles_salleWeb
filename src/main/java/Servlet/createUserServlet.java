package Servlet;

import jakarta.ejb.EJB;
import EJB.ProfesseurService;
import EJB.CoordinateurService;
import EJB.ResponsableService;
import EJB.MatiereService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Coordinateur;
import model.Matiere;
import model.Professeur;
import model.ResponsableSalle;

import java.io.IOException;

@WebServlet("/CreateUserServlet")
public class createUserServlet extends HttpServlet {
    @EJB
    private ProfesseurService professeurService;
    @EJB
    private CoordinateurService coordinateurService;
    @EJB
    private ResponsableService responsableService;
    @EJB
    private MatiereService matiereService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("admin.jsp").forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");
        String role = request.getParameter("role");
        String matiere = request.getParameter("matiere");

        switch (role){
            case "professeur":
                Matiere mat = matiereService.getMatiereByName(matiere);
                Professeur prof = new Professeur(firstName,lastName,email,password,phone,mat);
                professeurService.ajouterProfesseur(prof);
                break;

            case "ResponsableSalle":
                ResponsableSalle respo = new ResponsableSalle(firstName,lastName,email,phone,password);
                responsableService.ajouterResponsable(respo);
                break;
            case "Coordinnateur":
                Coordinateur coord = new Coordinateur(firstName,lastName,email,password,phone);
                coordinateurService.ajouterCoordinateur(coord);
                break;

        }

        // TODO: Enregistrer l'utilisateur dans la base de données
        response.getWriter().println("User created successfully!");
    }
}
