package SLENIUM_08_HANHNT;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic10_IFrame_Frame {
	WebDriver driver;
	JavascriptExecutor javascript;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.get("http://live.guru99.com");
		javascript = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01() throws Exception {
		driver.get("https://www.hdfcbank.com/");
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
		WebElement lookingForIframe = driver.findElement(By.xpath("//div[@class='flipBannerWrap']//iframe"));
		driver.switchTo().frame(lookingForIframe);

		WebElement lookingForText = driver.findElement(By.xpath("//span[@id='messageText' and text()='What are you looking for?']"));
		Assert.assertTrue(lookingForText.isDisplayed());
		driver.switchTo().defaultContent();
	
		
	
		WebElement slidingBannerIframe = driver.findElement(By.xpath("//div[@class='slidingbanners']//iframe"));
		driver.switchTo().frame(slidingBannerIframe);
		List<WebElement> slidingImg = driver.findElements(By.xpath("//div[@id='productcontainer']//img"));
		Assert.assertEquals(slidingImg.size(), 6);
		System.out.println("sliding Image " + slidingImg.size());
		driver.switchTo().defaultContent();


		List<WebElement> fliperBanner = driver.findElements(By.xpath("//div[@class='flipBanner']//img[@class='front icon']"));
		Assert.assertEquals(fliperBanner.size(), 8);
		System.out.println("fliper Banner " + fliperBanner.size());

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}