package javabootcamp.loan;
/**
 * Describe Loan properties 
 * @author ketty
 *
 */
public class Loan {
	
	protected final double AMOUNT;
	protected int payments;
	protected double currentDept;
	protected float interestRate;
	protected double monthlyPayment;
	protected int leftPayments;
	
	public Loan(double amount, int payments, float interestRate, double monthlyPayment) {
		AMOUNT = amount;
		this.payments = payments;
		this.interestRate = interestRate;
		this.monthlyPayment = monthlyPayment;
		leftPayments = payments;
		currentDept = AMOUNT;
	}
	
	public double getMonthlyPayment() {
		return monthlyPayment;
	}
	
	/**
	 * Pays monthly loan
	 */
	public void payLoan() {
		currentDept -= monthlyPayment;
		leftPayments--;
	}

	@Override
	public String toString() {
		return "Loan of " + AMOUNT + ", monthly payment:" + monthlyPayment +
				", interest : " + interestRate + ", left payment: "+ leftPayments+", curren debt: "+ currentDept;
	}
	
	
	
	

}
