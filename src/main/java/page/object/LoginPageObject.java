package page.object;

import org.openqa.selenium.WebDriver;

import common.AbstractPage;
import common.PageFactoryManage;
import interfaceBankguru.AbstractPageUI;
import interfaceBankguru.LoginForm;

public class LoginPageObject extends AbstractPage {
	WebDriver driver;
	public LoginPageObject(WebDriver mappingDriver) {
		driver = mappingDriver;
	}

	/**
	 * LIST ACTIONS
	 * 1. GET URL LOGIN PAGE
	 * 2. INPUT USERID INTO TEXTBOX
	 * 3. INPUT PASSWORD INTO TEXTBOX
	 * 4. CLICK LOGIN BUTTON
	 * 5. CLICK 'HERE' LINK
	 */
	public String getLoginURL() {
		return getCurrentURL(driver);
	}
	
	public void inputUserIDToTextbox(String userId) {
		inputIntoDynamicTextboxWithTypeInput(driver, userId, "UserID");
	}
	
	public void inputPasswordToTextbox(String password) {
		inputIntoDynamicTextboxWithTypeInput(driver, password, "Password");
	}
	
	public HomePageObject clickLoginButton() {
		clickDynamicButtonWithNameInput(driver, "LOGIN");
		return PageFactoryManage.getHomePageObject(driver);
	}
	
	public RegisterPageObject clickHereLink() {
		waitElementVisible(driver, AbstractPageUI.DYNAMIC_MENU_LINK,"here");
		clickLeftElement(driver, AbstractPageUI.DYNAMIC_MENU_LINK, "here");
		return PageFactoryManage.getRegisterPageObject(driver);
	}
	
	public boolean isLoginFormDisplayed() {
		return isDisplayElement(driver, LoginForm.LOGIN_FORM);
	}

}
