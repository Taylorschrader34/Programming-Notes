package unl.cse;

import java.time.LocalDate;

public class Demo {

	public static void main(String args[]) {
		Student s = new Student(12345678, "Chris", "Bourke", 3.0, LocalDate.of(1978, 7, 9));

		// goal: print the student out:
		System.out.println(s.getNuid());
		System.out.println(s);
		System.out.println(s.toString());
		
		//goal: change my GPA to 3.5
		Student t = new Student(s.getNuid(), s.getFirstName(), s.getLastName(), 3.5, s.getDateOfBirth());
		System.out.println(t);
	}
}
