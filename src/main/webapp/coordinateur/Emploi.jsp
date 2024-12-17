<%@ page import="java.util.List" %>
<%@ page import="model.Filiere" %>
<%@ page import="java.util.Map" %>
<%@ page import="model.Reservation" %>
<%@ page import="model.Matiere" %><%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 23/11/2024
  Time: 12:19
  To change this template use File | Settings | File Templates.
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/html2pdf.js/0.9.2/html2pdf.bundle.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <title>Coordinateur Filiere Interface</title>
    <style>
        body {
            min-height: 100vh;
        }


        h2 {
            font-size: 24px;
            font-weight: bold;
            margin-bottom: 20px;
            color: #333;
        }

        main {
            height: 400px;
            max-height: 100vh;
            overflow-x: auto;
            overflow-y: hidden;
        }


        #Logout:hover {
            background-color: #0d6efd;
        }
        [data-bs-theme="dark"] .btn-toggle::before {
            content: url("data:image/svg+xml,%3csvg xmlns='http://www.w3.org/2000/svg' width='16' height='16' viewBox='0 0 16 16'%3e%3cpath fill='none' stroke='rgba%28255,255,255,.5%29' stroke-linecap='round' stroke-linejoin='round' stroke-width='2' d='M5 14l6-6-6-6'/%3e%3c/svg%3e");
        }
        .btn-toggle-nav a {
            padding: .1875rem .5rem;
            margin-top: .125rem;
            margin-left: 1.25rem;
        }
        .btn-toggle-nav a:hover,
        .btn-toggle-nav a:focus {

        }


        #container{
            display: flex;
            justify-content:flex-start;
        }

        .reservation-container h2 {
            text-align: center;
            margin-bottom: 20px;
            font-size: 24px;
            color: #333;
        }

        .form-group label {
            display: block;
            margin-bottom: 8px;
            font-weight: bold;
            color: #555;
        }

        .form-group input {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 14px;
        }

        .form-group input:focus {
            border-color: #4CAF50;
            outline: none;
        }

        .form-buttons button {
            padding: 10px 20px;
            font-size: 16px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        .table th, .table td {
            text-align: center;
            vertical-align: middle;
        }

        .btn-create {
            margin-top: 20px;
        }

        .day-column {
            width: 12.5%; /* 100% / 8 hours = 12.5% per column */
        }

        .time-column {
            width: 12.5%;
        }

        .form-group input,
        .form-group select {
            width: 50%; /* Réduction de la largeur à 50% */
            margin-left: 500px ;/* Centrer les champs */
            padding: 5px; /* Réduire l'espacement interne */
            font-size: 13px; /* Réduire la taille de la police */
        }

        #schedule-table {
            width: 70%;
            margin-left: 360px ;/* Table spans 90% of the page width */
            border-collapse: collapse;
            box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1);
            font-family: Arial, sans-serif;
            background-color: #fff; /* White background */
        }

        table {
            text-align: center; /* Center the content horizontally */
            margin-left: 160px ;
            width: 40% ;
            padding: 20px;
            background-color: white;
            border: 1px solid #ccc;
            border-radius: 8px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
        }

        #schedule-title {
            font-size: 28px;
            font-weight: bold;
            margin-bottom: 20px;
            color: #333;
        }

        .table {/
             /*    margin-left: 450px ;*/
             /*    border: 1px solid #ddd;*/
             /*    border-collapse: collapse;*/
             /*    width: 80%;*/
         /}

        .table th, .table td {
             /*    padding: 10px;*/
             /*    text-align: center;*/
             /*    border: 1px solid #ddd;*/
         }

        .table th {
            background-color: #f4f4f4;
            font-weight: bold;
        }

    </style>
</head>
<%
    @SuppressWarnings("unchecked")
    List<Filiere> filieres = (List<Filiere>) session.getAttribute("filieres");
    String coordName=(String) session.getAttribute("coordinateurname") ;
