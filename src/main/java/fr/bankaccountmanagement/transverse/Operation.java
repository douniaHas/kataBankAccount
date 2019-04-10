package fr.bankaccountmanagement.transverse;

public enum Operation {
	DEPOSIT("Deposit"), WITHDRAWAL("Withdrawal");
	private final String value; 
	
	private Operation(String value){
		this.value = value; 
	}
	
	public String getValue(){
		return this.value;
	}
}
