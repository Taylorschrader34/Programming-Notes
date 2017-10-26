
# Conditionals

* Normally, programs have sequential control flow
* However, more complex problems require *decisions*
* Conditionals are how we can make some code execute under some condition(s) and/or other, different code to execute under other conditions
* `if`-statements, `if-else` statements, `if-else-if` statements
* Conditionals rely on some logical *condition*

## Basic If/Else-If/If-else-if

### If Statements

* An if-statement allows you to specify that a block of code is executed or not
* Syntax/style:
    * You use the keyword `if` and parentheses for the condition and square brackets for the block of code
    * Style: you should have the opening curly bracket on the same line, closing curly bracket at the same indentation level, the block of code should be indented
* If the condition in an if statement evaluates to true, the code block is executed in its entirety
* If the condition evaluates to false, the entire block is skipped

```c
if(<condition>) {
  //block of code to be executed
}
```

### If-Else Statement

* An if-else statement allows you to specify that one or another block of code are executed, but not both: *mutually exclusive*
* Note the style: closing bracket, `else` keyword, opening bracket are all on the same line
* If the condition is true, then block A is executed, if false, block B is executed

```c
if(<condition>) {
  //block A
} else {
  //block B
}

```

### If-Else-If Statement

* Order matters: the first condition that evaluates to true is the one and only one that gets executed
* In general, the most specific conditions should come first: since they are all mutually exclusive
* Note: the final `else` code block is *optional*

```c
if(<condition1>) {
  //block A
} else if(<condition2>) {
  //block B
} else {
  //block C
}

```

## Logical Statements

### Numerical Comparison Operators

* `<, >, <=, >=`
* Equality testing: `==`
* Inequality testing: `!=`
* All of these operators can be used on literals (constant values), variables, or expressions

```c
int a;
double b, c;

//you can, but don't do the following:
if(10 == 11) { ... }  //contradition: never true
if(10 == 10) { ... }  //tautology: always true

if(a == 10) { ... }
//equivalent, okay, but not good style:
if(10 == a) { ... }

if(a == b) { ... }

if(b * b - 4 * a * c < 0) { ... }
```

### Negation operator

* Any logical expression can be negated by using the single `!` *in front of it
* `(a == b)` has a negation of `!(a == b)` but its better to use `(a != b)`
* `(a < b)` has a negation of `!(a < b)` but its better to use `(a >= b)`

#### Boolean variables

* In Java you have a `boolean` variable type

```java
//Java
boolean isStudent;
isStudent = true;
isStudent = false;

if(isStudent) { ... }
if(!isStudent) { ... }

//but don't do this:
if(isStudent == true) { ... } //bad styles
```

* In C, there are no boolean variables
* Instead, C uses truthy-falsy values: `0` is false, anything else is true
* `1, 10, -10, 3.14` are all true

```c
//IN C:
int isStudent;

isStudent = 1;
isStudent = 0;

if(isStudent) { ... }
if(!isStudent) { ... }

```

### Logical Operators

* You can combine logical statements to form more complex logical statements using logical operators (or "logical connectives")
* Logical "And" (conjunction)is a true if both of its operands are true
    * Syntax: `a && b`
    * It is false if either of its operands is false or if they both are
* Logical "or" (disjunction) is true if at least one of its operands is true
    * Syntax: `a || b`
    * It is false if both of its operands are false

```c
if(a > 10 && a < 20) { ... }
if(a == b && a < 10) { ... }

if(a > 10 || a < 20) { ... }
if(a == b || a < 10) { ... }

```

### Short Circuiting

* Condsider a logical and: `a && b`
    * If `a` evaluates to false, does it matter what `b` is?
    * No, the expression will evaluate as false regardless of `b`
    * Therefore, `b` will never be evaluated
* Consider a logical or: `a || b`
    * If `a` evaluates to true, does it matter what `b` is?
    * No, the entire expression will be true
    * therefore, `b` is not evaluated

```java
Integer a = null;
...


//the following is potentially dangerous and may result in NullPointerException
if(a >= 0) { ... }

//instead, you can check for null:
if(a != null && a >= 0) { ... }

```

### Other pitfalls

* Consider the following C code:

```c
if(0 <= a <= 10) {
...
}
```

* Valid C code, it will compile, run, but not give you what you want

```c
if(0 <= a && a <= 10) {
  ...
}
```

* In contrast, in Java, the above code would be a compiler error

Another Pitfall:

* Consider the following code:

```c
//C:
int a = 5;

if(a = 10) { ... }
```

* The above code is valid C and will run, but will be logically wrong, `a` will be assigned the value 10 which will evaluate to true and the code block will be executed
* In Java, it is a compiler error

* Consider the following code:

```c
int a = 10;

if(a == 5); {
 //... do somethin' ...
}
```

* The above codeblock will always execute, because the if statement is bound to an empty statement

### Non numerical comparisons


* In both languages, you can compare single `char` values using character literals

```c
char initial = ...;

if(initial == 'C' || initial == 'c') { ... }

```

* In neither language can you do this with strings

```java
//Java:
String a = "hello";
String b = "goodbye";
String c = "hello";

if(a == b) { ... }  //false

if(a == c) { ... } //false

```

* Solution is to use methods/functions:
* Java: `a.equals(b)` true if the contents of `a, b` are the same; negation: `!a.equals(b)`
* Java: `a.compareTo(b)`: returns an integer that is:
    * *something* negative if $a < b$
    * zero if $a = b$
    * *something* positive if $a > b$

* In C: `strcmp(a, b)` (more later)


## Exercise:

Write a program that reads a decibel level from the user (using command line arguments)
and gives the user a description of the sound level.

* 0 - 60 Quiet
* 61 - 70 Conversational
* 71 - 90 Loud
* 91 - 110 Very Loud
* 111 - 129 Dangerous
* 130 - 194 Very Dangerous
* < 0 or 195+


```text




















```

