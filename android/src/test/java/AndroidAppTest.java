

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.Console;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import io.appium.java_client.TouchAction;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import io.appium.java_client.android.AndroidDriver;
import config.Configs;

public class AndroidAppTest {
	public static AndroidDriver<WebElement> driver = null;
	protected static ExtentReports extent;
	private ExtentTest test;

	@BeforeTest
	public void Setup() {
		extent = new ExtentReports();
		SimpleDateFormat sdf = new SimpleDateFormat("HHmmss_MMddyyyy");
		Date now = new Date();
		String extentReportName =  sdf.format(now);
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(extentReportName + ".html");
//		htmlReporter.setAppendExisting(true);
		extent.attachReporter(htmlReporter);

//		driver = new AndroidDriver<WebElement>(Configs.itcGroupServerUrl(), Configs.desiredCapabilitiesAndroidApp());
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	}

	@AfterTest
	public void Teardown() {
		try {
			if (driver != null)
				driver.quit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@BeforeMethod
	public void beforeMethod(Method method) {
		String methodName = method.getName();
		test = extent.createTest(methodName);
	}

	@AfterMethod
	public void afterMethod(){
		try {
			extent.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * [ifixit]-1: Test search devices on Acura Support Community
	 * <p>
	 * Steps:
	 * 1. Click on "Car and Truck" Categories on Homepage
	 * 2. Click on "Acura" Categories
	 *
	 * Expected:
	 * 1. General Information is "Acura".
	 * 2. Verify three devices below displays.
	 * + Acura Integra
	 * + Acura MDX
	 * + Acura RL
	 * <p>
	 *
	 * [ifixit]-2: Test search answers on Acura Support Community
	 * <p>
	 * 1. Click on "Acura" General Information
	 * 2. Swipe from left to right, it will move to Answers tab
	 * 3. Enter keyword 'Acura MDX'
	 * 4. Click Search icon
	 *
	 * Expected: It should show at least 6 results.
	 *
	 */
	@Test(priority = 1, description = "should allow to search some questions on Acura Support Community")
	public void testCreditCardJourney() {

		// Login to snap
		test.info("******START*****");
		test.info("Login to phptravel ");
		driver.findElementById("com.phptravelsnative:id/naviIcon").click();

		sleep(2);
		test.info("\tClicked Menu button");

		driver.findElementById("com.phptravelsnative:id/indicator").click();
		sleep(1);

		// login to system
		driver.findElementById("com.phptravelsnative:id/input_email").sendKeys("user@phptravels.com");
		driver.findElementById("com.phptravelsnative:id/input_password").sendKeys("demouser");
		driver.findElementById("com.phptravelsnative:id/btn_login").click();

		sleep(1);
		driver.findElementById("com.phptravelsnative:id/naviIcon").click();
		AssertJUnit.assertTrue(driver.findElementById("com.phptravelsnative:id/title").isDisplayed());

	}

	public void swipe(String direction) {
		//Get the size of screen.
		Dimension size = driver.manage().window().getSize();
		
		int startx = (int) (size.width * 0.9);
		//Find endx point which is at left side of screen.
		int endx = (int) (size.width * 0.2);
		//Find vertical point where you wants to swipe. It is in middle of screen height.
		int starty = size.height / 2;
		
		if (direction == "RightLeft") {
			//Swipe from Right to Left.
//			driver.swipe(startx, starty, endx, starty, 200);
			test.info("Swiped from right to left : " + startx + ": " + starty + "to " + endx + ": " +
				starty);
		}
		
		if (direction == "LeftRight") {
			//Swipe from Left to Right.
//			driver.swipe(endx, starty, startx, starty, 200);
			test.info("Swiped from left to right : " + endx + ": " + starty + "to " + startx + ": " +
				starty);
		}
		sleep(2);
	}

	public void sleep(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Goto Acura community to input search question then get the search answers
	 * <p>
	 * @param driver
	 * @param question
	 * @return String : is the search result
	 */
	public String searchQuestion(AndroidDriver<WebElement> driver, String question) {
		// Act: select first topic
		driver.findElementByXPath("//*[@resource-id='com.dozuki.ifixit:id/topic_title' and @index=1]").click();
		test.info("Clicked first topic");
		// Act: waiting for Info tab display
		(new WebDriverWait(driver, 60))
		.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@resource-id='com.dozuki.ifixit:id/topic_info_image']")));
		// Act: swipe right to left
		swipe("RightLeft");
		// Act: waiting for Answers tab display
		(new WebDriverWait(driver, 60)).until(
			ExpectedConditions.elementToBeClickable(By.xpath("//android.view.View[@resource-id='mainHeader']")));
		// Act: input questions
		driver.findElementByXPath("//*[@resource-id='answersSearch']").sendKeys(question);
		test.info("Entered : " + question );
		// Act: press enter
//		driver.pressKeyCode(66);
		sleep(3);

		return driver.findElementByXPath("//*[contains(@content-desc,'questions')]")
			.getAttribute("name");
	}

	public void swiptToBottom()
	{
		Dimension dim = driver.manage().window().getSize();
		int height = dim.getHeight();
		int width = dim.getWidth();
		int x = width/2;
		int top_y = (int)(height*0.8);
		int bottom_y = (int)(height*0.2) ;
		System.out.println("coordinates :" + x + "  "+ top_y + " "+ bottom_y);
		TouchAction ts = new TouchAction(driver);
//		ts.press(x, top_y).waitAction(1000).moveTo(x, bottom_y).release().perform();
	}

	public void scrollToElement(WebElement element) {
		Dimension screenSize = driver.manage().window().getSize();
		int screenWidth = screenSize.getWidth() / 2;
		int screenHight = screenSize.getHeight() - 20;
		// this swipes down the screen until the given element is visible
		if (!element.isDisplayed()) {
			do {
//				driver.swipe(screenWidth, screenHight, screenWidth, screenHight - 600, 1000);
			} while (!element.isDisplayed());
		}
	}
}
