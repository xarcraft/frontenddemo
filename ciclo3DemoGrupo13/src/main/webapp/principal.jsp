<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pagina Principal</title>
<link href="css/estilos.css" rel='stylesheet' />
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
	crossorigin="anonymous">
</head>
<body>
	<header>
	<div class="contGeneral">
		<img class="logoGeneral" src="img/logo.png" alt="FiveWare" />
		<div class="usuarioActivo" ><p class="nombreUsuario">${usuario.getNombre_usuario()}</p></div>
		<div class="cerrarSesion"><a style="color: #fff;" href="index.jsp">Cerrar Sesión</a></div>
		<nav class="nav">	
			<ul>
				<li class="boton_nav" ><a id="texto_nav" href="controlador?menu=Principal" target="myFrame">Inicio</a></li>
				<li class="boton_nav" ><a id="texto_nav" href="controlador?menu=Usuarios&accion=Listar" target="myFrame">Usuarios</a></li>
				<li class="boton_nav" ><a id="texto_nav" href="controlador?menu=Clientes&accion=Listar" target="myFrame">Clientes</a></li>
				<li class="boton_nav" ><a id="texto_nav" href="controlador?menu=Proveedores&accion=Listar" target="myFrame">Proveedores</a></li>
				<li class="boton_nav" ><a id="texto_nav" href="controlador?menu=Productos&accion=Listar" target="myFrame">Productos</a></li>
				<li class="boton_nav" ><a id="texto_nav" href="controlador?menu=Ventas&accion=default&UsuarioActivo=${usuario.getCedula_usuario()}" target="myFrame">Ventas</a></li>
				<li class="boton_nav" ><a id="texto_nav" href="#" target="myFrame">Reportes</a></li>
			</ul>
					
		</nav>
	</div>
	</header>
	<div class="m-4" style="height: 700px;">
		<iframe style="height: 100%; width: 100%; border: none" name="myFrame"></iframe>
	</div>

	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
		integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
		integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
		crossorigin="anonymous"></script>
</body>
</html>