# Parallel Streams en Java

Los **Parallel Streams** en Java permiten procesar grandes volúmenes de datos en paralelo utilizando múltiples núcleos de la CPU. Se basan en la API de Streams introducida en Java 8 y facilitan la ejecución concurrente de operaciones sobre colecciones de datos.

## 1. ¿Qué es un Parallel Stream?
Un **Parallel Stream** divide los elementos de un flujo de datos en varias partes y las procesa en paralelo en distintos hilos del Fork/Join Pool.

## 2. Creación de Parallel Streams

Se pueden crear Parallel Streams de las siguientes formas:

### Desde una colección
```java
import java.util.List;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        List<Integer> numeros = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        
        // Convertir una lista en un Parallel Stream
        numeros.parallelStream().forEach(n -> System.out.println(n + " - " + Thread.currentThread().getName()));
    }
}
```

### Desde un Stream Secuencial
```java
IntStream.range(1, 10)
    .parallel()
    .forEach(n -> System.out.println(n + " - " + Thread.currentThread().getName()));
```

## 3. Comparación entre Streams secuenciales y paralelos

| Característica | Stream Secuencial | Parallel Stream |
|--------------|----------------|----------------|
| Ejecución | Un solo hilo | Múltiples hilos |
| Rendimiento | Mejor para tareas simples | Mejor para tareas costosas |
| Orden de procesamiento | Mantenido | No garantizado |
| Sincronización | No necesaria | Puede requerir |

## 4. Casos de uso
- Procesamiento de grandes volúmenes de datos.
- Operaciones independientes sin efectos secundarios.
- Cálculos intensivos en CPU.

## 5. Consideraciones y Buenas Prácticas
- **Evitar la modificación de estados compartidos** para evitar condiciones de carrera.
- **No usar Parallel Streams en estructuras sincronizadas** como `Collections.synchronizedList()`.
- **Analizar el rendimiento** antes de usar, ya que overhead de gestión de hilos puede hacer que en datos pequeños sea más lento que un stream secuencial.

## 6. Ejemplo de Reducción con `reduce()`
```java
int suma = IntStream.range(1, 1000)
    .parallel()
    .reduce(0, Integer::sum);
System.out.println("Suma total: " + suma);
```

## Conclusión
Parallel Streams pueden mejorar el rendimiento al aprovechar múltiples núcleos del procesador, pero deben usarse con precaución en función del contexto y el tipo de tarea.

