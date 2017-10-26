
# Functions & Methods

* A *function* is a reusable unit of code that may take *input(s)* and may produce an *output*
* Already familiar with functions: `main(), printf(), sqrt()`, etc.
* Functions facilitate code reuse, you don't have to cut-n-paste the same block of code over and over
* Procedural abstraction: functions allow us to ignore the minute details of how a certain block of code or algorithm works
* Functions *encapsulate* functionality into reusable, abstract code blocks
* Standard libraries and functions have a lot of testing, debugging behind them and have been optimized and are efficient
* The first question you should ask in problems solving is "is this problem already solved?" that is, does a function already exist to solve my problem?

## Functions in C

* Just as with variables, functions must be declared before they can be used
* In C, functions are "declared" using what is called a *prototype* which defines the function *signature*
    * The name (identifier) of the function
    * The function parameters (its inputs: both the number of inputs, and each input's type)
    * The return type (the type of data that the function returns to the calling function)
* Example: declare a prototype in C for a function that computes Euclidean distance

```c
/**
 * This function computes the Euclidean distance between the
 * two points defined by (x1, y1) and (x2, y2).
 */
double euclideanDistance(double x1, double y1, double x2, double y2);
```

* All functions should be well-documented with comments (preferably doc-style comments)
* In C, documentation should be attached to the prototypes
* Syntax note: for prototypes, the function declaration is ended with a semicolon
* Later in the code, you provide a function *body*:

```c
double euclideanDistance(double x1, double y1, double x2, double y2) {
  return sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
}
```

* Note that you can declare any number of *local variables* in a function
* The names follow the same rules, but cannot conflict with parameter variables
* In general, you do not *need* to declare or use local variables, but it is convenient
* Combined:

```c
#include<stdlib.h>
#include<stdio.h>
#include<math.h>

double euclideanDistance(double x1, double y1, double x2, double y2);

int main(int argc, char **argv) {

  double a = 10.0;
  double b = 15.5;
  double c = 3.14;
  double d = 8.0;

  double distance = euclideanDistance(a, b, c, d);

  printf("the distance is %f\n", distance);

  return 0;
}

double euclideanDistance(double x1, double y1, double x2, double y2) {
  return sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
}

```

## Pass By Value

* In general, all function parameters are *passed by value*
* When a variable is used in a function call, its *value* is copied into the other parameter variable, they are still distinct variables
* Thus any changes to the second "copy" of the variable are not reflected in the calling function.

## Modularity

* For organizational purposes we want to separate functions into different files
* You place function prototoypes into *header files* (end with `.h`)
* You place function definitions into *source files* (have the same name with a `.c` extension)
* DRY = Dont' Repeat Yourself
* Example:
    * To compile (but not link) a "library" file:
    `gcc -c distanceUtils.c`
    produces an object file, `distanceUtils.o`
    * To compile and link to produce an executable:
    `gcc distanceUtils.o distance.c`
    produces `a.out`

## Other issues

### Keyword `void`

* Some functions do not take any inputs
* Some functions do not return any value (output)
* You can use the keyword `void` in both Java and C to specify this

```c
void foo(int a, double b);
int bar(void);
//it is better to omit void for parameters:
int bar();
```

## "Functions" in Java

* Java is an object oriented programming language, thus most people call functions, "methods"
* In Java, all methods must belong to some class
* In Java, there are no prototypes, just method definitions

```java
public class DistanceUtils {

  /**
   * This function computes the Euclidean distance between the
   * two points defined by (x1, y1) and (x2, y2).
   */
  public static double distance(double x1, double y1, double x2, double y2) {

    double temp = (x1-x2)*(x1-x2) + (y1-y2)*(y1-y2);
    double result = Math.sqrt(temp);
    return result;

  }

  public static double distance(Point p1, Point p2) {

     //TODO

  }

}

```

* There are additional *modifier* keywords used
* `public` indicates that the method is publicly available (any piece of code can use/invoke/call) the method
* Alternatives:
    * `private`: only the class itself can see the method
    * `protected`: only the class and its subclasses can see the method
    * No keyword: indicates "package protected"
* The `static` keyword makes the method belong to the class itself rather than instances of the class
* One consequence of `static` methods is that you invoke them using the class name + `.` + the method name
    * `Math.sqrt()`
    * `DistanceUtils.distance(...)`
* For now, just make all of your methods `public static`

### Function Overloading

* A function is *overloaded* if there is more than one version of the function with the same name
* Functions with the same name must have a different number (arity) or a different type(s) of parameters
* Java supports method overloading
* C *does not support* method overloading: you cannot have multiple functions with the same name
* Instead, multiple functions with different names are created for different types
    * `abs(int)`
    * `fabs(double)`
    * `labs(long)`
* Another common workaround is for libraries to prepend a unique identifier to every function: GTK library `gtk_` to almost every function in it
* All of these are workarounds to avoid "polluting the namespace"

# Exercises

Create a suite/library of generalized round functions.  Include a function to `roundToCents` and a general `roundTo` function that can round to an arbitrary decimal place.

## Functions in Detail

* Consider the following "swap" function: swapping doesn't work because variables are passed by value (the original variables are unchanged)

### How functions actually work

* Programs have what is called a *program stack*
* A *stack* is a LIFO data structure (Last-in First Out)
* A program stack has stack *frames*, every time a function is called, a new stack frame is created that contains parameters, local variablles, etc.
* When a function returns, its stack frame is popped off the top, subsequent function calls will overwrite old stack frames
* The data in each stack frame is distinct and separate from data in other stack frames
* Consequence: passing by value means that we cannot swap values

## Pointers in C

* Every piece of data in a computer is stored in memory
* Memory itself has both an address and contents
* In C you can create variables that refere to the address instead of the contents
* These are called *pointers*
* YOu can declare a pointer using an asterisk `*`

```c
int a = 10; //normal variable declaration/assignment
int *ptrA; //pointer declaration
```

* In the above, `ptrA` is a pointer variable that can point to a memory location that contains an integer
* In general, it is best practice to initialize a pointer ot `NULL` (case sensitive, all caps)

```c
int *ptrA = NULL;

//you can also test for NULL:
if(ptrA == NULL) {
  printf("the pointer is invalid\n");
}
```

* How can you create (and initialize) a pointer variable to point to a `double`?

```c
double *ptrB = NULL;
```

### HOw to make pointers point to variables

* There is a *referencing operator*, `&` that when applied to a normal variable, gives you the memory address of that variable
* There is also a dereferencing operator, `*` that when applied to a pointer variable (temporarily) changes it into a regular variable

```c
int a = 42;
int *ptrA = NULL;

//make ptrA point to a:
ptrA = &a;

//WRONG: it makes ptrA point to memory location 42 which may
// not exist or may not belong to us
//ptrA = a;

//change the contents of a through its pointer, ptrA
*ptrA = 52;
```

### Pointers in Java

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

* Most built-in types are like this, they are immutable: once created, their contents cannot be changed
* In general, immutability is a Very Good Thing: provides thread safety

### Review

* Recall: you used `scanf("%lf", &b);`
* By using pointers we can get "pass by reference": we can pass a variable's memory location instead of its value
* The function can change the contents of the original variable
* If the function has a variable's memory address, it can do whatever it wants with it
* You can now write a "swap" function that works

### Summary

* If you have a regular variable `a` the referencing operator, `&a` gives you `a`'s memory location
* If you have a pointer variable `p` the dereferencing operator `*p` makes it (temporarily) into a regular variable; it gets its contents
* Pointers effectively allow you to "return multiple values"


```text












```
