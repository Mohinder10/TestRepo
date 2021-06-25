package screens;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import com.aventstack.extentreports.ExtentReports;

import io.cucumber.datatable.DataTable;
import utilities.ConfigReader;
import utilities.DriverFactory;

public class BaseScreen {

	Properties properties=new Properties();
	ConfigReader configReader= new ConfigReader();
	ExtentReports extentReports= new ExtentReports();
	
	private DriverFactory driverFactory;
	WebDriver driver= DriverFactory.getDriver();
	
	public void launchWebsite(){
		properties=configReader.initialize();
		String baseUrl= properties.getProperty("url");
		driver.get(baseUrl);
		driver.manage().window().maximize();
	}
	
	
	public WebElement waitForElement(String path){
		FluentWait<WebDriver> fluentWait;
		WebElement webElement;
		fluentWait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(20)).pollingEvery(Duration.ofMillis(3000))
				.ignoring(NoSuchElementException.class);
		
		webElement=fluentWait.until(ExpectedConditions.elementToBeClickable(By.xpath(path)));
		return webElement;
	}

	
	public void clickOnLink(String linkText){
		WebElement webElement;
		FluentWait<WebDriver> fluentWait;
		fluentWait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(10)).pollingEvery(Duration.ofMillis(3000))
				.ignoring(NoSuchElementException.class);
		webElement = fluentWait
				.until(ExpectedConditions.elementToBeClickable(By.linkText(linkText)));
		webElement.click();
	}
	
	
	public Map<String, String> getTestDataMap(DataTable dataTable){
		
		Map<String, String> map=new HashMap<>();
		
		try{
			List<Map<String,String>> list= dataTable.asMaps(String.class,String.class);
			if(list.size()>0){
				map=list.get(0);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return map;
		
		
		
	}
	
	
	public void enterValueInTextField(String locatorVal, String text){
		WebElement field=null;
		field= driver.findElement(By.id(locatorVal));
		field.sendKeys(text);
	}


	public DriverFactory getDriverFactory() {
		return driverFactory;
	}


	public void setDriverFactory(DriverFactory driverFactory) {
		this.driverFactory = driverFactory;
	}
	
	

}



