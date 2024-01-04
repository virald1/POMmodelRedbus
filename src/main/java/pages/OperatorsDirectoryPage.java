package pages;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import base.Basepage;

public class OperatorsDirectoryPage extends Basepage {

		public OperatorsDirectoryPage(WebDriver driver) {
			super(driver);
	}
		
		@FindBy(xpath="//a[contains(@href,'directory/a')]")
		public WebElement directoryA;
		
		@FindBy(xpath="//a[contains(@href,'directory/i')]")
		public WebElement directoryI;
		
		@FindBy(xpath="//a[contains(@href,'directory/r')]")
		public WebElement directoryR;
		
		@FindBy(xpath="//a[contains(@href,'a/3')]")
		public WebElement page3;
		
		@FindBy(css="li.D113_item>a")
		public List<WebElement> operators;
		
		public ArrayList<String> list = new ArrayList<String>();

		public void getAllBusOpertorofA() { 
			
			click(directoryI,"directory I");
			click(directoryR,"directory R");
			click(directoryA,"directory A");
			
			js.executeScript("arguments[0].scrollIntoView();",page3);
			w.until(ExpectedConditions.elementToBeClickable(page3));
			js.executeScript("arguments[0].click();", page3);
			
			for(int i=0;i<operators.size();i++) {	
				
				String text = operators.get(i).getText();
				list.add(text);		
			}	
		}
		
		
		public void updateBusOperatorsInFile(String fileName) {

			String info ="Bus Operators Name: \n";
			File file = new File(fileName);
			try {
				FileWriter fw = new FileWriter(file, true);    
				fw.write(info);
				String lineSeparator = System.getProperty("line.separator");
				
				for (int i = 0; i < list.size(); i++) {		
					fw.write(list.get(i));
					fw.write(lineSeparator);
				}

				fw.flush();
				fw.close();
				FileUtils.copyFile(file, new File(".//busOperator//" + fileName + ".txt"));
				FileUtils.deleteQuietly(file);
			} catch (IOException e) {
				e.printStackTrace();

			}
		}
		
}

