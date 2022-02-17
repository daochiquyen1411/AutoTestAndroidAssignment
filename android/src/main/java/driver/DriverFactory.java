package driver;

import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import config.Configs;
import io.appium.java_client.android.AndroidDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
	 public static  AndroidDriver<WebElement> getDriver(String browser) throws MalformedURLException{
	      switch (browser){
	          case "Android":
	              return new AndroidDriver<WebElement>(Configs.serverUrl(), Configs.androidDesiredCapabilities());
	          default:
	              return new AndroidDriver<WebElement>(Configs.serverUrl(), Configs.androidDesiredCapabilities());
	      }
	 }
}
