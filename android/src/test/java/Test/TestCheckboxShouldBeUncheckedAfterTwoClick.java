package Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import page.AppHomePage;

public class TestCheckboxShouldBeUncheckedAfterTwoClick extends BaseTest{
	@Test
	public void CheckboxShouldBeUncheckedAfterTwoClick() throws InterruptedException {
		AppHomePage appHomePage = new AppHomePage(this.driver);
		Boolean shouldBeUncheckedAfterTwoClick = appHomePage.checkBoxShouldBeUnCheckedAfterTwoClick();
		Assert.assertFalse(shouldBeUncheckedAfterTwoClick);
	}
}
