package javabootcamp.person;

import java.time.LocalDate;

import javabootcamp.account.Account;
import javabootcamp.credentials.LogInCredentials;

public class AccountOwner extends Person{
	
	protected Account account = null;
	protected double monthlyIncome;
	protected LogInCredentials credentials = null;
	
	public AccountOwner(String firstName, String lastName, long phoneNumber, LocalDate birthDat, double monthlyIncome) {
		super(firstName, lastName, phoneNumber, birthDat);
		this.monthlyIncome = monthlyIncome;
	}
	
	public void setCredentials(String username, String password) {
		credentials = new LogInCredentials(username, password);
	}
	
	public LogInCredentials getCredentials() {
		return credentials;
	}
	
	public long getPhoneNumber() {
		return super.phoneNumber;
	}
	
	public void makeDeposit(double depositFunds) {
		account.deposit(depositFunds);
		System.out.println("Successful deposit");
	}
	
}
