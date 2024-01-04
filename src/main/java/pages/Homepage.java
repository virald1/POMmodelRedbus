package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import base.Basepage;

public class Homepage extends Basepage{


	public Homepage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath="//h2[contains(text(),'PARTNERED WITH')]")
	public WebElement busOperator;
	
	@FindBy(xpath="//h2/a")
	public WebElement viewAll;
	
	
	public OperatorsDirectoryPage gotoBusOperators() {
		
		js.executeScript("arguments[0].scrollIntoView();", busOperator);
		click(viewAll, "viewAll");
		
		return new OperatorsDirectoryPage(driver);
	}

}
