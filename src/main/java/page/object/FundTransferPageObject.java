package page.object;

import org.openqa.selenium.WebDriver;

import common.AbstractPage;

public class FundTransferPageObject extends AbstractPage{
	WebDriver driver;
	public FundTransferPageObject(WebDriver mappingDriver) {
		driver = mappingDriver;
	}
	public boolean checkFundTransferSuccessfully() {
		return checkDisplayDynamicLabel(driver, "Fund Transfer Details");
	}
}
