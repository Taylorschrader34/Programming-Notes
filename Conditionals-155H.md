
# Conditionals

* Normally, programs have sequential control flow
* However, more complex problems require *decisions*
* Conditionals are how we can make some code execute under some condition(s) and/or other, different code to execute under other conditions
* `if`-statements, `if-else` statements, `if-else-if` statements
* Conditionals rely on some logical *condition*

## Basic If/Else-If/If-else-if

```c
if(<condition>) {
  //the code inside this block will execute if and only
  //if the <condition> evaluates to true
}

if(<condition>) {
  //block A
} else {
  //block B
}

if(<condition1>) {
  //block A
} else if (<condition2>){
  //block B
} else {
  //block C
}
```

## Numeric Comparison Operators

* `<, >, <=, >=`
* Equality operator: `==`
* Inequality operator: `!=`
* All of these numeric operators can operate on literals, variables or expressions

```c
int a;
double b, c;

if(a == 10) {
  //...
}

if(a == b) {
}

//can, but shouldn't:
if(10 == a) {
  //...
}

if( b * b - 4 * a * c < 0) {
  //...

}

//you can, but you shouldn't: (this is a tautology)
if(10 < 20) {
  //...
}
```

## Negation operator

* Any logical expression can be negated with a single `!`
* `(a == b)` has a negation of `!(a==b)` but that's the same as `(a != b)`
* Usually used in the context of a boolean "flag" variable
* In Java: you have `boolean` variable type, which can be negated

```java
//Java:
boolean isStudent;
isStudent = true;
isStudent = false;

if(isStudent) {
  //...
}

if(!isStudent) {
  //...
}

//you can, but you shouldn't test for equality with booleans:
if(isStudent == true) {
  //....
}

```

## Boolean variables in C

* C has no `boolean` type of variable
* Instead it uses `int` variables as substitutes
* `0` represents false, anything else represents `true` (`1`, `2`, `-10` are all true)

```c
//C:
//you cannot have: boolean isStudent;
int isStudent;
isStudent = 1;
isStudent = 0;

if(isStudent) {
  //...
}

if(!isStudent) {
  //...
}

```

## Logical Operators

* You can combine logical statements to form more complex logical statements using "connectives" or logical operators
* The logical AND is true if and only if *both* of its operands are true
    * Syntax: `&&`
    * Example: `a && b` is true if and only if both `a` and `b` evaluate to true
* The logical OR is an operator that is true if *at least* one of its operands is true
    * Syntax: `||`
    * Example: `a || b` is true if `a` is true or if `b` is true or if they are both true

```c
if(a > 10 && a < 20) { ... }
if(a == b && a < 10) { ... }

if(a > 10 || a < 20) { ... }
if(a == b || a < 10) { ... }
```

### pitfalls

* Consider the following C code:

```c
if(0 <= a <= 10) {
  ...
}
```

* In C, the above code will compile, execute but will not work for certain values (such as 20)
* Solution:

```c
if(0 <= a && a <= 10) {
  ...
}
```

* In Java, it would actually be a compiler error, in Java, all logical operators must be applied to boolean expressions
* How might we negate this?

```c
if( !(0 <= a && a <= 10) ) {
  ...
}
//or:
if(a < 0 || a > 10) {
}
```

### Short Circuiting

* Consider a logical and: `a && b`
    * If `a` evaluates false, does it matter what the value of `b` is? (NO)
    * The entire expression is false
    * Consequently, the program never evaluates `b`
* Consider a logical or: `a || b`
    * If `a` evaluates to true, does it matter what the value of `b` is?
    * No, the entire expression will evaluate to true
    * Consequently, the program never evaluates `b`
* As a consequence, you need to write expressions in a certain order

```java
Integer a = ...;

//at this point, a could be null, may result in a NullPointerException
if(a >= 0) {
  ...
}

//safer
if(a != null && a >=0) {
 ...
}

//this is not the same thing:
if(a >= 0 && a != null) {
 ...
}
```

### Non-numeric comparisons

* In both languages, you can compare a single `char` value

```c
char initial = 'C';

if(initial == 'c' || initial == 'C') {
  ...
}
```

* In *neither* language can you (should you) use the `==` or other numerical operator with strings

```java
//Java:
String a = "hello";
String b = "goodbye";
String c = "hello";

if(a == c) {  //will never (hardly ever) be true
  ...
}

```

* IN both languages, when you use `==` on strings, it compares the reference, or memory address, not hte contents of the string
* In both languages, you need to use a method or function to do a string comparison
* IN Java: `a.equals(b)` or `a.compareTo(b)`
* In C: use `strcmp(a, b)`
* The `compareTo` and `strcmp` are *comparators*: they have a basic contract:
    * if $a < b$ then it returns something negative
    * if $a = b$ then it returns zero
    * if $a > b$ then it returns something positive

```java
String a = "hello";
String b = "goodbye";
String c = "hello";

if(a.equals(c)) { //evaluates to true
}

if(a.compareTo(b) < 0) {
  System.out.println(a + " comes before " + b);
} else if(a.compareTo(b) == 0) {
  System.out.println(a + " equals " + b);
} else {
  System.out.println(a + " comes after " + b);
}
```

## More Gotchas

* Consider the following code in C:

```c
//C:
int a = 5;

if(a = 10) { ... }
```
* The above code is valid C and will run, but will be logically wrong, `a` will be assigned the value 10 which will evaluate to true and the code block will be executed
* In Java, its a compiler error

* Consider the following code:

```c
int a = 10;

if(a == 5); {
 //... do somethin' ...
}

```

* The above codeblock will always execute, because the if statement is bound to an empty statement


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

