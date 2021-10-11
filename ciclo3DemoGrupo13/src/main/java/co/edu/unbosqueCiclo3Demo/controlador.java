package co.edu.unbosqueCiclo3Demo;

import java.util.List;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.tagext.TryCatchFinally;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

@WebServlet("/controlador")
public class controlador extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static URL url;
	private static String sitio = "http://localhost:5000/";

	// variables generales
	double subtotal=0, totalapagar, subtotaliva, acusubtotal;
	long codigo_producto, codProducto, numfac = 0;
	double valor_iva, precio, iva = 0;
	int cantidad, item = 0;
	String descripcion, cedulaCliente;

	List<Detalle_ventas> listaVentas = new ArrayList<>();
	Usuarios usuarios = new Usuarios();
	Detalle_ventas detalle_venta = new Detalle_ventas();

	// metodos locales
	public void buscarCliente(String id, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			ArrayList<Clientes> listac = TestJSONClientes.getJSON();
			for (Clientes clientes : listac) {
				if (clientes.getCedula_cliente().equals(id)) {
					request.setAttribute("clienteSeleccionado", clientes);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void buscarProducto(String id, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			ArrayList<Productos> listap = TestJSONProductos.getJSON();
			for (Productos productos : listap) {
				if (productos.getCodigo_producto().equals(id)) {
					request.setAttribute("productoSeleccionado", productos);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void mostrarNumFactura(String numFact, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (numFact == null) {
			numFact = "1";
			numfac = Integer.parseInt(numFact);
		} else {
			numfac = Integer.parseInt(numFact) + 1;
		}
		request.setAttribute("numerofactura", numfac);
	}
	
	public void grabarDetalleVentas (Long numFact, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		for(int i=0; i<listaVentas.size();i++) {
			detalle_venta=new Detalle_ventas();
			detalle_venta.setCodigo_detalle_venta(i+1);
			detalle_venta.setCodigo_venta(numFact);
			detalle_venta.setCodigo_producto(listaVentas.get(i).getCodigo_producto());
			detalle_venta.setCantidad_producto(listaVentas.get(i).getCantidad_producto());
			detalle_venta.setValor_venta(listaVentas.get(i).getValor_venta());
			detalle_venta.setValor_total(listaVentas.get(i).getValor_total());
			detalle_venta.setValoriva(listaVentas.get(i).getValoriva());
			
			int respuesta=0;
			try {
				respuesta=TestJSONDetalleVenta.postJSON(detalle_venta);
				PrintWriter write= response.getWriter();
				if(respuesta==200) {
					System.out.println("Registro grabado en detalle ventas: "+i);
					request.getRequestDispatcher("controlador?menu=Ventas&accion=default").forward(request,
							response);
				}else {
					write.println("Error detalles ventas: "+ respuesta);
				}
				write.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public controlador() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String menu = request.getParameter("menu");
		String accion = request.getParameter("accion");

		// Cedula del usuario activo
		String cedula_usuario_activo = request.getParameter("UsuarioActivo");
		usuarios.setCedula_usuario(cedula_usuario_activo);
		request.setAttribute("usuarioSeleccionado", usuarios);

		switch (menu) {
		case "Principal":
			request.getRequestDispatcher("/Inicio.jsp").forward(request, response);
			break;

		case "Usuarios":
			if (accion.equals("Listar")) {
				try {
					ArrayList<Usuarios> lista = TestJSON.getJSON();
					request.setAttribute("lista", lista);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (accion.equals("Agregar")) {
				Usuarios usuario = new Usuarios();
				usuario.setCedula_usuario(request.getParameter("txtcedula"));
				usuario.setNombre_usuario(request.getParameter("txtnombre"));
				usuario.setEmail_usuario(request.getParameter("txtemail"));
				usuario.setUsuario(request.getParameter("txtusuario"));
				usuario.setPassword(request.getParameter("txtpassword"));

				int respuesta = 0;
				try {
					respuesta = TestJSON.postJSON(usuario);
					if (respuesta == 200) {
						request.getRequestDispatcher("controlador?menu=Usuarios&accion=Listar").forward(request,
								response);
					} else {
						System.out.println("Error: " + respuesta);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

			} else if (accion.equals("Actualizar")) {
				Usuarios usuario = new Usuarios();
				usuario.setCedula_usuario(request.getParameter("txtcedula"));
				usuario.setNombre_usuario(request.getParameter("txtnombre"));
				usuario.setEmail_usuario(request.getParameter("txtemail"));
				usuario.setUsuario(request.getParameter("txtusuario"));
				usuario.setPassword(request.getParameter("txtpassword"));

				int respuesta = 0;
				try {
					respuesta = TestJSON.putJSON(usuario, Long.parseLong(usuario.getCedula_usuario()));
					PrintWriter write = response.getWriter();

					if (respuesta == 200) {
						request.getRequestDispatcher("controlador?menu=Usuarios&accion=Listar").forward(request,
								response);
					} else {
						write.println("Error: " + respuesta);
					}
					write.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (accion.equals("Cargar")) {
				String id = request.getParameter("id");
				try {
					ArrayList<Usuarios> lista1 = TestJSON.getJSON();
					for (Usuarios usuarios : lista1) {
						if (usuarios.getCedula_usuario().equals(id)) {
							request.setAttribute("usuarioSeleccionado", usuarios);
							request.getRequestDispatcher("controlador?menu=Usuarios&accion=Listar").forward(request,
									response);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (accion.equals("Eliminar")) {
				Long id = Long.parseLong(request.getParameter("id"));
				int respuesta = 0;
				try {
					respuesta = TestJSON.deleteJSON(id);
					PrintWriter write = response.getWriter();
					if (respuesta == 200) {
						request.getRequestDispatcher("controlador?menu=Usuarios&accion=Listar").forward(request,
								response);
					} else {
						write.println("Error: " + respuesta);
					}
					write.close();
				} catch (Exception e) {
					e.printStackTrace();
				}			
			}
			request.getRequestDispatcher("/Usuarios.jsp").forward(request, response);
			break;

		case "Clientes":
			if (accion.equals("Listar")) {
				try {
					ArrayList<Clientes> lista = TestJSONClientes.getJSON();
					request.setAttribute("lista", lista);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (accion.equals("Agregar")) {
				Clientes cliente = new Clientes();
				cliente.setCedula_cliente(request.getParameter("txtcedula"));
				cliente.setNombre_cliente(request.getParameter("txtnombre"));
				cliente.setEmail_cliente(request.getParameter("txtemail"));
				cliente.setDireccion_cliente(request.getParameter("txtdireccion"));
				cliente.setTelefono_cliente(request.getParameter("txttelefono"));

				int respuesta = 0;
				try {
					respuesta = TestJSONClientes.postJSON(cliente);
					if (respuesta == 200) {
						request.getRequestDispatcher("controlador?menu=Clientes&accion=Listar").forward(request,
								response);
					} else {
						System.out.println("Error: " + respuesta);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

			} else if (accion.equals("Actualizar")) {
				Clientes cliente = new Clientes();
				cliente.setCedula_cliente(request.getParameter("txtcedula"));
				cliente.setNombre_cliente(request.getParameter("txtnombre"));
				cliente.setEmail_cliente(request.getParameter("txtemail"));
				cliente.setDireccion_cliente(request.getParameter("txtdireccion"));
				cliente.setTelefono_cliente(request.getParameter("txttelefono"));

				int respuesta = 0;
				try {
					respuesta = TestJSONClientes.putJSON(cliente, Long.parseLong(cliente.getCedula_cliente()));
					PrintWriter write = response.getWriter();

					if (respuesta == 200) {
						request.getRequestDispatcher("controlador?menu=Clientes&accion=Listar").forward(request,
								response);
					} else {
						write.println("Error: " + respuesta);
					}
					write.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (accion.equals("Cargar")) {
				String id = request.getParameter("id");
				try {
					ArrayList<Clientes> lista1 = TestJSONClientes.getJSON();
					for (Clientes clientes : lista1) {
						System.out.println(clientes);
						if (clientes.getCedula_cliente().equals(id)) {
							request.setAttribute("clienteSeleccionado", clientes);
							request.getRequestDispatcher("controlador?menu=Clientes&accion=Listar").forward(request,
									response);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (accion.equals("Eliminar")) {
				Long id = Long.parseLong(request.getParameter("id"));
				int respuesta = 0;
				try {
					respuesta = TestJSONClientes.deleteJSON(id);
					PrintWriter write = response.getWriter();
					if (respuesta == 200) {
						request.getRequestDispatcher("controlador?menu=Clientes&accion=Listar").forward(request,
								response);
					} else {
						write.println("Error: " + respuesta);
					}
					write.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			request.getRequestDispatcher("/Clientes.jsp").forward(request, response);
			break;

		case "Proveedores":
			if (accion.equals("Listar")) {
				try {
					ArrayList<Proveedores> lista = TestJSONProveedores.getJSON();
					request.setAttribute("lista", lista);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (accion.equals("Agregar")) {
				Proveedores proveedor = new Proveedores();
				proveedor.setNitproveedor(request.getParameter("txtnit"));
				proveedor.setNombre_proveedor(request.getParameter("txtnombre"));
				proveedor.setCiudad_proveedor(request.getParameter("txtciudad"));
				proveedor.setDireccion_proveedor(request.getParameter("txtdireccion"));
				proveedor.setTelefono_proveedor(request.getParameter("txttel"));

				int respuesta = 0;
				try {
					respuesta = TestJSONProveedores.postJSON(proveedor);
					if (respuesta == 200) {
						request.getRequestDispatcher("controlador?menu=Proveedores&accion=Listar").forward(request,
								response);
					} else {
						System.out.println("Error: " + respuesta);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

			} else if (accion.equals("Actualizar")) {
				Proveedores proveedor = new Proveedores();
				proveedor.setNitproveedor(request.getParameter("txtnit"));
				proveedor.setNombre_proveedor(request.getParameter("txtnombre"));
				proveedor.setCiudad_proveedor(request.getParameter("txtciudad"));
				proveedor.setDireccion_proveedor(request.getParameter("txtdireccion"));
				proveedor.setTelefono_proveedor(request.getParameter("txttel"));

				int respuesta = 0;
				try {
					respuesta = TestJSONProveedores.putJSON(proveedor, Long.parseLong(proveedor.getNitproveedor()));
					PrintWriter write = response.getWriter();

					if (respuesta == 200) {
						request.getRequestDispatcher("controlador?menu=Proveedores&accion=Listar").forward(request,
								response);
					} else {
						write.println("Error: " + respuesta);
					}
					write.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (accion.equals("Cargar")) {
				String id = request.getParameter("id");
				try {
					ArrayList<Proveedores> lista1 = TestJSONProveedores.getJSON();
					for (Proveedores proveedores : lista1) {
						System.out.println(proveedores);
						if (proveedores.getNitproveedor().equals(id)) {
							request.setAttribute("proveedorSeleccionado", proveedores);
							request.getRequestDispatcher("controlador?menu=Proveedores&accion=Listar").forward(request,
									response);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (accion.equals("Eliminar")) {
				Long id = Long.parseLong(request.getParameter("id"));
				int respuesta = 0;
				try {
					respuesta = TestJSONProveedores.deleteJSON(id);
					PrintWriter write = response.getWriter();
					if (respuesta == 200) {
						request.getRequestDispatcher("controlador?menu=Proveedores&accion=Listar").forward(request,
								response);
					} else {
						write.println("Error: " + respuesta);
					}
					write.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			request.getRequestDispatcher("/Proveedores.jsp").forward(request, response);
			break;

		case "Productos":
			if (accion.equals("Listar")) {
				try {
					ArrayList<Productos> lista = TestJSONProductos.getJSON();
					request.setAttribute("lista", lista);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (accion.equals("Agregar")) {
				Productos producto = new Productos();
				producto.setCodigo_producto(request.getParameter("txtcodigo"));
				producto.setIvacompra(request.getParameter("txtiva"));
				producto.setNitproveedor(request.getParameter("txtnit"));
				producto.setNombre_producto(request.getParameter("txtnombre"));
				producto.setPrecio_compra(request.getParameter("txtcompra"));
				producto.setPrecio_venta(request.getParameter("txtventa"));

				int respuesta = 0;
				try {
					respuesta = TestJSONProductos.postJSON(producto);
					if (respuesta == 200) {
						request.getRequestDispatcher("controlador?menu=Productos&accion=Listar").forward(request,
								response);
					} else {
						System.out.println("Error: " + respuesta);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

			} else if (accion.equals("Actualizar")) {
				Productos producto = new Productos();
				producto.setCodigo_producto(request.getParameter("txtcodigo"));
				producto.setIvacompra(request.getParameter("txtiva"));
				producto.setNitproveedor(request.getParameter("txtnit"));
				producto.setNombre_producto(request.getParameter("txtnombre"));
				producto.setPrecio_compra(request.getParameter("txtcompra"));
				producto.setPrecio_venta(request.getParameter("txtventa"));

				int respuesta = 0;
				try {
					respuesta = TestJSONProductos.putJSON(producto, Long.parseLong(producto.getCodigo_producto()));
					PrintWriter write = response.getWriter();

					if (respuesta == 200) {
						request.getRequestDispatcher("controlador?menu=Productos&accion=Listar").forward(request,
								response);
					} else {
						write.println("Error: " + respuesta);
					}
					write.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (accion.equals("Cargar")) {
				String id = request.getParameter("id");
				try {
					ArrayList<Productos> lista1 = TestJSONProductos.getJSON();
					for (Productos productos : lista1) {
						System.out.println(productos);
						if (productos.getCodigo_producto().equals(id)) {
							request.setAttribute("productoSeleccionado", productos);
							request.getRequestDispatcher("controlador?menu=Productos&accion=Listar").forward(request,
									response);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (accion.equals("Eliminar")) {
				Long id = Long.parseLong(request.getParameter("id"));
				int respuesta = 0;
				try {
					respuesta = TestJSONProductos.deleteJSON(id);
					PrintWriter write = response.getWriter();
					if (respuesta == 200) {
						request.getRequestDispatcher("controlador?menu=Productos&accion=Listar").forward(request,
								response);
					} else {
						write.println("Error: " + respuesta);
					}
					write.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			request.getRequestDispatcher("/Productos.jsp").forward(request, response);
			break;
			
		case "Ventas":
			request.setAttribute("usuarioSeleccionado", usuarios);
			request.setAttribute("numerofactura", numfac);
			
			if (accion.equals("BuscarCliente")) {
				String id = request.getParameter("cedulacliente");
				this.buscarCliente(id, request, response);
			} else if (accion.equals("BuscarProducto")) {
				String id = request.getParameter("codigoproducto");
				this.buscarProducto(id, request, response);
				String idc = request.getParameter("cedulacliente");
				this.buscarCliente(idc, request, response);
			} else if (accion.equals("AgregarProducto")) {
				String id = request.getParameter("cedulacliente");
				this.buscarCliente(id, request, response);

				detalle_venta = new Detalle_ventas();
				item++;
				totalapagar = 0;
				acusubtotal=0;
				subtotaliva=0;
				
				codProducto = Integer.parseInt(request.getParameter("codigoproducto"));
				descripcion = request.getParameter("producto");
				cantidad = Integer.parseInt(request.getParameter("cantidad"));
				precio = Double.parseDouble(request.getParameter("precio"));
				iva = Double.parseDouble(request.getParameter("ivaproducto"));

				subtotal = (precio * cantidad);
				valor_iva = (subtotal * iva / 100);

				detalle_venta.setCodigo_detalle_venta(item);
				detalle_venta.setCodigo_producto(codProducto);
				detalle_venta.setDescripcion_producto(descripcion);
				detalle_venta.setCantidad_producto(cantidad);
				detalle_venta.setPrecio_producto(precio);
				detalle_venta.setCodigo_venta(numfac);
				detalle_venta.setValoriva(valor_iva);
				detalle_venta.setValor_venta(subtotal);
				listaVentas.add(detalle_venta);

				for (int i = 0; i < listaVentas.size(); i++) {
					acusubtotal += listaVentas.get(i).getValor_venta();
					subtotaliva += listaVentas.get(i).getValoriva();
				}
				totalapagar = acusubtotal + subtotaliva;
				detalle_venta.setValor_total(totalapagar);

				request.setAttribute("listaventas", listaVentas);
				request.setAttribute("totalsubtotal", acusubtotal);
				request.setAttribute("totaliva", subtotaliva);
				request.setAttribute("totalapagar", totalapagar);
			} else if (accion.equals("GenerarVenta")) {
				String numFact = request.getParameter("numerofactura");
				cedulaCliente = request.getParameter("cedulacliente");

				Ventas ventas = new Ventas();
				ventas.setCodigo_venta(Long.parseLong(numFact));
				ventas.setCedula_cliente(Long.parseLong(cedulaCliente));
				ventas.setCedula_usuario(Long.parseLong(usuarios.getCedula_usuario()));
				ventas.setIvaventa(subtotaliva);
				ventas.setValor_venta(acusubtotal);
				ventas.setTotal_venta(totalapagar);

				int respuesta = 0;
				try {
					respuesta = TestJSONVentas.postJSON(ventas);
					PrintWriter write = response.getWriter();

					if (respuesta == 200) {
						System.out.println("Grabacion exitosa " + respuesta);						
						this.grabarDetalleVentas(ventas.getCodigo_venta(), request, response);
					} else {
						write.println("error ventas: " + respuesta);
					}
					write.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				listaVentas.clear();
				item=0;
				totalapagar=0;
				acusubtotal=0;
				subtotaliva=0;
			} else {
				String factura = request.getParameter("numerofactura");
				this.mostrarNumFactura(factura, request, response);
			}
			
			request.getRequestDispatcher("/Ventas.jsp").forward(request, response);
			break;
		case "Reportes":			
			int opcion=0;
			if(accion.equals("ReporteUsuarios")) {
				try {
					ArrayList<Usuarios> lista = TestJSON.getJSON();
					opcion=1;
					request.setAttribute("listaUsuarios", lista);
					request.setAttribute("opcion", opcion);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else if(accion.equals("ReporteClientes")) {
				try {
					ArrayList<Clientes> lista = TestJSONClientes.getJSON();
					opcion=2;
					request.setAttribute("listaClientes", lista);
					request.setAttribute("opcion", opcion);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else if(accion.equals("ReporteVentas")) {
				try {
					ArrayList<Ventas> lista = TestJSONVentas.getJSON();
					opcion=3;
					request.setAttribute("listaVentas1", lista);
					request.setAttribute("opcion", opcion);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			request.getRequestDispatcher("/Reportes.jsp").forward(request, response);
			break;
		}
	}
}
