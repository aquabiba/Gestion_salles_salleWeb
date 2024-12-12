<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>SuperAdmin - Create User</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      margin: 20px;
      background-color: #f4f4f9;
    }
    form {
      max-width: 500px;
      margin: 0 auto;
      padding: 20px;
      background-color: white;
      box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
      border-radius: 8px;
    }
    label {
      display: block;
      margin-top: 10px;
      font-weight: bold;
    }
    input, select, button {
      width: 100%;
      padding: 8px;
      margin-top: 5px;
      border: 1px solid #ccc;
      border-radius: 4px;
    }
    .hidden {
      display: none;
    }
  </style>

</head>
<body>
<h1>SuperAdmin - Create User</h1>
<form action="CreateUserServlet" method="post">
  <label for="firstName">First Name:</label>
  <input type="text" id="firstName" name="firstName" required placeholder="Enter first name">

  <label for="lastName">Last Name:</label>
  <input type="text" id="lastName" name="lastName" required placeholder="Enter last name">

  <label for="email">Email Address:</label>
  <input type="email" id="email" name="email" required placeholder="Enter email address">

  <label for="phone">Phone Number:</label>
  <input type="tel" id="phone" name="phone" required placeholder="Enter phone number">

  <label for="password">Password:</label>
  <input type="password" id="password" name="password" required placeholder="Enter password">

  <label for="role">User Role:</label>
  <select id="role" name="role"  required>
    <option value="" disabled selected>Select role</option>
    <option value="Professeur">Professeur</option>
    <option value="Coordinnateur">Coordinnateur</option>
    <option value="ResponsableSalle">Responsable Salle</option>
  </select>

    <label for="matiere" class="hidden">Matière (For Professeur):</label>
    <input type="text" id="matiere" name="matiere"  placeholder="Enter matière">
  <button type="submit">Create User</button>
</form>
</body>
</html>
