# VideoGames
Proyecto de biblioteca de videojuegos, con conexión a la API Documentación: https://www.freetogame.com/api-doc. 

# Descripción 
El propósito de esta prueba técnica consiste en desarrollar una aplicación que consuma
una API pública de videojuegos previamente construida, en la cual su tarea será
únicamente realizar su consumo.
La aplicación ofrecerá a los usuarios la posibilidad de visualizar un listado de videojuegos,
filtrarlos por género y editor. Guardar/eliminar un videojuego como favorito.

Este proyecto debe cumplir los siguientes requisitos:
1. Cuando se inicie la aplicación, es necesario realizar una validación de conectividad
   a internet. En caso de contar con una conexión activa, se mostrarán dos listas: una
   que contiene todos los videojuegos proporcionados por el API, y otra donde se
   cargarán los videojuegos favoritos, los cuales estarán almacenados localmente en
   el dispositivo. Si no se detecta una conexión a internet, únicamente se cargarán los
   videojuegos que estén almacenados localmente como favoritos.
2. La información que se deberá presentar al usuario incluye los siguientes datos:
   miniatura (thumbnail), título (title), género (genre), descripción breve
   (short_description), plataforma (platform), editor (publisher), fecha de
   lanzamiento (release_date) (en formato dd/MM/aaaa) y un elemento interactivo
   para ver los detalles completos del videojuego.
3. Cuando un usuario seleccione un videojuego, se realizará una transición a una
   pantalla adicional que servirá para obtener los detalles completos del videojuego a
   través del consumo de una API específica. En dicha pantalla adicional, se
   presentarán visualmente diversos datos relevantes del videojuego, entre ellos su
   miniatura (thumbnail), descripción (description), URL del juego (game_url),
   requisitos mínimos del sistema (minimum_system_requirements) y capturas de
   pantalla (screenshots) las cuales estarán organizadas en un formato de carrusel
   para una mejor visualización. Además, se implementará una funcionalidad
   interactiva que permitirá al usuario guardar el videojuego como favorito utilizando
   la biblioteca de persistencia de datos Room (SqlLite) como solución de
   almacenamiento.
4. La implementación incluirá una fuente de datos que facilitará una transición fluida
   entre las distintas tecnologías de almacenamiento existentes, como Room(SqlLite)
   y Realtime Database, de manera que se pueda adaptar según las necesidades y
   requerimientos específicos del proyecto.
5. Dentro de la lista de videojuegos marcados como favoritos, se debe proporcionar
   al usuario la posibilidad de eliminar videojuegos de su lista de favoritos.
6. Funcionalidad de filtrado de videojuegos: Brindar al usuario la capacidad de filtrar
   los videojuegos según el género (genre) y el editor (publisher), y ordenarlos de
   forma ascendente según la fecha de lanzamiento (release_date)

Esto se realizará con la siguiente Api:
Documentación: https://www.freetogame.com/api-doc
Lista de videojuegos: GET https://www.freetogame.com/api/games
Detalle de videojuego por id: GET https://www.freetogame.com/api/game?id=

# Diseño de la app
Para el inicio del diseño de la aplicación, comenzamos creando diferentes diagramas de flujo, 
extrayendo cada requisito y cómo se vería el flujo en la aplicación. 
Estos son de forma muy general y buscan entender cómo serían los flujos de las funcionalidades de la
aplicación.
![juegosSisteDiagramaFlujo.drawio.png](img_diagrams%2FjuegosSisteDiagramaFlujo.drawio.png)

Podemos ver que estos diseños nos ayudan a entender cuál sería el flujo que debe seguir la 
aplicación. Después de esto, mejoramos un poco más el tema de cómo sería la arquitectura.

Para esta aplicación, utilizaremos una arquitectura MVVM y seguiremos el patrón de arquitectura 
limpia, modularizando la aplicación por características. 
Según esto, este sería el diseño de MVVM según lo recomendado y siguiendo el patrón repositorio.
![arquitectura_Android.png](img_diagrams%2Farquitectura_Android.png)

Con esta arquitectura para cada uno de los módulos, podemos ver cómo quedarían, comunicados por un 
módulo de conexión que sería, en este caso, la app.

Ahora, con esto, podemos ver cómo quedarían los módulos, cómo se comunicarían y, viendo su 
distribución, cómo quedarían conectados.
![DiagramaArquitectura.drawio.png](img_diagrams%2FDiagramaArquitectura.drawio.png)

Finalmente, se crearon los siguientes diagramas de secuencia para entender cómo se comunicaría 
cada componente entre ellos. En este caso, tenemos 5 componentes para que se comuniquen y logren 
entender las interacciones entre objetos o componentes en un sistema a lo largo del tiempo.
![DiagramaSecuencia.drawio.png](img_diagrams%2FDiagramaSecuencia.drawio.png)

# Librerias utilizadas
1. Room database 
2. Retrofit
3. Jetpack Compose
4. Dagger hilt
5. 




