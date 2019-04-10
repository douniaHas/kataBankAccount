package fr.bankaccountmanagement.tools;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class DateTools {
	private static final String DD_MM_YYYY = "dd/MM/yyyy";

	private DateTools() {
		//
	}

	public static String formatDate(LocalDateTime date){
		if(date == null){
			throw new IllegalArgumentException(); 
		}
		return date.format(DateTimeFormatter.ofPattern(DD_MM_YYYY)); 
	}

}
