package stepDefinitions;

import org.openqa.selenium.WebDriver;

import common.AbstractPage;
import common.AbstractTest;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumberOptions.Hooks;

public class CommonSteps extends AbstractTest {
		WebDriver driver;
		AbstractPage abs = new AbstractPage();
		String randomEmailValue;
		
		public  CommonSteps() {
			driver = Hooks.openBrowser();
		}
	
		@Given("^I input to \"([^\"]*)\" textbox type input with \"([^\"]*)\"$")
		public void iInputToTextboxWith(String nameTextbox, String value){
			abs.inputIntoDynamicTextboxWithTypeInput(driver, value, nameTextbox);
		}
		
		@Given("^I input to \"([^\"]*)\" textbox type input contains with \"([^\"]*)\"$")
		public void iInputToTextboxContainsWith(String nameTextbox, String value){
			abs.inputIntoDynamicTextboxWithTypeInputContainsText(driver, value, nameTextbox);
		}
		
		@Given("^I input to \"([^\"]*)\" textbox type input with \"([^\"]*)\" random time value$")
		public void iInputToTextboxWithRandomTimeValue(String nameTextbox, String value){
			randomEmailValue = value + AbstractPage.getTodayString("ddMMyyHHmmss")+"@lv.com";
			abs.inputIntoDynamicTextboxWithTypeInput(driver, randomEmailValue, nameTextbox);
		}
		
		@Given("^I input to \"([^\"]*)\" textarea with \"([^\"]*)\"$")
		public void iInputToTextAreaWith(String nameTextbox, String value){
			abs.inputIntoDynamicTextboxWithTypeArea(driver, value, nameTextbox);
		}
		
		@Given("^I input to \"([^\"]*)\" datePicker with \"([^\"]*)\"$")
		public void iInputTodatePickerWith(String nameTextbox, String value){
		   abs.inputValueDynamicTextboxNonTextTypeInput(driver, value, nameTextbox);
		}

		@When("^I click to \"([^\"]*)\" button$")
		public void iClickToButton(String nameButton){
			abs.clickDynamicButtonWithNameInput(driver, nameButton);
		}
		
		@When("^I open \"([^\"]*)\" Page$")
		public void iOpen(String nameButton){
			if(nameButton.toLowerCase().equals("new customer"))
				abs.openNewCustomerPage(driver);
			else if(nameButton.toLowerCase().equals("edit customer"))
				abs.openEditNewCustomerPage(driver);
			else if(nameButton.toLowerCase().equals("new account"))
				abs.openNewAccountPage(driver);
			else if(nameButton.toLowerCase().equals("deposit"))
				abs.openDepositPage(driver);
			else if(nameButton.toLowerCase().equals("withdrawal"))
				abs.openWithdrawalPage(driver);
			else if(nameButton.toLowerCase().equals("fund transfer"))
				abs.openFundTransferPage(driver);
			else if(nameButton.toLowerCase().equals("balance enquiry"))
				abs.openBalanceEnquiryPage(driver);
			
		}
		
		@Given("^I check \"([^\"]*)\" checkbox with \"([^\"]*)\"$")
		public void iCheckTheCheckbox(String nameCheckbox, String valueCheckbox){
		   abs.checkDynamicCheckbox(driver, nameCheckbox, valueCheckbox);
		   
		}
		
		@When("^I select \"([^\"]*)\" from dropdown with \"([^\"]*)\"$")
		public void iSelectDropdownList(String nameDropdown, String valueSelect){
			   abs.selectDynamicDropdownListTypeSelect(driver, valueSelect, nameDropdown);
			   
			}
		
		
		@Then("^I verify message \"([^\"]*)\"$")
		public void IVerifyIsDisplayedWithMessage(String msgVerify)  {
			verifyTrue(abs.checkDisplayDynamicLabel(driver, msgVerify));
		}
		
		@Then("^I verify value Table for \"([^\"]*)\" cell with \"([^\"]*)\"$")
		public void IVerifyValueTable( String nameLocator,String value)  {
			if(value.toLowerCase().equals("rdemail")) 
				verifyEquals( abs.getTextValueForDynamicTable(driver, nameLocator),randomEmailValue);
			else if(nameLocator.toLowerCase().equals("birthdate"))
				verifyEquals( abs.getTextValueForDynamicTable(driver, nameLocator),
						value.split("/")[2]+"-"+value.split("/")[0]+ "-" + value.split("/")[1]);
			else verifyEquals( abs.getTextValueForDynamicTable(driver, nameLocator),value);
		}





}
