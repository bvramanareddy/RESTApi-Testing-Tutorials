package com.ramana.smoke;


import java.text.SimpleDateFormat;
import java.util.Date;



import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager extends BaseClass implements ITestListener {

	
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extentReports;
	public ExtentTest test; 
	
	@Override
	public void onStart(ITestContext context) {
		
		String timeString=new  SimpleDateFormat("yyyy.mm.dd").format(new Date());
		String reportName = "Test-Report"+timeString+".html";
		sparkReporter= new ExtentSparkReporter(System.getProperty("user.dir")+"/Extent-Reports/"+reportName);
		
		sparkReporter.config().setDocumentTitle("Ramana Reddy Automation Reportz");
		sparkReporter.config().setReportName("Smoke Testing- Reddys");
		sparkReporter.config().setTheme(Theme.DARK);
		
		extentReports= new ExtentReports();
		extentReports.attachReporter(sparkReporter);
		
		extentReports.setSystemInfo("Computer Name", "Amazon AWS");
		extentReports.setSystemInfo("Environment", "PROD");
		extentReports.setSystemInfo("Tester Name", "RamanA");
		extentReports.setSystemInfo("OS", "Windows");
		extentReports.setSystemInfo("Browser Name", "Chrome");
	}
	@Override
	public void onTestStart(ITestResult result) {
		
		test= extentReports.createTest(result.getName());
		test.log(Status.INFO, "***********The Test is started************"+ result.getName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {

		test= extentReports.createTest(result.getName());
		test.log(Status.PASS, "Test Case is Passed: " +result.getName());
		
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String failedTest = result.getName();
		String screenShotDir= "./Screenshots/";
		
		failedTestCapturingScreenshor(failedTest);
		test= extentReports.createTest(result.getName());
		test.log(Status.FAIL, "The test is failed: "+ failedTest);
		test.log(Status.FAIL, "The test  is failed because: "+ result.getThrowable());
	
		failedTestCapturingScreenshor(failedTest);
		test.log(Status.FAIL,"Test Failure").addScreenCaptureFromPath(screenShotDir+failedTest+".png");
		//test.log(Status.FAIL,"Test Failure").fail(MediaEntityBuilder.createScreenCaptureFromPath(screenShotDir+failedTest+".png").build());

		
			
			
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {

		test= extentReports.createTest(result.getName());
		test.log(Status.SKIP, "The test is Skipped: "+ result.getName());
		test.log(Status.SKIP, "The test is Skipped because: " +result.getThrowable());
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
	}

	

	@Override
	public void onFinish(ITestContext context) {
		extentReports.flush();
	}

}
