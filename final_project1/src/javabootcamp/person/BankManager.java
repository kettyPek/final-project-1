package javabootcamp.person;

import java.time.LocalDate;

public class BankManager extends AccountOwner{
	
	protected AccountOwner [] usersToApprove;
	
	public BankManager(String firstName, String lastName, long phoneNumber, LocalDate birthDat, double monthlyIncome) {
		super(firstName, lastName, phoneNumber, birthDat, monthlyIncome);
	}

}
