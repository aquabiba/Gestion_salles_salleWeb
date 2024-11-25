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
import jakarta.servlet.http.HttpSession;
import model.Coordinateur;
import model.Matiere;
import model.Professeur;
import model.ResponsableSalle;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin")
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

        List<Matiere> matieres = matiereService.getAllMatieres();
        HttpSession session = request.getSession();
        // Check for null or empty list
        if (matieres == null || matieres.isEmpty()) {
            matieres = List.of(); // Set an empty list to avoid null pointer exception
        }
        session.setAttribute("matieres", matieres);
        //request.getRequestDispatcher("admin.jsp").forward(request, response);
        response.sendRedirect("admin.jsp");
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        switch (role){
            case "Professeur":
                String matiere = request.getParameter("matiere");
                Matiere mat = matiereService.getMatiereByName(matiere);// problem

                if (professeurService.emailExists(email)){
                   response.setContentType("text/html");
                    response.getWriter().write("<script>alert('cet email existe deja dans la base de données');window.location='admin.jsp'</script>");
                   return;
                }else {
                    Professeur prof = new Professeur(firstName, lastName, email, password, phone, mat);
                    professeurService.ajouterProfesseur(prof);
                    response.setContentType("text/html");
                    response.getWriter().write("<script>alert('Professeur ajouté avec succès');window.location='admin.jsp'</script>");

                }
                break;

            case "ResponsableSalle":
                if (responsableService.emailExists(email)) {
                    response.setContentType("text/html");
                    response.getWriter().write("<script>alert('cet email existe deja dans la base de données');window.location='admin.jsp'</script>");

                    return;
                }else {
                    ResponsableSalle respo = new ResponsableSalle(firstName, lastName, email, phone, password);
                    responsableService.ajouterResponsable(respo);
                    response.setContentType("text/html");
                    response.getWriter().write("<script>alert('Responsable de salle ajouté avec succès');window.location='admin.jsp'</script>");
                }
                break;
            case "Coordinnateur":
                if (coordinateurService.emailExists(email)) {
                    response.setContentType("text/html");
                    response.getWriter().write("<script>alert('cet email existe deja dans la base de données');window.location='admin.jsp'</script>");
                    return;
                }else {
                    Coordinateur coord = new Coordinateur(firstName, lastName, email, password, phone);
                    coordinateurService.ajouterCoordinateur(coord);
                    response.setContentType("text/html");
                    response.getWriter().write("<script>alert('Coordinateur ajouté avec succès');window.location='admin.jsp'</script>");
                }
                break;

        }

        // TODO: Enregistrer l'utilisateur dans la base de données
        response.setContentType("text/html");
        response.getWriter().write("<script>alert('cet email existe deja dans la base de données');window.location='admin.jsp'</script>");
        
    }
}
