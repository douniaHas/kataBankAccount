package fr.katas.bankaccountmanagement.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.Test;

import static fr.bankaccountmanagement.service.BankAccountService.printAccountTransactions;
import static fr.bankaccountmanagement.service.BankAccountService.updateAccountBalance;
import fr.bankaccountmanagement.exceptions.InvalidAmountException;
import fr.bankaccountmanagement.transverse.Account;
import fr.bankaccountmanagement.transverse.Operation;

public class BankAccountServiceTest {

	@Test
	public void depositOnAccount() throws InvalidAmountException {
		Assert.assertEquals("10.00",
				updateAccountBalance(new Account(), BigDecimal.valueOf(10d), Operation.DEPOSIT, LocalDateTime.now())
						.toString());
	}

	@Test
	public void depositBigAmountOnAccount() throws InvalidAmountException {
		Assert.assertEquals("9999999999.99", updateAccountBalance(new Account(), BigDecimal.valueOf(9999999999.99d),
				Operation.DEPOSIT, LocalDateTime.now()).toString());
	}

	@Test
	public void depositNotRoundedAmountOnAccount() throws InvalidAmountException {
		Assert.assertEquals("0.01",
				updateAccountBalance(new Account(), BigDecimal.valueOf(0.01d), Operation.DEPOSIT, LocalDateTime.now())
						.toString());
	}

	@Test
	public void depositNotScaledAmountOnAccount() throws InvalidAmountException {
		Assert.assertEquals("10.02", updateAccountBalance(new Account(), BigDecimal.valueOf(10.0190d),
				Operation.DEPOSIT, LocalDateTime.now()).toString());
	}

	@Test
	public void makeMultipleDepositsOnAccount() throws InvalidAmountException {
		Account account = new Account();
		updateAccountBalance(account, BigDecimal.valueOf(28.90d), Operation.DEPOSIT, LocalDateTime.now());
		Assert.assertEquals("58.90",
				updateAccountBalance(account, BigDecimal.valueOf(30.00d), Operation.DEPOSIT, LocalDateTime.now())
						.toString());
	}

	@Test
	public void makeRoundedWithdrawalFromOverdraftAccount() throws InvalidAmountException {
		Assert.assertEquals("-30.00", updateAccountBalance(new Account(), BigDecimal.valueOf(30.00d),
				Operation.WITHDRAWAL, LocalDateTime.now()).toString());
	}

	@Test
	public void withdrawalNotRoundedAmountFromAccount() throws InvalidAmountException {
		Assert.assertEquals("-0.21", updateAccountBalance(new Account(), BigDecimal.valueOf(0.21d),
				Operation.WITHDRAWAL, LocalDateTime.now()).toString());
	}

	@Test
	public void withdrawalNotScaledAmountFromAccount() throws InvalidAmountException {
		Assert.assertEquals("-87.10", updateAccountBalance(new Account(), BigDecimal.valueOf(87.0966d),
				Operation.WITHDRAWAL, LocalDateTime.now()).toString());
	}

	@Test
	public void withdrawalBigAmountOnAccount() throws InvalidAmountException {
		Assert.assertEquals("-9999999999.19", updateAccountBalance(new Account(), BigDecimal.valueOf(9999999999.19d),
				Operation.WITHDRAWAL, LocalDateTime.now()).toString());
	}

	@Test
	public void withdrawalMultipleAmountsFromAccount() throws InvalidAmountException {
		Account account = new Account();
		updateAccountBalance(account, BigDecimal.valueOf(10.10d), Operation.WITHDRAWAL, LocalDateTime.now());
		Assert.assertEquals("-42.12",
				updateAccountBalance(account, BigDecimal.valueOf(32.02d), Operation.WITHDRAWAL, LocalDateTime.now())
						.toString());
	}

	@Test
	public void withdrawalThenDepositOnAccount() throws InvalidAmountException {
		Account account = new Account();
		updateAccountBalance(account, BigDecimal.valueOf(15.20d), Operation.WITHDRAWAL, LocalDateTime.now());
		Assert.assertEquals("34.80",
				updateAccountBalance(account, BigDecimal.valueOf(50.00d), Operation.DEPOSIT, LocalDateTime.now())
						.toString());
	}

