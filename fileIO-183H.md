
# File I/O

* A *file* is a unit of stored memory, usually on disk
* A file can also be... directories, buffers (standard input/output are files), a socket could be a file, a program is a file
* You can write to and read from a file
* The basic steps to follow are:
	1. Open the file
	2. Process the file
	3. Close the file

## File I/O in C

* There is a special `FILE *` pointer type built into the standard library
* You can open a file using `fopen()`
* It takes two arguments: the path of the file you want to open and the "mode"

```c
//open the file data.txt for reading
FILE *f = fopen("data.txt", "r");

//open the file data.txt for writing:
FILE *f = fopen("data.txt", "w");

//alternatively, you can use relative paths:
//opens data.txt which is expected to be two directries upward
FILE *f = fopen("../../data.txt", "r");

//absolute paths:
FILE *f = fopen("/etc/shadow", "r");
```

* Upon failure to open a file, `fopen` returns `NULL` which you can check for and handle the error
* Errors can happen when you don't have read/write permissions or possibly when the file does not exist!
* If you open a file for writing that does not exist, it will be created for you

### File Output

* There are at least 2 dozen ways to do file output
* The simplest and easiest: use `fprintf`, it uses the same formatting as `printf`, but it takes an extra argument (first): the file you want to write to

```c
if(f == NULL) {
    printf("error: file cannot be opened!\n");
  } else {
    printf("wow, go wild on this system!\n");

    fprintf(f, "Hello, how are you Now??\n");
    fprintf(f, "Pi is %.10f\n", M_PI);
    fprintf(f, "Hello, %s\n", name);
    //close here?
  }

```

* You can then use `fclose()` to close the file when you are done reading/writing to it

### File Input

* Many ways of reading from a file are dangerous
* Two functions that are safe: `fgetc`,  `fgets`
* `fgetc` gets a single character (the next one that is) from a file
* `fgets` gets up to a single line, but limited to the number of bytes you specify
* `fgets` reads an entire string from a file, `*fgets(char *s, int size, FILE *stream);`
	* The first argument is the string you want to read the file contents into, it is *your* responsibility to ensure it is large enough
	* The second argument is the maximum number of bytes/characters to read from the file MINUS ONE to accommodate the null terminating character which it includes for you
	* The last argument is the file you want to read from
  * Note: it reads at most `size` bytes, but it stops at the first endline character

```c
//read the first 200 characters from the file:
  int i;
  char c;
  for(i=0; i<200; i++) {
    c = fgetc(f);
    printf("c = %c\n", c);
  }
```

```c
  //read the first 5 lines of the file
  int i;
  char buffer[200];
  for(i=0; i<5; i++) {
    fgets(buffer, 200, f);
    //get rid of the endline character:
    buffer[strlen(buffer)-1] = '\0';
    printf("line = %s\n", buffer);
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

## Exercise: Build a Finger Utility

* Unix has a utility called `finger` that allows you to pull up information on any user
* You should have a user specify a username (as a command line argument), open the `/etc/passwd` file, read line by line until you get to that particular user
* Tokenize that user's line and print out each piece of information (formatting is up to you).
* C or Java, but then do the other one for practice







```text














```




