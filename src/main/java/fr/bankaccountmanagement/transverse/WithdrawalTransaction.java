package fr.bankaccountmanagement.transverse;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class WithdrawalTransaction extends Transaction {

	public WithdrawalTransaction(BigDecimal amount, BigDecimal balance,
			LocalDateTime operationDate) {
		super(amount, balance.subtract(amount), operationDate);
	}

	@Override
	public Operation getOperation() {
		return Operation.WITHDRAWAL; 
	}

}
