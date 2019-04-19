package fr.bankaccountmanagement.transverse;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static fr.bankaccountmanagement.tools.NumberTools.format;

public class Account {
	private BigDecimal balance;
	private List<Transaction> transactions = new ArrayList<>();

	public Account() {
		this.balance = BigDecimal.ZERO;
	}

	public String buildMessage() {
		if (transactions.isEmpty()) {
			return "No transactions recorded on empty account. Your balance is of " + balance + " euros";
		}
		return transactions.stream()
				.map(t -> t.toString())
				.collect(Collectors.joining("\n"));
	}

	public synchronized BigDecimal updateBalance(BigDecimal amount, TransactionType operation, LocalDateTime operationDate) {
		TransactionFactoryImpl transactionFactory = new TransactionFactoryImpl(); 
		Transaction transaction = transactionFactory.makeTransaction(format(amount), operation, balance, operationDate);
		transactions.add(transaction);
		this.balance = transaction.getBalance(); 
		return this.balance; 
	}

}
