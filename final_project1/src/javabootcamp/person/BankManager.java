package javabootcamp.person;

import java.time.LocalDate;
import java.util.Scanner;


import javabootcamp.account.AccountType;

public class BankManager extends AccountOwner{
	
	protected AccountOwner [] usersToApprove = {};
	
	Scanner scannerManager = new Scanner(System.in);
	
	public BankManager(String firstName, String lastName, long phoneNumber, LocalDate birthDat, double monthlyIncome) {
		super(firstName, lastName, phoneNumber, birthDat, monthlyIncome);
	}
	
	public void addUsersToApprove(AccountOwner user) {
		AccountOwner [] updatedUsersToApprove = new AccountOwner[usersToApprove.length+1];
		for(int i=0; i<usersToApprove.length; i++)
			updatedUsersToApprove[i] = usersToApprove[i];
		updatedUsersToApprove[usersToApprove.length] = user;
		usersToApprove = updatedUsersToApprove;
	}
	
	public void setAndApproveAccount() {
		for(AccountOwner user : usersToApprove) {
			AccountType accountType = classifyAccountType(user.getMonthlyIncome());
			float interestRate = determineInterestRate(accountType);
			float operationFee  = determineOperationFee(accountType);
			user.createAccountByManager(accountType, interestRate, operationFee);	
		}
		emptyUsersToApprove();
		scannerManager.close();
	}
	
	private AccountType classifyAccountType(double income) {
		AccountType accountType;
		if(income<=5000)
			accountType = AccountType.BRONZE;
		else 
			if (income<=10000)
			accountType = AccountType.SILVER;
			else
				accountType = AccountType.GOLD;
			return accountType;
	}
	
	private float determineInterestRate(AccountType accountType) {
		float interestRateMin = accountType.getMinInterestRate();
		float interestRateMax = accountType.getMaxInterestRate();
		while(true) {
			System.out.println("Insert interest rate from the domain ["+interestRateMin+","+interestRateMax+")");
			float interestRate = scannerManager.nextFloat();
			if(interestRate>=interestRateMin && interestRate<interestRateMax)
				return interestRate;
			System.out.println("input not valid, try again");
		}
	}
	
	private float determineOperationFee(AccountType accountType) {
		float operationFeeMin = accountType.getMinOperationFee();
		float operationFeeMax = accountType.getMaxOperationFee();
		while(true) {
			System.out.println("Insert interest rate from the domain ["+operationFeeMin+","+operationFeeMax+")");
			float operationFee = scannerManager.nextFloat();
			if(operationFee>=operationFeeMin && operationFee<operationFeeMax)
				return operationFee;
			System.out.println("input not valid, try again");
		}
	}
	
	private void emptyUsersToApprove() {
		AccountOwner [] emptyUsersToApprove = {};
		usersToApprove = emptyUsersToApprove;
		System.out.println("array empty : " + usersToApprove.length);
	}
	

}
