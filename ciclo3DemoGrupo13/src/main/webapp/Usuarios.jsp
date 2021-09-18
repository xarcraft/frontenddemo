<%@page import="co.edu.unbosqueCiclo3Demo.Usuarios"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
	crossorigin="anonymous">
<title>Creacion de Usuario</title>
</head>
<body>
	<div class="row">
		<div class="card col-md-4">
			<div class="card-body">
				<h5 class="card-title">Usuarios</h5>
				<h6 class="card-subtitle mb-2 text-muted">En este panel podras
					gestionar los datos de los usuarios del sistema</h6>
				<div>
					<form class="form-sign" method="get" action="controlador">

						<div class="form-group">
							<input type="hidden" name="menu" value="Usuarios"> <label>Cedula:</label>
							<input type="text" name="txtcedula" class="form-control">
						</div>
						<div class="form-group">
							<label>Nombre:</label> <input type="text" name="txtnombre"
								class="form-control">
						</div>
						<div class="form-group">
							<label>Email:</label> <input type="text" name="txtemail"
								class="form-control">
						</div>
						<div class="form-group">
							<label>Usuario:</label> <input type="text" name="txtusuario"
								class="form-control">
						</div>
						<div class="form-group">
							<label>Password:</label> <input type="password"
								name="txtpassword" class="form-control">
						</div>
						<br>
						<input type="submit" class="btn btn-primary" name="accion"
							value="Agregar"> <input type="submit"
							class="btn btn-success" name="accion" value="Actualizar">
					</form>
				</div>
			</div>
		</div>
		<div class="col-md-8">
			<table class="table">
				<thead class="thead-dark">
					<tr>
						<th scope="col">Cedula</th>
						<th scope="col">Nombre</th>
						<th scope="col">Email</th>
						<th scope="col">Usuario</th>
						<th scope="col">Password</th>
					</tr>
				</thead>
				<tbody>
					<%
					ArrayList<Usuarios> lista = (ArrayList<Usuarios>) request.getAttribute("lista");
					for (Usuarios usuario : lista) {
					%>
					<tr>
						<td><%=usuario.getCedula_usuario()%></td>
						<td><%=usuario.getNombre_usuario()%></td>
						<td><%=usuario.getEmail_usuario()%></td>
						<td><%=usuario.getUsuario()%></td>
						<td><%=usuario.getPassword()%></td>
						<td><a class="btn btn-warning"
							href="controlador?menu=Usuarios&accion=Cargar&id= <%=usuario.getCedula_usuario()%>">Editar</a>
							<a class="btn btn-danger"
							href="controlador?menu=Usuarios&accion=Eliminar&id=<%=usuario.getCedula_usuario()%>">Eliminar</a>
						</td>
					</tr>
					<%}%>
				</tbody>
			</table>
		</div>
</body>
</html>
