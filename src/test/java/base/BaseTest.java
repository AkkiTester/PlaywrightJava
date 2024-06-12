package base;

import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.microsoft.playwright.Page;

import Factory.PlaywrightFactory;
import POM.LoginPage;
import POM.AdministratorLogin;
import POM.MaintanancePage;
import reader.Configreader;
import reader.ExcelFileReader;
import POM.Dashboard;


public class BaseTest extends PlaywrightFactory{
	protected static ExtentReports extent;
//	private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    protected static ExtentTest test;
    static Page page;
	
	
	@BeforeSuite
	public void setupSuite() {
		String timestampString = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        ExtentSparkReporter reporter = new ExtentSparkReporter(".\\Report\\OrangeHRM_extent-report"+timestampString+".html");
        extent = new ExtentReports();
        
        reporter.config().setReportName("Application:Orange HRM Demo");
        reporter.config().setDocumentTitle("Orange HRM Demo");
//        reporter.config().setTheme(Theme.STANDARD);
        
        extent.attachReporter(reporter);
        extent.setSystemInfo("OS", "Windows");
        extent.setSystemInfo("Tester", "Akash Dilwale");
        extent.setSystemInfo("Web Application", "Orange HRM");
    }
	
	@AfterSuite
    public void tearDownSuite() {
        extent.flush();
    }
	
	// Static method to create a new test case in Extent Reports
    public static ExtentTest createTest(String testName) {
        test = extent.createTest(testName);
        return test;
    }

    // Static method to log messages to Extent Reports
    public static void log(Status status, String message) {
        if (test != null) {
            test.log(status, message);
        }
    }
    
 // Static method to log messages to Extent Reports with specified color
    public static void log(Status status, String message, ExtentColor color) {
        if (test != null) {
            test.log(status, MarkupHelper.createLabel(message, color));
        }
    }
		
	@BeforeTest
	@Parameters("browser")
	public void setup(@Optional ("chrome")  String  browser) {
		prop = new Configreader();
		page = initBrowser(browser);
		LoginPage = new LoginPage(page);
		AdministratorLogin = new AdministratorLogin(page);
		MaintanancePage = new MaintanancePage(page);
		Dashboard = new Dashboard(page);
		excel = new ExcelFileReader();
		
	}
	
	@AfterTest
	public void tearDown() {
		page.context().browser().close();
	}
	
	
	@AfterMethod
    public void captureScreenshotOnFailure(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
        	String testName = result.getName();
        	
        	String path =captureSS(testName) ;
    		test.addScreenCaptureFromPath(path);
        }
    }
	
	public String captureSS(String fileName) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String timestampString = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		String path = System.getProperty("user.dir");
		System.out.println(timestampString);
		String sspath = path+"//Screenshot//"+fileName+timestampString+".png";
		page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(sspath)).setFullPage(true));
		return sspath;
	}


}
