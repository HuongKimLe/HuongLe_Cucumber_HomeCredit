package common;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import interfaceBankguru.AbstractPageUI;
import page.object.BalanceEnquiryPageObject;
import page.object.DeleteAccountPageObject;
import page.object.DeleteCustomerPageObject;
import page.object.DepositPageObject;
import page.object.EditCustomerPageObject;
import page.object.FundTransferPageObject;
import page.object.LoginPageObject;
import page.object.NewAccountPageObject;
import page.object.NewCustomerPageObject;
import page.object.WithdrawalPageObject;

public class AbstractPage {
	
	/**
	 * WEB BROWSERS
	 */
	public void staticSleep(long timeout) {
		try {
			Thread.sleep(timeout*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void openURL(WebDriver driver, String addressURL) {
		driver.get(addressURL);
		
		if(driver.toString().toLowerCase().contains("internetexplorer")) {
			staticSleep(5);
		}
		
	}
	
	public String getCurrentURL(WebDriver driver) {
		return driver.getCurrentUrl();
	}
	
	public String getTitlePage(WebDriver driver) {
		return driver.getTitle();
	}
	
	public String getPageSource(WebDriver driver) {
		return driver.getPageSource();
	}
	
	public void backToPreviousPage(WebDriver driver) {
		driver.navigate().back();
	}
	
	public void forwardToNextPage(WebDriver driver) {
		driver.navigate().forward();
	}
	
	public void refreshPage(WebDriver driver) {
		driver.navigate().refresh();
	}
	
	public void navigateToPage(WebDriver driver, String url) {
		driver.navigate().to(url);
	}
	
	public void quitBrowser(WebDriver driver) {
		driver.quit();
	}
	
	public void closeTab(WebDriver driver) {
		driver.close();
	}
	
	public String getCurrentWindow(WebDriver driver) {
		return driver.getWindowHandle();
	}
	
	
	public void acceptAlert(WebDriver driver) {
		waitAlertPresence(driver);
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}
	
	public boolean verifyAlertText(WebDriver driver, String valueCompare) {
		waitAlertPresence(driver);
		Alert alert = driver.switchTo().alert();
		if(alert.getText()== valueCompare) {
			alert.accept();
			return true;
		}
		else {
			alert.accept();
			return false;
		}
		
	}
	
	/**
	 * WEB ELEMENTS
	 */
	//Interaction User

	
	public void clickLeftElement(WebDriver driver, String locator) {
		waitElementVisible(driver, locator);
		if(driver.toString().toLowerCase().contains("internetexplorer")) {
			clickToElementByJS(driver, locator);
			staticSleep(5);
		}else driver.findElement(By.xpath(locator)).click();
	}
	
	public void clickLeftElement(WebDriver driver, String locator, String... value) {
		locator = String.format(locator, (Object[])value);
	
		if(driver.toString().toLowerCase().contains("internetexplorer")) {
			clickToElementByJS(driver, locator);
			staticSleep(5);
		}else driver.findElement(By.xpath(locator)).click();
	}
	
	public void clickRightElement(WebDriver driver, String locator) {
		Actions action = new Actions(driver);
		action.contextClick(driver.findElement(By.xpath(locator))).perform();
	}
	
	public void clickDoubleElement(WebDriver driver, String locator) {
		Actions action = new Actions(driver);
		action.doubleClick(driver.findElement(By.xpath(locator))).perform();
	}
	
	public void hoverElement(WebDriver driver, String locator) {
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath(locator))).perform();
	}
	
	public void sendKeyBrowser(WebDriver driver, Keys keyInput) {
		Actions action = new Actions(driver);
		action.sendKeys(keyInput);
	}
	
	public void sendKeyElement(WebDriver driver, String locator, String stringKey) {
		driver.findElement(By.xpath(locator)).clear();
		Actions action = new Actions(driver);
		action.sendKeys(driver.findElement(By.xpath(locator)), stringKey).perform();
	}
	
	public void sendKeyElement(WebDriver driver, String locator, String stringKey,String... value) {
		locator = String.format(locator, (Object[])value);
		driver.findElement(By.xpath(locator)).clear();
		Actions action = new Actions(driver);
		action.sendKeys(driver.findElement(By.xpath(locator)), stringKey).perform();
	}
	
	public void sendKeyPickerWithoutClear(WebDriver driver, String locator, String stringKey) {
		driver.findElement(By.xpath(locator)).sendKeys(stringKey);
	}
	
	public void sendKeyPickerWithoutClear(WebDriver driver, String locator, String stringKey,String... value) {
		locator = String.format(locator, (Object[])value);
		driver.findElement(By.xpath(locator)).sendKeys(stringKey);
	}
	
	public void dragAndDropElement(WebDriver driver, String locatorTarget, String locatorSource) {
		Actions action = new Actions(driver);
		action.dragAndDrop(driver.findElement(By.xpath(locatorSource)), driver.findElement(By.xpath(locatorTarget))).perform();
	}
	
	public void checkCheckbox(WebDriver driver, String locator) {
		if(!(isSelectedElement(driver, locator)))
			clickLeftElement(driver, locator);
	}
	
	public void uncheckCheckbox(WebDriver driver, String locator) {
		if(isSelectedElement(driver, locator))
			clickLeftElement(driver, locator);
	}
	
	public void waitElementVisible(WebDriver driver, String locator) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
//		if(driver.toString().toLowerCase().contains("internetexplorer")) {
//			staticSleep(5);
//		}
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
	}
	
