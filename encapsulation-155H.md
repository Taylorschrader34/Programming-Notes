
Encapsulation
=============
Structures & Objects
--------------------

* Built-in primitive types (`int, double, char`) are limiting: not everything is a number or character
* Real-world entities are made up of multiple aspects (data)
* Example: Lab 10: sorting teams
* Encapsulation:
	1. The grouping of data
	2. The protection of data
	3. The grouping of functionality that acts on that data

## Encapsulation in C

* C only provides *weak* encapsulation, that is, #1 the grouping of data
* C provides weak encapsulation through *structures* (struct for short)
* Syntax:

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

* Alternatively: you could use ISO8601 standard strings or a single number (milliseconds since the unix epoch)
* Structures can contain other structures as *members*, this is known as *composition*
* The order you declare strutures matters: you cannot use a structure before you define it
* Typically structures are declared in header files (`.h`) along with any functions that involve (take as arguments or return types) the structure
* Modern conventions: use `UpperCamelCasing` for structure names, `lowerCamelCasing` for fields or "members" of a structure
* In general, all fucntions that use the structure should be included in a source file /header with the same name: `student.h, student.c`

## Using Structures

* once declared, you can use a structure like any other variable type
* You can use the "dot operator" to access individual pieces of data

```c
//delcare a student:
Student s;
//set the nuid
s.nuid = 12345678;
//set the first name:
//not allowed: s.firstName = "Chris";
//first setup memory:
s.firstName = (char *) malloc( (5+1) * sizeof(char));
//copy the value
strcpy(s.firstName, "Chris");
s.lastName = (char *) malloc(7 * sizeof(char));
strcpy(s.lastName, "Bourke");
s.gpa = 3.9;
s.dateOfBirth.year = 1978;
s.dateOfBirth.month = 7;
s.dateOfBirth.day = 9;
```

## Factory Functions

* Write a "factory" function that allows you to easily create new structures

```c
Student * createStudent(int nuid,
                        char *firstName,
                        char *lastName,
                        double gpa,
                        Date dateOfBirth) {
  Student *s = (Student *) malloc(sizeof(Student) * 1);

  s->nuid = nuid;
  s->gpa = gpa;

  //TODO: refactor to use a deep copy string function
  //  also, write that deep copy string function

  s->firstName =
    (char *) malloc(sizeof(char) * (strlen(firstName) + 1));
  strcpy(s->firstName, firstName);

  s->lastName =
    (char *) malloc(sizeof(char) * (strlen(lastName) + 1));
  strcpy(s->lastName, lastName);

  s->dateOfBirth.year = dateOfBirth.year;
  s->dateOfBirth.month = dateOfBirth.month;
  s->dateOfBirth.day = dateOfBirth.day;

  return s;
}
```

```c
Date dayOfBirth;
dayOfBirth.year = 1978;
dayOfBirth.month = 7;
dayOfBirth.day = 9;

Student *s = createStudent(12345678, "Chris",
  "Bourke", 3.9, dayOfBirth);

Student *justin = createStudent(87654321, "Justin",
  "Verlander", 4.0, TODO);
```

### Using Structures in Arrays

* You can create static arrays of structures, but its better to create dynamic arrays


```c
int n = 10;
//how would you create an array of 10 integers?
int *arr = (int *) malloc(sizeof(int) * n);

//create an array of 10 students:
Student *roster = (Student *) malloc(sizeof(Student) * n);

roster[0].nuid = 1234;

//you can dereference from a returned pointer to
//"copy" the data byte for byte:
Student *s = createStudent(12345678, "Chris",
  "Bourke", 3.9, dayOfBirth);
//store s in the second slot:
roster[1] = *s;

```

* Alternatively: you can create an array of student pointers
* A pointer takes much less memory, the structures do not need to be stored contiguously, only their pointers

```c
Student **roster = (Student *) malloc(n * sizeof(Student *));
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

* Its always best to pass by reference

```c
/**
 * Prints the given Student structure to the standard
 * output
 */
void printStudent(const Student *s) {
  char *str = studentToString(s);
  printf("%s\n", str);
  free(str);
}

/**
 * This function takaes a student (by reference) and
 * returns a string representation of it
 */
char *studentToString(const Student *s) {

  char temp[100];
  sprintf(temp, "%s, %s (%08d), GPA = %.2f",
    s->lastName, s->firstName, s->nuid, s->gpa);

  char *str = (char *) malloc(sizeof(char) * (strlen(temp) + 1));
  strcpy(str, temp);

  return str;
}

void printRoster(Student *s, int n) {

  int i;
  for(i=0; i<n; i++) {
    printStudent(&s[i]);
  }
  return;
}

```

## Encapsulation in Java: Class-Based Objects

* Java is an object-oriented programming language and supports "objects" through the use of classes (it is a class-based OOP language)
* An *object* is an entity with identity, state, and behavior
	* Identity: a class provides a blue-print for how to create *instances* of an object, each *instance* has identity (it is distinct from other instances)
  * State is sipmly the *member variables* of a class
  * Behavior: its a collection of *member methods* that act on that data
* Example: create a student class/object in Java

### Basics

* You use the dot operator to access individual member variables
* You can use the `new` keyword to create new *instances* of a class
* The omission of the `static` keyword makes a variable a member variable (every instance owns its own variable)
* Design considerations: use composition when appropriate (a class can own instances of other classes)
* When designing a class, ask first: does a standard or library class already exist that can be used?

### Visibility

* Member variables (and methods) can be made:
    * `private`: only instances of the class can "see" and thus access/modify the variable
    * the omission of a keyword makes it "package protected": other classes in the same package can "see" the variable
    * `protected`: only instances and subclasses can see the variable
    * `public`: every piece of code can "see" the variable
* In general, you should make all of your member variables `private` unless there is a Very Good Reason to not do so:
    * Allows you to make your classes immutable which gives you thread safety
    * Allows you to control side effects: data validation, limit the scope of changes to a class so that testing is simplified: with `public` variables, anything can change it, thus you need to test for the eventuality that anything will change it


### Constructor methods

* Constructor methods have the same name as the class, no return type, but may take any number of arguments
* Multiple constructors can be defined
* Constructor methods can be invoked (called) using the `new` keyword
* If you do not define a constructor, a default, no-argument one will be provided for you, it simply sets all values to default values (0, 0.0, `null`, etc.)
* The `this` keyword is used to refer to an object's own variables and methods (referred to as "open recursion")
* If you define a constructor, the default no-argument constructor goes away

## Misc

* The `toString()` method can be overridden/defined for conveniently printing an object in a human-readable format
* Recall the `static` keyword: it makes a variable or method part of the class rather than instances of the class, it is best to use the `final` keyword to make it a constant (so nothing can change it)

* One object may contain other objects, this is known as *composition*

* Recall what *abstraction* is:
	* Procedural Abstraction: what does the `sqrt()` function do? How does it do it?
	* Object abstraction: the representation of an object is internal to the object and irrelevant to the outside world.
 	* The outside world merely interacts with an object by calling its `public` methods

* Copy constructors: you can define a constructor that takes an object and constructs a new copy of it.


```text

















```

