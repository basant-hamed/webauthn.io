package test_cases;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.pages.P01_HomePage;
import org.example.pages.PageBase;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


import java.awt.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class TestBase {

	// define main properties
	public static WebDriver driver;

	// extend report
	static ExtentTest test;
	static ExtentReports report;
	public static ChromeOptions options;
	JavascriptExecutor js;
	P01_HomePage homePage;

	@Parameters("browser")
	@BeforeTest
	public void setupDriver(String browser) throws IOException, AWTException {
		options = new ChromeOptions();
		options.addArguments("--start-maximized");
		options.addArguments("--disable-web-security");
		options.addArguments("--no-proxy-server");
		options.addArguments("--ignore-certificate-errors");
		// to run headless test
//		options.addArguments("--headless");

		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("credentials_enable_service", false);
		prefs.put("profile.password_manager_enabled", false);

		options.setExperimentalOption("prefs", prefs);
		options.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });

		if (browser.equalsIgnoreCase("Firefox")) {
			// use webdrivermanager
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("Chrome")) {
			// use webdrivermanager
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(options);
		} else {
			throw new IllegalArgumentException("Invalid browser value!!");
		}
		js = (JavascriptExecutor) driver;
		// Set Driver wait
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		// define extend report
		report = new ExtentReports(System.getProperty("user.dir")+"/WebAuthReport.html");
		test = report.startTest("WebAuth.io Automation Project");
	}

	@Test(priority = 1, groups = "smoke", description = "Start WebAuth Web Application")
	public void startApplication() throws InterruptedException {
		// define home page object
		homePage=new P01_HomePage(driver);
		// Mazimize current window
		driver.manage().window().maximize();
		// navigate to website
		driver.get("https://webauthn.io/");
		// take screenshot to login page
		PageBase.captureScreenshot(driver, "signUpPage");
		// assert if application start correctly
		Assert.assertEquals(homePage.getHomePageTitle(),"WebAuthn.io");
	}

	@AfterTest
	public void tearDown() {
		report.endTest(test);
		report.flush();
		driver.quit();
	}

}
