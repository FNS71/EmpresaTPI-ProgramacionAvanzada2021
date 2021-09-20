# EmpresaTPI-ProgramacionAvanzada2021
TPI Empresa de Desarrollo

## Configuración
### Base de datos
Se requiere una base de datos en el motor PostgreSQL como se habló en la clase. Utilizamos el JDBC Driver de PostgreSQL, librería incluida en el proyecto.
La configuración de acceso la encuentra en la clase "Conexion" del package Modelos (ubicación: Modelos/Conexion.java).
Se deben cambiar las variables del comienzo del archivo donde se indica el `usuario`, `contra` y `DB`.

Para el funcionamiento del programa solo es necesario que exista una base de datos creada (no importa el nombre, siempre y cuando la variable `DB` previamente mencionada esté correctamente cargada). El propio sistema se encarga de ejecutar una Query para crear todas las tablas y relaciónes.

En caso de que las tablas no estén generadas (como la primera vez que inicie el programa) le aparecerá un aviso (`JOptionPane.showConfirmDialog`), al cual debe darle `yes` para que se le creen todas las tablas. Puede ver las salidas del script en la consola, o consultar el script en el archivo `scriptInicial.sql` ubicado en el package Modelos.

## Documentación
### Modelo utilizado
Se utiliza el patrón de diseño MVC (Modelo - Vista - Controlador), por lo que se definen los controladores para cada una de las vistas, y luego las clases para interactuar con el modelo. Puede ver el contenido en sus respectivos packages.

#### Modelos
Las clases se detallan en el package Modelos, donde a cada clase persistente le corresponden 2 clases dentro del package con su propio archivo: claseVO y claseBO.
##### class ClaseVO()
Cada clase persistente se representa por una clase llamada {nombreClase}+VO, en dicha clase se definen:
- atributos de la clase
- constructores de la clase (sobrecargados)
- getters de cada atributo
- setters de cada atributo
- otros métodos de la clase

Sobre éstas clases se mapearán los objetos desde Java antes de guardarlos en la base de datos y después de obtenerlos de ella.

##### class ClaseBO()
Ésta clase extiende a la clase Conexión, que va a ser la clase padre de la que todas las clases BO heredarán sus atributos y métodos. El objetivo de ésta generalización es heredar los atributos de la conexión y los métodos necesarios.

Dentro de cada clase se definen métodos para manejar el ABMC de cada clase, junto con los métodos auxiliares que pueden necesitar los ABMC.
Ejemplos:
- AlmacenarObjeto
- ModificarObjeto
- EliminarObjeto
- buscarTodos
- nuevoId

Además de otros métodos secundarios, como por ejemplo, la clase proyecto requiere obtener datos de los clientes existentes y los tipos de proyectos, tanto para la tabla como para los combobox.
#### Controladores
Los controladores tienen la funcionalidad de separar el código funcional de las vistas. Cada clase tiene su controlador, el cual le otorgará a los formularios (vistas) la funcionalidad necesaria.
Dentro de las funcionalidades tenemos:
- `actionListeners` para escuchar cuando el usuario interactua con un elemento y luego realizar la operación deseada.
- Cambio de modos entre `modoAlta()`, `modoModificacion()`
- Selección de objetos cuando el usuario marca una fila en la tabla.
- Llenado de `JTable` y `JComboBox`.
- Validaciónes de formularios.
- Gestionar altas, bajas y eliminaciones.


#### Vistas
En las vistas se definen los formularios bajo el nombre frmClase. Las vistas contienen solo el formulario propiamente dicho, sin código adentro, para respetar el patrón MVC.
La programación de las funcionalidades se realiza dentro de los controladores.


### Librerías
Se utilizan las librerías:
- PostgreSQL JDBC Driver (postgresql@~42.2.10)
- jCalendar-tz@~1.3.3-4
- mybatis@~3.5.7

Java JDK 13.

## Autores
- Funes, Federico René
- Pattuzzi, Aylén Romina
- Quaino, Fabio Matías José
- Villasuso, Carlos Emanuel

