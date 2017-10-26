#include<stdlib.h>
#include<stdio.h>

int main(int argc, char **argv) {

  int decibel;

  printf("Please enter a decibel level: ");
  scanf("%d", &decibel);

  if(decibel < 0) {
    printf("Invalid input\n");
  } else if(decibel >= 0 && decibel <= 60) {
    printf("Quiet\n");
  } else if(decibel <= 70) {
    printf("Conversational\n");
  } else if(decibel <= 90) {
    printf("Loud\n");
  } else if(decibel <= 110) {
    printf("Very Loud\n");
  } else if(decibel <= 129) {
    printf("Dangerous\n");
  } else if(decibel <= 194) {
    printf("Very Dangerous\n");
  } else { 
    printf("Level exceeds parameters\n");
  }


  return 0;
}
