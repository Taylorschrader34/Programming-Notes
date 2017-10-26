
# Arrays

* It is rare to only deal with one piece of data
* Usually, more than one number, string, object, etc. must be stored and processed
* Collections of data can be stored in arrays
* In general:
	* Arrays have a single identifier (name)
	* You access individual *elements* in an array with an *index*
	* Usually, arrays are 0-indexed: first element is at index 0, the second at index 1, etc.
	* If an array is of size *n*, then the last element would be at index *n-1*
	* Indexing is usually done with the square brackets `[]`

## Arrays in C

### Static Arrays

* Static arrays are arrays of a fixed size, that are allocated on top of the program call stack
* Syntax:

```c
int arr[10];
double numbers[20];
```

* In the above, the first delcares an array that can hold 10 `int` values
* The second declares an array that does hold 20 `double` values
* Indexing:

```c
arr[0] = 42; //sets the first value in arr to 42
arr[1] = 12;
arr[2] = -40;
arr[9] = 3.75; //last element gets set to 3 (truncation)
```

* Question: what value does `arr[3]` hold?
* Answer: C does not define a default value, it could be anything
* Alternative syntax:

```c
int n = 7;
int primes[] = {2, 3, 5, 7, 11, 13, 17};
```

* In the above, the compiler takes care of the size for us
* However, *we* are always responsible for *bookkeeping*

### Sizing and Iteration

* In C, there is *absolutely no way* to (consistently) determine the size of an array
* Therefore, it is our responsibility to keep track ("bookkeeping") of the size of all arrays that we create in C
* If we pass an array to a funciton, we would also need to pass its size
* Usually we keep track of the size of an array in an `int` variable

```c
int n = 7;
int primes[] = {2, 3, 5, 7, 11, 13, 17};
primes[7] = 19;
primes[-1] = 42;
```

* Accessing index values outside the bounds of an array is *Undefined behavior* in C: segmenation fault, corrupted memory, bus error

### Variable Length Arrays (VLAs)

* In C99 (and in GNU C89), VLAs are supported:

```c
int n = ....; //read in from the user
int arr[n];
```

* Just because you *can* (or more accurately *might*) be able to do this, doesn't mean you should.

```c
int n = 10000000;
int arr[n]; //an array of size 10 million
```

* Above would almost certainly be a segmentation fault: stack frames are not meant for such large data
* Instead: to use dynamic arrays

## Dynamic Arrays

* Dynamic memory is allocated on top of a program's *heap*
* The memory heap space is geneerally larger but less efficient
* YOu can dynamically allocate memory on the heap using a function in the standard library called `malloc` (Memory Allocation)
* You give it one argument: the number of bytes you want to allocate on the heap for storage
* It returns a *pointer* to the memory location that it has allocated
* It actually returns a generic `void *` pointer
* A `void *` pointer can point to any type of data

```c
int *arr = NULL:
//want to create an array of 1 million integers
int n = 1000000;
arr = (int *) malloc(n * sizeof(int));
```

* Argument to `malloc` is the number of bytes you want allocated (*not* the number of elements)
    * You can use a built-in macro, `sizeof()` to determine the number of bytes each type of variable takes
    * Number of bytes for a double: `sizeof(double)`

```c
//want to create a double array to hold 500 doubles:
int n = 500;
double *numbers = (double *) malloc(n * sizeof(double));
```

* Once you've dynamically allocated an array, you can use it like a normal array

```c
int n = 500;
double *numbers = (double *) malloc(n * sizeof(double));
numbers[0] = 3.14;
numbers[1] = 42.5;
numbers[n-1] = 10.5;
numbers[n] = 1.5; //segmentation fault
```

### Error Checking and Iteration

* It is most natural to use a for-loop to iteate over the elements in an array

```c
int n = 500;
double *numbers = (double *) malloc(n * sizeof(double));
...

//iterate over the array, print each element one to a line:
int i;
for(i=0; i<n; i++) {
  printf("%f\n", numbers[i]);
}
```

* If `malloc` is not able to allocate as much memory as you requested, you can do some error checking
* In such an event, `malloc` will return `NULL` which you can check

```c
int n = 2100000000;
double *numbers = (double *) malloc(n * sizeof(double));

if(numbers == NULL) {
    printf(" You cannot allocate that much!\n");
}
```

## Memory Management

* Using `malloc` creates memory on the heap.  If you no longer need it, you should clean it up and release back so that the OS or your program can reuse it
* Once we no longer need the memory, we should *free* it
* In C you use the `free` function

```c
//no longer need numbers, so let's free it
free(numbers);

number[0] = 10; //illegal

```

