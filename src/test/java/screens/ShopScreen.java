package screens;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.cucumber.datatable.DataTable;
import utilities.DriverFactory;

public class ShopScreen {

	BaseScreen baseScreen=new BaseScreen();
	private DriverFactory driverFactory;
	WebDriver driver= DriverFactory.getDriver();
	public static int totalQty=0;
	
	
	public void addItems(DataTable dataTable) {

		List<Map<String, String>> list = dataTable.asMaps(String.class, String.class);
		Map<String, String> map = new HashMap<>();
		int rowCount = list.size();
		int countItems = 0;
		String xpathBuy="";
		
		if (rowCount > 0) {
			for (int count = 0; count < rowCount; count++) {
				map = list.get(count);
				int itemQty=Integer.parseInt(map.get("Qty"));
				totalQty=totalQty+itemQty;
				String itemName=map.get("ItemName");
				System.out.println("Item Name: "+ itemName+ "\nItem Qty: "+itemQty);
				xpathBuy="//*[text()='"+itemName+"']"+"/..//a";
				baseScreen.waitForElement(xpathBuy);
				for(int num=0;num<itemQty;num++){
					driver.findElement(By.xpath(xpathBuy)).click();
				}
				xpathBuy="";
				countItems++;
			}
			System.out.println("Total number of items validated: " + countItems);
			Assert.assertEquals(rowCount, countItems);
		}

	}
	
	
	public void clickOnCart(){
		
		WebElement cart;
		System.out.println("Total Qty: "+totalQty);
		cart = driver.findElement(By.linkText("Cart ("+totalQty+")"));
		cart.click();
		
	}
	
	
	public void verifyItems(DataTable dataTable) {

		List<Map<String, String>> list = dataTable.asMaps(String.class, String.class);
		Map<String, String> map = new HashMap<>();
		int rowCount = list.size();
		String xpathQty="";
		totalQty=0;
		int expectedQty=0;
		String val="";
		
		if (rowCount > 0) {
			for (int count = 0; count < rowCount; count++) {
				map = list.get(count);
				int itemQty=Integer.parseInt(map.get("Qty"));
				String itemName=map.get("ItemName");
				System.out.println("Item Name: "+ itemName+ "\nItem Qty: "+itemQty);
				xpathQty="//*[text()=' "+itemName+"']"+"/..//input";
				totalQty=totalQty+itemQty;
				baseScreen.waitForElement(xpathQty);
				switch (itemName) {
				case "Funny Cow":
					val=driver.findElement(By.xpath(xpathQty)).getAttribute("value");
					expectedQty+=Integer.parseInt(val);
					break;

				case "Fluffy Bunny":
					val=driver.findElement(By.xpath(xpathQty)).getAttribute("value");
					expectedQty+=Integer.parseInt(val);
					break;

				default:
					System.out.println("Item not found in cart...");
					Assert.fail();
					break;
				}
				
				xpathQty="";
				val="";
			}
			
			Assert.assertEquals(expectedQty, totalQty);;
			
			
		}

	}


	public DriverFactory getDriverFactory() {
		return driverFactory;
	}


	public void setDriverFactory(DriverFactory driverFactory) {
		this.driverFactory = driverFactory;
	}
	
	
}
