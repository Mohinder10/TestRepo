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

public class ContactScreen {

	BaseScreen baseScreen = new BaseScreen();

	private DriverFactory driverFactory;
	WebDriver driver= DriverFactory.getDriver();
	
	
	public void validateErrors(DataTable dataTable) {

		List<Map<String, String>> list = dataTable.asMaps(String.class, String.class);
		Map<String, String> map = new HashMap<>();
		int rowCount = list.size();
		int countMsg = 0;
		
		baseScreen.waitForElement("//*[@id=\"forename\"]");
		
		if (rowCount > 0) {
			for (int count = 0; count < rowCount; count++) {
				map = list.get(count);
				switch (map.get("Field")) {
				case "Forename":
					WebElement foreName = driver.findElement(By.id("forename-err"));
					String forenameMsg = foreName.getText();
					if (forenameMsg.trim().equals(map.get("Error").trim())) {
						countMsg++;
					}
					break;

				case "Email":
					WebElement email = driver.findElement(By.id("email-err"));
					String emailMsg = email.getText();
					if (emailMsg.trim().equals(map.get("Error").trim())) {
						countMsg++;
					}
					break;

				case "Message":
					WebElement textBox = driver.findElement(By.id("message-err"));
					String textBoxMsg = textBox.getText();
					if (textBoxMsg.trim().equals(map.get("Error").trim())) {
						countMsg++;
					}
					break;
				}
			}
			System.out.println("Total number of error messages validated: " + countMsg);
			Assert.assertEquals(rowCount, countMsg);
		}

	}

	public void enterMandatoryData(DataTable dataTable) {

		Map<String, String> map = baseScreen.getTestDataMap(dataTable);
		baseScreen.waitForElement("//*[@id=\"forename\"]");
		baseScreen.enterValueInTextField("forename", map.get("Forename"));
		baseScreen.enterValueInTextField("email", map.get("Email"));
		baseScreen.enterValueInTextField("message", map.get("Message"));

	}

	public void validateErrorsAreAbsent() {

		Assert.assertEquals(0, driver.findElements(By.id("forename-err")).size());
		Assert.assertEquals(0, driver.findElements(By.id("email-err")).size());
		Assert.assertEquals(0, driver.findElements(By.id("message-err")).size());

	}
	
	
	public void verifySuccessMsg(DataTable dataTable){
		
		Map<String, String> map = baseScreen.getTestDataMap(dataTable);
		String firstName= map.get("Forename");
		WebElement successMsgElement;
		String successMsg;
		
		successMsgElement=baseScreen.waitForElement("//*[@class='alert alert-success']");
		successMsg = successMsgElement.getText();

		Assert.assertEquals("Thanks " + firstName + ", we appreciate your feedback.", successMsg);
		
	}

	public DriverFactory getDriverFactory() {
		return driverFactory;
	}

	public void setDriverFactory(DriverFactory driverFactory) {
		this.driverFactory = driverFactory;
	}

}
