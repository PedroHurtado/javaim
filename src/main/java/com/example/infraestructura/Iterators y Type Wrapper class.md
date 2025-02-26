# Iterators y Type-Wrapper Classes en Java

## 2. Iterators

En Java, un **Iterator** es una interfaz que permite recorrer colecciones de manera segura y uniforme sin exponer su implementación interna. Se encuentra en el paquete `java.util` y es útil para iterar sobre estructuras de datos como `ArrayList`, `HashSet`, `LinkedList`, etc.

### Métodos principales de `Iterator<E>`

```java
boolean hasNext();  // Verifica si hay más elementos
E next();           // Retorna el siguiente elemento
void remove();      // Elimina el elemento actual
```

### Ejemplo de uso de `Iterator`

```java
import java.util.ArrayList;
import java.util.Iterator;

public class IteratorExample {
    public static void main(String[] args) {
        ArrayList<String> nombres = new ArrayList<>();
        nombres.add("Juan");
        nombres.add("María");
        nombres.add("Carlos");
        
        Iterator<String> iterator = nombres.iterator();
        
        while (iterator.hasNext()) {
            String nombre = iterator.next();
            System.out.println(nombre);
            
            // Ejemplo de eliminación de un elemento durante la iteración
            if (nombre.equals("María")) {
                iterator.remove();
            }
        }
        System.out.println("Lista después de la eliminación: " + nombres);
    }
}
```

### Uso de `ListIterator`

`ListIterator` es una extensión de `Iterator` que permite recorrer listas en ambas direcciones.

```java
import java.util.LinkedList;
import java.util.ListIterator;

public class ListIteratorExample {
    public static void main(String[] args) {
        LinkedList<Integer> numeros = new LinkedList<>();
        numeros.add(10);
        numeros.add(20);
        numeros.add(30);
        
        ListIterator<Integer> listIterator = numeros.listIterator();
        
        System.out.println("Recorrido hacia adelante:");
        while (listIterator.hasNext()) {
            System.out.println(listIterator.next());
        }
        
        System.out.println("Recorrido hacia atrás:");
        while (listIterator.hasPrevious()) {
            System.out.println(listIterator.previous());
        }
    }
}
```

### Ejemplo de un `Custom Iterator`

```java
import java.util.Iterator;

class CustomCollection implements Iterable<Integer> {
    private final int[] data;
    
    public CustomCollection(int[] data) {
        this.data = data;
    }
    
    @Override
    public Iterator<Integer> iterator() {
        return new CustomIterator();
    }
    
    private class CustomIterator implements Iterator<Integer> {
        private int index = 0;
        
        @Override
        public boolean hasNext() {
            return index < data.length;
        }
        
        @Override
        public Integer next() {
            return data[index++];
        }
    }
}

public class CustomIteratorExample {
    public static void main(String[] args) {
        int[] valores = {1, 2, 3, 4, 5};
        CustomCollection collection = new CustomCollection(valores);
        
        for (int val : collection) {
            System.out.println(val);
        }
    }
}
```

## 3. Type-Wrapper Classes

Las **Type-Wrapper Classes** en Java proporcionan una representación de los tipos primitivos (`int`, `double`, `char`, etc.) como objetos. Estas clases permiten utilizar valores primitivos en estructuras de datos genéricas y ofrecen métodos útiles para manipulación de datos.

### Principales clases wrapper

| Tipo Primitivo | Clase Wrapper |
|---------------|--------------|
| `byte`        | `Byte`       |
| `short`       | `Short`      |
| `int`         | `Integer`    |
| `long`        | `Long`       |
| `float`       | `Float`      |
| `double`      | `Double`     |
| `char`        | `Character`  |
| `boolean`     | `Boolean`    |

### Ejemplo de uso de clases Wrapper

```java
public class WrapperExample {
    public static void main(String[] args) {
        // Conversión de primitivo a Wrapper (Boxing)
        Integer numero = Integer.valueOf(100);
        Double decimal = Double.valueOf(10.5);

        // Conversión de Wrapper a primitivo (Unboxing)
        int num = numero.intValue();
        double dec = decimal.doubleValue();
        
        System.out.println("Valor entero: " + num);
        System.out.println("Valor decimal: " + dec);
    }
}
```

### Autoboxing y Unboxing

Desde Java 5, el **Autoboxing** y **Unboxing** permiten la conversión automática entre tipos primitivos y sus correspondientes clases Wrapper.

```java
public class AutoBoxingExample {
    public static void main(String[] args) {
        Integer num = 50; // Autoboxing: int -> Integer
        int valor = num;  // Unboxing: Integer -> int
        
        System.out.println("Autoboxing: " + num);
        System.out.println("Unboxing: " + valor);
    }
}
```

### Métodos útiles de las clases Wrapper

Las clases Wrapper incluyen métodos para conversión y manipulación de datos:

```java
public class WrapperMethods {
    public static void main(String[] args) {
        // Conversión de String a número
        int numero = Integer.parseInt("123");
        double decimal = Double.parseDouble("45.67");
        
        // Conversión de número a String
        String numStr = Integer.toString(456);
        
        System.out.println("Número entero: " + numero);
        System.out.println("Número decimal: " + decimal);
        System.out.println("Número como String: " + numStr);
    }
}
```

## Conclusión

- **Iterators** permiten recorrer colecciones de manera segura y eliminar elementos durante la iteración.
- **ListIterator** permite recorrer listas en ambas direcciones.
- **Custom Iterators** permiten definir iteradores personalizados para estructuras de datos propias.
- **Clases Wrapper** permiten trabajar con tipos primitivos como objetos y ofrecen métodos útiles.
- **Autoboxing y Unboxing** facilitan la conversión automática entre primitivos y clases Wrapper.

