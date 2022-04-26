package javabootcamp.person;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javabootcamp.account.Account;
import javabootcamp.account.AccountType;
import javabootcamp.actions.ActivityData;
import javabootcamp.actions.ActivityName;
import javabootcamp.actions.BillType;
import javabootcamp.credentials.LogInCredentials;
import javabootcamp.menu.AppMenu;
import javabootcamp.utils.Utils;

public class AccountOwner extends Person{
	
	protected Account account = null;
	protected double monthlyIncome;
	protected LogInCredentials credentials = null;
	private final double MAX_TRANSFER_AMOUNT = 2000;
	private final double MAX_BILL_AMOUNT = 5000;
	private final int ALLOWED_PAYMENTS = 60;
	
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
	
	public double getMonthlyIncome() {
		return monthlyIncome;
	}
	
	public Account getAccount() {
		return account;
	}
	
	public long getPhoneNumber() {
		return super.phoneNumber;
	}
	//
	
	/**
	 * Display owner's balance
	 */
	private void displayBalance() {
		System.out.println("Your balance is: " + account.getBalance());
	}
	
	/**
	 * Receive date from user, displays activities log from this date
	 * Display balance
	 */
	protected void activateProduceReportSelection() {
		LocalDate startDate = Utils.receiveDateFromUser();
		produceReport(startDate);
		displayBalance(); 
	}
	
	/**
	 * Display activities log from given date
	 * @param startDate - date to display activities from
	 */
	private void produceReport(LocalDate startDate) {
		account.produceActivityReportFromDate(startDate);		
	}
	
	private void activateDepositeSelection() {
		double depositAmount = Utils.scanDoubleOf("amount to deposit");	
		deposit(depositAmount);
		submitNewActivity(ActivityName.DEPOSIT,depositAmount);
	}
	
	protected void deposit(double depositAmount) {
		account.addToBalance(depositAmount);	
	}
	
	private void activateWithdrawalSelection() {
		double withdrawalAmount = Utils.scanDoubleOf("withdrawal amount");
		if(withdrawalAmount<=account.getAccountType().getMaxWithdrawalAmount()) {
			withdrawal(withdrawalAmount);
			submitNewActivity(ActivityName.WITHDRAWAL,-1*withdrawalAmount);
		}
		else
			System.out.println("Withdrawal amount exceeds allowed withdrawal ");		
	}
	
	private void withdrawal(double withdrawalAmount) {
		account.subtractFromBalance(withdrawalAmount);
	}
	
	
	private void transferfund() {
		long receiverPhoneNum = Utils.scanLongOf("receiver phone number");
		double amountToTransfer = Utils.scanDoubleOf("amount to transfer");
		//TODO check if user exist
		
		if(amountToTransfer>MAX_TRANSFER_AMOUNT)
			System.out.println("The amount to transfer exceeds allowed amount.");
		else {
			transferFromAccount(amountToTransfer);
			//TODO finish 
//			user.deposit(amountToTransfer);
//			user.submitNewActivity(ActivityName.RECEIVE_TRANSFER,amountToTransfer,"transfer");
		}
	}
	
	private void transferFromAccount(double amountToTransfer) {
		account.subtractFromBalance(amountToTransfer);
		submitNewActivity(ActivityName.TRANSFER,-1*amountToTransfer);
	}
	
	private void payBill() {
		ActivityName ativity = ActivityName.PAY_BILL;
		BillType.diaplayBillTypes();
		int selection = Utils.scanIntOf("selection");
		BillType billType = BillType.getBillType(selection);
		double billAmount = Utils.scanDoubleOf("bill amount");
		if(billAmount>MAX_BILL_AMOUNT)
			System.out.println("The amount to pay the bill exceeds allowed amount.");
		else {
			account.subtractFromBalance(billAmount);
			if(billType==BillType.BANK)
				ativity = ActivityName.LOAN_PAYMENTS;
			submitNewActivity(ativity ,-1*billAmount);	
		}	
	}
	
	private void getLoan() {
		double loanAmount = Utils.scanDoubleOf("loan amount");
		int payments = Utils.scanIntOf("amount of payments");
		if(loanAmount>account.getAccountType().getNaxLoanAmount() || payments>ALLOWED_PAYMENTS) {
			System.out.println("can not loan");
			return;
		}
		//TODO updated amount of monthly return
		System.out.println("Amount of monthly return: " + payments);
		account.addToBalance(loanAmount);
		submitNewActivity(ActivityName.GET_LOAN,loanAmount);	
		//TODO add bank withdrawal and activity log
//		bankManager.withdrawal(loanAmount);
//		bankManager.submitNewActivity(ActivityName.GIVE_LOAN,-1*loanAmount,"");
	}
	
	/**
	 * Create new activity and add it to activity log
	 * @param activityName - type of activity
	 * @param balanceChange - change in balance after the activity
	 */
	private void submitNewActivity(ActivityName activityName,double balanceChange) {
		String notes = Utils.scanStringOf("notes");
		account.addNewActivity(activityName,balanceChange,notes);
	}

	private void submitNewActivity(ActivityName activityName,double balanceChange,String notes) {
		account.addNewActivity(activityName,balanceChange,notes);
	}
	
	public void createAccountByManager(AccountType accountType,float intersetTare, float operationFee) {
		account = new Account(accountType,intersetTare,operationFee);
	}
	
	public void displaySelcetionMenu() {
		AppMenu.displayAccountOwnerMenu();
	}
	
	
	
	public void activateUserSelection(int selection) {
		switch(selection) {
		case 1:
			displayBalance(); 
			break;
		case 2:
			activateProduceReportSelection();
			break;
		case 3:
			activateDepositeSelection();
			break;
		case 4:
			activateWithdrawalSelection(); 
			break;
		case 5: 
			transferfund();
			break;
		case 6:
			payBill();
			break;
		case 7:
			getLoan();
			break;
		}
	}
	
	
}
