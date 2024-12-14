<%@ page import="java.util.List" %>
<%@ page import="model.Filiere" %>
<%@ page import="java.util.Map" %><%--
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
    <title>Coordinateur Filiere Interface</title>
    <style>
        body {
            min-height: 100vh;
        }

        main {
            height: 400px;
            max-height: 100vh;
            overflow-x: auto;
            overflow-y: hidden;
        }

        #Home {
            background-color: #0d6efd;
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


        #About:hover{
            background-color: #0d6efd;
        }


        #container{
            display: flex;
            justify-content:flex-start;
        }

        .reservation-container {
            width: 100%;
            margin-left: 350px;
            max-width: 500px;
            background-color: white;
            padding: 30px;
            border-radius: 10px;
        }

        .reservation-container h2 {
            text-align: center;
            margin-bottom: 20px;
            font-size: 24px;
            color: #333;
        }

        .form-group {
            margin-bottom: 20px;
            text-align: left;
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

        .form-buttons button.add {
            background-color: #4CAF50;
            color: white;
        }

        .form-buttons button.add:hover {
            background-color: #45a049;
        }

        .form-buttons button.reset {
            background-color: #f44336;
            color: white;
        }

        .form-buttons button.reset:hover {
            background-color: #d32f2f;
        }

        .table th, .table td {
            text-align: center;
            vertical-align: middle;
        }
        .table {
            width: 70%;
            margin-top: 20px;
            margin-left: 400px;
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


    </style>
</head>
<body>
<div id="container">
    <div class="d-flex flex-column flex-shrink-0 p-3" style="position: fixed; width: 350px; height: 900px; background-color: rgb(214, 95, 95);">
        <a href="/" class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-white text-decoration-none">
            <span class="fs-4">Coordinateur Name</span>
        </a>
        <hr>
        <ul class="nav nav-pills flex-column mb-auto">
            <li class="nav-item">
                <a href="${pageContext.request.contextPath}/coord" id="Home" class="nav-link active" aria-current="page">Filières</a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/empl" id="emploi" onclick="changecolor()" class="nav-link text-white">Emplois du Temps</a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/mat"  id="Matiére" onclick="changecolor()" class="nav-link text-white">Matiére</a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/log" id="Logout" class="nav-link text-white">Logout</a>
            </li>
        </ul>

    </div>
</div>

<h2 class="text-center mt-4">Emploi du Temps</h2>
<form id="Schedule" action="empl" method="post">
    <!-- Saisie du nom de la filière -->
    <div class="form-group">
        <label for="filiere">Filière</label>
        <select id="filiere" name="filiere" class="form-control" required>
            <option value="" disabled selected>Choisissez une filière</option>
            <%
                @SuppressWarnings("unchecked")
                List<Filiere> filieres = (List<Filiere>) session.getAttribute("filieres");
                if (filieres != null) {
                    for (Filiere filiere : filieres) {
            %>
            <option value="<%= filiere.getId_fil() %>"><%= filiere.getLibelle_fil() %></option>
            <%
                    }
                }
            %>


        </select>
        <input type="submit" name="uploadEmploi" value="importer Emploi">
    </div>

    <table class="table table-bordered mt-4">
        <thead>
        <tr>
            <th class="time-column">Horaire</th>
            <th class="day-column">Lundi</th>
            <th class="day-column">Mardi</th>
            <th class="day-column">Mercredi</th>
            <th class="day-column">Jeudi</th>
            <th class="day-column">Vendredi</th>
            <th class="day-column">Samedi</th>
        </tr>
        </thead>
        <tbody>
        <%
            @SuppressWarnings("unchecked")
            Map<String, Map<String, Object[]>> timetable = (Map<String, Map<String, Object[]>>) session.getAttribute("timetable");
        %>
        <%-- Définissez les créneaux horaires disponibles --%>
        <c:forEach var="hour" items="${['8h30 - 10h20', '10h40 - 12h30', '14h30 - 16h20', '16h40 - 18h30']}">
            <tr>
                <td>${hour}</td>
                    <%-- Boucle sur les jours de la semaine --%>
                <c:forEach var="day" items="${['Lundi', 'Mardi', 'Mercredi', 'Jeudi', 'Vendredi', 'Samedi']}">
                    <td>
                        <c:choose>
                            <%-- Si des données existent pour ce créneau et ce jour, affichez-les --%>
                            <c:when test="${not empty timetable[day][hour]}">
                                <c:set var="data" value="${timetable[day][hour]}" />
                                Matière : ${data[0].libelleMat}<br />
                                Professeur : ${data[1].nom}<br />
                                Salle : ${data[2].nom}
                            </c:when>
                            <%-- Sinon, affichez un tiret pour indiquer qu'il n'y a pas de données --%>
                            <c:otherwise>
                                -
                            </c:otherwise>
                        </c:choose>
                    </td>
                </c:forEach>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</div>
<%
        String nomFil = (String) session.getAttribute("filiereName");
        String niv = (String) session.getAttribute("niveau");
%>
<div class="text-center">
    <button class="btn btn-primary btn-create" onclick="downloadPDF()">Créer un emploi du temps</button>
</div>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/2.4.0/jspdf.umd.min.js"></script>
    <script>
        async function downloadPDF() {
            const { jsPDF } = window.jspdf;

            // Capture the table
            const pdf = new jsPDF('p', 'pt', 'a4'); // Portrait, Points, A4 size
            const source = document.getElementById('Schedule');

            pdf.html(source, {
                callback: function (doc) {
                    doc.save('<%=nomFil%>_<%=niv%>%>.pdf'); // Save as PDF
                },
                x: 10,
                y: 10
            });
        }
    </script>
</form>

</body>
</html>