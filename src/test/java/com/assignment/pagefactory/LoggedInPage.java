package com.assignment.pagefactory;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.assignment.utils.Utilities;

public class LoggedInPage {
	WebDriver driver;

	public LoggedInPage(WebDriver driver) {
		PageFactory.initElements(driver,this);
		this.driver=driver;
	}


	@FindBy(xpath = "//span[@class='logged-in' and contains(text(),'Welcome,')]") private WebElement txtWelcome;

	@FindBy(xpath = "//button[@class='action switch']") private WebElement btnAction;

	@FindBy(xpath = "//span[text()='Sale']") private WebElement linkSale;


	public boolean isWelcomeTextDisplayed() {

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(Utilities.GLOBAL_WAIT_TIME));

		wait.until(ExpectedConditions.visibilityOf(txtWelcome));
		if(txtWelcome.isDisplayed()) 
			return true;
		else return false;	
	}

	
	public void clickOnSaleLink() {
		linkSale.click();
	}









}



