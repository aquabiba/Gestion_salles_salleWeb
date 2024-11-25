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

import java.io.IOException;
@WebServlet("/log")
public class AuthServlet extends HttpServlet {

    @EJB
    private ProfesseurService professeurService;
    @EJB
    private CoordinateurService coordinateurService;
    @EJB
    private ResponsableService responsableService;


    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("Auth.jsp").forward(req, resp);

    }

}
