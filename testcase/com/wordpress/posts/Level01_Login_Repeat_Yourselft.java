package com.wordpress.posts;

import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Level01_Login_Repeat_Yourselft {
	WebDriver driver;
	String projectLocation = System.getProperty("user.dir");

	By usernameTextboxBy = By.xpath("//input[@id='usernameOrEmail']");
	By passwordTextboxBy = By.xpath("//input[@id='password']");
	By continueButtonBy = By.xpath("//button[text()='Continue']");
	By loginButtonBy = By.xpath("//button[text()='Log In']");

	@BeforeClass
	public void beforeClass() {
		// System.setProperties("");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.manage().window().maximize();
	}

	@BeforeMethod
	public void beforeMethod() {
		driver.get("https://wordpress.com/log-in");

	}

	@Test
	public void Login_01_Empty_Email_Username() {
		driver.findElement(usernameTextboxBy).sendKeys("");
		driver.findElement(continueButtonBy).click();
      sleepInSecond(2);
      Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Please enter a username or email address.']")).isDisplayed());
	}

	@Test
	public void Login_02_Invalid_Email() {
		driver.findElement(usernameTextboxBy).sendKeys("automation3256@fmail#com");
		driver.findElement(continueButtonBy).click();
      sleepInSecond(2);
      String userNotExistMessage=driver.findElement(By.xpath("//div[@class='form-input-validation is-error has-icon']")).getText();
      Assert.assertEquals(userNotExistMessage, "User does not exist. Would you like to create a new account?");
      Assert.assertTrue(driver.findElement(By.xpath("//div[@class='form-input-validation is-error has-icon' and contains(string(),'User does not exist. Would you like to create a new account?')]")).isDisplayed());
	}

	@Test
	public void Login_03_Username_Not_Exist() {
		driver.findElement(usernameTextboxBy).sendKeys("autotest"+getRandom());
		driver.findElement(continueButtonBy).click();
      sleepInSecond(2);
      String userNotExistMessage=driver.findElement(By.xpath("//div[@class='form-input-validation is-error has-icon']")).getText();
      Assert.assertEquals(userNotExistMessage, "User does not exist. Would you like to create a new account?");
      Assert.assertTrue(driver.findElement(By.xpath("//div[@class='form-input-validation is-error has-icon' and contains(string(),'User does not exist. Would you like to create a new account?')]")).isDisplayed());
	}

	@Test
	public void Login_04_Empty_Password() {
		driver.findElement(usernameTextboxBy).sendKeys("testautomation");
		driver.findElement(continueButtonBy).click();
		sleepInSecond(2);
		driver.findElement(passwordTextboxBy).sendKeys("");
		driver.findElement(loginButtonBy).click();
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='login-form__validation-error-wrapper']")).isDisplayed());
		}

	@Test
	public void Login_05_Invalid_Password() {
		driver.findElement(usernameTextboxBy).sendKeys("testautomation");
		driver.findElement(continueButtonBy).click();
		sleepInSecond(2);
		driver.findElement(passwordTextboxBy).sendKeys("111");
		driver.findElement(loginButtonBy).click();
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='login-form__validation-error-wrapper']")).isDisplayed());
	}
	@Test
	public void Login_06_Incorrect_Password() {
		driver.findElement(usernameTextboxBy).sendKeys("testautomation");
		driver.findElement(continueButtonBy).click();
		sleepInSecond(2);
		driver.findElement(passwordTextboxBy).sendKeys("123456789");
		driver.findElement(loginButtonBy).click();
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='login-form__validation-error-wrapper']")).isDisplayed());
	}
	@Test
	public void Login_07_Valid_Email_Password() {
		driver.findElement(usernameTextboxBy).sendKeys("");
		driver.findElement(continueButtonBy).click();
		sleepInSecond(2);
		driver.findElement(passwordTextboxBy).sendKeys("");
		driver.findElement(loginButtonBy).click();
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//h1[contains(text(),'Bảng tin')]")).isDisplayed());
	}
	public void sleepInSecond(long time) {
		try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
public int getRandom() {
	Random rand=new Random();
	return rand.nextInt(9999);
}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
