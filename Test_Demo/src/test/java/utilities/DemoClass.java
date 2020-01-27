package utilities;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.winium.DesktopOptions;
import org.openqa.selenium.winium.WiniumDriver;
import org.openqa.selenium.winium.WiniumDriverService;

public class DemoClass {
	
	public static void main(String args[]) throws Exception {
		
		WiniumDriver driver = null;
		 System.out.println("Test in progress A");
		 //String outlookApplicationPath = "D:\\OnePOS\\OnePos\\exe\\Onepos.Pos.App.Desktop.exe";
		 // String outlookApplicationPath = "C:\\Users\\Administrator\\Documents\\Zoom.exe";
		  String outlookApplicationPath = "C:\\Users\\Administrator\\AppData\\Roaming\\Zoom\\bin\\Zoom.exe";
		    String winiumDriverPath = "C:\\Users\\gayathri.chanathlaa\\Downloads\\Winium.Desktop.Driver.exe";
		    //Runtime.getRuntime().exec("C:\\Users\\Administrator\\Downloads\\Winium.Desktop.Driver\\Winium.Desktop.Driver.exe --port 9998");
		    DesktopOptions options = new DesktopOptions(); //Initiate Winium Desktop Options
		    options.setApplicationPath(outlookApplicationPath); //Set outlook application path
		    File drivePath = new File(winiumDriverPath); //Set winium driver path
		    System.out.println("Test in progress B");
		    //WiniumDriverService service = new WiniumDriverService.Builder().usingDriverExecutable(drivePath).usingPort(9999).withVerbose(true).withSilent(false).buildDesktopService();
		    System.out.println("Test in progress C");
		   // service.start(); //Build and Start a Winium Driver service
		    System.out.println("Test in progress D");
		    driver = new WiniumDriver(new URL ("http://192.168.5.14:9999"), options);
		    //driver = new WiniumDriver(service, options); //Start a winium driver
		    //Thread.sleep(25000);
		    System.out.println("Test in progress E");
		    Thread.sleep(16000);
		    //driver.get
		    //driver.findElement(By.name("Clear")).click();
		    driver.findElement(By.name("Join a Meeting")).click();
		    driver.findElement(By.name("Please enter your Meeting ID or Personal Link Name")).sendKeys("12345678902");
		     
		    driver.findElement(By.name("Join")).click();
		     
		    driver.findElement(By.name("Leave")).click();
		     
		    Thread.sleep(10000);
		     
		    //String output = driver.findElement(By.id("CalculatorResults")).getAttribute("Name");
		     
		    //System.out.println("Result after addition is: " + output);
		     
		   // driver.quit();
		 
			
	}

}
