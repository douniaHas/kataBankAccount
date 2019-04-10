package fr.bankaccountmanagement.transverse;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@FunctionalInterface
public interface TransactionFactory {
	public Transaction makeTransaction(BigDecimal amount, Operation operation, BigDecimal balance, LocalDateTime operationDate);
}
