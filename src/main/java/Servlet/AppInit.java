package Servlet;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import EJB.SalleService ;
import EJB.ResponsableService ;
import EJB.MatiereService;
import EJB.CoordinateurService;
import EJB.FiliereService;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import model.*;
import EJB.ProfesseurService ;
import EJB.SalleService;

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
    @EJB
    private SalleService salleService;
    @EJB
    private FiliereService filiereService;

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

        Salle salle1 = new Salle("F12","Block F 2 eme etage","Cours",60,responsableSalle);
        Salle salle2 = new Salle("H11","Block H 1 eme etage","TP",70,responsableSalle);
        // Persister les données
        Filiere fil1 = new Filiere("Genie informatique","CI2",64,coordinateur1);
        Filiere fil2 = new Filiere("Genie electrique","CI2",30,coordinateur1);

        filiereService.ajouterFiliere(fil1);
        filiereService.ajouterFiliere(fil2);
        matiere.ajouterMatiere(matiere1);
        matiere.ajouterMatiere(matiere2);
        professeurService.ajouterProfesseur(professeur);
        salleService.ajouterSalle(salle1);
        salleService.ajouterSalle(salle2);

    }

}

