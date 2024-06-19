package tests;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import base.BaseTest;

public class AdminSystemUserSearch extends BaseTest {

	@Test
	public void AdminSystemUserSearch() {
		createTest("Admin System User Search");
		log(Status.INFO, "Entering Username ");
		LoginPage.setUsername();
		log(Status.INFO, "Entering Password");
		LoginPage.setPassword();
		log(Status.INFO, "Click on Login Button");
		LoginPage.ClickLoginButton();
	}
}
