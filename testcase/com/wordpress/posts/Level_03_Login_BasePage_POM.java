package com.wordpress.posts;

import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageObjects.DashboardPageObject;
import pageObjects.LoginPageObject;

public class Level_03_Login_BasePage_POM {
	WebDriver driver;
	// String projectLocation = System.getProperty("user.dir");
	LoginPageObject loginPage;
	DashboardPageObject dashboardPageObject;

	@BeforeClass
	public void beforeClass() {
		// System.setProperties("");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.manage().window().maximize();
	}

	@BeforeMethod
	public void beforeMethod() {
		// openPageUrl(driver, "https://wordpress.com/log-in");
		driver.get("https://wordpress.com/log-in");
		loginPage = new LoginPageObject(driver);
	}

	@Test
	public void Login_01_Empty_Email_Username() {
		loginPage.inputToUsernameTextbox("");
		loginPage.clickToContinueButton();
		Assert.assertTrue(loginPage.isEmptyEmailErrorMessageDisplayed());

	}

	@Test
	public void Login_02_Invalid_Email() {
		loginPage.inputToUsernameTextbox("automation3256@fmail#com");
		loginPage.clickToContinueButton();
		loginPage.sleepInSecond(2);
		String userNotExistMessage = loginPage.getInvalidEmailErrorMessage();
		Assert.assertEquals(userNotExistMessage, "User does not exist. Would you like to create a new account?");
		// Assert.assertTrue(loginPage.getInvalidEmailErrorMessage());
	}

	@Test
	public void Login_03_Username_Not_Exist() {
		loginPage.inputToUsernameTextbox("autotest" + getRandom());
		loginPage.clickToContinueButton();
		loginPage.sleepInSecond(2);
		String userNotExistMessage = loginPage.getInvalidEmailErrorMessage();
		Assert.assertEquals(userNotExistMessage, "User does not exist. Would you like to create a new account?");
		// Assert.assertTrue(loginPage.getInvalidEmailErrorMessage());
	}

	@Test
	public void Login_04_Empty_Password() {
		loginPage.inputToUsernameTextbox("testautomation");
		loginPage.clickToContinueButton();
		loginPage.sleepInSecond(2);
		loginPage.inputToPasswordTextbox("");
		loginPage.clickToLoginButton();
		loginPage.sleepInSecond(2);
		Assert.assertTrue(loginPage.isEmptyPasswordErrorMessageDisplayed());
	}

	@Test
	public void Login_05_Invalid_Password() {
		loginPage.inputToUsernameTextbox("testautomation");
		loginPage.clickToContinueButton();
		loginPage.sleepInSecond(2);
		loginPage.inputToPasswordTextbox("1234");
		loginPage.clickToLoginButton();
		loginPage.sleepInSecond(2);
		Assert.assertTrue(loginPage.isInvalidPasswordErrorMEssageDisplaed());
	}

	@Test
	public void Login_06_Incorrect_Password() {
		loginPage.inputToUsernameTextbox("testautomation");
		loginPage.clickToContinueButton();
		loginPage.sleepInSecond(2);
		loginPage.inputToPasswordTextbox("123456789");
		loginPage.clickToLoginButton();
		loginPage.sleepInSecond(2);
		Assert.assertTrue(loginPage.isInvalidPasswordErrorMEssageDisplaed());
	}

	@Test
	public void Login_07_Valid_Email_Password() {
		loginPage.inputToUsernameTextbox("phuongnguyenth");
		loginPage.clickToContinueButton();
		loginPage.sleepInSecond(2);
		loginPage.inputToPasswordTextbox("Phuongminh020503");
		loginPage.clickToLoginButton();
		loginPage.sleepInSecond(3);
		dashboardPageObject = new DashboardPageObject(driver);
		Assert.assertTrue(dashboardPageObject.isDashboardHeaderTextDisplaed());
	}

	public int getRandom() {
		Random rand = new Random();
		return rand.nextInt(9999);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
