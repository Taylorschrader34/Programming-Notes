
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

* Static arrays are arrays of a fixed size that are allocated on the program stack
* Syntax:

```c
int arr[10];
double numbers[20];
```

* In the above, the first line declares an array that holds 10 `int` values
* The second declares an array that holds 20 `double` values
* Using arrays:

```c
arr[0] = 42; //sets the first value in arr to 42
arr[1] = 12;
arr[2] = -40;
arr[9] = 3.75; //make the last element equal to 3
```

* Question: in the above code, what value does `arr[3]` have?
* Answer: C does not have default values for array elements, it could be zero, it could be garbage, you don't know, depends on the OS, compiler, etc.
* Alternative syntax:

```c
int n = 7;
int primes[] = {2, 3, 5, 7, 11, 13, 17};
```

* In C, there is *absolutely NO way* to determine the size of an array after it is created
* In C, you must do you own bookkeeping: you have to keep track of the size of every array that you create
* It is our responsibiliity to keep track of the size of all array
* If we pass an array to a function, we also need to pass the size of that array
* IN general you keep track of the size of an arary using an `int` variable

### Pitfall: invalid indexing

```c
int n = 7;
int primes[] = {2, 3, 5, 7, 11, 13, 17};
primes[7] = 19;
primes[-1] = 42;
```

* The above code illegally accesses an invalid index: segmentation fault, it could corrupt some other part of our program's memory
* Doing so is "undefined behavior"

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

* Dynamic arrays are allocated on top of a program's *heap* instead of its stack
* The memory heap is generally larger, but less efficient
* We dynamicaly allocate memory on the heap using a function called `malloc()` (Memory ALLOCation)
* You give it one argument: the number of bytes you want to be allocated
* It returns a generic `void *` pointer
* Example:

```c
int *arr = NULL;
//create an array of 1 million integers
int n = 1000000;
arr = (int *) malloc(n * sizeof(int));
```

* You can use `sizeof()` to determine the number of bytes for any type
* You generally want to type cast the returned `void*` pointer to the pointer type that you want
* Create an array to hold 500 `double` values:

```
int n = 500;
double *numbers = (double *) malloc(n * sizeof(double));
//once created, however, you can treat a dynamic array just as you would a static array
numbers[0] = 3.14;
numbers[1] = 42.5;
numbers[n-1] = 32.1;
numbers[n] = 10.5; //illegal/undefined

```

### Error Checking Dynamic Arrays

* `malloc` can fail if you ask for too much memory
* You can test for failure by checking hte pointer it returns
* If it fails, it returns `NULL`

```c
double *numbers = (double *) malloc(n * sizeof(double));

//check if it worked:
if(numbers == NULL) {
  printf("ERROR: out of memory\n");
}
```

### Iterating over arrays

* its most natural to use a for-loop to iterate over elemnets in an array

```c
int i;
for(i=0; i<n; i++) {
  arr[i] = 10 * i;
}

```


### Memory Management

* Using `malloc` creates memory on the heap.  If you no longer need it, you should clean it up
* Cleaning it up means "freeing" it

```c
//we no longer need arr, so free it
int x = arr[0]; //okay :)
free(arr);

//once free, accessing it is invalid
int x = arr[0]; //illegal, seg fault
```

### Bad Memory Management

```c
int n = 500;
double *numbers = (double *) malloc(n * sizeof(double));
numbers[0] = 42.5;

numbers = NULL;
free(numbers);

```

