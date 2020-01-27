package pageobjects;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utilities.BaseClass;

public class GmailLoginPageObjects extends BaseClass{	
	
	WebDriver driver;
	
	public static final By USERNAME_TEXTFIELD = By.xpath("//*[@id='identifierId']");
	public static final By NEXT_BUTTON = By.xpath("//*[@id=\'identifierNext\']/span/span");
	public static final By PASSWORD_TEXTFIELD = By.xpath("//*[@id=\'password\']/div[1]/div/div[1]/input");
	public static final By NEXT_BUTTON2 = By.xpath("//*[@id=\'passwordNext\']/span/span");
	
	public GmailLoginPageObjects(WebDriver driver) throws IOException {
		this.driver = driver;
	}

	public void userName_textField(WebDriver driver, String username) {
		try {
			waitForExpectedElement(driver, USERNAME_TEXTFIELD);
			driver.findElement(USERNAME_TEXTFIELD).sendKeys(username);
		} catch (Exception e) {
			e.getMessage();
		}
	}
	
	public void next_button(WebDriver driver) {
		try {
			waitForExpectedElement(driver,NEXT_BUTTON);
			driver.findElement(NEXT_BUTTON).click();
		} catch (Exception e) {
			e.getMessage();
		}
	}
	
	public void password_textField(WebDriver driver, String password) {
		try {
			waitForExpectedElement(driver, PASSWORD_TEXTFIELD);
			driver.findElement(PASSWORD_TEXTFIELD).sendKeys(password);
		} catch (Exception e) {
			e.getMessage();
		}
	}
	
	public void next_button2(WebDriver driver) {
		try {
			waitForExpectedElement(driver,NEXT_BUTTON2);
			driver.findElement(NEXT_BUTTON2).click();
		} catch (Exception e) {
			e.getMessage();
		}
	}
	
}
