package common;

import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.Reporter;

import common.VerificationFailures;
import io.github.bonigarcia.wdm.WebDriverManager;

public class AbstractTest extends AbstractPage{
	WebDriver driver;
	protected final Log log;
	private final String workingDir = System.getProperty("user.dir");
	
	protected AbstractTest() {
		log = LogFactory.getLog(getClass());
	}
	
	public WebDriver initDriver(String browserName) {
		if(browserName.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			ChromeOptions options = new ChromeOptions();
			//options.addArguments("--incognito");
			options.addArguments("--disable-extensions");
			options.addArguments("disable-infobars");
			options.addArguments("start-maximized");
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			driver = new ChromeDriver(capabilities);
		}
		else if(browserName.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
			System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, workingDir + "\\FirefoxNonHeadlessBrowserLog.txt");
			
			FirefoxProfile profile = new FirefoxProfile();
			DesiredCapabilities capability = DesiredCapabilities.firefox();
			
			//profile.setPreference("browser.private.browsing.autostart",true);
			profile.setAcceptUntrustedCertificates(false);
			profile.setAssumeUntrustedCertificateIssuer(true);
			profile.setPreference("browser.download.folderList", 2);
			profile.setPreference("browser.helperApps.alwaysAsk.force", false);
			profile.setPreference("browser.download.manager.showWhenStarting", false);
			profile.setPreference("browser.download.dir", "C:\\Downloads");
			profile.setPreference("browser.download.downloadDir", "C:\\Downloads");
			profile.setPreference("browser.download.defaultFolder", "C:\\Downloads");
			profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "text/anytext ,text/plain,text/html,application/plain");
			capability = DesiredCapabilities.firefox();
			capability.setCapability(FirefoxDriver.PROFILE, profile);
			driver = new FirefoxDriver(capability);
		}
		else if(browserName.equals("ie")) {
			WebDriverManager.iedriver().arch32().setup();
			DesiredCapabilities capability = DesiredCapabilities.internetExplorer();
			capability.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			capability.setCapability(CapabilityType.ELEMENT_SCROLL_BEHAVIOR, true);
			capability.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
			capability.setCapability("ignoreProtectedModeSettings", true);
			capability.setCapability("ignoreZoomSetting", true);
			capability.setCapability("requireWindowFocus", true);
			capability.setJavascriptEnabled(true);
			capability.setCapability("enableElementCacheCleanup", true);
			capability.setBrowserName("internet explorer");
			capability.setPlatform(org.openqa.selenium.Platform.ANY);
			driver = new InternetExplorerDriver(capability);
			
		}
		else if(browserName.equals("chromeheadless")) {
			WebDriverManager.chromedriver().setup();
			
			ChromeOptions options = new ChromeOptions();
			options.addArguments("headless");
			options.addArguments("window-size=1920x1080");
			driver = new ChromeDriver(options);
		}
		
		else if(browserName.equals("firefoxheadless")) {
			WebDriverManager.firefoxdriver().setup();
			System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
			System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, workingDir + "\\FirefoxHeadlessBrowserLog.txt");
			FirefoxOptions options = new FirefoxOptions();
			options.setHeadless(true);
			driver = new FirefoxDriver(options);
		}
		
		else if(browserName.equals("edge")) {
			WebDriverManager.edgedriver().arch32().setup();
			driver = new EdgeDriver();
		}
		
		openURL(driver, Constant.DEV_URL);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		return driver;
	}
	
	
	protected boolean verifyTrue(boolean condition) {
		return checkPassed(condition);
	}

	protected boolean verifyFalse(boolean condition) {
		return checkFailed(condition);
	}

	protected boolean verifyEquals(Object actual, Object expected) {
		return checkEquals(actual, expected);
	}

	private boolean checkPassed(boolean condition) {
		boolean pass = true;

		try {
			if (condition == true)
				Assert.assertTrue(condition);
		} catch (Throwable e) {
			pass = false;
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	private boolean checkFailed(boolean condition) {
		boolean pass = true;

		try {
			if (condition == true)
				Assert.assertFalse(condition);
		} catch (Throwable e) {
			pass = false;
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	private boolean checkEquals(Object actual, Object expected) {
		boolean pass = true;

		try {
			Assert.assertEquals(actual, expected);
		} catch (Throwable e) {
			pass = false;
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}
	
	protected void closeBrowser(WebDriver driver) {
    	try {
			// IE-11
			driver.manage().deleteAllCookies();
			
			String osName = System.getProperty("os.name").toLowerCase();
			String cmd = "";
			// Quit browser
			driver.quit();
			
			// Quit process
			if (driver.toString().toLowerCase().contains("chrome")) {
				if (osName.contains("mac")) {
					cmd = "pkill chromedriver";
				} else {
					cmd = "taskkill /F /FI \"IMAGENAME eq chromedriver*\"";
				}
				
				// Execute process
				Process process = Runtime.getRuntime().exec(cmd);
				process.waitFor();
			}
			if (driver.toString().toLowerCase().contains("internetexplorer")) {
				cmd = "taskkill /F /FI \"IMAGENAME eq IEDriverServer*\"";
				Process process = Runtime.getRuntime().exec(cmd);
				process.waitFor();
			}
			
			if (driver.toString().toLowerCase().contains("firefox")) {
				cmd = "taskkill /F /FI \"IMAGENAME eq geckodriver*\"";
				Process process = Runtime.getRuntime().exec(cmd);
				process.waitFor();
			}
			System.out.println("----------- Quit Process -----------");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
}
