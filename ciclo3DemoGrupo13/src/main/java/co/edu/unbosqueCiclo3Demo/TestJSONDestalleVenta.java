package co.edu.unbosqueCiclo3Demo;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class TestJSONDestalleVenta {

	private static URL url;
	private static String sitio = "http://localhost:5000/";
	
	public static int postJSON(Detalle_ventas detalle_venta) throws IOException {

		url = new URL(sitio + "detalle_venta/guardar");
		HttpURLConnection http;
		http = (HttpURLConnection) url.openConnection();

		try {
			http.setRequestMethod("POST");
		} catch (ProtocolException e) {
			e.printStackTrace();
		}

		http.setDoOutput(true);
		http.setRequestProperty("Accept", "application/json");
		http.setRequestProperty("Content-Type", "application/json");

		String data = "{" 
				+ "\"codigo_detalle_venta\":\"" + detalle_venta.getCodigo_detalle_venta()
				+ "\",\"cantidad_producto\": \"" + detalle_venta.getCantidad_producto() 
				+ "\",\"codigo_producto\": \"" + detalle_venta.getCodigo_producto() 
				+ "\",\"codigo_venta\":\"" + detalle_venta.getCodigo_venta() 
				+ "\",\"valor_total\":\"" + detalle_venta.getValor_total()
				+ "\",\"valor_venta\":\"" + detalle_venta.getValor_venta()
				+ "\",\"valoriva\":\"" + detalle_venta.getValoriva()
				+ "\"}";
		byte[] out = data.getBytes(StandardCharsets.UTF_8);
		OutputStream stream = http.getOutputStream();
		stream.write(out);

		int respuesta = http.getResponseCode();
		http.disconnect();
		return respuesta;
	}
}
