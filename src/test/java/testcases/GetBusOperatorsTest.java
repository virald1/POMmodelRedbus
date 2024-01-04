package testcases;

import org.testng.annotations.Test;
import base.BaseTest;
import pages.Homepage;
import pages.OperatorsDirectoryPage;

public class GetBusOperatorsTest extends BaseTest{
	
	@Test
	public void getBusOperatorsTest() throws Exception {
		
		setup("chrome");
		Homepage home     = new Homepage(driver);
		OperatorsDirectoryPage busOpearators=home.gotoBusOperators();
											 busOpearators.getAllBusOpertorofA();
											 busOpearators.updateBusOperatorsInFile("BusOperators");
											 
											//fail
											 
										 				 
							
	}
	
	
}
