package com.assignment.pagefactory;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.assignment.utils.Utilities;

public class SalePage {

	WebDriver driver;

	public SalePage(WebDriver driver) {
		PageFactory.initElements(driver,this);
		this.driver=driver;
	}



	@FindBy(xpath = "//h1//span[text()='Sale']") private WebElement titleSale;

	@FindBy(xpath = "(//a[text()='Hoodies and Sweatshirts'])[1]") private WebElement linkHoodies;


	String productName="";
	String locator = "//a[contains(text(),'"+productName+"')] ";


	public void clickOnHoodiesLink() {
		linkHoodies.click();

	}

	public boolean isTitleDisplayed() {

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(Utilities.GLOBAL_WAIT_TIME));

		wait.until(ExpectedConditions.visibilityOf(titleSale));
		if(titleSale.isDisplayed()) 
			return true;
		else return false;	
	}
	
	
	public void selectProduct(String productName) {
		locator = "//a[contains(text(),'"+productName+"')]";
		
		driver.findElement(By.xpath(locator)).click();
		
	}







}
