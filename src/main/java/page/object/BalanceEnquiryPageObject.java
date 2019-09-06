package page.object;

import org.openqa.selenium.WebDriver;

import common.AbstractPage;

public class BalanceEnquiryPageObject extends AbstractPage {
	WebDriver driver;

	public BalanceEnquiryPageObject(WebDriver driver) {
		super();
		this.driver = driver;
	}

	public boolean checkBalanceEnquirySuccessfully(String accountNo) {
		return checkDisplayDynamicLabel(driver, "Balance Details for Account " + accountNo);
	}
}
