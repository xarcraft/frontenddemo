<%@page import="co.edu.unbosqueCiclo3Demo.Proveedores"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
	crossorigin="anonymous">
</head>
<body>
<h1>hola</h1>
<div>
<a class="btn btn-primary" href="#" role="button">Usuarios</a>
<a class="btn btn-primary" href="#" role="button">Clientes</a>
<a class="btn btn-primary" href="controlador?menu=Reportes&accion=ListarProvee" role="button">Proveedores</a>
</div>

<table class="table">
						<thead class="thead-dark">
							<tr>
								<th>Nit</th>
								<th>Nombre</th>
								<th>Ciudad</th>
								<th>Direccion</th>
								<th>Telefono</th>
							</tr>
						</thead>
						<tbody>
						
						</tbody>
					</table>

</body>
</html>