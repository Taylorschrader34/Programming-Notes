
Encapsulation
=============
Structures & Objects
--------------------

* Built-in primitive types (`int, double, char`) are limiting: not everything is a number or character
* Real-world entities are made up of multiple aspects (data)
* Example: Lab 10: sorting teams
    * Kept a lot of different, but related data in different arrays
    * Every swap required a swap of every array
    * Very easy to screw up the book keeping
    * Very inconvenient to treat related pieces of data as unrelated
* Encapsulation:
	1. The grouping of data
	2. The protection of data
	3. The grouping of functionality that acts on that data

## Encapsulation in C

* C only provides *weak* encapsulation: the first item, the grouping of data
* C provides weak encapsulation through *structures* (or structs for short)
* Sytnax:

```c
typedef struct {
  int year;
  int month;
  int day;
} Date;

typedef struct {
  int nuid;
  char *firstName;
  char *lastName;
  Date dateOfBirth;
  double gpa;
} Student;

```

* Alternatively: ISO8601 standard lets you define universal time with a single string
* Alternatively: count the number of milliseconds since Jan 1, 1970 (Unix Epoch)
* In general, the order doesn't matter
* Typically, structures are declared in a header file (`.h`) where all the function prototypes that operate on the structures go as well
* Modern convention: use `UpperCamelCasing` for structure names, `lowerCamelCasing` for member fields variables, etc.
* Structures can contain other structures as members: this is called *composition*
* However, the order you declare them then matters
* In general, all fucntions that use the structure should be included in a source file /header with the same name: `student.h, student.c`

## Using Structures

* once declared, you can use a structure like any other variable type
* You can use the dot operator to access individual fields or member variables of a structure

```c
//declare a student:
Student s;
//set the nuid of s:
s.nuid = 12345678;
s.firstName = (char *) malloc(6 * sizeof(char));
strcpy(s.firstName, "Chris");
s.lastName = (char *) malloc(7 * sizeof(char));
strcpy(s.lastName, "Bourke");
s.gpa = 4.0;
s.dateOfBirth.year = 1978;
s.dateOfBirth.month = 7;
s.dateOfBirth.day = 9;

```

## Factory Functions

* "Factory" functions allow you to easily create new structures
* When using pointers to structures, you use the arrow operator, `->`
```c
Student * createStudent(int nuid,
                      char * firstName,
                      char * lastName,
                      double gpa,
                      Date dateOfBirth) {
  Student *s = (Student *) malloc(sizeof(Student) * 1);
  //(*s).nuid = nuid;
  s->nuid = nuid;
  s->gpa = gpa;
  //TODO: do some error checking here and maybe elsewhere
  s->dateOfBirth.year = dateOfBirth.year;
  s->dateOfBirth.month = dateOfBirth.month;
  s->dateOfBirth.day = dateOfBirth.day;

  //TODO: make a deep copy string function
  s->firstName = (char *) malloc( (strlen(firstName)+1) * sizeof(char));
  strcpy(s->firstName, firstName);
  s->lastName = (char *) malloc( (strlen(lastName)+1) * sizeof(char));
  strcpy(s->lastName, lastName);

  //wrong: this is a shallow copy s->firstName = firstName;

  return s;
}

...

Date dayOfBirth;
dayOfBirth.year = 1978;
dayOfBirth.month = 7;
dayOfBirth.day = 9;

Student *me = createStudent(12345678, "Chris", "Bourke", 4.0, dayOfBirth);

Student *justin = createStudent(87654321, "Justin", "Verlander", 4.0, dayOfBirth);


```

### Using Structures in Arrays

* You can create static arrays of structures, but its better to create dynamic arrays

```c
int n = 10;
//reminder: how do we create an array of 10 integers
int *arr = (int *) malloc(sizeof(int) * n);


//create an array for 10 students:
Student *roster = (Student *) malloc(sizeof(Student) * n);

roster[0].nuid = 1234;

//set the second student to a call to the factory function:
roster[1] = *createStudent(12345678, "Chris",
  "Bourke", 3.9, dayOfBirth);
//may have a memory leak!  Check it out!
```

* Alternatively: you could create instead an array of pointers

