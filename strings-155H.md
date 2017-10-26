
# Strings

* Strings are ordered sequences of characters (may be ASCII or Unicode)
* Different languages represent strings differently
* Most languages provide a standard library of functions/methods to process strings

## Strings in C

* In C, strings are arrays of `char` elements
* In C, all strings **must** end with a special null-terminating character `\0`
* You *can* declare/use static strings

```c
char message[] = "hello World!";

//you can treat a string as a regular array:
message[0] = 'H';
```

* In the above, the `message` string was automatically null-terminated for us, thus it has 13 characters
* Strings in C are *mutable*: the contents of a character array can be changed

```c
message[5] = '\0'; //message to "Hello"

printf("%s", message);  //prints "hello", but not the remaining characters

//this makes message into an empty string
message[0] = '\0';

//but you can restore it:
message[0] = 'H'; //now its back to "Hello"
```

* The size of the array of `message` is still 13 bytes, but the string has been "shortened"
* What happens when a string is not properly null-terminated?

```c
char message[] = "Hello World!";
message[12] = '!';
```

* If the contents of a string can be modified, then how long is a string?
* C provides a standard string libarary in `string.h`

### String Length

* `strlen()` takes a string and returns the number of characters in it *not including* the null terminating character

```c
char message[] = "Hello World!";
int n = strlen(message); //12
```

### Dynamic Strings

* Just as with dynamic integer arrays, you may not know how big of a string you need at compile time
* You can similarily us `malloc()` to create enough space to hold any string you want

```c
//create a string to hold up to 100 characters
int n = 100;
char *str = (char *) malloc((n+1) * sizeof(char));

//printf("%s\n", str);  //dangerous: it may lead to a seg fault

str[0] = 'C';
str[1] = 'h';
str[2] = 'r';
str[3] = 'i';
str[4] = 's';
str[5] = '\0';

printf("%s\n", str); //prints "Chris"
```

### Other Convenience Functions

* `strcpy` can be used to copy one string into another

```c
int n = 5;
char *str = (char *) malloc((n+1) * sizeof(char));

strcpy(str, "Chris");
```

* `strcpy` copies the second string into the first
* `strcpy` *will assume* that the second string is a valid, null-terminated string
* `strcpy` *will assume* that the destination string is *large enough to accommodate the copied string

```c

char *firstName = (char *) malloc((5+1) * sizeof(char));
char *lastName = (char *) malloc((6+1) * sizeof(char));
char *str = (char *) malloc(101) * sizeof(char));

//by the way, this is NOT possible:
name = "Chris";

strcpy(firstName, "Chris");
strcpy(lastName, "Bourke");

//goal: I want to have "str" contain "Bourke, Chris"
strcpy(str, lastName);

```

### Concatenation functions

* `strcat(char *dest, const char *source)`

```c
//I want my full name, Bourke, Chris
strcpy(str, lastName);
strcat(str, ", ");
strcat(str, firstName);

```

* `strcat` will assume that all strings are valid and null-terminated, and that the destination string is big enough to hold what you are concatenating

### Other Convenience functions

* `ctype.h` library provides several other "check" functions: `isalpha(char c)`, `isdigit(char c)`, `islower()` `isupper()' `isspace()`

### Byte-Limited Versions

* `strncpy(char * dst, const char * src, size_t len)`
* `strncat()`


```c
char fullFirstName[] = "Christopher";
char *firstName = (char *) malloc((5+1) * sizeof(char));

strncpy(firstName, fullFirstName, 5); //places "Chris" into firstName
firstName[5] = '\0';

```

## Strings in Java

* In Java, Strings are full objects, they have many methods that can process them.
* In Java *there is no null terminating character* at the end of strings
* In Java, strings are *immutable*, once created their contents cannot be changed

```java
String s = "hello";
s.toUpperCase();  //no effect
System.out.println(s);
String t = s.toUpperCase();
System.out.println(t); //works

```

* The String "library" are all the methods in the String class
* https://docs.oracle.com/javase/7/docs/api/java/lang/String.html

```java
String str = "...";
//iterate over each character in a string, print out one to a line
for(Character c : str.toCharArray()) {
  System.out.println(c);
}
```


```text
















```
