package n0rf3n.android;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import persistencia.Registro;
import n0rf3n.android.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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

public class SQLVista extends Activity {

	private TextView texto;
	private String textoCSV;
	private String nombreArchivo = "benchmarking.csv";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.vista_registros);

		iniciar();

		Registro info = new Registro(this);

		try {
			info.abrir();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String datos = info.recibir();
		textoCSV = info.recibirCSV();
		info.cerrar();
		texto.setText(datos);

		Button btnCrearArchivoMail = (Button) findViewById(R.id.btnConstuirArchivo);
		btnCrearArchivoMail.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				boolean sdDisponible = false;
				boolean sdAccesoEscritura = false;

				// Comprobamos el estado de la memoria externa (tarjeta SD)
				String estado = Environment.getExternalStorageState();

				if (estado.equals(Environment.MEDIA_MOUNTED)) {
					sdDisponible = true;
					sdAccesoEscritura = true;
				} else if (estado.equals(Environment.MEDIA_MOUNTED_READ_ONLY)) {
					sdDisponible = true;
					sdAccesoEscritura = false;
				} else {
					sdDisponible = false;
					sdAccesoEscritura = false;
				}

				// Si la memoria externa est· disponible y se puede escribir
				if (sdDisponible && sdAccesoEscritura) {
					try {
						File ruta_sd = Environment
								.getExternalStorageDirectory();

						File f = new File(ruta_sd.getAbsolutePath(),
								nombreArchivo);

						OutputStreamWriter fout = new OutputStreamWriter(
								new FileOutputStream(f));

						fout.write(textoCSV);
						fout.close();

						Toast toast1 = Toast.makeText(getApplicationContext(),
								"Se ha creado el Archivo [" + nombreArchivo
										+ "] en la SD!", Toast.LENGTH_SHORT);

						toast1.show();

					} catch (Exception ex) {
						Log.e("Ficheros",
								"Error al escribir fichero a tarjeta SD");

						Toast toast2 = Toast.makeText(getApplicationContext(),
								"Error al escribir fichero a tarjeta SD!",
								Toast.LENGTH_SHORT);

						toast2.show();

					}
				}

			}
		});

		Button btnEnviarMail = (Button) findViewById(R.id.btnEnviarMailInfoAlmacenada);
		btnEnviarMail.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Intent i = new Intent("n0rf3n.android.EnvioMail");
				startActivity(i);

			}
		});

	}

	public void iniciar() {
		texto = (TextView) findViewById(R.id.textVw);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub

		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_principal, menu);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.MnuSalir:
			finish();
			System.exit(0);
			return true;
		case R.id.MnuSobre:
			Dialog dialogo;

			dialogo = crearDialogoAlerta(
					"Acerca de..",
					"BenchMarking Móvil Versión 1 \n \n Esta aplicación es de uso académico..!! \n \n Desarrollada por: \n Ing. Diego Ariza \n diego.ariza@ieee.org \n @n0rf3n \n \n Docentes - CoAutores \n Ph.D. Juan D. López \n Ing. Frank Romero \n \n Colombia - Bogotá \n 2012 \n \n http://benchmarkingmovil.googlecode.com/  \n",
					"Aceptar");
			dialogo.show();

			return true;
		case R.id.MnuSitioweb:

			Intent ventana = new Intent("n0rf3n.android.SitioWeb");
			startActivity(ventana);

			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private Dialog crearDialogoAlerta(String titulo, String mensaje,
			String boton) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);

		builder.setTitle(titulo);
		builder.setMessage(mensaje);

		builder.setPositiveButton(boton, new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.cancel();
			}

		});
		return builder.create();
	}

}
