
# File I/O

* A *file* is a unit of stored memory, usually on disk
* A file can also be... directories, buffers (standard input/output are files), a socket could be a file, a program is a file
* You can write to and read from a file
* Files may be plaintext or binary (or plaintext but not intended for human consumption: EDI, XML, JSON, base-64 encoding)
* The basic steps to follow are:
	1. Open the file
	2. Process the file
	3. Close the file

## File I/O in C

* There is a special `FILE *` pointer type built into the standard library
* You can open a file using `fopen()`
* It takes two arguments: the path of the file you want to open and the "mode"

```c
//open a file data.txt for reading ie an input file
FILE *f = fopen("data.txt", "r");

//open a file data.txt for writing ie an output file
FILE *f = fopen("data.txt", "w");

//you can also use relative paths
FILE *f = fopen("../../data.txt", "r");

//absolute paths are also allowed:
FILE *f = fopen("/etc/shadow", "r");

```

* If `fopen` fails, it returns `NULL`
* If you open a file for writing and it does not exist, it will be created for you *if* you have permission to create files in that directory
* Once you are done with the file, use `fclose()` to close it, failure to do so *may* result in corrupted files

### File Output

* There are at least 2 dozen ways to do file output
* The simplest and easiest: use `fprintf`, it uses the same formatting as `printf`, but it takes an extra argument (first): the file you want to write to


### File Input

* Many ways of reading from a file are dangerous
* We need a way to limit the amount of data that is read so that we don't overflow any buffers (ie strings)
* Two functions that are "safe": `fgetc` and `fgets`
* `fgetc` gets a single character (the next one that is) from a file
* `fgets` gets up to a single line, but limited to the number of bytes you specify
* `fgets` reads an entire string from a file, `*fgets(char *s, int size, FILE *stream);`
	* The first argument is the string you want to read the file contents into, it is *your* responsibility to ensure it is large enough
	* The second argument is the maximum number of bytes/characters to read from the file MINUS ONE to accommodate the null terminating character which it includes for you
	* The last argument is the file you want to read from
  * Note: it reads at most `size` bytes, but it stops at the first endline character

```c
//read the first 50 characters from the file:
  int i;
  char c;
  for(i=0; i<50; i++) {
    //read a character
    c = fgetc(f);
    //print it out
    printf("character is %c\n", c);
  }
```

```c
//read all the lines from the file:
  int numLines = 0;
  char buffer[200];
  char *s = fgets(buffer, 200, f);
  while(s != NULL) {
    numLines++;
    //read a line
    //note: fgets preserves the endline character if it is read
    //note: fgets returns a string pointer (char *) which is equal to
    //      the buffer it reads into upon success, otherwise, upon failure
    //      or the end of a file, it returns... NULL

    //it is common to "chomp" or "trim" any trailing or leading whitespace
    buffer[strlen(buffer)-1] = '\0';
    //print it out
    printf("line is %s\n", buffer);
    s = fgets(buffer, 200, f);

  }
```

* `fgets` retains the endline characters
* You can always write code to "chomp" trailing whitespace
* How do you know when you are done reading from a file?
* `fgets` returns a pointer which is `NULL` when it has reached the EOF
* `fgetc` returns `EOF` when it gets to the end of a file

### Binary Data in C

* C provides both `fread` for reading binary data and `fwrite`
* `size_t fwrite(const void *restrict ptr, size_t size, size_t nitems, FILE *restrict stream);`

## File IO in Java


* Java provides a `File` class

```java
File f = new File("path/to/some/file.txt");

Scanner s = new Scanner(f);

int x = s.nextInt();
String line = s.nextLine();

//closing...
s.close();

//read every line:
while(s.hasNextLine()) {
  String line = s.nextLine();
}

```

```java

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class ReadPasswd {

    public static void main(String args[]) {
        try {
            File f = new File("/etc/passwd");
            Scanner s = new Scanner(f);
            while(s.hasNextLine()) {
                String line = s.nextLine();
                System.out.println(line);
            }
            s.close();
        } catch(FileNotFoundException fnfe) {
            throw new RuntimeException(fnfe);
        }
    }

}
```

## File Output in Java

* There are at least a half dozen ways to do file output
* We'll focus on the simplest and easiest
* You can use `PrintWriter` to do file output

```java
File f = new File("myOutputFile.txt");
PrintWriter pw = new PrintWriter(f);
pw.println("hello world!");
pw.print("no endline!");
int x = 10;
pw.printf("%d\n", x);
pw.println("x = " + x);
```

```text














```




