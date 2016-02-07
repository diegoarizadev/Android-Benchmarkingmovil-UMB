# Benchmarking Móvil V.1
# Aplicación para el análisis de la calidad de servicio (Internet Móvil - WiFI)

El benchmarking Móvil, en la administración de empresas, puede definirse como un proceso sistemático y continuo para evaluar comparativamente los productos, en este caso evaluaremos los servicios de conectividad móvil (Internet) que ofrecen los diferentes proveedores de telecomunicaciones móviles en nuestro país (Colombia), así como los proveedores de servicios de internet residencial o empresarial (Internet Wifi). Con el objetivo de poder almacenar en una base de datos (sqlite) del dispositivo y enviar esta información vía mail o descargarla de la memoria SD o microSD del equipo para ser analizada en áreas o cursos de Desarrollo de software para telecomunicaciones o Nuevas tecnologías.

# Nota:
Esta aplicación es de uso Educativo, por tal motivo el Código Fuente no estará disponible para evitar copias, Pero si encontraran el instalador (.apk) en la sección de DOWNLOADS de este repositorio para poder ser instalado en su equipo, Sí requiere el proyecto para hacerle alguna modificación debe solicitarla a los Coautores Ph.D. Juan Diego López y el Ing. Frank Romero. Una vez realizadas las modificaciones deben enviarnos una copia del proyecto con las actualizaciones o mejoras, para que mas estudiantes puedan usar esta herramienta y enriquecerla en funcionabilidades.

# Galeria de imagenes

* [Galeria](https://picasaweb.google.com/102172818830568538607/BenchMarking_Movil)

![Alt text](https://lh3.googleusercontent.com/-sG2vcPlV7VY/T9S7NxPe-nI/AAAAAAAAAgw/VXwee6Vb-2E/s512-Ic42/Captura%252520de%252520pantalla%2525202012-06-10%252520a%252520la%252528s%252529%25252010.19.03.png "1")

![Alt text](https://lh3.googleusercontent.com/-noCEsWnpdPU/T9aBRrtvoBI/AAAAAAAAAio/miL7NX2DOcE/s512-Ic42/Captura%252520de%252520pantalla%2525202012-06-11%252520a%252520la%252528s%252529%25252015.20.28.png "2")

![Alt text](https://lh3.googleusercontent.com/-40MXUo3Thbk/T9S7NE0KIVI/AAAAAAAAAgo/cKjx1ebhwkE/s512-Ic42/Captura%252520de%252520pantalla%2525202012-06-10%252520a%252520la%252528s%252529%25252010.19.11.png "3")

![Alt text](https://lh3.googleusercontent.com/-lLzAQj0L6oE/T9TN3GhPyCI/AAAAAAAAAhU/lZE6GItlOEw/s512-Ic42/Captura%252520de%252520pantalla%2525202012-06-10%252520a%252520la%252528s%252529%25252011.39.16.png "4")

![Alt text](https://lh3.googleusercontent.com/-upMi0kY_XW4/T9aBNLm4YtI/AAAAAAAAAiM/jmNdm4Keawc/s512-Ic42/Captura%252520de%252520pantalla%2525202012-06-11%252520a%252520la%252528s%252529%25252018.32.13.png "5")

![Alt text](https://lh3.googleusercontent.com/-UUIpHB2LvKs/T9aBSze0lRI/AAAAAAAAAi8/aqw4LxTpgvo/s512-Ic42/Captura%252520de%252520pantalla%2525202012-06-11%252520a%252520la%252528s%252529%25252015.29.16.png "6")

![Alt text](https://lh3.googleusercontent.com/-ziQxrHW88vI/T9aBUy4JjoI/AAAAAAAAAjM/PSJzJJxygTg/s512-Ic42/Captura%252520de%252520pantalla%2525202012-06-11%252520a%252520la%252528s%252529%25252015.29.29.png "7")

![Alt text](https://lh3.googleusercontent.com/-Y5X3MJczh0A/T9aBVm_2hkI/AAAAAAAAAjc/SvRBaxiz17E/s512-Ic42/Captura%252520de%252520pantalla%2525202012-06-10%252520a%252520la%252528s%252529%25252011.39.16.png "8")

![Alt text](https://lh3.googleusercontent.com/-FXlHX_hP85c/T9aBZx5v2XI/AAAAAAAAAkc/dQkh-xzNiy8/s512-Ic42/Captura%252520de%252520pantalla%2525202012-06-11%252520a%252520la%252528s%252529%25252015.32.42.png "9")

![Alt text](https://lh3.googleusercontent.com/-090a4MsQkIM/T9aBaT45Y5I/AAAAAAAAAkk/HGBfAKF38nE/s512-Ic42/Captura%252520de%252520pantalla%2525202012-06-11%252520a%252520la%252528s%252529%25252015.33.10.png "10")

![Alt text](https://lh3.googleusercontent.com/-njG5kdrmV2g/T9aBabJ5I2I/AAAAAAAAAko/WCkmJPX3NXY/s512-Ic42/Captura%252520de%252520pantalla%2525202012-06-11%252520a%252520la%252528s%252529%25252015.33.18.png "11")

![Alt text](https://lh3.googleusercontent.com/-5ZFTUb6B7ds/T9aBar_xwuI/AAAAAAAAAk0/ZwBNg2AA7OY/s512-Ic42/Captura%252520de%252520pantalla%2525202012-06-11%252520a%252520la%252528s%252529%25252015.33.29.png "12")

![Alt text](https://lh3.googleusercontent.com/-89ClDVzEQGk/T9aBdmhgCzI/AAAAAAAAAmM/8y3LArb9ugE/s512-Ic42/Captura%252520de%252520pantalla%2525202012-06-11%252520a%252520la%252528s%252529%25252017.16.10.png "13")

![Alt text](https://lh3.googleusercontent.com/-b3K68AzpsiI/T9aBem7hkaI/AAAAAAAAAmU/V0npUFFeb9A/s512-Ic42/Captura%252520de%252520pantalla%2525202012-06-11%252520a%252520la%252528s%252529%25252017.41.20.png "14")



# Requerimientos

A continuación se hace referencia a los requerimientos mínimos que debe contar el dispositivo móvil, actualmente se han realizado pruebas con Samsung, HTC y LG, las cuales han sido exitosas.

  - Android Google 2.3.3 o superior.
  - Conexión de datos ilimitada (Wifi o Móvil).
  - El dispositivo móvil debe contar con GPS.
  - Api Google Versión 2.3.3 o superior (Google Maps y Google Gmail), debe estar configurado con una cuenta de Gmail.
  - Adobe Flash Player, se descargar de Google Play o el antiguo Market.
  - Espacio de 10MB en la tarjeta de almacenamiento externa del dispositivo MicroSD o SD (En la raíz de la memoria se creara un archivo .csv, el cual almacenara los registros o información de una consulta a la base de datos).

#Nota:
Se recomienda tener la Batería del móvil 100% cargada, puesto que al momento de realizar los test se consumirán recursos de GPS, Datos (EDGE, HSDPA, 3G, 4G y demás..) y estos tienden a descargar la carga con facilidad. A nivel de datos se aconseja que el plan sea abierto sin ningún tipo de restricción puesto que se descargar por medio del protocolo HTTP archivo de 10k, 100k y 1 MB, los cuales encontraran en la sección de DOWNLOADS de este repositorio.

Aplicación Desarrollada por.
Ing. Diego Ariza
diego.ariza2@gmail.com
Twitter : @n0rf3n
 
