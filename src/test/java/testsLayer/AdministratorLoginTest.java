package testsLayer;

import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import baseTest.BaseTestWeb;
import static org.testng.Assert.*;

public class AdministratorLoginTest extends BaseTestWeb{
	
	@Test
	public void loginTestValidIDPass() {
		try {
		createTest("Administrator Login Test"+" - "+getBrowserName());
		log(Status.INFO, "Entering Username ");
		getLoginPage().setUsername();
		log(Status.INFO, "Entering Password");
		getLoginPage().setPassword();
		log(Status.INFO, "Click on Login Button");
		getLoginPage().clickLoginButton();
		log(Status.INFO, "Click on Maintenance Side Menu");
		getDashboard().ClickMaintenanceSideMenu();
		log(Status.INFO, "Entering Administrator Password");
		getAdministratorLogin().Enterpassword();
		log(Status.INFO, "Click on Confirm Button");
		getAdministratorLogin().ClickConfirmButton();}
		catch (Exception e) {
			log(Status.FAIL, "Administrator Login Test Fail",ExtentColor.RED);
			assertFalse(false);
		}
		if ("Purge Employee Records".equals(getMaintanancePage().CheckPurgeHeader())){
			assertTrue(true);
			log(Status.PASS, "Administrator Login Test Pass",ExtentColor.GREEN);
		}
		else {
			log(Status.FAIL, "Administrator Login Test Fail",ExtentColor.RED);
			assertFalse(false);
		}
	}
}
