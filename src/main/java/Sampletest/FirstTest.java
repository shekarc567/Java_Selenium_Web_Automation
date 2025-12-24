package Sampletest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class FirstTest {
	public static void main(String[] args) {

		WebDriver driver = new ChromeDriver();
//        WebDriver driver = new FirefoxDriver();
//        WebDriver driver = new EdgeDriver();

		driver.get("https://selenium.dev");

		driver.quit();
	}

}
