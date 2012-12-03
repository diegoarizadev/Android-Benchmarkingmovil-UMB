package persistencia;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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

public class Registro {

	private static final String DATABASE_NAME = "bechmarking.db";
	private static final String TABLA = "registros";
	private static final int VERSION_BD = 1;

	public static final String ID_FILA = "idregistro";
	public static final String ID_NOMBRE_REGISTRO = "nombreregistro";
	public static final String ID_LONGITUD = "longitud";
	public static final String ID_LATITUD = "latitud";
	public static final String ID_ALTURA = "altura";
	public static final String ID_TIPO_RED = "tipo_red";
	public static final String ID_IP = "ip";
	public static final String ID_ROAMING = "roaming";
	public static final String ID_ESTADO = "estado";
	public static final String ID_ESTADO_DETALLADO = "estado_detallado";
	public static final String ID_10K = "diezk";
	public static final String ID_100K = "cienk";
	public static final String ID_1MB = "unmb";

	// instancia para la base de datos
	private BDHelper nHelper;
	private final Context nContexto;
	private SQLiteDatabase nBD;

	public Registro(Context c) {
		nContexto = c;

	}

	private static class BDHelper extends SQLiteOpenHelper {

		public BDHelper(Context context) {
			super(context, DATABASE_NAME, null, VERSION_BD);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL("CREATE TABLE " + TABLA + " (" + ID_FILA
					+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ ID_NOMBRE_REGISTRO + " TEXT NOT NULL, " + ID_LONGITUD
					+ " TEXT NOT NULL, " + ID_LATITUD + " TEXT NOT NULL, "
					+ ID_ALTURA + " TEXT NOT NULL, " + ID_TIPO_RED
					+ " TEXT NOT NULL, " + ID_IP + " TEXT NOT NULL, "
					+ ID_ROAMING + " TEXT NOT NULL, " + ID_ESTADO
					+ " TEXT NOT NULL, " + ID_ESTADO_DETALLADO
					+ " TEXT NOT NULL, " + ID_10K + " TEXT NOT NULL, "
					+ ID_100K + " TEXT NOT NULL, " + ID_1MB + " TEXT); ");

		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

			db.execSQL(" DROP TABLE IF EXISTS " + TABLA);
			onCreate(db);

		}

	}

	// Metodo para abrir la base de datos que pasa por el contexto
	public Registro abrir() throws Exception {
		nHelper = new BDHelper(nContexto);
		nBD = nHelper.getWritableDatabase();
		return this;

	}

	public void cerrar() {

		nHelper.close();

	}

	public void borrarTabla() {
		nBD.execSQL(" DROP TABLE " + TABLA + ";");

	}

	public void crearEntrada(String nombreRegistro, String longitud,
			String latitud, String altura, String tipo_red, String ip,
			String roaming, String estado, String estado_detallado,
			String diezk, String cienk, String unmb) {

		nBD.execSQL("INSERT INTO " + TABLA + " VALUES ( null, '"
				+ nombreRegistro + "', '" + longitud + "', '" + latitud
				+ "', '" + altura + "', '" + tipo_red + "', '" + ip + "', '"
				+ roaming + "', '" + estado + "', '" + estado_detallado
				+ "', '" + diezk + "', '" + cienk + "', '" + unmb + "')");

	}

	public String recibir() {

		String resultado = " ";

		String slq = "SELECT * FROM " + TABLA;

		Cursor c = nBD.rawQuery(slq, null);

		c.getCount();

		while (c.moveToNext()) {
			resultado = resultado + " " + c.getString(0) + " - "
					+ c.getString(1) + " | " + c.getString(2) + " | "
					+ c.getString(3) + " | " + c.getString(4) + " | "
					+ c.getString(5) + " | " + c.getString(6) + " | "
					+ c.getString(7) + " | " + c.getString(8) + " | "
					+ c.getString(9) + " | " + c.getString(10) + " | "
					+ c.getString(11) + " | " + c.getString(12) + "\n";
		}

		if (resultado.equals(" ")) {

			return "No hay datos registrados";

		} else {

			return resultado;
		}

	}

	public String recibirCSV() {

		String resultado = "TestNumero;NombreRegistro;Latitud;Longitud;Altura;TipoRed;DireccionIP;Roaming;Estado;EstadoDetallado;Informacion10K;Informacion100k;Informacion1MB;"
				+ "\n";

		String slq = "SELECT * FROM " + TABLA;

		Cursor c = nBD.rawQuery(slq, null);

		c.getCount();

		while (c.moveToNext()) {
			resultado = resultado + " " + c.getString(0) + " ; "
					+ c.getString(1) + " ; " + c.getString(2) + " ; "
					+ c.getString(3) + " ; " + c.getString(4) + " ; "
					+ c.getString(5) + " ; " + c.getString(6) + " ; "
					+ c.getString(7) + " ; " + c.getString(8) + " ; "
					+ c.getString(9) + " ; " + c.getString(10) + " ; "
					+ c.getString(11) + " ; " + c.getString(12) + "\n";
		}

		resultado = resultado
				+ "Aplicacion creada por Ing. Diego Ariza - @n0rf3n";

		if (resultado.equals(" ")) {

			return "No hay datos registrados";

		} else {

			return resultado;
		}

	}

}
