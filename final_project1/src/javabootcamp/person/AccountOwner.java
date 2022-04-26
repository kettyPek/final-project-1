package javabootcamp.person;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javabootcamp.account.Account;
import javabootcamp.account.AccountType;
import javabootcamp.actions.ActivityData;
import javabootcamp.actions.ActivityName;
import javabootcamp.actions.BillType;
import javabootcamp.app.AppManager;
import javabootcamp.credentials.LogInCredentials;
import javabootcamp.loan.Loan;
import javabootcamp.menu.AppMenu;
import javabootcamp.utils.Utils;
/**
 * Represents a person that owns account in the bank
 * @author ketty
 *
 */
public class AccountOwner extends Person{
	
	protected Account account = null;
	protected double monthlyIncome;
	protected LogInCredentials credentials = null;
	private final double MAX_TRANSFER_AMOUNT = 2000;
	private final double MAX_BILL_AMOUNT = 5000;
	private final int ALLOWED_PAYMENTS = 60;
	private BankManager bankManager;
	private Loan loan = null;
	
	public AccountOwner(String firstName, String lastName, long phoneNumber, LocalDate birthDat, double monthlyIncome) {
		super(firstName, lastName, phoneNumber, birthDat);
		this.monthlyIncome = monthlyIncome;
	}
	
