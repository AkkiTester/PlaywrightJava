package baseTest;

import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
import com.microsoft.playwright.Tracing;
import com.microsoft.playwright.options.LoadState;

import base.MainClass;
import pageLayer.AdminPage;
import pageLayer.AdministratorLogin;
import pageLayer.Dashboard;
import pageLayer.LoginPage;
import pageLayer.MaintanancePage;
import pageLayer.PIMPage;
import reader.Configreader;
import reader.ExcelFileReader;


public class BaseTestWeb extends MainClass{
	protected static ExtentReports extent;
	private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
//	private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
//    protected static ExtentTest test;
    static Page page;
    String bName;
    
    
    public String getBrowserName() {
    	return bName;
    }
	
    @Parameters("browser")
	@BeforeSuite
	public void setupSuite(@Optional ("chrome")  String  browser) {
		String timestampString = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        ExtentSparkReporter reporter = new ExtentSparkReporter(".\\Report\\OrangeHRM_extent-report"+timestampString+".html");
        extent = new ExtentReports();
        
        reporter.config().setReportName("Application:Orange HRM Demo");
        reporter.config().setDocumentTitle("Orange HRM Demo");
//        reporter.config().setTheme(Theme.STANDARD);
        
        extent.attachReporter(reporter);
        extent.setSystemInfo("OS", "Windows");
        extent.setSystemInfo("Testing Team", "RemoSys Team");
        extent.setSystemInfo("Web Application", "Orange HRM");
        extent.setSystemInfo("Browser", browser);
    }
	
	@AfterSuite
    public void tearDownSuite() {
        extent.flush();
    }


	
	
	// Static method to create a new test case in Extent Reports

    public ExtentTest createTest(String testName) {
    	ExtentTest extentTest = extent.createTest(testName);
    	test.set(extentTest);
    	return extentTest;
//        test = extent.createTest(testName);
//        return test;
    }

    // Static method to log messages to Extent Reports
    public static void log(Status status, String message) {
    	ExtentTest extentTest = test.get();
        if (extentTest != null) {
            extentTest.log(status, message);
        }
//    	if (test != null) {
//            test.log(status, message);
//        }
    }
    
 // Static method to log messages to Extent Reports with specified color
    public static void log(Status status, String message, ExtentColor color) {
    	ExtentTest extentTest = test.get();
        if (extentTest != null) {
            extentTest.log(status, MarkupHelper.createLabel(message, color));
        }
//    	if (test != null) {
//            test.log(status, MarkupHelper.createLabel(message, color));
//        }
    }
		
	@BeforeTest
	@Parameters("browser")
	public void setup(@Optional ("chrome")  String  browser) {
		bName=browser;
		prop = new Configreader();
		page = initBrowser(browser);
		LoginPage = new LoginPage(page);
		AdministratorLogin = new AdministratorLogin(page);
		MaintanancePage = new MaintanancePage(page);
		Dashboard = new Dashboard(page);
		AdminPage = new AdminPage(page);
		PIMPage = new PIMPage(page);
		excel = new ExcelFileReader();
		// Start tracing before performing actions
		browserContext.tracing().start(new Tracing.StartOptions()
                .setScreenshots(true) // Enable screenshots
                .setSnapshots(true)   // Enable snapshots
                .setSources(true));   // Include sources
	}
	
	@AfterTest
	public void tearDown() {
		// Stop tracing and export it to a file in the specified folder
				browserContext.tracing().stop(new Tracing.StopOptions()
		                .setPath(Paths.get("trace_files/trace.zip"))); // Specify the folder and file name
		page.context().browser().close();
	}
	
	
	
	@AfterMethod
    public void captureScreenshotOnFailure(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
            String testName = result.getName();
            String path = captureSS(testName);
            test.get().addScreenCaptureFromPath(path);
//            try {
//                test.get().addScreenCaptureFromPath(path);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
        }
		
//        if (result.getStatus() == ITestResult.FAILURE) {
//        	String testName = result.getName();
//        	
//        	String path =captureSS(testName) ;
//    		test.addScreenCaptureFromPath(path);
//        }
    }
	
	public String captureSS(String fileName) {
		try {
			page.waitForLoadState(LoadState.DOMCONTENTLOADED);
			Thread.sleep(2500);
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