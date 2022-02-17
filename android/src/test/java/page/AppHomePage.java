package page;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import java.time.Duration;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;
import keyword.Keyword.Direction;

import org.openqa.selenium.Dimension;

public class AppHomePage extends AppBasePage{

	public AppHomePage(AndroidDriver<WebElement> driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	private WebElement txtFilter = keyword.findElement(By.id("filter_text"));
	private WebElement checkBox; 
	private WebElement blockApps = keyword.findElement(By.className("android.widget.ListView"));
	List<WebElement> allAppLsts; 
	private WebElement btnFilter = keyword.findElement(By.id("filterButton"));
	private WebElement checkBoxSystem;
	
	public Boolean checkBoxShoulbBeCheckedAfterOneClick() {
		keyword.setText(txtFilter, "Appium");
		
		checkBox = keyword.findElement(By.id("check"));
		keyword.click(checkBox);

//		return Boolean.parseBoolean(checkBox.getAttribute("checked"));
		
		return Boolean.parseBoolean(keyword.GetAttribute(checkBox, "checked"));
	}
	
	public Boolean checkBoxShouldBeUnCheckedAfterTwoClick() {
		keyword.setText(txtFilter, "Appium");
		
		checkBox = keyword.findElement(By.id("check"));
		keyword.click(checkBox);
		keyword.click(checkBox);
		
		return Boolean.parseBoolean(keyword.GetAttribute(checkBox, "checked"));
	}
	
	public int totalAppListCount() throws InterruptedException {
		return keyword.countApps(blockApps,Direction.UP,"license checker",By.xpath("//android.widget.TextView[@resource-id='com.intelloware.apkinfo:id/app_name']"));
		
	}
	
	public int downloadAppListCount() throws InterruptedException {
		keyword.click(btnFilter);
		
		checkBoxSystem = keyword.findElement(By.className("android.widget.CheckedTextView"));
		keyword.click(checkBoxSystem);
		
		driver.navigate().back();
		
		return keyword.countApps(blockApps,Direction.DOWN,"API Demos",By.xpath("//android.widget.TextView[@resource-id='com.intelloware.apkinfo:id/app_name']"));
		
	}


}
