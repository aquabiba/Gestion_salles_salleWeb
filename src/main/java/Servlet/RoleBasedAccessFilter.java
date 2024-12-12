package Servlet;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebFilter("/*") // Intercepter toutes les URLs, on filtrera en fonction du chemin ensuite
public class RoleBasedAccessFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialisation du filtre si nécessaire
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpReq = (HttpServletRequest) request;
        HttpServletResponse httpResp = (HttpServletResponse) response;

        // Récupération du rôle de l'utilisateur depuis la session (après login)
        String userRole = (String) httpReq.getSession().getAttribute("userRole");

        // URL demandée (path)
        String requestURI = httpReq.getRequestURI();

        // Si l'utilisateur n'est pas connecté, rediriger vers la page de login
        // (Ici, on considère qu'un utilisateur non connecté n'a pas de userRole en session)
        if (userRole == null) {
            httpResp.sendRedirect(httpReq.getContextPath() + "/login.jsp");
            return;
        }

        // Vérification des accès selon le rôle
        // Supposons les règles suivantes :
        // - Le professeur peut accéder uniquement aux URLs sous /professeur/*
        // - Le responsable des salles peut accéder uniquement aux URLs sous /responsableSalle/*
        // - Le coordinateur peut accéder uniquement aux URLs sous /coordinateur/*

        // Pour plus de souplesse, on peut effectuer la vérification par des conditions sur le pattern
        if (requestURI.contains("/professeur/")) {
            if (!userRole.equals("professeur")) {
                // Pas le bon rôle pour cette zone, accès refusé
                httpResp.sendRedirect(httpReq.getContextPath() + "/accessDenied.jsp");
                return;
            }
        } else if (requestURI.contains("/responsableSalle/")) {
            if (!userRole.equals("responsableSalle")) {
                httpResp.sendRedirect(httpReq.getContextPath() + "/accessDenied.jsp");
                return;
            }
        } else if (requestURI.contains("/coordinateur/")) {
            if (!userRole.equals("coordinateur")) {
                httpResp.sendRedirect(httpReq.getContextPath() + "/accessDenied.jsp");
                return;
            }
        }

        // Si on arrive ici, c’est que les conditions d’accès sont remplies, ou que l’URL
        // ne se trouve pas dans un espace restreint. On passe donc la requête au reste de la chaîne.
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // Ressources à libérer si nécessaire
    }
}
