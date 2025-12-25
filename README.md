# 📚 EduTECH - Sistema de Gestión de Biblioteca Digital

Sistema de gestión educativa basado en arquitectura de microservicios que permite administrar cursos, usuarios, inscripciones, pagos, evaluaciones, reportes y soporte técnico de manera desacoplada y escalable.

## 📋 Tabla de Contenidos

- [Descripción](#-descripción)
- [Arquitectura](#-arquitectura)
- [Tecnologías](#-tecnologías-utilizadas)
- [Requisitos Previos](#-requisitos-previos)
- [Instalación](#-instalación)
- [Configuración](#-configuración)
- [Estructura del Proyecto](#-estructura-del-proyecto)
- [Microservicios](#-microservicios)
- [API Endpoints](#-api-endpoints)
- [Ejemplos de Uso](#-ejemplos-de-uso)
- [Comandos Útiles](#-comandos-útiles)
- [Troubleshooting](#-troubleshooting)
- [Contribución](#-contribución)

---

## 🎯 Descripción

EduTECH es una plataforma educativa completa que gestiona el ciclo de vida de cursos en línea, desde la inscripción de estudiantes hasta la evaluación y generación de reportes. El sistema está diseñado con una arquitectura de microservicios que permite:

- **Escalabilidad independiente** de cada servicio
- **Alta disponibilidad** y tolerancia a fallos
- **Desarrollo y despliegue independiente** de cada módulo
- **Base de datos dedicada** por servicio para mejor rendimiento
- **Mantenibilidad** mejorada mediante separación de responsabilidades

---

## 🧩 Arquitectura

El sistema sigue una arquitectura de microservicios RESTful donde cada servicio es independiente y se comunica mediante APIs HTTP/JSON.

```
                                  ┌────────────────────────────┐
                                  │ Cliente (Postman / Frontend)│
                                  └────────────▲────────────────┘
                                               │
                                    REST APIs (HTTP JSON)
                                               │
            ┌────────────────────┬────────────┬─────────────┬────────────────┬───────────────┬────────────────┬──────────────┐
        ┌───▼─────────────┐ ┌─────▼─────────┐ ┌─▼─────────────┐ ┌────────────▼───┐ ┌──────────▼─────┐ ┌────────▼────────────┐ ┌──────────▼────────┐
        │ Servicio        │ │ Servicio      │ │ Servicio      │ │ Servicio       │ │ Servicio       │ │ Servicio            │ │ Servicio          │
        │ Usuarios        │ │ Cursos        │ │ Inscripciones │ │ Pagos          │ │ Soporte        │ │ Evaluaciones        │ │ Reportes          │
        │ :8082           │ │ :8081         │ │ :8083         │ │ :8084          │ │ :8085          │ │ :8086               │ │ :8087             │
        └────┬────────────┘ └────┬──────────┘ └────┬──────────┘ └───────┬────────┘ └───────┬────────┘ └───────┬─────────────┘ └───────┬────────────┘
             ▼                   ▼                 ▼                    ▼                  ▼                 ▼                 ▼
         🗃️ db_usuarios    🗃️ db_cursos      🗃️ db_inscripciones   🗃️ db_pagos      🗃️ db_soporte     🗃️ db_evaluaciones   🗃️ db_reportes
```

### Principios de Diseño

- **Separación de responsabilidades**: Cada microservicio gestiona un dominio específico
- **Base de datos por servicio**: Cada servicio tiene su propia base de datos MySQL
- **API RESTful**: Comunicación mediante HTTP/JSON
- **Stateless**: Los servicios no mantienen estado entre peticiones
- **CORS habilitado**: Permite peticiones desde cualquier origen

---

## ⚙️ Tecnologías Utilizadas

| Tecnología | Versión | Propósito |
|-----------|---------|-----------|
| **Java** | 17+ | Lenguaje de programación |
| **Spring Boot** | 3.4.5 | Framework principal |
| **Spring Web** | 3.4.5 | API REST |
| **Spring Data JPA** | 3.4.5 | Persistencia de datos |
| **MySQL** | 8.0+ | Base de datos relacional |
| **Lombok** | Latest | Reducción de código boilerplate |
| **Maven** | 3.6+ | Gestión de dependencias |
| **Postman** | Latest | Pruebas de API |

### Dependencias Principales

- `spring-boot-starter-web`: Servidor web embebido y soporte REST
- `spring-boot-starter-data-jpa`: Integración con JPA/Hibernate
- `mysql-connector-j`: Driver de MySQL
- `lombok`: Anotaciones para generar código automáticamente
- `spring-boot-starter-test`: Herramientas de testing

---

## 📦 Requisitos Previos

Antes de comenzar, asegúrate de tener instalado:

- **Java JDK 17 o superior**
  ```bash
  java -version
  ```

- **Maven 3.6 o superior**
  ```bash
  mvn -version
  ```

- **MySQL 8.0 o superior**
  ```bash
  mysql --version
  ```

- **Git** (opcional, para clonar el repositorio)
  ```bash
  git --version
  ```

---

## 🚀 Instalación

### 1. Configurar Bases de Datos

Crea las bases de datos necesarias en MySQL:

```sql
CREATE DATABASE db_usuarios;
CREATE DATABASE db_cursos;
CREATE DATABASE db_inscripciones;
CREATE DATABASE db_pagos;
CREATE DATABASE db_soporte;
CREATE DATABASE db_evaluaciones;
CREATE DATABASE db_reportes;
```

### 2. Configurar Credenciales

Edita los archivos `application.properties` de cada servicio con tus credenciales de MySQL:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/db_[nombre_servicio]
spring.datasource.username=root
spring.datasource.password=tu_contraseña
```

### 3. Compilar el Proyecto

Desde la raíz del proyecto, compila cada servicio:

```bash
# Servicio de Cursos
cd serviciocursos/serviciocursos
mvn clean install

# Repite para cada servicio...
```

O compila todos los servicios con un script:

```bash
# Desde la raíz del proyecto
for dir in */; do
  cd "$dir"*/ && mvn clean install && cd ../..
done
```

### 4. Ejecutar los Servicios

Ejecuta cada servicio en una terminal separada:

```bash
# Terminal 1 - Servicio de Cursos
cd serviciocursos/serviciocursos
mvn spring-boot:run

# Terminal 2 - Servicio de Usuarios
cd serviciousuarios/serviciousuarios
mvn spring-boot:run

# Repite para cada servicio...
```

O ejecuta directamente con Java:

```bash
java -jar target/serviciocursos-0.0.1-SNAPSHOT.jar
```

---

## ⚙️ Configuración

### Configuración de Puertos

Cada servicio está configurado para ejecutarse en un puerto específico:

| Servicio | Puerto | Base de Datos |
|----------|--------|---------------|
| Cursos | 8081 | db_cursos |
| Usuarios | 8082 | db_usuarios |
| Inscripciones | 8083 | db_inscripciones |
| Pagos | 8084 | db_pagos |
| Soporte | 8085 | db_soporte |
| Evaluaciones | 8086 | db_evaluaciones |
| Reportes | 8087 | db_reportes |

### Configuración de JPA

Por defecto, cada servicio está configurado con:

```properties
spring.jpa.hibernate.ddl-auto=create-drop  # Recrea las tablas en cada inicio
spring.jpa.show-sql=true                    # Muestra las consultas SQL
spring.jpa.properties.hibernate.format_sql=true  # Formatea las consultas SQL
```

**⚠️ Advertencia**: `create-drop` elimina todas las tablas al detener la aplicación. Para producción, cambia a `update` o `validate`.

---

## 📁 Estructura del Proyecto

```
EduTECH/
├── README.md
├── serviciocursos/
│   └── serviciocursos/
│       ├── pom.xml
│       ├── src/
│       │   ├── main/
│       │   │   ├── java/cl/douc/edutech/serviciocursos/
│       │   │   │   ├── ServiciocursosApplication.java
│       │   │   │   ├── controller/
│       │   │   │   │   └── CursoController.java
│       │   │   │   ├── service/
│       │   │   │   │   └── CursoService.java
│       │   │   │   ├── repository/
│       │   │   │   │   └── CursoRepository.java
│       │   │   │   └── model/
│       │   │   │       └── Curso.java
│       │   │   └── resources/
│       │   │       └── application.properties
│       │   └── test/
│       └── target/
├── serviciousuarios/
│   └── serviciousuarios/
│       └── [estructura similar]
├── servicioinscripcion/
├── serviciopago/
├── serviciosoporte/
├── servicioevaluacion/
└── servicioreportes/
```

### Estructura de Cada Microservicio

Cada microservicio sigue el patrón **MVC (Model-View-Controller)** adaptado a REST:

```
servicio/
├── controller/     → Expone endpoints REST (@RestController)
├── service/        → Lógica de negocio (@Service)
├── repository/     → Acceso a datos vía JPA (@Repository)
├── model/          → Entidades JPA (@Entity)
└── application.properties → Configuración del servicio
```

---

## 🔧 Microservicios

### 1. 📖 Servicio de Cursos (`serviciocursos`)

**Puerto**: 8081  
**Base de Datos**: `db_cursos`  
**Propósito**: Gestiona el catálogo de cursos disponibles

#### Modelo de Datos

```java
{
  "id": Long,                    // ID único del curso
  "nombre": String,              // Nombre del curso
  "descripcion": String,         // Descripción detallada
  "cuposTotales": int,           // Total de cupos disponibles
  "cuposDisponibles": int,       // Cupos restantes
  "categoria": String,           // Categoría del curso
  "activo": boolean              // Estado del curso
}
```

#### Endpoints

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/v1/cursos` | Lista todos los cursos |
| GET | `/api/v1/cursos/{id}` | Obtiene un curso por ID |
| POST | `/api/v1/cursos` | Crea un nuevo curso |
| PUT | `/api/v1/cursos/{id}` | Actualiza un curso existente |
| DELETE | `/api/v1/cursos/{id}` | Elimina un curso |

---

### 2. 👥 Servicio de Usuarios (`serviciousuarios`)

**Puerto**: 8082  
**Base de Datos**: `db_usuarios`  
**Propósito**: Gestiona la información de usuarios del sistema

#### Modelo de Datos

```java
{
  "id": Long,                    // ID único del usuario
  "nombre": String,              // Nombre del usuario
  "apellido": String,            // Apellido del usuario
  "email": String,               // Correo electrónico
  "rut": String,                 // RUT del usuario
  "tipo": String,                // Tipo: "Estudiante", "Docente", "Administrativo"
  "activo": boolean              // Estado del usuario
}
```

#### Endpoints

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/v1/usuarios` | Lista todos los usuarios |
| GET | `/api/v1/usuarios/{id}` | Obtiene un usuario por ID |
| POST | `/api/v1/usuarios` | Crea un nuevo usuario |
| PUT | `/api/v1/usuarios/{id}` | Actualiza un usuario existente |
| DELETE | `/api/v1/usuarios/{id}` | Elimina un usuario |

---

### 3. 📝 Servicio de Inscripciones (`servicioinscripcion`)

**Puerto**: 8083  
**Base de Datos**: `db_inscripciones`  
**Propósito**: Gestiona las inscripciones de usuarios a cursos

#### Modelo de Datos

```java
{
  "id": Long,                    // ID único de la inscripción
  "idUsuario": Long,             // ID del usuario inscrito
  "idCurso": Long,               // ID del curso
  "fechaInscripcion": LocalDate, // Fecha de inscripción
  "confirmada": boolean           // Estado de confirmación
}
```

#### Endpoints

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/v1/inscripciones` | Lista todas las inscripciones |
| GET | `/api/v1/inscripciones/{id}` | Obtiene una inscripción por ID |
| POST | `/api/v1/inscripciones` | Crea una nueva inscripción |
| DELETE | `/api/v1/inscripciones/{id}` | Elimina una inscripción |

---

### 4. 💳 Servicio de Pagos (`serviciopago`)

**Puerto**: 8084  
**Base de Datos**: `db_pagos`  
**Propósito**: Registra y gestiona los pagos realizados por los usuarios

#### Modelo de Datos

```java
{
  "id": Long,                    // ID único del pago
  "idUsuario": Long,             // ID del usuario que realiza el pago
  "idInscripcion": Long,         // ID de la inscripción asociada
  "monto": Double,               // Monto del pago
  "fechaPago": LocalDate,        // Fecha del pago
  "metodo": String               // Método: "Transferencia", "Tarjeta", etc.
}
```

#### Endpoints

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/v1/pagos` | Lista todos los pagos |
| GET | `/api/v1/pagos/{id}` | Obtiene un pago por ID |
| POST | `/api/v1/pagos` | Registra un nuevo pago |
| DELETE | `/api/v1/pagos/{id}` | Elimina un pago |

---

### 5. 🎓 Servicio de Evaluaciones (`servicioevaluacion`)

**Puerto**: 8086  
**Base de Datos**: `db_evaluaciones`  
**Propósito**: Registra las calificaciones de usuarios en cursos

#### Modelo de Datos

```java
{
  "id": Long,                    // ID único de la evaluación
  "idUsuario": Long,             // ID del usuario evaluado
  "idCurso": Long,               // ID del curso
  "nota": Double,                // Calificación obtenida
  "observaciones": String        // Comentarios del docente
}
```

#### Endpoints

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/v1/evaluaciones` | Lista todas las evaluaciones |
| GET | `/api/v1/evaluaciones/{id}` | Obtiene una evaluación por ID |
| POST | `/api/v1/evaluaciones` | Crea una nueva evaluación |
| PUT | `/api/v1/evaluaciones/{id}` | Actualiza una evaluación |
| DELETE | `/api/v1/evaluaciones/{id}` | Elimina una evaluación |

---

### 6. 🆘 Servicio de Soporte (`serviciosoporte`)

**Puerto**: 8085  
**Base de Datos**: `db_soporte`  
**Propósito**: Gestiona solicitudes de soporte técnico y consultas

#### Modelo de Datos

```java
{
  "id": Long,                    // ID único del ticket
  "idUsuario": Long,             // ID del usuario que solicita soporte
  "tipo": String,                // Tipo: "Reclamo", "Consulta", "Sugerencia"
  "descripcion": String,         // Descripción del problema
  "estado": String,              // Estado: "Pendiente", "En proceso", "Resuelto"
  "fechaCreacion": LocalDateTime // Fecha de creación del ticket
}
```

#### Endpoints

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/v1/soporte` | Lista todos los tickets |
| GET | `/api/v1/soporte/{id}` | Obtiene un ticket por ID |
| POST | `/api/v1/soporte` | Crea un nuevo ticket |
| DELETE | `/api/v1/soporte/{id}` | Elimina un ticket |

---

### 7. 📊 Servicio de Reportes (`servicioreportes`)

**Puerto**: 8087  
**Base de Datos**: `db_reportes`  
**Propósito**: Genera y almacena reportes del sistema

#### Modelo de Datos

```java
{
  "id": Long,                    // ID único del reporte
  "tipo": String,                // Tipo: "Usuarios por curso", "Pagos realizados", etc.
  "descripcion": String,         // Descripción del reporte
  "generadoPor": String,         // Quién generó el reporte: "admin", "sistema", etc.
  "fechaGeneracion": LocalDateTime // Fecha de generación
}
```

#### Endpoints

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/v1/reportes` | Lista todos los reportes |
| GET | `/api/v1/reportes/{id}` | Obtiene un reporte por ID |
| POST | `/api/v1/reportes` | Genera un nuevo reporte |
| DELETE | `/api/v1/reportes/{id}` | Elimina un reporte |

---

## 📡 API Endpoints

### Resumen de Endpoints por Servicio

| Servicio | Base URL | Endpoints Disponibles |
|----------|----------|----------------------|
| Cursos | `http://localhost:8081/api/v1/cursos` | GET, POST, PUT, DELETE |
| Usuarios | `http://localhost:8082/api/v1/usuarios` | GET, POST, PUT, DELETE |
| Inscripciones | `http://localhost:8083/api/v1/inscripciones` | GET, POST, DELETE |
| Pagos | `http://localhost:8084/api/v1/pagos` | GET, POST, DELETE |
| Evaluaciones | `http://localhost:8086/api/v1/evaluaciones` | GET, POST, PUT, DELETE |
| Soporte | `http://localhost:8085/api/v1/soporte` | GET, POST, DELETE |
| Reportes | `http://localhost:8087/api/v1/reportes` | GET, POST, DELETE |

---

## 💡 Ejemplos de Uso

### Crear un Curso

**Request:**
```http
POST http://localhost:8081/api/v1/cursos
Content-Type: application/json

{
  "nombre": "Java Avanzado",
  "descripcion": "Curso de programación Java para nivel avanzado",
  "cuposTotales": 30,
  "cuposDisponibles": 30,
  "categoria": "Programación",
  "activo": true
}
```

**Response:**
```json
{
  "id": 1,
  "nombre": "Java Avanzado",
  "descripcion": "Curso de programación Java para nivel avanzado",
  "cuposTotales": 30,
  "cuposDisponibles": 30,
  "categoria": "Programación",
  "activo": true
}
```

### Crear un Usuario

**Request:**
```http
POST http://localhost:8082/api/v1/usuarios
Content-Type: application/json

{
  "nombre": "Juan",
  "apellido": "Pérez",
  "email": "juan.perez@example.com",
  "rut": "12345678-9",
  "tipo": "Estudiante",
  "activo": true
}
```

### Inscribir un Usuario en un Curso

**Request:**
```http
POST http://localhost:8083/api/v1/inscripciones
Content-Type: application/json

{
  "idUsuario": 1,
  "idCurso": 1,
  "fechaInscripcion": "2024-01-15",
  "confirmada": true
}
```

### Registrar un Pago

**Request:**
```http
POST http://localhost:8084/api/v1/pagos
Content-Type: application/json

{
  "idUsuario": 1,
  "idInscripcion": 1,
  "monto": 50000.0,
  "fechaPago": "2024-01-16",
  "metodo": "Transferencia"
}
```

### Crear una Evaluación

**Request:**
```http
POST http://localhost:8086/api/v1/evaluaciones
Content-Type: application/json

{
  "idUsuario": 1,
  "idCurso": 1,
  "nota": 6.5,
  "observaciones": "Excelente desempeño en el curso"
}
```

### Crear un Ticket de Soporte

**Request:**
```http
POST http://localhost:8085/api/v1/soporte
Content-Type: application/json

{
  "idUsuario": 1,
  "tipo": "Consulta",
  "descripcion": "No puedo acceder a mi curso",
  "estado": "Pendiente",
  "fechaCreacion": "2024-01-20T10:30:00"
}
```

### Generar un Reporte

**Request:**
```http
POST http://localhost:8087/api/v1/reportes
Content-Type: application/json

{
  "tipo": "Usuarios por curso",
  "descripcion": "Reporte de usuarios inscritos en cada curso",
  "generadoPor": "admin",
  "fechaGeneracion": "2024-01-20T14:00:00"
}
```

---

## 🛠️ Comandos Útiles

### Compilar un Servicio

```bash
cd serviciocursos/serviciocursos
mvn clean install
```

### Ejecutar un Servicio

```bash
mvn spring-boot:run
```

### Ejecutar Tests

```bash
mvn test
```

### Verificar Puertos en Uso

```bash
# macOS/Linux
lsof -i :8081
lsof -i :8082
# etc.

# Windows
netstat -ano | findstr :8081
```

### Limpiar Proyecto Maven

```bash
mvn clean
```

### Generar JAR Ejecutable

```bash
mvn clean package
java -jar target/serviciocursos-0.0.1-SNAPSHOT.jar
```

---

## 🔍 Troubleshooting

### Problema: Puerto ya en uso

**Solución:**
```bash
# Encontrar el proceso
lsof -i :8081

# Matar el proceso (reemplaza PID con el número del proceso)
kill -9 PID
```

### Problema: Error de conexión a MySQL

**Solución:**
1. Verifica que MySQL esté ejecutándose:
   ```bash
   mysql -u root -p
   ```

2. Verifica las credenciales en `application.properties`

3. Asegúrate de que las bases de datos existan:
   ```sql
   SHOW DATABASES;
   ```

### Problema: Tablas no se crean

**Solución:**
1. Verifica que `spring.jpa.hibernate.ddl-auto=create-drop` esté configurado
2. Revisa los logs de la aplicación para errores de SQL
3. Verifica que el usuario de MySQL tenga permisos de creación

### Problema: CORS Error

**Solución:**
Los servicios ya tienen `@CrossOrigin(origins = "*")` configurado. Si persiste:
- Verifica que el frontend esté haciendo peticiones al puerto correcto
- Revisa la configuración del navegador

### Problema: Lombok no funciona

**Solución:**
1. Asegúrate de tener Lombok habilitado en tu IDE
2. En IntelliJ: Settings → Build → Compiler → Annotation Processors → Enable
3. En Eclipse: Instala el plugin de Lombok

---

## 🤝 Contribución

Las contribuciones son bienvenidas. Para contribuir:

1. Crea una rama para tu feature (`git checkout -b feature/AmazingFeature`)
2. Commit tus cambios (`git commit -m 'Add some AmazingFeature'`)
3. Contacta al equipo para revisar los cambios

### Estándares de Código

- Sigue las convenciones de Java
- Usa nombres descriptivos para variables y métodos
- Documenta funciones complejas
- Mantén los tests actualizados

---

## 📝 Notas Adicionales

### Consideraciones de Producción

Antes de desplegar en producción:

1. **Cambiar `ddl-auto`**: De `create-drop` a `update` o `validate`
2. **Configurar credenciales**: Usar variables de entorno para contraseñas
3. **Habilitar logging**: Configurar niveles de log apropiados
4. **Implementar autenticación**: Agregar seguridad a los endpoints
5. **Configurar CORS**: Restringir orígenes permitidos
6. **Monitoreo**: Implementar health checks y métricas
7. **Backup**: Configurar backups automáticos de las bases de datos

### Próximas Mejoras

- [ ] Implementar autenticación y autorización (JWT)
- [ ] Agregar validación de datos con Bean Validation
- [ ] Implementar manejo de excepciones global
- [ ] Agregar documentación con Swagger/OpenAPI
- [ ] Implementar tests unitarios y de integración
- [ ] Agregar logging estructurado
- [ ] Implementar circuit breaker para resiliencia
- [ ] Agregar API Gateway (Spring Cloud Gateway)
- [ ] Implementar service discovery (Eureka)
- [ ] Configurar CI/CD

---


## 👥 Autores

- **Equipo EduTECH** - Desarrollo inicial

---

## 📞 Contacto

Para preguntas o soporte, contacta al equipo de desarrollo.
hawkdurant.jed@gmail.com

---

**Última actualización**: Enero 2024
