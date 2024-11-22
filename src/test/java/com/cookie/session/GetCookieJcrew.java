package com.cookie.session;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class GetCookieJcrew {
	
	static String tokenValue;
	static String requiredToken;
	static Set<Cookie> cookies;

	public static void main(String[] args) {

		
		WebDriver driver= new ChromeDriver();
		
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
	
//	Cookie sessionCookie= driver.manage().getCookieNamed("checkout_jwt");
//	  tokenValue= sessionCookie.getValue();
	// requiredToken = tokenValue.split(";")[0];
	// System.out.println(requiredToken);
	 System.out.println(tokenValue);
	
	
	driver.close();
			
	}

}
