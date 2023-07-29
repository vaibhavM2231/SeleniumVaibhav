package com.assignment.pagefactory;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.assignment.utils.Utilities;

public class SignUpPage {

	WebDriver driver;


	@FindBy(xpath = "//span[text()='Create New Customer Account']") private WebElement pageTitle;
	@FindBy(css = "input#firstname") private WebElement inputFname;
	@FindBy(css = "input#lastname") private WebElement inputLname;
	@FindBy(css = "input#is_subscribed") private WebElement chkBoxSubscribe;
	@FindBy(css = "input#email_address") private WebElement inputEmail;
	@FindBy(css = "input#password") private WebElement inputPassword;
	@FindBy(css = "input#password-confirmation") private WebElement inputConfirmPassword;
	
	@FindBy(xpath = "//button[@title='Create an Account']") private WebElement btnSubmit;
	
	@FindBy(xpath = "//div[contains(text(),'There is already an account with this email address.,')]") 
	private WebElement txtErrorMsg;
			

	public SignUpPage(WebDriver driver) {
		PageFactory.initElements(driver,this);
	}
	
	/*create some actions*/
	
	public void FillupForm(String Fname, String Lname, String email, String password) {
		
		inputFname.sendKeys(Fname);
		inputLname.sendKeys(Lname);
		inputEmail.sendKeys(email);
		inputPassword.sendKeys(password);
		inputConfirmPassword.sendKeys(password);
		
		btnSubmit.click();
				
	}
	
	public boolean isTitleDisplayed() {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(Utilities.GLOBAL_WAIT_TIME));

		wait.until(ExpectedConditions.visibilityOf(pageTitle));
		if(pageTitle.isDisplayed()) 
			return true;
		else return false;	
	}
	
	
}


