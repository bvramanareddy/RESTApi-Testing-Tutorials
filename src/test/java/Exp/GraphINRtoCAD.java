package Exp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class GraphINRtoCAD {
	
	@Test
	
	public void graphAutomation()
	{
	WebDriver driver = new ChromeDriver();
	
	driver.get("https://www.xe.com/currencycharts/?from=INR&to=CAD");
	
	WebElement e= driver.findElement(By.xpath("(//div[@class='recharts-wrapper']//*[name()='svg'])")); 
	Actions actions= new Actions(driver);
	actions.moveToElement(null)
	
	
	 
	}
	

}
