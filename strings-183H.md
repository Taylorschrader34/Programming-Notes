
# Strings

* Strings are ordered sequences of characters (may be ASCII or Unicode)
* Different languages represent strings differently
* Most languages provide a standard library of functions/methods to process strings

## Strings in C

* In C, strings are arrays of `char` elements
* In C, all strings **must** end with a special null-terminating character, `\0`
* You *can* declare/use static strings

```c
char message[] = "hello World!"; //the size of this array is 13
//to accommodate the null terminating character

//strings are mutable:
message[0] = 'H';
```

* In the above, the `message` string was automatically null-terminated for us, thus it has 13 characters in it
* Strings in C are *mutable*: they can be changed

```c
message[5] = '\0';
```

* The above "shortens" the string to only `"Hello"`, but the array still has the same amount of memory, we did not free anything

```c
message[5] = '_';
//message is now "Hello_World!"
```

```c
message[12] = '!';
```

### String Length

* If the contents of a string can be modified, then how long is a string?
* C provides a standard string library in `string.h`
* `strlen(s)` returns the length of the string `s`
* NOTE: all string library functions *assume* that your strings are all valid

```c
char message[] = "Hello World!";
int n = strlen(message); //12

```

* `strlen` does not include the null terminating character

### Dynamic Strings

* Just as with dynamic integer arrays, you may not know how big of a string you need at compile time
* You can similarly use `malloc()` to create enough space to hold any string you want

```c
//create a string that can hold 100 characters
char *str = (char *) malloc(101 * sizeof(char));

//what is in str?
printf("%s", str);
//do not assume what is in your array
//always make sure your strings are valid:

str[0] = 'C';
str[1] = 'h';
str[2] = 'r';
str[3] = 'i';
str[4] = 's';
//not a valid string without hte null termianating character
str[5] = '\0';

//invalid, memory leak:
str = "Chris";
```

* Once we've created a dynamic string, how can we easily set its contents?

### String Copy

* `strcpy` is a function provided by the string library that allows you to copy the contents of one string into another

```c
char *name = (char *) malloc(101 * sizeof(char));

//copies "Chris" including the null terminating character
//in to the name array
strcpy(name, "Chris");

//Now I want "Chris Bourke"
strcpy(name, "Bourke");
```

### String Concatenation

* `strcat` allows you to concatenate one string onto the end of the other
* It is assumed that both are valid, null terminated strings AND the first string has enough room to accommodate the second

```c

char *firstName = (char *) malloc((5+1) * sizeof(char));
char *lastName = (char *) malloc((6+1) * sizeof(char));
char *str = (char *) malloc(101) * sizeof(char));

strcpy(firstName, "Chris");
strcpy(lastName, "Bourke");

//goal: I want to have "str" contain "Bourke, Chris"
strcpy(str, lastName);
strcat(str, ", ");
strcat(str, firstName);
//str contains "Bourke, Chris"

```
### Other Convenience functions

* `ctype.h` library provides several other "check" functions: `isalpha(char c)`, `isdigit(char c)`, `islower()` `isupper()` `isspace()`

### Byte-Limited Versions

* `strncpy(char * dst, const char * src, size_t len)`
* `strncat(char * dst, const char * src, size_t len)`

```c
char fullFirstName[] = "Christopher";
char *firstName = (char *) malloc((5+1) * sizeof(char));

strncpy(firstName, fullFirstName, 5);
//you need to null terminate manually in some instances:
firstName[5] = '\0';

```

## Strings in Java

* IN Java, strings are much easier
* In Java, they are full objects, they have many methods built in to help you process them
* In Java, Strings are immutable, once created, they cannot be

```java
String s = "hello";
s.toUpperCase();  //no effect on the string s
System.out.println(s);
String t = s.toUpperCase();
System.out.println(t); //works

```

* Concatenation is really easy too:

```java
String firstName = "Chris";
String lastName = "Bourke";
String fullName = lastName + ", " + firstName;

firstName = "Christopher";
```



```text
















```
