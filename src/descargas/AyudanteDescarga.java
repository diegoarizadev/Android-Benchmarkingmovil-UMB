package descargas;

import java.io.InputStream;
import java.net.Socket;

import utilidades.Log;
import utilidades.Util;

import n0rf3n.android.AnalisisMovil;
import n0rf3n.android.R;
import android.os.SystemClock;
import android.widget.ProgressBar;
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

public class AyudanteDescarga {
	public static boolean performTest(String name, int expsize,
			final AnalisisMovil mainAct, final ProgressBar pb,
			final TextView text) {
		final int expsize_ = expsize + 220 - 1024;
		mainAct.runOnUiThread(new Thread() {
			public void run() {
				pb.setMax(100);
				pb.setProgress(1);
			}
		});
		try {
			Socket sock = new Socket("benchmarkingmovil.googlecode.com", 80);
			if (mainAct.dejarEn()) {
				return false;
			}
			sock.getOutputStream()
					.write(("GET /files/" + name + " HTTP/1.0\r\n" + "Host: benchmarkingmovil.googlecode.com\r\n\r\n")
							.getBytes("US-ASCII"));
			InputStream is = sock.getInputStream();
			byte[] b = new byte[4096];
			int read_count, total_read = 0;
			is.read(b, 0, 1024);

			long time_begin = SystemClock.uptimeMillis();
			while ((read_count = is.read(b, 0, 4096)) != -1) {
				if (mainAct.dejarEn()) {
					return false;
				}
				total_read += read_count;
				final int total_read_ = total_read;
				final String str = mainAct
						.getString(
								R.string.velocidad_dl,
								total_read
										/ 1024.0
										/ ((SystemClock.uptimeMillis() - time_begin) / 1000.0));
				mainAct.runOnUiThread(new Thread() {
					public void run() {
						text.setText(str);
						pb.setProgress(100 * total_read_ / expsize_);
					}
				});
			}
			mainAct.runOnUiThread(new Thread() {
				public void run() {
					pb.setProgress(100);
				}
			});

			if (total_read < expsize / 2) {
				mainAct.runOnUiThread(new Thread() {
					public void run() {
						Toast.makeText(mainAct, R.string.archivo_pequeno,
								Toast.LENGTH_LONG).show();
					}
				});
			}

			sock.close();
			return true;

		} catch (Exception e) {
			Log.debug(Util.printException(e));
			final String str = Util.typicalHttpclientExceptionToString(mainAct,
					e);
			mainAct.runOnUiThread(new Thread() {
				public void run() {
					text.setText(str);
				}
			});
			return false;
		}
	}

}