	@Test
	public void depositThenWithdrawalFromAccount() throws InvalidAmountException {
		Account account = new Account();
		updateAccountBalance(account, BigDecimal.valueOf(150.10d), Operation.DEPOSIT, LocalDateTime.now());
		Assert.assertEquals("-15.40",
				updateAccountBalance(account, BigDecimal.valueOf(165.50d), Operation.WITHDRAWAL, LocalDateTime.now())
						.toString());
	}

	@Test
	public void withdrawalCompensateDepositOnAccount() throws InvalidAmountException {
		Account account = new Account();
		updateAccountBalance(account, BigDecimal.valueOf(15.20d), Operation.WITHDRAWAL, LocalDateTime.now());
		Assert.assertEquals("0.00",
				updateAccountBalance(account, BigDecimal.valueOf(15.20d), Operation.DEPOSIT, LocalDateTime.now())
						.toString());
	}

	@Test
	public void depositCompensateWithdrawalOnAccount() throws InvalidAmountException {
		Account account = new Account();
		updateAccountBalance(account, BigDecimal.valueOf(50.00d), Operation.DEPOSIT, LocalDateTime.now());
		Assert.assertEquals("0.00",
				updateAccountBalance(account, BigDecimal.valueOf(50.00d), Operation.WITHDRAWAL, LocalDateTime.now())
						.toString());
	}

	@Test
	public void makeMultipleDepositsThenWithdrawalsFromAccount() throws InvalidAmountException {
		Account account = new Account();
		updateAccountBalance(account, BigDecimal.valueOf(10.10d), Operation.DEPOSIT, LocalDateTime.now());
		updateAccountBalance(account, BigDecimal.valueOf(51.30d), Operation.DEPOSIT, LocalDateTime.now());
		updateAccountBalance(account, BigDecimal.valueOf(7.80d), Operation.DEPOSIT, LocalDateTime.now());
		updateAccountBalance(account, BigDecimal.valueOf(60.00d), Operation.WITHDRAWAL, LocalDateTime.now());
		updateAccountBalance(account, BigDecimal.valueOf(10.65d), Operation.WITHDRAWAL, LocalDateTime.now());
		Assert.assertEquals("-71.46",
				updateAccountBalance(account, BigDecimal.valueOf(70.01d), Operation.WITHDRAWAL, LocalDateTime.now())
						.toString());
	}

	@Test
	public void makeMultipleWithdrawalsThenDepositsOnAccount() throws InvalidAmountException {
		Account account = new Account();
		updateAccountBalance(account, BigDecimal.valueOf(60.00d), Operation.WITHDRAWAL, LocalDateTime.now());
		updateAccountBalance(account, BigDecimal.valueOf(10.65d), Operation.WITHDRAWAL, LocalDateTime.now());
		updateAccountBalance(account, BigDecimal.valueOf(10.10d), Operation.DEPOSIT, LocalDateTime.now());
		updateAccountBalance(account, BigDecimal.valueOf(51.30d), Operation.DEPOSIT, LocalDateTime.now());
		updateAccountBalance(account, BigDecimal.valueOf(7.80d), Operation.DEPOSIT, LocalDateTime.now());
		Assert.assertEquals("-71.46",
				updateAccountBalance(account, BigDecimal.valueOf(70.01d), Operation.WITHDRAWAL, LocalDateTime.now())
						.toString());
	}

	@Test
	public void makeMultipleWithdrawalsAndDepositsInMixedOrder() throws InvalidAmountException {
		Account account = new Account();
		updateAccountBalance(account, BigDecimal.valueOf(10.10d), Operation.DEPOSIT, LocalDateTime.now());
		updateAccountBalance(account, BigDecimal.valueOf(51.30d), Operation.DEPOSIT, LocalDateTime.now());
		updateAccountBalance(account, BigDecimal.valueOf(60.00d), Operation.WITHDRAWAL, LocalDateTime.now());
		updateAccountBalance(account, BigDecimal.valueOf(7.80d), Operation.DEPOSIT, LocalDateTime.now());
		updateAccountBalance(account, BigDecimal.valueOf(10.65d), Operation.WITHDRAWAL, LocalDateTime.now());
		Assert.assertEquals("-71.46",
				updateAccountBalance(account, BigDecimal.valueOf(70.01d), Operation.WITHDRAWAL, LocalDateTime.now())
						.toString());
	}

