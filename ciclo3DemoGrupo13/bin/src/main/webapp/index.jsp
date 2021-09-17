<%-- 
    Document   : inicio
    Created on : 6/09/2021, 10:18:41 a. m.
    Author     : harold
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
        <title>Ejemplo JSP</title>
    </head>
    <body>
        <h1 class="text-center">Bienvenidos a mi pagina web dinamica</h1>
        <div class="container mt-4 col-lg-4">
            <div class="card col-sm-10">
                <div class="card-body">
                    <form class="form-group" action="/ciclo3DemoGrupo13/DemoServlet" method="post">
                        <div class="form-group text-center">
                            <h3>Login</h3>
                            <img src="image/logo.png" width="40" height="40"/>
                            <label>Bienvenido al sistema</label>
                        </div>
                        <div class="form-group text-center">
                            <label>Usuario</label>
                            <input type="text" name="txtusuario" class="form-control">
                        </div>
                        <div class="form-group text-center">
                            <label>Contraseña</label>
                            <input type="password" name="txtpassword" class="form-control">
                        </div>
                        <div class="form-group text-center">
                            <div>
                                <br>
                                <input type="submit" value="Ingresar" class="btn btn-primary btn-b">
                            </div>                                
                        </div>
                    </form>
                </div>

