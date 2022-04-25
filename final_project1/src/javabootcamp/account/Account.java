package javabootcamp.account;

import javabootcamp.actions.ActivityData;

public class Account {
	//TODO enter id field
	protected double balance;
	protected AccountType accountType; 
	protected ActivityData [] activityLog;
	
	public Account(double balance,AccountType accountType) {
		this.balance = balance;
		this.accountType = accountType;
	}
	
	public double getBalance() {
		return balance;
	}
	
	public void deposit(double depositFunds) {
		this.balance += depositFunds;
	}
	
	
	

}
