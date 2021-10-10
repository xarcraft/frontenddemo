package co.edu.unbosqueCiclo3Demo;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class TestJSONVentas {	
	
	public TestJSONVentas() {
		super();
		// TODO Auto-generated constructor stub
	}

	private static URL url;
	private static String sitio = "http://localhost:5000/";
	
	public static int postJSON(Ventas ventas) throws IOException {

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
				+ "\"codigo_venta\":\""+ String.valueOf(ventas.getCodigo_venta())
				+"\",\"cedula_cliente\": \""+String.valueOf(ventas.getCedula_cliente())
				+"\",\"cedula_usuario\": \""+String.valueOf(ventas.getCedula_usuario())
				+"\",\"ivaventa\":\""+String.valueOf(ventas.getIvaventa())
				+"\",\"total_venta\":\""+String.valueOf(ventas.getTotal_venta())
				+"\",\"valor_venta\":\""+String.valueOf(ventas.getValor_venta())
				+ "\"}";
		
		byte[] out = data.getBytes(StandardCharsets.UTF_8);
		OutputStream stream = http.getOutputStream();
		stream.write(out);

		int respuesta = http.getResponseCode();
		http.disconnect();
		return respuesta;
	}
	
	private String obtenerJson(InputStream respuesta) throws Exception {
		byte[] inp = respuesta.readAllBytes();
		String json = "";
		for (int i = 0; i < inp.length; i++) {
			json += (char) inp[i];
		}
		System.out.println("json::>" + json);
		return json;
	}
	
	public Long parsingConsecutivo(String json) throws ParseException {
		Long cod = null;
		JSONParser jsonParser = new JSONParser();
		JSONObject innerObj = (JSONObject) jsonParser.parse(json);
		
		if(innerObj != null && !innerObj.isEmpty()) {					
			cod = Long.parseLong(innerObj.get("id").toString());
		}
		return cod;
	}
	
	public Long obtenerConsecutivo() throws Exception {
		Long cod = null;
		try {
			url = new URL(sitio + "ventas/buscarSigCod");
			HttpURLConnection http;
			http = (HttpURLConnection) url.openConnection();
			http.setRequestMethod("GET");
			http.setRequestProperty("Accept", "application/json");
			http.setRequestProperty("Content-Type", "application/json");
			
			String json =obtenerJson(http.getInputStream());
			if(json!=null && !json.isEmpty()) {
				cod = parsingConsecutivo(json);
			}		

		} catch (Exception e) {
			System.err.println("No hay resultados para esa consulta::>" + e.getMessage());
			throw new Exception("No hay resultados para esa consulta");
		}
		return cod;
	}
	
}
