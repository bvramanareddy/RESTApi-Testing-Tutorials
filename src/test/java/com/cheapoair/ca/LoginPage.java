package com.cheapoair.ca;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class LoginPage extends BaseTest {
	
	public LoginPage() {
		super();
	}
	@Test
	
	public void accessGoogle()
	{
		
		String titleofGoogle= driver.getTitle();
		System.out.println(titleofGoogle);
	}

}
