# ScreenBook 📚

**ScreenBook** es una aplicación Java desarrollada con Spring Boot que permite buscar y registrar libros y autores utilizando la API pública [Gutendex](https://gutendex.com/books/), basada en el Proyecto Gutenberg. La aplicación almacena los datos localmente mediante una base de datos relacional y proporciona una interfaz en consola.

## 🛠 Tecnologías Utilizadas

- Java 17+
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Gutendex API

## 🚀 Funcionalidades Principales

- 🔍 Buscar libros por título
- 📚 Visualizar libros registrados en base de datos
- 👩‍💼 Ver autores registrados
- 📅 Consultar autores vivos en un año determinado
- 🌐 Buscar libros por idioma

## ⚙️ Configuración de Base de Datos

La aplicación utiliza PostgreSQL. Puedes configurar las credenciales y la conexión mediante variables de entorno o modificando el archivo `application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://${DB_HOST}/${DB_SCREENBOOK}
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASSWORD}
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.format-sql=true
spring.jpa.properties.hibernate.formal_sql=true
```

> ✅ Reemplaza `${DB_HOST}`, `${DB_SCREENBOOK}`, `${DB_USER}` y `${DB_PASSWORD}` con los valores reales o define esas variables en tu entorno.

### Ejemplo de archivo `.env`:

```dotenv
DB_HOST=localhost:5432
DB_SCREENBOOK=screenbook_db
DB_USER=postgres
DB_PASSWORD=tu_contraseña
```

## 🧪 Ejecución del Proyecto

1. Clona el repositorio:
   ```bash
   git clone https://github.com/tuusuario/screenbook.git
   cd screenbook
   ```

2. Configura las variables de entorno necesarias o edita directamente `application.properties`.

3. Ejecuta la aplicación:
   ```bash
   ./mvnw spring-boot:run
   ```

4. Interactúa con el menú en consola:
   ```
   1 - Buscar por título
   2 - Ver los libros registrados
   3 - Ver los autores registrados
   4 - Ver los autores vivos en un determinado año
   5 - Ver libros por idioma
   0 - Salir
   ```

## 📦 Estructura del Proyecto

```
src/
├── dto/           # Clases de transferencia de datos (DTOs)
├── model/         # Entidades JPA
├── repository/    # Interfaces de repositorio (JPA)
├── service/       # Servicios de consumo de API y conversión de datos
└── principal/     # Clase Principal.java (menú interactivo)
```

## 🔗 API Utilizada

- [Gutendex Books API](https://gutendex.com)

## 📖 Créditos

Proyecto desarrollado como parte del curso de Java y Spring de **Alura**.

## 📝 Licencia

Distribuido bajo la licencia MIT.
