package page.object;

import org.openqa.selenium.WebDriver;

import common.AbstractPage;
import interfaceBankguru.RegisterForm;

public class RegisterPageObject extends AbstractPage {
	WebDriver driver;
	public RegisterPageObject(WebDriver mappingDriver) {
		driver = mappingDriver;
	}

	/**
	 * LIST ACTIONS
	 * 1. INPUT EMAIL TEXTBOX
	 * 2. CLICK SUBMIT BUTTON
	 * 3. GET USER ID
	 * 4. GET PASSWORD
	 * 5. NAVIGATE TO LOGIN PAGE
	 */
	
	public void inputRandomEmailToTextbox() {
		waitElementVisible(driver, RegisterForm.EMAIL_TEXTBOX);
		String emailAddress = "auto06" + getTodayString("ddMMyyHHmmss")+"@lv.com";
		sendKeyElement(driver,RegisterForm.EMAIL_TEXTBOX, emailAddress);
	}
	
	public void clickSubmitButton() {
		waitElementVisible(driver, RegisterForm.SUBMIT_BUTTON);
		clickLeftElement(driver, RegisterForm.SUBMIT_BUTTON);
	}
	
	public String getUserIdGeneratedByRegisterPage() {
		waitElementVisible(driver, RegisterForm.USERID_TEXT);
		return getTextElement(driver, RegisterForm.USERID_TEXT);
	}
	
	public String getPasswordGeneratedByRegisterPage() {
		waitElementVisible(driver, RegisterForm.PASSWORD_TEXT);
		return getTextElement(driver, RegisterForm.PASSWORD_TEXT);
	}
	
	public LoginPageObject navigateToLoginPage(String urlLoginPage) {
		navigateToPage(driver, urlLoginPage);
		return new LoginPageObject(driver);
	}
}
