# Introduction

CSCE 155H, Fall 2017

An overview of basics including variables, input and output in C and Java.

## Programming, Compiling & Running Process

* Source files are *plain text* files containing computer code
* Source files have to be compiled:
  $$source \rightarrow assembly \rightarrow machine\,\,code$$


### C

* Edit a source file, say `hello.c`
* Assemble: `gcc -S hello.c`
* Compile: `gcc -c hello.c`
  * Produces an object file, `hello.o`
  * View: `hexdump -C hello.o`
* All in one shot: `gcc hello.c`
* Run: `./a.out`

### Java

* Not a purely compiled language
* You compile a Java program into Java Bytecode
* Java Bytecode is interpretted and run on a Java Virtual Machine (JVM)

#### Command Line

* Compile (to Java bytecode): `javac Hello.java`
* Produces a java class: `Hello.class`
* View: `javap -c Hello.class`

#### Eclipse/IDE

* Process is all automatic

## Program Basics

### In C

```c
/**
 * Author: Chris Bourke
 * Date: August 25, 2016
 *
 * This program converts miles to kilometers
 */
#include<stdio.h>
#include<stdlib.h>

#define KMS_PER_MILE 1.60934

int main(int argc, char **argv) {

  double miles, kms;

  //prompt the user for the input
  printf("Please enter miles: ");

  //read in the input
  scanf("%lf", &miles);

  kms = KMS_PER_MILE * miles;

  printf("%f miles is equal to %f kilometers\n", miles, kms);

  return 0;
}
```

* Comments are human readable messages embedded in code
    * Single line comments: `// this is a comment`
    * Multiple line comments: begin with `/*` and end with a `*/`
    * Doc style comments: `/**` end with `*/`
    * comments are designed to tell the why and the how, not the what
* Preprocessor directives
    * Begin with a hash, `#`
    * They tell the compiler to do certain things before compiling
    * `#define a b` replaces all instances of `a` in your program with `b`s
    * Advantage: you don't have any "magic" numbers; also: any changes are done in one place!
    * `#include<...>` brings in external libraries: `string.h`, `math.h`
* The `main` function is the main entry point to the program; it is where the program begins
* Other syntax and punctuation:
    * each executable line ends with a semicolon `;`
    * blocks of code are delimited by opening and closing curly brackets `{ ... }`
    * commas are used to delimit arguments or variables among other things
    * in general, whitespace does not matter
    * special reserved words and keywords: `printf` and `scanf` are standard identifiers

### In Java

```java
package unl.cse;

import java.util.Scanner;

/**
 * This program converts miles to kilomters
 */
public class MilesToKilometers {

  public static final double KMS_PER_MILE = 1.60934;

  public static void main(String args[]) {

    double miles, kms;

    System.out.print("Please enter miles: \n");

    //create a scanner that reads from the standard input
    Scanner s = new Scanner(System.in);
    miles = s.nextDouble();
    KMS_PER_MILE = 2.2;
    kms = miles * KMS_PER_MILE;

    System.out.println(miles + " miles is equal to " + kms + " kilometers");

    return;
  }

}

```


* Packages organize code into a directory structure
* no package declaration uses the "default package", the top level directory
* Classes need to be in a file with the same name: `MilesToKilometers.java`
* Main entry point is the `main` *method*
* Java also has reserved words: `public class void package static`
* Note: special escaped characters include `\n` endline, `\t` tab, `\\` (backslash)
* Note: all classes in the package `java.lang` such as `System` are automatically *imported*
* Other classes and libraries that are not in `java.lang` need to be manually imported

## Variables

* Java and C are both *statically typed*
* Variables must be *declared* before they are used
* Declarations *must* include both a type and an *identifier* (name)
* The type of a variable is fixed throughout its *scope*
* The *scope* of a variable is the section(s) of code in which it exists
* There are a few core types of variables:
    * `int`: a 32-bit signed 2s complement integer variable
    * an `int` variable can hold any integer value from -2147483648 ~ 2147483647
    * A `double` is a 64-bit "floating" point number: it can hold decimal values accurate to 16-17 digits
    * A `char` is a single character; in C this is a single ASCII character, in Java it is a single unicode character
* The single equals sign is an assignment operator: it means put the value on the right hand side into the variable on the left hand side
```c
//delcare an int variable and set it to 10:
int a;
a = 10;
//or
int a = 10;


double c = 10.5 + a * 2 - 10 / 2;

//java:
int x = 0xa0;
```

### Truncation

* An integer and a floating point number are not the same *type* of variable
* In some situations they are *incompatible* and certain things may happen
* Example:

```c
//okay in both languages:
int a = 10;
double x = 10;

//in C, this results in truncation
int b = 3.14;  //b has the value 3

//in Java the above is invalid
//but you can force a truncation:
int b = (int) 3.14;
```

* truncation is when the fractional part is "chopped off"
* IN Java, implicit type casts are not allowed, but explicit type casts are
* WHen performing division, a similar truncation happens

```c
int a = 10;
int b = 20;

double c = a / b;  //results in 0.0

//explicit type cast:
double d = a / (double) b; //results in 0.5
```

