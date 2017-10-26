
# Functions & Methods

* A *function* is a reusable unit of code that may take *input(s)* and may produce an *output*
* Already familiar with functions: `main(), printf(), sqrt()`, etc.
* Functions facilitate code reuse, you don't have to cut-n-paste the same block of code over and over
* Procedural abstraction: functions allow us to ignore the minute details of how a certain block of code or algorithm works
* Functions *encapsulate* functionality into reusable, abstract code blocks
* Standard libraries and functions have a lot of testing, debugging behind them and have been optimized and are efficient
* The first question you should ask in problems solving is "is this problem already solved?" that is, does a function already exist to solve my problem?

## Functions in C

* As with variables, functions must be *declared* before they can be used
* In C, we "declare" a function using a *prototype* which specifies the function's *signature* which includes:
    * the name of the function (its "identifier")
    * a list of its parameters (arguments): both the number of parameters and each one's type
    * the return type: the type of data the function returns

```c
/**
 *  This function computes the Euclidean distance between
 *  the two points defined by (x1, y1) and (x2, y2)
 */
double euclideanDistance(double x1, double y1,
                         double x2, double y2);
```

* Syntax notes: a prototype ends with a semicolon and has no function body
* Later in your code you provide a function *definition* which has the same signature and provides a function body surrounded by curly brackets

```c
double euclideanDistance(double x1, double y1,
                         double x2, double y2) {

  return sqrt( pow(x1 - x2, 2) + pow(y1 - y2, 2) );

}
```

* Every function should be documented with doc-style comments
* In C, comments are attached to the prototype, *not* the definition: DRY = Don't Repeat Yourself
* In the original version we had several *local variables* `temp, result` these variables were declared inside the function and so only exist within the function, their *scope* was local to the function
* After the function is done executing, they no longer exist and are *out of scope*
* In general, you can declare as many local variables as you want
* In addition, the parameters are treated as local variables

### Passing by value

Consider a function that swaps variable values


```c
void swap(int a, int b) {
  int temp = a;
  a = b;
  b = temp;
  return;
}
```

* Passing by value means that the value of variable is copied into the new parameter variables
* This is because the variables that are passed to the function `swap` are *passed by value*
* The values stored in the variables at the time you call the function are *copied* onto the call stack, the function knows nothing about the original variables, only the copies stored in `a, b` are swapped

### Modularity

* Programs can quickly become very complex with dozens or hundreds or thousands of different functions
* In C we organize code into different files such that files contain related functionality and functions
* Prototypes are generally placed into *header files* (`.h` files)
* Definitions are paced into *source files* of the same name but extension `.c`
* Once in their own header files, you can "include" them similar to language header files; difference: `#include "mylibrary.h"`

Demonstration: quadratic roots

