package page;

import org.openqa.selenium.WebElement;

import io.appium.java_client.android.AndroidDriver;
import keyword.Keyword;

public class AppBasePage {
	public static AndroidDriver<WebElement> driver;
	public Keyword keyword;
	
	public AppBasePage(AndroidDriver<WebElement> driver) {
		this.driver = driver;
		this.keyword = new Keyword(this.driver);
	}
	
}
