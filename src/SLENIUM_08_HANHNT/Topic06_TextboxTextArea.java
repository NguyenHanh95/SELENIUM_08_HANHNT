package SLENIUM_08_HANHNT;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic06_TextboxTextArea {
	By nameLocator = By.xpath("//input[@name='name']");
	By DateLocator = By.xpath("//input[@name='dob']");
	By addressLocator = By.xpath("//textarea[@name='addr']");
	By cityLocator = By.xpath("//input[@name='city']");
	By stateLocator = By.xpath("//input[@name='state']");
	By pinnoLocator = By.xpath("//input[@name='pinno']");
	By telephonenoLocator = By.xpath("//input[@name='telephoneno']");
	By emailidLocator = By.xpath("//input[@name='emailid']");

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.get("http://demo.guru99.com/v4");
		driver.manage().timeouts().implicitlyWait(330, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		customerName = "hanh";
		Date = "2000-10-01";
		Address = "dong da";
		city = "ha noi";
		State = "Viet Nam";
		Pin = "123456";
		Phone = "0123456789";
		Email = "autotest" + ramdomString() + "gmail.com";
		Password = "12345678";

		// gán giá trị cho các biến trong form edit

		editAddress = "Thai Thinh";
		editCity = "Nam Dinh";
		editEmail = "automationtest" + ramdomString() + "gmail.com";
		editPhone = "01299492949";
		editPin = "323242";
		editState = "Americano";

	}

	@Test
	public void TC_01() {
		// lOGIN
		driver.findElement(By.xpath("//input[@name='uid']")).sendKeys("mngr180866");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("UhUnunE");
		driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
		// verify Homepage: Có 2 cách
		// cách 1:
		String verifyHompage = driver.findElement(By.xpath("//marquee[@class='heading3']")).getText();
		Assert.assertEquals(verifyHompage, "Welcome To Manager's Page of Guru99 Bank");
		// cách 2:
		Assert.assertTrue(driver.findElement(By.xpath("//marquee[@class='heading3' and text()=\"Welcome To Manager's Page of Guru99 Bank\"]")).isDisplayed());
		// Click vào nút New Customer
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();

		// Nhập dữ liệu vào các trường

		driver.findElement(nameLocator).sendKeys(customerName);
		driver.findElement(DateLocator).sendKeys(Date);
		driver.findElement(addressLocator).sendKeys(Address);
		driver.findElement(cityLocator).sendKeys(city);
		driver.findElement(stateLocator).sendKeys(State);
		driver.findElement(pinnoLocator).sendKeys(Pin);
		driver.findElement(telephonenoLocator).sendKeys(Phone);
		driver.findElement(emailidLocator).sendKeys(Email);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(Password);
		// click vào nút Submit
		driver.findElement(By.xpath("//input[@name='sub']")).click();
		// get ra text của CustomerID và verify các trường đã tao
		customerID = driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), customerName);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(), Date);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), Address);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), city);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), State);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), Pin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), Phone);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(), Email);

	}
	@Test

	public void TC_02_EditCustomer() {
		driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();
		driver.findElement(By.xpath("//input[@name='cusid']")).sendKeys("customerID");
		driver.findElement(By.xpath("//input[@name='AccSubmit']")).click();
		// verrify tai step7
		Assert.assertEquals(driver.findElement(nameLocator).getAttribute("value"), customerName);
		Assert.assertEquals(driver.findElement(addressLocator).getText(), Address);

		// Nhập dữ liệu vào các trường trong form chỉnh sửa thông tin
		driver.findElement(addressLocator).clear();
		driver.findElement(addressLocator).sendKeys(editAddress);
		driver.findElement(cityLocator).clear();
		driver.findElement(cityLocator).sendKeys(editCity);
		driver.findElement(stateLocator).clear();
		driver.findElement(stateLocator).sendKeys(editState);
		driver.findElement(pinnoLocator).clear();
		driver.findElement(pinnoLocator).sendKeys(editPin);
		driver.findElement(telephonenoLocator).clear();
		driver.findElement(telephonenoLocator).sendKeys(editPhone);
		driver.findElement(emailidLocator).clear();
		driver.findElement(emailidLocator).sendKeys(editEmail);
		driver.findElement(By.xpath("//input[@name='sub']")).click();

		// Verify các trường đã chỉnh sửa
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), editAddress);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), editCity);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), editState);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), editPin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), editPhone);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(), editEmail);

//
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	WebDriver driver;
	String customerName, Date, Address, city, State, Pin, Phone, Email, Password, customerID;
	String editAddress, editCity, editState, editPin, editPhone, editEmail;

	public int ramdomString() {
		Random randomString = new Random();
		return randomString.nextInt(99999999);
	}
}