	public void waitElementVisible(WebDriver driver, String locator, String... value) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		locator = String.format(locator,(Object[]) value);
//		if(driver.toString().toLowerCase().contains("internetexplorer")) {
//			staticSleep(5);
//		}
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
	}
	
	public void waitElementInvisible(WebDriver driver, String locator) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		overrideGlobalTimeout(driver, shortTimeout);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(locator)));
		overrideGlobalTimeout(driver, longTimeout);
	}
	
	public void waitAlertPresence(WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.alertIsPresent());
		if(driver.toString().toLowerCase().contains("internetexplorer")) {
			staticSleep(5);
		}
	}
	
	public boolean isControlUndisplayed(WebDriver driver, String locator, String... value) {
		locator = String.format(locator, (Object[])value);
		Date date = new Date();
		System.out.println("Started time = " + date.toString());
		// 5s
		overrideGlobalTimeout(driver, shortTimeout);
		List<WebElement> elements = driver.findElements(By.xpath(locator));
		if (elements.size() == 0) {
			date = new Date();
			System.out.println("End time = " + date.toString());
			// 30s
			overrideGlobalTimeout(driver, longTimeout);
			return true;
		} else {
			date = new Date();
			System.out.println("End time = " + date.toString());
			// 30s
			overrideGlobalTimeout(driver, longTimeout);
			return false;
		}
}

	public void overrideGlobalTimeout(WebDriver driver, long timeOut) {
    	driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
}
	
	//Attribute element
	public int getSizeOfListElement(WebDriver driver, String locator) {
		return driver.findElements(By.xpath(locator)).size();
	}
	
	public String getTextElement(WebDriver driver, String locator) {
		return driver.findElement(By.xpath(locator)).getText();
	}
	
	public String getTextElement(WebDriver driver, String locator, String... value) {
		locator = String.format(locator, (Object[])value);
		return driver.findElement(By.xpath(locator)).getText();
	}
	
	public String getAttributeElement(WebDriver driver, String locator, String nameAttribute) {
		return driver.findElement(By.xpath(locator)).getAttribute(nameAttribute);
	}
	
	public boolean isSelectedElement(WebDriver driver, String locator) {
		return driver.findElement(By.xpath(locator)).isSelected();
	}
	
	public boolean isDisplayElement(WebDriver driver, String locator) {
		return driver.findElement(By.xpath(locator)).isDisplayed();
	}
	
	public boolean isDisplayElement(WebDriver driver, String locator, String... value) {
		locator = String.format(locator, (Object[])value);
		return driver.findElement(By.xpath(locator)).isDisplayed();
	}
	
	public boolean isEnableElement(WebDriver driver, String locator) {
		return driver.findElement(By.xpath(locator)).isEnabled();
	}
	
	//Dropdown list type Select
	public void selectItemDropdownTypeSelect(WebDriver driver, String locator, String nameItem) {
		Select select = new Select(driver.findElement(By.xpath(locator)));
		select.selectByVisibleText(nameItem);
	}
	
	public void selectItemDropdownTypeSelect(WebDriver driver, String locator, String nameItem, String... expectedName ) {
		locator = String.format(locator, (Object[])expectedName);
		Select select = new Select(driver.findElement(By.xpath(locator)));
		select.selectByVisibleText(nameItem);
	}
	
	public String getFirstOptionDropdownTypeSelect(WebDriver driver, String locator) {
		Select select = new Select(driver.findElement(By.xpath(locator)));
		return select.getFirstSelectedOption().getText();
	}
	
	public String getFirstOptionDropdownTypeSelect(WebDriver driver, String locator, String... expectedName) {
		locator = String.format(locator, (Object[])expectedName);
		Select select = new Select(driver.findElement(By.xpath(locator)));
		return select.getFirstSelectedOption().getText();
	}
	
	public int getSizeSelectDropdownTypeSelect(WebDriver driver, String locator) {
		Select select = new Select(driver.findElement(By.xpath(locator)));
		return select.getOptions().size();
	}
	
	public void onlySelectItemFromCustomDropbox(WebDriver driver,String parentLocator, String allItemsLocator, String expectedValue) {
		  WebElement parentDropbox = driver.findElement(By.xpath(parentLocator));
		  ((JavascriptExecutor)driver).executeScript("arguments[0].click();", parentDropbox);
		  List<WebElement> allItemsElement = driver.findElements(By.xpath(allItemsLocator));
		  int numberItems = allItemsElement.size(); 
		  for(int i = 0; i<numberItems; i++) {
			  if(allItemsElement.get(i).getText().equals(expectedValue)) {
				  allItemsElement.get(i).click();
				  break;
			  }
		  }
	  }
	
	/**
	 * WEB ELEMENTS JAVASCRIPT
	 */
	
	public Object clickToElementByJS(WebDriver driver,String locator) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			return js.executeScript("arguments[0].click();", driver.findElement(By.xpath(locator)));
		} catch (Exception e) {
			e.getMessage();
			return null;
		}
	}
	
	public Object clickToElementByJS(WebDriver driver,String locator, String...  value) {
		locator = String.format(locator, (Object[])value);
		try {
	          JavascriptExecutor js = (JavascriptExecutor) driver;
	          return js.executeScript("arguments[0].click();", driver.findElement(By.xpath(locator)));
	      } catch (Exception e) {
	          e.getMessage();
	          return null;
	      }
	  }
	
	public void highlightElement(WebDriver driver,WebElement element) {
	      JavascriptExecutor js = (JavascriptExecutor) driver;
	      js.executeScript("arguments[0].style.border='6px groove red'", element);
	  }

	  public Object executeForBrowser(WebDriver driver,String javaSript) {
	      try {
	          JavascriptExecutor js = (JavascriptExecutor) driver;
	          return js.executeScript(javaSript);
	      } catch (Exception e) {
	          e.getMessage();
	          return null;
	      }
	  }
	
	  public Object sendkeyToElementByJS(WebDriver driver,String locator, String value) {
		  WebElement element = driver.findElement(By.xpath(locator));
		  try {
			  JavascriptExecutor js = (JavascriptExecutor) driver;
			  return js.executeScript("arguments[0].setAttribute('value', '" + value + "')", element);
		  } catch (Exception e) {
			  e.getMessage();
			  return null;
		  }
	  }
	  
	  public Object sendkeyToElementByJS(WebDriver driver,String locator, String value,String... nameLocator) {
		  locator = String.format(locator, (Object[])nameLocator);
		  WebElement element = driver.findElement(By.xpath(locator));
		  try {
			  JavascriptExecutor js = (JavascriptExecutor) driver;
			  return js.executeScript("arguments[0].setAttribute('value', '" + value + "')", element);
		  } catch (Exception e) {
			  e.getMessage();
			  return null;
		  }
	  }
	  
	  public Object setValueToOfAttributeToElementByJS(WebDriver driver,String locator,String nameAttribute, String value) {
		  WebElement element = driver.findElement(By.xpath(locator));
		  try {
			  JavascriptExecutor js = (JavascriptExecutor) driver;
			  return js.executeScript("arguments[0].setAttribute('" + nameAttribute + "', '" + value + "')", element);
		  } catch (Exception e) {
			  e.getMessage();
			  return null;
		  }
	  }
	  
	  public Object setValueToOfAttributeToElementByJS(WebDriver driver,String locator,String nameAttribute, String value, String... nameLocator) {
		  locator = String.format(locator, (Object[])nameLocator);
		  WebElement element = driver.findElement(By.xpath(locator));
		  try {
	          JavascriptExecutor js = (JavascriptExecutor) driver;
	          return js.executeScript("arguments[0].setAttribute('" + nameAttribute + "', '" + value + "')", element);
	      } catch (Exception e) {
	          e.getMessage();
	          return null;
	      }
	  }
	
	  public Object removeAttributeInDOM(WebDriver driver,WebElement element, String attribute) {
	      try {
	          JavascriptExecutor js = (JavascriptExecutor) driver;
	          return js.executeScript("arguments[0].removeAttribute('" + attribute + "');", element);
	      } catch (Exception e) {
	          e.getMessage();
	          return null;
	      }
	  }
	
	  public Object scrollToBottomPage(WebDriver driver) {
	      try {
	          JavascriptExecutor js = (JavascriptExecutor) driver;
	          return js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	      } catch (Exception e) {
	          e.getMessage();
	          return null;
	      }
	  }
	
	  public Object navigateToUrlByJS(WebDriver driver,String url) {
	      try {
	          JavascriptExecutor js = (JavascriptExecutor) driver;
	          return js.executeScript("window.location = '" + url + "'");
	      } catch (Exception e) {
	          e.getMessage();
	          return null;
	      }
	  }
	  
	  //Handle window, frame, iframe
	  public void switchToWindowByID(WebDriver driver, String windowToSwitch) {
		  Set<String> allwindows  = driver.getWindowHandles();
		  if(allwindows.size()>1) {
			  for(String currentWindow:allwindows) {
				  if(!currentWindow.equals(windowToSwitch)) {
					  driver.switchTo().window(windowToSwitch);
					  closeTab(driver);
				  }
			  }
			  driver.switchTo().window(windowToSwitch);
		  }
		}
	  
	  public void switchToWindowByTitle(WebDriver driver, String titleWindowToSwitch) {
		  Set<String> allwindows  = driver.getWindowHandles();
		  String windowIDToSwitch="";
		  if(allwindows.size()>1) {
			  for(String currentWindow:allwindows) {
				  driver.switchTo().window(currentWindow);
				  if(driver.getTitle().equals(titleWindowToSwitch)) {
					  windowIDToSwitch = driver.getWindowHandle();
				  }
			  }
			  switchToWindowByID(driver, windowIDToSwitch);
		  }
		}
	  
	  public void switchToFrame(WebDriver driver, String frameToSwitchLocator) {
		  driver.switchTo().frame(driver.findElement(By.xpath(frameToSwitchLocator)));
	  }
	
	/**
	 * GENERAL
	 */
	public int randomNumber(int Number) {
		Random rd = new Random();
		return rd.nextInt(Number);
	}
	
	public int randomNumberInRange(int High, int Low ) {
		  Random rd = new Random();
		  return rd.nextInt(High-Low) + Low;
	}
	
	public static String getTodayString(String formatTime) {
		return new SimpleDateFormat(formatTime).format(Calendar.getInstance().getTime());
	}
	
	/**
	 * DYNAMIC PAGE OBJECT
	 */
	public NewCustomerPageObject openNewCustomerPage(WebDriver driver) {
		waitElementVisible(driver, AbstractPageUI.DYNAMIC_MENU_LINK,"New Customer");
		clickLeftElement(driver, AbstractPageUI.DYNAMIC_MENU_LINK,"New Customer");
		return PageFactoryManage.getNewCustomerPageObject(driver);
	}
	
	public EditCustomerPageObject openEditNewCustomerPage(WebDriver driver) {
		waitElementVisible(driver, AbstractPageUI.DYNAMIC_MENU_LINK,"Edit Customer");
		clickLeftElement(driver, AbstractPageUI.DYNAMIC_MENU_LINK,"Edit Customer");
		return PageFactoryManage.getEditCustomerPageObject(driver);
	}
	
	public DepositPageObject openDepositPage(WebDriver driver) {
		waitElementVisible(driver, AbstractPageUI.DYNAMIC_MENU_LINK,"Deposit");
		clickLeftElement(driver, AbstractPageUI.DYNAMIC_MENU_LINK,"Deposit");
		return PageFactoryManage.getDepositPageObject(driver);
	}
	
	public WithdrawalPageObject openWithdrawalPage(WebDriver driver) {
		waitElementVisible(driver, AbstractPageUI.DYNAMIC_MENU_LINK,"Withdrawal");
		clickLeftElement(driver, AbstractPageUI.DYNAMIC_MENU_LINK,"Withdrawal");
		return PageFactoryManage.getWithdrawalPageObject(driver);
	}
	
	public FundTransferPageObject openFundTransferPage(WebDriver driver) {
		waitElementVisible(driver, AbstractPageUI.DYNAMIC_MENU_LINK,"Fund Transfer");
		clickLeftElement(driver, AbstractPageUI.DYNAMIC_MENU_LINK,"Fund Transfer");
		return PageFactoryManage.getFundTransferPageObject(driver);
	}
	
	public NewAccountPageObject openNewAccountPage(WebDriver driver) {
		waitElementVisible(driver, AbstractPageUI.DYNAMIC_MENU_LINK,"New Account");
		clickLeftElement(driver, AbstractPageUI.DYNAMIC_MENU_LINK,"New Account");
		return PageFactoryManage.getNewAccountPageObject(driver);
	}
	
	public BalanceEnquiryPageObject openBalanceEnquiryPage(WebDriver driver) {
		waitElementVisible(driver, AbstractPageUI.DYNAMIC_MENU_LINK,"Balance Enquiry");
		clickLeftElement(driver, AbstractPageUI.DYNAMIC_MENU_LINK,"Balance Enquiry");
		return PageFactoryManage.getBalanceEnquiryPageObject(driver);
	}
	
	public DeleteCustomerPageObject openDeleteCustomerPage(WebDriver driver) {
		waitElementVisible(driver, AbstractPageUI.DYNAMIC_MENU_LINK,"Delete Customer");
		clickLeftElement(driver, AbstractPageUI.DYNAMIC_MENU_LINK,"Delete Customer");
		return PageFactoryManage.getDeleteCustomerPageObject(driver);
	}
	
	public DeleteAccountPageObject openDeleteAccountPage(WebDriver driver) {
		waitElementVisible(driver, AbstractPageUI.DYNAMIC_MENU_LINK,"Delete Account");
		clickLeftElement(driver, AbstractPageUI.DYNAMIC_MENU_LINK,"Delete Account");
		return PageFactoryManage.getDeleteAccountPageObject(driver);
	}
	
	public LoginPageObject clickToLogoutFieldPanel(WebDriver driver) {
		waitElementVisible(driver, AbstractPageUI.DYNAMIC_MENU_LINK,"Log out");
		clickLeftElement(driver, AbstractPageUI.DYNAMIC_MENU_LINK,"Log out");
		waitAlertPresence(driver);
		acceptAlert(driver);
		return PageFactoryManage.getLoginPageObject(driver);
	}
	
	public void inputIntoDynamicTextboxWithTypeInput(WebDriver driver,String valueInput,String nameLocator) {
		waitElementVisible(driver, AbstractPageUI.DYNAMIC_TEXTBOX,nameLocator,"input");
		if(driver.toString().toLowerCase().contains("internetexplorer")) {
			sendkeyToElementByJS(driver, AbstractPageUI.DYNAMIC_TEXTBOX, valueInput, nameLocator,"input");
		}
		else sendKeyElement(driver,AbstractPageUI.DYNAMIC_TEXTBOX, valueInput,nameLocator,"input");
	}
	
	public void setAttributeValueOfDynamicTextboxWithTypeInput(WebDriver driver,String nameAttribute, String nameLocator,String valueInput) {
		waitElementVisible(driver, AbstractPageUI.DYNAMIC_TEXTBOX,nameLocator,"input");
		setValueToOfAttributeToElementByJS(driver, AbstractPageUI.DYNAMIC_TEXTBOX, nameAttribute, valueInput, nameLocator,"input");
	}
	
	public void inputValueDynamicTextboxNonTextTypeInput(WebDriver driver,String valueInput, String nameLocator) {
		waitElementVisible(driver, AbstractPageUI.DYNAMIC_TEXTBOX,nameLocator,"input");
		setValueToOfAttributeToElementByJS(driver, AbstractPageUI.DYNAMIC_TEXTBOX, "type", "text", nameLocator,"input");
		if(driver.toString().toLowerCase().contains("internetexplorer")) {
			sendkeyToElementByJS(driver, AbstractPageUI.DYNAMIC_TEXTBOX, valueInput, nameLocator,"input");
		}
		else sendKeyElement(driver,AbstractPageUI.DYNAMIC_TEXTBOX, valueInput,nameLocator,"input");
	}
	
	public void inputIntoDynamicTextboxWithTypeInputContainsText(WebDriver driver,String valueInput,String nameLocator) {
		waitElementVisible(driver, AbstractPageUI.DYNAMIC_TEXTBOX_COINTAINS,nameLocator,"input");
		if(driver.toString().toLowerCase().contains("internetexplorer")) {
			sendkeyToElementByJS(driver, AbstractPageUI.DYNAMIC_TEXTBOX, valueInput, nameLocator,"input");
		}
		else sendKeyElement(driver,AbstractPageUI.DYNAMIC_TEXTBOX_COINTAINS, valueInput,nameLocator,"input");
	}
	
	public void inputIntoDynamicTextboxWithTypeInputWithoutClear(WebDriver driver,String valueInput,String typeInput) {
		waitElementVisible(driver, AbstractPageUI.DYNAMIC_TEXTBOX,typeInput,"input");
		if(driver.toString().toLowerCase().contains("internetexplorer")) {
			sendkeyToElementByJS(driver, AbstractPageUI.DYNAMIC_TEXTBOX, valueInput, typeInput,"input");
		}
		else sendKeyPickerWithoutClear(driver, AbstractPageUI.DYNAMIC_TEXTBOX, valueInput, typeInput,"input");
	}
	
	public void inputIntoDynamicTextboxWithTypeArea(WebDriver driver,String valueInput,String typeInput) {
		waitElementVisible(driver, AbstractPageUI.DYNAMIC_TEXTBOX,typeInput,"textarea");
		if(driver.toString().toLowerCase().contains("internetexplorer")) {
			sendkeyToElementByJS(driver, AbstractPageUI.DYNAMIC_TEXTBOX, valueInput, typeInput,"textarea");
		}
		else sendKeyElement(driver,AbstractPageUI.DYNAMIC_TEXTBOX, valueInput,typeInput,"textarea");
	}
	
	public void clickDynamicButtonWithNameInput(WebDriver driver,String nameInput) {
		waitElementVisible(driver, AbstractPageUI.DYNAMIC_BUTTON, nameInput);
		clickLeftElement(driver, AbstractPageUI.DYNAMIC_BUTTON, nameInput);
	}
	
	public String getTextValueForDynamicTable(WebDriver driver,String nameLocator) {
		waitElementVisible(driver, AbstractPageUI.DYNAMIC_TABLE, nameLocator);
		return getTextElement(driver, AbstractPageUI.DYNAMIC_TABLE, nameLocator);
	}
	
	public void checkDisplayDynamicTable(WebDriver driver,String nameLocator) {
		waitElementVisible(driver, AbstractPageUI.DYNAMIC_TABLE, nameLocator);
		isDisplayElement(driver, AbstractPageUI.DYNAMIC_TABLE, nameLocator);
	}
	
	public void checkDynamicCheckbox(WebDriver driver,String nameLocator,String valueCheckbox) {
		waitElementVisible(driver, AbstractPageUI.DYNAMIC_CHECKBOX_INPUTFORM, nameLocator,valueCheckbox);
		clickLeftElement(driver, AbstractPageUI.DYNAMIC_CHECKBOX_INPUTFORM, nameLocator,valueCheckbox);
	}
	
	public boolean checkDisplayDynamicLabel(WebDriver driver,String nameLocator) {
		waitElementVisible(driver, AbstractPageUI.DYNAMIC_LABEL, nameLocator);
		return isDisplayElement(driver, AbstractPageUI.DYNAMIC_LABEL, nameLocator);
	}
	
	public void selectDynamicDropdownListTypeSelect(WebDriver driver,String optionText,String nameLocator) {
		selectItemDropdownTypeSelect(driver, AbstractPageUI.DYNAMIC_SELECT_DROPDOWNLIST, optionText, nameLocator);
		//Assert.assertEquals(optionText, getFirstOptionDropdownTypeSelect(driver, AbstractPageUI.DYNAMIC_SELECT_DROPDOWNLIST, nameLocator));
	}
	
	private int shortTimeout = 5;
	private int longTimeout = 30;
	
	
}

	
	


	
