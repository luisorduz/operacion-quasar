# Operación Fuego de Quásar - Challenge - Meli

Operación Fuego de Quásar es un proyecto del Challenge de Meli destinado a desarrolladores backend. En esta aventura, tuve el honor de brindar mi ayuda a la Alianza Rebelde. El objetivo principal del proyecto es ayudar a los rebeldes a descifrar mensajes de auxilio enviados desde naves portacarga imperiales a la deriva en campos de asteroides. El sistema utiliza la técnica de trilateración para determinar la ubicación de la nave y un algoritmo de decodificación para recuperar el mensaje original.

## Introduccion

En este repositorio encontrarás todas las instrucciones necesarias para obtener una copia del proyecto en tu máquina local con el propósito de desarrollar y realizar pruebas. Si estás interesado en implementar el proyecto en producción, te recomendamos revisar la sección de Despliegue para obtener más detalles.

### Requisitos previos

Antes de ejecutar el proyecto, asegúrate de tener instalados los siguientes requisitos:
- Un IDE de desarrollo como Spring Tool Suite o Eclipse.
- Una terminal para ejecutar comandos.
- Java 11 o una versión posterior instalada en tu sistema.
- Maven, una herramienta de construcción y gestión de dependencias para Java. Asegúrate de tenerlo instalado y configurado en tu entorno de desarrollo.

## Tecnologías utilizadas

**Java 11**

**SpringBoot** (web, jpa, security, test)

**Hibernate**

**H2** Database

**Maven**

**Junit** Mockito

**Swagger2**

**Mapstruct**

### Arquitectura del Proyecto
Este proyecto sigue la arquitectura hexagonal (también conocida como "puertos y adaptadores"), que es un enfoque de diseño de software que separa la lógica empresarial central de los detalles de implementación. En esta arquitectura, la lógica empresarial se encuentra en el centro (el núcleo), mientras que los detalles de la infraestructura, como la base de datos o la interfaz de usuario, se encuentran en los adaptadores. Los puertos son interfaces que definen la comunicación entre el núcleo y los adaptadores.

La arquitectura hexagonal permite una mayor modularidad y facilita la prueba y el mantenimiento del código. En este proyecto, se utilizó Spring Boot como framework para implementar la arquitectura hexagonal. La capa del núcleo contiene la lógica empresarial, mientras que las capas de los adaptadores contienen la implementación de la API RESTful y la persistencia de datos en la base de datos.