//    List<Reservation> reservations=(List<Reservation>) session.getAttribute("reservattions") ;
    Reservation reservation1_1= (Reservation) session.getAttribute("reservation1-1");
    Reservation reservation1_2= (Reservation) session.getAttribute("reservation1-2");
    Reservation reservation1_3= (Reservation) session.getAttribute("reservation1-3");
    Reservation reservation1_4= (Reservation) session.getAttribute("reservation1-4");
    Reservation reservation1_5= (Reservation) session.getAttribute("reservation1-5");
    Reservation reservation1_6= (Reservation) session.getAttribute("reservation1-6");

    Reservation reservation2_1= (Reservation) session.getAttribute("reservation2-1");
    Reservation reservation2_2= (Reservation) session.getAttribute("reservation2-2");
    Reservation reservation2_3= (Reservation) session.getAttribute("reservation2-3");
    Reservation reservation2_4= (Reservation) session.getAttribute("reservation2-4");
    Reservation reservation2_5= (Reservation) session.getAttribute("reservation2-5");
    Reservation reservation2_6= (Reservation) session.getAttribute("reservation2-6");

    Reservation reservation3_1= (Reservation) session.getAttribute("reservation3-1");
    Reservation reservation3_2= (Reservation) session.getAttribute("reservation3-2");
    Reservation reservation3_3= (Reservation) session.getAttribute("reservation3-3");
    Reservation reservation3_4= (Reservation) session.getAttribute("reservation3-4");
    Reservation reservation3_5= (Reservation) session.getAttribute("reservation3-5");
    Reservation reservation3_6= (Reservation) session.getAttribute("reservation3-6");

    Reservation reservation4_1= (Reservation) session.getAttribute("reservation4-1");
    Reservation reservation4_2= (Reservation) session.getAttribute("reservation4-2");
    Reservation reservation4_3= (Reservation) session.getAttribute("reservation4-3");
    Reservation reservation4_4= (Reservation) session.getAttribute("reservation4-4");
    Reservation reservation4_5= (Reservation) session.getAttribute("reservation4-5");
    Reservation reservation4_6= (Reservation) session.getAttribute("reservation4-6");
    String nomFil = (String) session.getAttribute("filiere");

%>
<body>
<div id="container">
    <div class="d-flex flex-column flex-shrink-0 p-3" style="position: fixed; width: 350px; height: 900px; background-color: rgb(214, 95, 95);">
        <a href="#" class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-white text-decoration-none">
            <span class="fs-4">Bonjour <%=coordName%></span>
        </a>
        <hr>
        <ul class="nav nav-pills flex-column mb-auto">
            <li class="nav-item">
                <a href="${pageContext.request.contextPath}/coord"  class="nav-link text-white" aria-current="page">Filières</a>
            </li>
            <li>
                <a href="#" id="emploi"   class="nav-link active" >Emplois du Temps</a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/mat"  id="Matiére"  class="nav-link text-white" >Matiére</a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/log" id="Logout" class="nav-link text-white">Logout</a>
            </li>
        </ul>

    </div>
</div>

