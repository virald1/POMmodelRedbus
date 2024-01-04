package extentlisteners;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import base.Basepage;



public class ExtentManager {

	private static ExtentReports extent;
	
	    public static ExtentReports createInstance(String fileName) {
	        ExtentSparkReporter htmlReporter = new ExtentSparkReporter(fileName);
	       
	        
	        htmlReporter.config().setTheme(Theme.STANDARD);
	        htmlReporter.config().setDocumentTitle(fileName);
	        htmlReporter.config().setEncoding("utf-8");
	        htmlReporter.config().setReportName(fileName);
	        
	        extent = new ExtentReports();
	        extent.attachReporter(htmlReporter);
	        extent.setSystemInfo("Automation Tester", "Viral");
	        extent.setSystemInfo("Organization", "Accion");
	        extent.setSystemInfo("Build no", "Acc-1234");
	           
	        return extent;
	    }
	    
	    
    public static  String srcfileName;
	    
    	public static  void captureScreenshot() {
    		Date d = new Date();
    		srcfileName = d.toString().replace(":", "_").replace(" ", "_") + ".jpg";

    		File screenshot = ((TakesScreenshot)Basepage.driver).getScreenshotAs(OutputType.FILE);
    		try {
    			FileUtils.copyFile(screenshot, new File(System.getProperty("user.dir") + "\\target\\reports\\" + srcfileName));
    			FileUtils.copyFile(screenshot, new File(System.getProperty("user.dir") + "/test-output/html/" + srcfileName));
    			
    		} catch (IOException e) {
    			e.printStackTrace();
    		}

    	}	
	}
