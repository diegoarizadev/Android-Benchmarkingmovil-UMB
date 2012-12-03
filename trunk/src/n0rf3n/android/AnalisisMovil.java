package n0rf3n.android;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import descargas.Descarga1MB;
import descargas.Descargar100k;
import descargas.Descargar10k;

import utilidades.Log;
import utilidades.Tester;
import utilidades.Util;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
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

public class AnalisisMovil extends Activity {

	private TextView latitud, longitud, altura, txtTipoRed, direccionIp,
			roaming, estado, estadoDetallado, diezkb, cienkb, unmb;
	private Button operar, guardarInfo;
	private String informacionConectividad;
	private Integer tipoRed;
	private List<Tester> test;
	private volatile boolean wantStop = false;
	private boolean iniciar = false;
	final public static String MyKey = "mikey";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.benchmarking);

		inicializar();
		iniciarVistas();
		mostrarInformacionConexion();

		System.setProperty("networkaddress.cache.ttl", "0");
		System.setProperty("networkaddress.cache.negative.ttl", "0");

		// inicializar la persistencia

		guardarInfo = (Button) findViewById(R.id.btnGuardarInfo);
		guardarInfo.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {

				Intent i = new Intent("n0rf3n.android.SQLiteActivity");

				// parsar variables a la siguiente actividad

				i.putExtra("tipoRed", txtTipoRed.getText());
				i.putExtra("direccionIP", direccionIp.getText());
				i.putExtra("roaMing", roaming.getText());
				i.putExtra("estado", estado.getText());
				i.putExtra("estadoDetallado", estadoDetallado.getText());
				i.putExtra("diezkb", diezkb.getText());
				i.putExtra("cienkb", cienkb.getText());
				i.putExtra("unmb", unmb.getText());
				i.putExtra("LatiTud", latitud.getText());
				i.putExtra("LongiTud", longitud.getText());
				i.putExtra("AlTura", altura.getText());

				startActivity(i);

			}
		});

	}

	public void inicializar() {

		latitud = (TextView) findViewById(R.id.txtLatitud);
		latitud.setText(getIntent().getStringExtra("laTitud"));

		longitud = (TextView) findViewById(R.id.txtLongitud);
		longitud.setText(getIntent().getStringExtra("longiTud"));

		altura = (TextView) findViewById(R.id.txtAltura);
		altura.setText(getIntent().getStringExtra("alTura"));

		direccionIp = (TextView) findViewById(R.id.txtDireccionIp);
		roaming = (TextView) findViewById(R.id.txtRoaming);
		estado = (TextView) findViewById(R.id.txtEstado);
		estadoDetallado = (TextView) findViewById(R.id.txtEstadoDetallado);
		diezkb = (TextView) findViewById(R.id.txt10kb);
		cienkb = (TextView) findViewById(R.id.txt100kb);
		unmb = (TextView) findViewById(R.id.txt1mb);

		informacionConectividad = "";

		test = new ArrayList<Tester>();
		test.add(new Descargar10k());
		test.add(new Descargar100k());
		test.add(new Descarga1MB());

	}

	public void tipoRed() {

		NetworkInfo netinfo = ((ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE))
				.getActiveNetworkInfo();
		String tipo = netinfo.getSubtypeName().length() == 0 ? netinfo
				.getTypeName() : netinfo.getTypeName() + "/"
				+ netinfo.getSubtypeName();
		txtTipoRed.setText(tipo);

		// Documentación obtenida de
		// http://es.wikicode.org/index.php/Detectar_conexi%C3%B3n_de_datos_en_android

	}

	private void mostrarInformacionConexion() {

		String esTado = "";
		String direccionIP = "";
		String roaMing = "";
		String estadoFull = "";

		NetworkInfo netinfo = ((ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE))
				.getActiveNetworkInfo();
		if (netinfo != null) {

			esTado += netinfo.getState();
			estadoFull += netinfo.getDetailedState();
			roaMing += netinfo.isRoaming();

			informacionConectividad += "Estado: " + netinfo.getState() + "\n";
			informacionConectividad += "Estado detallado: "
					+ netinfo.getDetailedState() + "\n";
			informacionConectividad += "Roaming: " + netinfo.isRoaming() + "\n";
		}

		Enumeration<NetworkInterface> en = null;
		try {
			en = NetworkInterface.getNetworkInterfaces();
			if (en != null) {
				while (en.hasMoreElements()) {
					NetworkInterface intf = en.nextElement();
					List<String> ips = new ArrayList<String>();
					boolean loopback;
					try {
						loopback = intf.isLoopback();
					} catch (NoSuchMethodError nsme) {
						// for API level < 9
						loopback = intf.getName().startsWith("lo");
					}
					if (!loopback) {
						for (Enumeration<InetAddress> enumIpAddr = intf
								.getInetAddresses(); enumIpAddr
								.hasMoreElements();) {
							InetAddress inetAddress = enumIpAddr.nextElement();
							ips.add(inetAddress.getHostAddress());
						}
						if (ips.size() > 0) {

							direccionIP += Util.join(ips, ", ");

							informacionConectividad += "IP de "
									+ intf.getName() + ": "
									+ Util.join(ips, ", ") + "\n";
						}
					}
				}
			}
		} catch (SocketException se) {
			Log.error("No se pudo obtener las interfaces de red: " + se);
		}

		if (informacionConectividad.length() == 0) {
			informacionConectividad = getString(R.string.no_hay_InformacionRed);
		}

		estado.setText(esTado);
		direccionIp.setText(direccionIP);
		roaming.setText(roaMing);
		estadoDetallado.setText(estadoFull);

	}

	public Integer getNetworkType() {
		return tipoRed;
	}

	private void iniciar() {
		tipoRed(); // update for in case app was launched kinda long
					// ago and network has changed
		final Map<Tester, Boolean> areActive = new HashMap<Tester, Boolean>();
		for (Tester tester : test) {
			tester.prepareTest();
			areActive.put(tester, tester.isActive());
		}

		new Thread() {
			@Override
			public void run() {
				try {
					for (Tester tester : test) {
						if (areActive.get(tester)) {
							Log.debug("Launch test " + tester);
							if (!tester.performTest() || wantStop) {
								return;
							}
						}
					}
				} finally {
					runOnUiThread(new Thread() {
						public void run() {
							// iniciar = false;
							for (Tester tester : test) {
								tester.cleanupTests();
							}
						}
					});
				}
			}
		}.start();
	}

	public boolean dejarEn() {
		return wantStop;
	}

	private void iniciarVistas() {

		txtTipoRed = (TextView) findViewById(R.id.txTipoRed);
		tipoRed();

		operar = (Button) findViewById(R.id.btnOperar);

		operar.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (iniciar) {
					wantStop = true;
				} else {
					iniciar = true;
					wantStop = false;
					iniciar();
				}
			}
		});

		for (Tester tester : test) {
			tester.setupViews(this);
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
		builder.setPositiveButton(boton, new OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
			}
		});

		return builder.create();
	}

}
