# Java Exceptions and Assertions Guide

## Table of Contents
- [1. Exceptions](#1-exceptions)
  - [Types of Exceptions](#types-of-exceptions)
  - [Exception Hierarchy](#exception-hierarchy)
  - [Common Exceptions](#common-exceptions)
- [2. Error Handling](#2-error-handling)
  - [Try-Catch-Finally](#try-catch-finally)
  - [Multiple Catch Blocks](#multiple-catch-blocks)
  - [Try-with-resources](#try-with-resources)
  - [Throwing Exceptions](#throwing-exceptions)
- [3. Assertions](#3-assertions)
  - [Basic Syntax](#basic-syntax)
  - [When to Use Assertions](#when-to-use-assertions)
  - [Best Practices](#best-practices)

## 1. Exceptions

Las excepciones en Java son eventos que ocurren durante la ejecución de un programa que interrumpen el flujo normal de las instrucciones. Proporcionan una forma de manejar errores y condiciones anormales de manera organizada.

### Types of Exceptions

#### Checked Exceptions
- Heredan de `Exception` pero no de `RuntimeException`
- Deben ser declaradas o manejadas explícitamente
- Ejemplo: `IOException`, `SQLException`

```java
public void readFile(String path) throws IOException {
    FileReader file = new FileReader(path);
    BufferedReader reader = new BufferedReader(file);
    String line = reader.readLine();
}
```

#### Unchecked Exceptions
- Heredan de `RuntimeException`
- No requieren declaración explícita
- Ejemplo: `NullPointerException`, `ArrayIndexOutOfBoundsException`

```java
public void divideNumbers(int a, int b) {
    if (b == 0) {
        throw new ArithmeticException("División por cero no permitida");
    }
    int result = a / b;
}
```

### Exception Hierarchy

```
Throwable
├── Error
│   ├── OutOfMemoryError
│   ├── StackOverflowError
│   └── ...
└── Exception
    ├── IOException
    ├── SQLException
    └── RuntimeException
        ├── NullPointerException
        ├── ArithmeticException
        └── ...
```

### Common Exceptions

1. **NullPointerException**
```java
String str = null;
int length = str.length(); // Throws NullPointerException
```

2. **ArrayIndexOutOfBoundsException**
```java
int[] array = new int[3];
array[5] = 10; // Throws ArrayIndexOutOfBoundsException
```

3. **NumberFormatException**
```java
String notANumber = "abc";
int number = Integer.parseInt(notANumber); // Throws NumberFormatException
```

## 2. Error Handling

### Try-Catch-Finally

La estructura básica para el manejo de excepciones:

```java
try {
    // Código que puede lanzar una excepción
    riskyOperation();
} catch (Exception e) {
    // Manejo de la excepción
    System.err.println("Error: " + e.getMessage());
} finally {
    // Este bloque siempre se ejecuta
    cleanup();
}
```

### Multiple Catch Blocks

```java
try {
    File file = new File("example.txt");
    FileReader fr = new FileReader(file);
    // Más código...
} catch (FileNotFoundException e) {
    System.err.println("El archivo no existe");
} catch (IOException e) {
    System.err.println("Error al leer el archivo");
} catch (Exception e) {
    System.err.println("Error general");
}
```

### Try-with-resources

Manejo automático de recursos que implementan `AutoCloseable`:

```java
try (FileReader fr = new FileReader("archivo.txt");
     BufferedReader br = new BufferedReader(fr)) {
    String linea = br.readLine();
    System.out.println(linea);
} catch (IOException e) {
    System.err.println("Error al leer el archivo: " + e.getMessage());
}
```

### Throwing Exceptions

```java
public class BankAccount {
    private double balance;

    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount > balance) {
            throw new InsufficientFundsException("Saldo insuficiente");
        }
        balance -= amount;
    }
}
```

## 3. Assertions

Las aserciones son declaraciones que permiten verificar suposiciones sobre el estado del programa durante el desarrollo y las pruebas.

### Basic Syntax

```java
assert condición;
assert condición : mensajeDeError;
```

Ejemplo práctico:

```java
public class Rectangle {
    private int width;
    private int height;

    public void setDimensions(int width, int height) {
        // Precondiciones
        assert width > 0 : "El ancho debe ser positivo";
        assert height > 0 : "El alto debe ser positivo";
        
        this.width = width;
        this.height = height;
        
        // Postcondiciones
        assert getArea() > 0 : "El área debe ser positiva";
    }

    public int getArea() {
        return width * height;
    }
}
```

### When to Use Assertions

1. **Precondiciones**
```java
public void processArray(int[] array) {
    assert array != null : "El array no puede ser null";
    assert array.length > 0 : "El array no puede estar vacío";
    // Procesar array...
}
```

2. **Postcondiciones**
```java
public int calculateSquareRoot(int number) {
    assert number >= 0 : "El número debe ser positivo";
    int result = (int) Math.sqrt(number);
    assert result * result <= number : "Error en el cálculo";
    return result;
}
```

3. **Invariantes de clase**
```java
public class Stack {
    private int size;
    private Object[] elements;

    private void checkInvariant() {
        assert size >= 0 : "Tamaño no puede ser negativo";
        assert size <= elements.length : "Tamaño no puede exceder la capacidad";
    }
}
```

### Best Practices

1. **Habilitar assertions**
   - En desarrollo: `java -ea MiPrograma`
   - En producción: normalmente deshabilitadas

2. **Cuándo usar assertions**
   - Para verificar el estado interno del programa
   - En pruebas unitarias
   - Para documentar suposiciones
   - En código de desarrollo y pruebas

3. **Cuándo NO usar assertions**
   - Para validar datos de entrada del usuario
   - Para comprobar argumentos de métodos públicos
   - Para código que debe ejecutarse en producción

4. **Guidelines**
   - Las aserciones no deben tener efectos secundarios
   - Usar mensajes de error descriptivos
   - Mantener las aserciones simples y claras
   - No confiar en aserciones para el flujo normal del programa

### Ejemplo Completo

```java
public class BankAccount {
    private double balance;
    private static final double MAX_BALANCE = 1000000.0;

    public void deposit(double amount) {
        // Precondiciones
        assert amount > 0 : "El monto debe ser positivo";
        assert amount <= MAX_BALANCE : "Monto excede el límite permitido";

        double oldBalance = balance;
        balance += amount;

        // Postcondiciones
        assert balance > oldBalance : "El balance debe aumentar después del depósito";
        assert balance <= MAX_BALANCE : "Balance total excede el límite permitido";
    }

    public void withdraw(double amount) {
        // Precondiciones
        assert amount > 0 : "El monto debe ser positivo";
        assert amount <= balance : "Saldo insuficiente";

        double oldBalance = balance;
        balance -= amount;

        // Postcondiciones
        assert balance < oldBalance : "El balance debe disminuir después del retiro";
        assert balance >= 0 : "El balance no puede ser negativo";
    }
}
```