	@Test(expected = InvalidAmountException.class)
	public void makeNegativeDepositOnAccount() throws InvalidAmountException {
		updateAccountBalance(new Account(), BigDecimal.valueOf(-10.70d), Operation.DEPOSIT, LocalDateTime.now());
	}

	@Test(expected = InvalidAmountException.class)
	public void makeFakeDepositWithZeroAmount() throws InvalidAmountException {
		Assert.assertEquals(BigDecimal.ZERO,
				updateAccountBalance(new Account(), BigDecimal.ZERO, Operation.DEPOSIT, LocalDateTime.now()));
	}

	@Test(expected = InvalidAmountException.class)
	public void makeFakeDepositWithNullAmount() throws InvalidAmountException {
		updateAccountBalance(new Account(), null, Operation.DEPOSIT, LocalDateTime.now());
	}

	@Test(expected = InvalidAmountException.class)
	public void makeNegativeWithdrawalFromAccount() throws InvalidAmountException {
		updateAccountBalance(new Account(), BigDecimal.valueOf(-20.00d), Operation.WITHDRAWAL, LocalDateTime.now());
	}

	@Test(expected = InvalidAmountException.class)
	public void makeFakeWithdrawalWithZeroAmount() throws InvalidAmountException {
		updateAccountBalance(new Account(), BigDecimal.ZERO, Operation.WITHDRAWAL, LocalDateTime.now());
	}

	@Test(expected = InvalidAmountException.class)
	public void makeFakeWithdrawalWithNullAmount() throws InvalidAmountException {
		updateAccountBalance(new Account(), null, Operation.WITHDRAWAL, LocalDateTime.now());
	}

	@Test(expected = IllegalArgumentException.class)
	public void makeInvalidOperation() throws InvalidAmountException {
		updateAccountBalance(new Account(), BigDecimal.valueOf(10), Operation.valueOf("NOT_PERMITTED_OP"),
				LocalDateTime.now());
	}

	@Test
	public void printOneDeposit() throws InvalidAmountException {
		Account account = new Account();
		LocalDateTime date = LocalDateTime.of(2019, 4, 1, 00, 00); 
		updateAccountBalance(account, BigDecimal.valueOf(20.00d), Operation.DEPOSIT, date);
		Assert.assertEquals("Deposit, the 01/04/2019 of 20.00 euros. Your account balance is of 20.00 euros",
				printAccountTransactions(System.out, account));
	}

	@Test
	public void printMultipleDeposits() throws InvalidAmountException {
		LocalDateTime date = LocalDateTime.of(2019, 4, 3, 00, 00); 
		Account account = new Account();
		updateAccountBalance(account, BigDecimal.valueOf(5.90d), Operation.DEPOSIT, date);
		updateAccountBalance(account, BigDecimal.valueOf(15.90d), Operation.DEPOSIT, date);
		Assert.assertEquals(
				"Deposit, the 03/04/2019 of 5.90 euros. Your account balance is of 5.90 euros\n" + 
		        "Deposit, the 03/04/2019 of 15.90 euros. Your account balance is of 21.80 euros",
				printAccountTransactions(System.out, account));
	}

	@Test
	public void printOneWithdrawal() throws InvalidAmountException {
		Account account = new Account();
		updateAccountBalance(account, BigDecimal.valueOf(10.00d), Operation.WITHDRAWAL, LocalDateTime.of(2019, 2, 1, 00, 00));
		Assert.assertEquals("Withdrawal, the 01/02/2019 of 10.00 euros. Your account balance is of -10.00 euros",
				printAccountTransactions(System.out, account));
	}

