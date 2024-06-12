package tests;
import org.testng.Assert;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import base.BaseTest;

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
		
		System.out.println(MaintanancePage.CheckPurgeHeader());	

		
	}
}
