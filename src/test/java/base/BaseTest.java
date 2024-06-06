package base;

import java.util.Properties;

import org.junit.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import Factory.PlaywrightFactory;
import POM.LoginPage;
import POM.AdministratorLogin;
import POM.MaintanancePage;
import reader.Configreader;
import reader.ExcelFileReader;
import POM.Dashboard;


public class BaseTest extends PlaywrightFactory{
		
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

}
