package page.object;

import org.openqa.selenium.WebDriver;

import common.AbstractPage;

public class NewAccountPageObject extends AbstractPage {
	WebDriver driver;
	

	public NewAccountPageObject(WebDriver driver) {
		super();
		this.driver = driver;
	}
	
	public boolean checkAccountGeneratedSuccessfully() {
		return checkDisplayDynamicLabel(driver, "Account Generated Successfully!!!");
	}
	
}
