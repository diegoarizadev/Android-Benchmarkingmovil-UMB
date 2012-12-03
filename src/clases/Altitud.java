package clases;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;

/*
 * Aplicación BenchMarking 
 * Desarrollada por Ing. Diego Ariza
 * 
 * La utilización de esta aplicación es netamente Estudiantil y es desarrollada
 * en el marco de investigación y teoría de las siguientes clases 
 * 
 * Desarrollo de software para telecomunicaciones - Dictada por el Docente Ph.D. Juan Diego López
 * Desarrollo de software para dispositivos móviles - Dictada por el Docente Ing. Frank Romero
 *  * Universidad Manuela Beltrán - Junio 05 del 2012
 * Jornada Nocturna
 * 
 * Cualquier cambio que se le realice a esta aplicación debe ser notificado y enviar el proyecto 
 * completo con la modificación o mejora, esto con el objetivo de mantener un repositorio 
 * actualizado para que nuevos estudiantes puedan utilizar esta herramienta y mejorarla.
 * 
 * Este aplicación NO TIENE PERMISOS PARA SER UTILIZADA CON FINES COMERCIALES
 * O DE INVESTIGACIÓN DE CUALQUIER EMPRESA, ENTIDAD O GRUPO AJENO. SU USO ES EXCLUSIVAMENTE
 * ACADEMICO
 * 
 * Atentamente ..
 * 
 * 
 * Diego Ariza
 * Email : diego.ariza@ieee.com
 * Email : diego.ariza2@gmail.com
 * Twitter : @n0rf3n
 * Celular : (057)(1) 312-37632-65
 * Skype : Diego_ariza
 * Linux User # 483023
 * Blog : http://hack--store.blogspot.com/
 * 
 */

public class Altitud extends Activity {

	public Double latitud;
	public Double longitud;
	public InputStream contenido;

	public Altitud(Double latitud, Double longitud) {
		super();
		this.latitud = latitud;
		this.longitud = longitud;
	}

	public String Altura() {

		String altura = "";
		// Ejemplo http://ws.geonames.org/srtm3?lat=4.67&lng=-74.15
		String url = "http://ws.geonames.org/srtm3?lat=" + latitud + "&lng="
				+ longitud;

		HttpClient httpcliente = new DefaultHttpClient();
		HttpGet request = new HttpGet(url);

		try {

			HttpResponse response = httpcliente.execute(request);
			HttpEntity entity = response.getEntity();
			InputStream webs = entity.getContent();

			BufferedReader b = new BufferedReader(new InputStreamReader(webs,
					"iso-8859-1"), 8);

			altura = b.readLine();
			webs.close();

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return altura;

	}

	public Double getLatitud() {
		return latitud;
	}

	public void setLatitud(Double latitud) {
		this.latitud = latitud;
	}

	public InputStream getContenido() {
		return contenido;
	}

	public void setContenido(InputStream contenido) {
		this.contenido = contenido;
	}

}
