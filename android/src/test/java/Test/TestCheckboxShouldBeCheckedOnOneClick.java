package Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import page.AppHomePage;

public class TestCheckboxShouldBeCheckedOnOneClick extends BaseTest{
	@Test
	public void CheckboxShouldBeCheckedAfterOneClick() throws InterruptedException {
		AppHomePage appHomePage = new AppHomePage(this.driver);
		Boolean checkboxShouldBeChecked = appHomePage.checkBoxShoulbBeCheckedAfterOneClick();
		Assert.assertTrue(checkboxShouldBeChecked);	
	}
}
