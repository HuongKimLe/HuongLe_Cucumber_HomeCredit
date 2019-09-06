package stepDefinitions;

import org.openqa.selenium.WebDriver;

import common.AbstractPage;
import common.AbstractTest;
import cucumber.api.java.en.When;
import cucumberOptions.Hooks;

public class NewCustomerSteps extends AbstractTest {
		WebDriver driver;
		public static String customerID;
		AbstractPage abs = new AbstractPage();
		public  NewCustomerSteps() {
			driver = Hooks.openBrowser();
		}
		
		@When("^I get Customer ID$")
		public void iGetCustomerID(){
			customerID = abs.getTextValueForDynamicTable(driver, "Customer ID");
		}
		

	


}
