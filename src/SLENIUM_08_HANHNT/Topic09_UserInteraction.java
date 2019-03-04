package SLENIUM_08_HANHNT;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test
public class Topic09_UserInteraction {
	WebDriver driver;
	Actions action;

	String workingDirectory = System.getProperty("user.dir");
	String jsFilePath = workingDirectory + "\\helper\\drag_and_drop_helper.js";
	String jQueryFilePath = workingDirectory + "\\helper\\jquery_load_helper.js";

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		action = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test

	public void TC_01_MoveMouse() throws Exception {

		driver.get("http://www.myntra.com/");

		WebElement avatarIcon = driver.findElement(By.xpath("//div[@class='desktop-user']//div[@class='desktop-userIconsContainer']"));
		action.moveToElement(avatarIcon).perform();

		driver.findElement(By.xpath("//a[text()='log in']")).click();

		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='login-box']")).isDisplayed());

		WebElement menPage = driver.findElement(By.xpath("//a[contains(text(),'Men')]"));
		action.moveToElement(menPage).perform();

		driver.findElement(By.xpath("//div[@data-group='men']//a[text()='T-Shirts']")).click();

		Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='Men Tshirts']")).isDisplayed());

	}

	@Test
	public void TC_02_ClickAndHold() throws Exception {

		driver.get("http://jqueryui.com/resources/demos/selectable/display-grid.html");

		List<WebElement> numberIcon = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
		action.clickAndHold(numberIcon.get(0)).moveToElement(numberIcon.get(3)).release().perform();

		List<WebElement> numberSelected = driver.findElements(By.xpath("//ol[@id='selectable']/li[contains(@class,'ui-selected')]"));
		System.out.println("Phan tu duoc chon = " + numberSelected);
		Assert.assertEquals(4, numberSelected.size());

		driver.navigate().refresh();

		List<WebElement> elements = driver.findElements(By.xpath("//*[@id='selectable']/li"));
		Actions action = new Actions(driver);
		action.keyDown(Keys.CONTROL).build().perform();
		elements.get(0).click();
		elements.get(2).click();
		elements.get(4).click();
		elements.get(6).click();
		action.keyUp(Keys.CONTROL).build().perform();
	}

	@Test

	public void TC_03_DoubleClick() throws Exception {

		driver.get("http://www.seleniumlearn.com/double-click");

		WebElement doubleButton = driver.findElement(By.xpath("//button[text()='Double-Click Me!']"));
		action.doubleClick(doubleButton).perform();

		Alert doubleAlert = driver.switchTo().alert();
		Assert.assertEquals(doubleAlert.getText(), "The Button was double-clicked.");

		doubleAlert.accept();
	}

	@Test
	public void TC_04_RightOrContextClick() throws Exception {

		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");

		WebElement rightClickButton = driver.findElement(By.xpath("//span[text()='right click me']"));
		action.contextClick(rightClickButton).perform();

		WebElement quitVisible = driver.findElement(By.xpath("//li[contains(@class,'context-menu-icon-quit')]"));
		action.moveToElement(quitVisible).perform();

		Assert.assertTrue(driver.findElement(By.xpath("//li[contains(@class,'context-menu-icon-quit') and contains(@class,'context-menu-visible') and contains(@class,'context-menu-hover')]")).isDisplayed());

		quitVisible.click();

		Alert doubleAlert = driver.switchTo().alert();
		doubleAlert.accept();
	}

	@Test
	public void TC_05_DragAndDrop() throws Exception {

		driver.get("http://demos.telerik.com/kendo-ui/dragdrop/angular");

		WebElement sourceElement = driver.findElement(By.xpath("//div[@id = 'draggable']"));
		WebElement targetElement = driver.findElement(By.xpath("//div[@id = 'droptarget']"));
		Action.DragAndDrop(sourceElement, targetElement).build().perform();

		Assert.assertTrue(driver.findElement(By.xpath("//div[@id = 'droptarget' and text()='You did great!']")).isDisplayed());

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public String readFile(String file) throws IOException {
		Charset cs = Charset.forName("UTF-8");
		FileInputStream stream = new FileInputStream(file);
		try {
			Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
			StringBuilder builder = new StringBuilder();
			char[] buffer = new char[8192];
			int read;
			while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
				builder.append(buffer, 0, read);
			}
			return builder.toString();
		} finally {
			stream.close();
		}
	}
}