* A pointer takes much less memory, the structures do not need to be stored contiguously, only their pointers

```c

Student **roster = (Student **) malloc(sizeof(Student *) * n);

roster[0] = createStudent(12345678, "Chris",
  "Bourke", 3.9, dayOfBirth);
roster[1] = createStudent(87654321, "Justin",
  "Verlander", 4.0, TODO);

//swap me and Justin:
Student *temp = roster[0];
roster[0] = roster[1];
roster[1] = temp;

```

### Passing Structures to functions

* It is generally always best to pass by reference

```c
/**
 * Prints the given student structure to the standard output
 */
void printStudent(const Student *s) {
/*
  printf("%s, %s (%08d), GPA = %.2f\n",
    s->lastName, s->firstName, s->nuid, s->gpa);
*/

  char *str = studentToString(s);
  printf("%s\n", str);
  free(str);

  return;
}

/**
 * Takes a student structure (by reference) and returns a
 * string representation of it.
 */
char * studentToString(const Student *s) {

  char temp[200];
  //format the student into temp
  sprintf(temp, "%s, %s (%08d), GPA = %.2f",
    s->lastName, s->firstName, s->nuid, s->gpa);

  //create a new dynamic string to hold the exact contents of temp
  char *result = (char *) malloc( (strlen(temp) + 1) * sizeof(char));
  strcpy(result, temp);
  return result;
}

/**
 *  Prints the entire given roster of students, one to a line
 */
void printRoster(Student *roster, int size) {

  int i;
  for(i=0; i<size; i++) {
    printStudent(&roster[i]);
  }

}

//OR:
/**
 *  Prints the entire given roster of students, one to a line
 */
void printRoster(Student **roster, int size) {

  int i;
  for(i=0; i<size; i++) {
    printStudent(roster[i]);
  }
  return;

}

```

## Encapsulation in Java: Class-based Objects

* An object is an entity with identity, state, and behavior
    * Identity: a way to distinguish one object from another
    * State: an object has member variables whose scope is to that *instance* of an object
    * Behavior: methods in an object that act on that data
* Java is an object-oriented programming language (OOP) and supports objects through *classes* (blue prints for creating instances of objects)
* Example: recreate a student class
    * Use `UpperCamelCasing` for class names
    * Use `lowerCamelCasing` for member variable names
    * Member variables are declared by not using the `static` keyword
* To create a new instance (or "instantiate") use the `new` keyword, which invokes a *constructor* method
    * By default, a no-argument constructor is provided for you in Java
    * Sets default values to all member variables: 0 for numbers, `null` for objects,
    * Its best practice to always reuse preexisting objects: `String, LocalDate`, this is known as *composition*

## Visibility

* You can achieve #2 of encapsulation (protection of data) using visibility keywords
    * `private`: only the class itself can "see" the variable (it can access/change the variable's value)
    * `protected`: only the class and its subclass(es) can see the variable
    * The absence of any keyword makes the variable "package protected": any class in the same package can see the variable
    * `public`: ANY piece of code can see the variable
* In general, you should prefer the most conservative visibility unless you have a Very Good Reason to do otherwise
* You should make all of you member variables `private`
* You still need to initialize member variables and access them once created:
    * Create your own constructor method(s)
    * You can use "getters" to access member variables

* With constructors:
    * They can be `public` or `private` but generally you make them `public`
    * Constructors have the same name as the class, but no return type
    * The `this` keyword can be used to refer to the class itself within class ("Open Recursion"), and can be used to invoke other constructors
    * You can generate code for constructors easily using your IDE
    * You invoke a constructor using the `new` keyword

* You can also use getters/setters to enable the outside world access to your variables
    * In general, you should prefer immutable objects (only getters, no setters)
    * To enable new objects with different values you can create *copy constructors*
    * IDE tricK: you can generate getters/setter code with the click of a button

* Recall what *abstraction* is:
	* Procedural Abstraction: what does the `sqrt()` function do? How does it do it?
	* Object abstraction: the representation of an object is internal to the object and irrelevant to the outside world.
 	* The outside world merely interacts with an object by calling its `public` methods








```text

















```

