package com.ramana.smoke;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class MyListerner implements ITestListener {

	@Override
	public void onStart(ITestContext context) {
System.out.println("Once the test is begin its eexecution-------------");		
	}
	@Override
	public void onTestStart(ITestResult result) {
		System.out.println("Test Method is Started-------------");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("Test Method is PASSED-------------");
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("Test Method is Failed-------------");
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println("Test Method is Skipped-------------");
		
	}

	
	

	@Override
	public void onFinish(ITestContext context) {
		System.out.println("Test Execution is Completed-------------");
		
	}
	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		System.out.println("No IDEA");
		
	}

}
