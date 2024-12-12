package Servlet;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import EJB.ResponsableService ;
import EJB.MatiereService;
import EJB.CoordinateurService;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import model.Coordinateur;
import model.Matiere;
import model.Professeur;
import model.ResponsableSalle;
import EJB.ProfesseurService ;

@Startup
@Singleton
public class AppInit {
    @EJB
    private  MatiereService matiere;
    @EJB
    private  CoordinateurService coordinateur;
    @EJB
    private ResponsableService responsableService ;
    @EJB
    private ProfesseurService professeurService;

    @PostConstruct
    public  void init() {
        // Données pour la table Coordinateur
        Coordinateur coordinateur1 = new Coordinateur("Akssase", "Hamid", "Aksasse.Hamid@edu.uiz.ac.ma", "1234","0666098876");
        coordinateur.ajouterCoordinateur(coordinateur1);

        ResponsableSalle responsableSalle=new ResponsableSalle("Lamya","asmae","lamya.asmae@edu.uiz.ac.ma","1234","562356423");
        responsableService.ajouterResponsable(responsableSalle);

        // Données pour la table Matiere
        Matiere matiere1 = new Matiere("JEE", 10, coordinateur1);
        Matiere matiere2 = new Matiere("Anglais", 20,  coordinateur1);

        Professeur professeur=new Professeur("Quazdar","Imade","Quazdar.Imade@edu.uiz.ac.ma","1234","0654163",matiere1);

        // Persister les données

        matiere.ajouterMatiere(matiere1);
        matiere.ajouterMatiere(matiere2);
        professeurService.ajouterProfesseur(professeur);

    }

}

