package n0rf3n.android;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import clases.Altitud;
import clases.DibujarMapa;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.text.Html;
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

public class BenchMarking_Movil_V_1Activity extends MapActivity {
	/** Called when the activity is first created. */

	private MapView mapa = null;
	private MapController controlMapa = null;
	private Button btnTipoSatelite = null;
	private Button btnTestConexion = null;
	private Button btnUbicarEnMapa = null;
	private Button btnCalcularAltura = null;
	private TextView latitud, longitud, altura;
	private double lat, lon;
	final Context context = this;
	// Variables para la actividad del GPS
	private LocationListener locationListener;
	private Altitud alturaWS;

	private MyLocationOverlay ubicacion = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		iniciar();

		locationListener = new MiLocationListener();
		LocationManager milocManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		milocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0,
				locationListener);

		mapa = (MapView) findViewById(R.id.mapa);

		List<Overlay> capas = mapa.getOverlays();
		DibujarMapa dm = new DibujarMapa();
		dm.setupViews(this);
		capas.add(dm);
		mapa.postInvalidate();

		controlMapa = mapa.getController();
		mapa.setBuiltInZoomControls(true);

		ubicacion = new MyLocationOverlay(this, mapa);
		mapa.getOverlays().add(ubicacion);

		ubicacion.enableCompass();
		ubicacion.enableMyLocation();

		btnTipoSatelite = (Button) findViewById(R.id.btnSatelite);
		btnTipoSatelite.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (mapa.isSatellite())
					mapa.setSatellite(false);
				else
					mapa.setSatellite(true);

			}
		});

		btnTestConexion = (Button) findViewById(R.id.btnCentrar);
		btnTestConexion.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View vista) {

				Dialog dialogo;

				if (latitud.getText() == "Esperando datos del GPS"
						&& longitud.getText() == "Esperando datos del GPS") {

					dialogo = crearDialogoAlerta("Alerta..!!",
							"No hay Latitud y Longitud registrada.", "Aceptar");
					dialogo.show();

				} else {

					if (altura.getText() == "Calcule la Altura") {

						dialogo = crearDialogoAlerta("Alerta..!!",
								"No hay Altura registrada", "Aceptar");
						dialogo.show();

					} else {

						Intent ventana = new Intent(
								"n0rf3n.android.AnalisisMovil");

						ventana.putExtra("laTitud", latitud.getText());
						ventana.putExtra("longiTud", longitud.getText());
						ventana.putExtra("alTura", altura.getText());

						startActivity(ventana);

					}
				}
			}

		});

		btnUbicarEnMapa = (Button) findViewById(R.id.btnUbicar);
		btnUbicarEnMapa.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				ubicacion.runOnFirstFix(new Runnable() {

					@Override
					public void run() {
						mapa.getController().animateTo(
								ubicacion.getMyLocation());

					}
				});

			}
		});

		btnCalcularAltura = (Button) findViewById(R.id.btnAltura);
		btnCalcularAltura.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Dialog dialogo;

				if (latitud.getText() == "Esperando datos del GPS"
						&& longitud.getText() == "Esperando datos del GPS") {
					dialogo = crearDialogoAlerta(
							"Información",
							"El GPS no a entregado su ubicación actual, por lo tanto NO se puede calcular la Altura.",
							"Aceptar");
					dialogo.show();

				} else {

					alturaWS = new Altitud(Double.parseDouble((String) latitud
							.getText()), Double.parseDouble((String) longitud
							.getText()));
					altura.setText(alturaWS.Altura() + " Mts");

					dialogo = crearDialogoAlerta(
							"Actualización",
							"Se ha calulado la ALTURA, Si esta en continuo movimiento, por favor actualice la altura antes de continuar con el test de conexión.",
							"Aceptar");
					dialogo.show();

				}

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
					"BenchMarking Móvil Versión 1 \n \n Esta aplicación es de uso académico..!! \n \n Desarrollada por: \n Ing. Diego Ariza \n diego.ariza@ieee.org \n @n0rf3n \n \n Docentes - CoAutores \n Ph.D. Juan D. López \n Ing. Frank Romero \n \n Colombia - Bogotá \n 2012 \n \n "
							+ Html.fromHtml("<a href=http://benchmarkingmovil.googlecode.com/>http://benchmarkingmovil.googlecode.com/</a>")
							+ " \n", "Aceptar");
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

	public void iniciar() {

		latitud = (TextView) findViewById(R.id.txtMainLatitud);
		latitud.setText("Esperando datos del GPS");
		longitud = (TextView) findViewById(R.id.txtMainLongitud);
		longitud.setText("Esperando datos del GPS");
		altura = (TextView) findViewById(R.id.txtMainAltura);
		altura.setText("Calcule la Altura");

	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false; // El valor de retorno debe ser true si se va a
						// representar algun tipo de informacion sobre la ruta
		// en este caso es no se mostrara informacion por lo tanto no se
		// devolvera nada "False"
	}

	@Override
	protected void onPause() {
		ubicacion.disableCompass();
		ubicacion.disableMyLocation();
		super.onPause();
	}

	@Override
	protected void onResume() {
		ubicacion.enableMyLocation();
		ubicacion.enableCompass();

		super.onResume();
	}

	public class MiLocationListener implements LocationListener {
		public void onLocationChanged(Location loc) {
			if (loc != null) {

				lat = loc.getLatitude();
				lon = loc.getLongitude();

				latitud.setText("" + lat);
				longitud.setText("" + lon);

			}
		}

		private void lanzarOpcionesGPS() {
			final ComponentName toLaunch = new ComponentName(
					"com.android.settings",
					"com.android.settings.SecuritySettings");
			final Intent intent = new Intent(
					Settings.ACTION_LOCATION_SOURCE_SETTINGS);
			intent.addCategory(Intent.CATEGORY_LAUNCHER);
			intent.setComponent(toLaunch);
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivityForResult(intent, 0);
		}

		private void construirAlertaGPS() {
			final AlertDialog.Builder builder = new AlertDialog.Builder(context);
			builder.setMessage(
					"Su GPS, parace estar deshabilitado, ¿Quiere activar el GPS?")
					.setCancelable(false)
					.setPositiveButton("Sí",
							new DialogInterface.OnClickListener() {
								public void onClick(
										@SuppressWarnings("unused") final DialogInterface dialog,
										@SuppressWarnings("unused") final int id) {
									lanzarOpcionesGPS();
								}
							})
					.setNegativeButton("No",
							new DialogInterface.OnClickListener() {
								public void onClick(
										final DialogInterface dialog,
										@SuppressWarnings("unused") final int id) {
									dialog.cancel();
								}
							});
			final AlertDialog alert = builder.create();
			alert.show();
		}

		@Override
		public void onProviderDisabled(String provider) {

			construirAlertaGPS();

		}

		@Override
		public void onProviderEnabled(String provider) {
			Toast.makeText(getApplicationContext(), "Gps Verificado...!!",
					Toast.LENGTH_SHORT).show();

		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			// TODO Auto-generated method stub

		}

	}

}
