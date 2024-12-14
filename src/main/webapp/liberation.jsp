<%@ page import="model.Reservation" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
  <title>Gestion des Réservations</title>
  <style>
    body {
      min-height: 100vh;
    }

    #container {
      display: flex;
      justify-content: flex-start;
    }

    .table-container {
      width: 100%;
      margin-left: 350px;
      padding: 30px;
    }

    table {
      width: 100%;
      border-collapse: collapse;
      margin-bottom: 20px;
    }

    th, td {
      border: 1px solid #ddd;
      padding: 10px;
      text-align: center;
    }

    th {
      background-color: #f4f4f4;
    }

    .form-container {
      display: flex;
      justify-content: center;
      gap: 15px;
      margin-top: 20px;
    }

    .form-container input {
      padding: 8px;
      font-size: 14px;
      width: 200px;
    }

    .form-container button {
      padding: 10px 20px;
      font-size: 14px;
      background-color: #007BFF;
      color: #fff;
      border: none;
      cursor: pointer;
      border-radius: 5px;
      transition: background-color 0.3s ease;
    }

    .form-container button:hover {
      background-color: #0056b3;
    }

    .form-container .danger {
      background-color: #f44336;
    }

    .form-container .danger:hover {
      background-color: #d32f2f;
    }
  </style>
</head>
<%
  String prof_name=(String) session.getAttribute("prof_name") ;
  List<Reservation> reservations= (List<Reservation>) session.getAttribute("reservations") ;
%>
<body>
<div id="container">
  <!-- Sidebar -->
  <div class="d-flex flex-column flex-shrink-0 p-3" style="position: fixed; width: 350px; height: 900px; background-color: rgb(214, 95, 95);">
    <a href="/" class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-white text-decoration-none">
      <span class="fs-4">Prof <%=prof_name%></span>
    </a>
    <hr>
    <ul class="nav nav-pills flex-column mb-auto">
      <li  >
        <a href="ListReservation.jsp"  class="nav-link text-white" >Liste Réservations</a>
      </li>
      <li>
        <a href="Prof.jsp" id="Home" class="nav-link text-white" >Ajouter Réservation</a>
      </li>
      <li class="nav-item" >
        <a href="liberation.jsp" id="liberation"  class="nav-link active"  >Libération</a>
      </li>
      <li>
        <a href="Auth.jsp" id="Logout" class="nav-link text-white">Déconnexion</a>
      </li>
    </ul>
  </div>

  <!-- Main Content -->
  <div class="table-container">
    <h2>Liste des Réservations</h2>
    <table>
      <thead>
      <tr>
        <th>ID</th>
        <th>Filière</th>
        <th>Matière</th>
        <th>Salle</th>
        <th>Créneaux</th>
        <th>Date Début</th>
        <th>Date Fin</th>
      </tr>
      </thead>
      <% for(Reservation reservation:reservations){%>

      <tbody>
      <th><%=reservation.getId_res()%></th>
      <th><%=reservation.getFiliere().getLibelle_fil()%></th>
      <th><%=reservation.getProfesseur().getMatiere().getLibelle_mat()%></th>
      <th><%=reservation.getCreneau().getSalle().getNom_sal()%></th>
      <th><%=reservation.getCreneau().getDesc_creneau()%></th>
      <th><%=reservation.getInfos_res().getDateDebut()%></th>
      <th><%=reservation.getInfos_res().getDateFin()%></th>
      </tbody>
      <% }%>

    </table>
    <form action="liberation" method="post">
      <div class="form-container">
        <input type="text" id="reservationId" name="idReservation" placeholder="ID" >
        <input type="date" id="startDate" name="dateDebut" placeholder="Date de Début">
        <input type="date" id="endDate" name="dateFin" placeholder="Date de Fin">
        <button type="submit" onclick="liberationDefinitive()" name="liberationDef">Libération Définitive</button>
        <button type="submit" class="danger" onclick="liberationExceptionnelle()" name="liberationExce">Libération Exceptionnelle</button>

      </div>
      <%
        if(session.getAttribute("message")!=null){
      %>
      <script>
        alert("<%= session.getAttribute("message") %>");
      </script>

      <%
          session.removeAttribute("message");
        }%>
    </form>
  </div>
</div>


</body>
</html>