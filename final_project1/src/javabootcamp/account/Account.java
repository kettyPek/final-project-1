package javabootcamp.account;

import javabootcamp.actions.ActivityData;

public class Account {
	//TODO enter id field
	protected double balance;
	protected AccountType accountType; 
	protected ActivityData [] activityLog;
	protected  float interestRate;
	protected  float operationFee;
	
	public Account(AccountType accountType,float intersetRate, float operationFee) {
		balance = 0;
		this.accountType = accountType;
		setInterestRate(intersetRate);
		setOperationFee(operationFee);
	}
	
	public void setInterestRate(float interestRate) {
		this.interestRate = interestRate;
	}
	
	public void setOperationFee(float operationFee) {
		this.operationFee = operationFee;
	}
	
	public float getInterestRate() {
		return interestRate;
	}
	
	public float getOperationFee() {
		return operationFee;
	}
	
	public double getBalance() {
		return balance;
	}
	
	public AccountType getAccountType() {
		return accountType;
	}
		
	public void deposit(double depositFunds) {
		this.balance += depositFunds;
	}
	
	
	
	

}
