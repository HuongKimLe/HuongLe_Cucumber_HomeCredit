package page.object;

import org.openqa.selenium.WebDriver;

import common.AbstractPage;

public class DeleteAccountPageObject extends AbstractPage {
	WebDriver driver;

	public DeleteAccountPageObject(WebDriver driver) {
		super();
		this.driver = driver;
	}
}
