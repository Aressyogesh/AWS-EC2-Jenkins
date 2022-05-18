package TestData;

import org.testng.annotations.DataProvider;

/**
 * @CreateBy  Yogesh Lolage 
 */
public class loginData {
	
	// Test Data
		public String textusername = "Admin";
		public String textpassword = "super";
		public String PageTitle="Dashboard" ;
		public String invalidLoginError= "Invalid username or password.";
		public String blankUsername = "The User name field is required.";
		public String blankPassword = "The Password field is required.";
			
		@DataProvider
		public Object[][] invalidLoginData() {
			Object[][] credentials = { { "admin", "fsfsfffsdf"}, // valid username and invalid password
					{ "asdasd", "super" }};// invalid username and valid password
			return credentials;
		}
		
		@DataProvider
		public Object[][] emptyUsernameOrPasswordData() {
			Object[][] credentials = { { "", "super" }, // empty username
					{ "admin", "" }};// empty password
			return credentials;
		}
}
