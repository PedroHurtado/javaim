# API de Pizzas

## Modelo de Pizza

```json
pizza = {
    id,
    name,
    description,
    price: sum(ingredients.cost) * 1.2,
    url,
    ingredients: [
        { id, name, cost }
    ]
}
```

---

## 1. URLs

- Todos los recursos deben estar pluralizados.
- No deben expresar acciones.
  - ❌ `/pizzas/add`
  - ✅ `/pizzas` (method: POST)
- No deben expresar formatos.
  - ❌ `/pizzas.json`
  - ✅ `/pizzas`
- Content-Type y Accept deben indicar el formato de respuesta.

```http
Content-Type: application/json | application/xml
Accept: application/json | application/xml
```

---

## 2. Versionado

- Se debe usar versionado en la API:
  ```
  /api/v1/pizzas
  ```

---

## 3. Operaciones

### 3.1 Crear Pizza

**Request:**
```http
POST /pizzas
Content-Type: application/json
Accept: application/xml
```

**Body:**
```json
{ ... }
```

**Response:**
```http
201 Created
Content-Type: application/json
```
```json
{ id, ... }
```

**Errores:**
- 400 Bad Request
- 500 Internal Server Error
- 401 No autenticado
- 403 | 404 No autorizado
- 405 Método no permitido
- 415 Formato no permitido

---

### 3.2 Actualizar Pizza

**Request:**
```http
PUT /pizzas/{id} | PATCH /pizzas/{id}
Content-Type: application/json
Accept: application/xml
```

**Body:**
```json
{ ... }
```

**Response:**
```http
200 OK | 204 No Content
Content-Type: application/json
```

**Errores:**
- 400 Bad Request
- 500 Internal Server Error
- 401 No autenticado
- 403 | 404 No autorizado
- 405 Método no permitido
- 415 Formato no permitido
- 404 Not Found

**Diferencias:**
- `PUT`: Modifica una entidad completa.
- `PATCH`: Modifica parte de la entidad.
- **Idempotencia:**
  - Idempotentes: `GET`, `PUT`, `DELETE`
  - No Idempotentes: `POST`, `PATCH`

Ejemplo:
```json
{name: "carbonara", oldName: "CARBONARA"}
```

---

### 3.3 Eliminar Pizza

**Request:**
```http
DELETE /pizzas/{id}
```

**Response:**
```http
204 No Content
```

**Errores:**
- 400 Bad Request
- 500 Internal Server Error
- 401 No autenticado
- 403 | 404 No autorizado
- 405 Método no permitido
- 404 Not Found

---

### 3.4 Obtener Pizza

**Request:**
```http
GET /pizzas/{id}
Accept: application/xml
```

**Response:**
```http
200 OK
Content-Type: application/xml
```
```json
{ id, ... }
```

**Errores:**
- 400 Bad Request
- 500 Internal Server Error
- 401 No autenticado
- 403 | 404 No autorizado
- 405 Método no permitido
- 415 Formato no permitido
- 404 Not Found

---

### 3.5 Obtener Lista de Pizzas

**Request:**
```http
GET /pizzas
Accept: application/xml
```

**Response:**
```http
200 OK
Content-Type: application/json
```
```json
[{ id, ... }] | []
```

**Errores:**
- 400 Bad Request
- 500 Internal Server Error
- 401 No autenticado
- 403 | 404 No autorizado
- 405 Método no permitido
- 415 Formato no permitido

**Consultas:**
```http
GET /pizzas?name=carb&page=1&size=25&fields=id,name,price
```

**Proyecciones:**
- `id`, `name`, `price`

Recursos relacionados:
- [OData](https://www.odata.org/)
- [Apache Olingo](https://olingo.apache.org/)
- [GraphQL](https://graphql.org/) (No es REST)
- [gRPC](https://grpc.io/)

Ejemplo GraphQL Query:
```graphql
{
    project(name: "GraphQL") {
        tagline
    }
}
```

Para modificar con GraphQL, se usa `mutation`.

---

## 4. Rutas Anidadas (Nested Routes)

### 4.1 Agregar Ingredientes a una Pizza

**Request:**
```http
POST /pizzas/{id}/ingredients
```

### 4.2 Eliminar Ingredientes de una Pizza

**Request:**
```http
DELETE /pizzas/{id}/ingredients/{idIngredient}
```

---

## 5. Slug

- `id → pizza-carbonara`

