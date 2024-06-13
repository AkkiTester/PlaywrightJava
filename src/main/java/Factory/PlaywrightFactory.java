package Factory;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Properties;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Tracing;

import POM.AdministratorLogin;
import POM.Dashboard;
import POM.LoginPage;
import POM.MaintanancePage;
import reader.Configreader;
import reader.ExcelFileReader;

//Initial Playwright
public class PlaywrightFactory {
	
	Playwright playwright;
	Browser brows;
	BrowserContext browserContext;
	protected Page page;
//	protected Properties prop;
	
	protected LoginPage LoginPage;
	protected AdministratorLogin AdministratorLogin;
	protected MaintanancePage MaintanancePage;
	protected Dashboard Dashboard;
	public Configreader prop;
	public ExcelFileReader excel;
	
	public Page initBrowser(String browser) {
		
		
		
		String browserName = browser.toLowerCase();
		System.out.println("browser name is :"+ browserName);
		
		playwright = Playwright.create(null);
		
		switch (browserName.toLowerCase()) {
		case "chromium":
			brows = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
			break;
		case "firefox":
			brows = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
			break;
		case "chrome":
			brows = playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false));
			break;
		
		default:
			System.out.println("Please pass the right browse name.....");
		}
		Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
		Double width = screensize.getWidth();
		Double height = screensize.getHeight();	
		
		// Convert Double to int
        int widthInt = width.intValue();
        int heightInt = height.intValue();
		
		browserContext = brows.newContext(new Browser.NewContextOptions()
				.setViewportSize(widthInt, heightInt)
				.setRecordVideoDir(Paths.get("TestVideos/"))
				.setRecordVideoSize(1280, 720));
		// Start tracing before performing actions
		browserContext.tracing().start(new Tracing.StartOptions()
                .setScreenshots(true) // Enable screenshots
                .setSnapshots(true)   // Enable snapshots
                .setSources(true));   // Include sources

		// Stop tracing and export it to a file in the specified folder
		browserContext.tracing().stop(new Tracing.StopOptions()
                .setPath(Paths.get("trace_files/trace.zip"))); // Specify the folder and file name
		
		browserContext.setDefaultTimeout(40000);
		
		page = browserContext.newPage();
		page.navigate(prop.getProperty("loginpageurl"));
		
		return page;
		
		
	}
	/***
	 * this method is used to initialize the properties from config file
	 */
//	public Properties init_prop() {
//		
//		try {
//			FileInputStream ip = new FileInputStream(".\\src\\test\\resources\\config\\config.properties");
//			
//			prop.load(ip);
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return prop;
//	}
	
	

}