	@Test
	public void printMultipleWithdrawals() throws InvalidAmountException {
		Account account = new Account();
		LocalDateTime date = LocalDateTime.of(2019, 4, 6, 00, 00); 
		updateAccountBalance(account, BigDecimal.valueOf(10.20d), Operation.WITHDRAWAL, date);
		updateAccountBalance(account, BigDecimal.valueOf(35.01d), Operation.WITHDRAWAL, date);
		Assert.assertEquals(
				"Withdrawal, the 06/04/2019 of 10.20 euros. Your account balance is of -10.20 euros\n"
						+ "Withdrawal, the 06/04/2019 of 35.01 euros. Your account balance is of -45.21 euros",
				printAccountTransactions(System.out, account));
	}

	@Test
	public void printTransactionsOnEmptyAccount() {
		Assert.assertEquals("No transactions recorded on empty account. Your balance is of 0 euros",
				printAccountTransactions(System.out, new Account()));
	}

	@Test
	public void printDepositThenWithdrawalTransactions() throws InvalidAmountException {
		LocalDateTime date = LocalDateTime.of(2019, 04, 01, 00, 00);
		Account account = new Account();
		updateAccountBalance(account, BigDecimal.valueOf(100.00d), Operation.DEPOSIT, date);
		updateAccountBalance(account, BigDecimal.valueOf(5.87d), Operation.DEPOSIT, date);
		updateAccountBalance(account, BigDecimal.valueOf(3.5d), Operation.WITHDRAWAL, date);
		updateAccountBalance(account, BigDecimal.valueOf(85.10d), Operation.WITHDRAWAL, date);
		updateAccountBalance(account, BigDecimal.valueOf(0.01d), Operation.WITHDRAWAL, date);
		Assert.assertEquals(
				"Deposit, the 01/04/2019 of 100.00 euros. Your account balance is of 100.00 euros\n"
						+ "Deposit, the 01/04/2019 of 5.87 euros. Your account balance is of 105.87 euros\n"
						+ "Withdrawal, the 01/04/2019 of 3.50 euros. Your account balance is of 102.37 euros\n"
						+ "Withdrawal, the 01/04/2019 of 85.10 euros. Your account balance is of 17.27 euros\n"
						+ "Withdrawal, the 01/04/2019 of 0.01 euros. Your account balance is of 17.26 euros",
				printAccountTransactions(System.out, account));
	}

	@Test
	public void printWithdrawalThenDepositTransactions() throws InvalidAmountException {
		LocalDateTime date = LocalDateTime.of(2019, 04, 01, 00, 00);
		Account account = new Account();
		updateAccountBalance(account, BigDecimal.valueOf(35.90d), Operation.WITHDRAWAL, date);
		updateAccountBalance(account, BigDecimal.valueOf(5.91d), Operation.WITHDRAWAL, date);
		updateAccountBalance(account, BigDecimal.valueOf(0.20d), Operation.WITHDRAWAL, date);
		updateAccountBalance(account, BigDecimal.valueOf(18.10d), Operation.DEPOSIT, date);
		updateAccountBalance(account, BigDecimal.valueOf(1.15d), Operation.DEPOSIT, date);
		Assert.assertEquals(
				"Withdrawal, the 01/04/2019 of 35.90 euros. Your account balance is of -35.90 euros\n"
						+ "Withdrawal, the 01/04/2019 of 5.91 euros. Your account balance is of -41.81 euros\n"
						+ "Withdrawal, the 01/04/2019 of 0.20 euros. Your account balance is of -42.01 euros\n"
						+ "Deposit, the 01/04/2019 of 18.10 euros. Your account balance is of -23.91 euros\n"
						+ "Deposit, the 01/04/2019 of 1.15 euros. Your account balance is of -22.76 euros",
				printAccountTransactions(System.out, account));
	}

