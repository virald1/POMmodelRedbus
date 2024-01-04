package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;

import com.aventstack.extentreports.Status;
import extentlisteners.ExtentListeners;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public WebDriver driver;
	public Properties Config = new Properties();
	public FileInputStream fis;
	public String browser;
	
	public static  String srcfileName;
    
	public static  void captureScreenshot() {
		Date d = new Date();
		srcfileName = d.toString().replace(":", "_").replace(" ", "_") + ".jpg";
		File screenshot = ((TakesScreenshot) Basepage.driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screenshot, new File(System.getProperty("user.dir") + "\\target\\reports\\" + srcfileName));
			FileUtils.copyFile(screenshot, new File(System.getProperty("user.dir") + "/test-output/html/" + srcfileName));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public void extentlog(String msg) {
		Reporter.log(msg +"<br>");
		ExtentListeners.test.log(Status.INFO,msg);
	}
	
	
	public void setup(String browser) throws IOException {

		PropertyConfigurator.configure(".\\src\\test\\resources\\property\\log4j.properties");
		fis = new FileInputStream(".\\src\\test\\resources\\property\\Config.properties");
		Config.load(fis);
		extentlog("Config property loaded");

		if (browser.equals("chrome")) {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-infobars");
			options.addArguments("--disable-notifications");
			options.setExperimentalOption("excludeSwitches", Arrays.asList("enable-automation"));
		
			//WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(options);
		} else if (browser.equals("firefox")) {
			//WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		
		extentlog("Browser launched");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.get(Config.getProperty("url"));
		extentlog("Navigated to testsite URL");
	}
	
	
	//@AfterMethod
	public void teardown() {
		driver.close();
	}

}
