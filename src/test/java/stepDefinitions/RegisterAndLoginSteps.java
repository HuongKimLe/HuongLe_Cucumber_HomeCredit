package stepDefinitions;

import org.openqa.selenium.WebDriver;

import common.AbstractTest;
import common.PageFactoryManage;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumberOptions.Hooks;
import page.object.HomePageObject;
import page.object.LoginPageObject;
import page.object.RegisterPageObject;

public class RegisterAndLoginSteps extends AbstractTest {
		WebDriver driver;
		public static String userID, password, loginURL;
		private RegisterPageObject registerPO;
		private LoginPageObject loginPO;
		private HomePageObject homepagePO;
		
		
		
		public  RegisterAndLoginSteps() {
			driver = Hooks.openBrowser();
			registerPO = PageFactoryManage.getRegisterPageObject(driver);
			loginPO = PageFactoryManage.getLoginPageObject(driver);
			homepagePO = PageFactoryManage.getHomePageObject(driver);
			
		}
		
		@Given("^I click to here link$")
		public void iClickToLink()  {
			registerPO = loginPO.clickHereLink();
		}
		
		@Given("^I get login URL$")
		public void iGetLoginURL(){
			loginURL = loginPO.getCurrentURL(driver);
		}

		@Then("^I get userID values$")
		public void iGetUserIDValues(){
		   userID = registerPO.getUserIdGeneratedByRegisterPage();
		}

		@When("^I get  password values$")
		public void iGetPasswordValues(){
		   password = registerPO.getPasswordGeneratedByRegisterPage();
		}
		
		@Given("^I input to email textbox type input with random email$")
		public void iInputToTextboxWith(){
			registerPO.inputRandomEmailToTextbox();
		}
		
		@Given("^I input UserID textbox$")
		public void iInputUserTextbox(){
			loginPO.inputUserIDToTextbox(userID);
		}
		
		@Given("^I input Password textbox$")
		public void iInputPasswrdTextbox(){
		 loginPO.inputPasswordToTextbox(password);
		}
		
		@When("^I open login page again$")
		public void iOpenLoginPageAgain(){
		   registerPO.navigateToLoginPage(loginURL);
		}
		
		@Then("^Verify (.*) is displayed with message \"([^\"]*)\"$")
		public void verifyIsDisplayedWithMessage( String none,String msgVerify)  {
			verifyTrue(homepagePO.verifyWelcomeLoginSuccessfullyAppear());
		}
		
		}
