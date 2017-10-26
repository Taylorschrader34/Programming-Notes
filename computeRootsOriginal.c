#include<stdio.h>
#include<stdlib.h>
#include<math.h>

/**
 * This function computes the radical of a quadratic
 * equation with the given coefficients
 */
double radical(double a, double b, double c);

/**
 * This funciton computes the first root of a quadratic
 * equation with the given coefficients
 */
double getRoot01(double a, double b, double c);

/**
 * This function comptues the second root of a quadratic
 * equation with the given coefficients                                             */
double getRoot02(double a, double b, double c);


int main(int argc, char **argv) {

  if(argc != 4) {
    printf("Error: expected 3 coefficients\n");
    exit(1);
  }

  double a = atof(argv[1]);
  double b = atof(argv[2]);
  double c = atof(argv[3]);

  double root1 = getRoot01(a, b, c); //(-b + sqrt(b*b - 4*a*c)) / (2*a);
  double root2 = getRoot02(a, b, c); //(-b - sqrt(b*b - 4*a*c)) / (2*a);

  printf("Roots of %f x^2 + %f x + %f are %f and %f\n", a, b, c, root1, root2);

  return 0;
}

double radical(double a, double b, double c) {
  return sqrt(b*b - 4*a*c);
}

double getRoot01(double a, double b, double c) {
  return (-b + radical(a, b, c)) / (2*a);
}

double getRoot02(double a, double b, double c) {
  return (-b - radical(a, b, c)) / (2*a);
}