![Logo](https://miro.medium.com/v2/resize:fit:450/1*yR4C1B-YfMh5zqpbHzTyag.png)

- Diagrama de Contexto
![Logo](http://creaduz.com/wp-content/uploads/2023/07/ArquitecturaC4Contexto.png)

- Diagrama de Componentes
![Logo](http://creaduz.com/wp-content/uploads/2023/07/ArquitecturaC4Componentes.png)

### Estructura de Directorios
- src/main/java: Contiene el código fuente del proyecto.
- com.quasar.orduz.adapters: Contiene los adaptadores para la API REST.
- com.quasar.orduz.adapters.swagger: Contiene la configuracion Swagger para la API REST.
- com.quasar.orduz.adapters.rest: Contiene los controladores y DTOs para la API REST.
- com.quasar.orduz.adapters.rest.exceptions: Contiene excepciones personalizadas para la API REST.
- com.quasar.orduz.adapters.constans: Contiene constantes del proyecto.
- com.quasar.orduz.domain: Contiene el dominio del proyecto.
- com.quasar.orduz.domain.model: Contiene las clases de dominio, como Satellite y Point.
- com.quasar.orduz.ports: Contiene los puertos del proyecto.
- com.quasar.orduz.ports.service: Contiene las interfaces y clases de servicio de los puertos.
- com.quasar.orduz.ports.service: Contiene las implementaciones de los servicios.

## Validaciones
Se utilizan anotaciones de validación para asegurar que los datos recibidos en las solicitudes HTTP cumplan con ciertas restricciones.

## Configuración de la Base de Datos
El proyecto utiliza una base de datos H2 en memoria para almacenar los datos de los satélites (Realice un script para cuando ejecutes el proyecto en local se registren los datos de los saliletes en la bd H2, el archivo se encuentra en resources/import.sql.
La configuración de la base de datos se encuentra en el archivo application.yml:
```bash
  datasource:
    username: sa
    url: jdbc:h2:mem:testdb
    driverClassName: org.h2.Driver
    password: password
    h2:
      console:
        enabled: 'true'
    jpa:
      database-platform: org.hibernate.dialect.H2Dialect
      hibernate:
        ddl-auto: update
      show-sql: true


```


Si deseas ejecutar el proyecto en produccion debes realizar la siguiente configuracion, en mi caso uso Postgres:
```bash
    datasource:
    url: jdbc:postgresql://quasar.abcde.us-east-1.rds.amazonaws.com:5432/name_db
    driver-class-name: org.postgresql.Driver
    username: username
    password: "#Contraseña2023"


```

Recuerda que debes agregar la siguiente dependencia y quitar la de H2 :
```bash
    <dependency>
        <groupId>org.postgresql</groupId>
        <artifactId>postgresql</artifactId>
        <scope>runtime</scope>
    </dependency>
```


## Instalación y ejecución

```bash
Clonar el repositorio: git clone https://github.com/luisorduz/operacion-quasar.git

Opción 1:
Navegar al directorio del proyecto: cd proyecto
Compilar el proyecto: mvn clean install
Ejecutar el proyecto Opción 1: mvn spring-boot:run
Ejecutar el proyecto Opción 2: java -jar target/operacion-fuego-de-quasar-1.0

Opción 2:
Importa el proyecto en tu IDE como "Existing Maven Project".
Ejecuta la aplicación desde tu IDE o mediante el comando mvn spring-boot:run en la terminal.
La aplicación se ejecutará en http://localhost:8081.

```

Verificar que la aplicación está funcionando correctamente accediendo a la siguiente URL en un navegador web:
- Entorno Local : http://localhost:8081/api/swagger-ui/#/
- Entorno Producción : http://quasar.us-east-1.elasticbeanstalk.com/api/swagger-ui/#/
## Endpoints

- Documentación Postman: https://documenter.getpostman.com/view/6097625/2s946mbqaD

**POST /topsecret**

Este endpoint recibe una solicitud HTTP con la información de tres satélites y devuelve la ubicación de la nave y el mensaje decodificado.

**POST /topsecret_split/{satellite_name}**

Este endpoint recibe una solicitud HTTP con la información de un satélite específico y actualiza su distancia y mensaje en la base de datos.

**GET /topsecret_split_by_name/{satellite_name}**

Este endpoint recibe una solicitud HTTP con el nombre de un satélite y devuelve su información almacenada en la base de datos.

**GET /get_all_topsecret/**

Este endpoint devuelve una lista con la información de todos los satélites almacenados en la base de datos.


## Pruebas Unitarias

Se han incluido pruebas unitarias para validar el funcionamiento de los controladores y servicios. Estas pruebas se pueden ejecutar mediante el comando mvn test.

```bash
  mvn test
```

## Despliegue en AWS Beanstalk
Para realizar el despliegue del proyecto en AWS Beanstalk, sigue los siguientes pasos:

- Crea una cuenta en AWS: Si aún no tienes una cuenta en AWS, regístrate en su plataforma para poder acceder a sus servicios.

- Accede al servicio AWS Elastic Beanstalk: Ingresa a tu cuenta de AWS y navega hasta el servicio de Elastic Beanstalk.

- Crea una nueva aplicación: Dentro de Elastic Beanstalk, crea una nueva aplicación con el nombre "OperacionFuegoDeQuasar".

- Crea un nuevo entorno de ejecución: Una vez creada la aplicación, procede a configurar un nuevo entorno de ejecución para tu proyecto.

- Selecciona la plataforma "Java": En el proceso de configuración del entorno, asegúrate de seleccionar la plataforma "Java" para que AWS sepa cómo ejecutar tu aplicación.

- Sube el archivo JAR: Utiliza la opción para cargar el archivo JAR generado previamente con el comando "mvn package" en tu proyecto.

- Configura las variables de entorno: Ajusta las variables de entorno necesarias para el correcto funcionamiento de tu aplicación. Por ejemplo yo uso estas dos acionales( SERVER_PORT:5000 y TZ:America/Bogota )

- Ajusta la configuración: Personaliza la configuración del entorno según las especificaciones requeridas para tu proyecto.

- Crea el entorno: Haz clic en "Crear entorno" y espera a que AWS complete el proceso de despliegue.

- Verifica el estado del entorno: Una vez que el entorno esté en funcionamiento, verifica su estado y asegúrate de que esté listo para recibir solicitudes.

- Accede a la aplicación: Finalmente, podrás acceder a tu aplicación utilizando la URL proporcionada por AWS, y tu proyecto estará disponible para su uso.

Con estos pasos, tendrás tu proyecto desplegado en AWS Elastic Beanstalk y listo para ser utilizado.

- Url de producción : http://quasar.us-east-1.elasticbeanstalk.com/
- Diagrama de Arquitectura AWS (Servicios adicionales configurados para tener un ambiente controlado y escalable)
![Logo](http://creaduz.com/wp-content/uploads/2023/07/Arquitecturadespliegue.png)

## Autor

* Luis Andres Orduz 
* Email: andresorduz1@gmail.com

Si tienes alguna pregunta o comentario, no dudes en escribirme al correo andresorduz1@gmail.com.

