package descargas;

import utilidades.Tester;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import n0rf3n.android.AnalisisMovil;
import n0rf3n.android.R;

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

public class Descarga1MB implements Tester {

	private AnalisisMovil actividad;
	private CheckBox seleccion;
	private ProgressBar barraProgreso;
	private TextView texto;
	private boolean erroInformacion = false;

	@Override
	public void setupViews(AnalisisMovil mainActivity) {
		// TODO Auto-generated method stub
		this.actividad = mainActivity;
		seleccion = (CheckBox) mainActivity.findViewById(R.id.checkbox1mb);
		texto = (TextView) mainActivity.findViewById(R.id.txt1mb);
		barraProgreso = (ProgressBar) mainActivity
				.findViewById(R.id.main__progressbar_1mb_download);
		barraProgreso.setProgress(0);
		seleccion.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				NetworkInfo netinfo = ((ConnectivityManager) actividad
						.getSystemService(Context.CONNECTIVITY_SERVICE))
						.getActiveNetworkInfo();
				if (!erroInformacion) {
					erroInformacion = true;
					if (netinfo != null
							&& netinfo.getType() == ConnectivityManager.TYPE_MOBILE) {
						Toast.makeText(actividad, R.string.peligro_datos_movil,
								Toast.LENGTH_LONG).show();
					} else {
						Toast.makeText(actividad,
								R.string.peligro_datos_genericos,
								Toast.LENGTH_LONG).show();
					}
				}
			}
		});

	}

	@Override
	public void prepareTest() {
		// TODO Auto-generated method stub
		seleccion.setEnabled(false);
		texto.setText(R.string.valor_vacio);
		barraProgreso.setProgress(0);

	}

	@Override
	public boolean isActive() {
		// TODO Auto-generated method stub
		return seleccion.isChecked();
	}

	@Override
	public void setActive(boolean value) {
		// TODO Auto-generated method stub
		seleccion.setChecked(value);

	}

	@Override
	public boolean performTest() {
		// TODO Auto-generated method stub
		return AyudanteDescarga.performTest("1mb.txt", 1048576, actividad,
				barraProgreso, texto);
	}

	@Override
	public void cleanupTests() {
		// TODO Auto-generated method stub
		seleccion.setEnabled(true);
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub

	}

}
