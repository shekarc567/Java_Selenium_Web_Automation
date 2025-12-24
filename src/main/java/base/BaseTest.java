package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

import utils.ExtentReportManager;
import utils.Log;

public class BaseTest {

	protected WebDriver driver;
	protected static ExtentReports extent;
	protected ExtentTest test;

	@BeforeSuite
	public void setupReport() {
		extent = ExtentReportManager.getReportsInstance();
	}

	@AfterSuite
	public void teardownReport() {
		extent.flush();
	}

	@BeforeMethod
	public void setup() {

		Log.info("Starting WebDriver ..............");
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		Log.info("Navigating to URL ..............");
//		https://opensource-demo.orangehrmlive.com/web/index.php/auth/login
//		https://admin-demo.nopcommerce.com/login?returnUrl=%2Fadmin%2F	
			
		driver.get("https://admin-demo.nopcommerce.com/login?returnUrl=%2Fadmin%2F");
		try {
		    Thread.sleep(3000); // 3 seconds
		} catch (InterruptedException e) {
		    Thread.currentThread().interrupt();
		}
	}

	@AfterMethod
	public void teardown(ITestResult result) {
		
		if (result.getStatus() == ITestResult.FAILURE) {
			String screenshotpath = ExtentReportManager.captureScreenshot(driver, "Login Failure");
			test.fail("Test Failed Check Screenshot........",
					MediaEntityBuilder.createScreenCaptureFromPath(screenshotpath).build());
		}
		
		if (driver != null) {
			Log.info("Closing the Browser ........");
			driver.quit();
		}
	}

}
