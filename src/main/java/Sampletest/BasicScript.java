package Sampletest;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class BasicScript {

	public static void main(String[] args) {

		WebDriver driver = new ChromeDriver();
//      WebDriver driver = new FirefoxDriver();
//      WebDriver driver = new EdgeDriver();

		driver.get("https://admin-demo.nopcommerce.com/login?returnUrl=%2Fadmin%2F");
		driver.manage().window().maximize();

		String title = driver.getTitle();
		System.out.println("Title of the Page is :-  " + title);

		// driver.findElement(By.id("Email")).sendKeys("admin@yourstore.com");

		WebElement emailBox = driver.findElement(By.id("Email"));
		emailBox.clear();
		emailBox.sendKeys("admin@yourstore.com");

		WebElement passwordBox = driver.findElement(By.name("Password"));
		passwordBox.clear();
		passwordBox.sendKeys("admin");

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		WebElement submitButton = driver.findElement(By.xpath("//button[@type='submit']"));
		submitButton.click();

		driver.quit();

	}

}
