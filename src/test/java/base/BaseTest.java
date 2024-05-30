package base;

import java.util.Properties;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.microsoft.playwright.Page;

import Factory.PlaywrightFactory;
import POM.LoginPage;

public class BaseTest {
	PlaywrightFactory pf;
	Page page;
	Properties prop;
	
	protected LoginPage LoginPage;
	
	@BeforeTest
	public void setup() {
		pf = new PlaywrightFactory();
		prop = pf.init_prop();
		page = pf.initBrowser(prop);
		LoginPage = new LoginPage(page);
	}
	
	@AfterTest
	public void tearDown() {
		page.context().browser().close();
	}

}
