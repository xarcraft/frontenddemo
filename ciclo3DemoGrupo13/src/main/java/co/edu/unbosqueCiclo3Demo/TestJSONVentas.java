package co.edu.unbosqueCiclo3Demo;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class TestJSONVentas {

	private static URL url;
	private static String sitio = "http://localhost:5000/";
	
	public static int postJSON(Ventas venta) throws IOException {

		url = new URL(sitio + "ventas/guardar");
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
				+ "\"codigo_venta\":\"" + venta.getCodigo_venta()
				+ "\",\"cedula_cliente\": \"" + venta.getCedula_cliente() 
				+ "\",\"cedula_usuario\": \"" + venta.getCedula_usuario() 
				+ "\",\"ivaventa\":\"" + venta.getIvaventa() 
				+ "\",\"total_venta\":\"" + venta.getTotal_venta()
				+ "\",\"valor_venta\":\"" + venta.getValor_venta()
				+ "\"}";
		byte[] out = data.getBytes(StandardCharsets.UTF_8);
		OutputStream stream = http.getOutputStream();
		stream.write(out);

		int respuesta = http.getResponseCode();
		http.disconnect();
		return respuesta;
	}
}