<form id="Schedule" action="<%= request.getContextPath() %>/empl" method="post">

        <h2>Formulaire de Réservation</h2>
        <div class="form-group">
            <label for="filiere">Filière</label>
            <select id="filiere" name="filiere" class="form-control" required>
                <option value="" disabled selected>Choisissez une filière</option>
                <% for(Filiere filiere:filieres){%>
                <option value="<%=filiere.getLibelle_fil()%>"><%=filiere.getLibelle_fil()%></option>
                <%}%>
            </select>
            <button id="download-pdf1" name="afficherEmploiFiliere" class="btn btn-primary btn-create" style="margin-left: 750px ; width :250px;text-align: center">Afficher Emploi</button>
        </div>



    <h2 class="text-center mt-4">Emploi du Temps </h2>

    <table id="schedule-table" class="table table-bordered mt-4">
        <thead>
        <tr>
            <th>Horaires</th>
            <th class="day-column">Lundi</th>
            <th class="day-column">Mardi</th>
            <th class="day-column">Mercredi</th>
            <th class="day-column">Jeudi</th>
            <th class="day-column">Vendredi</th>
            <th class="day-column">Samedi</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td class="time-column">8:30 - 10:20</td>
            <%
                if(reservation1_1!=null){
            %><td><%=reservation1_1.getProfesseur().getMatiere().getLibelle_mat()%><br><%=reservation1_1.getProfesseur().getNom_Ut()%><br><%=reservation1_1.getCreneau().getSalle().getNom_sal()%></td>
            <%}else{
            %><td></td>
            <%}%>
            <%
                if(reservation1_2!=null){
            %><td><%=reservation1_2.getProfesseur().getMatiere().getLibelle_mat()%><br><%=reservation1_2.getProfesseur().getNom_Ut()%><br><%=reservation1_2.getCreneau().getSalle().getNom_sal()%>/td>
            <%}else{
            %><td></td>
            <%}%>
            <%
                if(reservation1_3!=null){
            %><td><%=reservation1_3.getProfesseur().getMatiere().getLibelle_mat()%><br><%=reservation1_3.getProfesseur().getNom_Ut()%><br><%=reservation1_3.getCreneau().getSalle().getNom_sal()%></td>
            <%}else{
            %><td></td>
            <%}%>
            <%
                if(reservation1_4!=null){
            %><td><%=reservation1_4.getProfesseur().getMatiere().getLibelle_mat()%><br><%=reservation1_4.getProfesseur().getNom_Ut()%><br><%=reservation1_4.getCreneau().getSalle().getNom_sal()%></td>
            <%}else{
            %><td></td>
            <%}%>
            <%
                if(reservation1_5!=null){
            %><td><%=reservation1_5.getProfesseur().getMatiere().getLibelle_mat()%><br><%=reservation1_5.getProfesseur().getNom_Ut()%><br><%=reservation1_5.getCreneau().getSalle().getNom_sal()%></td>
            <%}else{
            %><td></td>
            <%}%>
            <%
                if(reservation1_6!=null){
            %><td><%=reservation1_6.getProfesseur().getMatiere().getLibelle_mat()%><br><%=reservation1_6.getProfesseur().getNom_Ut()%><br><%=reservation1_6.getCreneau().getSalle().getNom_sal()%></td>
            <%}else{
            %><td></td>
            <%}%>
        </tr>

        <tr>
            <td class="time-column">10:20 - 12:30</td>
            <%
                if(reservation2_1!=null){
            %><td><%=reservation2_1.getProfesseur().getMatiere().getLibelle_mat()%><br><%=reservation2_1.getProfesseur().getNom_Ut()%><br><%=reservation2_1.getCreneau().getSalle().getNom_sal()%></td>
            <%}else{
            %><td></td>
            <%}%>
            <%
                if(reservation2_2!=null){
            %><td><%=reservation2_2.getProfesseur().getMatiere().getLibelle_mat()%><br><%=reservation2_2.getProfesseur().getNom_Ut()%><br><%=reservation2_2.getCreneau().getSalle().getNom_sal()%></td>
            <%}else{
            %><td></td>
            <%}%>
            <%
                if(reservation2_3!=null){
            %><td><%=reservation2_3.getProfesseur().getMatiere().getLibelle_mat()%><br><%=reservation2_3.getProfesseur().getNom_Ut()%><br><%=reservation2_3.getCreneau().getSalle().getNom_sal()%></td>
            <%}else{
            %><td></td>
            <%}%>
            <%
                if(reservation2_4!=null){
            %><td><%=reservation2_4.getProfesseur().getMatiere().getLibelle_mat()%><br><%=reservation2_4.getProfesseur().getNom_Ut()%><br><%=reservation2_4.getCreneau().getSalle().getNom_sal()%></td>
            <%}else{
            %><td></td>
            <%}%>
            <%
                if(reservation2_5!=null){
            %><td><%=reservation2_5.getProfesseur().getMatiere().getLibelle_mat()%><br><%=reservation2_5.getProfesseur().getNom_Ut()%><br><%=reservation2_5.getCreneau().getSalle().getNom_sal()%></td>
            <%}else{
            %><td></td>
            <%}%>
            <%
                if(reservation2_6!=null){
            %><td><%=reservation2_6.getProfesseur().getMatiere().getLibelle_mat()%><br><%=reservation2_6.getProfesseur().getNom_Ut()%><br><%=reservation2_6.getCreneau().getSalle().getNom_sal()%></td>
            <%}else{
            %><td></td>
            <%}%>
        </tr>

        <tr>
            <td class="time-column">2:30 - 4:20</td>
            <%
                if(reservation3_1!=null){
            %><td><%=reservation3_1.getProfesseur().getMatiere().getLibelle_mat()%><br><%=reservation3_1.getProfesseur().getNom_Ut()%><br><%=reservation3_1.getCreneau().getSalle().getNom_sal()%></td>
            <%}else{
            %><td></td>
            <%}%>
            <%
                if(reservation3_2!=null){
            %><td><%=reservation3_2.getProfesseur().getMatiere().getLibelle_mat()%><br><%=reservation3_2.getProfesseur().getNom_Ut()%><br><%=reservation3_2.getCreneau().getSalle().getNom_sal()%></td>
            <%}else{
            %><td></td>
            <%}%>
            <%
                if(reservation3_3!=null){
            %><td><%=reservation3_3.getProfesseur().getMatiere().getLibelle_mat()%><br><%=reservation3_3.getProfesseur().getNom_Ut()%><br><%=reservation3_3.getCreneau().getSalle().getNom_sal()%></td>
            <%}else{
            %><td></td>
            <%}%>
            <%
                if(reservation3_4!=null){
            %><td><%=reservation3_4.getProfesseur().getMatiere().getLibelle_mat()%><br><%=reservation3_4.getProfesseur().getNom_Ut()%><br><%=reservation3_4.getCreneau().getSalle().getNom_sal()%></td>
            <%}else{
            %><td></td>
            <%}%>
            <%
                if(reservation3_5!=null){
            %><td><%=reservation3_5.getProfesseur().getMatiere().getLibelle_mat()%><br><%=reservation3_5.getProfesseur().getNom_Ut()%><br><%=reservation3_5.getCreneau().getSalle().getNom_sal()%></td>
            <%}else{
            %><td></td>
            <%}%>
            <%
                if(reservation3_6!=null){
            %><td><%=reservation3_6.getProfesseur().getMatiere().getLibelle_mat()%><br><%=reservation3_6.getProfesseur().getNom_Ut()%><br><%=reservation3_6.getCreneau().getSalle().getNom_sal()%></td>
            <%}else{
            %><td></td>
            <%}%>
        </tr>
        <tr>
            <td class="time-column">4:20 - 6:30</td>
            <%
                if(reservation4_1!=null){
            %><td><%=reservation4_1.getProfesseur().getMatiere().getLibelle_mat()%><br><%=reservation4_1.getProfesseur().getNom_Ut()%><br><%=reservation4_1.getCreneau().getSalle().getNom_sal()%></td>
            <%}else{
            %><td></td>
            <%}%>
            <%
                if(reservation4_2!=null){
            %><td><%=reservation4_2.getProfesseur().getMatiere().getLibelle_mat()%><br><%=reservation4_2.getProfesseur().getNom_Ut()%><br><%=reservation4_2.getCreneau().getSalle().getNom_sal()%></td>
            <%}else{
            %><td></td>
            <%}%>
            <%
                if(reservation4_3!=null){
            %><td><%=reservation4_3.getProfesseur().getMatiere().getLibelle_mat()%><br><%=reservation4_3.getProfesseur().getNom_Ut()%><br><%=reservation4_3.getCreneau().getSalle().getNom_sal()%></td>
            <%}else{
            %><td></td>
            <%}%>
            <%
                if(reservation4_4!=null){
            %><td><%=reservation4_4.getProfesseur().getMatiere().getLibelle_mat()%><br><%=reservation4_4.getProfesseur().getNom_Ut()%><br><%=reservation4_4.getCreneau().getSalle().getNom_sal()%></td>
            <%}else{
            %><td></td>
            <%}%>
            <%
                if(reservation4_5!=null){
            %><td><%=reservation4_5.getProfesseur().getMatiere().getLibelle_mat()%><br><%=reservation4_5.getProfesseur().getNom_Ut()%><br><%=reservation4_5.getCreneau().getSalle().getNom_sal()%></td>
            <%}else{
            %><td></td>
            <%}%>
            <%
                if(reservation4_6!=null){
            %><td><%=reservation4_6.getProfesseur().getMatiere().getLibelle_mat()%><br><%=reservation4_6.getProfesseur().getNom_Ut()%><br><%=reservation4_6.getCreneau().getSalle().getNom_sal()%></td>
            <%}else{
            %><td></td>
            <%}%>
        </tr>
        </tbody>
    </table>
    <%

        String niv = (String) session.getAttribute("niveau");
    %>
    <div class="text-center">
        <button id="download-pdf" class="btn btn-primary btn-create" name="envoieMail">Envoyer L'emploi aux Professeurs</button>
    </div>


