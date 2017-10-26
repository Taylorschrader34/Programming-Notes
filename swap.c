#include<stdio.h>
#include<stdlib.h>

void swap(int a, int b);
void swapByRef(int *a, int *b);

int main(int argc, char **argv) {

  int x = 10;
  int y = 20;
  printf("x, y = %d, %d\n", x, y);
  swap(x, y);
  printf("x, y = %d, %d\n", x, y);

  swapByRef(&x, &y);
  printf("x, y = %d, %d\n", x, y);


  return 0;

}

void swapByRef(int *a, int *b) {
  int temp = *a;
  *a = *b;
  *b = temp;
  return;
}

void swap(int a, int b) {
  int temp = a;
  a = b;
  b = temp;
  return;
}

