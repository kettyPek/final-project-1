package javabootcamp.account;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javabootcamp.actions.ActivityData;
import javabootcamp.actions.ActivityName;
/**
 * Represents bank account 
 * 
 * @author ketty
 *
 */
public class Account {
	protected final long ACCOUNT_NUMBER;
	protected static int accountCounter;
	protected double balance;
	protected AccountType accountType; 
	protected ActivityData [] activityLog = {};
	protected  float interestRate;
	protected  float operationFee;
	
	static {
		accountCounter = 0;
	}
	
	public Account(AccountType accountType,float intersetRate, float operationFee) {
		balance = 0;
		this.accountType = accountType;
		this.ACCOUNT_NUMBER = accountCounter;
		setInterestRate(intersetRate);
		setOperationFee(operationFee);
		accountCounter++;
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
	
	public long getAccountNumber() {
		return ACCOUNT_NUMBER;
	}
	
	/**
	 * Adds new activity to activityLog array
	 * @param activity - new activity to add
	 */
	private void addActivityToActivityLog(ActivityData activity) {
		ActivityData [] updatedActivityLog = new ActivityData[activityLog.length+1];
		for(int i=0; i<activityLog.length; i++)
			updatedActivityLog[i] = activityLog[i];
		updatedActivityLog[activityLog.length] = activity;
		activityLog = updatedActivityLog;
	}
	
	/**
	 * Produce activity report that starts from given date 
	 * @param startDate - date to display the report from
	 */
	public void produceActivityReportFromDate(LocalDate startDate) {
		int index = findIndexOfStartDate(startDate);
		if(index==-1)
			System.out.println("No activities from this date.");
		else 
			displayActivitiesFromIndex(index);	
	}
	
	/**
	 * Finds log in activityLog array that starts from given date 
	 * @param startDate - date to display the activity log from
	 * @return first index of a log that starts from given date, retrun -1 if there is no appropriate log
	 */
	private int findIndexOfStartDate(LocalDate startDate) {
		for(int i=0; i<activityLog.length;i++)
			if(activityLog[i].getTimeStemp().toLocalDate().compareTo(startDate)>=0)
				return i;
		return -1;
	}
	
	/**
	 * Display activities from activityLog array which starts from given index
	 * @param index - index in activityLog 
	 */
	private void displayActivitiesFromIndex(int index) {
		for(int i=index; i<activityLog.length; i++)
			System.out.println(activityLog[i].toString());
	}
	
	/**
	 * Add amount to balance
	 * @param amountToAdd
	 */
	public void addToBalance(double amountToAdd) {
		balance += amountToAdd;
	}
	
	/**
	 * Subtract amount from balance
	 * @param amountToSubtract
	 */
	public void subtractFromBalance(double amountToSubtract) {
		balance -= amountToSubtract;
	}
	
	/**
	 * Creates new activity and adds activity to activityLog array
	 * @param activityName - enum of activity type
	 * @param balanceChange - change in balance after the activity
	 * @param additionalInfo - additional info from the user
	 */
	public void addNewActivity(ActivityName activityName,double balanceChange,String additionalInfo) {
		ActivityData activity = new ActivityData(activityName,balanceChange,additionalInfo);
		addActivityToActivityLog(activity);	
	}
	
	
	
	
	
		
	
	
	
	

}
