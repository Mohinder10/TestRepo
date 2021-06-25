package hook;

import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utilities.ConfigReader;
import utilities.DriverFactory;

public class AppHook {

	Properties properties;
	private DriverFactory driverFactory;
	private WebDriver driver;
	private ConfigReader configReader;
	
	
	@Before(order=0)
	public void getProperty(){
		configReader= new ConfigReader();
		properties=configReader.initialize();
		
	}
	
	@Before(order=1)
	public void launchBrowser(){
		String browserName=properties.getProperty("browser");
		driverFactory=new DriverFactory();
		driverFactory.init_driver(browserName);
	}
	
	
	@AfterStep
	public void takeScreenShot(Scenario scenario){
		String screenshotName=scenario.getName().replaceAll(" ", "_");
		
		byte[] src=((TakesScreenshot)DriverFactory.getDriver()).getScreenshotAs(OutputType.BYTES);
		
		scenario.attach(src, "image/png", screenshotName);
	}
	
	
	@After
	public void tearDown(){	
		DriverFactory.getDriver().close();
		DriverFactory.getDriver().quit();
	}

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}
	
	
	
	
	
}
