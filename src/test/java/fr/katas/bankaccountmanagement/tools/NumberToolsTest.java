package fr.katas.bankaccountmanagement.tools;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

import fr.bankaccountmanagement.exceptions.InvalidAmountException;
import static fr.bankaccountmanagement.tools.NumberTools.checkInvalidAmount;
import static fr.bankaccountmanagement.tools.NumberTools.format;

public class NumberToolsTest {

	@Test
	public void controlPermittedAmountTest(){
		try{
			checkInvalidAmount(BigDecimal.valueOf(10));
		}
		catch(Exception e){
			Assert.fail(); 
		}
	}
	
	@Test(expected = InvalidAmountException.class)
	public void controlZeroAmountTest() throws InvalidAmountException {
		checkInvalidAmount(BigDecimal.ZERO);
	}
	
	
	@Test(expected = InvalidAmountException.class)
	public void checkAmountNull() throws InvalidAmountException {
		checkInvalidAmount(null);
	}
	
	@Test
	public void checkNegativeAmount() {
		try{
			checkInvalidAmount(BigDecimal.valueOf(-10)); 
			Assert.fail();
		}
		catch(Exception e){
			Assert.assertEquals("Not permitted amount", e.getMessage());
		}
	}
	
	@Test 
	public void formatZero(){
		Assert.assertEquals("0.00",format(BigDecimal.ZERO).toString());
	}
	
	@Test 
	public void formatSupThanHalf(){
		Assert.assertEquals("2.49",format(BigDecimal.valueOf(2.487d)).toString());
	}
	
	@Test 
	public void formatInfThanHalf(){
		Assert.assertEquals("2.48",format(BigDecimal.valueOf(2.483d)).toString());
	}
	
	@Test 
	public void formatHalfOddNumber(){
		Assert.assertEquals("2.42",format(BigDecimal.valueOf(2.415d)).toString());
	}
	
	@Test 
	public void formatHalEvenNumber(){
		Assert.assertEquals("2.46",format(BigDecimal.valueOf(2.465d)).toString());
	}

}
