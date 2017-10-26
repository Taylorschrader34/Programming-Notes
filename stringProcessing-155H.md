
# String Processing

* Strings represent *data* that may need to be processed
* Common to deal with CSV (Comma separated value) or similar *formatted* data
* Standard library functions can help, but processing involves designing algorithms

## String Formatting

* In C: You can use `printf` to print a formatted string
* You can also use `sprintf` to store a formatted string
* It is used exactly the same, but hte first argument is a string in which to store the formatted string

```c
char s[100];
int x = 10;
double pi = 3.1415
char name[] = "Chris";
//this prints it:
printf("Hello, %s, you have a value of %d, and pi is %.3f\n",
name, x, pi);

sprintf(s, "Hello, %s, you have a value of %d, and pi is %.3f\n",
name, x, pi);

```

* It is *your* responsibility to ensure that the target string is large enough to hold the result (which will automatically be null terminated for you).
* It is generally not efficient to exactly calculate how big of a string you need
* Strategy: use a "large" buffer string, then format it, then compute how many characters it took, then create a permanent "non"-buffer string

* Java: `String.format` that uses `printf`-style placeholders and returns a `String`

## Java: Mutable Strings

* In Java, the `String` type is *immutable*
* However, you also have a mutable version: `StringBuilder` that can grow/shrink as needed, it can be changed
* In many applications (multithreaded applications) you WANT immutability
* The immutable version is somewhat more efficient; growing and shrinking of `StringBuilder` comes at a price
* Another version: `StringBuffer` that is both mutable and thread safe

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
* We may want to split a string up along some delimiter (space, tab, etc), called tokenization: splitting it up into individual *tokens*
* C provides a function called `strtok`, it take two arguments: the string you want to tokenize and a (list of) *delimiters*
* The first call to `strtok` specifies the delimiter, each subseqeuent call passes `NULL` for the string or it will start over at the beginning of the string

```c
char s[] = "Chris,Bourke,UNL,Omaha,NE";
//strtok returns a pointer to the next token, so we need to store that:
char *token = NULL;
//first argument is the string you want to tokenize
//second argument: the delimiter
//it returns a pointer to the "next' token
token = strtok(s, ",");
//the pointer returned, points to a part of the tokenized string
//with the delimiter *replaced* with a null termianting character
//print it:
printf("The first token is %s\n", token);

//next token: passing in NULL tells strtok to continue tokenizing
// where it left off
token = strtok(NULL, ",");`
printf("the second token is %s\n", token);

//how do we continue?
//When no further tokens are available, strtok returns NULL
while(token != NULL) {
  printf("token = %s\n", token);
  token = strtok(NULL, ",");
}
```

* In Java:there is a nice `.split()` method

```java
String s = "Chris,Bourke,UNL,Omaha,NE";
String tokens[] = s.split(",");
for(String str : tokens) {
  System.out.println(str);
}

```

### String Comparisons

* In both languages, the `==` operator is NOT going to work for string comparisons
* In both languages, `==` for strings would only be true if they were the same thing in memory (in C, the same memory address)
* Instead, you need to use a *comparator* function/method: given two elements, a, b it returns:
	* *something* negative if a < b
	* zero if a = b
	* *something* positive if a > b
* Strings and characters are lexicographically ordered according to the ASCII text table values
* IN C, the string comparator function is `strcmp(const char *s1, const char *s2);`
* Its return type is an integer, `strcmp` assumes that both are valid, null-terminated characters
* There is a case insensitive version: `strcasecmp(const char *s1, const char *s2);` (ignores upper/lower case)

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
* Write a string function to change the string's characters to uppercase letters
* Write a string function that returns a new copy of a string with all characters converted to uppercase
* **Extra challenge**: write a `split()`-style function in C (it takes a string and a delimiter and returns an array of strings (*with no memory leaks!*)

```text
















```
