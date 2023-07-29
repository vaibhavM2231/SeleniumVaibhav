package com.assignment.testcases;

import org.testng.annotations.Test;

import com.assignment.base.TestBase;
import com.assignment.pagefactory.HomePage;
import com.assignment.pagefactory.MyAccountPage;
import com.assignment.pagefactory.SignUpPage;
import com.assignment.utils.Utilities;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class SignUpTest extends TestBase {

	WebDriver driver;

	@BeforeMethod
	public void beforeMethod() {

		driver =initializeDriver(TestBase.getValue("browser"));
		navigateToUrl(driver, TestBase.getValue("url"));

		HomePage homePage= new HomePage(driver);
		homePage.ClickonLinkCreateAccount();
	}


	
	@Test(priority = 1,groups = {"Sanity"},dataProvider="SignupDataProvider")
	public void SignUpTestCases(String fName, String lName, String email, String Password) {
		
		SignUpPage signup = new SignUpPage(driver);
		
		Assert.assertTrue(signup.isTitleDisplayed(),"Page Title is displayed" );
		
		signup.FillupForm(fName, lName, email, Password);
		
		MyAccountPage myAccount = new MyAccountPage(driver);
		
		Assert.assertTrue(myAccount.isTitleDisplayed(),"User is navigated to My Account Page" );
		
		Assert.assertTrue(myAccount.isSuccessMessageDisplayed(),"Success Message is displayed" );
		
		
	}

	@DataProvider (name="SignupDataProvider")
	public static Object [][] getLoginDataprovider(Method m) {
		
		Object [][] data = Utilities.getDataFromExcel(m.getName());
		
		return data;
	}


	@AfterMethod
	public void afterMethod() {
		driver.close();
	}

	@BeforeClass
	public void beforeClass() {
	}

	@AfterClass
	public void afterClass() {
	}

}