<script>
    document.getElementById("download-pdf").addEventListener("click", function (event) {
        event.preventDefault();
        try {
            // Créer un conteneur principal pour le PDF
            const pdfContainer = document.createElement("div");

            // Ajouter un titre pour le PDF
            const title = document.createElement("h1");
            title.textContent = "Emploi du Temps de la filière : <%=nomFil%>"; // Titre
            title.style.textAlign = "center";
            title.style.fontSize = "22px";
            title.style.marginBottom = "20px";
            title.style.fontFamily = "Arial, sans-serif";
            title.style.color = "#333";

            // Cloner le tableau existant
            const originalTable = document.getElementById("schedule-table");
            const clonedTable = originalTable.cloneNode(true);

            // Appliquer les styles pour le tableau dans le PDF
            clonedTable.style.width = "100%";
            clonedTable.style.margin = "0 auto";
            clonedTable.style.border = "1px solid black";
            clonedTable.style.borderCollapse = "collapse";
            clonedTable.style.fontFamily = "Arial, sans-serif";
            clonedTable.style.backgroundColor = "#ffffff";

            const thElements = clonedTable.querySelectorAll("th");
            thElements.forEach(th => {
                th.style.backgroundColor = "#333333";
                th.style.color = "#ffffff";
                th.style.padding = "10px";
                th.style.border = "1px solid black";
            });

            const tdElements = clonedTable.querySelectorAll("td");
            tdElements.forEach(td => {
                td.style.padding = "8px";
                td.style.border = "1px solid black";
                td.style.color = "#333333";
            });

            // Ajouter le titre et le tableau dans le conteneur PDF
            pdfContainer.appendChild(title);
            pdfContainer.appendChild(clonedTable);

            // Options pour html2pdf
            const options = {
                margin: 1,
                filename: 'Emploi_Temps_<%=nomFil%>.pdf',
                image: { type: 'jpeg', quality: 0.98 },
                html2canvas: { scale: 2 },
                jsPDF: { unit: 'cm', format: 'a4', orientation: 'landscape' }
            };

            // Générer le PDF
            html2pdf().set(options).from(pdfContainer).save();
        } catch (error) {
            console.error("Erreur lors de la génération du PDF :", error);
            alert("Une erreur est survenue lors de la génération du PDF.");
        }
    });
</script>


</form>

</body>
</html>