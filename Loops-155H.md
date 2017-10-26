
# Loops

An introduction to loop control structures.

* We need a way to repeatedly execute blocks of code
* There are three basic elements to a loop control structure:
	* An initialization (where the loop starts)
	* A continuation condition (how long should the loop continue or when should it stop)
	* An increment or "update" (how you make progress toward that ending condition)

## For loops

* A for loop uses the keyword `for`
* All three elements of the loop are placed on the same line
* `for(<initialization>; <continuation>; <increment>)`

```c
int i;  //index variable
for(i=0; i<10; i++) {
  printf("%d\n", i);
}

```
* The above example prints 0..9 one to a line
* Initialization: we set the value of `i` to zero
* Continuation: while `i` is strictly less than 10, execute the *loop body*
* Increment: `i++` adds one to the variable `i`

* Side note: the C standard defines no default values for any variable, it could be zero, it could be 1, it could be `0xDEADBEEF`, it completely depends on the compiler, the OS, etc.
* In Java, uninitialized variables cannot be used (compiler error)

## Increment operators

* You can use single value increment operators: `i++` adds one to the variable, `i--` subtracts 1 from the variable, either are ``equivalent'' to `i = i + 1;` and `i = i - 1;`, the incrmeent operators are just *syntactic sugar*
* You can also use compound increment operators:
    * `a += 10;` adds 10 to the variable `a`
    * `a -= 5;` subtracts 5 from the variable `a`
    * `a *= 2;` this multiples `a` by 2
    * `a /= 3;` divides `a` by 3

```c
//print numbers 10, 20, 30, ... 100
int i;
for(i=10; i<=100; i+=10) {
  printf("%d\n", i);
}

for(i=10; i<100; i+=10) {
  printf("%d\n", i);
}
printf("%d\n", i);

```

* the last example prints 100 because the variable `i`'s last value was 100 and the variable `i` is still *in scope*
* IN contrast, in Java you can declare an index variable within the scope of the loop itself

```java

//loop prints 10 .. 90
for(int i=10; i<100; i+=10) {
  System.out.printf("%d\n", i);
}

//compiler error:
System.out.printf("%d\n", i);
```
* In the example above, `i` is declared inside the loop so its scope is contained only to the loop
* After the loop, the variable is "out of scope" and not valid
* Often, you do need to handle the final (or the initial) iteration of a loop separately

## While Loops

* While loops use a different syntax and the keyword `while`
* The three elements (initialization, continuation, increment) are not on teh same line

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
* Why two loop control structures?
* For loops are generally used when the number of iterations is known up front (ie a fixed number of times or $n$ times, where $n$ is a known variable)
* While loops are generally used in conditions in which we don't know up front how many iterations will be executed

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

* Similar pitfall: misplaced semicolons

```c
int i = 0;
while(i < 10); {
  printf("%d\n", i);
  i++;
}
```

* Consider the following code:

```c
int i = 0;
while(i < 10)
  printf("%d\n", i);
  i++;
```

* Always include curly brackets even if they are not necessary

* Another pitfall: off-by-one errors: Zune Bug

## Other Issues

* Some languages (Java) also support "for-each" loops that allow you to iterate over a collection (an array) of elements (Java actually calls this an "enhanced" for loop)

```java
int a[] = {10, 20, 30, 40};

for(int x : a) {
  System.out.println(x);
}

```

### Nested Loop

* You can (just as with conditionals) place loops within loops

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

3. Write a program to project the total earnings in a savings account with a fixed APR, initial balance, and monthly deposit over a specified number of years.
4. Implement a program to use the Babylonian method to compute a square root of a number a using the series,

$$x_{n+1} = \frac{1}{2} \cdot \left(x_n + \frac{a}{x_n} \right), \quad x_0 = 1$$

## Solutions:

```c
#include<stdio.h>
#include<math.h>

int main(int argc, char **argv) {

  int n = 40;
  //a. print even integers 0 through n
  int i;
  for(i=0; i<=n; i+=2) {
    printf("%d\n", i);
  }

  //a. same, but with commas instead
  for(i=0; i<=n; i+=2) {
    if(i<n) {
      printf("%d, ", i);
    } else {
      printf("%d\n", i);
    }
  }
  //or
  for(i=0; i<n; i+=2) {
    printf("%d, ", i);
  }
  printf("%d\n", i);

  //c. A list of integers divisible by 3 between 10 and 100 (print a total as well)
  int sum = 0;
  for(i=10; i<=100; i++) {
    if(i % 3 == 0) {
      printf("%d\n", i);
      sum += i;
    }
  }
  printf("total = %d\n", sum);
  sum = 0;
  for(i=12; i<=99; i+=3) {
    printf("%d\n", i);
    sum += i;
  }
  printf("total = %d\n", sum);

  //d. print powers of 2: 1 2 4... 2^30
  for(i=0; i<=30; i++) {
    int r = pow(2, i);
    printf("%d ", r);
  }
  //or:
  int r = 1;
  for(i=0; i<=30; i++) {
    printf("%d ", r);
    r *= 2;
    //or:
    //r = r << 1;
  }

  //e. print even numbers 200...10, 10 per line
  printf("\n");
  for(i=200; i>=10; i-=2) {
    if(i % 20 == 0) {
      printf("\n");
    }
    printf("%d ", i);
  }

  int j;
  printf("\n\n");
  for(i=1; i<=10; i++) {
    for(j=1; j<=10; j++) {
      printf("%d ", i+10*j);
    }
    printf("\n");
  }












  return 0;
}

```
```text



























```
