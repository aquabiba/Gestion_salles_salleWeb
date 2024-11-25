package Servlet;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import EJB.MatiereService;
import EJB.CoordinateurService;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import model.Coordinateur;
import model.Matiere;
//import jakarta.inject.Inject;


@Startup
@Singleton
public class AppInit {
    @EJB
    private  MatiereService matiere;
    @EJB
    private  CoordinateurService coordinateur;

    @PostConstruct
    public  void init() {
        // Données pour la table Coordinateur
        Coordinateur coordinateur1 = new Coordinateur("Jean", "Dupont", "coord1@example.com", "password123","0666098876");
        coordinateur.ajouterCoordinateur(coordinateur1);

        // Données pour la table Matiere
        Matiere matiere1 = new Matiere("JEE", 10, coordinateur1);
        Matiere matiere2 = new Matiere("Anglais", 20,  coordinateur1);

        // Persister les données

        matiere.ajouterMatiere(matiere1);
        matiere.ajouterMatiere(matiere2);


    }
}
