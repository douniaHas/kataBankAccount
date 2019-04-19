package fr.bankaccountmanagement.service;

import java.io.PrintStream;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import fr.bankaccountmanagement.exceptions.InvalidAmountException;
import fr.bankaccountmanagement.tools.NumberTools;
import fr.bankaccountmanagement.transverse.Account;
import fr.bankaccountmanagement.transverse.TransactionType;

public class BankAccountService {

	private BankAccountService() {
		//
	}

	/**
	 * Updates the balance of the account after applying the operation on the operation date
	 * @param account
	 * @param amount
	 * @param operation
	 * @param operationDate
	 * @return
	 * @throws InvalidAmountException
	 */
	public static BigDecimal updateAccountBalance(Account account, BigDecimal amount, TransactionType operation,
			LocalDateTime operationDate) throws InvalidAmountException {
		checkTransactionValidity(amount);
		return account.updateBalance(amount, operation, operationDate);
	}

	/**
	 * Prints the account transactions on a printStream 
	 * @param printStream
	 * @param account
	 * @return
	 */
	public static String printAccountTransactions(PrintStream printStream, Account account) {
		String message = account.buildMessage();
		printStream.println(message);
		return message;
	}
	
	private static void checkTransactionValidity(BigDecimal amount) throws InvalidAmountException {
		NumberTools.checkInvalidAmount(amount);
	}

}
