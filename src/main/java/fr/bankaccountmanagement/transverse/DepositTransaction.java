package fr.bankaccountmanagement.transverse;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class DepositTransaction extends Transaction {
	
	public DepositTransaction(BigDecimal amount, BigDecimal balance,
			LocalDateTime operationDate) {
		super(amount, balance.add(amount), operationDate);
	}
	
	@Override
	public TransactionType getOperation() {
		return TransactionType.DEPOSIT; 
	}

}
