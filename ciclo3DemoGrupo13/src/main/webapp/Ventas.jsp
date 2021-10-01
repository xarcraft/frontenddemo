<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
	crossorigin="anonymous">
<title>Ventas</title>
</head>
<body>
	<div class="row">
		<div class="col-md-5 seccion1">
			<form method="get" action="controlador">
				<div class="card">
					<div class="card-body">
						<div class="form-group">
							<label>Datos Cliente</label>
						</div>

						<input type="hidden" name="menu" value="Ventas">
						<div class="form-group d-flex">
							<div class="col-sm-6 d-flex">
								<input type="number" name="cedulacliente" class="form-control"
									placeholder="Cédula Cliente" value=""> <input
									type="submit" name="accion" value="BuscarCliente"
									class="btn btn-outline-info">
							</div>
						</div>
						<div class="col-sm-6">
							<input type="text" name="nombrecliente" class="form-control"
								placeholder="Nombre Cliente" value="">
						</div>
					</div>
				</div>
				<div class="card">
					<div class="card-body">
						<div class="form-group">
							<label>Datos del producto</label>
						</div>
						<div class="form-group d-flex">
							<div class="col-sm-6 d-flex">
								<input type="number" name="codigoproducto" class="form-control"
									placeholder="Código Producto" value=""> <input
									type="submit" name="accion" value="BuscarProducto"
									class="btn btn-outline-info">
							</div>
							<div class="col-sm-6 d-flex">
								<input type="text" name="producto" class="form-control"
									placeholder="Nombre Producto" value="">
							</div>
						</div>
						<div class="form-group d-flex">
							<div class="col-sm-6 d-flex">
								<input type="text" name="precioproducto" class="form-control" placeholder="$ 0000.00" value="">
							</div>
							<div class="col-sm-3 d-flex">
								<input type="number" name="cantidadproducto" class="form-control" placeholder="cantidad" value="">
							</div>
							<div class="col-sm-3 d-flex">
								<input type="text" name="ivaproducto" class="form-control" placeholder="Valor IVA" value="">
							</div>
						</div>						
							<div class="form-group d-flex">
								<input type="submit" name="accion" value="AgregarProducto" class="btn btn-outline-primary">
							</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>