package com.cookie.session;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class TestCookieHandling {
	public static WebDriver driver;
	Set<Cookie> cookies;
ChromeOptions options;
	
	@Test
	
	public void getCookies()
	{
		options= new ChromeOptions();
		options.setAcceptInsecureCerts(true);  // Ignore SSL errors
		options.addArguments("start-maximized");
		options.addArguments("--disable-cache");
		options.addArguments("--Incognito");
driver= new ChromeDriver(options);
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get("https:www.jcrew.com/CA/");
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//*[contains(text(), 'START SHOPPING')]")).click();

	driver.findElement(By.xpath("//button[@data-qaid='navDesktopSignInModalButton']")).click();
	driver.findElement(By.xpath("//*[@id=\"loginEmail\"]")).sendKeys("7sep@aol.com");
	driver.findElement(By.xpath("//*[@id=\"loginPassword\"]")).sendKeys("Abc@12345");
	
	cookies= driver.manage().getCookies();
	
	for (Cookie cookie :cookies) {
		System.out.println("Cookie Name: "+ cookie);
		System.out.println("Cookie Value: "+ cookie.getValue());
	}
	
	driver.close();
	
	}
	
	@Test
	
	public void usingGeneratedCookies() throws InterruptedException
	{
		driver= new ChromeDriver(options);
		
		driver.navigate().to("https://www.jcrew.com/ca/");
		driver.findElement(By.xpath("//*[contains(text(), 'START SHOPPING')]")).click();
			
			
		for (Cookie cookie : cookies) {
			driver.manage().addCookie(cookie);
			
		}
		
driver.navigate().to("https://www.jcrew.com/ca/l/account/order-history");
//		WebElement signIn= 	driver.findElement(By.xpath("//button[normalize-space()='Sign In']"));
//		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(15));
//		wait.until(ExpectedConditions.visibilityOf(signIn));
//		signIn.click();
		
Thread.sleep(3000L);
		driver.quit();
		


	}

}
