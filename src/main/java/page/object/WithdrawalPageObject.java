package page.object;

import org.openqa.selenium.WebDriver;

import common.AbstractPage;

public class WithdrawalPageObject extends AbstractPage{
	WebDriver driver;

	public WithdrawalPageObject(WebDriver driver) {
		super();
		this.driver = driver;
	}
	
	public boolean checkAccountGeneratedSuccessfully(String accountNo) {
		return checkDisplayDynamicLabel(driver, "Transaction details of Withdrawal for Account " + accountNo);	
	}
	
}
