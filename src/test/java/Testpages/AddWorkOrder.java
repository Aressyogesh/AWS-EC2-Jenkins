package Testpages;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Core.StepBase;
import Pagefactory.LoginPage;
import Pagefactory.WorkOrderPage;

public class AddWorkOrder extends StepBase {
	StepBase stepbase = new StepBase();
	WorkOrderPage objWO = new WorkOrderPage();
	LoginPage objLogin = new LoginPage();
	
	@Parameters("browser")
	@BeforeTest
	public void setup(String browser) throws Exception {
		stepbase.setup(browser);
		//objLogin.login(uname,pswd);
	}
	
	@Test 
	public void addWorkOrder() throws Exception {
		objWO.addWorkOrder();
		
	}
	
	
	@AfterTest 
	public void tearDown() throws Exception {

		driver.quit();
	}


}
