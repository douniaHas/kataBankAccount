package fr.katas.bankaccountmanagement.tools;

import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.Test;

import static fr.bankaccountmanagement.tools.DateTools.formatDate;

public class DateToolsTest {

	@Test 
	public void formatDateNotNull(){
		Assert.assertEquals("01/03/2019",formatDate(LocalDateTime.of(2019, 03, 01, 01, 50)));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void formatDateNull(){
		formatDate(null);
	}
	
}