	public AccountOwner(String firstName, String lastName, long phoneNumber, LocalDate birthDat, double monthlyIncome,BankManager bankManager) {
		super(firstName, lastName, phoneNumber, birthDat);
		this.monthlyIncome = monthlyIncome;
		this.bankManager = bankManager;
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
	protected void displayBalance() {
		System.out.println("Your balance is: " + account.getBalance());
	}
	
	/**
	 * Display owner's loan status
	 */
	private void displayLoanStatus() {
		System.out.println("Loan status:");
		if(loan==null)
			System.out.println("No loan");
		
		else
			System.out.println(loan.toString());
	}
	
	/**
	 * Receive date from user, displays activities log from this date
	 * Display balance
	 */
	protected void activateProduceReportSelection() {
		LocalDate startDate = Utils.receiveDateFromUser();
		account.produceActivityReportFromDate(startDate);
		payOperationRate();
		displayBalance(); 
		displayLoanStatus();
	}
	
	/**
	 * Activate deposit option
	 */
	private void activateDepositeSelection() {
		double depositAmount = Utils.scanDoubleOf("amount to deposit");	
		deposit(depositAmount);
		payOperationRate();
		submitNewActivity(ActivityName.DEPOSIT,depositAmount);
	}
	
	/**
	 * Add deposit funds to accounts balance
	 * @param depositAmount - fund to deposit
	 */
	protected void deposit(double depositAmount) {
		account.addToBalance(depositAmount);	
	}
	
	/**
	 * Activates withdrawal option
	 */
	private void activateWithdrawalSelection() {
		double withdrawalAmount = Utils.scanDoubleOf("withdrawal amount");
		if(withdrawalAmount<=account.getAccountType().getMaxWithdrawalAmount()) {
			withdrawal(withdrawalAmount);
			submitNewActivity(ActivityName.WITHDRAWAL,-1*withdrawalAmount);
			payOperationRate();
		}
		else
			System.out.println("Withdrawal amount exceeds allowed withdrawal ");		
	}
	
	/**
	 * Subtract withdrawal funds from account balance
	 * @param withdrawalAmount - funds to withdrawal
	 */
	protected void withdrawal(double withdrawalAmount) {
		account.subtractFromBalance(withdrawalAmount);
	}
	
	/**
	 * Transfers funds to different account owner by given phone number
	 */
	private void transferfund() {
		long receiverPhoneNum = Utils.scanLongOf("receiver phone number");
		double amountToTransfer = Utils.scanDoubleOf("amount to transfer");
		AccountOwner accountToTransfer = AppManager.getAccountOwnerByPhoneNumber(receiverPhoneNum);
		if(accountToTransfer!=null) {
			if(amountToTransfer>MAX_TRANSFER_AMOUNT)
				System.out.println("The amount to transfer exceeds allowed amount.");
			else {
				transferFromAccount(amountToTransfer);
				accountToTransfer.deposit(amountToTransfer);
				accountToTransfer.submitNewActivity(ActivityName.RECEIVE_TRANSFER,amountToTransfer,"Transfer to account");
				payOperationRate();
			}
		}
		else
			System.out.println("no such user");
	}
	
	/**
	 * Subtract transfer funds from account balance
	 * @param amountToTransfer - funds to transfer
	 */
	private void transferFromAccount(double amountToTransfer) {
		account.subtractFromBalance(amountToTransfer);
		submitNewActivity(ActivityName.TRANSFER,-1*amountToTransfer);
	}
	
	/**
	 * Pay bill
	 */
	private void payBill() {
		BillType.diaplayBillTypes();
		int selection = Utils.scanIntOf("selection");
		BillType billType = BillType.getBillType(selection);
		if(billType==BillType.BANK) {
			payLoanToBank();
		}
		else {
			double billAmount = Utils.scanDoubleOf("bill amount");
			if(billAmount>MAX_BILL_AMOUNT) {
				System.out.println("The amount to pay the bill exceeds allowed amount.");
				return;
			}
			account.subtractFromBalance(billAmount);
			submitNewActivity(ActivityName.PAY_BILL ,-1*billAmount);
		}
		payOperationRate();
	}
	
	/**
	 * Get loan from the bank
	 */
	private void getLoan() {
		double loanAmount = Utils.scanDoubleOf("loan amount");
		int payments = Utils.scanIntOf("amount of payments");
		if(loanAmount>account.getAccountType().getNaxLoanAmount() || payments>ALLOWED_PAYMENTS) {
			System.out.println("can not loan");
			return;
		}
		double monthlyReturnAmount = (loanAmount/payments)*(account.getInterestRate()%100);
		loan = new Loan(loanAmount, payments, account.getInterestRate(),monthlyReturnAmount);
		System.out.println("Amount of monthly return: " + monthlyReturnAmount);
		account.addToBalance(loanAmount);
		submitNewActivity(ActivityName.GET_LOAN,loanAmount);	
		AccountOwner bankManager = AppManager.getAccountOwnerByPhoneNumber(0000);
		bankManager.withdrawal(loanAmount);
		payOperationRate();
	}
	
	/**
	 * Transfer commission to bank's account and subtract it from owner's account
	 */
	private void payOperationRate() {
		float operationRate = account.getOperationFee();
		bankManager.collectFee(operationRate);
		account.subtractFromBalance(operationRate);
		submitNewActivity(ActivityName.FEE_PAYMENT,-1* operationRate," ");
	}
	
	
	/**
	 * Create new activity and add it to activity log
	 * @param activityName - type of activity
	 * @param balanceChange - change in balance after the activity
	 */
	protected void submitNewActivity(ActivityName activityName,double balanceChange) {
		String notes = Utils.scanStringOf("notes");
		account.addNewActivity(activityName,balanceChange,notes);
	}

	/**
	 * Create new activity and add it to activity log with manual notes
	 * @param activityName
	 * @param balanceChange
	 * @param notes
	 */
	protected void submitNewActivity(ActivityName activityName,double balanceChange,String notes) {
		account.addNewActivity(activityName,balanceChange,notes);
	}
	
	/**
	 * Create account for account owner 
	 * @param accountType
	 * @param intersetTare
	 * @param operationFee
	 */
	protected void createAccountByManager(AccountType accountType,float intersetTare, float operationFee) {
		account = new Account(accountType,intersetTare,operationFee);
	}
	
	/**
	 * Activates user selection from account owner menu
	 * @param selection
	 */
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
		case 8:
			AppMenu.exitAccountOwnerMenu();
			break;
		default:
			System.out.println("invalid input");
			break;
		}
	}
	
	public void displaySelcetionMenu() {
		AppMenu.displayAccountOwnerMenu();
	}
	
	/**
	 * Checks if user has account
	 * @return - true if account exists, otherwise false
	 */
	public boolean userHasAccount() {
		if(account == null)
			return false;
		return true;
	}
	
	/**
	 * Pay monthly payment of the loan to the bank
	 */
	private void payLoanToBank() {
		if(loan!=null) {
			loan.payLoan();
			submitNewActivity(ActivityName.LOAN_PAYMENTS, loan.getMonthlyPayment(),"Loan payment");
			bankManager.deposit(loan.getMonthlyPayment());
		}
		else
			System.out.println("no loan");
	}
	
	
}
