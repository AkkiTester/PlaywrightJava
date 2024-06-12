package base;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.Playwright;

public class BaseApiTest {
	protected static ExtentReports extent;
//	private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    protected static ExtentTest test;
	
    

	public Playwright playwright ;
	public APIRequest request ;
	public APIRequestContext requestContext ;
	
	@BeforeSuite
	public void setupSuite() {
        ExtentSparkReporter reporter = new ExtentSparkReporter(".\\Report\\Api-extent-report.html");
        extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("os", "Windows");
        extent.setSystemInfo("Tester", "Akash Dilwale");
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
	public void setup() {
	playwright = Playwright.create();
	request = playwright.request();
	requestContext =  request.newContext();
	}
	
}
