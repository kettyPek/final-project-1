package javabootcamp.person;

import java.time.LocalDate;

import javabootcamp.account.AccountType;
import javabootcamp.actions.ActivityName;
import javabootcamp.menu.AppMenu;
import javabootcamp.utils.Utils;
/**
 * Describe bank manager properties and possible methods
 * @author ketty
 *
 */
public class BankManager extends AccountOwner{
	
	protected AccountOwner [] usersToApprove = {};
	
	public BankManager() {
		super("admin", "admin", 0000,null, 0);
		createAccountByManager(AccountType.TITANIUM,0,0);
	}
	
	/**
	 * Add user to usersToApprove array
	 * @param user - user to add
	 */
	public void addUsersToApprove(AccountOwner user) {
		AccountOwner [] updatedUsersToApprove = new AccountOwner[usersToApprove.length+1];
		for(int i=0; i<usersToApprove.length; i++)
			updatedUsersToApprove[i] = usersToApprove[i];
		updatedUsersToApprove[usersToApprove.length] = user;
		usersToApprove = updatedUsersToApprove;
	}
	
	/**
	 * Open accounts for applicants
	 * Empties usersToApprove array
	 */
	public void setAndApproveAccount() {
		for(AccountOwner user : usersToApprove) {
			AccountType accountType = classifyAccountType(user.getMonthlyIncome());
			float interestRate = determineInterestRate(accountType);
			float operationFee  = determineOperationFee(accountType);
			user.createAccountByManager(accountType, interestRate, operationFee);
		}
		emptyUsersToApprove();
	}
	
	/**
	 * Classification to account type by applicant's income
	 * @param income - applicant's income
	 * @return - defined account type
	 */
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
	
	/**
	 * Set applicants interest rate by manager's input
	 * @param accountType 
	 * @return - given interest rate
	 */
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
	
	/**
	 * Set applicants operation fee  by manager's input
	 * @param accountType 
	 * @return - given operation fee
	 */
	private float determineOperationFee(AccountType accountType) {
		float operationFeeMin = accountType.getMinOperationFee();
		float operationFeeMax = accountType.getMaxOperationFee();
		while(true) {
			System.out.println("Insert operation fee from the domain ["+operationFeeMin+","+operationFeeMax+")");
			float operationFee = Utils.scanner.nextFloat();
			if(operationFee>=operationFeeMin && operationFee<operationFeeMax)
				return operationFee;
			System.out.println("input not valid, try again");
		}
	}
	
	/**
	 * Empty emptyUsersToApprove array
	 */
	private void emptyUsersToApprove() {
		AccountOwner [] emptyUsersToApprove = {};
		usersToApprove = emptyUsersToApprove;
		System.out.println("array empty : " + usersToApprove.length);
	}
	
	
	/*
	 * Activates options from the account owner menu by user input
	 */
	@Override
	public void activateUserSelection(int selection) {
		switch(selection) {
		case 1:
			setAndApproveAccount(); 
			System.out.println("No more requests");
			break;
		case 2:
			activateProduceReportSelection();
			break;
		case 3:
			AppMenu.exitAccountOwnerMenu();
			break;
		default:
			System.out.println("invalid input");
			break;
		}
	}
	
	/**
	 * Add deposit funds to bank's balance
	 * Add activity to activity log
	 */
	@Override
	protected void deposit(double depositAmount) {
		account.addToBalance(depositAmount);
		super.submitNewActivity(ActivityName.LOAN_PAYMENTS, depositAmount, "loan payment from aaaccount owner");
	}
	
	/**
	 * Subtract withdrawal funds to bank's balance
	 * Add activity to activity log
	 */
	@Override
	protected void withdrawal(double withdrawalAmount){
		account.subtractFromBalance(withdrawalAmount);
		super.submitNewActivity(ActivityName.GIVE_LOAN, withdrawalAmount, "given loan");
	}
	
	/**
	 * Add given fee to bank account
	 * @param operationFee
	 */
	protected void collectFee(double operationFee) {
		account.addToBalance(operationFee);
		super.submitNewActivity(ActivityName.FEE_COLLECTION, operationFee, " ");
	}
	
	/**
	 * Display manager's option menu
	 */
	@Override
	public void displaySelcetionMenu() {
		AppMenu.displayBankManagerMenu();
	}
	
	/**
	 * Activates produce report selection
	 */
	@Override
	protected void activateProduceReportSelection() {
		LocalDate startDate = Utils.receiveDateFromUser();
		account.produceActivityReportFromDate(startDate);
		super.displayBalance(); 
	}
	
	
	
	
	
	

}
