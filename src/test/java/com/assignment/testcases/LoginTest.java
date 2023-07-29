package com.assignment.testcases;

import static org.testng.Assert.assertTrue;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.assignment.base.TestBase;
import com.assignment.pagefactory.HomePage;
import com.assignment.pagefactory.LoggedInPage;
import com.assignment.pagefactory.LoginPage;

import com.assignment.utils.Utilities;

public class LoginTest extends TestBase {
	
	
	WebDriver driver;

	@BeforeMethod
	public void beforeMethod() {

		driver =initializeDriver(TestBase.getValue("browser"));
		navigateToUrl(driver, TestBase.getValue("url"));

		HomePage homePage= new HomePage(driver);
		homePage.clickOnSignIn();
	}



	@Test (priority=0, groups = {"Sanity","Parallel"})
	public void LoginTestPositive() {
		
		LoginPage login = new LoginPage(driver);	
		login.Login(getData("username_login"), getData("password_login"));
		
		LoggedInPage loggedIn = new LoggedInPage(driver);
		
		assertTrue(loggedIn.isWelcomeTextDisplayed(), "Welcome Message to User");
		
		
	}
	
	
	
	@Test (priority=1, groups = {"Sanity"},dataProvider ="LoginNegativeTestData")
	public void LoginTestNegative(String username, String password, String error) {
		
		LoginPage login = new LoginPage(driver);	
		login.Login(username,password);
		
		
		assertTrue(login.isLoginPageTitleDisplayed(), "User is in Login Page");
		Assert.assertEquals(login.getEmailFieldErrorMessage(),error, "Mandatory Error for email Field");
	
	}

	@DataProvider (name="LoginNegativeTestData")
	public static Object [][] getLoginDataprovider(Method m) {
		
		Object [][] data = {{"test.com", "Pass", "Please enter a valid email address (Ex: johndoe@domain.com)."},
							{"test","Pass", "Please enter a valid email address (Ex: johndoe@domain.com)."}};
		
		return data;
	}


	@AfterMethod
	public void afterMethod() {
		driver.close();
	}

	

}
