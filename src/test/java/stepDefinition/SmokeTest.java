package stepDefinition;

import org.openqa.selenium.WebDriver;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import screens.BaseScreen;
import screens.ContactScreen;
import screens.ShopScreen;
import utilities.*;

public class SmokeTest {
 

	WebDriver driver=DriverFactory.getDriver();
	
	BaseScreen baseScreen=new BaseScreen();
	ContactScreen contactScreen= new ContactScreen();
	ShopScreen shopScreen= new ShopScreen();
	
	@Given("Open browser and start the application")
	public void open_browser_and_start_the_application() {
		baseScreen.launchWebsite();
	}
	
	@And("user clicks on {string} link")
	public void user_clicks_on_link(String linkText) {
	    baseScreen.clickOnLink(linkText);
	}

	@And("user validates errors")
	public void user_validates_errors(DataTable dataTable) {
		contactScreen.validateErrors(dataTable);
	}

	@And("user populates mandatory fields")
	public void user_populates_mandatory_fields(DataTable dataTable) {
		contactScreen.enterMandatoryData(dataTable);

	}

	@Then("user validates all errors are gone")
	public void user_validates_all_errors_are_gone() {
		contactScreen.validateErrorsAreAbsent();
	}

	@Then("user validates successful submission message")
	public void user_validates_successful_submission_message(DataTable dataTable) {
		contactScreen.verifySuccessMsg(dataTable);
	}

	@Given("user buys item")
	public void user_buys_item(DataTable dataTable) {
	    shopScreen.addItems(dataTable);
	}
	
	@When("user clicks on cart menu")
	public void user_clicks_on_cart_menu() {
		shopScreen.clickOnCart();
	}

	@Then("user validates items are in the cart")
	public void user_validates_items_are_in_the_cart(DataTable dataTable) {
		shopScreen.verifyItems(dataTable);
	}

}
