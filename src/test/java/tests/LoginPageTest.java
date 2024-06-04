package tests;
import org.testng.Assert;

import org.testng.annotations.Test;

import base.BaseTest;

public class LoginPageTest  extends BaseTest{

	
	@Test
	public void loginTestValidIDPass() {
		LoginPage.setUsername(prop.getProperty("username").trim());
		LoginPage.setPassword(prop.getProperty("password").trim());
		LoginPage.ClickLoginButton();
		String pageurl = LoginPage.getLoginPageUrl();
		Assert.assertTrue(pageurl.contains("dashboard"), "URL does not contain 'dashboard'");

		
	}
	
	
	
}
