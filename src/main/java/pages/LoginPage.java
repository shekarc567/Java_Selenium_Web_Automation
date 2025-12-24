package pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.Log;

public class LoginPage {

	private WebDriver driver;
	private WebDriverWait wait;

	// Page Factory
	@FindBy(xpath = "//input[@name='Email']")
	WebElement usernameTextBox;

	@FindBy(xpath = "//input[@name='Password']")
	WebElement passwordTextBox;

	@FindBy(xpath = "//button[@type='submit']")
	WebElement loginButton;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	}

	public void enterUsername(String username) {
		usernameTextBox.clear();
		usernameTextBox.sendKeys(username);
	}

	public void enterPassword(String password) {
		passwordTextBox.clear();
		passwordTextBox.sendKeys(password);
	}

	public void clickLogin() {
		Log.info("Waiting for Login Button to be clickable...");

		wait.until(ExpectedConditions.elementToBeClickable(loginButton));
		Log.info("Clicking Login Button ...............");
		loginButton.click();
	}
}
