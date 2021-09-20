# EmpresaTPI-ProgramacionAvanzada2021
TPI Empresa de Desarrollo

## Configuración
### Base de datos
Se requiere una base de datos en el motor PostgreSQL como se habló en la clase. Utilizamos el JDBC Driver de PostgreSQL, librería incluida en el proyecto.
La configuración de acceso la encuentra en la clase "Conexion" del package Modelos (ubicación: Modelos/Conexion.java).
Se deben cambiar las variables del comienzo del archivo donde se indica el `user`, `contra` y `db`.

Para el funcionamiento del programa solo es necesario que exista una base de datos creada (no importa el nombre, siempre y cuando la variable `db` previamente mencionada esté correctamente cargada). El propio sistema se encarga de ejecutar una Query para crear todas las tablas y relaciónes.

En caso de que las tablas no estén generadas (como la primera vez que inicie el programa) le aparecerá un aviso (`JOptionPane.showConfirmDialog`), al cual debe darle `yes` para que se le creen todas las tablas. Puede ver las salidas del script en la consola, o consultar el script en el archivo `scriptInicial.sql` ubicado en el package Modelos.

## Documentación
### Modelo utilizado
Se utiliza el patrón de diseño MVC (Modelo - Vista - Controlador), por lo que se definen los controladores para cada una de las vistas, y luego las clases para interactuar con el modelo. Puede ver el contenido en sus respectivos packages.

### Librerías
Se utilizan las librerías:
- PostgreSQL JDBC Driver (postgresql@~42.2.10)
- jCalendar-tz@~1.3.3-4
- mybatis@~3.5.7

Java JDK 13.

