package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.DashboardPageUI;

public class DashboardPageObject extends BasePage{
	WebDriver driver;
	public DashboardPageObject(WebDriver driver) {
		super();
		this.driver = driver;
	}
	public boolean isDashboardHeaderTextDisplaed() {
		waitForElementVisible(driver, DashboardPageUI.DASHBOARD_HEADER_TEXT);
		return isElementDisplayed(driver, DashboardPageUI.DASHBOARD_HEADER_TEXT);
	}

}