	@Test
	public void printMultipleWithdrawalsAndDeposalsInMixedOrder() throws InvalidAmountException {
		LocalDateTime date = LocalDateTime.of(2019, 04, 01, 00, 00);
		Account account = new Account();
		updateAccountBalance(account, BigDecimal.valueOf(20.00d), Operation.WITHDRAWAL, date);
		updateAccountBalance(account, BigDecimal.valueOf(20.00d), Operation.DEPOSIT, date);
		updateAccountBalance(account, BigDecimal.valueOf(5.00d), Operation.WITHDRAWAL, date);
		updateAccountBalance(account, BigDecimal.valueOf(100.00d), Operation.DEPOSIT, date);
		updateAccountBalance(account, BigDecimal.valueOf(35.00d), Operation.WITHDRAWAL, date);
		Assert.assertEquals(
				"Withdrawal, the 01/04/2019 of 20.00 euros. Your account balance is of -20.00 euros\n"
						+ "Deposit, the 01/04/2019 of 20.00 euros. Your account balance is of 0.00 euros\n"
						+ "Withdrawal, the 01/04/2019 of 5.00 euros. Your account balance is of -5.00 euros\n"
						+ "Deposit, the 01/04/2019 of 100.00 euros. Your account balance is of 95.00 euros\n"
						+ "Withdrawal, the 01/04/2019 of 35.00 euros. Your account balance is of 60.00 euros",
				printAccountTransactions(System.out, account));
	}

	@Test
	public void printMultipleWithdrawalsAndDeposalsMadeOnDifferentDates() throws InvalidAmountException {
		Account account = new Account();
		updateAccountBalance(account, BigDecimal.valueOf(20.00d), Operation.WITHDRAWAL,
				LocalDateTime.of(2019, 2, 1, 00, 00));
		updateAccountBalance(account, BigDecimal.valueOf(20.00d), Operation.DEPOSIT,
				LocalDateTime.of(2019, 2, 2, 00, 00));
		updateAccountBalance(account, BigDecimal.valueOf(5.00d), Operation.WITHDRAWAL,
				LocalDateTime.of(2019, 3, 4, 00, 00));
		updateAccountBalance(account, BigDecimal.valueOf(100.00d), Operation.DEPOSIT,
				LocalDateTime.of(2019, 4, 6, 00, 00));
		updateAccountBalance(account, BigDecimal.valueOf(35.00d), Operation.WITHDRAWAL,
				LocalDateTime.of(2019, 4, 8, 00, 00));
		Assert.assertEquals(
				"Withdrawal, the 01/02/2019 of 20.00 euros. Your account balance is of -20.00 euros\n"
						+ "Deposit, the 02/02/2019 of 20.00 euros. Your account balance is of 0.00 euros\n"
						+ "Withdrawal, the 04/03/2019 of 5.00 euros. Your account balance is of -5.00 euros\n"
						+ "Deposit, the 06/04/2019 of 100.00 euros. Your account balance is of 95.00 euros\n"
						+ "Withdrawal, the 08/04/2019 of 35.00 euros. Your account balance is of 60.00 euros",
				printAccountTransactions(System.out, account));
	}

	@Test
	public void printMultipleWithdrawalsCompensateDeposits() throws InvalidAmountException {
		Account account = new Account();
		updateAccountBalance(account, BigDecimal.valueOf(20.00d), Operation.WITHDRAWAL,
				LocalDateTime.of(2019, 2, 1, 00, 00));
		updateAccountBalance(account, BigDecimal.valueOf(20.00d), Operation.DEPOSIT,
				LocalDateTime.of(2019, 2, 2, 00, 00));
		updateAccountBalance(account, BigDecimal.valueOf(5.00d), Operation.WITHDRAWAL,
				LocalDateTime.of(2019, 3, 4, 00, 00));
		updateAccountBalance(account, BigDecimal.valueOf(100.00d), Operation.DEPOSIT,
				LocalDateTime.of(2019, 4, 6, 00, 00));
		updateAccountBalance(account, BigDecimal.valueOf(95.00d), Operation.WITHDRAWAL,
				LocalDateTime.of(2019, 4, 8, 00, 00));
		Assert.assertEquals(
				"Withdrawal, the 01/02/2019 of 20.00 euros. Your account balance is of -20.00 euros\n"
						+ "Deposit, the 02/02/2019 of 20.00 euros. Your account balance is of 0.00 euros\n"
						+ "Withdrawal, the 04/03/2019 of 5.00 euros. Your account balance is of -5.00 euros\n"
						+ "Deposit, the 06/04/2019 of 100.00 euros. Your account balance is of 95.00 euros\n"
						+ "Withdrawal, the 08/04/2019 of 95.00 euros. Your account balance is of 0.00 euros",
				printAccountTransactions(System.out, account));
	}

}
