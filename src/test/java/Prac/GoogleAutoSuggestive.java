package Prac;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class GoogleAutoSuggestive {
	
	@Test
	
	public void clickGoogleAutoSuggestDropDown() throws InterruptedException
	{
		String searchKeyWord= "Selenium Testing";
		
		ChromeOptions options =  new ChromeOptions();
		options.addArguments("--disable-notifications");
		
		WebDriver driver= new ChromeDriver(options);
		driver.manage().window().maximize();
		
		driver.get("https://www.google.com");
		WebElement searchBox=  driver.findElement(By.xpath("//textarea[@id='APjFqb']"));
		searchBox.sendKeys("Selenium Test");
		
		
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(15));
//	WebElement overlayingBanner= 	driver.findElement(By.xpath("//div[@class='WrcADd']//button[@class='M6CB1c rr4y5c']"));
//	wait.until(ExpectedConditions.visibilityOf(overlayingBanner));
	//searchBox.sendKeys("Selenium Test");
	//overlayingBanner.click();
	JavascriptExecutor jsExecutor= (JavascriptExecutor)driver;
//	jsExecutor.executeScript("arguments[0].click();", overlayingBanner);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@class='wM6W7d']//span[contains(.,'selenium test')]")));
		
		List<WebElement> autoDropDownElements= driver.findElements(By.xpath("//div[@class='wM6W7d']//span[contains(.,'selenium test')]"));
		
		for(WebElement element: autoDropDownElements)
		{
			String textCaptured=  element.getText();
			System.out.println(textCaptured);
			if(textCaptured.contains("selenium testing"))
			{
				element.click();
				//jsExecutor.executeScript("arguments[0].click();", element);
				break;
			}
			
		}
		Thread.sleep(2000L);
String titleString =		driver.getTitle();
System.out.println(titleString);
driver.quit();
	}
	
	

}
