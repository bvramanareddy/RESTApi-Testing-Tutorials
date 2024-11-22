package com.ramana.smoke;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseClass {
 protected WebDriver driver;

	


	@BeforeClass
	public void intializeDriver() {
	this.driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.google.com");
	}
	public WebDriver getDriver() {
		return this.driver;
	}

	@AfterClass
	public void tearDown() {
		driver.close();
	}

	public void failedTestCapturingScreenshor(String testMethodName) {
		//String timeString=new  SimpleDateFormat("yyyy.mm.dd").format(new Date());
		
	try {
			if(driver==null)
			{
				throw new IllegalStateException("Web Driver is not instantiated");
			}
	
		TakesScreenshot screnshot= (TakesScreenshot)this.driver;
		File file= screnshot.getScreenshotAs(OutputType.FILE);
				
		File destFile= new File("./Screenshots/FailedTest.png");
		
	
			FileUtils.copyFile(file, destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	catch (IllegalStateException e) {
		System.out.println(e.getMessage());
	}
		}
	}
