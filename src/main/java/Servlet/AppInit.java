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

        Coordinateur coordinateur1 = new Coordinateur("Akssase", "Hamid", "Aksasse.Hamid@edu.uiz.ac.ma", "1234","0666098876");

        ResponsableSalle responsableSalle=new ResponsableSalle("Lamya","asmae","lamya.asmae@edu.uiz.ac.ma","1234","562356423");

        Matiere matiere1 = new Matiere("JEE", 10, coordinateur1);
        Matiere matiere2 = new Matiere("Oracle", 20,  coordinateur1);
        Matiere matiere3 = new Matiere("ADD", 20,  coordinateur1);
        Matiere matiere4 = new Matiere("A.Financiére", 20,  coordinateur1);
        Matiere matiere5 = new Matiere("Optimisation", 20,  coordinateur1);
        Matiere matiere6 = new Matiere("Gestion De Projet", 20,  coordinateur1);

        Filiere filiere1=new Filiere("Info2","2éme année",60,coordinateur1);
        Filiere filiere2=new Filiere("Meca3","3éme année",40,coordinateur1);

        Professeur professeur1=new Professeur("Quazdar","Imade","Quazdar.Imade@edu.uiz.ac.ma","1234","0654163",matiere1);
        Professeur professeur2=new Professeur("Chaouki","Brahim","Chaouki.Brahim@edu.uiz.ac.ma","1234","0654163",matiere2);
        Professeur professeur3=new Professeur("Bouzahir","Hassan","Bouzahir.Hassan@edu.uiz.ac.ma","1234","0654163",matiere3);
        Professeur professeur4=new Professeur("Tourabi","Amina","Tourabi.Amina@edu.uiz.ac.ma","1234","0654163",matiere4);
        Professeur professeur5=new Professeur("Bnouhachem","Lahcen","Bnouhachem.Lahcen@edu.uiz.ac.ma","1234","0654163",matiere5);
        Professeur professeur6=new Professeur("Tamtam","Fadoua","Tamtam.Fadoua@edu.uiz.ac.ma","1234","0654163",matiere6);

        Salle salle1=new Salle("F12","Bloc F","salle TD",25,responsableSalle);
        Salle salle2=new Salle("H11","Bloc H","salle Cours",60,responsableSalle);
        Salle salle3=new Salle("K2","block K","salle TP/info",35,responsableSalle);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        InfosRes infosRes = new InfosRes("Lundi", LocalDate.parse("12/01/2024", formatter), LocalDate.parse("12/01/2025", formatter));
        InfosRes infosRes1 = new InfosRes("Mardi", LocalDate.parse("12/01/2024", formatter), LocalDate.parse("12/01/2025", formatter));
        InfosRes infosRes2 = new InfosRes("Mercredi", LocalDate.parse("12/01/2024", formatter), LocalDate.parse("12/01/2025", formatter));
        InfosRes infosRes3 = new InfosRes("Jeudi", LocalDate.parse("12/01/2024", formatter), LocalDate.parse("12/01/2025", formatter));
        InfosRes infosRes4 = new InfosRes("Vendredi", LocalDate.parse("12/01/2024", formatter), LocalDate.parse("12/01/2025", formatter));
        InfosRes infosRes5 = new InfosRes("Samedi", LocalDate.parse("12/01/2024", formatter), LocalDate.parse("12/01/2025", formatter));

        Creneau creneau=new Creneau("8:30 - 10:20",false,salle1);
        Creneau creneau1=new Creneau("10:40 - 12:30",false,salle2);
        Creneau creneau2=new Creneau("2:30 - 4:20",false,salle1);
        Creneau creneau3=new Creneau("8:30 - 10:20",false,salle2);
        Creneau creneau4=new Creneau("4:40 - 6:30",false,salle1);


        Reservation reservation1=new Reservation(filiere1,infosRes,professeur1,creneau);
        Reservation reservation2=new Reservation(filiere1,infosRes1,professeur2,creneau2);
        Reservation reservation3=new Reservation(filiere1,infosRes2,professeur3,creneau1);
        Reservation reservation4=new Reservation(filiere1,infosRes2,professeur4,creneau1);
        Reservation reservation5=new Reservation(filiere1,infosRes4,professeur5,creneau2);
        Reservation reservation6=new Reservation(filiere1,infosRes3,professeur6,creneau4);

        coordinateur.ajouterCoordinateur(coordinateur1);

        responsableService.ajouterResponsable(responsableSalle);

        salleService.ajouterSalle(salle1);
        salleService.ajouterSalle(salle2);

        filiereService.ajouterFiliere(filiere1);
        filiereService.ajouterFiliere(filiere2);

        matiere.ajouterMatiere(matiere1);
        matiere.ajouterMatiere(matiere2);
        matiere.ajouterMatiere(matiere3);
        matiere.ajouterMatiere(matiere4);
        matiere.ajouterMatiere(matiere5);
        matiere.ajouterMatiere(matiere6);

        professeurService.ajouterProfesseur(professeur1);
        professeurService.ajouterProfesseur(professeur2);
        professeurService.ajouterProfesseur(professeur3);
        professeurService.ajouterProfesseur(professeur4);
        professeurService.ajouterProfesseur(professeur5);
        professeurService.ajouterProfesseur(professeur6);

        creneauService.addCreneau(creneau);
        creneauService.addCreneau(creneau1);
        creneauService.addCreneau(creneau2);
        creneauService.addCreneau(creneau3);
        creneauService.addCreneau(creneau4);


        reservationService.ajouterReservation(reservation1);
        reservationService.ajouterReservation(reservation2);
        reservationService.ajouterReservation(reservation3);
        reservationService.ajouterReservation(reservation4);
        reservationService.ajouterReservation(reservation5);
        reservationService.ajouterReservation(reservation6);

        libDefService.LiberationAuto() ;


    }
}