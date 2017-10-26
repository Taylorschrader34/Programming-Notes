
#include<stdlib.h>
#include<math.h>

#include "roots.h"

QuadraticRootError getRoots(double a, double b, double c, double *root1, double *root2) {

  if(a == 0) {
    return DIVISION_BY_ZERO_ERROR;
  } else if(b*b - 4*a*c < 0) {
    return COMPLEX_ROOT_ERROR;
  } else if(root1 == NULL || root2 == NULL) { 
    return NULL_POINTER_ERROR;
  }

  *root1 = (-b + radical(a, b, c)) / (2*a);
  *root2 = (-b - radical(a, b, c)) / (2*a);
  return NO_ERROR;
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


