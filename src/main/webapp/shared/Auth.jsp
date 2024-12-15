<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Tilt+Neon&display=swap" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <title>Login</title>
    <style>

        body{
            margin: 0;
            padding: 0;
            background-image: url(../images/Ensapic.png);
            background-repeat: no-repeat;
            background-attachment: fixed;
            background-position: center;
            background-size: cover;
            font-family: Verdana, Geneva, Tahoma, sans-serif;
            font-weight: bolder;
            color: white;
        }
        .content-wrapper {
            position: relative;
            z-index: 1; /* Content above background */
            filter: brightness(0.7); /* Apply filter to the content */
            padding: 20px;
            color: white;
        }


        #d2{
            margin: 160px ;
            width: 500px;
        }
        #d1 {
            margin: 160px 160px;
            font-size: 20px;
        }
        #container {
            display: flex;
            flex-direction: row;
        }
        .form-signin {
            max-width: 330px;
            padding: 1rem;
        }

        .form-signin .form-floating:focus-within {
            z-index: 2;
        }

        .form-signin input[type="email"] {
            margin-bottom: -1px;
            border-bottom-right-radius: 0;
            border-bottom-left-radius: 0;
        }
        #but1 {
            background-color: brown;
            color: white;
            font-weight: bolder;
        }
        #but1:hover{
            background-color: white;
            color: brown;
        }
        #create {
            color: brown;
        }
        #create:hover{
            color: white;
        }

        .form-signin input[type="password"] {
            margin-bottom: 10px;
            border-top-left-radius: 0;
            border-top-right-radius: 0;
        }
        #b0{
            width: 170px;
            height: 50px;
            border: 1px brown ;
            background-color: brown;
            border-radius: 5px;
            font-size: 20px;
            color: white;
        }
    </style>
</head>
<body>
<%
//    String role = (String) session.getAttribute("userRole");
//    if (role == null) {
//        response.sendRedirect(request.getContextPath() + "/shared/Auth.jsp");
//        return;
//    }

%>
<main class="content-wrapper">
    <div id="container">
        <div id="d1">
            <h1><span style="color: brown;">Welcome</span> to our</h1>
            <h1>secure portal</h1>
            <h3>Log in to stay informed and engaged</h3>
            <button id="b0">Learn more</button>
        </div>
        <div id="d2">
            <!-- Form for login -->
            <form action="log" method="post">
                <p class="h3 mb-3 fw-normal"
                   style="color: brown; text-align: center; font-weight: bolder; font-size: 30px;">
                    Login to your account
                </p>
                <p>Enter your email address </p>
                <div class="form-floating">
                    <input name="login_email" id="emailinput" type="email" class="form-control" placeholder="name@example.com">
                    <label for="emailinput">Email address</label>
                </div>

                <p>Enter your password</p>
                <div class="form-floating">
                    <input name="password_login" id="passwordinput" type="password" class="form-control" placeholder="Password">
                    <label for="passwordinput">Password</label>
                </div>

                <div class="form-check text-start my-3">
                    <input class="form-check-input" type="checkbox" value="remember-me" id="flexCheckDefault">
                    <label class="form-check-label" for="flexCheckDefault">Remember me</label>
                </div>
                <br>
                <button name="btnlogin" id="but1" class="btn btn-primary w-100 py-2" type="submit">Sign in</button>
            </form>
        </div>
    </div>
</main>
</body>
</html>