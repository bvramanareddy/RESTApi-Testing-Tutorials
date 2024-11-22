package com.makemytripsearch.ramana;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class MakeMyTripSearch {

	@Test

	public void getFlightPrices() throws InterruptedException {
		
		ChromeOptions options=  new ChromeOptions();
		options.addArguments("start-maximized");
		options.addArguments("--incognito");
		WebDriver driver = new ChromeDriver(options);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		driver.manage().deleteAllCookies();
		driver.get("https://www.makemytrip.com/");
		driver.manage().deleteAllCookies();
		Thread.sleep(2000L);
		driver.findElement(By.xpath("//span[@class='commonModal__close']")).click();
		

		WebElement fromCityElement = driver.findElement(By.xpath("//input[@id='fromCity']"));
		wait.until(ExpectedConditions.elementToBeClickable(fromCityElement));
		fromCityElement.click();
		//wait.until(ExpectedConditions.visibilityOf(fromCityElement));

		WebElement enterFromElement = driver.findElement(By.cssSelector("input[placeholder='From']"));
		wait.until(ExpectedConditions.elementToBeClickable(enterFromElement));
		enterFromElement.sendKeys("Toronto");
		Thread.sleep(2000);

		//List<WebElement> fromList = driver
				//.findElements(By.xpath("//div[@class='makeFlex flexOne column']/descendant::div[3]"));

		List<WebElement> newList = driver.findElements(By.xpath("//ul[@role='listbox']//li[@role='option']"));

		for (int i = 0; i < newList.size(); i++) {
			String newListString = newList.get(i).getText();
			System.out.println(newListString);
			if (newListString.contains("Hamilton")) {
				newList.get(i).click();
			}
		}
		
		
		WebElement toCityElement= driver.findElement(By.cssSelector("input[placeholder='From']"));
		wait.until(ExpectedConditions.elementToBeClickable(toCityElement));
		toCityElement.clear();
		toCityElement.click();

		// driver.quit();
	}
}
