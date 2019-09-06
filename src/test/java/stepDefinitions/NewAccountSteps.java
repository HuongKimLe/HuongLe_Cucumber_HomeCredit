package stepDefinitions;

import org.openqa.selenium.WebDriver;

import common.AbstractPage;
import common.AbstractTest;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumberOptions.Hooks;

public class NewAccountSteps extends AbstractTest {
		WebDriver driver;
		public static String accountID;
		AbstractPage abs = new AbstractPage();
		public  NewAccountSteps() {
			driver = Hooks.openBrowser();
		}
	
		@When("^I input Customer ID$")
		public void iInputCustomerID(){
			abs.inputIntoDynamicTextboxWithTypeInput(driver, NewCustomerSteps.customerID, "Customer id");
		}
		
		@When("^I get Account ID$")
		public void iGetAccountID(){
			accountID = abs.getTextValueForDynamicTable(driver, "Account ID");
		}
		
		@Then("^I verify Customer ID$")
		public void iVerifyCustomerID(){
			verifyEquals( abs.getTextValueForDynamicTable(driver, "Customer ID"),NewCustomerSteps.customerID);
		}
	


}