* Pitfall: once released, accessing the old memory is *undefined behavior*
* In general, it may be a segmentation fault, or you may certainly not get th eoriginal data
* Always free your memory as soon as possible,
* Don't free your memory if you are not done using

### Dangling Pointers

* Make sure you don't lose your pointers:

```c
int n = 500;
double *numbers = (double *) malloc(n * sizeof(double));

numbers = NULL;
free(numbers); //you cannot free nothing, seg fault
```

* The above is a typical memory leak

### Using arrays with function

* Recall that we need to keep track of the size of an array, therefore, we need to pass at least two variables to a function when we pass an array to a function

```c
/**
 * This function takes an integer array (of size n) and
 * returns the sum of its elements.  It returns 0 if the
 * array is NULL or if its size is <= 0
 */
int sum(int *arr, int n) {

  if(arr == NULL) {
    return 0;
  }

  int total = 0;
  int i;
  for(i=0; i<n; i++) {
    total += arr[i];
  }

  return total;

}
```

* In the above example, we never made changes to the array, we treated it as "read-only", but we could have

```c
int sum(int *arr, int n) {

  if(arr == NULL) {
    return 0;
  }

  int total = 0;
  int i;
  for(i=0; i<n; i++) {
    total += arr[i];
    arr[i] = -10;
  }

  return total;

}
```

* Since arrays are passed by reference, the function can make changes to the original contents
* We may not want that, to "prevent" it we can specify that the array will be treated as read-only, `const` (constant)

```c
int sum(const int *arr, int n) {

  if(arr == NULL) {
    return 0;
  }

  int total = 0;
  int i;
  for(i=0; i<n; i++) {
    total += arr[i];
    arr[i] = -10;  //compiler error now
  }

  return total;

}
```

### Exercises

1. Write a function that creates a new array of a specified size and fills it with ones, returning it

```c
/**
 * This function creates a new integer array of the specified size,
 * intiializes all the values to 1 and returns the array.
 *
 * Returns NULL if the allocation fails.
 */
int * arrayOfOnes(int n) {

  if(n < 0) {
    return NULL;
  }

  int *a = (int *) malloc(n * sizeof(int));
  if(a == NULL) {
    return NULL;
  }

  int i;
  for(i=0; i<n; i++) {
    a[i] = 1;
  }

  return a;
}

```

* In general, functions that return dynamically allocated memory are not responsible for memory management, the functions that call those functions are
* In the above, does the calling function need to know how big the new array is?

2) Write a function to compute the average of elements in an integer array

```c
/**
 * This function computes the average of the elements in the given
 * integer array
 */
double average(const int *a, int n) {
  //TODO: some error checking
  return (double) sum(a, n) / n;
}


```

## Arrays in Java

* Much easier to deal with in Java: No pointers, no memory management, no `malloc()`

```java
int n = 10;
int a[] = new int[n];

String names[] = new String[5];

a[0] = 42;
names[0] = "Chris";
```

* In Java, array values are initialized to default values: numbers have a default of 0, 0.0, `boolean` arrays have a default of `false`, objects have a default of `null`
* In Java, accessing invalid indices `a[-1]` or `a[10]` leads to an `IndexOutOfBoundsException`
* In Java, we do not need to keep track of an array's size it is stored as a "property":

```java
//iterate over an array
int n = 10;
int a[] = new int[n];
...

for(int i=0; i<a.length; i++) {
  System.out.println(i);
}

```

* In addition, there is no memory management, and so no `free` method to free up memory, the JVM handles it for you.

* In Java (as of 1.5) you also have "an enhanced for loop" (for-each loop)

```java
for(int x : a) { //for each element x in the array a
  System.out.println(x);
}

for(String name : names) {
  System.out.println(name);
}
```

* Using arrays in methods in Java

```java
public class ArrayUtils {

  public static int sum(int a[]) {
   int total = 0;
   //this is possible and not preventable a[0] = 42;
   for(int x : a) {
      total += x;
   }
   return total;
  }

  public static int [] newOnesArray(int n) {
    int result[] = new int[n];
    for(int i=0; i<result.length; i++) {
      result[i] = 1;
    }
    return result;
  }

}
```

* IN C you can use the `const` keyword to make the array read-only; in Java there is *no way to do this*
* Arrays are passed by reference, so changes to the passed array are reflected in the calling function

* Some code or authors will use the syntax `int a[]` others will use `int [] a`

### Java: Dynamic Collections

#### Lists

* Lists are ordered collections that grow and shrink as you add/remove elements
* The hold elements in a specific order: first element (index 0) second element (index 1), etc. last element

