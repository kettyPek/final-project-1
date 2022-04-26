package javabootcamp.account;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javabootcamp.actions.ActivityData;
import javabootcamp.actions.ActivityName;

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
	
 	//
	/**
	 * Add new activity to activityLog array
	 * @param activity - new activity to add
	 */
	private void addActivityToActivityLog(ActivityData activity) {
		ActivityData [] updatedActivityLog = new ActivityData[activityLog.length+1];
		for(int i=0; i<activityLog.length; i++)
			updatedActivityLog[i] = activityLog[i];
		updatedActivityLog[activityLog.length] = activity;
		activityLog = updatedActivityLog;
	}
	
	public void produceActivityReportFromDate(LocalDate startDate) {
		int index = findIndexOfStartDate(startDate);
		if(index==-1)
			System.out.println("No activities from this date.");
		else 
			displayActivitiesFromIndex(index);	
	}
	
	private int findIndexOfStartDate(LocalDate startDate) {
		for(int i=0; i<activityLog.length;i++)
			if(activityLog[i].getTimeStemp().toLocalDate().equals(startDate))
				return i;
		return -1;
	}
	
	private void displayActivitiesFromIndex(int index) {
		for(int i=index; i<activityLog.length; i++)
			System.out.println(activityLog[i].toString());
	}
	
	public void addToBalance(double amountToAdd) {
		balance += amountToAdd;
	}
	
	public void subtractFromBalance(double amountToSubtract) {
		balance -= amountToSubtract;
	}
	
	public void addNewActivity(ActivityName activityName,double balanceChange,String additionalInfo) {
		ActivityData activity = new ActivityData(activityName,balanceChange,additionalInfo);
		addActivityToActivityLog(activity);	
	}
	
	
	
		
	
	
	
	

}
