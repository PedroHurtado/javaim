# CORS (Cross-Origin Resource Sharing)

## Introducción

CORS es un mecanismo de seguridad implementado por los navegadores web para controlar las solicitudes HTTP realizadas desde un dominio a otro. Esto es crucial para proteger a los usuarios de posibles ataques maliciosos.

En tu ejemplo, tienes:

* **Servidor de la aplicación web:** `http://localhost:8080`
* **Servidor de la API:** `http://localhost:8081`

Cuando tu aplicación web en `http://localhost:8080` intenta hacer una solicitud (por ejemplo, usando `XMLHttpRequest` o `fetch`) a `http://localhost:8081`, el navegador verifica si el servidor de la API permite esta solicitud.

## El Problema: Solicitudes Cross-Origin

Por defecto, los navegadores web aplican la "Same-Origin Policy". Esto significa que una página web solo puede hacer solicitudes a recursos que tengan el mismo origen (protocolo, dominio y puerto).

En tu caso, `http://localhost:8080` y `http://localhost:8081` tienen diferentes puertos, por lo que se consideran orígenes diferentes.

## Cómo Funciona CORS

1.  **Solicitud del Navegador (Request):**
    * Cuando el navegador realiza una solicitud cross-origin, añade un encabezado `Origin` a la solicitud. Este encabezado indica el origen de la página web que realiza la solicitud.
        * Ejemplo: `Origin: http://localhost:8080`

2.  **Respuesta del Servidor (Response):**
    * El servidor de la API responde con encabezados específicos para controlar el acceso cross-origin.
    * **`Access-Control-Allow-Origin`:** Este encabezado especifica qué orígenes están permitidos para acceder al recurso.
        * `*`: Permite el acceso desde cualquier origen. **Importante:** Si se usa `*`, no se pueden enviar credenciales (cookies, encabezados de autenticación).
        * `http://localhost:8080`: Permite el acceso solo desde `http://localhost:8080`. Esto permite el uso de credenciales.
    * **`Access-Control-Allow-Credentials`:** Este encabezado indica si el navegador debe incluir credenciales (cookies, encabezados de autenticación) en la solicitud.
        * `true`: Permite el envío de credenciales.
    * **`Access-Control-Max-Age`:** Este encabezado especifica cuánto tiempo el navegador puede almacenar en caché la respuesta de la solicitud preflight (ver más abajo).
        * Ejemplo: `Access-Control-Max-Age: 3600` (1 hora).

## Solicitudes Preflight (Preflight Requests)

* Para ciertas solicitudes HTTP (como `PUT`, `DELETE`, `PATCH`, o `POST` con tipos de contenido distintos a `application/x-www-form-urlencoded`, `multipart/form-data`, o `text/plain`), el navegador realiza una solicitud "preflight" antes de la solicitud real.
* La solicitud preflight es una solicitud `OPTIONS` que el navegador envía al servidor de la API para verificar si la solicitud real está permitida.
* El servidor de la API responde a la solicitud `OPTIONS` con encabezados `Access-Control-Allow-*` para indicar qué métodos HTTP, encabezados y credenciales están permitidos.
* Si la respuesta preflight es exitosa, el navegador envía la solicitud HTTP real.
* [Documentación de Preflight Requests](https://developer.mozilla.org/en-US/docs/Glossary/Preflight_request)

## Métodos HTTP y CORS

* **Métodos Simples (GET, HEAD, POST con ciertos tipos de contenido):** Estas solicitudes no requieren una solicitud preflight.
* **Métodos Complejos (PUT, DELETE, PATCH, POST con otros tipos de contenido):** Estas solicitudes requieren una solicitud preflight.
* **OPTIONS:** Se utiliza para la solicitud preflight.

## Ejemplo de Flujo

1.  Navegador en `http://localhost:8080` realiza una solicitud `POST` a `http://localhost:8081`.
2.  Navegador envía una solicitud `OPTIONS` (preflight) a `http://localhost:8081`.
3.  Servidor en `http://localhost:8081` responde con encabezados `Access-Control-Allow-*`.
4.  Si la respuesta preflight es exitosa, el navegador envía la solicitud `POST` real.
5.  Servidor en `http://localhost:8081` responde a la solicitud `POST`.
6.  Navegador procesa la respuesta.

## Consideraciones de Seguridad

* Usar `Access-Control-Allow-Origin: *` puede ser conveniente, pero reduce la seguridad. Es mejor especificar los orígenes permitidos.
* Si necesitas enviar credenciales, asegúrate de que `Access-Control-Allow-Credentials: true` esté configurado y que `Access-Control-Allow-Origin` no sea `*`.
* Siempre valida y sanitiza las entradas del usuario en el servidor para prevenir ataques.

## Tecnologías para Realizar Solicitudes

* **XMLHttpRequest:** Una API tradicional para realizar solicitudes HTTP desde el navegador.
* **Fetch API:** Una API más moderna y basada en promesas para realizar solicitudes HTTP.