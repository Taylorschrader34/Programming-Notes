package unl.cse.fall2017;

import java.util.Scanner;

public class Sound {

	public static void main(String args[]) {

		int decibel;
		/*
		Scanner s = new Scanner(System.in);
		System.out.println("Enter a decibel level: ");
		decibel = s.nextInt();
		 */
		
		if(args.length != 1) {
			System.out.println("Error: expecting a decibel level");
			System.exit(1);
		}
		int foo = 0;
		while(foo < 10) {
			System.out.println("foo");
		}
		decibel = Integer.parseInt(args[0]);
		
		//loop prints 10 .. 90
		for(int i=10; i<100; i+=10) {
		  System.out.printf("%d\n", i);
		}

		//compiler error:
		int i=100;
		System.out.printf("%d\n", i);
		
		if (decibel < 0) {
			System.out.print("Invalid input\n");
		} else if (decibel >= 0 && decibel <= 60) {
			System.out.print("Quiet\n");
		} else if (decibel <= 70) {
			System.out.print("Conversational\n");
		} else if (decibel <= 90) {
			System.out.print("Loud\n");
		} else if (decibel <= 110) {
			System.out.print("Very Loud\n");
		} else if (decibel <= 129) {
			System.out.print("Dangerous\n");
		} else if (decibel <= 194) {
			System.out.print("Very Dangerous\n");
		} else {
			System.out.print("Level exceeds parameters\n");
		}

	}
}
