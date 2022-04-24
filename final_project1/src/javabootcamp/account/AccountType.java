package javabootcamp.account;

public enum AccountType {
	
	BRONZE(4.5f,6f,5f,7.5f,10000,2500),
	SILVER(3f,4.5f,3.8f,5f,20000,4000),
	GOLD(1.5f,3f,1.75f,3.8f,50000,6000),
	TITANIUM(0f,0f,0f,0f,0,0);
	
	private final float minInterestRate;
	private final float maxInterestRate;
	private final float minAperationFee;
	private final float maxAperationFee;
	private final int maxLoanAmount;
	private final int maxWithdrawalAmount;
	

	AccountType(float minInterestRate, float maxInterestRate, float minAperationFee,
			float maxAperationFee, int maxLoanAmount, int maxWithdrawalAmount) {
		
		this.minInterestRate = minInterestRate;
		this.maxInterestRate = maxInterestRate;
		this.minAperationFee = minAperationFee;
		this.maxAperationFee = maxAperationFee;
		this.maxLoanAmount = maxLoanAmount;
		this.maxWithdrawalAmount = maxWithdrawalAmount;
	}
		

}
