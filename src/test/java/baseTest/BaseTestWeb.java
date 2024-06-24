package baseTest;

import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.testng.ITestResult;
import org.testng.annotations.*;
import com.aventstack.extentreports.*;
import com.aventstack.extentreports.markuputils.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.microsoft.playwright.*;
import base.MainClass;
import pageLayer.*;
import reader.Configreader;
import reader.ExcelFileReader;

public class BaseTestWeb extends MainClass {
    protected static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    private static ThreadLocal<LoginPage> loginPage = new ThreadLocal<>();
    private static ThreadLocal<AdministratorLogin> administratorLogin = new ThreadLocal<>();
    private static ThreadLocal<MaintanancePage> maintanancePage = new ThreadLocal<>();
    private static ThreadLocal<Dashboard> dashboard = new ThreadLocal<>();
    private static ThreadLocal<AdminPage> adminPage = new ThreadLocal<>();
    private static ThreadLocal<PIMPage> pimPage = new ThreadLocal<>();
    
    private String bName;
    
    public String getBrowserName() {
        return bName;
    }
    
    @Parameters("browser")
    @BeforeSuite
    public void setupSuite(@Optional("chrome") String browser) {
        String timestampString = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        ExtentSparkReporter reporter = new ExtentSparkReporter(".\\Report\\OrangeHRM_extent-report" + timestampString + ".html");
        extent = new ExtentReports();
        reporter.config().setReportName("RemoSys Tech,Pune - Application - OrangeHRM Test Report");
        reporter.config().setDocumentTitle("Orange HRM Demo");
        
        extent.attachReporter(reporter);
        extent.setSystemInfo("OS", "Windows");
        extent.setSystemInfo("Testing Team", "RemoSys Team");
        extent.setSystemInfo("Web Application", "Orange HRM");
    }
    
    @AfterSuite
    public void tearDownSuite() {
        extent.flush();
    }

    public ExtentTest createTest(String testName) {
        ExtentTest extentTest = extent.createTest(testName);
        test.set(extentTest);
        return extentTest;
    }

    public static void log(Status status, String message) {
        ExtentTest extentTest = test.get();
        if (extentTest != null) {
            extentTest.log(status, message);
        }
    }
    
    public static void log(Status status, String message, ExtentColor color) {
        ExtentTest extentTest = test.get();
        if (extentTest != null) {
            extentTest.log(status, MarkupHelper.createLabel(message, color));
        }
    }
        
    @BeforeTest
    @Parameters("browser")
    public void setup(@Optional("chrome") String browser) {
        bName = browser;
        prop = new Configreader();
        setPage(initBrowser(browser));
        loginPage.set(new LoginPage(getPage()));
        administratorLogin.set(new AdministratorLogin(getPage()));
        maintanancePage.set(new MaintanancePage(getPage()));
        dashboard.set(new Dashboard(getPage()));
        adminPage.set(new AdminPage(getPage()));
        pimPage.set(new PIMPage(getPage()));
        excel = new ExcelFileReader();

        getBrowserContext().tracing().start(new Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(true)
                .setSources(true));
    }
    
    @AfterTest
    public void tearDown() {
        getBrowserContext().tracing().stop(new Tracing.StopOptions()
                .setPath(Paths.get("trace_files/trace.zip")));
        closeBrowser();
    }
    
    @AfterMethod
    public void captureScreenshotOnFailure(ITestResult result) {
    	String testName = result.getName();
        String path = captureSS(testName);
        if (result.getStatus() == ITestResult.FAILURE) {
            test.get().addScreenCaptureFromPath(path);
        }else if (result.getStatus() == ITestResult.SUCCESS) {
            test.get().addScreenCaptureFromPath(path);
        }
    }
    
    public String captureSS(String fileName) {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String timestampString = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        String path = System.getProperty("user.dir");
        String sspath = path + "//Screenshot//" + fileName + timestampString + ".png";
        getPage().screenshot(new Page.ScreenshotOptions().setPath(Paths.get(sspath)).setFullPage(true));
        return sspath;
    }

    protected LoginPage getLoginPage() {
        return loginPage.get();
    }

    protected AdministratorLogin getAdministratorLogin() {
        return administratorLogin.get();
    }

    protected MaintanancePage getMaintanancePage() {
        return maintanancePage.get();
    }

    protected Dashboard getDashboard() {
        return dashboard.get();
    }

    protected AdminPage getAdminPage() {
        return adminPage.get();
    }

    protected PIMPage getPIMPage() {
        return pimPage.get();
    }
}
