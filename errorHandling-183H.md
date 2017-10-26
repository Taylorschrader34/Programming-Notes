
# Error Handling

* Reconsider the quadratic roots program...

* Errors can either be completely unexpected *or* they can be anticipated but not normal
* Error handling can be done by different approaches:
    1. Defensive programming: we can check for illegal or invaid operations before doing them, if it would result in an error, we can "choose" not to do them (if done alone, called "failing silently"
    2. We could then communicate errors back to the calling function
    3. We could use exceptions and exception handling instead
* C usually uses the first two strategies, Java uses the third
* IN C you write functions that check for error conditions and communicate *error codes* back to the user, either be an integer or it could be a macro or it could be an *enumerated type
* In the standard C libaries, there is an `errno.h` library
    * It establishes a global variable (and `int`) that stores the current error condition
    * The C standard defines three required error codes:
        * `EDOM` when there is an invalid input example: `sqrt(-1)`
        * `ERANGE` when there is an invalid output example: `log(0)`
        * `EILSEQ` illegal byte sequence
    * The extended POSIX standard defines 130 some other errors
* Usually you use error "terms" instead of raw error numbers, which can be done using macros `#define` or enumerated types

## Enumerated Types in C

* Many pieces of data may have a limited number of possible values
* Example: days of the week, months in a year, error codes
* In C you can define an enumerated type and give it pre-defined human readable values
* Syntax: `typedef` (type definition) and `enum` (short for enumeration)
* Opening/closing curly brackets and givec it a comma delimited list of values
* At the end you give your enumerated type a name
* Modern Convention: name should be `UpperCamelCase`, values should be `UPPER_UNDERSCORE_CASE`

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

* Typcially enumerations are declared in a relevant header file
* After declaration, you can use it like any other variable type


```c
DayOfWeek today;
today = FRIDAY;

if(today == SATURDAY) {
  printf("kickoff!\n");
}
```

* C actually uses integers to represent enumerated values
* `SUNDAY` has the value 0, `MONDAY` has the value 1, etc.
* As a consequence, you *can* do "invalid" operations

```c
DayOfWeek today = SATURDAY;
DayOfWeek tomorrow = today + 1;

DayOfWeek someday = 12345;
```

* Demonstration: integrate an error code enumeration in our library

## Error Handling in Java: Exceptions

* Instead of defensive programming, java uses *exceptions*
* An *Exception* is an interruption of the normal linear flow of control
* Philosophy of defensive programming is: look before you leap
* Philosophy of exception handling: go ahead and leap, we'll `catch` you if you fall
* Defensive programming vs exception handling:
    * With error codes, there is no *semantic* meaning, exceptions *do* have semantic meaning, for example: `ArrayIndexOutOfBounds` is not the same thing as a `NullPointerException`
    * Often, defensive programming leads to large, nested and separate error handling code: mac's goto fail bug
    * Defensive programming is unable to handle event-based errors

## Exceptions in Java

* In Java, all exceptions are subclasses of the `Throwable` class
* There are two main types of `Throwables`:
    * `Error` almost exclusively used by the JVM, don't worry about them, always fatal
    * `Exception` is what you generally use, further there are two main types of exceptions:
        * `Exception` ("checked" exception): you are forced to surround your code in a `try-catch` block
        * `RuntimeException` ("unchecked" exception): you may or may not surround your code with a `try-catch` block

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

### Best Practice: how to deal with checked exceptions

* Checked excpetions: catch and release

```java
		File f = new File("kitties.txt");
		try {
			Scanner s = new Scanner(f);
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
```

### Throwing Exceptions

```java
//not want to divide by zero:
if(a == 0) {
  throw new RuntimeException("You cannot divide by zero");
}
```

### Finally Blocks

* A `try-catch` block may be followed optionally by a `finally` block that is typcially used to clean up or close resources
* A `finally` block is executed *regardless* of whether an exception occurred or not
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

### Aside: Java also has enumerated types

* Enumerated types in Java are full classes, so they have methods as well as state
* Example: create an enumerated type for days of the week

```text


















```














