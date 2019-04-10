package fr.bankaccountmanagement.tools;

import java.math.BigDecimal;
import java.math.RoundingMode;

import fr.bankaccountmanagement.exceptions.InvalidAmountException;

public final class NumberTools {
	private static final String S_NOT_PERMITTED_AMOUNT = "Not permitted amount";
	
	private NumberTools() {
		//
	}
	
	public static BigDecimal format(BigDecimal number){
		return number.setScale(2, RoundingMode.HALF_EVEN); 
	}

	public static void checkAmount(BigDecimal number) throws InvalidAmountException {
		if (number == null || number.compareTo(BigDecimal.ZERO) <= 0){
			throw new InvalidAmountException(S_NOT_PERMITTED_AMOUNT); 
		} 
	}
}
