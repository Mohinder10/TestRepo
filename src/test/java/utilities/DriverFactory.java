package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
	
	public static ThreadLocal<WebDriver> threadLocal= new ThreadLocal<>();
	
	
	public WebDriver init_driver(String browser){
		
		System.out.println("Opening in "+browser+"browser...");
		
		switch (browser.toUpperCase()) {
		case "CHROME":
			WebDriverManager.chromedriver().setup();
			threadLocal.set(new ChromeDriver());
			break;
		case "FIREFOX":
			WebDriverManager.firefoxdriver().setup();
			threadLocal.set(new FirefoxDriver());
			break;
		default:
			System.out.println("Invalid browser name..");
			break;
		}
		
		return getDriver();
		
	}
	
	
	public static synchronized WebDriver getDriver(){
		return threadLocal.get();
	}
	
}
