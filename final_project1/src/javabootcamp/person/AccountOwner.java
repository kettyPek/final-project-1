package javabootcamp.person;

import java.time.LocalDate;

import javabootcamp.account.Account;
import javabootcamp.credentials.LogInCredentials;

public class AccountOwner extends Person{
	
	protected Account account = null;
	protected double monthlyIncome;
	protected LogInCredentials credentials;
	
	public AccountOwner(String firstName, String lastName, long phoneNumber, LocalDate birthDat, double monthlyIncome) {
		super(firstName, lastName, phoneNumber, birthDat);
		this.monthlyIncome = monthlyIncome;
	}
	
	public LogInCredentials getCredentials() {
		return credentials;
	}
	
	

}
