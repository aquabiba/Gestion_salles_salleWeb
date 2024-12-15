<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8"/>
    <title>Accès Refusé</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f7f7f7;
            margin: 0;
            padding: 0;
            text-align: center;
        }
        .container {
            margin: 50px auto;
            width: 500px;
            background: #fff;
            border: 1px solid #ddd;
            padding: 30px;
            box-shadow: 0px 0px 5px #ccc;
        }
        h1 {
            color: #c00;
        }
        p {
            margin: 20px 0;
            line-height: 1.5em;
        }
        a.button {
            display: inline-block;
            text-decoration: none;
            background: #0066cc;
            color: #fff;
            padding: 10px 20px;
            border-radius: 4px;
        }
        a.button:hover {
            background: #004080;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Accès Refusé</h1>
    <p>Vous n'avez pas les autorisations nécessaires pour accéder à cette page.</p>
    <p><a class="button" href="<%= request.getContextPath() %>/log">Retour à la page de connexion</a></p>
</div>
</body>
</html>