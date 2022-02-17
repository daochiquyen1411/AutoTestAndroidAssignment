package keyword;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class Keyword {
	private AndroidDriver<WebElement> driver;
	private WebDriverWait wait;
	private int timeout = 30;
	
	public Keyword(AndroidDriver<WebElement> driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, timeout);
	}
	
	 public WebElement findElement(By locator){
	    return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	 }

	 public WebElement findElementWaitUntilClickable(By locator){
	    return wait.until(ExpectedConditions.elementToBeClickable(locator));
	 }	
	 
	 public  void click(WebElement elem){
	    Actions actions = new Actions(this.driver);
	    actions.moveToElement(elem).build().perform();
	    elem.click();
	 }	
	 
	 public void setText(WebElement elem, String text){
	    try{
	       elem.clear();
	       elem.sendKeys(text);
	    }
	    catch (WebDriverException e){
	        throw new WebDriverException("Element is not enable to set text");
	    }
	 }
	 
	 public String GetAttribute(WebElement elem, String attribute)
     {
         return elem.getAttribute(attribute);
     }
	 
		public int countApps(WebElement blockList,Direction dir, String elementName, By locator) throws InterruptedException {
			int index =0;
			ArrayList<String> generalArray = new ArrayList<String>();
			boolean found_result = false;
			List<WebElement> appLists = blockList.findElements(locator);
			while (!found_result) {
						appLists = blockList.findElements(locator);
			            int size = 0;
			            size = size + appLists.size();
			            switch (dir) {
			            case DOWN: 
			            	 for (int i = size - 1; i >= 0; i--) {
					                String s = appLists.get(i).getText();
					                if (s.equals(elementName)) {
					                    found_result = true;
					                    index++;
					                    return index;
					                } else {
					                    if (!generalArray.contains(s)) {
					                        index++;
					                        System.out.println(s);
					                        generalArray.add(s);
					                      }
					                  }
					              }
					             if (!found_result) {
					            	 swipeScreen(dir);
					                 Thread.sleep(1000);
					            }			               
			                break;
			            case UP:
			            	 for (int i = 0; i < size; i++) {
					                String s = appLists.get(i).getText();
					                if (s.equals(elementName)) {
					                    found_result = true;
					                    index++;
					                    return index;
					                } else {
					                    if (!generalArray.contains(s)) {
					                        index++;
					                        System.out.println(s);
					                        generalArray.add(s);
					                      }
					                  }
					              }
					             if (!found_result) {
					            	 swipeScreen(dir);
					                 Thread.sleep(1000);
					            }
			                break;
			            }
			            
			           
			}
			
			return index;
		}

	    public void scrollEvent(String visibleText) {
	        driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+visibleText+"\").instance(0))");
	    }
	  
	    public void swipeScreen(Direction dir) {
	        System.out.println("swipeScreen(): dir: '" + dir + "'"); // always log your actions

	        // Animation default time:
	        //  - Android: 300 ms
	        //  - iOS: 200 ms
	        // final value depends on your app and could be greater
	        final int ANIMATION_TIME = 200; // ms

	        final int PRESS_TIME = 200; // ms

	        int edgeBorder = 10; // better avoid edges
	        PointOption pointOptionStart, pointOptionEnd;

	        // init screen variables
	        Dimension dims = driver.manage().window().getSize();

	        // init start point = center of screen
	        pointOptionStart = PointOption.point(dims.width / 2, dims.height / 4);

	        switch (dir) {
	            case DOWN: // center of footer
	                pointOptionEnd = PointOption.point(dims.width / 10, dims.height - edgeBorder);
	                break;
	            case UP: // center of header
	                pointOptionEnd = PointOption.point(dims.width / 10, edgeBorder);
	                break;
	            case LEFT: // center of left side
	                pointOptionEnd = PointOption.point(edgeBorder, dims.height / 2);
	                break;
	            case RIGHT: // center of right side
	                pointOptionEnd = PointOption.point(dims.width - edgeBorder, dims.height / 2);
	                break;
	            default:
	                throw new IllegalArgumentException("swipeScreen(): dir: '" + dir + "' NOT supported");
	        }

	        // execute swipe using TouchAction
	        try {
	            new TouchAction(driver)
	                    .press(pointOptionStart)
	                    // a bit more reliable when we add small wait
	                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(PRESS_TIME)))
	                    .moveTo(pointOptionEnd)
	                    .release().perform();
	        } catch (Exception e) {
	            System.err.println("swipeScreen(): TouchAction FAILED\n" + e.getMessage());
	            return;
	        }

	        // always allow swipe action to complete
	        try {
	            Thread.sleep(ANIMATION_TIME);
	        } catch (InterruptedException e) {
	            // ignore
	        }
	    }

	    public enum Direction {
	        UP,
	        DOWN,
	        LEFT,
	        RIGHT;
	    }
}
