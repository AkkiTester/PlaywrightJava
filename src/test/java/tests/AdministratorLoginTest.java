package tests;
import org.testng.Assert;

import org.testng.annotations.Test;

import base.BaseTest;

public class AdministratorLoginTest extends BaseTest{

	@Test
	public void loginTestValidIDPass() {
		LoginPage.setUsername();
		LoginPage.setPassword();
		LoginPage.ClickLoginButton();
		Dashboard.ClickMaintenance();
		AdministratorLogin.Enterpassword();
		AdministratorLogin.ClickConfirmButton();
		
		System.out.println(MaintanancePage.CheckPurgeHeader());	

		
	}
}
