package base;

import java.util.Properties;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.microsoft.playwright.Page;

import Factory.PlaywrightFactory;
import POM.LoginPage;

public class BaseTest {
	PlaywrightFactory pf;
	Page page;
	protected Properties prop;
	
	protected LoginPage LoginPage;
	
	@BeforeTest
	@Parameters("browser")
	public void setup(@Optional ("chrome")  String  browser) {
		pf = new PlaywrightFactory();
		prop = pf.init_prop();
		page = pf.initBrowser(browser);
		LoginPage = new LoginPage(page);
	}
	
	@AfterTest
	public void tearDown() {
		page.context().browser().close();
	}

}
