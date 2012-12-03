package clases;

import n0rf3n.android.AnalisisMovil;
import n0rf3n.android.BenchMarking_Movil_V_1Activity;
import n0rf3n.android.R;
import android.R.string;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.location.Geocoder;
import android.location.LocationManager;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.Projection;

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

public class DibujarMapa extends Overlay {

	private TextView mainLatitud, mainLongitud, mainAltura;

	private Double latitud = 4.66 * 1E6;
	private Double longitud = -74.15 * 1E6;
	private Altitud altitud;

	private BenchMarking_Movil_V_1Activity actividad;

	public void setupViews(BenchMarking_Movil_V_1Activity mainActivity) {
		this.actividad = mainActivity;

		mainLatitud = (TextView) actividad.findViewById(R.id.txtMainLatitud);
		mainLongitud = (TextView) actividad.findViewById(R.id.txtMainLongitud);
		mainAltura = (TextView) actividad.findViewById(R.id.txtMainAltura);

	}

	@Override
	public void draw(Canvas canvas, MapView mapView, boolean shadow) {

		Projection projection = mapView.getProjection();
		GeoPoint geoPoint = new GeoPoint(latitud.intValue(),
				longitud.intValue());

		if (shadow == false) {
			Point centro = new Point();
			projection.toPixels(geoPoint, centro);

			// Definimos el pincel de dibujo
			Paint p = new Paint();
			p.setColor(Color.BLUE);

			// Marca Ejemplo
			// canvas.drawCircle(centro.x, centro.y, 5, p);
			// canvas.drawText("N0rf3n", centro.x + 10, centro.y + 5, p);

			// Bitmap bm = BitmapFactory.decodeResource(mapView.getResources(),
			// R.drawable.marcador);

			// canvas.drawBitmap(bm, centro.x - bm.getWidth(),
			// centro.y - bm.getHeight(), p);

		}

	}

	// @Override
	// public boolean onTap(GeoPoint point, MapView mapView) {

	// Double latitud2 = point.getLatitudeE6() / 1E6;
	// Double longitud2 = point.getLongitudeE6() / 1E6;

	// altitud = new Altitud(latitud2, longitud2);
	// String alt = altitud.Altura();

	// mainLatitud.setText(String.valueOf(latitud2));
	// mainLongitud.setText(String.valueOf(longitud2));
	// mainAltura.setText(alt + " Mts");

	// return true;
	// }

}
