package tests;
// We will Write Test Scripts here

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import utils.ExcelUtils;
import utils.ExtentReportManager;
import utils.Log;

public class LoginTest extends BaseTest {

	@DataProvider(name = "LoginData")
	public Object[][] getLoginData() throws IOException {

		String filepath = System.getProperty("user.dir") + "/testdata/TestData.xlsx";
		ExcelUtils.loadExcel(filepath, "LoginTestSheet");
		int rowCount = ExcelUtils.getRowCount();
		Object[][] data = new Object[rowCount - 1][2]; // First Row is header so to not use that we are using
														// rowCount-1.

		for (int i = 1; i < rowCount; i++) { // i =1 because 0'th index row is Header so we are not using first row

			data[i - 1][0] = ExcelUtils.getCellData(i, 0); // Username
			data[i - 1][1] = ExcelUtils.getCellData(i, 1); // Password
		}
		ExcelUtils.closeExcel();
		return data;
	}

	@DataProvider(name = "LoginData2")
	public Object[][] getData() {

		return new Object[][] { 
			{ "user1", "Pass1" }, 
			{ "user2", "Pass2" }, 
			{ "admin@yourstore.com", "admin" },
			{ "user3", "Pass3" }, 
			};
	}

	@Test(dataProvider = "LoginData")
//	@Test
//	@Parameters({"username","password"})
	public void testValidLogin(String username, String password) {

		Log.info("Started Valid Login Test ...............");
		test = ExtentReportManager.createTest("Valid Credential Login Test :- " + username);

		test.info("Navigating to URL-----");
		LoginPage loginPage = new LoginPage(driver);

		test.info("Adding Credentials----------");
		Log.info("Adding Credentials ...............");
//		loginPage.enterUsername("admin@yourstore.com");
//		loginPage.enterPassword("admin");

		loginPage.enterUsername(username);
		loginPage.enterPassword(password);
		test.info("Clicking on Login ...............");
		loginPage.clickLogin();

		System.out.println("Title of the Page is :-  " + driver.getTitle());
		Log.info("Verifying Page Title ...............");
		test.info("Verifying Page Title ...");
//		nopCommerce demo store. Login
//		Just a moment...
//      Dashboard / nopCommerce administration		
		Assert.assertEquals(driver.getTitle(), "Just a moment...");
		test.pass("Login Successful");

	}

//	@Test 
//	public void testInvalidLogin() {
//
//		Log.info("Started Invalid Login Test ...............");
//		test = ExtentReportManager.createTest("Invalid Credential Login Test");
//
//		test.info("Navigating to URL-----");
//		LoginPage loginPage = new LoginPage(driver);
//
//		test.info("Adding Invalid Credentials----------");
//		Log.info("Adding Invalid Credentials ...............");
//		loginPage.enterUsername("admin@yourstore.com");
//		loginPage.enterPassword("Chandu123");
//		test.info("Clicking on Login ...............");
//		loginPage.clickLogin();
//
//		System.out.println("Title of the Page is :-  " + driver.getTitle());
//		Log.info("Verifying Page Title .............");
//		test.info("Verifying Page Title -----------");
//		Assert.assertEquals(driver.getTitle(), "Just a moment...123");
//		test.pass("Login Successful");
//
//	}

}
