package unl.cse;

import java.time.LocalDate;

public class Student {

	private int nuid;
	private String firstName;
	private String lastName;
	private double gpa;
	private LocalDate dateOfBirth;
	
	//copy constructor
	public Student(Student s) {
		this(s.nuid, s.firstName, s.lastName, s.gpa, s.dateOfBirth);
	}
	
	public Student(Student s, double gpa) {
		this(s.nuid, s.firstName, s.lastName, gpa, s.dateOfBirth);		
	}

	public Student(int nuid, String firstName, String lastName, double gpa, LocalDate dateOfBirth) {
		this.nuid = nuid;
		this.firstName = firstName;
		this.lastName = lastName;
		if(gpa < 0.0 || gpa > 4.0) {
			throw new IllegalArgumentException("GPA is out of range");
		}
		this.gpa = gpa;
		this.dateOfBirth = dateOfBirth;
	}
	
	public int getNuid() {
		return this.nuid;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public double getGpa() {
		return gpa;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	
	public String toString() {
		return String.format("%s, %s (%03d), GPA = %.2f", this.lastName, this.firstName, this.nuid, this.gpa);
	}
	
	
	
}
