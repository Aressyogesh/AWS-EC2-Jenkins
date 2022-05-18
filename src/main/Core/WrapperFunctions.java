package Core;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import PageFactory.AdminOrangeHrm;
public class WrapperFunctions 
{
	@Test
	public void dropdown()
	{
		   By userrole=By.xpath(" //*[@id='searchSystemUser_userType']");
		  Select userrole3=new Select(driver.findElement(userrole)); 
		  userrole3.selectByIndex(1);
		  Assert.assertNotSame("ESS", userrole3.getOptions());
		  
   }
	@Test
	public void dropdown1()
	{
		 By status=By.xpath("//*[@id='searchSystemUser_status']");
		 Select  status2=new Select(driver.findElement(status)); 
	    status2.selectByIndex(2);
	}
}
