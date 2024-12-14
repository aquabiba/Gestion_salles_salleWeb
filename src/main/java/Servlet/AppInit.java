package Servlet;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import EJB.SalleService ;
import EJB.ResponsableService ;
import EJB.MatiereService;
import EJB.CoordinateurService;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import model.*;
import EJB.ProfesseurService ;
import EJB.FiliereService ;
import EJB.LibDefService ;
import EJB.ReservationService ;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import EJB.CreneauService ;
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
    @EJB
    private ReservationService reservationService;
    @EJB
    private CreneauService creneauService;
    @EJB
    private LibDefService libDefService;


    @PostConstruct
    public  void init() {
        // Données pour la table Coordinateur
        Coordinateur coordinateur1 = new Coordinateur("Akssase", "Hamid", "Aksasse.Hamid@edu.uiz.ac.ma", "1234","0666098876");
        coordinateur.ajouterCoordinateur(coordinateur1);

        ResponsableSalle responsableSalle=new ResponsableSalle("Lamya","asmae","lamya.asmae@edu.uiz.ac.ma","1234","562356423");
        responsableService.ajouterResponsable(responsableSalle);

        // Données pour la table Matiere
        Matiere matiere1 = new Matiere("JEE", 10, coordinateur1);
        Matiere matiere2 = new Matiere("Oracle", 20,  coordinateur1);

        libDefService.LiberationAuto() ;

        //filieres
        Filiere filiere1=new Filiere("Info2","2éme année",60,coordinateur1);
        Filiere filiere2=new Filiere("Meca3","3éme année",40,coordinateur1);


        Professeur professeur=new Professeur("Quazdar","Imade","Quazdar.Imade@edu.uiz.ac.ma","1234","0654163",matiere1);

        Salle salle1=new Salle("F12","Bloc F","salle TD",25,responsableSalle);
        Salle salle2=new Salle("H11","Bloc H","salle Cours",60,responsableSalle);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        InfosRes infosRes = new InfosRes("Lundi", LocalDate.parse("12/01/2024", formatter), LocalDate.parse("12/01/2025", formatter));
        Creneau creneau=new Creneau("8:30 - 10:20",false,salle1);
        Creneau creneau1=new Creneau("2:30 - 4:20",true,salle2);
        //Reservation
        Reservation reservation1=new Reservation(filiere1,infosRes,professeur,creneau);
        Reservation reservation2=new Reservation(filiere1,infosRes,professeur,creneau1);

        salleService.ajouterSalle(salle1);
        salleService.ajouterSalle(salle2);
        filiereService.ajouterFiliere(filiere1);
        filiereService.ajouterFiliere(filiere2);
        matiere.ajouterMatiere(matiere1);
        matiere.ajouterMatiere(matiere2);
        professeurService.ajouterProfesseur(professeur);
        creneauService.addCreneau(creneau);
        creneauService.addCreneau(creneau1);
        reservationService.ajouterReservation(reservation2);
        reservationService.ajouterReservation(reservation1);
    }
}