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
        Filiere filiere3=new Filiere("Electrica","2eme annee",35,coordinateur1);

        Professeur professeur1=new Professeur("Quazdar","Imade","Quazdar.Imade@edu.uiz.ac.ma","1234","0654163",matiere1);
        Professeur professeur2=new Professeur("Chaouki","Brahim","Chaouki.Brahim@edu.uiz.ac.ma","1234","0654163",matiere2);
        Professeur professeur3=new Professeur("Bouzahir","Hassan","Bouzahir.Hassan@edu.uiz.ac.ma","1234","0654163",matiere3);
        Professeur professeur4=new Professeur("Tourabi","Amina","Tourabi.Amina@edu.uiz.ac.ma","1234","0654163",matiere4);
        Professeur professeur5=new Professeur("Bnouhachem","Lahcen","Bnouhachem.Lahcen@edu.uiz.ac.ma","1234","0654163",matiere5);
        Professeur professeur6=new Professeur("Tamtam","Fadoua","Tamtam.Fadoua@edu.uiz.ac.ma","1234","0654163",matiere6);

        Salle salle1=new Salle("F12","Bloc F","salle TD",25,responsableSalle);
        Salle salle2=new Salle("H11","Bloc H","salle Cours",60,responsableSalle);
        Salle salle300=new Salle("K2","block K","salle TP/info",35,responsableSalle);
//        Salle salle1 = new Salle("A1", "Bloc A", "salle Cours", 60, responsableSalle);
//        Salle salle2 = new Salle("B1", "Bloc B", "salle Cours", 60, responsableSalle);
//        Salle salle3 = new Salle("C1", "Bloc C", "salle Cours", 60, responsableSalle);
//        Salle salle4 = new Salle("D1", "Bloc D", "salle Cours", 60, responsableSalle);
//        Salle salle5 = new Salle("E1", "Bloc E", "salle Cours", 60, responsableSalle);
//        Salle salle6 = new Salle("F1", "Bloc F", "salle Cours", 60, responsableSalle);
//        Salle salle7 = new Salle("G1", "Bloc G", "salle Cours", 60, responsableSalle);
//        Salle salle8 = new Salle("H1", "Bloc H", "salle Cours", 60, responsableSalle);
//        Salle salle9 = new Salle("I1", "Bloc I", "salle Cours", 60, responsableSalle);
//        Salle salle10 = new Salle("J1", "Bloc J", "salle Cours", 60, responsableSalle);
//
//        Salle salle11 = new Salle("K1", "Bloc K", "salle TP", 35, responsableSalle);
//        Salle salle12 = new Salle("L1", "Bloc L", "salle TP", 35, responsableSalle);
//        Salle salle13 = new Salle("M1", "Bloc M", "salle TP", 35, responsableSalle);
//        Salle salle14 = new Salle("N1", "Bloc N", "salle TP", 35, responsableSalle);
//        Salle salle15 = new Salle("O1", "Bloc O", "salle TP", 35, responsableSalle);
//
//        Salle salle16 = new Salle("P1", "Bloc P", "salle TD", 25, responsableSalle);
//        Salle salle17 = new Salle("Q1", "Bloc Q", "salle TD", 25, responsableSalle);
//        Salle salle18 = new Salle("R1", "Bloc R", "salle TD", 25, responsableSalle);
//        Salle salle19 = new Salle("S1", "Bloc S", "salle TD", 25, responsableSalle);
//        Salle salle20 = new Salle("T1", "Bloc T", "salle TD", 25, responsableSalle);
        for (int i = 1; i <= 10; i++) {
            Salle salle = new Salle("H" + i, "Bloc " + (char) ('A' + i - 1), "salle Cours", 60, responsableSalle);
            salleService.ajouterSalle(salle); // salleService.ajouterSalle(salle1)
        }

        for (int i = 1; i <= 5; i++) {
            Salle salle = new Salle("K" + i, "Bloc " + (char) ('K' + i - 1), "salle TP", 35, responsableSalle);
            salleService.ajouterSalle(salle); // salleService.ajouterSalle(salle2)
        }

        for (int i = 1; i <= 5; i++) {
            Salle salle = new Salle("F" + i, "Bloc " + (char) ('P' + i - 1), "salle TD", 25, responsableSalle);
            salleService.ajouterSalle(salle); // salleService.ajouterSalle(salle3)
        }


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
        Reservation reservation2=new Reservation(filiere1,infosRes1,professeur1,creneau2);
        Reservation reservation3=new Reservation(filiere1,infosRes2,professeur1,creneau1);
        Reservation reservation4=new Reservation(filiere1,infosRes2,professeur4,creneau1);
        Reservation reservation5=new Reservation(filiere1,infosRes4,professeur5,creneau2);
        Reservation reservation6=new Reservation(filiere1,infosRes3,professeur1,creneau4);
        Reservation reservation7=new Reservation(filiere3,infosRes5,professeur1,creneau3);


        coordinateur.ajouterCoordinateur(coordinateur1);

        responsableService.ajouterResponsable(responsableSalle);

        salleService.ajouterSalle(salle1);
        salleService.ajouterSalle(salle2);
        salleService.ajouterSalle(salle300);

//        salleService.ajouterSalle(salle1);
//        salleService.ajouterSalle(salle2);
//        salleService.ajouterSalle(salle3);
//        salleService.ajouterSalle(salle4);
//        salleService.ajouterSalle(salle5);
//        salleService.ajouterSalle(salle6);
//        salleService.ajouterSalle(salle7);
//        salleService.ajouterSalle(salle8);
//        salleService.ajouterSalle(salle9);
//        salleService.ajouterSalle(salle10);
//        salleService.ajouterSalle(salle11);
//        salleService.ajouterSalle(salle12);
//        salleService.ajouterSalle(salle13);


        filiereService.ajouterFiliere(filiere1);
        filiereService.ajouterFiliere(filiere2);
        filiereService.ajouterFiliere(filiere3);

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
        reservationService.ajouterReservation(reservation7);

        libDefService.LiberationAuto() ;


    }
}