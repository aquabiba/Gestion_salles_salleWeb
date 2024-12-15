package Servlet;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import EJB.MatiereService;
import EJB.CoordinateurService;
import jakarta.servlet.http.HttpSession;
import model.Coordinateur;
import model.Matiere;

import java.io.IOException;
@WebServlet("/mat")
public class MatiereServlet extends HttpServlet {
    @EJB
    MatiereService matiereService;
    @EJB
    CoordinateurService coordinateurService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("matiere.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String enregistrer = req.getParameter("enregistrer");
        String libelle = req.getParameter("libelle");

        int totalHeures=Integer.parseInt(req.getParameter("totalHeures"));

        HttpSession session = req.getSession(false);
        String coordMail = (String) session.getAttribute("coorMail");
        if (coordMail == null) {
            resp.setContentType("text/html");
            resp.getWriter().write("<script>alert('Coordinateur introuvable en base de données.');window.location='matiere.jsp'</script>");
            return;
        }
        Coordinateur coord = coordinateurService.getCoordinateurByEmail(coordMail);
        Matiere matiere = new Matiere(libelle,totalHeures,coord);
        if (enregistrer!=null){
            matiereService.ajouterMatiere(matiere);
            resp.setContentType("text/html");

            resp.sendRedirect("matiere.jsp");
        }
    }
}