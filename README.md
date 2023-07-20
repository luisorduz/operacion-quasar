# Operación Fuego de Quásar - Challenge-Meli

Operación Fuego de Quásar es un proyecto que se construye para el Challenge(desarrolladores backend) de Meli , desarrollado para la Alianza Rebelde. El objetivo principal del proyecto es ayudar a los rebeldes a descifrar mensajes de auxilio enviados desde naves portacarga imperiales a la deriva en campos de asteroides. El sistema utiliza la técnica de trilateración para determinar la ubicación de la nave y un algoritmo de decodificación para recuperar el mensaje original.

## Introduccion

En este repositorio encontrarás todas las instrucciones necesarias para obtener una copia del proyecto en tu máquina local con el propósito de desarrollar y realizar pruebas. Si estás interesado en implementar el proyecto en producción, te recomendamos revisar la sección de implementación para obtener más detalles.

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
![Logo](https://miro.medium.com/v2/resize:fit:1400/1*yR4C1B-YfMh5zqpbHzTyag.png)

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

## Instalación y ejecución

```bash
Clonar el repositorio: git clone https://github.com/luisorduz/operacion-quasar.git
Navegar al directorio del proyecto: cd proyecto
Compilar el proyecto: mvn clean install
Ejecutar el proyecto Opción 1: mvn spring-boot:run
Ejecutar el proyecto Opción 2: java -jar target/operacion-fuego-de-quasar-1.0


```

Verificar que la aplicación está funcionando correctamente accediendo a la siguiente URL en un navegador web:
http://localhost:8081/api/swagger-ui/#/
## Despliegue

Para desplegar este proyecto solo corra el siguiente comando en la terminal

```bash
  mvn spring-boot:run
```
## Autor

* Luis Andres Orduz 

¡Gracias por el interés en el proyecto de la Operación Fuego de Quásar! Si tienes alguna pregunta o comentario, no dudes en escribirme al correo andresorduz1@gmail.com.

