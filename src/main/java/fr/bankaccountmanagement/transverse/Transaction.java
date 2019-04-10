package fr.bankaccountmanagement.transverse;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import fr.bankaccountmanagement.tools.DateTools;

public abstract class Transaction {
	BigDecimal amount;
	LocalDateTime operationDate;
	BigDecimal balance;  

	public Transaction(BigDecimal amount, BigDecimal balance, LocalDateTime operationDate){
		this.amount = amount;
		this.operationDate = operationDate;
		this.balance = balance; 
	}

	public BigDecimal getAmount() {
		return this.amount;
	}

	public abstract Operation getOperation(); 
	
	public LocalDateTime getDateOperation(){
		return this.operationDate; 
	}
	
	public BigDecimal getBalance() {
		return balance;
	}
	
	@Override
	public String toString(){
		return getOperation().getValue() + ", the " + DateTools.formatDate(operationDate) + " of "
				+ amount + " euros. Your account balance is of " + balance + " euros";
	}

}
