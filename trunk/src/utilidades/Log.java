package utilidades;

/*
 * Aplicaci�n BenchMarking 
 * Desarrollada por Ing. Diego Ariza
 * 
 * La utilizaci�n de esta aplicaci�n es netamente Estudiantil y es desarrollada
 * en el marco de investigaci�n y teor�a de las siguientes clases 
 * 
 * Desarrollo de software para telecomunicaciones - Dictada por el Docente Ph.D. Juan Diego L�pez
 * Desarrollo de software para dispositivos m�viles - Dictada por el Docente Ing. Frank Romero
 *  * Universidad Manuela Beltr�n - Junio 05 del 2012
 * Jornada Nocturna
 * 
 * Cualquier cambio que se le realice a esta aplicaci�n debe ser notificado y enviar el proyecto 
 * completo con la modificaci�n o mejora, esto con el objetivo de mantener un repositorio 
 * actualizado para que nuevos estudiantes puedan utilizar esta herramienta y mejorarla.
 * 
 * Este aplicaci�n NO TIENE PERMISOS PARA SER UTILIZADA CON FINES COMERCIALES
 * O DE INVESTIGACI�N DE CUALQUIER EMPRESA, ENTIDAD O GRUPO AJENO. SU USO ES EXCLUSIVAMENTE
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
