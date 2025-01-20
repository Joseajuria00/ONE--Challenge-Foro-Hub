# Challenge Foro Hub - ONE (Oracle Next Education)
---
#### Autores: José Ajuriagogeascoa
#### Para ONE + Alura Latam.
***

## 📝 Descripción
Foro Hub es una aplicación backend desarollada como parte del desafío de ONE. Diseñada para gestionar un foro interactivo donde los usuarios pueden crear, visualizar, y moderar tópicos y respuestas

## 🏆 Características
* Inicialización automática de datos (Tópicos, Repsuestas y Usuarios).
* CRUD de tópicos, respuestas y usuarios.
* API REST construida con Spring Boot.
* Seguridad y autenticación.
* Acceso restringido a ciertas funcionalidades sin token.
* Migración de base de datos.

## ⚙️Tecnologías Utilizadas
* **Backend:**
  * **Java**: Lenguaje de programación principal.
  * **Spring Boot**: Framework utilizado para estructurar el backend y desarrollo rápido.
  * **Spring Security**: Manejo de autenticación y autorización.
  * **Spring Data JPA**: Persitencia de Datos.
  * **Spring Validation**: Validaciones.
* **Database:**
  * **MySQL**: Base de datos relacional.
  * **Flyway**: Migración y versionado de Bases de Datos.
* **Herramientas y Utilidades
  * **Swagger**: Documentación interactiva para APIs.
  * **Lombok**: Simplificación del código Java.
  * **Maven**: Gestión y mantenimiento de dependencias.


## 🚀Endpoints Principales
| Método | Endpoint                 | Descripción                        | Parámetros / Body       |
|--------|--------------------------|------------------------------------|-------------------------|
| GET    | `/topicos`               | Obtener todos los tópicos          | -                       |
| GET    | `/topicos/{id}`          | Obtener tópico según id            | `{id}`: ID del curso    |
| POST   | `/topicos`               | Crear un nuevo tópico              | `{titulo, mensaje, ...}`|
| POST   | `/login`                 | Autenticar un usuario              | `{email, password}`     |
|        | `/swagger-ui/index.html` | Documentación de los API endpoints |                         |

## Documentación Interactiva (Swagger)
Es posible explorar y testear los API endpoints usando la documentación generadoa por la herramienta Swagger. Una vez ejecutado el poyecto se puede utilizar esta documentación accediendo a la url indicada más arriba.

## 🛡️Sguridad
El backend implementa autenticación y autorización usando:
- **Spring Security**: Para la seguridad general de la aplicación.
- **JWT Tokens**: Protección de endpoints
- **BCrypt**: Para la encriptación de las contraseñas.

## Ejecución del Programa

## 📞 Contacto
* GitHub: [Joseajuria00](https://github.com/Joseajuria00)
* LinkedIn: [José Ajuriagogeascoa D'Amico](https://www.linkedin.com/in/joseajuriagogeascoa/)
