package utilities;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.PropertyConfigurator;

public class BaseClass {
	
	public WebDriver driver;
	public static String projectPath = System.getProperty("user.dir");
	public static String screenshotFilePath = projectPath + "/screenshots/";
	public static String reportFilePath = projectPath + "/Reports/";
	public static String reportPathName = new SimpleDateFormat("YYYY-MM-dd-hh-mm-ss-SSS").format(new Date()) + "_Report.html";
	public static String reportsPath = projectPath + File.separator + "Reports" + reportPathName;
	public String chromeDriverPath = projectPath + File.separator + "resources" + File.separator + "drivers" + File.separator + "chromedriver.exe";
	public String geckoFireFoxDriverPath = projectPath + File.separator + "resources" + File.separator + "drivers" + File.separator + "geckodriver.exe";
	public ConfigFileUtility globalConfig = new ConfigFileUtility();

	protected ExtentReports reports;
	// create variable for extent test
	protected ExtentTest test;
	// create variable for Itest results
	//protected ITestResult result;

	public BaseClass() throws IOException {
		PropertyConfigurator.configure("log4j.properties");
		globalConfig.loadPropertyFile();
	}
	
	// Explicit wait method
	public static WebElement waitForExpectedElement(WebDriver driver, final By locator, int time) {
		WebDriverWait wait = new WebDriverWait(driver, time);
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	// Explicit wait method
	public static WebElement waitForExpectedElement(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		return wait.until(ExpectedConditions.visibilityOf(element));
	}

	// Explicit wait
	public static WebElement waitForExpectedElement(WebDriver driver, final By locator) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	// Explicit wait method
	public boolean objectExists(WebDriver driver, final By locator) {
		try {
			waitForPageToLoad();
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public void endReport() {
		reports.endTest(test);
		reports.flush();
	}
	

	@Parameters("platform")
	@BeforeClass
	public void launchBrowser(String browserName) throws Exception {
		
		if (browserName.equalsIgnoreCase("chrome")) {
			reports = new ExtentReports(reportFilePath + "Web_Report_" + reportPathName, false);
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("firefox")) {
			reports = new ExtentReports(reportFilePath + "Web_Report_" + reportPathName, false);
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			System.out.println("FireFox Browser is Launched");
		}
		driver.manage().deleteAllCookies();
		driver.get(globalConfig.getProperty("URL"));
		driver.manage().window().maximize();
	}
	
	@AfterMethod
	public void result(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			test.log(LogStatus.FAIL, "Test failed");
		}
		reports.endTest(test);
	}

	@AfterClass
	public void tearDown() {
		driver.close();

	}

	// Method for Retry and It executes the failed test case based on our count
	public class Retry implements IRetryAnalyzer {
		private int count = 0;
		private static final int maxTry = 3;

		public boolean retry(ITestResult iTestResult) {
			if (!iTestResult.isSuccess()) { // Check if test not succeed
				if (count < maxTry) { // Check if maxtry count is reached
					count++; // Increase the maxTry count by 1
					iTestResult.setStatus(ITestResult.FAILURE); // Mark test as failed
					return true; // Tells TestNG to re-run the test
				} else {
					iTestResult.setStatus(ITestResult.FAILURE); // If maxCount reached,test marked as failed
				}
			} else {
				iTestResult.setStatus(ITestResult.SUCCESS); // If test passes, TestNG marks it as passed
			}
			return false;
		}
	}

	// Explicit wait method (While Script Execution we need to pass time limit)
	public boolean objectExists(WebDriver driver, final By locator, int timeout) {
		try {
			waitForPageToLoad();
			WebDriverWait wait = new WebDriverWait(driver, timeout);
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	// Explicit wait method (While Script Execution we need to pass time limit
	public void waitForPageToLoad() {
		(new WebDriverWait(driver, 60)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return (((org.openqa.selenium.JavascriptExecutor) driver).executeScript("return document.readyState")
						.equals("complete"));
			}
		});
	}

	// Method for timestamp
	public static String dateTimeStamp() {
		DateFormat dateFormat = new SimpleDateFormat("ddMMyy_HHmm");
		Date dt = new Date();
		return dateFormat.format(dt);
	}

	public static String timeStamp() {
		DateFormat dateFormat = new SimpleDateFormat("ddMMyy");
		Date dt = new Date();
		return dateFormat.format(dt);
	}
	
	// Method for timestamp
	public static String vindateTimeStamp() {
		DateFormat dateFormat = new SimpleDateFormat("ddmmss");
		Date dt = new Date();
		return dateFormat.format(dt);
	}

	// Capture Screen Shot and save in the screen shots folder
		public static String captureScreenshot(WebDriver driver, String screenshotName) {
			String scrFileName = null;
			try {
				DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy_HH-mm-ss");
				Date dt = new Date();
				//System.out.println(dateFormat.format(dt));
				scrFileName = screenshotName + "_" + dateFormat.format(dt) + ".png";

				TakesScreenshot ts = (TakesScreenshot) driver;
				File source = ts.getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(source,
						new File(screenshotFilePath + screenshotName + "_" +dateFormat.format(dt) + ".png"));
				//System.out.println("screenshot taken");
			} catch (Exception e) {
				System.out.println("exception while taking screenshot" + e.getMessage());
			}
			return scrFileName;

		}
}