1. Separate prototypes into their own header file `roots.h`
2. Separate definitions into their own source file `roots.c` (name matches)
3. You use `#include "roots.h"` in any file that needs the function prototypes
4. Compile seprate modules:
    Compile the "library" file: `gcc -c roots.c` (produces an object file `roots.o`
    Compile the entire program together with the library files:
    `gcc roots.o computeRoots.c`


## Functions in Java

* Functions in Java are usually referred to as "methods"
* There are no prototypes, instead, methods are placed inside of classes and the signature on the function body/definition is used
* In Java, everything is a class or belongs to a class, therefore methods must belong to a class

```java
public class RootUtils {

  public static double getRoot01(double a, double b, double c) {
    return (-b + Math.sqrt(b*b - 4*a*c) ) / (2*a);
  }

  public static double getRoot02(double a, double b, double c) {
    return (-b - Math.sqrt(b*b - 4*a*c) ) / (2*a);
  }

}
```

Observe the differences:
* Each method belongs to the class, `RootUtils`
* The `public` keyword makes the method available to any piece of code
    * `private` would make it so that only the class can "see" the method
    * `protected` would make it so that only the class and its subclasses can "see" the method
    * The lack of a visibility modifier means it is "package protected": classes in the same package can "see" the method
* The `static` keyword means that the method "belongs" to the class and not to instances of the class
    * Consequence: to invoke the method you use the class name + `.` + the method name
    `RootUtils.getRoot01(...)`
    * For now, you can make all of your methods `public static`
* Java is also pass by value

## Other Issues

* New keyword: `void`
    * A function that does not return a value is a `void` function
    * A function that takes no parameters may also use the `void` keyword

```c
void swap(int a, int b); //no output
int foo(void); //no input
//though it is better to omit void for no input, so:
int foo(); //better

```

* Java:

```java
public class Foo {

  public static void swap(int a, int b) {
    //...
  }

  public static int foo() {
    //...
  }
}
```

### Function Overloading

* How do you compute the absoulte value in C?  Or in Java?
* C does *not* support function overloading: the ability to define functions with the same name, but different number of different types of parameters
* Consequence: `abs(int), fabs(double), labs(long)`: one function with a distinct name for each type of variable
* Contrast: Java *does* support function overloading: there are four versions of the `abs()` method all with the same name

## Functions in Detail

* Recall the swap code from before

### How functions actually work

* Programs have what is called a program *stack* (LIFO = Last-In First-Out data structure)
* At the start of a program, a program *call* stack is created
* At the bottom, the program code is added
* Then global variables and static content are added to the top of the stack
* Then as functions get called, a new *stack frame* is created that includes parameters, local variables, etc.
* When a function is done executing, the frame is removed from the top of the call stack
* The data in each frame is distinct and separate from data in other frames
* Consequence: passing by value means that we cannot "swap" values

## Pointers in C

* Every piece of data in a computer is stored in memory
* Data is stored in memory and memory has both an address and contents
* In C, you can define a *pointer* variabe which represents not the contents of memory, but the memory address itself
* You can declare a pointer variable using an asterisk `*`

```c
int a = 10; //normal variable
int *ptrA; //pointer variable
```

* In the above, `ptrA` is a pointer variable that can point to a memory location that stores an integer
* In general, it is best practice to initialize pointer variables to `NULL` (it is case sensitive, all caps)

```c
int *ptrA = NULL;
```

* how can we create a pointer variable to point to a `double`?

```c
double *ptrToDbl = NULL;
```

* In addition, you can check for nullity:

```c
if(ptrToA == NULL) {
  printf("null pointer!\n");
}
```

### How to point pointers to other variables

* There is a referencing operator, `&` that can be applied to normal variables to get their memory address

```c
int a = 42;
int *ptrA = NULL;

//make ptrA point to a:
ptrA = &a;

//doubles:
double b = 3.14;
double *ptrB = &b;

//what happens here?
ptrA = &b;
//You are trying to make an integer pointer point
//to a double, but it will end up being a garbage value
```

* If we have a pointer that points to a memory location, how can we refer to the contents?
* You can use a *dereferencing operator* to make a pointer variable into a "regular" variable
* The dereferencing operator is an astrisk `*`

```c
int a = 42;
int *ptrA = &a;

int c = 10 + *ptrA;  //*ptrA dereferences ptrA and gets its contents (42)
//c = 52

//you can also modify the contents:
*ptrA = 35;
```

### Pointers in Java?

* There are no pointers in Java
* However, all object types are actually *references*

```java
//These are both reference variables:
String s = "Hello";
Intger x = 10;

//this is not a reference variable:
int b = 10;

//You can change references:
s = "Goodbye";
```

* Most built-in types are *immutable* (`String, Double`): once created, their contents cannot be changed
* In general, immutabilitiy is a Very Good Thing: provides thread safety

### Review

* Recall: how you used `scanf("%lf", &b);`
* The ampersand is getting the memory address of `b` so that the function `scanf` can directly manipulate the contents of `b`
* Passing by Reference: instead of copying the value of a variable, its memory address is passed to the function
* If the function has the memory address, then it can do whatever it wants to its contents
* Passing by reference enables us to change a passed variable's value

### Summary

* If you have a regular variable `a` the referencing operator, `&a` gives you `a`'s memory location
* If you have a pointer variable `p` the dereferencing operator `*p` makes it (temporarily) into a regular variable; it gets its contents
* Pointers effectively allow you to "return multiple values"

```text












```
