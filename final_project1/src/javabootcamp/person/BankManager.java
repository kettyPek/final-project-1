package javabootcamp.person;

import javabootcamp.account.AccountType;
import javabootcamp.menu.AppMenu;
import javabootcamp.utils.Utils;

public class BankManager extends AccountOwner{
	
	protected AccountOwner [] usersToApprove = {};
	
	public BankManager() {
		super("admin", "admin", 0000,null, 0);
		createAccountByManager(AccountType.TITANIUM,0,0);
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
			float interestRate = Utils.scanner.nextFloat();
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
			float operationFee = Utils.scanner.nextFloat();
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
	@Override
	public void displaySelcetionMenu() {
		AppMenu.displayBankManagerMenu();
	}
	
	@Override
	public void activateUserSelection(int selection) {
		switch(selection) {
		case 1:
			setAndApproveAccount(); 
			System.out.println("No more requests");
			break;
		}
	}
	

}
