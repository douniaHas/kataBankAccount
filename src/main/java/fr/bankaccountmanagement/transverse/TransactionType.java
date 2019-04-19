package fr.bankaccountmanagement.transverse;

public enum TransactionType {
	DEPOSIT("Deposit"), WITHDRAWAL("Withdrawal");
	private final String value; 
	
	private TransactionType(String value){
		this.value = value; 
	}
	
	public String getValue(){
		return this.value;
	}
}
