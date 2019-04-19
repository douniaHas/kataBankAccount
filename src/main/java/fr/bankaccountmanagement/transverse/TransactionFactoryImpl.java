package fr.bankaccountmanagement.transverse;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionFactoryImpl implements TransactionFactory {

	@Override
	public Transaction makeTransaction(BigDecimal amount, TransactionType operation, BigDecimal balance,
			LocalDateTime operationDate) {
		switch (operation) {
		case DEPOSIT: return new DepositTransaction(amount, balance, operationDate);
		case WITHDRAWAL: return new WithdrawalTransaction(amount, balance, operationDate);
		default:
			throw new IllegalArgumentException("invalid operation");
		}
	}
	
}
