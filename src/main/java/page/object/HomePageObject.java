package page.object;

import org.openqa.selenium.WebDriver;

import common.AbstractPage;
import interfaceBankguru.HomePageForm;

public class HomePageObject extends AbstractPage{
	WebDriver driver;
	public HomePageObject(WebDriver mappingDriver) {
		driver = mappingDriver;
	}

	/**
	 * LIST ACTIONS
	 * 1. VERIFY WELCOME TEXT APPEAR!!!
	 * 2. CLICK LOGOUT BUTTON
	 * 3. ACCEPT ALERT LOGOUT
	 */
	
	public boolean verifyWelcomeLoginSuccessfullyAppear() {
		waitElementVisible(driver, HomePageForm.WELCOME_TEXT);
		return isDisplayElement(driver, HomePageForm.WELCOME_TEXT);
	}
		
}
