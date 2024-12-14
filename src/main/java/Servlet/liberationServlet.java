package Servlet;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.LiberationDefinitive;
import model.LiberationExceptionnelle;
import model.Reservation;
import EJB.ReservationService ;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import EJB.LibExeService ;
import EJB.LibDefService ;

@WebServlet("/liberation")
public class liberationServlet extends HttpServlet {

    @EJB
    ReservationService reservationService;
    @EJB
    LibDefService libDefService;
    @EJB
    LibExeService libExeService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        String dateDebut =req.getParameter("dateDebut");
        String dateFin =req.getParameter("dateFin");
        String IdReservation =req.getParameter("idReservation");
        String liberationDef=req.getParameter("liberationDef");
        String liberationExce=req.getParameter("liberationExce");
        Reservation reservation = reservationService.getReservationById(Integer.parseInt(IdReservation)) ;

        if(liberationDef!=null){
            //supprimer dans la table de reservation
            LiberationDefinitive liberation = new LiberationDefinitive(" La réservation a été libérée définitivement ",java.sql.Date.valueOf(LocalDate.parse(dateFin)) ,reservation,reservation.getProfesseur());
            libDefService.ajouterLiberationDefinitive(liberation);
            String message="La réservation a été libérée définitivement avec succès !" ;
            session.setAttribute("message",message);
            resp.sendRedirect("liberation.jsp");
        }
        if (liberationExce!=null){
            Date dateDeb = java.sql.Date.valueOf(LocalDate.parse(dateDebut));
            Date dateF = java.sql.Date.valueOf(LocalDate.parse(dateFin));
            LiberationExceptionnelle liberationExceptionnelle=new LiberationExceptionnelle("Libération Exceptionnelle",dateDeb,dateF,reservation,reservation.getProfesseur()) ;
            libExeService.ajouterLibExe(liberationExceptionnelle);
            String message="La réservation a été libérer exceptionnelle avec succès !" ;
            session.setAttribute("message",message);
            resp.sendRedirect("liberation.jsp");
        }


    }
}
