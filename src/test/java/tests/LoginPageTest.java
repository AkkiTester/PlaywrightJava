package tests;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.microsoft.playwright.Page;

import Factory.PlaywrightFactory;
import POM.LoginPage;

public class LoginPageTest {

	PlaywrightFactory pf;
	Page page;
	
	LoginPage LoginPage;
	
	@BeforeTest
	public void setup() {
		pf = new PlaywrightFactory();
		page = pf.initBrowser("chromium");
		LoginPage = new LoginPage(page);
	}
	
	@Test
	public void loginPageTitleTest() {
		String actuleTitle = LoginPage.getLoginPageTitle();
		Assert.assertEquals(actuleTitle,"OrangeHRM");
		
	}
	
	@AfterTest
	public void tearDown() {
		page.context().browser().close();
	}
}
