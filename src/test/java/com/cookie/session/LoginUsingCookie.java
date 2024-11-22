package com.cookie.session;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginUsingCookie extends GetCookieJcrew {

	public static void main(String[] args)   {
		
		ChromeOptions options = new ChromeOptions();
		options.setAcceptInsecureCerts(true);  // Ignore SSL errors
		options.addArguments("start-maximized");
		options.addArguments("--disable-cache");
		options.addArguments("--Incognito");
		WebDriver driver = new ChromeDriver(options);
		

		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));

		driver.get("https://www.jcrew.com/ca/");
	driver.findElement(By.xpath("//*[contains(text(), 'START SHOPPING')]")).click();
		
		
	for (Cookie cookie : cookies) {
		driver.manage().addCookie(cookie);
		
	}

		
//		Cookie sessionCookie= new Cookie("checkout_jwt", tokenValue);
//		driver.manage().addCookie(sessionCookie);
//		
		
//		Cookie sessionCookie = new Cookie.Builder("checkout_jwt", tokenValue)
//                .domain("www.jcrew.com")      // The domain of your application
//                .path("/")                  // The path for which the cookie is valid
//                .isHttpOnly(true)           // Example of making it HttpOnly if required
//                .isSecure(true)             // If the website is HTTPS
//                .build();
//		
		
		
	driver.navigate().refresh();

	WebElement signIn= 	driver.findElement(By.xpath("//button[normalize-space()='Sign In']"));
	WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(15));
	wait.until(ExpectedConditions.visibilityOf(signIn));
	signIn.click();
		
		driver.navigate().to("https://www.jcrew.com/ca/l/account/details");
		
		System.out.println("Successfully Logged into Jcrew with Token Value ");
		
		
		driver.quit();
		
	}
}
	

