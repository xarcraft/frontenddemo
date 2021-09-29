<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FiveWare Inicio</title>
<link href="css/estilos.css" rel='stylesheet' />
<link rel="shortcut icon" type="image/x-icon" href="img/favicon.ico">
<link rel="stylesheet" href="css/flexslider.css" type="text/css">
<script src="https://kit.fontawesome.com/037355adb6.js" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js"></script>
<script src="js/jquery.flexslider.js"></script>
<script type="text/javascript" charset="utf-8">
  $(window).load(function() {
    $('.flexslider').flexslider({
    	touch: true,
    	pauseOnAction: false,
    	pauseOnHover: false,
    });
  });
</script>
</head>
<body>
	<div class="flexslider">	
		<ul class="slides">		
			<li>
				<img src="img/1.jpg" alt="">
				<section class="flex-caption">
				<p>DISEÑO DE SOFTWARE A LA MEDIDA </p>
				</section>
			</li>
			<li>
				<img src="img/2.jpg" alt="">
				<section class="flex-caption">
				<p class="texto2">PROFESIONALES EN LA GESTIÓN Y DESARROLLO DE SISTEMAS DE INFORMACIÓN</p>
				</section>
			</li>
			<li>
				<img src="img/3.jpg" alt="">
				<section class="flex-caption">
				<p>DESARROLLO DE APLICACIONES MOVILES</p>
				</section>
			</li>
		
		</ul>
	
	</div>
	
	<footer class="footerGral">
	
	<div class="footerInicio">
	
		<div class="equipo">
		<h3>COLABORADORES</h3>
			<ul>
				<li>Catherine Escobar</li>
				<li>Harold Gomez</li>
				<li>David Botia</li>
				<li>Jose Javier Daza</li>
				<li>Carlos Garcia</li>
			
			</ul>
		</div>
	
		<div class="queHacemos">
		<h3>QUE HACEMOS?</h3>
			<ul>
				<li>Webs Corporativas</li>
				<li>Desarrollo web a medida</li>
				<li>Tiendas Online</li>
				<li>Marketing Online</li>
				<li>Consultoria de desarrollo web</li>
			
			</ul>
		</div>
		
		<div class="redes">
			<h3>SÍGUENOS EN REDES</h3>
			<i class="fab fa-facebook-square"></i>
			<i class="fab fa-instagram"></i>
			<i class="fab fa-linkedin"></i>
		</div>
		
		<div class="contacto">
		<h3>CONTÁCTANOS</h3>
			<ul>
			<li>FIVEWARE</li>
			<li>Calle 63 # 25-98 Bogotá Colombia</li>
			<li>(57)+ 321 4253625</li>
			<li>contacto@fiveware.com</li>
			</ul>
			
		</div>
		
	</div>
	
	</footer>
</body>

</html>