### Other operators

* Assignment operator: `=` place the value of the variable, expression, or constant on the Right hand side into the variable on the left hand side
* Arithmetic operators: `+ - * /` and integer division: `%` which gives you the remainder of the integer division
    * `5 % 2` results in `1`
    * `11 % 3` results in `2`
    * `12 % 6` results in `0`
* Arithmetic follows the same basic order of operations
    * `5 + 12 / 2`
    * Division and multiplication are done before addition and subtraction, both sets are evaluated left to right

## Basic Input/Output (I/O)

* Both languages have a standard input (keyboard) and a standard output (console/terminal)

### C I/O

* Basic input can be achieved using `scanf` stands for scan formatted
* You can use several placeholders for the type of data you want to read:
    * `double`: use `%lf`
    * `int`: use `%d`
    * `char`: use `%c`
* Example:

```c
int a;
double x;

//prompt the user for an integer
printf("enter an integer: ");
scanf("%d", &a);
printf("%d\n", a);

//prompt for a double:
printf("enter a value: ");
scanf("%lf", &x);
printf("%f\n", x);

```

* The standard input is not formatted and may result in undefined behavior with bad input
* You can "print" to the standard output using `printf` which supports similar placeholdres
    * `double`: `%f`
    * `int`: `%d`
    * `char`: `%c`
* You can use multiple placeholders in a single format string

```
int a = 10;
double x = 3.14;
char initial = 'C';

printf("a = %d, x = %f and my initial is %c\n", a, x, initial);


```

* The default formatting for floating point numbers is to print 6 decimals of accuracy
* You can change this using the format placeholder `%.nf` where n is the number of decimals of accuracy
* You can also pad output using the placeh0older `%m.nf` which prints a floating point number to n digits of accuracy with a *minimum* of m columns (including the period)
* You can change this to "justify to the left" using a negative placeholder `%-10.2f`

## In Java

* Output: you have `System.out.print` or `System.out.println` or `System.out.printf`
* Input: the best way is to use a `Scanner` to read from the standard input, `System.in`

```java
import java.util.Scanner;
//create a new scanner that reads from the standard input:
Scanner s = new Scanner(System.in);
//you will need to import the scsanner class

//prompt the user for an integer:
System.out.println("Enter an integer: ");
int a = s.nextInt();

//prompt the user for a floating point number:
System.out.println("Enter a value: ");
double x = s.nextDouble();
```

### Non-interactive input

* Prompt-read pattern assumes that a human being is at the keyboard typing (interactive mode)
* You can have non-interactive input as command line arguments

## IN C

* `argc` represents the number of command line arguments provided to the program with the first one *always* being the executable file name
* `argv` is an array of strings representing the inputs
    * `argv[0]` is always the executable file name
    * `argv[1], argv[2], etc.` are the subsequent arguments
* C provides several ways to convert from these "strings" to numbers:
    * `atoi` converts a string to an integer
    * `atof` converts a string to a double

## In Java

* `public static void main(String args[])`
* `args` contains the command line arguments, but not including the class name
* You can use similar conversions:
    * `Integer.parseInt()` parses a stirng and returns an integer
    * `Double.parseDouble()` parses a string and returns a `double`
* You dont' have an `argc` instead you use `args.length`
```java
		  System.out.printf("The first argument is %s\n",  args[0]);
		  System.out.printf("The second argument is %s\n",  args[1]);
		  System.out.printf("The third argument is %s\n",  args[2]);

		  int a = Integer.parseInt(args[0]);
		  double x = Double.parseDouble(args[1]);

		  System.out.printf("a = %d, x = %f\n", a, x);

```



## Linters

A *linter* is a utility used to identify code that may be syntactically correct but that may indicate potential bugs.

* GCC can be used as a linter by forcing it to identify all warnings:

`gcc -Wall foo.c`

```c
#include<stdio.h>
#include<stdlib.h>

int main(int argc, char **argv) {

  int x = 10;
  int y;
  int a = 10;
  int b = 20;
  double c = a / b;

  printf("c = %f\n");

  scanf("%d", x);

  return;

}
```

* Without the flag: compmiles with no errors or warnings
* With the flag:

```text
lintExample.c: In function ‘main’:
lintExample.c:12:3: warning: format ‘%f’ expects a matching ‘double’ argument [-Wformat=]
   printf("c = %f\n");
   ^
lintExample.c:14:3: warning: format ‘%d’ expects argument of type ‘int *’, but argument 2 has type ‘int’ [-Wformat=]
   scanf("%d", x);
   ^
lintExample.c:16:3: warning: ‘return’ with no value, in function returning non-void [-Wreturn-type]
   return;
   ^
lintExample.c:10:10: warning: unused variable ‘c’ [-Wunused-variable]
   double c = a / b;
          ^
lintExample.c:7:7: warning: unused variable ‘y’ [-Wunused-variable]
   int y;
```


## Exercise

Write a program that prompts (or uses commnad line arguments) for the length of the two sides of a right triangle and outputs the length of its hypotenuse using the Pathagorean Theorem:
  $$h = \sqrt{a^2 + b^2}$$


