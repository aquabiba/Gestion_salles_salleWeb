package Servlet;
import EJB.SalleService;
import EJB.ResponsableService ;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.ResponsableSalle;
import model.Salle;



import java.io.IOException;
import java.util.List;

import static java.lang.System.out;


@WebServlet("/salle")

public class RespoServlet extends HttpServlet {

    @EJB
    private SalleService salleService;

    @EJB
    private ResponsableService responsableService;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("/responsable/Respo.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        String enregistrer=req.getParameter("enregistrer");
        String supprimer=req.getParameter("supprimer");
        String nomSalle = req.getParameter("nomSalle");
        String localisation = req.getParameter("localisation");
        String typeSalle = req.getParameter("typeSalle");

        int capacite = Integer.parseInt(req.getParameter("capacite"));
        Integer idResponsbale = (Integer) session.getAttribute("responsableId");

        if(enregistrer!=null) {
            if (idResponsbale!=null) {
                ResponsableSalle responsableSalle = responsableService.getResponsableById(idResponsbale);

            Salle salle = new Salle(nomSalle, localisation, typeSalle, capacite, responsableSalle);

            try {
                salleService.ajouterSalle(salle);
                List<Salle> salles=salleService.getAllSalles();
                session.setAttribute("salles", salles);
                String message = "Salle Ajouter avec Succes ";
                req.setAttribute("message", message);
                resp.sendRedirect(req.getContextPath()+"/salle");

            } catch (Exception e) {
                e.printStackTrace();
            }
           }else{
                String message = "Id professeur est null ";
                req.setAttribute("message", message);
                resp.sendRedirect(req.getContextPath()+"/salle");
            }
        }
        if(supprimer!=null) {
            Salle salle=salleService.getSalleByName(nomSalle);
            try{
                salleService.supprimerSalle(salle.getId_sal());
                List<Salle> salles=salleService.getAllSalles();
                session.setAttribute("salles", salles);
                String message = "Salle supprimer avec Succes ";
                req.setAttribute("message", message);
                resp.sendRedirect(req.getContextPath()+"/salle");
            }
            catch(Exception e){
                e.printStackTrace();
                out.println(" Waaaaaaaaaaaaaaaa  Erreur  TFOO : "+e.getMessage());
            }
        }
    }
}


