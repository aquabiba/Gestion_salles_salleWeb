<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <title>Responsable de Salle</title>
    <style>
        body {
            min-height: 100vh;
            font-family: "Tilt Neon" ;
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
        .btn-toggle-nav a {
            padding: .1875rem .5rem;
            margin-top: .125rem;
            margin-left: 1.25rem;
        }
        .btn-toggle-nav a:hover,
        .btn-toggle-nav a:focus {

        }

        #About:hover {
            background-color: #0d6efd;
        }

        #container {
            display: flex;
            justify-content: flex-start;
        }

        .reservation-container {
            width: 100%;
            margin-left: 650px;
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

        .form-group input,
        .form-group select {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 14px;
        }

        .form-group input:focus,
        .form-group select:focus {
            border-color: #4CAF50;
            outline: none;
        }

        .form-buttons {
            display: flex;
            justify-content: space-between;
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
    </style>
    <%
        String Responom=(String) session.getAttribute("responsableNom") ;
        String role = (String) session.getAttribute("userRole");
        if (role == null) {
            response.sendRedirect(request.getContextPath() + "/shared/Auth.jsp");
            return;
        }
        System.out.println(role);
    %>
</head>
<body>
<div id="container">
    <div class="d-flex flex-column flex-shrink-0 p-3" style="position: fixed; width: 350px; height: 900px; background-color: rgb(214, 95, 95);">
        <a href="/" class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-white text-decoration-none">
            <span class="fs-4">Bonjour <%=Responom%>
            </span>
        </a>
        <hr>
        <ul class="nav nav-pills flex-column mb-auto">
            <li>
                <a href="${pageContext.request.contextPath}/responsable/Respo_home.jsp" class="nav-link text-white">Home</a>
            </li>

            <li class="nav-item">
                <a href="${pageContext.request.contextPath}/salle" id="Gestion_sal" class="nav-link active" aria-current="page">Gestion Des Salles</a>
            </li>

            <li>

                <a href="${pageContext.request.contextPath}/log" id="Logout" class="nav-link text-white">Logout</a>
            </li>
        </ul>
    </div>

    <form class="reservation-container" action="salle" method="post">
        <h2>Gestion des Salles</h2>
        <div class="form-group">
            <label for="nomSalle">Nom de la Salle</label>
            <input type="text" id="nomSalle" name="nomSalle" placeholder="Entrez le nom de la salle" required>
        </div>
        <div class="form-group">
            <label for="localisation">Localisation</label>
            <input type="text" id="localisation" name="localisation" placeholder="Entrez la localisation" required>
        </div>
        <div class="form-group">
            <label for="typeSalle">Type de Salle</label>
            <select id="typeSalle" name="typeSalle" required>
                <option value="" disabled selected>Choisissez un type</option>
                <option value="Amphi">Amphi</option>
                <option value="Salle TD">Salle TD</option>
                <option value="Salle TP">Salle TP</option>
            </select>
        </div>
        <div class="form-group">
            <label for="capacite">Capacité</label>
            <input type="number" id="capacite" name="capacite" placeholder="Entrez la capacité de la salle" min="1" required>
        </div>
        <div class="form-buttons">
            <button  class="add" name="enregistrer">Enregistrer</button>
            <button  class="reset" name="supprimer">Supprimer</button>
        </div>

<%
    String message= (String) session.getAttribute("message");
    if(message!=null){
        session.removeAttribute("message");
%>
<script>
    alert("<%= message %>");
</script>

<%}%>
    </form>
</div>

</body>
</html>