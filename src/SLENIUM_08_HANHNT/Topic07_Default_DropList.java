package SLENIUM_08_HANHNT;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic07_Default_DropList {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.get("https://daominhdam.github.io/basic-form/index.html");
		driver.manage().timeouts().implicitlyWait(330, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01() throws Exception {
		WebElement jobRoleDropdown = driver.findElement(By.xpath("//select[@id='job1']"));
		Select jobRole = new Select(jobRoleDropdown);
		Assert.assertFalse(jobRole.isMultiple());
		/*
		 * Select Visible : Text Automation Tester 
		 * Select Value : value = automation 
		 * Select Index: thứ tự từng giá trị trong droplist bắt đầu từ 0
		 */
		jobRole.selectByVisibleText("Automation Tester");
		Thread.sleep(3000);

		// Kiểm tra xem giá trị được chọn đã đúng chưa?
		Assert.assertEquals(jobRole.getFirstSelectedOption().getText(), "Automation Tester");

		// Chọn giá trị Manual Tester trong dropdwon bằng phương thức Select Value
		jobRole.selectByValue("manual");
		Thread.sleep(3000);
		Assert.assertEquals(jobRole.getFirstSelectedOption().getText(), "Manual Tester");

		// Chọn giá trị Mobile Tester trong dropdown bằng phương thức Select Index
		jobRole.selectByIndex(3);
		Thread.sleep(3000);
		Assert.assertEquals(jobRole.getFirstSelectedOption().getText(), "Mobile Tester");

		// Kiểm tra dropdown có đủ 5 giá trị
		Assert.assertEquals(jobRole.getOptions().size(), 5);
		System.out.println(jobRole.getOptions().size());

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
