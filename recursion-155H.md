
Recursion
=============

* In general, *recursion* is something that references itself
* Example: *open recursion*: the `this` keyword allows a class to refer to itself
* Recursive functions: Fibonacci sequence, fractals
* Recursive functions: functions that can and do call themselves
* Motivating challenge: print a count down from 10 to 1 (blast off) without using a loop

```c

void countDown(int n) {

  if(n == 0) {
    printf("blastoff!\n");
  } else if(n > 0) {
    printf("%d\n", n);
    countDown(n-1);
  }

}

countDown(10);

```

* Recursive functions must have:
    * A base case (a condition after which the recursion stops)
    * Each recursive call must make progress toward the base case
    * Corner cases need to be handled: any invalid input, should not result in a recursive call
* A recursive call is simply when a function calls itself with different parameter values
* Deep or infinite recursions may result in a stack overflow

* Recursion can be "good" and "useful"
    * You have many divide and conquer algorithmic stategies
    * Many programming paradigms use recursion as a fundamental control flow mechanism

* Problems:
    * Recursive functions essentially abuse the program stack by creating many stack frames
    * Deep recursion risks stack overflows
    * Any recursive function can be rewritten as a non-recursive function (recursion is not absolutely necessary)
    * Some organizations (NASA) explicitly forbid recusion because of its risks and inefficiencies
    * Recursion risks recomputing the same values over and over, leading to an  exponential amount of work

* Compromise:
    * You can always "simulate" recursion by using data structures or additional memory
    * you can compile it away
    * You can use *memoization*
        * Instead of making repeated recursive calls, you can store previously computed values into a cache (an array, or a map or some other data structure)
        * You modify your recursive code to check: has a value for the input already been computed (is it stored in the cache?). If so, use it. If not, then pay for the recursion but when you get answer, store it in the cache so that future recursive calls can use it


```java
import java.util.*;
import java.math.*;

public class Main {

  //n => fib(n)
  public static Map<Integer, BigInteger> m = new HashMap<Integer, BigInteger>();
  static {
    m.put(0, BigInteger.ONE);
    m.put(1, BigInteger.ONE);
  }

  public static BigInteger fib(int n) {

    if(m.get(n) != null) {
      return m.get(n);
    } else {
      BigInteger a = fib(n-1);
      BigInteger b = fib(n-2);
      BigInteger x = a.add(b);
      m.put(n, x);
      return x;
    }

  }

  public static void main(String args[]) {

    int n = 100;
    BigInteger result = Main.fib(n);
    System.out.printf("fib(%d) = %s\n", n, result.toString());
  }
}
```

## Exercises

* Write a program to compute the binomial coefficient,
 $$C(n, k) = {n\choose k} = \frac{n!}{(n-k)!k!}$$
Use naive recursion to compute $C(n,k)$ using Pascal's identity:
 $$C(n, k) = C(n-1, k-1) + C(n-1, k)$$
With base cases: $C(n, 0) = 1$, $C(n, 1) = n$ and $C(n,k) = 0$ if $k > n$
* Test your implementation with $C(37, 12) = 1852482996$
* Estimate how long it would take to calculate $C(100, 50$ using this method
* Write a second version using memoization

```












```
