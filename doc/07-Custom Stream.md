# Creación de Streams Personalizados en Java

Los **Custom Streams** en Java permiten generar flujos de datos personalizados mediante `Stream.generate()`, `Stream.iterate()` o mediante el uso de `Spliterator`. Son útiles cuando se necesita una fuente de datos que no proviene de una colección convencional.

## 1. Uso de `Stream.generate()`
`Stream.generate()` permite crear un flujo infinito de elementos generados dinámicamente.

```java
import java.util.stream.Stream;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Stream.generate(() -> new Random().nextInt(100))
              .limit(10)
              .forEach(System.out::println);
    }
}
```

## 2. Uso de `Stream.iterate()`
`Stream.iterate()` se usa para generar una secuencia infinita basada en una función de iteración.

```java
Stream.iterate(1, n -> n + 2)
      .limit(10)
      .forEach(System.out::println);
```

## 3. Creación de Streams desde `Spliterator`
Un `Spliterator` permite dividir y procesar datos en paralelo de manera eficiente.

```java
import java.util.Spliterator;
import java.util.stream.Stream;

public class CustomSpliterator {
    public static void main(String[] args) {
        Stream<String> stream = Stream.of("A", "B", "C", "D", "E");
        Spliterator<String> spliterator = stream.spliterator();
        
        spliterator.forEachRemaining(System.out::println);
    }
}
```

## 4. Uso de Streams a partir de `BufferedReader`
Podemos convertir un `BufferedReader` en un Stream para leer líneas de un archivo de manera eficiente.

```java
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.stream.Stream;

public class FileStream {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader("archivo.txt"));
        Stream<String> lines = reader.lines();
        lines.forEach(System.out::println);
        reader.close();
    }
}
```

## 5. Uso de `IntStream.range()` para generar secuencias numéricas

```java
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        IntStream.range(1, 10)
                 .forEach(System.out::println);
    }
}
```

## 6. Creación de un Stream con un `Custom Iterator`
Podemos implementar un `Iterator` personalizado y convertirlo en un Stream utilizando `StreamSupport.stream()`.

```java
import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

class FibonacciIterator implements Iterator<Integer> {
    private int previous = 0, current = 1;

    @Override
    public boolean hasNext() {
        return true; // Secuencia infinita
    }

    @Override
    public Integer next() {
        int nextValue = previous + current;
        previous = current;
        current = nextValue;
        return previous;
    }
}

public class CustomIteratorStream {
    public static void main(String[] args) {
        Iterator<Integer> iterator = new FibonacciIterator();
        Stream<Integer> fibonacciStream = StreamSupport.stream(
            Spliterators.spliteratorUnknownSize(iterator, Spliterator.ORDERED), false
        );

        fibonacciStream.limit(10).forEach(System.out::println);
    }
}
```

## Conclusión
Los Streams personalizados permiten manipular datos de manera flexible y eficiente. Dependiendo del caso de uso, `generate()`, `iterate()`, `Spliterator`, `BufferedReader.lines()` o un `Custom Iterator` pueden ser herramientas clave para construir flujos de datos personalizados.

