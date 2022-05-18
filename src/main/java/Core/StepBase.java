package Core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class StepBase {

	public static ExtentHtmlReporter htmlreport;
	public static ExtentReports extent;
	public static ExtentTest extentTest;
	public static WebDriver driver;
	public static RemoteWebDriver rdriver;
	Properties objConfig = new Properties();

	@BeforeSuite
	public void setupExtentReport() throws FileNotFoundException, IOException {

		htmlreport = new ExtentHtmlReporter("./Reports/Extent/" + "HEMSWEB-Automation" + "-" + getCurrentDateTime() + ".html");
		htmlreport.config().setDocumentTitle("EQ2 Automation");
		htmlreport.config().setReportName("HEMSWEB Automation Report");
		htmlreport.config().setTheme(Theme.DARK);

		extent = new ExtentReports();
		extent.setSystemInfo("Organization", "EQ2");
		objConfig.load(new FileInputStream(System.getProperty("user.dir") + "/src/main/java/Core/Config.properties"));
		extent.setSystemInfo("Browser", objConfig.getProperty("test.browser"));
		extent.attachReporter(htmlreport);

	}

	
	
	 
	
	//@BeforeClass
	
	  public void setup(String browserName) throws Exception {
	 
	if (browserName.equalsIgnoreCase("chrome")) 
	{	  
		//System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")  + "/Resources/chromedriver.exe");
		//System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")  + "/usr/bin/chromedriver");
		System.setProperty("webdriver.chrome.driver", "/var/lib/jenkins/workspace/EQ2-HEMSWEB/usr/bin/chromedriver");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("headless");
		options.addArguments("disable-gpu");
		driver = new ChromeDriver(options);
		objConfig.load(new FileInputStream(System.getProperty("user.dir") + "/src/main/java/Core/Config.properties"));
		driver.get(objConfig.getProperty("test.browser.url"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
	}else if (browserName.equalsIgnoreCase("firefox"))
	{
		System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")  + "/Resources/geckodriver.exe");
		driver = new FirefoxDriver();
		objConfig.load(new FileInputStream(System.getProperty("user.dir") + "/src/main/java/Core/Config.properties"));
		driver.get(objConfig.getProperty("test.browser.url"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();} 
	}
	 	

	public WebDriver getDriver() {
		return driver;
	}

	public static String getReportName() {
		Date d = new Date();
		String fileName = "HEMSWEB-Report" + "_" + d.toString().replace(":", "-").replace("", "-") + ".html";
		return fileName;
	}

	public static String getCurrentDateTime() {
		Date d = Calendar.getInstance().getTime(); // Current time
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss"); // Set your date format
		String currentData = sdf.format(d);
		return currentData;

	}

	public static String getScreenshot(WebDriver driver, String screenshotName) throws Exception {
		String dateName = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
                //after execution, you could see a folder "FailedTestsScreenshots" under src folder
		String destination = System.getProperty("user.dir") + "/FailedTestsScreenshots/"+screenshotName+dateName+".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}

	@BeforeMethod
	public void testMethod(Method method) {
		extentTest = extent.createTest(method.getName());
		extentTest.log(Status.INFO, "Test " + "'"+method.getName()+"'" + " has been started");
	}

	@AfterMethod
	public void tearDownMethod(ITestResult result) throws Exception {
		if (result.getStatus() == ITestResult.FAILURE) {
			extentTest.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + ":-" + "Test Case Failed", ExtentColor.RED));
			extentTest.log(Status.FAIL,	MarkupHelper.createLabel(result.getThrowable() + "Test Case Failed", ExtentColor.RED));
			String Screenshotpath	= StepBase.getScreenshot(driver, result.getName());
			extentTest.addScreenCaptureFromPath(Screenshotpath);
			//extentTest.log(Status.FAIL, (Markup) extentTest.addScreenCaptureFromPath(Screenshotpath));
			
		} else if (result.getStatus() == ITestResult.SKIP) {
			extentTest.log(Status.SKIP,
					MarkupHelper.createLabel(result.getName() + ":-" + "Test Case Skipped", ExtentColor.PINK));
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			extentTest.log(Status.PASS,
					MarkupHelper.createLabel(result.getName() + ":-" + "Test Case Passed", ExtentColor.GREEN));
		}

	}

	@AfterSuite
	public void afterClass() {
		extent.flush();

	}

}
