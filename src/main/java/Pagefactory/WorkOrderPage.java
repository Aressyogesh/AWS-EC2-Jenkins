package Pagefactory;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import Core.StepBase;
import Core.WrapperFunctions;


public class WorkOrderPage extends StepBase {
	
	WrapperFunctions objwrapper = new WrapperFunctions();
	

	public static By linkWorkOrder = By.xpath("//div[@id='bs-example-navbar-collapse-1']/ul[@class='nav navbar-nav']/li[2]/a[@href='/hemsweb/Workord/Index']");
	
	
	public WorkOrderPage() {
		PageFactory.initElements(driver, this);
	}
	
	
	public void addWorkOrder() {
		objwrapper.click(linkWorkOrder);
		objwrapper.ThreadSleep(3000);
	}

}
