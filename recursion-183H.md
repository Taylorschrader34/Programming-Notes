
Recursion
=============

* In general, *recursion* is something that references itself
* Example: *open recursion*: the `this` keyword allows a class to refer to itself
* Recursive functions: Fibonacci sequence, fractals
* Recursive functions in coding are functions that make a call to themselves


* Motivating challenge: you now live in a world without loops, write a program to count down from 10, 9, 8, ..., 1, "blastoff"

```c

void countDown(int n) {

 if(n == 0) {
   printf("blastoff\n");
 } else {
   printf("%d\n", n);
   countDown(n-1);
 }

}


countDown(10);

```

* In general recursive functions have:
    * One or more base cases: in which no recursive call is made
    * It may also make one or more recursive calls to itself
    * However, each recursive call must make progress toward one of the base cases (thus it cannot pass the same variable values)
* You may have deep or infinite recursions that can result in a stack overflow (too many stack frames have been pushed onto the program stack)
* IN general you don't *need* recursion: any recursive function can be rewritten as a non-recursive function
* Recursion is good:
    * It allows you to think mathematically: recursive functions are straight forwardly implemented
    * It matches a divide and conquer algorithmic strategy
* Recursion is potentially bad because:
    * deep recursion risks stack overflows
    * recursion risks repeated work (recompute the same values over and over)
    * it risks exponential behavior
    * function calls still cost you cpu cycles
* Resolving issues with recursion:
    * Often, a compiler will take your recursive code and make it non-recursive
    * OFten, recursion is "simulated" with an in-memory data structure: ie a stack
    * Some programming languages don't even support recursion
    * Often programming guides/style guides forbid the use of recursion
    * Otherwise, you can stil use recursion, but to avoid computing the same values over and over you an use *memoization*

* Memoization:
    * You stil use recursion to do computation
    * However, you use a cache: its a storage mechanism
    * Every time you compute a value, you store it in the cache
    * Then before you do a recursive computation, you check to see if it is stored in the cache already, if so, you don't do recursion, and you simply return it.


## Exercises

* Write a program to compute the binomial coefficient,
 $$C(n, k) = {n\choose k} = \frac{n!}{(n-k)!k!}$$
Use naive recursion to compute $C(n,k)$ using Pascal's identity:
 $$C(n, k) = C(n-1, k-1) + C(n-1, k)$$
With base cases: $C(n, 0) = 1$, $C(n, 1) = n$ and $C(n,k) = 0$ if $k > n$
* Test your implementation with $C(37, 12) = 1852482996$
* Estimate how long it would take to calculate $C(100, 50$ using this method
* Write a second version using memoization
