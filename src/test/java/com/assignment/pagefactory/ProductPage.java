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

public class ProductPage {
	
WebDriver driver;
	
	@FindBy(xpath = "//input[@id='qty']") private WebElement inputQty;

	@FindBy(xpath = "//button[@title='Add to Cart']") private WebElement btnAddToCart;
	@FindBy(xpath = "(//span[text()='Add to Wish List'])[1]") private WebElement btnAddToWishlist;
	@FindBy(xpath = "//a[contains(text(),'shopping cart')]") private WebElement txtSuccess;
	@FindBy(xpath = "//div[contains(text(),'added to your Wish List.')]") private WebElement txtWishListSuccess;
	
	
		
	
	public ProductPage(WebDriver driver) {
		PageFactory.initElements(driver,this);
		this.driver=driver;
	}
	
	public boolean isProductTitleDisplayed(String productName) {
		String label="//span[contains(text(),'"+productName+"')]";
		WebElement element = driver.findElement(By.xpath(label));

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

		wait.until(ExpectedConditions.visibilityOf(element));
		if(element.isDisplayed()) 
			return true;
		else return false;	
	}
	
	
	public void selectSize(String size) {
		String label="//div[@option-label='"+size+"']";
		WebElement element = driver.findElement(By.xpath(label));
		element.click();
		
	}
	
	public void selectColor(String color) {
		String label="//div[@aria-label='"+color+"']";
		WebElement element = driver.findElement(By.xpath(label));
		element.click();
	}
	
	public void selectQuantity(String qty) {
		inputQty.clear();
		inputQty.sendKeys(qty);
	}
	
	
	public void clickOnAddToCart() {
		btnAddToCart.click();
		
	}
	
	public boolean isProductAddedToCart() {
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(Utilities.GLOBAL_WAIT_TIME));
		wait.until(ExpectedConditions.visibilityOf(txtSuccess));
		if(txtSuccess.isDisplayed()) 
			return true;
		else return false;	
	}
	
	public void addToWishList() {
		btnAddToWishlist.click();
	}
	
	
	public boolean isProductWishListed() {
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(Utilities.GLOBAL_WAIT_TIME));
		wait.until(ExpectedConditions.visibilityOf(txtWishListSuccess));
		if(txtWishListSuccess.isDisplayed()) 
			return true;
		else return false;	
	}
}
