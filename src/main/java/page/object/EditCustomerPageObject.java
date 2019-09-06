package page.object;

import org.openqa.selenium.WebDriver;

import common.AbstractPage;

public class EditCustomerPageObject extends AbstractPage{
	WebDriver driver;
	public EditCustomerPageObject(WebDriver mappingDriver) {
		driver = mappingDriver;
	}

	public boolean checkUpdateCustomerSuccessfully() {
		return checkDisplayDynamicLabel(driver, "Customer details updated Successfully!!!");
	}
}
