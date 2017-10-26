
# Loops

An introduction to loop control structures.  RAIK 183H, Fall 2018

* We need a way to repeatedly execute blocks of code
* There are three basic elements to a loop control structure:
	* An initialization (where the loop starts)
	* A continuation condition (how long should the loop continue or when should it stop)
	* An increment or "update" (how you make progress toward that ending condition)

## For loops

* A for loop uses the keyword `for`
* All three elements are on the same line:
* `for(<initialization>; <continuation>; <increment>)`
* Take syntax note: there is a semicolon on the first two, but not the third

```c
int i;  //index variable
for(i=0; i<10; i++) {
  printf("%d\n", i);
}

```
* The above example prints 0..9 one to a line
* Initialization: we initialize `i` to 0
* Continuation: we will continue as long as the value in `i` is strictly less than 10
* Increment: `i++` adds one to the variable `i`

* Side note: the C standard defines no default values for any variable, it could be zero, it could be 1, it could be `0xDEADBEEF`, it completely depends on the compiler, the OS, etc.

* In Java, uninitialized variables cannot be used (compiler error)
* In both languages it is best practice to initialize a variable if appropriate

## Increment Operator

* you can use single value increment operators, `i++` which adds one to the variable `i` and `i--` which subtracts one from the variabe `i` (postfix increment/decrement operators)
* The are "equivalent" `i = i + 1` or `i = i - 1`
* Side note: there are also prefix incrmeent/decrement operators `++i` and `--i`
* You can also use "compound assignment operators":
    * `a += 10` is "equivalent" to `a = a + 10;`
    * `a -= 10` is "equivalent" to `a = a - 10;`
    * `a *= 10` is "equivalent" to `a = a * 10;`
    * `a /= 10` is "equivalent" to `a = a / 10;`


* Why the different syntax?  Known as "syntactic sugar"

```c
//print numbers 10, 20, 30, ..., 100, one to a line
int i;
for(i=10; i<=100; i+=10) {
  printf("%d\n", i);
}

//or:
for(i=10; i<100; i+=10) {
  printf("%d\n", i);
}
printf("%d\n", i);

```

* The last example prints 100 because the variable `i`'s last value was 100 (in order to break out of the loop)
* Also, the variable `i` is still *in scope* because it was declared outside the loop
* In ANSI C (old school C) this was required
* In modern C and in Java, you can declare an index variable "inside" the loop

```java
//java:
for(int i=10; i<=100; i+=10) {
  System.out.println(i);
}
```

* In general, variables should have as limited of a scope as possible to avoid "polluting the namespace"
* In Java: use the above, in C (on CSE) you *may* need to use the "old" syntax

## While Loops

* WHile loops use a different syntax and the keyword `while`
* The three elements (initialization, continuation, increment) are *not* on the same line

```c
<initialization>
while(<continuation>) {
  //loop body

  <increment>
}
```

```c
//initialization:
int i = 0;
while(i < 10) {  //continuation
  printf("%d\n", i);

  //increment
  i++;

}
```

* Fact: any for loop can be rewritten as a while loop and vice versa
* Why two loop control structures?  Syntactic sugar, AND it is closer to "natural langauge"
* We have two loop control structures because there are at least 2 "natural" scenarios in which we use them:
    * For loops: used when you know up front how many iterations you are going to have
    * While loops: are used when you may not know how many iterations you have

### Do-while loop

```c
int i=0;
do {
  print("%d\n", i);
  i++;
} while(i < 10);
```

* A do-while loop is unconditionally executed at least once
* The continuation condition is checked at the *end* of the loop rather than at the beginning (as with for and while loops)

## Pitfalls

* Consider the following code:

```c
int i = 0;
while(i < 10) {
  printf("%d\n", i);
}
```

* The above is an *infinite loop* since there is no increment toward the termination condition
* To end a command line program: control-C
* To end a program in eclipse: use the "stop" button

* Similar pitfall:

```c
int i = 0;
while(i < 10); {
  printf("%d\n", i);
  i++;
}
```

* Always use good style

* Consider the following code:

```c
int i = 0;
while(i < 10)
  printf("%d\n", i);
  i++;
```

* Infinite loop because the loop binds to the next executable line, so *always use brackets* (even if you don't need to)

* Another pitfall: off-by-one errors: Zune Bug

## Other issues

* Some languages (Java) also support "for-each" loops that allow you to iterate over a collection (an array) of elements (Java actually calls this an "enhanced" for loop)

```java
int a[] = {10, 20, 30, 40};

for(int x : a) {
  System.out.println(x);
}

```

### Nested Loops

* You can nest a loop inside another loop


```c
int i, j;
for(i=0; i<10; i++) {
  for(j=0; j<10; j++) {
    printf("%d\n", (i+j));
  }
}

```

## Exercises

1. Do the following
	* A list of even integers 0 to n, one to a line
	* The same list, but delimited by commas
	* A list of integers divisible by 3 between 10 and 100 (print a total as well)
	* Prints all positive powers of two, 1, 2, 4, 8, …, up to 2^30
	* Prints all even integers 2 thru 200 on 10 different lines (10 numbers per line) in reverse order
	* Prints the following pattern of numbers (hint: use some value of i+10j):

```text
11, 21, 31, 41, ..., 91, 101
12, 22, 32, 42, ..., 92, 102
...
20, 30, 40, 50, ..., 100, 110
```

2. Write a FizzBuzz program (see [here](https://blog.codinghorror.com/why-cant-programmers-program/) for more information)

"Write a program that prints the numbers from 1 to 100. But for multiples of three print "Fizz" instead of the number and for the multiples of five print "Buzz". For numbers which are multiples of both three and five print "FizzBuzz"."

3. Write a program to project the total earnings in a savings account with a fixed APR, initial balance, and monthly deposit over a specified number of years.
4. Implement a program to use the Babylonian method to compute a square root of a number a using the series,

$$x_{n+1} = \frac{1}{2} \cdot \left(x_n + \frac{a}{x_n} \right), \quad x_0 = 1$$

Compute it until the difference is neglible:
$$|x_{n+1} - x_n| \leq \epsilon$$
with $\epsilon = 0.0001$

```text



























```
