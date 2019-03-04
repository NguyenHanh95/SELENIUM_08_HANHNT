package SLENIUM_08_HANHNT;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.management.ValueExp;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic07_Custom_DropList {
	WebDriver driver;
	WebDriverWait waitExplicit;
	JavascriptExecutor javascript;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		waitExplicit = new WebDriverWait(driver, 30);
		driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
		driver.manage().timeouts().implicitlyWait(330, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		javascript =(JavascriptExecutor)driver;

	}

	@Test
	public void TC_01() {
		selectCustomDropdownList("//span[@id='number-button']","//ul[@id='number-menu']/li[@class='ui-menu-item']/div", "19");
Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']//span[@class='ui-selectmenu-text'and text()='19']")).isDisplayed());
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public void selectCustomDropdownList(String parentXpath, String ChildXpath, String valueExpected) {
		// click để mở dropdown ra
		WebElement parent = driver.findElement(By.xpath(parentXpath));
		parent.click();
		// wait cho tất cả các item được mở
		List<WebElement> child = (List<WebElement>) driver.findElement(By.xpath(ChildXpath));
		waitExplicit.until(ExpectedConditions.visibilityOfAllElements(child));
		// GetText tất cả các item ra và kiểm tra giá trị có bằng giá trị mong muốn hay k
		for (WebElement childItem : child) {
			if (childItem.getText().equals(valueExpected)) {
				// Scroll đến item cần chọn
				javascript.executeScript("arguments[0].scrollIntoView(true);", childItem);
				// click vào item này
				childItem.click();
			}

		}

	}
}
