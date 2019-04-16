package SLENIUM_08_HANHNT;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.google.common.base.Function;

public class Topic13_Wait_01 {
	WebDriver driver;
	WebDriverWait waitExplicit;
	Date date;

	@Test
	public void TC_01_ImplicitWait() {

		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.findElement(By.xpath("//button[text()='Start']")).click();

		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='finish']/h4[text()='Hello World!']")).isDisplayed());
	}

	@Test
	public void TC_02_ExplicitWait_visibilityAndPresence() {

		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

		waitExplicit.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//button[text()='Start']")));
		driver.findElement(By.xpath("//button[text()='Start']")).click();

		waitExplicit.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='loading']")));

		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='loading']")));
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='finish']/h4[text()='Hello World!']")).isDisplayed());
	}

	@Test
	public void TC_03_ExplicitWait_visibility_Presence() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

		// Check visiable. Trước khi thao tác với element thì nên sử dụng wait explicit như 1 cái đi�?u kiện tiên quyết
		waitExplicit.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//button[text()='Start']")));
		driver.findElement(By.xpath("//button[text()='Start']")).click();

		// Check presence ( có trong DOM - Pass)
		waitExplicit.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='loading']")));

		// Check visble(Check nhìn thấy.)
		waitExplicit.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@id='finish']/h4[text()='Hello World!']")));
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='finish']/h4[text()='Hello World!']")).isDisplayed());
	}

	@Test
	public void TC_04_ExplicitWait_DontAppearInDOM() {

		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

		System.out.println("------------Start time của check presence---------");
		System.out.println(date = new Date());

		// Check presence (Ko có trong DOM -> failed)

		waitExplicit.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='finish']/h4[text()='Hello World!']")));

		System.out.println("------------End time check presence---------");
		System.out.println(date = new Date());
	}

	@Test
	public void TC_05_ExplicitWait_InvisibleInDOMAndNOT() {

		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		waitExplicit = new WebDriverWait(driver, 10);

		// ===========CHECK ELEMENT INVISIBLE- KO COS TRONG DOM============
		// Check invisible (hello word) => ko có trong DOM=> pass -> Ch�? hết timeout 10s
		System.out.println("------------Start time check hello word invisible (NOT IN DOM)---------");
		System.out.println(date = new Date());
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='finish']/h4[text()='Hello World!']")));
		System.out.println("------------End time check hello word invisible (NOT IN DOM)---------");
		System.out.println(date = new Date());

		// Check invisible (loading) => kO có trong DOM=> pass
		System.out.println("------------Start time loading invisible (NOT IN DOM) ---------");
		System.out.println(date = new Date());
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='loading']")));
		System.out.println("------------End time loading invisible (NOT IN DOM)---------");
		System.out.println(date = new Date());
		// =================================================================

		driver.findElement(By.xpath("//button[text()='Start']")).click();

		waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='finish']/h4[text()='Hello World!']")));
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='finish']/h4[text()='Hello World!']")).isDisplayed());

		// ===============CHECK ELEMENT INVISIBLE- CO TRONG DOM========================

		// Check invisible (loading) => có trong DOM=> pass -> ko ch�? hết timeout -> giây đầu tiên nó đã ko thấy hiển thi trên UI
		System.out.println("------------Start time loading invisible (IN DOM)---------");
		System.out.println(date = new Date());
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='loading']")));
		System.out.println("------------End time loading invisible (IN DOM)---------");
		System.out.println(date = new Date());
		// ============================================================================

	}

	@Test
	public void TC_06_ExplicitWait_Invisible() {
		// Check cho loading icon invisible trước khi Helloworld text được hiển thị
		// Implicit wait chỉ set 2s

		// Step 01 - Truy cập v� o trang: http://the-internet.herokuapp.com/dynamic_loading/2
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

		// Step 02 - Click the Start button
		driver.findElement(By.xpath("//button[text()='Start']")).click();

		// Step 03 - Wait Loading invisible
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='loading']")));

		// Step 04 - Check result text is "Hello World!"
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='finish']/h4[text()='Hello World!']")).isDisplayed());

	}

	@Test
	public void TC_07_ExplicitWait_Visible() {
		// Check cho Hello world text visible -> sau đó check Helloworld text được hiển thị
		// Implicit wait chỉ set 2s

		// Step 01 - Truy cập v� o trang: http://the-internet.herokuapp.com/dynamic_loading/2
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

		// Step 02 - Click the Start button
		driver.findElement(By.xpath("//button[text()='Start']")).click();

		// Step 03 - Wait Hello World visible
		waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='finish']/h4[text()='Hello World!']")));

		// Step 04 - Check result text is "Hello World!"
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='finish']/h4[text()='Hello World!']")).isDisplayed());
	}

	@Test
	public void TC_08_ExplicitWait_Invisible() {
		// Check cho Helloworld invisible + có trong DOM -> hết bao nhiêu s?
		// Check cho Helloworld invisible + ko có trong DOM -> hết bao nhiêu s?
		// Check cho Loading invisible + có trong DOM -> hết bao nhiêu s?
		// Check cho Loading invisible + ko có trong DOM -> hết bao nhiêu s?

		// Step 01 - Truy cập v� o trang: http://the-internet.herokuapp.com/dynamic_loading/2
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");

		// Step 02 - Check cho Helloworld invisible + ko có trong DOM -> hết bao nhiêu s?

		System.out.println("------------Start time Hello World invisible---------");
		System.out.println(date = new Date());

		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='finish']/h4[text()='Hello World!']")));

		System.out.println("------------End time Hello World invisible---------");
		System.out.println(date = new Date());

		// Step 03 -Check cho Loading invisible + có trong DOM -> hết bao nhiêu s?
		System.out.println("\n------------Start time loading invisible---------");
		System.out.println(date = new Date());
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='loading']")));
		System.out.println("------------And time loading invisible---------");
		System.out.println(date = new Date());

		// Step 04 - Click the Start button
		driver.findElement(By.xpath("//button[text()='Start']")).click();

		// Step 05 - Check cho Loading invisible + ko có trong DOM -> hết bao nhiêu s?
		System.out.println("\n------------Start time loading invisible---------");
		System.out.println(date = new Date());
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='loading']")));
		System.out.println("------------End time loading invisible---------");
		System.out.println(date = new Date());

	}

	@Test
	public void TC_09_ExplicitWait() {

//		Step 01 - Truy cập v� o trang:http://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx
		driver.get("http://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		waitExplicit = new WebDriverWait(driver, 25);

//			Step 02 - Wait cho "Date Time Picker" được hiển thị (sử dụng: presence or visibility)
		waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='demo-container size-narrow']")));
		waitExplicit.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='demo-container size-narrow']")));

