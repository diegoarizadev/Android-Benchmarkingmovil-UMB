package n0rf3n.android;

import persistencia.Registro;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

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

public class SQLiteActivity extends Activity implements OnClickListener {
	/** Called when the activity is first created. */

	private TextView nombreRegistro, latitud, longitud, altura, tipoRed, ip,
			roaming, estado, estadoDetallado, diez_k, cien_k, un_mb;
	private Button insertar, ver, cargar;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.informacion_recopilada);

		// recuperamos informacion de la actividad anterior

		Bundle extra = this.getIntent().getExtras();

		iniciar();

		insertar.setOnClickListener(this);
		ver.setOnClickListener(this);

	}

	public void iniciar() {

		nombreRegistro = (TextView) findViewById(R.id.txtNombre);

		insertar = (Button) findViewById(R.id.btnInfoInsertar);
		ver = (Button) findViewById(R.id.btnVerInfo);

		latitud = (TextView) findViewById(R.id.txtInfoLatitud);
		latitud.setText(getIntent().getStringExtra("LatiTud"));

		longitud = (TextView) findViewById(R.id.txtInfoLongitud);
		longitud.setText(getIntent().getStringExtra("LongiTud"));

		altura = (TextView) findViewById(R.id.txtInfoAltura);
		altura.setText(getIntent().getStringExtra("AlTura"));

		tipoRed = (TextView) findViewById(R.id.txtInfoTipoRed);
		tipoRed.setText(getIntent().getStringExtra("tipoRed"));

		ip = (TextView) findViewById(R.id.txtInfoDireccionIp);
		ip.setText(getIntent().getStringExtra("direccionIP"));

		roaming = (TextView) findViewById(R.id.txtInfoRoaming);
		roaming.setText(getIntent().getStringExtra("roaMing"));

		estado = (TextView) findViewById(R.id.txtInfoEstado);
		estado.setText(getIntent().getStringExtra("estado"));

		estadoDetallado = (TextView) findViewById(R.id.txtInfoEstadoDetallado);
		estadoDetallado.setText(getIntent().getStringExtra("estadoDetallado"));

		diez_k = (TextView) findViewById(R.id.txtInfo10k);
		diez_k.setText(getIntent().getStringExtra("diezkb"));

		cien_k = (TextView) findViewById(R.id.txtInfo100k);
		cien_k.setText(getIntent().getStringExtra("cienkb"));

		un_mb = (TextView) findViewById(R.id.txtInfo1mb);
		un_mb.setText(getIntent().getStringExtra("unmb"));
	}

	public void cargarDatosPrueba() {

		// metodo para cargar datos de prueba
		// String.valueOf((int) (Math.random()*10+1))

		latitud.setText(String.valueOf((int) (Math.random() * 10)));
		longitud.setText(String.valueOf((int) (Math.random() * 10)));
		altura.setText(String.valueOf((int) (Math.random() * 10)));
		tipoRed.setText(String.valueOf((int) (Math.random() * 10)) + "G");
		ip.setText(String.valueOf((int) (Math.random() * 10)) + "."
				+ String.valueOf((int) (Math.random() * 10)) + "."
				+ String.valueOf((int) (Math.random() * 10)) + "."
				+ String.valueOf((int) (Math.random() * 10)));
		roaming.setText("No");
		estado.setText("Conectado");
		estadoDetallado.setText("Conectado");
		diez_k.setText(String.valueOf((int) (Math.random() * 10)));
		cien_k.setText(String.valueOf((int) (Math.random() * 10)));
		un_mb.setText(String.valueOf((int) (Math.random() * 10)));

	}

	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.btnInfoInsertar:

			boolean funciona = true;

			try {

				Registro entrada = new Registro(SQLiteActivity.this);
				entrada.abrir();

				entrada.crearEntrada(nombreRegistro.getText().toString(),
						longitud.getText().toString(), latitud.getText()
								.toString(), altura.getText().toString(),
						tipoRed.getText().toString(), ip.getText().toString(),
						roaming.getText().toString(), estado.getText()
								.toString(), estadoDetallado.getText()
								.toString(), diez_k.getText().toString(),
						cien_k.getText().toString(), un_mb.getText().toString());
				entrada.cerrar();

				nombreRegistro.setText(" ");

				latitud.setText("");
				longitud.setText("");
				altura.setText("");
				tipoRed.setText("");
				ip.setText("");
				roaming.setText("");
				estado.setText("");
				estadoDetallado.setText("");
				diez_k.setText("");
				cien_k.setText("");
				un_mb.setText("");

			} catch (Exception e) {

				funciona = false;
				String error = e.toString();
				Dialog d = new Dialog(this);
				d.setTitle("Se a presentado un error..!!");
				TextView tv = new TextView(this);
				tv.setText(error);
				d.setContentView(tv);
				d.show();

			} finally {

				if (funciona) {
					Dialog d = new Dialog(this);
					d.setTitle("Mensaje ");
					TextView tv = new TextView(this);
					tv.setText("Se han agredado los datos correctamente");
					d.setContentView(tv);
					d.show();
				}
			}

			break;
		case R.id.btnVerInfo:

			Intent i = new Intent("n0rf3n.android.SQLVista");
			startActivity(i);

			break;

		}

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