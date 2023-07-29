package com.assignment.pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	WebDriver driver;


	@FindBy(xpath = "(//a[contains(text(),'Create an Account')])[1]") private WebElement linkCreateAccount;
	
	@FindBy(xpath = "(//a[contains(text(),'Sign In')])[1]") private WebElement linkSignIN;

	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver,this);
	}
	
	/*create some actions*/
	
	public void ClickonLinkCreateAccount() {
		linkCreateAccount.click();
	}
	
	public void clickOnSignIn() {
		linkSignIN.click();
	}
}


