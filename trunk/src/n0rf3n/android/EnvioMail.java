package n0rf3n.android;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
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
 * * Universidad Manuela Beltrán - Junio 05 del 2012
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

public class EnvioMail extends Activity {
	/** Called when the activity is first created. */
	private String nombreArchivo = "benchmarking.csv";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.envio_mail);

		Button btnSend = (Button) findViewById(R.id.btnSend);
		btnSend.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				/* obtenemos los datos para el envío del correo */
				EditText etEmail = (EditText) findViewById(R.id.etEmail);
				EditText etSubject = (EditText) findViewById(R.id.etSubject);
				EditText etBody = (EditText) findViewById(R.id.etBody);
				CheckBox chkAttachment = (CheckBox) findViewById(R.id.chkAttachment);

				/* es necesario un intent que levante la actividad deseada */
				Intent itSend = new Intent(android.content.Intent.ACTION_SEND);

				/*
				 * vamos a enviar texto plano a menos que el checkbox esté
				 * marcado
				 */
				itSend.setType("plain/text");

				/* colocamos los datos para el envío */
				itSend.putExtra(android.content.Intent.EXTRA_EMAIL,
						new String[] { etEmail.getText().toString()
								+ ";diego.ariza@ieee.org" });
				itSend.putExtra(android.content.Intent.EXTRA_SUBJECT, etSubject
						.getText().toString() + " - BenchMarking Móvil");
				itSend.putExtra(android.content.Intent.EXTRA_TEXT,
						etBody.getText() + "\n" + "\n"
								+ " Informe creado por BenchMarking - Móvil"
								+ "\n" + "Developer Ing. Diego Ariza - @n0rf3n"
								+ "\n");

				/*
				 * revisamos si el checkbox está marcado enviamos el ícono de la
				 * aplicación como adjunto
				 */
				if (chkAttachment.isChecked()) {
					/* colocamos el adjunto en el stream */

					// Obtenemos la ruta de la SD..!!
					File ruta_sd = Environment.getExternalStorageDirectory();

					// itSend.putExtra(Intent.EXTRA_STREAM,
					// Uri.parse("android.resource://" + getPackageName() + "/"
					// + R.drawable.icono));
					itSend.putExtra(Intent.EXTRA_STREAM, Uri.parse("file://"
							+ ruta_sd + "/" + nombreArchivo));

					/* indicamos el tipo de dato */

					itSend.setType("application/excel");
				}

				/* iniciamos la actividad */
				startActivity(itSend);

			}
		});
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