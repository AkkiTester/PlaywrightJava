package tests;
import org.testng.Assert;

import org.testng.annotations.Test;

import base.BaseTest;

public class LoginTestInvalidID  extends BaseTest{

	
	
	
	@Test
	public void loginTestInValidIDPass() {
		LoginPage.setUsername("invalid");
		LoginPage.setPassword(prop.getProperty("password").trim());
		LoginPage.ClickLoginButton();
		String pageurl = LoginPage.getLoginPageUrl();
		Assert.assertTrue(pageurl.contains("login"), "URL does not contain 'dashboard'");

		
	}
	
	
}

