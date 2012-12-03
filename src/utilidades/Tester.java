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

import n0rf3n.android.AnalisisMovil;

;

public interface Tester {

	public void setupViews(AnalisisMovil mainActivity);

	public void prepareTest();

	public boolean isActive();

	public void setActive(boolean value);

	public boolean performTest();

	public void cleanupTests();

	public void onPause();

}
