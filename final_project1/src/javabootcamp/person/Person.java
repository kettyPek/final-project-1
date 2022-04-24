package javabootcamp.person;

import java.time.LocalDate;

public abstract class Person { 
	
	protected String firstName;
	protected String lastName;
	protected long phoneNumber;
	protected LocalDate birthDat;
	
	public Person(String firstName, String lastName, long phoneNumber, LocalDate birthDat) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.birthDat = birthDat;
	}

	@Override
	public String toString() {
		return firstName + " " + lastName + ", phoneNumber: " + phoneNumber
				+ ", birth date: " + birthDat ;
	}


	
	
	
	

}
