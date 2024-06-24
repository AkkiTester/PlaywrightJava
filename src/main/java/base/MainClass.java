package base;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.nio.file.Paths;
import java.util.Arrays;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import reader.Configreader;
import reader.ExcelFileReader;
import Utility.GenericFunction;
import pageLayer.AdminPage;
import pageLayer.AdministratorLogin;
import pageLayer.Dashboard;
import pageLayer.LoginPage;
import pageLayer.MaintanancePage;
import pageLayer.PIMPage;

//Initial Playwright
public class MainClass {
	
	Playwright playwright;
	protected Browser brows;
	protected BrowserContext browserContext;
	protected Page page;
	protected GenericFunction GenericFunction;
	protected LoginPage LoginPage;
	protected AdministratorLogin AdministratorLogin;
	protected MaintanancePage MaintanancePage;
	protected Dashboard Dashboard;
	protected AdminPage AdminPage;
	protected PIMPage PIMPage;
	public Configreader prop;
	public ExcelFileReader excel;
	
	public Page initBrowser(String browser) {

		String browserName = browser.toLowerCase();
		System.out.println("browser name is :"+ browserName);
		
		playwright = Playwright.create();
		
		switch (browserName.toLowerCase()) {
		case "chromium":
			brows = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
			break;
		case "firefox":
			brows = playwright.firefox().launch(new BrowserType.LaunchOptions().setChannel("firefox").setHeadless(false));
			break;
		case "chrome":
			brows = playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false));
			break;
		case "edge":
	        brows = playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("msedge").setHeadless(false).setArgs(Arrays.asList("--start-maximized")));
	        break;
		
		default:
			System.out.println("Please pass the right browse name.....");
		}
		
		browserContext = brows.newContext(new Browser.NewContextOptions()
				.setRecordVideoDir(Paths.get("TestVideos/"))
				.setRecordVideoSize(1280, 720));
		
				
		browserContext.setDefaultTimeout(80000);

		
		page = browserContext.newPage();
		page.navigate(prop.getProperty("loginpageurl"));
		
		return page;
				
	}

}