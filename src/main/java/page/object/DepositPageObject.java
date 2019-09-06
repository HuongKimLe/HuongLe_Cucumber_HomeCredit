package page.object;

import org.openqa.selenium.WebDriver;

import common.AbstractPage;

public class DepositPageObject extends AbstractPage{
	WebDriver driver;
	public DepositPageObject(WebDriver mappingDriver) {
		driver = mappingDriver;
	}

	public boolean checkAccountGeneratedSuccessfully(String accountNo) {
		return checkDisplayDynamicLabel(driver, "Transaction details of Deposit for Account " + accountNo);	
	}
	
}
