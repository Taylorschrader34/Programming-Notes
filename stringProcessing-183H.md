
# String Processing

* Strings represent *data* that may need to be processed
* Common to deal with CSV (Comma separated value) or similar *formatted* data
* Standard library functions can help, but processing involves designing algorithms

## String Formatting

* IN C: use `printf` to print a formatted string to the standard output
* You can use `sprintf` to "print" a formatted string to another string
* It has the exact same behavior, but takes a new first argument: the string you want to store the result in

```c
char s[100];
int x = 10;
double pi = 3.1415
char name[] = "Chris";
//this prints it:
printf("Hello, %s, you have a value of %d, and pi is %.3f\n", name, x, pi);

//want to store the result into s:
sprintf(s, "Hello, %s, you have a value of %d, and pi is %.3f\n", name, x, pi);
```

* `sprintf` *does* insert the null termianting character for you
* It is *your* responsibility to ensure that the target string is big enough to hold the formmatted string
* It is common to use a temporary "buffer" that is large enough for any string that you (think) you'll encounter; then use `strlen()` to determine how big of a string is actually needed, then copy it over

* Java: string formatting
* `String.format` uses printf-style placeholders and returns a string!

```java
int x = 10;
double pi = 3.1415
String name = "Chris";

String s = String.format("Hello, %s, you have a value of %d, and pi is %.3f\n", name, x, pi);
```

## Java: Mutable Strings

* In Java, the `String` type is *immutable*
* However, you do have a mutable string class called `StringBuilder` that can grow/shrink and be modified as you want
* In multithreaded applications, immutability buys you automatic thread safety
* There is a mutable, thread-safe version `StringBuffer`

```java
StringBuilder sb = new StringBuilder();
sb.append("hello");
sb.append(", ");
sb.append("World");
sb.setCharAt(0, 'H');
String str = sb.toString();

```

### Tokenization

* Some strings may contain formatted data: CSV, TSV (tab separated values)
* Tokenization means splitting up a string along some delimiter or delimiters and processing each token separately
* The C standard string library provides an `strtok` function that takes two arguments: the first is the string to be tokenized, the second is a (list) of delimiters represented as a string
* To continue tokenizing the same string, you pass `NULL` as the first argument instead
* Each call to `strtok` returns a pointer to the "next" token
* It returns `NULL` when the string has no more tokens

```c
char s[] = "Chris,Bourke,UNL,Omaha,NE";
//strtok retunrs a pointer to hte next token, so we need to store it
char *token = NULL;
//first argument: the string you want to tokenize
//second arguemnt: a string that contains delimiter(s)
//returns a pointer to the "next" token
token = strtok(s, ",");
//it returns the pointer to "Chris", replacing the delimiter , with a \0
printf("The first token is %s\n", token); //prints "Chris"
//subsequent calls: you pass NULL for the first arguemnt

token = strtok(NULL, ",");
printf("The second token is %s\n", token); //prints "Bourke"

while(token != NULL) {
  printf("token = %s\n", token);
  token = strtok(NULL, ",");
}
```

* IN Java: you have a `.split()` method

```java
String s = "Chris,Bourke,UNL,Omaha,NE";
String tokens[] = s.split(",");
for(String str : tokens) {
  System.out.println(str);
}

```

```c
#include<stdlib.h>
#include<stdio.h>
#include<string.h>

int main(int argc, char **argv) {

  char s[] = "Chris,Bourke,UNL,Omaha:NE:68116";
  char *copy = (char *) malloc( (strlen(s)+1) * sizeof(char) );
  strcpy(copy, s);

  char *token = strtok(copy, ",:");
  while(token != NULL) {
    printf("token = %s\n", token);
    token = strtok(NULL, ",:");
  }

  printf("the original string is %s\n", s);
  printf("the copied string is   %s\n", copy);

  free(copy);

  return 0;
}
```

### String Comparisons

* In both langauges, *you cannot use* the `==` operator
* In both languages, `==` ends up comparing memory locations
* instead, you use a *comaprator* function or method: given two elements `a`, `b` it returns:
    * *something* negative if `a < b`
    * zero if `a = b`
    * *something positive if `a > b`
* In both languages, strings follow a "natural" lexicographic order (ASCII text table)
* IN C, the string comparator function is `strcmp(const char *s1, const char *s2);`


* In Java: every string has a `.compareTo` method

```c
int result;

result = strcmp("apple", "apple"); //0
result = strcmp("apple", "apples"); //negative
result = strcmp("apples", "apple"); //positive

result = strcmp("Apple", "apple"); //negative

result = strcmp("apples", "oranges"); //negative

result = strcasecmp("ApPlE", "apple"); //zero
```

```java
String a = "apple";
int result;
result = a.compareTo("apple"); // zero
result = a.compareTo("Apple"); // positive
result = a.compareTo("orange"); //negative
result = a.compareTo("apples"); //negative



//java also has a case insensitive version:

result a.compareToIgnoreCase("ApPlE"); //zero

```

## Exercises

* Write a string function to create a deep copy of a string
* Write a string function to change a string's characters to uppercase letters
* Write a string function that returns a new copy of a string with all characters converted to uppercase
* **Extra challenge**: write a `split()`-style function in C (it takes a string and a delimiter and returns an array of strings (*with no memory leaks!*)



```text
















```