* THe above is a memory leak: you lose access to memory (or just don't clean it up)

### Using Arrays with Functions

* Recall that you need to keep track of an array's size (bookkeeping)
* If you pass an array to a function, you also must pass its size

```c
/**
 * This function takes an array of integers
 * of size n and returns their sum
 */
int getSum(int *arr, int size) {

  int sum = 0;
  int i;
  for(i=0; i<size; i++) {
    sum += arr[i];
  }
  return sum;
}
```

* Question: did we make changes to the array?


```c
/**
 * This function takes an array of integers
 * of size n and returns their sum
 */
int getSum(int *arr, int size) {

  int sum = 0;
  int i;
  for(i=0; i<size; i++) {
    sum += arr[i];
    arr[i] = -10;
  }
  return sum;
}
```

* You can use `const` to indicate that no changes will be made to the array

```c
/**
 * This function takes an array of integers
 * of size n and returns their sum
 */
int getSum(const int *arr, int size) {

  int sum = 0;
  int i;
  for(i=0; i<size; i++) {
    sum += arr[i];
    arr[i] = -10; //compiler error
  }
  return sum;
}
```

### Exercises

1. Write a function that creates a new array of a specified size and fills it with ones, returning it

```c
/**
 * TODO: write documentation
 */
int * onesArray(int n) {

  if(n < 0) {
    return NULL;
  }

  int *result = (int *) malloc(n * sizeof(int));
  if(result == NULL) {
    return NULL;
  }
  int i;
  for(i=0; i<n; i++) {
    result[i] = 1;
  }
  return result;

}
```

2. Write a function to compute the average of elements in an integer array

```c
/**
 *  this function takes an integer array of the specified size
 * and returns the average of its elements
 */
double getAverage(const int *arr, int size) {
  if(size < 0 || arr == NULL) {
    return 0;
  }
  return (double) getSum(arr, size) / size;
}
```

## Arrays in Java

* Much easier to deal with in Java: No pointers, no memory management, no `malloc()`

```java
//create an array of 10 integers:
int arr[] = new int[10];

double numbers[] = new double[500];
//not valid Java: double numbers[10];

String names[] = new String[5];

arr[0] = 42;
numbers[499] = 42.5;
names[0] = "Chris";

```

* In Java, array values are initialied to default values, Numbers: 0, 0.0; `boolean`: `false`, objects: `null`
* In Java, accessing invalid indices `arr[-1]` or `arr[10]` will result in an `IndexOutOfBoundsException`
* In Java, the size of an array can be found using `arr.length` (the `.length` property)

```java
//iterate over our arr array
for(int i=0; i<arr.length; i++) {
  System.out.println(arr[i]);
}
```

* Java (as of 1.5) also has "enhanced for-loop" (just another term for a foreach loop)

```java
for(int x : arr) {
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

* In Java Arrays are passed by reference and so the method receiving the array can make changes to it, just like C
* In C, we used `const` to indicate no changes *would* be made, in Java *there is no way to do this*

* How might we "free" up an array's memory in Java?

```java
int arr[] = new int[10];
...

arr = null;
```

* The above is generally not good practice, let the JVM do it for you

### Java: Dynamic Collections

* Java supports arrays, but in general, they fall short
* Dynamic `List` data structure; arrays have a fixed size and cannot grow nor shrink, `Lists` can
* LIke arrays, they start indexing at 0

```java
//create a new empty array list that is able to hold integers
List<Integer> a = new ArrayList<Integer>();
a.add(10);  //adds 10 to the list to the "end"
a.add(20);  //adds 20 to the list's end
a.add(42);  //[10, 20, 42] at indices 0, 1, 2 respectively

//you can determine the number of elements in a using a.size():
for(int i=0; i<a.size(); i++) {
  System.out.println(a.get(i));
}

//you can also use an enhanced for loop
for(int x : a) {
  System.out.println(x);
}

//The polymorphic generics mean that you *cannot* do the following:
a.add("ten");

```

#### Sets

* A set is an *unordered* collection of unique similar elements

```java
Set<Integer> b = new HashSet<Integer>();
b.add(10);
b.add(20);
b.add(42);
b.add(10); //no effect, "noop"

//what is the i-th element in b?
//sets have no index, so it has no .get(i) method

//how to iterate over elements in a set:
for(int x : b) {
  System.out.println(x);
}

```

#### Maps

* Some languages (PHP) don't even have "arrays", they have "associative arrays" (non-contiguous arrays that can have either integers or strings as index values)
* Some languages have "dictionaries": indices can be integers, strings, or maybe something else
* Maps are even more general: you can map any type to any type
* You can define any type as the "key" and any other type as the "value", keys map to values

```java
Map<String, String> namesToNuid = new HashMap<String, String>();
namesToNuid.put("35140602", "Bourke");
namesToNuid.put("12345678", "Bryant");

String name = namesToNuid.get("35140602");

//you can also reassign key values:
namesToNuid.put("35140602", "Foo");
name = namesToNuid.get("35140602");  //now name has the value "Foo"

//what happens if you try to get an invalid key?
name = namesToNuid.get("blahblah"); //name is null because no such key-value pair was defined
```

* Maps are great data structures that can really simplify basic operations:

* Exercise: write a java method to compute the *mode* of a `List` of integers

```java

import java.util.*;
public class Main {

  public static int getMode(List<Integer> a) {

    //map an element to a count of that element:
    Map<Integer, Integer> m = new HashMap<>();
    int maxCount = 0;
    Integer mode = null;
    for(int x : a) {
      Integer count = m.get(x);
      if(count == null) {
        //first time we've seen x
        count = 0;
      }
      m.put(x, count + 1);
      if(count+1 >= maxCount) {
        maxCount = count+1;
        mode = x;
      }
    }
    return mode;

  }

  public static void main(String args[]) {
    List<Integer> a = Arrays.asList(10, 20, 5, 7, 5, 20, 5, 8, 10, 10, 10, 5);
    int mode = getMode(a);
    System.out.println("Mode of " + a + " is " + mode);
  }

}
```

## Multidimensional Arrays

* You can have 2-D arrays: rows and columns (matrices)
* You can have 3-D arrays: rows, columns, lanes or aisles
* 4+ dimensional: rethink what you are doing
* Focus: 2-D arrays, rows/columns ("row major" form), each element is specified with 2 indices

```java
int n = 10, m = 20;
int matrix[][] = new int[n][m];  //n x m matrix of integer values

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

//need a pointer to a pointer: need an array of integer pointers
int i, n, m;

int **matrix = NULL;
matrix = (int **) malloc(n * sizeof(int *));
for(i=0; i<n; i++) {
  matrix[i] = (int *) malloc(m * sizeof(int));
}

//now you can use it "normally"
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

* The above is a *shallow copy* changes to `b` affect `a` because the pointers refer to the same memory address
* Alternatively, you can create *deep copies* which are brand new memory spaces with the same contents, changes to one do not affect the other

```c
//make a deep copy of a:
int *b = (int *) malloc(n * sizeof(int));
for(i=0; i<n; i++) {
  b[i] = a[i];
}

//maybe it is better as its own funciton:
int * deepCopy(const int *a, int size) {
  //TODO: do some rudimentary error checking
  int i;
  int *copy = (int *) malloc(size * sizeof(int));
  for(i=0; i<size; i++) {
    copy[i] = a[i];
  }
  return copy;
}

```

#### Deep vs Shallow Copy in Java

```java
int i, n = 10;
int a[] = new int[n];
for(i=0; i<n; i++) {
  a[i] = i*10;
}

//make a "copy"
int b[] = a;
b[0] = 42;
System.out.printf("a[0] = %d\n", a[0]); //prints 42, since it is a shallow copy
```

* To do a deep copy in Java:

```java
//you can create a deep copy using Arrays:
int b[] = Arrays.copyOf(a, a.length);
```

* The `copyOf` method can expand or contract the new array
* Exercise: let's implement the same thing but in C

```c

/**
 * This function takes an array and builds a deep copy of
 * it of the specified new size, cutting off elements or
 * padding with zeros as necessary
 */
int * copyOf(const int *a, int size, int newSize) {
  //TODO: do some rudimentary error checking
  int i;
  int *copy = (int *) malloc(newSize * sizeof(int));
  for(i=0; i<size && i<newSize; i++) {
    copy[i] = a[i];
  }
  for(i = size; i<newSize; i++) {
    copy[i] = 0;
  }

  return copy;
}

```


```text



















```
