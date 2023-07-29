package com.assignment.testcases;



import com.assignment.base.TestBase;
import com.assignment.pagefactory.HomePage;
import com.assignment.pagefactory.LoggedInPage;
import com.assignment.pagefactory.LoginPage;
import com.assignment.pagefactory.ProductPage;
import com.assignment.pagefactory.SalePage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;

import java.lang.reflect.Method;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;

public class SaleTest extends TestBase {

	WebDriver driver;

	@BeforeMethod
	public void beforeMethod() {

		driver =initializeDriver(TestBase.getValue("browser"));
		navigateToUrl(driver, TestBase.getValue("url"));

		HomePage homePage= new HomePage(driver);
		homePage.clickOnSignIn();
		LoginPage login =new LoginPage(driver);
		login.Login(getData("username"), getData("password"));
	
	}

	@Test(priority = 1,groups = {"Sanity"}, dataProvider = "productDataProvider")
	public void selectProductandNavigateToCheckOut(String productName, String color, String size, String qty) {
		
		LoggedInPage loggedIn = new LoggedInPage(driver);
		
		loggedIn.clickOnSaleLink();
		
		SalePage sale = new SalePage(driver);
		assertTrue(sale.isTitleDisplayed(), "Sale Page is Displayed");
		
		sale.clickOnHoodiesLink();
		sale.selectProduct(productName);
		
		ProductPage product = new ProductPage(driver);
		
		assertTrue(product.isProductTitleDisplayed(productName),"Product Page is Displayed");
		
		product.selectColor(color);
		product.selectSize(size);
		product.selectQuantity(qty);
		
		product.clickOnAddToCart();
		assertTrue(product.isProductAddedToCart(), "Product is added to Cart");
	
	}
	
	@Test(priority = 0,groups = {"Sanity","Parallel"}, dataProvider = "productDataProvider")
	public void selectProductAddToWishList(String productName, String color, String size, String qty) {
		
		LoggedInPage loggedIn = new LoggedInPage(driver);
		
		loggedIn.clickOnSaleLink();
		
		SalePage sale = new SalePage(driver);
		assertTrue(sale.isTitleDisplayed(), "Sale Page is Displayed");
		
		sale.clickOnHoodiesLink();
		sale.selectProduct(productName);
		
		ProductPage product = new ProductPage(driver);
		
		assertTrue(product.isProductTitleDisplayed(productName),"Product Page is Displayed");
		
		product.addToWishList();;
		assertTrue(product.isProductWishListed(), "Product is added to WishList");
	
	}

	@DataProvider (name="productDataProvider")
	public static Object [][] DataProvider(Method m) {
		
		Object [][] data = {{ "Eos V-Neck Hoodie", "Blue", "S", "1"},
							{ "Ariel Roll Sleeve Sweatshirt", "Green", "M", "2"},
							{ "Phoebe Zipper Sweatshirt", "Gray", "L", "3"}
							};
		
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
