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
import java.util.HashMap;
import java.util.Map;

@WebFilter("/*") // Le filtre s'applique à toutes les requêtes
public class RoleBasedAccessFilter implements Filter {

    private Map<String, String[]> roleAccessMap;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        roleAccessMap = new HashMap<>();

        // Définir les accès pour chaque rôle
        roleAccessMap.put("Professeur", new String[]{"/professeur/", "/shared/"});
        roleAccessMap.put("Coordinateur", new String[]{"/coordinateur/", "/shared/", "/empl"});
        roleAccessMap.put("Responsable", new String[]{"/responsable/", "/shared/"});
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String requestURI = httpRequest.getRequestURI();

        // Exclure la page de connexion et la page d'accès refusé du filtre
        if (requestURI.contains("/log") || requestURI.contains("/accessDenied.jsp")) {
            chain.doFilter(request, response); // Autoriser l'accès à ces pages
            return;
        }

        // Récupérer le rôle de l'utilisateur connecté depuis la session
        String userRole = (String) httpRequest.getSession().getAttribute("userRole");

        // Si l'utilisateur n'est pas connecté ou ne dispose pas d'un rôle valide, rediriger vers une page d'accès refusé
        if (userRole == null) {
            // Rediriger vers la page de connexion si l'utilisateur n'est pas connecté
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/log");
            return;
        }

        // Vérifier les droits d'accès en fonction du rôle
        if (!isAccessAllowed(userRole, requestURI)) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/shared/accessDenied.jsp");
            return;
        }
        if (isPublicPath(requestURI)) {
            chain.doFilter(request, response);
            return;
        }


        // Si autorisé, continuer la chaîne de filtres
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // Libération des ressources si nécessaire
    }

    /**
     * Vérifie si un rôle est autorisé à accéder à une URL donnée.
     *
     * @param role       Rôle de l'utilisateur
     * @param requestURI URI demandée
     * @return true si autorisé, false sinon
     */
    private boolean isAccessAllowed(String role, String requestURI) {
        String[] allowedPaths = roleAccessMap.get(role);
        if (allowedPaths == null) {
            return false;
        }
        for (String path : allowedPaths) {
            if (requestURI.startsWith("/salleWEB-1.0-SNAPSHOT"+path)) {
                return true;
            }
        }
        return false;
    }
    private boolean isPublicPath(String requestURI) {
        return requestURI.contains("/log") ||
                requestURI.contains("/accessDenied.jsp") ||
                requestURI.contains("/css/") ||
                requestURI.contains("/js/") ||
                requestURI.contains("/images/");
    }

}