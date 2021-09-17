<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
	crossorigin="anonymous">
</head>
<body>
	<h1 class="text-center">Bienvenidos a mi pagina web dinamica</h1>
	<div class="container mt-4 col-lg-4">
		<div class="card col-sm-10">
			<div class="card-body">
				<form class="form-group" action="./DemoServlet" method="get">
					<div class="form-group text-center">
						<h3>Login</h3>
						<img src="image/logo.png" width="40" height="40" /> <label>Bienvenido
							al sistema</label>
					</div>
					<div class="form-group text-center">
						<label>Usuario</label> <input type="text" name="txtusuario"
							class="form-control">
					</div>
					<div class="form-group text-center">
						<label>Contraseña</label> <input type="password"
							name="txtpassword" class="form-control">
					</div>
					<div class="form-group text-center">
						<div>
							<br> <input type="submit" value="Ingresar"
								class="btn btn-primary btn-b" name="accion">
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>