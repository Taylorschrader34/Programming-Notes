import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class QuadraticRoots {

	public static double getRoot01(Double a, Double b, Double c) {
		
//		if(a == null || b == null || c == null) {
//			throw new NullPointerException("null!");
//		} else 
		if(b*b - 4*a*c < 0) {
			throw new ComplexRootException("complex roots!");
		} else if(a == 0) {
			throw new RuntimeException("cannot divide by zero!");
		}
		
		return (-b + Math.sqrt(b * b - 4 * a * c)) / (2 * a);
	}

	public static double getRoot02(Double a, Double b, Double c) {
		return (-b - Math.sqrt(b * b - 4 * a * c)) / (2 * a);
	}

	public static void main(String args[]) {
		double a = 0;
		double b = 20;
		double c = 10;

		try {
			double r1 = QuadraticRoots.getRoot01(null, b, c);
			double r2 = QuadraticRoots.getRoot02(a, b, c);
			
			System.out.println("roots: " + r1 + ", " + r2);
		} catch(ComplexRootException cre) {
			cre.printStackTrace();
			System.out.println("message = " + cre.getMessage());
			
		} catch(NullPointerException npe) {
			npe.printStackTrace();
			System.out.println("message = " + npe.getMessage());
			
		} catch(RuntimeException re) {
			re.printStackTrace();
			System.out.println("message = " + re.getMessage());
		} 
	}
}
