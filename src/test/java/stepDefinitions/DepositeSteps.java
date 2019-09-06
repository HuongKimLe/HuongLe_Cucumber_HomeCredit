package stepDefinitions;

import org.openqa.selenium.WebDriver;

import common.AbstractPage;
import common.AbstractTest;
import cucumber.api.java.en.When;
import cucumberOptions.Hooks;

public class DepositeSteps extends AbstractTest {
		WebDriver driver;
		public static String accountID;
		AbstractPage abs = new AbstractPage();
		public  DepositeSteps() {
			driver = Hooks.openBrowser();
		}
	
		@When("^I input Account ID$")
		public void iInputCustomerID(){
			abs.inputIntoDynamicTextboxWithTypeInput(driver, NewAccountSteps.accountID, "Account No");
		}
		
		


}
