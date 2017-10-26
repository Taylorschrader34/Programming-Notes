#include<stdio.h>
#include<stdlib.h>

#include "roots.h"

int main(int argc, char **argv) {

  if(argc != 4) {
    printf("Error: expected 3 coefficients\n");
    exit(1);
  }

  double a = atof(argv[1]);
  double b = atof(argv[2]);
  double c = atof(argv[3]);

  //TODO: validate the input

  double root1, root2;  

    //double root1 = getRoot01(a, b, c); //(-b + sqrt(b*b - 4*a*c)) / (2*a);
    //double root2 = getRoot02(a, b, c); //(-b - sqrt(b*b - 4*a*c)) / (2*a);

  QuadraticRootError errorCode = getRoots(a, b, c, &root1, NULL);

  if(errorCode == DIVISION_BY_ZERO_ERROR) {
    printf("invalid input: a cannot be zero\n");
    exit(1); 
  } else if(errorCode == COMPLEX_ROOT_ERROR) {
    printf("invalid input: complex roots\n");
    exit(1);
  } else if(errorCode == NULL_POINTER_ERROR) {
    printf("null pointer!\n");
    exit(1);
  }

  printf("Roots of %f x^2 + %f x + %f are %f and %f\n", a, b, c, root1, root2);

  return 0;
}
