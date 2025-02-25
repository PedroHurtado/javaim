# Generics en Java

## Introducción
Los **genéricos** en Java permiten escribir código reutilizable y seguro en tiempo de compilación. Se utilizan para trabajar con tipos de datos de manera flexible sin comprometer la seguridad del tipo.

## Conceptos Clave
1. **`<T>` Genéricos básicos** - Definir clases y métodos genéricos.
2. **`? extends T`** - Límite superior para restringir a subtipos de `T`.
3. **`? super T`** - Límite inferior para restringir a supertipos de `T`.
4. **Uso práctico en colecciones** - Manipulación de listas con genéricos.

## Implementación en Java

### 1. Clase Genérica Básica
```java
class Box<T> {
    private T value;

    public Box(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
```

### 2. Jerarquía de Clases
```java
class Animal {
    public void speak() {
        System.out.println("Animal speaking...");
    }
}

class Dog extends Animal {
    @Override
    public void speak() {
        System.out.println("Woof! Woof!");
    }
}

class Labrador extends Dog {
    @Override
    public void speak() {
        System.out.println("I am a Labrador! Woof!");
    }
}
```

### 3. Uso de `? extends T` (Solo Lectura)
```java
public static void printAnimals(List<? extends Animal> animals) {
    for (Animal a : animals) {
        a.speak();
    }
    // animals.add(new Dog()); // ❌ ERROR: No se puede agregar elementos
}
```

### 4. Uso de `? super T` (Solo Escritura)
```java
public static void addDogs(List<? super Dog> animals) {
    animals.add(new Dog()); // ✅ Se puede agregar Dog
    animals.add(new Labrador()); // ✅ Se puede agregar Labrador (subtipo de Dog)
    // Dog dog = animals.get(0); // ❌ ERROR: Solo se puede leer como Object
    Object obj = animals.get(0); // ✅ Lectura permitida como Object
}
```

### 5. Ejemplo Completo en `main`
```java
public static void main(String[] args) {
    List<Dog> dogs = new ArrayList<>();
    dogs.add(new Dog());
    printAnimals(dogs); // ✅ Funciona porque Dog extiende Animal

    List<Animal> animals = new ArrayList<>();
    addDogs(animals); // ✅ Funciona porque Animal es super de Dog
}
```

## Resumen
| Caso de Uso       | Restricción  | Lectura | Escritura |
|------------------|--------------|---------|-----------|
| `? extends T`   | Solo subtipos de `T` | ✅ Sí | ❌ No |
| `? super T`     | Solo supertipos de `T` | ❌ Solo como `Object` | ✅ Sí |

### 📌 Regla Práctica
- Usa **`? extends T`** cuando solo necesites **leer** datos de la lista.
- Usa **`? super T`** cuando necesites **escribir** datos en la lista.

Los genéricos en Java permiten flexibilidad y seguridad en estructuras de datos, facilitando la reutilización del código. 🚀