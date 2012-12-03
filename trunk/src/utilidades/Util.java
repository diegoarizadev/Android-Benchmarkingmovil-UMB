package utilidades;

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

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Collection;
import java.util.Iterator;

import org.apache.http.conn.ConnectTimeoutException;

import n0rf3n.android.R;

import android.content.Context;

public class Util {

	public static int unsignedByteToInt(byte input) {
		return input < 0 ? input + 256 : input;
	}

	public static <T> String join(Collection<T> col, String sep, int max,
			String ellipsizeText, String type) {
		StringBuilder sb = new StringBuilder();
		Iterator<T> li = col.iterator();
		if (li.hasNext()) {
			if (type.equals("toString")) {
				sb.append(li.next());
			} else if (type.equals("getClass")) {
				sb.append(li.next().getClass().getName());
			} else {
				sb.append("???");
			}
			while (li.hasNext()) {
				if (max == 1) {
					sb.append(ellipsizeText);
					return sb.toString();
				}
				sb.append(sep);
				if (type.equals("toString")) {
					sb.append(li.next());
				} else if (type.equals("getClass")) {
					sb.append(li.next().getClass().getName());
				} else {
					sb.append("???");
				}
				max--;
			}
		}
		return sb.toString();
	}

	public static <T> String join(Collection<T> col, String sep) {
		return join(col, sep, -1, null, "toString");
	}

	public static StackTraceElement[] getStackTrace() {
		return new Exception().fillInStackTrace().getStackTrace();
	}

	public static String backtraceFull(StackTraceElement[] trace, int from) {
		StringBuilder sb = new StringBuilder();
		for (int i = from; i < trace.length; i++) {
			sb.append("\t").append(trace[i].toString()).append("\n");
		}
		return sb.toString();
	}

	public static String backtraceFull() {
		return backtraceFull(getStackTrace(), 2);
	}

	public static String printException(Exception e) {
		StringBuilder out = new StringBuilder();
		String causePrefix = "";
		Throwable t = e;
		while (t != null) {
			out.append(causePrefix).append("exception: ").append(t)
					.append(" at: ");
			if (t.getCause() == null) {

				out.append("\n").append(backtraceFull(t.getStackTrace(), 0))
						.append("\n");
			} else {

				out.append(t.getStackTrace()[0]).append("\n");
			}
			t = t.getCause();

			causePrefix += "...cause: ";
		}
		return out.toString();
	}

	public static String exceptionMessageOrClass(Exception e) {
		return e.getMessage() != null && e.getMessage().length() > 0 ? e
				.getMessage() : e.getClass().getSimpleName();
	}

	public static String typicalHttpclientExceptionToString(Context ctx,
			Exception e) {
		return e instanceof UnknownHostException ? ctx
				.getString(R.string.tipico_error_HostDesconocido)
				: e instanceof ConnectTimeoutException ? ctx
						.getString(R.string.tipico_error_ConexionRechazada)
						: e instanceof ConnectException ? ctx
								.getString(R.string.tipico_error_TiempoConexion)
								: e instanceof SocketTimeoutException ? ctx
										.getString(R.string.tipico_error_SocketNoResponde)
										: exceptionMessageOrClass(e);
	}

	private static long[] wifiThresholds = new long[] { 30, 60 };
	private static long[] mobileThresholds = new long[] { 150, 400 };

}
