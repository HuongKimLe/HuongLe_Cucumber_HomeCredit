package page.object;

import org.openqa.selenium.WebDriver;

import common.AbstractPage;
import interfaceBankguru.AbstractPageUI;

public class NewCustomerPageObject extends AbstractPage{
	WebDriver driver;
	public NewCustomerPageObject(WebDriver mappingDriver) {
		driver = mappingDriver;
	}

	
	public void checkGenderType(String gender) {
		if(gender.toLowerCase()=="male") {
		waitElementVisible(driver, AbstractPageUI.DYNAMIC_CHECKBOX_INPUTFORM, "m","");
		clickLeftElement(driver, AbstractPageUI.DYNAMIC_CHECKBOX_INPUTFORM, "m","");
		//waitElementVisible(driver, AbstractPageUI.DYNAMIC_CHECKBOX, "m","and @checked");
		} else {
			waitElementVisible(driver, AbstractPageUI.DYNAMIC_CHECKBOX_INPUTFORM, "f","");
			clickLeftElement(driver, AbstractPageUI.DYNAMIC_CHECKBOX_INPUTFORM, "f","");
			//waitElementVisible(driver, AbstractPageUI.DYNAMIC_CHECKBOX, "f","and @checked");
		}
	}
	

	public boolean checkAddNewCustomerSuccessfully() {
		return checkDisplayDynamicLabel(driver, "Customer Registered Successfully!!!");
	}
	
}
