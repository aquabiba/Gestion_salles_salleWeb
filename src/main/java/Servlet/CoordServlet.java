package Servlet;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Coordinateur;
import model.Filiere;
import EJB.FiliereService;
import EJB.CoordinateurService;
import org.hibernate.Session;


import java.io.IOException;
@WebServlet("/coord")
public class CoordServlet extends HttpServlet {
    @EJB
    FiliereService filiereService;
    @EJB
    CoordinateurService coordinateurService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("coord.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String ajouter = req.getParameter("ajouter");
        String supprimer = req.getParameter("supprimer");
        String nomFil=req.getParameter("nomFiliere");
        int effectif=Integer.parseInt(req.getParameter("effectif"));
        String niveau=req.getParameter("niveau");
        HttpSession session=req.getSession(false);
        Integer idCoord = (Integer) session.getAttribute("coordinateurid");

        if (idCoord == null) {
            resp.setContentType("text/html");
            resp.getWriter().write("<script>alert('Coordinateur non trouvé dans la session. Veuillez vous reconnecter.');window.location='login.jsp'</script>");
            return;
        }

        Coordinateur coord = coordinateurService.getCoordinateurById(idCoord);
        if (coord == null) {
            resp.setContentType("text/html");
            resp.getWriter().write("<script>alert('Coordinateur introuvable en base de données.');window.location='coord.jsp'</script>");
            return;
        }
        Filiere fil = new Filiere(nomFil,niveau,effectif,coord);
        if(ajouter!=null){
            filiereService.ajouterFiliere(fil);
            resp.setContentType("text/html");
            resp.getWriter().write("<script>alert('Filière ajoutée avec succès');window.location='coord.jsp'</script>");
        } else if (supprimer!=null) {
            Filiere fil2 = filiereService.getFiliereByName(nomFil);
            filiereService.supprimerFiliere(fil2.getId_fil());
            resp.setContentType("text/html");
            resp.getWriter().write("<script>alert('Filière supprimée avec succès');window.location='coord.jsp'</script>");
        }
        session.setAttribute("coord", coordinateurService.getCoordinateurById(idCoord));

    }
}
