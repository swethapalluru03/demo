package testcases;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import jxl.read.biff.BiffException;
import pageobjects.GmailLoginPageObjects;
import utilities.BaseClass;
import utilities.ConfigFileUtility;

public class GmailLoginTestCases extends BaseClass{
	
	private static final Logger logger = Logger.getLogger(GmailLoginTestCases.class);
	GmailLoginPageObjects gmaillogin = new GmailLoginPageObjects(driver);
	
	public GmailLoginTestCases() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Parameters("platform")
	@Test
	public void login(String platform) throws BiffException, IOException {
		test = reports.startTest("Verify Login Functionality");
		test.log(LogStatus.INFO, "Browser is Lanuched");
		logger.info("open screen");
		test.log(LogStatus.INFO, "open gmail login page ");
		gmaillogin.userName_textField(driver, globalConfig.getProperty("username"));
		test.log(LogStatus.INFO, "Email Field is filled with : " + globalConfig.getProperty("username"));
		logger.info("Screenshot taken : " + captureScreenshot(driver, "enter username"));
		gmaillogin.next_button(driver);
		test.log(LogStatus.PASS, "Clicked on next button");
		gmaillogin.password_textField(driver, globalConfig.getProperty("validpassword"));
		test.log(LogStatus.INFO, "Password Field is filled with : " + globalConfig.getProperty("validpassword"));
		gmaillogin.next_button2(driver);
		test.log(LogStatus.PASS, "Clicked on next button");
		endReport();
	}
}
