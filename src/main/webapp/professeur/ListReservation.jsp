<%@ page import="java.util.List" %>
<%@ page import="model.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <title>Enseignant Interface</title>
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
            margin-left: 650px;
            max-width: 500px;
            background-color: white;
            padding: 30px;
            border-radius: 10px;
        }
        .modify {
            background-color: darkorange;
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

        .form-buttons {
            display: flex;
            justify-content: space-evenly;
            padding-left: 20px;
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
            margin-left: 30px;
        }
        .form-buttons button.modify {
            color: white;
            margin-left: 30px;
        }

        .form-buttons button.add:hover {
            background-color: #45a049;
        }

        .form-buttons button.reset {
            background-color: #f44336;
            margin-left: 30px;
            color: white;
        }

        .form-buttons button.reset:hover {
            background-color: #d32f2f;
        }




    </style>
</head>
<body>
<%
    String prof_name=(String) session.getAttribute("NomProf") ;
    String role = (String) session.getAttribute("userRole");
    if (role == null) {
        response.sendRedirect(request.getContextPath() + "/shared/Auth.jsp");
        return;
    }
    System.out.println(role);
%>
<div id="container">
    <div class="d-flex flex-column flex-shrink-0 p-3" style="position: fixed; width: 350px; height: 900px; background-color: rgb(214, 95, 95);">
        <a href="/" class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-white text-decoration-none">
            <span class="fs-4">Bonjour <%=prof_name%></span>
        </a>
        <hr>
        <ul class="nav nav-pills flex-column mb-auto">
            <li class="nav-item">

                <a href="${pageContext.request.contextPath}/professeur/ListReservation.jsp" id="Home" class="nav-link active" aria-current="page">Liste Réservations</a>
            </li>
            <li >
                <a href="${pageContext.request.contextPath}/professeur/Prof.jsp" class="nav-link text-white" >Ajouter Réservations</a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/professeur/liberation.jsp" id="liberation" class="nav-link text-white" >Libération</a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/shared/Auth.jsp" id="Logout" class="nav-link text-white">Déconnexion</a>
            </li>
        </ul>

    </div>

    <div class="container" style="margin-left: 400px; margin-top: 50px;">
        <h2>Réservations Disponibles</h2>
        <table class="table table-striped">
            <thead>
            <tr>
                <th>Id</th>
                <th>Salle</th>
                <th>Date Debut</th>
                <th>Date Fin</th>
                <th>créaneau</th>
                <th>Status</th>
            </tr>
            </thead>
            <tbody>
            <%
                @SuppressWarnings("unchecked")
                List<Reservation> reservations = (List<Reservation>) session.getAttribute("reservations");

                if (reservations != null && !reservations.isEmpty()) {
                    for (Reservation reservation : reservations) {
            %>
            <tr>
                <td><%=reservation.getId_res() %></td>
                <%
                    Creneau creaneau=reservation.getCreneau() ;
                %>
                <td><%= reservation.getCreneau() != null && (reservation.getCreneau()).getSalle() != null
                        ? creaneau.getSalle().getNom_sal()
                        : "Salle non définie" %></td>
                <td><%= reservation.getInfos_res().getDateDebut() %></td>
                <td><%= reservation.getInfos_res().getDateFin() %></td>
                <td><%= reservation.getCreneau().getDesc_creneau()%></td>
                <% if(reservation.getInfos_res().isStatus_res()){ %>
                <td style="color: #4CAF50"><%=reservation.getInfos_res().isStatus_res()%></td>
                <% }else {%>
                <td style="color: #d32f2f"><%=reservation.getInfos_res().isStatus_res()%></td>
                <% }%>

            </tr>
            <%
                }
            } else {
            %>
            <tr>
                <td colspan="5" class="text-center">Aucune réservation disponible.</td>
            </tr>
            <% } %>
            </tbody>
        </table>
    </div>

</div>
</body>
</html>