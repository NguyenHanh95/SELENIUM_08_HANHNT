package SLENIUM_08_HANHNT;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Xpath {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.get("http://live.guru99.com/index.php/customer/account/login/");
		driver.manage().timeouts().implicitlyWait(330, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_Xpath_Example() throws InterruptedException {

//  id
		driver.findElement(By.id("email")).sendKeys("automationemail@gmail.com");
		Thread.sleep(4000);
		driver.findElement(By.id("email")).clear();
		// Xpath thay thế cho id
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("automationtid@gmail.com");
		Thread.sleep(4000);
		driver.findElement(By.xpath("//input[@id='email']")).clear();
//  Class
		driver.findElement(By.className("validate-email")).sendKeys("automationclass@gmail.com");
		Thread.sleep(4000);
		driver.findElement(By.className("validate-email")).clear();
//  Xpath thay thế cho Class
		driver.findElement(By.xpath("//input[@class='input-text required-entry validate-email']"))
				.sendKeys("automation@gmail.com");
		Thread.sleep(4000);
		driver.findElement(By.xpath("//input[@class='input-text required-entry validate-email']")).clear();
// Name
		driver.findElement(By.name("login[username]")).sendKeys("automationname@gmail.com");
		Thread.sleep(4000);
		driver.findElement(By.name("login[username]")).clear();
//  Xpath thay thế cho name
		driver.findElement(By.xpath("//input[@name='login[username]']")).sendKeys("automationxpath@gmail.com");
		Thread.sleep(4000);
		driver.findElement(By.xpath("//input[@name='email']")).clear();

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}