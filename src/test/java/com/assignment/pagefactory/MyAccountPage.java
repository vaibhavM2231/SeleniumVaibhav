package com.assignment.pagefactory;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.assignment.utils.Utilities;

public class MyAccountPage {
	
	
	WebDriver driver;
	
	@FindBy(xpath = "//span[contains(text(),'My Account')]") private WebElement pageTitle;
	@FindBy(xpath = "//div[contains(text(),'Thank you for registering with Main Website Store.')]") private WebElement msgSuccess;
	
	
	public MyAccountPage(WebDriver driver) {
		PageFactory.initElements(driver,this);
		this.driver=driver;
	}
	
	
	public boolean isTitleDisplayed() {
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(Utilities.GLOBAL_WAIT_TIME));
		wait.until(ExpectedConditions.visibilityOf(pageTitle));
		if(pageTitle.isDisplayed()) 
			return true;
		else return false;	
	}
	
	public boolean isSuccessMessageDisplayed() {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(Utilities.GLOBAL_WAIT_TIME));
		wait.until(ExpectedConditions.visibilityOf(msgSuccess));
		if(msgSuccess.isDisplayed()) 
			return true;
		else return false;	
	}
}
