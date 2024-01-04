package base;

import java.time.Duration;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.aventstack.extentreports.Status;

import extentlisteners.ExtentListeners;


public class Basepage {
	
	public static WebDriver driver;
	public WebDriverWait w;
	public Logger log = Logger.getLogger(Basepage.class.getName());
	public JavascriptExecutor js;
	
	
	public Basepage(WebDriver driver) {
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
		w = new WebDriverWait(driver, Duration.ofSeconds(5));
		js = (JavascriptExecutor)driver;
	}
	
	public void click(WebElement key, String msg) {
		 
		key.click();
		log.info("Cliking on an Element: " + msg);
		Reporter.log("Cliking on an Element: " + msg +"<br>");
		ExtentListeners.test.log(Status.INFO, "Cliking on an Element: "+msg);
	}

	public void type(WebElement key, String value, String msg) {
		key.sendKeys(value);
		log.info("Typing in an Element: " + msg + " value is: " + value + "<br>");
		Reporter.log("Typing in an Element: " + msg + " value is: " + value);
		ExtentListeners.test.log(Status.INFO, "Typing in an Element: " + msg + " value is: " + value);
	}

}