//			Step 03 - In ra ng� y đã ch�?n (Before AJAX call) -> hiện tại chưa ch�?n nên in ra = "No Selected Dates to display."
		WebElement SelectedDateBefor = driver.findElement(By.xpath("//span[@class='label']"));
		Assert.assertEquals(SelectedDateBefor.getText(), "No Selected Dates to display.");

		driver.findElement(By.xpath("//a[text()='1']")).click();

		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='raDiv']")));

		waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@class,'rcSelected')]//a[text()='1']")));
		Assert.assertTrue(driver.findElement(By.xpath("//*[contains(@class,'rcSelected')]//a[text()='1']")).isDisplayed());

		WebElement SelectedDateAfter = driver.findElement(By.xpath("//span[@class='label']"));
		Assert.assertEquals(SelectedDateAfter.getText(), "Monday, April 01, 2019");
	}

	@Test
	public void TC_09_ExplicitWait_Cach2() {

		driver.get("http://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		waitExplicit = new WebDriverWait(driver, 25);

		waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='demo-container size-narrow']")));
		waitExplicit.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='demo-container size-narrow']")));

		WebElement SelectedDateBefor = driver.findElement(By.xpath("//span[@class='label']"));
		Assert.assertEquals(SelectedDateBefor.getText(), "No Selected Dates to display.");

		driver.findElement(By.xpath("//a[text()='1']")).click();

		Assert.assertTrue(driver.findElement(By.xpath("//*[contains(@class,'rcSelected')]//a[text()='1']")).isDisplayed());

		WebElement SelectedDateDisplayed = driver.findElement(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1' and contains(text(),'Monday, April 01, 2019')]"));
		Assert.assertTrue(SelectedDateDisplayed.isDisplayed());
	}

	@Test
	public void TC_10_FluentWait() {

		driver.get("https://daominhdam.github.io/fluent-wait/");

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		waitExplicit = new WebDriverWait(driver, 25);

		WebElement countdount = driver.findElement(By.xpath("//div[@id='javascript_countdown_time']"));
		waitExplicit.until(ExpectedConditions.visibilityOf(countdount));

		new FluentWait<WebElement>(countdount)

				.withTimeout(15, TimeUnit.SECONDS)

				.pollingEvery(1, TimeUnit.SECONDS)

				.ignoring(NoSuchElementException.class)

				.until(new Function<WebElement, Boolean>() {
					public Boolean apply(WebElement element) {

						boolean flag = element.getText().endsWith("00");
						System.out.println("Time = " + element.getText());

						return flag;
					}
				});

	}

	@BeforeMethod
	public void beforMethod() {
		driver = new FirefoxDriver();
		waitExplicit = new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@AfterMethod
	public void afterMethod() {
		// driver.quit();
	}
}