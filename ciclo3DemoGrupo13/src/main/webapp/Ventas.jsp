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

						<input type="hidden" name="menu" value="Ventas"> <input
							type="hidden" name="UsuarioActivo"
							value="${usuarioSeleccionado.getCedula_usuario() }">
						<div class="form-group d-flex">
							<div class="col-sm-6 d-flex">
								<input type="number" name="cedulacliente" class="form-control"
									placeholder="Cédula Cliente"
									value="${clienteSeleccionado.getCedula_cliente() }"> <input
									type="submit" name="accion" value="BuscarCliente"
									class="btn btn-outline-info">
							</div>
							<div class="col-sm-6">
								<input type="text" name="nombrecliente" class="form-control"
									placeholder="Nombre Cliente"
									value="${clienteSeleccionado.getNombre_cliente() }" readonly="readonly">
							</div>
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
									placeholder="Código Producto"
									value="${productoSeleccionado.getCodigo_producto() }">
								<input type="submit" name="accion" value="BuscarProducto"
									class="btn btn-outline-info">
							</div>
							<div class="col-sm-6 d-flex">
								<input type="text" name="producto" class="form-control"
									placeholder="Nombre Producto"
									value="${productoSeleccionado.getNombre_producto() }" readonly="readonly">
							</div>
						</div>
						<div class="form-group d-flex">
							<div class="col-sm-6 d-flex">
								<input type="text" name="precio" class="form-control"
									placeholder="$ 0000.00"
									value="${productoSeleccionado.getPrecio_venta() }" readonly="readonly">
							</div>
							<div class="col-sm-3 d-flex">
								<input type="number" name="cantidad"
									class="form-control" placeholder="cantidad" value="1">
							</div>
							<div class="col-sm-3 d-flex">
								<input type="text" name="ivaproducto" class="form-control"
									placeholder="Valor IVA"
									value="${productoSeleccionado.getIvacompra() }" readonly="readonly">
							</div>
						</div>
						<div class="form-group d-flex">
							<input type="submit" name="accion" value="AgregarProducto"
								class="btn btn-outline-primary">
						</div>
					</div>
				</div>
			</form>
		</div>
		<div class="col-md-5 seccion2">
			<div class="card">
				<div class="card-header">
					<div class="form-grow row">
						<label class="col-sm-3 col-form-label">Número de Factura</label> <input
							class="form-control col-md-4" type="text" name="numerofactura"
							value="${numerofactura}" readonly="readonly">
					</div>
				</div>
				<div class="card-body">
					<table class="table">
						<thead class="thead-dark">
							<tr>
								<th>#</th>
								<th>Codigo</th>
								<th>Producto</th>
								<th>Precio</th>
								<th>Cantidad</th>
								<th>Iva</th>
								<th>Total</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="lista" items="${listaventas }">
								<tr>
									<td>${lista.getCodigo_detalle_venta()}</td>
									<td>${lista.getCodigo_producto()}</td>
									<td>${lista.getDescripcion_producto()}</td>
									<td>${lista.getPrecio_producto()}</td>
									<td>${lista.getCantidad_producto()}</td>
									<td>${lista.getValoriva()}</td>
									<td>${lista.getValor_venta()}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<div class="card-footer d-flex">
					<div class="col-md-4">
						<label>subtotal</label><br> <label>Iva</label><br> <label>Total
							a pagar</label>
					</div>
					<div class="col-md-4">
						<input type="text" name="txtsubtotal" class="form-control"
							value="${totalsubtotal }" placeholder="$ 00.000.00" disabled>
						<input type="text" name="txttotaliva" class="form-control"
							value="${totaliva }" placeholder="$ 00.000.00" disabled>
						<input type="text" name="txttotalapagar" class="form-control"
							value="${totalapagar }" placeholder="$ 00.000.00" disabled>
					</div>
				</div>
			</div>
			<div class="card-footer d-flex">
				<div class="col-md-8">
					<a class="btn btn-success" onclick="print()"
						href="controlador?menu=Ventas&accion=GenerarVenta&cedulacliente=${clienteSeleccionado.getCedula_cliente()}&UsuarioActivo=${usuarioSeleccionado.getCedula_usuario()}&numerofactura=${numerofactura}">Generar
						Venta</a> <a class="btn btn-danger"
						href="controlador?menu=Ventas&accion=NuevaVenta">Nueva Venta</a>
				</div>
			</div>
		</div>
	</div>
</body>
</html>