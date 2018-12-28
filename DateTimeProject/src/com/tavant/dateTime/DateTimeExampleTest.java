package com.tavant.dateTime;

import java.time.LocalDate;

import org.junit.Test;
import org.junit.

public class DateTimeExampleTest
{
	AbdulKalam ak = new AbdulKalam();
	@Test
	public void KalamWasOn15October1931()
	{
		LocalDate dateOfBirth = ak.dateOfBirht();
		assertThat(dateOfBirth.toString(),equals("1931-10-15"));
		
	}

 
}
