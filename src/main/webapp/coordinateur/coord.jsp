
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
</head>
<body>
<%
    String coord = (String) session.getAttribute("coordinateurname");
    String role = (String) session.getAttribute("userRole");
    if (role == null) {
        response.sendRedirect(request.getContextPath() + "/log");
        return;
    }
    System.out.println(role);
%>
<div id="container">
    <div class="d-flex flex-column flex-shrink-0 p-3" style="position: fixed; width: 350px; height: 900px; background-color: rgb(214, 95, 95);">
        <a href="" class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-white text-decoration-none">
            <span class="fs-4">Bonjour <%=coord%> </span>
        </a>
        <hr>
        <ul class="nav nav-pills flex-column mb-auto">
            <li class="nav-item">
                <a href="${pageContext.request.contextPath}/coord" id="Home" class="nav-link active" >Filières</a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/empl" id="emploi"  class="nav-link text-white">Emplois du Temps</a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/mat"  id="Matiére"  class="nav-link text-white">Matiére</a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/log" id="Logout" class="nav-link text-white">Logout</a>
            </li>
        </ul>

    </div>

    <form class="reservation-container" action="coord" method="post">
        <div class="reservation-container">
            <h2>Coordinateur de Filière</h2>
            <form>
                <div class="form-group">
                    <label for="nomFiliere">Nom de la Filière</label>
                    <input type="text" id="nomFiliere" name="nomFiliere" placeholder="Entrez le nom de la filière" required>
                </div>
                <div class="form-group">
                    <label for="effectif">Effectif</label>
                    <input type="number" id="effectif" name="effectif" placeholder="Entrez l'effectif" required>
                </div>
                <div class="form-group">
                    <label for="niveau">Niveau</label>
                    <select id="niveau" name="niveau" class="form-control" required>
                        <option value="" disabled selected>Choisissez un niveau</option>
                        <option value="1ere annee">1ere annee</option>
                        <option value="2eme annee">2eme annee</option>
                        <option value="3eme annee">3eme annee</option>
                        <option value="4eme annee">4eme annee</option>
                        <option value="5eme annee">5eme annee</option>

                    </select>
                </div>

                <div class="form-buttons">
                    <button type="submit" class="add" name="ajouter">Ajouter</button>
                    <button type="reset" class="reset" name="supprimer">Supprimer</button>
                </div>
            </form>
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

<body>

</body>
</html>