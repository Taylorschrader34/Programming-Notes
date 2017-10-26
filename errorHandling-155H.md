
# Error Handling

* Reconsider the quadratic roots program...

* Errors can either be completely unexpected OR they can be anticipated but not normal
* We could take one of three strategies:
    1. Defensive Programming: we can check for illegal or invalid operations before doing them, then if it would result in an error, we can "choose" not to do them (without communicating: it is failing silently, no-op = no operation)
    2. We can then communicate errors back to the calling function using either integer error codes or enumerated types
    3. We can use exceptions and exception handling instead (Java, C does not support exceptions)
* IN C, we do a combination of 1 and 2: we check for potential errors inside a function, return an appropriae error code to communicate back to teh calling function what type of error occurred it is then the responsibiity of the calling function to decide how to *handle* the error
* In C there are already many preexisting error codes
    * In the POSIX standard, there are only 3:
      * `EDOM`: error in the domain of a function `sqrt(-1)`
      * `ERANGE` error in the range of a function `log(0)`
      * `EILSEQ` illegal byte sequence
* These and many more are defined in `errno.h`, they are all defined using `#define` macros
* They use "human readable" rather than "magic numbers"

## Enumerated Types

* Many pieces of data may have a limited number of possible values
* Example: days of the week, months, error codes, etc.
* In C, you can define an *enumerated type* and give it pre-defined human readable values
* Syntax: `typedef` (type definition) and `enum` (short for enumeration)
* Opening/closing curly brackets after which we give a comma delimited list of values
* Modern convention: to use `UpperCamelCasing` to name it
* Inside, each value is `UPPER_UNDERSCORE_CASING`

```c
typedef enum {
  SUNDAY,
  MONDAY,
  TUESDAY,
  WEDNESDAY,
  THURSDAY,
  FRIDAY,
  SATURDAY,
} DayOfWeek;
```

* Later in the program, you can use your enumerated type like any other variable

```c
DayOfWeek today;
today = TUESDAY;

if(today == FRIDAY) {
  printf("have a good weekend\n");
}
```

* The way that C accomplishes enumerated types is by assigning and treating them as integers, by default they start at 0 (`SUNDAY` is the same as 0, `SATURDAY` is 6)
* You can get into trouble if *you* treat them like integers

```c
DayOfWeek today = SATURDAY;
DayOfWeek tomorrow = today + 1;
DayOfWeek when = 12345;
```

* By convention, all enumerated type definitions are placed into a header file
* Demonstration

## Error Handling in Java: Exceptions

* Instead of defensive programming, Java uses *exceptions*
* An *Exception* is an interuption of the normal linear flow of control
* Philosophy: we will go ahead and leap before we look because an exception will `catch` us as we fall
* Advantages of Exceptions over defensive programming:
    * There is no *semantic* meaning to error codes, exceptions *do* have semantic meaning: there is a fundamental difference between `NullPointerException` and a `InputMismatchExcpetion`, these differences are *recognizable* by the programming language
    * Often, defensive programming leads to large, nested and separte error handling code
    * Defensive programming is unable to handle event-based errors


### Exceptions in Java

* In Java, all exceptions are subclasses of the `Throwable` objects
* There are two main types of `Throwable`s:
    * `Error`: mainly used by the JVM and is always fatal: JVM memory error, you the programmer do not generally use or catch `Error`
    * `Exception` is what you do use; there are two main types of `Exception`s:
        * `Exception` ("checked" exception): you are *forced* to try-catch them
        * `RuntimeException` ("unchecked" exception): you are not forced to try-catch them

### Syntax

* In general, error handling is done in a `try-catch` block
* There may be multiple catch blocks, but at most one will execute
* Therefore, the order that you write your catch blocks matters just like the order matters in an `if-else-if` statement

```java
try {
  //potentially dangerous code inside
  //maybe a FileNotFoundException or something is thrown here
} catch(FileNotFoundException fnfe) {
  //handle the exception in this catch block
} catch(NullPointerException npe) {
  //handle a null pointer differently
} catch(Exception e) {
  //optionally choose to catch any and all other exceptions here
}

```

* You can also explicitly throw your own custom exceptions


```java
//we want to divide by a, but not if it is zero
if(a == 0) {
  throw new RuntimeException("You cannot divide by zero");
}
```

### Best Practice

* How to deal with checked exceptions that must be caught and handled
* Catch and release: we will catch the exception and rethrow it as a `RuntimeException`

```java
try {
  Scanner s = new Scanner(new File("bunnies.txt"));
} catch(FileNotFoundException fnfe) {
  throw new RuntimeException(fnfe);
}
```

### Finally Blocks

* A `try-catch` block may be followed by a `finally` block that is typcially used to clean up/release resources at the end
* A `finally` lbock is executed regardless of whether or not an excpetion occurred
* Java 7 introduced a new syntax "try-with-resources"

```java
Scanner s1, s2;
try {
  s1 = new Scanner(new File("thisfileexists.txt")):
  s2 = new Scanner(new File("bunnies.txt"));
} catch(FileNotFoundException fnfe) {
  throw new RuntimeException(fnfe);
} finally {
  s1.close();
  s2.close();
}
```

### Creating your own excpetions

* YOu need to create a new class for each custom exception
* IN general, if a class already exists, use it
* You `extends RuntimeException`




```text


















```














