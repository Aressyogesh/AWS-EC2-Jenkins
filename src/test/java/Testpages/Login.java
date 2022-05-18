package Testpages;


import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import DataProvider.DataProviders;

import Core.Log;
import Core.StepBase;
import DataProvider.DataProviders;
import Pagefactory.LoginPage;
import TestData.loginData;
import Utils.RetryAnalyzer;

/**
 * @CreateBy Yogesh Lolage
 */
public class Login extends StepBase {
	StepBase stepbase = new StepBase();
	LoginPage objLogin = new LoginPage();
	
	@Parameters("browser")
	@BeforeMethod 
	public void setup(String browser) throws Exception {
		stepbase.setup(browser);

	}
		
	//@Test(priority=1, enabled=true, retryAnalyzer = Utils.RetryAnalyzer.class) 
	@Test(priority=1, enabled=true, dataProvider = "credentials", dataProviderClass = DataProvider.DataProviders.class)
	 public void testLogin(String uname, String pswd) throws Exception {
	Log.startTestCase("Valid Login");	
	
	objLogin.login(uname, pswd); 
	Log.info("Validate Login"); 
	objLogin.verify_PageTitle();
	 
	 }
	 
	 @Test(priority=0, enabled=false, dataProvider = "invalidLoginData", dataProviderClass = loginData.class)
	 public void invalidLogin(String username, String password) {
			
		 	objLogin.invalidlogin(username, password);
		 	objLogin.verify_invalidLoginError();
		 
	 }
	 
		/*
		 * @Test(priority=0, dataProvider = "emptyUsernameOrPasswordData",
		 * dataProviderClass = loginData.class) public void emptyCredentials(String
		 * username, String password) {
		 * 
		 * objLogin.invalidlogin(username, password);
		 * 
		 * 
		 * }
		 */
	
	
	/*
	 * @Test(priority=1) public void testLogin1() throws Exception { throw new
	 * SkipException("Skipping this exception");
	 * 
	 * }
	 * 
	 * @Test(priority=2) public void sampleCase() throws Exception { extentTest =
	 * extent.createTest("sample case"); Assert.assertTrue(false); }
	 */

	@AfterMethod
	public void tearDown() throws Exception {

		driver.quit();
	}

}
