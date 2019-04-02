package SLENIUM_08_HANHNT;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import java.util.List;
import java.util.Random;
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

public class Topic11_Javascripexcuter {
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
		// open page
		driver.get("http://live.guru99.com/");

		// Get domain cua page va verify domain = live.guru99.com
		String domainName = (String) executeForBrowser("return document.domain");
		Assert.assertEquals(domainName, "live.guru99.com");

		// get URL cua page va verify URL ='http://live.guru99.com/'
		String pageURL = (String) executeForBrowser("return document.URL");
		Assert.assertEquals(pageURL, "http://live.guru99.com/");

		// Click to Mobile tab and click Add to card at ss galaxy
		clickToElementByJS("//a[text()='Mobile']");
		clickToElementByJS("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div/button");

		// verify text "Samsung Galaxy was added to your shopping cart" duoc hien thi
		String message = (String) executeForBrowser("return document.documentElement.innerText");
		Assert.assertTrue(message.contains("Samsung Galaxy was added to your shopping cart."));

		// Click privacy policy
		clickToElementByJS("//a[text()='Privacy Policy']");
		Thread.sleep(3000);
		// verify title cua page la privacy policy
		String privacyTitle = (String) executeForBrowser("return document.title");
		Assert.assertEquals(privacyTitle, "Privacy Policy");

		// scroll to bottom page
		scrollToBottomPage();
		Thread.sleep(3000);
		Assert.assertTrue(driver.findElement(By.xpath("//th[text()='WISHLIST_CNT']/following-sibling::td[text()='The number of items in your Wishlist.']")).isDisplayed());
		// navigate to http://demo.guru99.com/v4/
		navigateToUrlByJS("http://demo.guru99.com/v4/ ");
		Thread.sleep(3000);
	}

	@Test
	public void TC_02() throws Exception {
		driver.get("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_input_disabled");
		WebElement iframe = driver.findElement(By.xpath("//iframe[@id='iframeResult']"));
		driver.switchTo().frame(iframe);
		removeAttributeInDOM("//input[@name='lname']", "disabled");
		sendkeyToElementByJS("//input[@name='fname']", "Automation firstname");
		sendkeyToElementByJS("//input[@name='lname']", "Automation lastname");
		clickToElementByJS("//input[contains(@value,'Submit')]");
		Assert.assertEquals(driver.findElement(By.xpath("//body[@class='w3-container']/div[contains(@class,'w3-container')]")).getText().trim(), "fname=Automation firstname&lname=Automation lastname");

	}

	@Test
	public void TC_03() throws Exception {
		// Truy cập vào trang: http://live.guru99.com/
		driver.get("http://live.guru99.com/");
		// click My account o footer
		highlightElement("//div[@class='footer']//a[text()='My Account']");
		clickToElementByJS("//div[@class='footer']//a[text()='My Account']");
		// click create an account
		highlightElement("//span[text()='Create an Account']");
		clickToElementByJS("//span[text()='Create an Account']");
		highlightElement("//input[@id='firstname']");
		sendkeyToElementByJS("//input[@id='firstname']", "huyen");
		highlightElement("//input[@id='lastname']");
		sendkeyToElementByJS("//input[@id='lastname']", "tran");
		highlightElement("//input[@id='email_address']");
		sendkeyToElementByJS("//input[@id='email_address']", "huyen" + random() + "@gmail.com");
		highlightElement("//input[@id='password']");
		sendkeyToElementByJS("//input[@id='password']", "123456");
		highlightElement("//input[@id='confirmation']");
		sendkeyToElementByJS("//input[@id='confirmation']", "123456");
		Thread.sleep(3000);
		highlightElement("//button[@class='button']");
		clickToElementByJS("//button[@class='button']");
		// verify message xuat hien khi dang ky thanh cong
		String message = (String) executeForBrowser("return document.documentElement.innerText");
		Assert.assertTrue(message.contains("Thank you for registering with Main Website Store."));

		// Logout khoi he thong
		highlightElement("//div[@class='page-header-container']//span[text()='Account']");
		clickToElementByJS("//div[@class='page-header-container']//span[text()='Account']");
		highlightElement("//a[text()='Log Out']");
		clickToElementByJS("//a[text()='Log Out']");

		// verify navigate to Homepage
		highlightElement("//img[contains(@src,'logo.png')]");
		WebElement homePageLogo = driver.findElement(By.xpath("//img[contains(@src,'logo.png')]"));
		Assert.assertTrue(homePageLogo.isDisplayed());
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public int random() {
		Random radom = new Random();
		return radom.nextInt(999999);

	}

	public void highlightElement(String xpathName) {
		WebElement element = driver.findElement(By.xpath(xpathName));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].style.border='6px groove red'", element);
	}

	public Object executeForBrowser(String javaSript) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript(javaSript);
	}

	public Object clickToElementByJS(String xpathName) {
		WebElement element = driver.findElement(By.xpath(xpathName));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript("arguments[0].click();", element);
	}

	public Object sendkeyToElementByJS(String xpathName, String value) {
		WebElement element = driver.findElement(By.xpath(xpathName));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript("arguments[0].setAttribute('value', '" + value + "')", element);
	}

	public Object removeAttributeInDOM(String xpathName, String attribute) {
		WebElement element = driver.findElement(By.xpath(xpathName));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript("arguments[0].removeAttribute('" + attribute + "');", element);
	}

	public Object scrollToBottomPage() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public Object navigateToUrlByJS(String url) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript("window.location = '" + url + "'");
	}

}
