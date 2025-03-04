# Autenticación

## 1. Métodos de Autenticación
La autenticación es el proceso mediante el cual un usuario demuestra su identidad a un sistema. Existen varios métodos para hacerlo:

### 1.1 User y Password con Bcrypt
El uso de contraseñas tradicionales puede ser mejorado mediante el uso de `bcrypt` para almacenar los passwords de forma segura:

- `bcrypt` genera un hash de la contraseña con un *salt* aleatorio, dificultando ataques de fuerza bruta.
- Evita almacenar contraseñas en texto plano.

### 1.2 OAuth (Identity Provider - IDP)
OAuth es un protocolo estándar que permite delegar la autenticación en un proveedor de identidad externo (IDP), como:

- Google
- Twitter
- Facebook
- LinkedIn
- Microsoft

OAuth reduce la necesidad de manejar credenciales directamente en nuestra aplicación.

## 2. Cookies y Tokens
Los sistemas de autenticación suelen utilizar cookies o tokens para gestionar sesiones.

### 2.1 Cookies
Las cookies son pequeños fragmentos de información almacenados en el navegador y enviados automáticamente en cada petición.

Ejemplo de cookie segura:
```http
Set-Cookie: sessionId=1234567890; HttpOnly; Secure;
```

- `HttpOnly`: Evita que JavaScript acceda a la cookie (protección contra XSS).
- `Secure`: Solo se envía a través de HTTPS.

Cuando un usuario realiza una petición, la cookie se envía automáticamente:
```http
GET /perfil HTTP/1.1
Cookie: sessionId=1234567890
```

### 2.2 Tokens
Los tokens se pueden enviar en las cabeceras de las peticiones:
```http
Authorization: Bearer 1323456666
```

#### ¿Dónde guardar el token?
Se pueden almacenar en diferentes lugares, cada uno con sus ventajas y desventajas:

#### LocalStorage
- Requiere serialización y deserialización.
- Los accesos son síncronos.
- No es accesible desde los Web Workers (PWA).

#### IndexDB
- Soporta serialización/deserialización nativa.
- Accesos asíncronos.
- Funciona con Web Workers.

#### Protección contra XSS
Para mitigar ataques XSS, se recomienda utilizar Content Security Policy (CSP):

[Referencia CSP3](https://www.w3.org/TR/CSP3/)

## 3. OAuth - Flujo de Autenticación

1. **Registro en el IDP**
   - Se obtiene un `client_id` y `client_secret`.
   - Se define un `redirect_uri` para recibir el token de autenticación.

2. **Formulario de autenticación**
   ```html
   <button type="submit">Login con Google</button>
   ```

   El usuario es redirigido al IDP:
   ```http
   GET https://github.com/login/oauth/authorize?client_id=xxxx&redirect_uri=xxxx&scope=read
   ```

3. **Redirección con código de autorización**
   ```http
   HTTP/1.1 302 Found
   Location: http://localhost:8080/login/github?code=45679899999999
   ```

4. **Intercambio del código por un token de acceso**
   ```http
   POST https://github.com/login/oauth/access_token
   Content-Type: application/json
   {
     "code": "45679899999999",
     "client_id": "xxxx",
     "client_secret": "xxxx",
     "redirect_uri": "http://localhost:8080/login"
   }
   ```

   Respuesta:
   ```json
   {
     "access_token": "gho_16C7e42F292c6912E7710c838347Ae178B4a",
     "scope": "repo,gist",
     "token_type": "bearer"
   }
   ```

5. **Uso del token para obtener información del usuario**
   ```http
   Authorization: Bearer OAUTH-TOKEN
   GET https://api.github.com/user
   ```

6. **Almacenamiento y emisión de credenciales internas**
   - Guardar la información del usuario en la base de datos.
   - Generar un nuevo `JWT` o establecer una cookie para gestionar la sesión.

## 4. Seguridad en el uso de Tokens

- **Tiempo de vida corto**: Se recomienda que el token expire en 3600 segundos (1 hora).
- **Endpoint de refresco**: Implementar un mecanismo de renovación del token sin necesidad de que el usuario vuelva a autenticarse.

## 5. JWT - JSON Web Token
Un JWT está compuesto por tres partes codificadas en Base64:
```js
const parts = 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiYWRtaW4iOnRydWUsImlhdCI6MTUxNjIzOTAyMn0.KMUFsIDTnFmyG3nMiGM6H9FNFUROf3wh7SmqJp-QV30'.split('.');

const header = atob(parts[0]);
const claims = atob(parts[1]);

atob(parts[2]); // Error (firma inválida si no se verifica correctamente)
```

### Estructura del JWT
Un JWT está compuesto de:
1. **Header**: Contiene información sobre el tipo de token y el algoritmo de firma.
2. **Payload (Claims)**: Contiene información sobre el usuario y otros datos relevantes.
3. **Signature**: Se genera utilizando una clave secreta para verificar la autenticidad del token.

Ejemplo de un **Header**:
```json
{
  "alg": "HS256",
  "typ": "JWT"
}
```

Ejemplo de un **Payload**:
```json
{
  "sub": "1234567890",
  "name": "John Doe",
  "admin": true,
  "iat": 1516239022
}
```

Ejemplo de **firma**:
```js
HMACSHA256(base64UrlEncode(header) + "." + base64UrlEncode(payload), secret)
```

Para validar un JWT, el servidor debe verificar la firma y comprobar que el `iat` (issued at) y `exp` (expiration) sigan siendo válidos.

---

Con estos conceptos, se tiene una base sólida sobre autenticación, almacenamiento de credenciales y seguridad en aplicaciones web modernas.

