package com.cheapoair.ca;

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

public class E2EFlightSearchRoundTrip {

	@Test

	public void flightSearchCanadaToIndia() throws InterruptedException {

		String fromLocation = "Toronto Pearson";
		String toLocation = "Hyderabad";

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--incognito");
		options.addArguments("start-maximized");

		WebDriver driver = new ChromeDriver(options);
		// driver.manage().deleteAllCookies();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

		driver.get("https://www.cheapoair.ca/");
		Thread.sleep(1500L);

		WebElement xSVGIconElement = driver.findElement(By.xpath("(//*[local-name()='svg'])[17]"));
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", xSVGIconElement);
		wait.until(ExpectedConditions.visibilityOf(xSVGIconElement));
		xSVGIconElement.click();
		WebElement fromElement = driver.findElement(By.xpath("(//input[@class='form-control pr-4'])[1]"));
		wait.until(ExpectedConditions.elementToBeClickable(fromElement));
		fromElement.sendKeys(fromLocation);

		Thread.sleep(1500L);

		List<WebElement> fromList = driver.findElements(By.cssSelector("div[class='suggestion-displayText']"));
		wait.until(ExpectedConditions.visibilityOfAllElements(fromList));
		for (WebElement element : fromList) {
			String suggestedString = element.getText();
			if (suggestedString.contains("YYZ")) {
				System.out.println("Selected From Location is " + suggestedString);

				element.click();
				break;
			}
		}
		Thread.sleep(1000L);

		WebElement toElement = driver.findElement(By.xpath("(//input[@class='form-control pr-4'])[2]"));
		wait.until(ExpectedConditions.visibilityOf(toElement));
		toElement.sendKeys(toLocation);
		Thread.sleep(1500L);

		List<WebElement> toList = driver.findElements(By.xpath("//div[@class='suggestion-displayText']"));
		wait.until(ExpectedConditions.visibilityOfAllElements(toList));
		for (WebElement element : toList) {
			String destinationString = element.getText();

			if (destinationString.contains("HYD")) {
				System.out.println("Selection To Location is " + destinationString);
				element.click();
				break;
			}
		}
		Thread.sleep(1500L);

		WebElement fromDateCalender = driver
				.findElement(By.xpath("//div[@id='widgetcalendar']//a[@class=' month-date is--today']"));
		wait.until(ExpectedConditions.visibilityOf(fromDateCalender));

		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", fromDateCalender);

		fromDateCalender.click();
		String dateSelectedString = fromDateCalender.getText();
		System.out.println("SelectedDate From Date is :" + dateSelectedString);

		WebElement toDateCaleder = driver.findElement(By.xpath("//a[@aria-label='28 December 2024']"));
		System.out.println("To Date Entered is " + toDateCaleder.getText());

		toDateCaleder.click();

		WebElement searchElement = driver.findElement(By.cssSelector("#searchNow"));
		searchElement.click();

		Thread.sleep(2000L);

		WebElement scrollingBanner = driver
				.findElement(By.xpath("//article[@class='matrix__load box-outer-skin col-12 pl-0 pr-0 mb-5']"));
		wait.until(ExpectedConditions.invisibilityOf(scrollingBanner));

		Thread.sleep(1000L);

		WebElement bannerPresentElement = driver.findElement(By.xpath("//div[@class='slick-list']"));

		wait.until(ExpectedConditions.visibilityOf(bannerPresentElement));

		String firstRowPriceString = driver.findElement(By.xpath(
				"//section[@class='listing__contracts contract-section row contracts ']//header[@id='contIndex__0']//span[@class='fpamount fpRoundToFixDecimal']"))
				.getText();
		System.out.println("Printing the first appeared price on the results page: " + firstRowPriceString);
		List<WebElement> getPriceElements = driver.findElements(By.xpath(
				"//section[@class='listing__contracts contract-section row contracts ']//header[@data-test='selectContract']//section//div[1]//span[@class='fpamount fpRoundToFixDecimal']"));
		wait.until(ExpectedConditions.visibilityOfAllElements(getPriceElements));

		float lowest = Float.MAX_VALUE;
		int lowestPriceIndex= -1;
		for (int i=0; i<getPriceElements.size(); i++) {
			
			
			String desPrice = getPriceElements.get(i).getText();
			System.out.println("Captured Price from the site : " + desPrice);
			String cleanAmount = desPrice.replace(",", "");
			System.out.println("Price After removing the , from it: " + cleanAmount);

			float amountFloat = Float.parseFloat(cleanAmount);
			if (amountFloat < lowest) {
				lowest = amountFloat;
				lowestPriceIndex=i;

			}
			System.out.println(" ");

		}
		System.out.println("<===================Lowest Price for the flights from " +fromLocation+ " TO "+ toLocation+ " is ===============> "+ lowest);
		
		
		WebElement currencySelector = driver.findElement(By.xpath("//span[@role='link' and @aria-label='Selected currencey CADC$. Press enter to change currency']"));
		currencySelector.click();
		
		List<WebElement> countriesList= driver.findElements(By.xpath("//ul[@id='Currency_Popup']//span"));
		for(WebElement element: countriesList)
		{
			if(element.getText().contains("CAD"))
			{
				element.click();
				break;
			}
		}
//	List<WebElement> selectButtons= 	driver.findElements(By.xpath("//section[@class='listing__contracts contract-section row contracts ']//header[@data-test='selectContract']//section//div[1]//span[@class='fpamount fpRoundToFixDecimal']/following::button[1]"));	
	List<WebElement> selectButtons= 	driver.findElements(By.xpath("//header//button[@title='SELECT']"));
	if(lowestPriceIndex!=-1)
	{
		WebElement lowestPriceSelectButtonElement=  selectButtons.get(lowestPriceIndex);
		wait.until(ExpectedConditions.elementToBeClickable(lowestPriceSelectButtonElement));
		
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", lowestPriceSelectButtonElement);
		Thread.sleep(1000L);
		//lowestPriceSelectButtonElement.click();
		
		jsExecutor.executeScript("arguments[0].click();", lowestPriceSelectButtonElement);

		System.out.println("Selected the Lowest Price Airline option---> Clikced on SELECT BUTTON");
		
	}
	else {
		System.out.println("No Lowest element found or No Select Button Cliked");
	}
	
	
//	 List<WebElement> selectButtons= driver.findElements(By.xpath("//div//span[@class='pr-1'][normalize-space()='Select']"));
//	 wait.until(ExpectedConditions.visibilityOfAllElements(selectButtons));

		System.out.println("Title of the current  Page :" + driver.getTitle());

	//	driver.quit();

	}

}
