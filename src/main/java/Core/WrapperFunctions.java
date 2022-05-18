package Core;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class WrapperFunctions extends StepBase {
	
	private StepBase objStepBase = new StepBase();
	
	public String getText(By locator) {
		waitForElementPresence(locator, 10);
		WebElement webElement = objStepBase.getDriver().findElement(locator);
		return webElement.getText();
	}
	public  Dimension checkEmptyElement(By locator) {
		waitForElementPresence(locator, 10);
		WebElement webElement = objStepBase.getDriver().findElement(locator);
		return webElement.getSize();
	}
	
	
	public void assertCheck(String expectedText, By locator) {
		waitForElementPresence(locator, 10);
		WebElement originalTitle = driver.findElement(locator);
		Assert.assertEquals(originalTitle.getText(), expectedText, "Assert Fail");
	}

	public void assertCheckWithMassage(By locator, String expectedText, String errorMassage) {
		waitForElementPresence(locator, 10);
		WebElement originalTitle = driver.findElement(locator);
		Assert.assertEquals(originalTitle.getText(), expectedText, errorMassage);
	}
	
	/**
	 * @CreateBy  Yogesh Lolage 
	 */

	public static void setClipboardData(String string) {
		//StringSelection is a class that can be used for copy and paste operations.
		   StringSelection stringSelection = new StringSelection(string);
		   Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
		}
	
	/**
	 * @CreateBy  Yogesh Lolage 
	 */

	public void ThreadSleep(int millisecond)
	{
		try 
		{
			Thread.sleep(millisecond);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
	}
	
	public static void uploadFile(String fileLocation) {
        try {
        	//Setting clipboard with file location
            setClipboardData(fileLocation);
            //native key strokes for CTRL, V and ENTER keys
            Robot robot = new Robot();
	
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
        } catch (Exception exp) {
        	exp.printStackTrace();
        }
    }
	
	public void waitForElementPresence(By locator, int waitInSeconds) 
	{
		try 
		{
			WebDriverWait wait = (WebDriverWait) new WebDriverWait(objStepBase.getDriver(), waitInSeconds).ignoring(StaleElementReferenceException.class);
			wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		} 
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
	}
	
	
	public String  getPageTitle() 
	{
		 return objStepBase.getDriver().getTitle();
		
	}
	
	/**
	 * @CreateBy  Yogesh Lolage 
	 */
	public void selectListValue(By locator, String value, String list) throws InterruptedException 
	{
		setText(locator, value);
		Thread.sleep(3000); 
		java.util.List<WebElement> listofelement= driver.findElements(By.xpath(list));
		for (WebElement webElement : listofelement) {
			if (webElement.getText().trim().contains(value)){
				//System.out.println(webElement.getText());
				webElement.click();
			break;
			}
		}
	} 
	public void click(WebElement locator) {
		// waitForElementPresence(locator, 10);
		// WebElement webElement = objStepBase.getDriver().findElement(locator);
		locator.click();

	}
	
	
	
	public boolean click(By locator) 
	{
		waitForElementPresence(locator, 10);
		WebElement webElement = objStepBase.getDriver().findElement(locator);
		try
		{
			JavascriptExecutor executor = (JavascriptExecutor)objStepBase.getDriver();
			executor.executeScript("arguments[0].click();", webElement);
			return true;
		} 
		catch (Exception exception)
		{
			exception.printStackTrace();
			return false;
		}
	}
	
	public boolean doubleClick(By locator)
	{
		waitForElementPresence(locator, 10);
		WebElement webElement = objStepBase.getDriver().findElement(locator);
		try
		{
			Actions actionBuilder = new Actions(objStepBase.getDriver());
			actionBuilder.doubleClick(webElement).build().perform();
			return true;
		} 
		catch (Exception exception)
		{
			exception.printStackTrace();
			return false;
		}
	}
	
	// function to generate a random string of length n 
    public static String getAlphaNumericString(int n) 
    { 
  
        // chose a Character random from this String 
        String alphanumeric = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                                    + "0123456789"
                                    + "abcdefghijklmnopqrstuvxyz"; 
  
        // create StringBuffer size of AlphaNumericString 
        StringBuilder sb = new StringBuilder(n); 
  
        for (int i = 0; i < n; i++) { 
  
            // generate a random number between 
            // 0 to AlphaNumericString variable length 
            int index 
                = (int)(alphanumeric.length() 
                        * Math.random()); 
  
            // add Character one by one in end of sb 
            sb.append(alphanumeric 
                          .charAt(index)); 
        } 
  
        return sb.toString(); 
    } 
	
	public boolean setText(By locator, String fieldValue) 
	{
		waitForElementPresence(locator, 10);
		WebElement webElement = objStepBase.getDriver().findElement(locator);
		try
		{
			// replace the text
			JavascriptExecutor executor = (JavascriptExecutor)objStepBase.getDriver();
			executor.executeScript("arguments[0].click();", webElement);
			webElement.sendKeys(Keys.chord(Keys.CONTROL, "a"));
			webElement.sendKeys(Keys.DELETE);
			webElement.clear();
			webElement.sendKeys(fieldValue);
			//webElement.sendKeys(Keys.TAB);
			return true;
		} 
		catch (Exception exception)
		{
			exception.printStackTrace();
			return false;
		}
	}

	public boolean clearText(By locator) 
	{
		waitForElementPresence(locator, 10);
		WebElement webElement = objStepBase.getDriver().findElement(locator);
		try
		{
			// replace the text
			JavascriptExecutor executor = (JavascriptExecutor)objStepBase.getDriver();
			executor.executeScript("arguments[0].click();", webElement);
			webElement.sendKeys(Keys.chord(Keys.CONTROL, "a"));
			webElement.sendKeys(Keys.DELETE);
			webElement.clear();
			return true;
		} 
		catch (Exception exception)
		{
			exception.printStackTrace();
			return false;
		}
	}
	
	public String getText(By locator, String textBy) 
	{
		waitForElementPresence(locator, 10);
		WebElement webElement = objStepBase.getDriver().findElement(locator);
		try
		{
			String strText = "";
			if(textBy.equals("value"))
				strText = webElement.getAttribute("value");
			else if(textBy.equalsIgnoreCase("text"))
				strText = webElement.getText();
			return strText; 
		} 
		catch (Exception exception)
		{
			exception.printStackTrace();
			return null;
		}
	}
		
	public boolean selectCheckBox(By locator, boolean status) 
	{
		waitForElementPresence(locator, 10);
		WebElement webElement = objStepBase.getDriver().findElement(locator);
		try
		{
			if(webElement.getAttribute("type").equals("checkbox"))   
			{
				if((webElement.isSelected() && !status) || (!webElement.isSelected() && status))
					webElement.click();
				return true;
			}
			else
				return false;
		} 
		catch (Exception exception)
		{
			exception.printStackTrace();
			return false;
		}
	}
	
	public boolean isCheckBoxSelected(By locator, boolean status) 
	{
		waitForElementPresence(locator, 10);
		WebElement webElement = objStepBase.getDriver().findElement(locator);
		boolean state = false;
		try
		{
			if(webElement.getAttribute("type").equals("checkbox"))   
				state = webElement.isSelected();

			return state;
		} 
		catch (Exception exception)
		{
			exception.printStackTrace();
			return false;
		}
	}

	
	public boolean selectRadioButton(By locator, boolean status)
	{
		waitForElementPresence(locator, 10);
		WebElement webElement = objStepBase.getDriver().findElement(locator);
		try
		{
			if(webElement.getAttribute("type").equals("radio"))   
			{
				if((webElement.isSelected() && !status) || (!webElement.isSelected() && status))
					webElement.click();
				return true;
			}
			else
				return false;
		} 
		catch (Exception exception)
		{
			exception.printStackTrace();
			return false;
		}
	}

	
	
	public boolean mouseHover(By locator)
	{
		waitForElementPresence(locator, 10);
		WebElement webElement = objStepBase.getDriver().findElement(locator);
		try
		{
			Actions actionBuilder = new Actions(objStepBase.getDriver());
			actionBuilder.moveToElement(webElement).build().perform();
			return true;
		} 
		catch (Exception exception)
		{
			exception.printStackTrace();
			return false;
		}
	}
	
	
	public boolean switchToWindowUsingTitle(String windowTitle)
	{
		try
		{
			String mainWindowHandle = objStepBase.getDriver().getWindowHandle();
			Set<String> openWindows = objStepBase.getDriver().getWindowHandles();

			if (!openWindows.isEmpty()) 
			{
				for (String windows : openWindows) 
				{
					String window = objStepBase.getDriver().switchTo().window(windows).getTitle();
					if (windowTitle.equals(window)) 
						return true;
					else 
						objStepBase.getDriver().switchTo().window(mainWindowHandle);
				}
			}
			return false;
		} 
		catch (Exception exception)
		{
			exception.printStackTrace();
			return false;
		}
	}

	
	public boolean selectDropDownOption(By locator, String option, String... selectType) 
	{
		try
		{
			waitForElementPresence(locator, 10);
			WebElement webElement = objStepBase.getDriver().findElement(locator);
			Select sltDropDown = new Select(webElement);
			
			if(selectType.length > 0 && !selectType[0].equals(""))
			{
				if(selectType[0].equals("Value"))
					sltDropDown.selectByValue(option);
				else if(selectType[0].equals("Text"))
					sltDropDown.selectByVisibleText(option);
				else if(selectType[0].equals("Index"))
					sltDropDown.selectByIndex(Integer.parseInt(option));
				

				return true;
			}
			else
			{
				// Web elements from dropdown list 
				List<WebElement> options = sltDropDown.getOptions();
				boolean blnOptionAvailable = false;
				int iIndex = 0;
				for(WebElement weOptions : options)  
				{  
					if (weOptions.getText().trim().equals(option))
					{
						sltDropDown.selectByIndex(iIndex);
						blnOptionAvailable = true;
					}
					else
						iIndex++;
					if(blnOptionAvailable)
						break;
				}
				if(blnOptionAvailable)
					return true;
				else
					return false;
			}
		} 
		catch (Exception exception)
		{
			exception.printStackTrace();
			return false;
		}
	}
	
	
	public String getSelectedValueFormDropDown(By locator) 
	{
		try
		{
			waitForElementPresence(locator, 10);
			Select selectDorpDown = new Select(objStepBase.getDriver().findElement(locator));
			String selectedDorpDownValue = selectDorpDown.getFirstSelectedOption().getText();
			return selectedDorpDownValue;
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
			return null;
		}

	}
	
	
	public boolean selectRadioButtonForSpecificColumn(By locator, String columnContent, int columnNumberForRadio) 
	{
		try
		{
			waitForElementPresence(locator, 10);
			List<WebElement> weResultTable = objStepBase.getDriver().findElements(locator);
			for(WebElement weRow : weResultTable)
			{
				List<WebElement> weColumns = weRow.findElements(By.xpath(".//td"));
				for(WebElement weColumn : weColumns)
				{
					if(weColumn.getText().trim().equals(columnContent))
					{
						WebElement webElement = weRow.findElement(By.xpath(".//td['" + columnNumberForRadio + "']/input[@type='radio']"));
						JavascriptExecutor executor = (JavascriptExecutor)objStepBase.getDriver();
						executor.executeScript("arguments[0].click();", webElement);
					}
				}
			}
			return true;
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
			return false;
		}
	}
	
	
	public boolean selectCheckBoxForSpecificColumn(By locator, String columnContent, int columnNumberForRadio) 
	{
		try
		{
			waitForElementPresence(locator, 10);
			List<WebElement> weResultTable = objStepBase.getDriver().findElements(locator);
			for(WebElement weRow : weResultTable)
			{
				List<WebElement> weColumns = weRow.findElements(By.xpath(".//td"));
				for(WebElement weColumn : weColumns)
				{
					if(weColumn.getText().trim().equals(columnContent))
						weRow.findElement(By.xpath(".//td['" + columnNumberForRadio + "']/span/input[@type='checkbox']")).click();
				}
			}
			return true;
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
			return false;
		}
	}
	
	public boolean navigateURL(String url) {
		System.out.println("*************  In navigate function********");
		objStepBase.getDriver().get(url);
		return false;
	}
	
	public void scrollintoview(By popup, By locator)
	{
		WebDriverWait wait = (WebDriverWait) new WebDriverWait(objStepBase.getDriver(), 5000).ignoring(StaleElementReferenceException.class);
		wait.until(ExpectedConditions.presenceOfElementLocated(popup));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", locator);
	}
	
	public boolean sendKeyBoardKeys(By locator, String key, int waitInSeconds) 
	{
		try
		{
			waitForElementPresence(locator, waitInSeconds);
			WebElement webElement = objStepBase.getDriver().findElement(locator);
			if(key.equalsIgnoreCase("enter"))
				webElement.sendKeys(Keys.ENTER);
			if(key.equalsIgnoreCase("shift"))
				webElement.sendKeys(Keys.SHIFT);
			if(key.equalsIgnoreCase("arrow_up"))
				webElement.sendKeys(Keys.ARROW_UP);
			if(key.equalsIgnoreCase("tab"))
				webElement.sendKeys(Keys.TAB);
			return true;
		} 
		catch (Exception exception)
		{
			exception.printStackTrace();
			return false;
		}
	}
	
	

	
	
}
