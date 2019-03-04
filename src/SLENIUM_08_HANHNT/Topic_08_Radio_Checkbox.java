package SLENIUM_08_HANHNT;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Radio_Checkbox {
	WebDriver driver;
	JavascriptExecutor javascript;

	@BeforeClass
	public void beforeClass() {
		driver.get("http://live.guru99.com");
		driver = new FirefoxDriver();
		javascript = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_HandleButton() {

		WebElement myAccountLink = driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']"));
		javascript.executeScript("arguments[0].click();", myAccountLink);

		String myAccountURL = driver.getCurrentUrl();
		Assert.assertEquals(myAccountURL, "http://live.guru99.com/index.php/customer/account/login/");

		WebElement createAnAccountBtn = driver.findElement(By.xpath("//span[text()='Create an Account']"));
		javascript.executeScript("arguments[0].click();", createAnAccountBtn);

		String createAnAccountUrl = driver.getCurrentUrl();
		Assert.assertEquals(createAnAccountUrl, "http://live.guru99.com/index.php/customer/account/create/");

	}

	@Test
	public void TC_02_HandleCheckbox() throws Exception {

		driver.get("http://demos.telerik.com/kendo-ui/styling/checkboxes");

		WebElement dualZoneCheckbox = driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input"));
		javascript.executeScript("arguments[0].click();", dualZoneCheckbox);
		Thread.sleep(3000);

		Assert.assertTrue(dualZoneCheckbox.isSelected());

		if (dualZoneCheckbox.isSelected()) {
			javascript.executeScript("arguments[0].click();", dualZoneCheckbox);
		}
		Assert.assertFalse(dualZoneCheckbox.isSelected());

	}

	@Test
	public void TC_03_HandleRadiobutton() throws Exception {

		driver.get("http://demos.telerik.com/kendo-ui/styling/radios");

		// Click vào radiobutton: 2.0 Petrol, 147kW (Thẻ input ko được sử dụng thuộc tính id)
		WebElement petroRadiobutton = driver.findElement(By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input"));
		javascript.executeScript("arguments[0].click();", petroRadiobutton);
		Thread.sleep(3000);

		// Kiểm tra radio button đó đã chọn hay chưa/ nếu chưa chọn lại
		if (petroRadiobutton.isSelected()) {
			javascript.executeScript("arguments[0].click();", petroRadiobutton);
		}
	}

	@Test
	public void TC_04_HandleAlert() throws Exception {

		driver.get("https://daominhdam.github.io/basic-form/index.html");

		// Click vào button: Click for JS Alert
		WebElement alertButton = driver.findElement(By.xpath("//button[@onclick='jsAlert()']"));
		alertButton.click();
		Thread.sleep(3000);

		// Verify message hiển thị trong alert là: I am a JS Alert
		Alert alert = driver.switchTo().alert();
		String alertText = alert.getText();
		Assert.assertEquals(alertText, "I am a JS Alert");

		// Accept alert và verify message hiển thị tại Result là: You clicked an alert successfully
		alert.accept();
		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(), "You clicked an alert successfully");

	}

	@Test
	public void TC_05_HandleConfirm() throws Exception {

		driver.get("https://daominhdam.github.io/basic-form/index.html																																							");

		// Click vào button: Click for JS Confirm
		WebElement alertButton = driver.findElement(By.xpath("//button[@onclick='jsConfirm()']"));
		alertButton.click();
		Thread.sleep(3000);

		// Verify message hiển thị trong alert là: I am a JS Confirm
		Alert alert = driver.switchTo().alert();
		String alertText = alert.getText();
		Assert.assertEquals(alertText, "I am a JS Confirm");

		// Cancel alert và verify message hiển thị tại Result là: You clicked: Cancel
		alert.dismiss();
		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(), "You clicked: Cancel");

	}

	@Test
	public void TC_06_HandlePrompt() throws Exception {

		driver.get("https://daominhdam.github.io/basic-form/index.html");

		// Click vào button: Click for JS Prompt
		driver.findElement(By.xpath("//button[@onclick='jsPrompt()']")).click();
		Thread.sleep(5000);

		// Verify message hiển thị trong alert là: I am a JS prompt
		Alert alert = driver.switchTo().alert();
		String alertText = alert.getText();
		Assert.assertEquals(alertText, "I am a JS prompt");
		Thread.sleep(5000);

		// Nhập vào text bất kì (daominhdam) và verify message hiển thị tại Result là: You entered: Nhập vào text bất kì (daominhdam) và verify message hiển thị tại Result là: You entered: daominhdam
		alert.sendKeys("daominhdam");
		Thread.sleep(5000);
		alert.accept();
		Assert.assertTrue(driver.findElement(By.xpath("//p[@id='result' and text()='You entered: daominhdam']")).isDisplayed());

	}

	@Test
	public void TC_07_AuthenticationAlert() throws Exception {

		driver.get("http://admin:admin@the-internet.herokuapp.com/basic_auth");

		// Verify message hiển thị sau khi login thành công:
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credenti')]")).isDisplayed());

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}