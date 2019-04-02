package SLENIUM_08_HANHNT;

import org.testng.annotations.Test;

//import junit.framework.Assert;

import org.testng.annotations.BeforeClass;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic10_HandleWindown {
	WebDriver driver;
	JavascriptExecutor javascript;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\lib\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01() throws Exception {
		driver.get("https://daominhdam.github.io/basic-form/index.html");
		String parentWindowId = driver.getWindowHandle();
		System.out.println(parentWindowId);
		driver.findElement(By.xpath("//a[text()='Click Here']")).click();
		switchToChildWindow(parentWindowId);
		Assert.assertEquals(driver.getTitle(), "Google");
		closeAllWithoutParentWindows(parentWindowId);
		Assert.assertEquals(driver.getTitle(), "SELENIUM WEBDRIVER FORM DEMO");

	}

	@Test
	public void TC_02() throws Exception {
		driver.get("http://www.hdfcbank.com/");
		String parentWindowId = driver.getWindowHandle();

		List<WebElement> notificationIframe = driver.findElements(By.xpath("//div[@id='vizury-notification-container']//iframe"));
		System.out.println("Notification size " + notificationIframe.size());
		if (notificationIframe.size() > 0) {
			Assert.assertTrue(notificationIframe.get(0).isDisplayed());
			driver.switchTo().frame(notificationIframe.get(0));
			Thread.sleep(3000);
			javascript.executeScript("arguments[0].click()", driver.findElement(By.xpath("//div[@id='div-close']")));
			driver.switchTo().defaultContent();
			Thread.sleep(3000);
		}
		driver.findElement(By.xpath("//div[@class='sectionnav']//a[text()='Agri']")).click();
		switchToWindowByTitle("HDFC Bank Kisan Dhan Vikas e-Kendra");

		// click Account detail link trong cua so con
		driver.findElement(By.xpath("//ul[@class='grid_list clearfix']//p[text()='Account Details']")).click();
		switchToWindowByTitle("Welcome to HDFC Bank NetBanking");

		// Click privacy
		WebElement footerFrame = driver.findElement(By.xpath("//frame[@name='footer']"));
		driver.switchTo().frame(footerFrame);
		driver.findElement(By.xpath("//a[text()='Privacy Policy']")).click();
		switchToWindowByTitle("HDFC Bank - Leading Bank in India, Banking Services, Private Banking, Personal Loan, Car Loan");
		// driver.switchTo().defaultContent();
		// click csr
		driver.findElement(By.xpath("//a[text()='CSR']")).click();
		closeAllWithoutParentWindows(parentWindowId);

	}

	@Test
	public void TC_03() throws Exception {
		driver.get("http://live.guru99.com/index.php/");
		String parentWindowId = driver.getWindowHandle();
		driver.findElement(By.xpath("//a[text()='Mobile']")).click();
		driver.findElement(By.xpath("//a[text()='Sony Xperia']/parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']\n" + "\n" + "")).click();
		driver.findElement(By.xpath("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();
		driver.findElement(By.xpath("//button[@title='Compare']")).click();
		switchToWindowByTitle("Products Comparison List - Magento Commerce");
		closeAllWithoutParentWindows(parentWindowId);
	}

	// Handle khi chi co 2 cua so window
	public void switchToChildWindow(String parent) throws Exception {
		// get ra tat ca cac tab dang co
		Set<String> allWindows = driver.getWindowHandles();
		Thread.sleep(3000);
		for (String runWindow : allWindows) {
			if (!runWindow.equals(parent)) {
				driver.switchTo().window(runWindow);
				break;
			}
		}
	}

	public void switchToWindowByTitle(String title) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindows : allWindows) {
			driver.switchTo().window(runWindows);
			String currentWin = driver.getTitle();
			if (currentWin.equals(title)) {
				break;
			}
		}
	}

	public boolean closeAllWithoutParentWindows(String parentWindow) throws Exception {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindows : allWindows) {
			if (!runWindows.equals(parentWindow)) {
				driver.switchTo().window(runWindows);
				driver.close();
			}
		}
		Thread.sleep(3000);
		driver.switchTo().window(parentWindow);
		if (driver.getWindowHandles().size() == 1)
			return true;
		else
			return false;
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
