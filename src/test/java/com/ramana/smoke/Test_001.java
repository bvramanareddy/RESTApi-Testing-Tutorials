package com.ramana.smoke;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Test_001 extends BaseClass {

	@Test(priority = 0)
	public void accessGoogle() {

		System.out.println(driver.getClass());
	}

	@Test(priority = 1)
	public void getTitle() {
		String titleString = driver.getTitle();
		Assert.assertEquals(titleString, "Google...");
	}

	@Test(priority = 3, dependsOnMethods = { "getTitle" })
	public void googleChecking() {
		System.out.println(driver.getTitle());
	}

	@Test(priority = 4)
	public void testClose() {
		System.out.println(driver.getCurrentUrl());
	}

}
