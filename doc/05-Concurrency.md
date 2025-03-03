# Concurrencia en Java y Futures

La **concurrencia** en Java permite la ejecución de múltiples tareas al mismo tiempo, mejorando el rendimiento y la capacidad de respuesta de las aplicaciones. Java proporciona múltiples herramientas para gestionar la concurrencia, incluyendo `Threads`, `Executors`, `Futures` y `CompletableFuture`.

## 1. Introducción a la Concurrencia

La concurrencia en Java se maneja principalmente a través de:
- **Threads**: Creación y gestión de hilos manualmente.
- **Executors**: Un mecanismo de alto nivel para manejar hilos de manera eficiente.
- **Futures**: Representan el resultado de una operación asincrónica.
- **CompletableFuture**: Una extensión de `Future` con capacidades funcionales y composición de tareas.

## 2. Uso de Threads

```java
class MiHilo extends Thread {
    public void run() {
        System.out.println("Hilo en ejecución: " + Thread.currentThread().getName());
    }
}

public class Main {
    public static void main(String[] args) {
        MiHilo hilo = new MiHilo();
        hilo.start();
    }
}
```

## 3. Uso de Executors

Los **Executors** administran un grupo de hilos y permiten la ejecución de tareas concurrentes de manera eficiente.

```java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorExample {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        
        for (int i = 0; i < 5; i++) {
            executor.submit(() -> System.out.println("Ejecutando: " + Thread.currentThread().getName()));
        }
        
        executor.shutdown();
    }
}
```

## 4. Futures en Java

Un **Future** es un objeto que representa un resultado pendiente de una tarea asincrónica.

### Uso de Future con ExecutorService
```java
import java.util.concurrent.*;

public class FutureExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        
        Future<Integer> future = executor.submit(() -> {
            Thread.sleep(2000);
            return 42;
        });
        
        System.out.println("Esperando resultado...");
        System.out.println("Resultado: " + future.get()); // Bloquea hasta que el resultado esté listo
        
        executor.shutdown();
    }
}
```

## 5. Uso de CompletableFuture

`CompletableFuture` extiende `Future` y permite la programación funcional con métodos como `thenApply`, `thenAccept` y `thenCompose`.

```java
import java.util.concurrent.*;

public class CompletableFutureExample {
    public static void main(String[] args) {
        CompletableFuture.supplyAsync(() -> {
            try { Thread.sleep(2000); } catch (InterruptedException e) {}
            return "Hola, mundo!";
        }).thenAccept(System.out::println);
        
        System.out.println("Tarea iniciada, esperando resultado...");
    }
}
```

## 6. Comparación entre Future y CompletableFuture

| Característica       | Future        | CompletableFuture |
|----------------------|--------------|------------------|
| Bloqueante          | Sí (`get()`)  | No (`thenApply()`) |
| Encadenamiento      | No           | Sí |
| Manejo de errores  | Excepciones   | `exceptionally()` |

## Conclusión
La concurrencia en Java es clave para mejorar el rendimiento de las aplicaciones. `Future` y `CompletableFuture` permiten manejar tareas asincrónicas, con `CompletableFuture` ofreciendo un enfoque más moderno y flexible.

