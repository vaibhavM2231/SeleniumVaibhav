package com.assignment.pagefactory;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.assignment.utils.Utilities;

public class LoginPage {
	
	WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		PageFactory.initElements(driver,this);
		this.driver=driver;
		
	}
	
	@FindBy(xpath = "//span[text()='Customer Login']") private WebElement pageTittle;
	
	@FindBy(id = "email") private WebElement inputEmail;
	@FindBy(id = "pass") private WebElement inputPassword;
	@FindBy(xpath = "//button[@name='send']") private WebElement btnSubmit;
	@FindBy(id = "pass-error") private WebElement txtPasswordError;
	@FindBy(id = "email-error") private WebElement txtEmailError;	
	
		
	
	public void Login(String email, String password) {
		
		inputEmail.sendKeys(email);
		inputPassword.sendKeys(password);
		btnSubmit.click();
	}
	
	public void clickOnSubmit() {
		btnSubmit.click();
		
	}
			
	
	public boolean isLoginPageTitleDisplayed() {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(Utilities.GLOBAL_WAIT_TIME));

		wait.until(ExpectedConditions.visibilityOf(pageTittle));
		if(pageTittle.isDisplayed()) 
			return true;
		else return false;	
		
	}
	
	public String getEmailFieldErrorMessage() {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(Utilities.GLOBAL_WAIT_TIME));
		wait.until(ExpectedConditions.visibilityOf(txtEmailError));

		return txtEmailError.getText();
		
	}
	
	public String getPasswordFieldErrorMessage() {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(Utilities.GLOBAL_WAIT_TIME));
		wait.until(ExpectedConditions.visibilityOf(txtPasswordError));

		return txtPasswordError.getText();
		
	}

}
