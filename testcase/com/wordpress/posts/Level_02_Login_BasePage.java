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

import commons.BasePage;

public class Level_02_Login_BasePage extends BasePage {
	WebDriver driver;
	String projectLocation = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		// System.setProperties("");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.manage().window().maximize();
	}

	@BeforeMethod
	public void beforeMethod() {
		openPageUrl(driver, "https://wordpress.com/log-in");
	}

	@Test
	public void Login_01_Empty_Email_Username() {
		senKeyToElement(driver, "//input[@id='usernameOrEmail']", "");
		clickToElement(driver, "//button[text()='Continue']");
		sleepInSecond(2);
		Assert.assertTrue(isElementDisplayed(driver, "//span[text()='Please enter a username or email address.']"));
	}

	@Test
	public void Login_02_Invalid_Email() {
		senKeyToElement(driver, "//input[@id='usernameOrEmail']", "automation3256@fmail#com");
		clickToElement(driver, "//button[text()='Continue']");
		sleepInSecond(2);
		String userNotExistMessage = getElementText(driver, "//div[@class='form-input-validation is-error has-icon']");
		Assert.assertEquals(userNotExistMessage, "User does not exist. Would you like to create a new account?");
		Assert.assertTrue(isElementDisplayed(driver,
				"//div[@class='form-input-validation is-error has-icon' and contains(string(),'User does not exist. Would you like to create a new account?')]"));
	}

	@Test
	public void Login_03_Username_Not_Exist() {
		senKeyToElement(driver, "//input[@id='usernameOrEmail']", "autotest" + getRandom());
		clickToElement(driver, "//button[text()='Continue']");
		sleepInSecond(2);
		String userNotExistMessage = getElementText(driver, "//div[@class='form-input-validation is-error has-icon']");
		Assert.assertEquals(userNotExistMessage, "User does not exist. Would you like to create a new account?");
		Assert.assertTrue(isElementDisplayed(driver,
				"//div[@class='form-input-validation is-error has-icon' and contains(string(),'User does not exist. Would you like to create a new account?')]"));
	}

	@Test
	public void Login_04_Empty_Password() {
		senKeyToElement(driver, "//input[@id='usernameOrEmail']", "testautomation");
		clickToElement(driver, "//button[text()='Continue']");
		sleepInSecond(2);
		senKeyToElement(driver, "//input[@id='password']", "");
		clickToElement(driver, "//button[text()='Log In']");
		sleepInSecond(2);
		Assert.assertTrue(isElementDisplayed(driver, "//p[@class='login-form__validation-error-wrapper']"));
	}

	@Test
	public void Login_05_Invalid_Password() {
		senKeyToElement(driver, "//input[@id='usernameOrEmail']", "testautomation");
		clickToElement(driver, "//button[text()='Continue']");
		sleepInSecond(2);
		senKeyToElement(driver, "//input[@id='password']", "111");
		clickToElement(driver, "//button[text()='Log In']");
		sleepInSecond(2);
		Assert.assertTrue(isElementDisplayed(driver, "//p[@class='login-form__validation-error-wrapper']"));
	}

	@Test
	public void Login_06_Incorrect_Password() {
		senKeyToElement(driver, "//input[@id='usernameOrEmail']", "testautomation");
		clickToElement(driver, "//button[text()='Continue']");
		sleepInSecond(2);
		senKeyToElement(driver, "//input[@id='password']", "123456789");
		clickToElement(driver, "//button[text()='Log In']");
		sleepInSecond(2);
		Assert.assertTrue(isElementDisplayed(driver, "//p[@class='login-form__validation-error-wrapper']"));
	}

	@Test
	public void Login_07_Valid_Email_Password() {
		senKeyToElement(driver, "//input[@id='usernameOrEmail']", "phuongnguyenth");
		clickToElement(driver, "//button[text()='Continue']");
		sleepInSecond(2);
		senKeyToElement(driver, "//input[@id='password']", "Phuongminh020503");
		clickToElement(driver, "//button[text()='Log In']");
		sleepInSecond(3);
		Assert.assertTrue(isElementDisplayed(driver, "//h1[contains(text(),'Bảng tin')]"));
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
