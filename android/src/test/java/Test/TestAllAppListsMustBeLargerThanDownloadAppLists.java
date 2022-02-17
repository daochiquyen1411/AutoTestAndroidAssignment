package Test;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import page.AppHomePage;

public class TestAllAppListsMustBeLargerThanDownloadAppLists extends BaseTest{
	@Test
	public void systemAppCount() throws InterruptedException {
		AppHomePage appHomePage = new AppHomePage(this.driver);
		int totalAppLists = appHomePage.totalAppListCount();
		int downloadAppLists = appHomePage.downloadAppListCount();
		Assert.assertTrue(totalAppLists > downloadAppLists);
		System.out.println("System app " + (totalAppLists - downloadAppLists) + " Total App " + totalAppLists + " Download app " + downloadAppLists);
	}
}
