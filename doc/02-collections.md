# Framework de Colecciones Java

## Introducción
El Framework de Colecciones de Java proporciona una arquitectura unificada para almacenar y manipular grupos de objetos. Este framework fue diseñado para ofrecer interfaces y implementaciones estandarizadas de las estructuras de datos más comunes.

## Interfaces Principales

### 1. List (Lista)
Interface que define una colección ordenada que permite elementos duplicados.

#### Implementaciones principales:
- **ArrayList**
  - Implementación basada en arrays dinámicos
  - Acceso rápido a elementos por índice
  - Ideal para: lectura frecuente y acceso aleatorio
  ```java
  List<String> lista = new ArrayList<>();
  lista.add("Primero");
  lista.add("Segundo");
  ```

- **LinkedList**
  - Implementación basada en lista doblemente enlazada
  - Eficiente para inserciones y eliminaciones
  - Ideal para: modificaciones frecuentes
  ```java
  LinkedList<String> listaEnlazada = new LinkedList<>();
  listaEnlazada.addFirst("Inicio");
  listaEnlazada.addLast("Fin");
  ```

### 2. Set (Conjunto)
Interface que define una colección que no permite elementos duplicados.

#### Implementaciones principales:
- **HashSet**
  - Implementación basada en tabla hash
  - No mantiene orden específico
  - Operaciones más rápidas (O(1))
  ```java
  Set<Integer> conjunto = new HashSet<>();
  conjunto.add(1);
  conjunto.add(2);
  ```

- **TreeSet**
  - Implementación basada en árbol rojo-negro
  - Mantiene elementos ordenados
  - Operaciones más lentas (O(log n))
  ```java
  TreeSet<Integer> conjuntoOrdenado = new TreeSet<>();
  conjuntoOrdenado.add(3);
  conjuntoOrdenado.add(1);
  ```

### 3. Map (Mapa)
Interface para colecciones de pares clave-valor.

#### Implementaciones principales:
- **HashMap**
  - Implementación basada en tabla hash
  - No garantiza orden
  - Operaciones más rápidas
  ```java
  Map<String, Integer> mapa = new HashMap<>();
  mapa.put("Uno", 1);
  mapa.put("Dos", 2);
  ```

- **TreeMap**
  - Implementación basada en árbol rojo-negro
  - Mantiene claves ordenadas
  ```java
  TreeMap<String, Integer> mapaOrdenado = new TreeMap<>();
  mapaOrdenado.put("A", 1);
  mapaOrdenado.put("B", 2);
  ```

## Características Importantes

### Rendimiento
| Colección | Acceso | Inserción | Búsqueda |
|-----------|---------|-----------|-----------|
| ArrayList | O(1)    | O(n)      | O(n)      |
| LinkedList| O(n)    | O(1)      | O(n)      |
| HashSet   | N/A     | O(1)      | O(1)      |
| TreeSet   | N/A     | O(log n)  | O(log n)  |

### Consideraciones de Uso
- **ArrayList**: Usar cuando se necesite acceso rápido por índice
- **LinkedList**: Preferir para muchas inserciones/eliminaciones
- **HashSet**: Mejor opción para conjuntos sin orden específico
- **TreeSet**: Usar cuando se necesite mantener elementos ordenados
- **HashMap**: Opción general para mapas
- **TreeMap**: Cuando se requiera orden en las claves

## Métodos Comunes

### Para todas las colecciones:
```java
add(elemento)        // Añade un elemento
remove(elemento)     // Elimina un elemento
contains(elemento)   // Verifica si existe un elemento
size()              // Obtiene el tamaño
clear()             // Elimina todos los elementos
```

### Específicos para List:
```java
get(index)          // Obtiene elemento por índice
set(index, elemento) // Modifica elemento en índice
```

### Específicos para Map:
```java
put(clave, valor)   // Añade par clave-valor
get(clave)          // Obtiene valor por clave
containsKey(clave)  // Verifica si existe clave
```