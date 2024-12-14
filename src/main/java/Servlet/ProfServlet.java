package Servlet;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import EJB.ReservationService ;
import jakarta.servlet.http.HttpSession;
import model.*;
import EJB.FiliereService ;
import EJB.ProfesseurService ;
import javax.swing.*;
import java.io.IOException;
import java.time.LocalDate;
import EJB.CreneauService ;
import java.util.Date;
import EJB.SalleService ;
import EJB.LibDefService ;
@WebServlet("/reservation")
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


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("Prof.jsp");
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ajouter=req.getParameter("ajouter");
        String supprimer=req.getParameter("supprimer");
        String modifier=req.getParameter("modifier");
        String filiere_name=req.getParameter("filiere");
        String matiere=req.getParameter("matiere");
        String creneaux=req.getParameter("creneaux");
        String dateDebut=req.getParameter("dateDebut");
        String dateFin=req.getParameter("dateFin");
        String salle=req.getParameter("salle");
        String sujet=req.getParameter("sujet");
        String jour=req.getParameter("jour");
        HttpSession session = req.getSession();
        Salle salle1=salleService.getSalleByName(salle);
        Creneau creneau=new Creneau(creneaux,false,salle1);
        creneauService.addCreneau(creneau);
        InfosRes infosRes=new InfosRes(jour,LocalDate.parse(dateDebut),LocalDate.parse(dateFin));
        Filiere filiere=filiereService.getFiliereByName(filiere_name);
        int id_professeur=(int) session.getAttribute("professeur");
        Professeur professeur=professeurService.getProfesseurById(id_professeur);

        Reservation reservation=new Reservation(filiere,infosRes,professeur,creneau);

        if(ajouter!=null){

            reservationService.ajouterReservation(reservation);
            String message="Reservation ajoutée avec succées ";
            session.setAttribute("message",message);
            resp.sendRedirect("Prof.jsp");

        }



    }
}
