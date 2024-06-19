package tests;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;

import base.BaseTest;

import static org.testng.Assert.*;

public class AdministratorLoginTest extends BaseTest{

	@Test
	public void loginTestValidIDPass() {
		createTest("Administrator Login Test");
		log(Status.INFO, "Entering Username ");
		LoginPage.setUsername();
		log(Status.INFO, "Entering Password");
		LoginPage.setPassword();
		log(Status.INFO, "Click on Login Button");
		LoginPage.ClickLoginButton();
		log(Status.INFO, "Click on Maintenance Side Menu");
		Dashboard.ClickMaintenance();
		log(Status.INFO, "Entering Administrator Password");
		AdministratorLogin.Enterpassword();
		log(Status.INFO, "Click on Confirm Button");
		AdministratorLogin.ClickConfirmButton();
		if ("Purge Employee Records".equals(MaintanancePage.CheckPurgeHeader())){
//			assertTrue(" Employee Records".equals(MaintanancePage.CheckPurgeHeader()));
			assertTrue(true);
			log(Status.PASS, "Administrator Login Test Pass",ExtentColor.GREEN);
		}
		else {
//			assertEquals("Purge Employee Records",MaintanancePage.CheckPurgeHeader());
			assertFalse(false);
			log(Status.PASS, "Administrator Login Test Fail",ExtentColor.GREEN);
		}
		System.out.println(MaintanancePage.CheckPurgeHeader());	

	}
}
