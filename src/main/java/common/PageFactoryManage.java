package common;

import org.openqa.selenium.WebDriver;

import page.object.BalanceEnquiryPageObject;
import page.object.DeleteAccountPageObject;
import page.object.DeleteCustomerPageObject;
import page.object.DepositPageObject;
import page.object.EditCustomerPageObject;
import page.object.FundTransferPageObject;
import page.object.HomePageObject;
import page.object.LoginPageObject;
import page.object.NewAccountPageObject;
import page.object.NewCustomerPageObject;
import page.object.RegisterPageObject;
import page.object.WithdrawalPageObject;

public class PageFactoryManage {
	public static LoginPageObject getLoginPageObject(WebDriver driver) {
		return new LoginPageObject(driver);
	}
	public static HomePageObject getHomePageObject(WebDriver driver) {
		return new HomePageObject(driver);
	}
	public static RegisterPageObject getRegisterPageObject(WebDriver driver) {
		return new RegisterPageObject(driver);
	}
	public static NewCustomerPageObject getNewCustomerPageObject(WebDriver driver) {
		return new NewCustomerPageObject(driver);
	}
	public static EditCustomerPageObject getEditCustomerPageObject(WebDriver driver) {
		return new EditCustomerPageObject(driver);
	}
	public static DepositPageObject getDepositPageObject(WebDriver driver) {
		return new DepositPageObject(driver);
	}
	public static FundTransferPageObject getFundTransferPageObject(WebDriver driver) {
		return new FundTransferPageObject(driver);
	}
	
	public static NewAccountPageObject getNewAccountPageObject(WebDriver driver) {
		return new NewAccountPageObject(driver);
	}
	
	public static WithdrawalPageObject getWithdrawalPageObject(WebDriver driver) {
		return new WithdrawalPageObject(driver);
	}
	
	public static BalanceEnquiryPageObject getBalanceEnquiryPageObject(WebDriver driver) {
		return new BalanceEnquiryPageObject(driver);
	}
	
	public static DeleteAccountPageObject getDeleteAccountPageObject(WebDriver driver) {
		return new DeleteAccountPageObject(driver);
	}
	
	public static DeleteCustomerPageObject getDeleteCustomerPageObject(WebDriver driver) {
		return new DeleteCustomerPageObject(driver);
	}
	
}
