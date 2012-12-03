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

public class Log {

	public enum Type {
		DEBUG {
			public int value() {
				return 20;
			}
		},
		INFO {
			public int value() {
				return 30;
			}
		},
		WARN {
			public int value() {
				return 40;
			}
		},
		ERROR {
			public int value() {
				return 50;
			}
		};
		public abstract int value();
	};

	private static Type minLevel = Type.DEBUG;

	public static void setMinLevel(Type type) {
		minLevel = type;
	}

	private static void log(Type type, Object what) {
		if (type.value() < minLevel.value()) {
			return;
		}
		StackTraceElement trace = Thread.currentThread().getStackTrace()[4];
		String me = "/" + trace.getFileName().replace(".java", "") + ":"
				+ trace.getLineNumber() + "(" + trace.getMethodName() + ")";
		String msg = what.toString();
		if (type == Type.DEBUG) {
			android.util.Log.d(me, msg);
		} else if (type == Type.INFO) {
			android.util.Log.i(me, msg);
		} else if (type == Type.WARN) {
			android.util.Log.w(me, msg);
		} else if (type == Type.ERROR) {
			android.util.Log.e(me, msg);
		} else {
			android.util.Log.e(me, msg);
		}
	}

	public static void debug(Object what) {
		log(Type.DEBUG, what);
	}

	public static void info(Object what) {
		log(Type.INFO, what);
	}

	public static void warn(Object what) {
		log(Type.WARN, what);
	}

	public static void error(Object what) {
		log(Type.ERROR, what);
	}

}
