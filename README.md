# ForumHub API

API REST desarrollada con Spring Boot para la gestión de tópicos de discusión dentro de una plataforma comunitaria.

Este proyecto implementa autenticación basada en JWT y operaciones CRUD completas para la administración de tópicos.

---

## Tecnologías Utilizadas

- Java 17+
- Spring Boot
- Spring Security
- JWT (JSON Web Token)
- Spring Data JPA
- MySQL
- Flyway (Migraciones de Base de Datos)
- Maven

---

## Estructura del Proyecto

```
src/main/java/com/forohub
│
├── controller        # Controladores REST
├── domain
│   ├── topico        # Entidad Tópico
│   └── usuario       # Entidad Usuario
├── dto               # Objetos de Transferencia de Datos
├── repository        # Repositorios JPA
├── security          # Configuración de Seguridad y JWT
└── service           # Capa de lógica de negocio
```

---

## Autenticación

La API utiliza autenticación basada en JWT (JSON Web Token).

### Endpoint de Login

```
POST /auth/login
```


**Cuerpo de la peticióny:**
```json
{
  "email": "usuario@email.com",
  "password": "contraseña"
}
```

Respuesta:
```
{
  "token": "jwt_token_generado",
  "type": "Bearer"
}
```

Para acceder a los endpoints protegidos, debes incluir el token en el header:

``` Authorization:  Bearer <your_token> ```

## Endpoints de Tópicos

- Crear Tópico
- POST /topicos
- Listar Todos los Tópicos
- GET /topicos
- Obtener Tópico por ID
- GET /topicos/{id}
- Actualizar Tópico
- PUT /topicos/{id}
- Eliminar Tópico
- DELETE /topicos/{id}
