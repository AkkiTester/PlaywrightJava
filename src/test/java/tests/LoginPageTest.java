package tests;
import org.testng.Assert;
import org.testng.annotations.Test;


import base.BaseTest;

public class LoginPageTest  extends BaseTest{

	
	
	@Test
	public void loginPageTitleTest() {
		LoginPage.setUsername("Admin");
		LoginPage.setPassword("admin");
		LoginPage.ClickLoginButton();
		String actuleTitle = LoginPage.getLoginPageTitle();
		Assert.assertEquals(actuleTitle,"OrangeHRM");
		
	}
	
	
}
