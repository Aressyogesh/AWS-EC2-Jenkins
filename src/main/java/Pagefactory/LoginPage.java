package Pagefactory;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.DataProvider;

import Core.StepBase;
import Core.WrapperFunctions;
import TestData.loginData;

/**
 * @CreateBy Yogesh Lolage
 */

public class LoginPage extends StepBase {
	WrapperFunctions objwrapper = new WrapperFunctions();
	loginData objlogindata = new loginData();

	public static By txtusername = By.xpath("/html//input[@id='UserName']");
	public static By txtpassword = By.xpath("/html//input[@id='Password']");
	public static By loginbtn = By.xpath("//section[@id='loginForm']/form[@role='form']//input[@value='Log in']");
	public static By errinvalidlogin = By.cssSelector("li");
	public static By errorBlankUsername = By.xpath("/html//span[@id='UserName-error']");
	public static By errorBlankPassword = By.xpath("/html//span[@id='Password-error']");
			
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}

	public void login(String uname, String pswd) {
		objwrapper.setText(txtusername, uname);
		objwrapper.ThreadSleep(3000);
		objwrapper.setText(txtpassword, pswd);
		objwrapper.click(loginbtn);
		objwrapper.ThreadSleep(3000);
	}

	public void invalidlogin(String username, String password) {
		objwrapper.setText(txtusername, username);
		objwrapper.ThreadSleep(3000);
		objwrapper.setText(txtpassword, password);
		objwrapper.ThreadSleep(3000);
		objwrapper.click(loginbtn);
		objwrapper.ThreadSleep(3000);
		
	}
	
	public void verify_PageTitle() {
		// System.out.println(objwrapper.getPageTitle());
		String pageTitle = objwrapper.getPageTitle();
		assertEquals(objlogindata.PageTitle, pageTitle);
	}
	
	public void verify_BlankUsername() {
		String errormessage = objwrapper.getText(errorBlankUsername);
		//System.out.println(objwrapper.getPageTitle());
		assertEquals(objlogindata.blankUsername, errormessage);
	}
	
	public void verify_BlankPassword() {
		String errormessage = objwrapper.getText(errorBlankPassword);
		//System.out.println(objwrapper.getPageTitle());
		assertEquals(objlogindata.blankPassword, errormessage);
	}

	public void verify_invalidLoginError() {
		String errormessage = objwrapper.getText(errinvalidlogin);
		// System.out.println(errormessage);
		assertEquals(objlogindata.invalidLoginError, errormessage);
	}

}