```java
//create a new empty array list that is able to hold integers
List<Integer> a = new ArrayList<Integer>();
a.add(10);
a.add(20);
a.add(42);  //[10, 20, 42]

int firstItem = a.get(0);
int lastItem = a.get(2);

//you can determine the size of a list using .size():
int n = a.size();
for(int i=0; i<a.size(); i++) {
  System.out.println(a.get(i));
}

for(Integer x : a) {
  System.out.println(x);
}

a.add("ten");  //compiler error
```

#### Sets

* A set is unordered collection of *unique* elements

```java
Set<Integer> b = new HashSet<Integer>();
b.add(10);
b.add(20);
b.add(10); //no effect since 10 is already in the set

//how do we access the i-th element?
for(Integer x : b) {
  System.out.println(x);
}
```

#### Maps

* Some languages (PHP) don't even have "arrays", they have associative arrays (noncontiguous arrays that can use either integers or strings as indices)
* Some languages (Python) "dictionaries": indices can be integers, stirngs, or something else (?)
* Maps are even more general: it allows you to map any type to any type
* You map "keys" to "values"

```java
Map<String, String> namesToNuid = new HashMap<String, String>();
namesToNuid.put("Chris", "35140602");
namesToNuid.put("Bryant", "12345678");
namesToNuid.put("Hosmer", "11111111");

String nuid = namesToNuid.get("Hosmer"); //nuid is "11111111"
nuid = namesToNuid.get("Gordon"); //nuid has the value null

//overwrite values:
namesToNuid.put("Judge", "12345678");
namesToNuid.put("Bryant", "Foo");

//how do you iterate over a map?
//you iterate over keys:
for(String name : namesToNuid.keySet()) {
  System.out.println(name + " maps to " + namesToNuid.get(name));
}

```

* Key values are unique, mapped are not necessarily

### Multidimensional Arrays

* You can have arrays with multiple dimensions
* 2-D arrays: rows and columns (matrices)
* 3-D arrays: rows, columns, lanes or aisles
* 4+: rethink it
* Focus: 2-D arrays, rows and columns, each element is specified with two indices


#### Java

```java
int n, m;
int matrix[][] = new int[n][m];

for(int i=0; i<n; i++) {
  for(int j=0; j<m; j++) {
    matrix[i][j] = (i+j);
  }
}

for(int i=0; i<matrix.length; i++) {
  for(int j=0; j<matrix[i].length; j++) {
    matrix[i][j] = (i+j);
  }
}

for(int[] row : matrix) {
  for(int x : row) {
    System.out.println(x);
  }
}


```

### In C

```c
//we need a pointer to a pointer: need an array of integer pointers
int i, n, m;
int **matrix = NULL;
matrix = (int**) malloc(n * sizeof(int*));
for(i=0; i<n; i++) {
  matrix[i] = (int *) malloc(m * sizeof(int));
}
//once created, you can treat it like a "normal" 2-D array

matrix[0][0] = 42;
matrix[n-1][m-1] = 101;

//clean up?
for(i=0; i<n; i++) {
  free(matrix[i]);
}
free(matrix);

```

### Shallow vs Deep Copies

* Consider the following C code:

```c
int i, n = 10;
int *a = (int *) malloc(n * sizeof(int));
for(i=0; i<n; i++) {
  a[i] = i*10;
}

//make a "copy"
int *b = a;
b[0] = 42;
printf("a[0] = %d\n", a[0]); //prints 42
```

* The above "copy" is a shallow copy, both references refer to the same array, changes to one array affect the "other"
* A deep copy would be two seprate arrays in different memory locations but with the same contents
* Exercise: write code to do a deep copy

```c
int *a = ...


int * deepCopy(const int *a, int n) {
  //TODO: error checking
  int *copy = (int *) malloc(n * sizeof(int));
  for(i=0; i<n; i++) {
    copy[i] = a[i];
  }
  return copy;
}
```

* IN Java: do we have shallow and/or deep copies?

```java
int i, n = 10;
int a[] = new int[n];
for(i=0; i<n; i++) {
  a[i] = i*10;
}

//make a "copy"
int b[] = a;
b[0] = 42;
System.out.printf("a[0] = %d\n", a[0]); //prints 42?
```
* The above is a shallow copy
* How do we make a deep copy? Plan A: write our own code as we did with C above

```java
//make a deep copy using the Arrays class:
int b[] = Arrays.copyOf(a, a.length);
```

* Reimplement a version of `copyOf` for C that allows you to expand or shrink the array, filling with zeros if necessary
* Deep copy of a list:

```java
List<Integer> a = ...

List<Integer> deepCopy = new ArrayList<Integer>(a);


```text



















```
