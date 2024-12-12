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

import static java.lang.System.out;

@WebServlet("/respo/salleManagement")
public class RespoServlet extends HttpServlet {

    @EJB
    private SalleService salleService;

    @EJB
    private ResponsableService responsableService;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.sendRedirect("Respo.jsp");



        HttpSession session = req.getSession();


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String enregistrer=req.getParameter("enregistrer");
        String supprimer=req.getParameter("supprimer");

        HttpSession session = req.getSession(false);

        if(enregistrer!=null) {

            String nomSalle = req.getParameter("nomSalle");
            String localisation = req.getParameter("localisation");
            String typeSalle = req.getParameter("typeSalle");
            int capacite = Integer.parseInt(req.getParameter("capacite"));
            int idResponsbale = (int) session.getAttribute("responsableId");
            ResponsableSalle responsableSalle = responsableService.getResponsableById(idResponsbale);
            Salle salle = new Salle(nomSalle, localisation, typeSalle, capacite,responsableSalle);
            try {
                salleService.ajouterSalle(salle);
                String message = "Salle Ajouter avec Succes ";
                req.setAttribute("message", message);
                req.getRequestDispatcher("Respo.jsp").forward(req,resp);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(supprimer!=null) {
            String nomSalle = req.getParameter("nomSalle");
            Salle salle=salleService.getSalleByName(nomSalle);
            try{
                salleService.supprimerSalle(salle.getId_sal());
                String message = "Salle supprimer avec Succes ";
                req.setAttribute("message", message);
                req.getRequestDispatcher("Respo.jsp").forward(req,resp);
            }
            catch(Exception e){
                e.printStackTrace();
                out.println(" Waaaaaaaaaaaaaaaa  Erreur  TFOO : "+e.getMessage());
            }
        }
    }
}


