
typedef enum {
  NO_ERROR,
  DIVISION_BY_ZERO_ERROR,
  COMPLEX_ROOT_ERROR,
  NULL_POINTER_ERROR,
} QuadraticRootError;

/**
 * This function computes both roots of a quadratic
 * equation with the given coefficients, placing each
 * result in the given variables passed by reference
 */
QuadraticRootError getRoots(double a, double b, double c, double *root1, double *root2);

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


