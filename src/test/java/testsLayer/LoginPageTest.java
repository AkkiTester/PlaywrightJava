package testsLayer;
import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;

import baseTest.BaseTestWeb;

public class LoginPageTest  extends BaseTestWeb{

	@Test
	public void loginTestValidIDPass() {
		createTest("Login Test Case Valid Credntial "+" - "+getBrowserName());
		log(Status.INFO, "Entering Username ");
		LoginPage.setUsername();
		log(Status.INFO, "Entering Password ");
		LoginPage.setPassword();
		LoginPage.ClickLoginButton();
		log(Status.INFO, "Click On Login Button ");
		String pageurl = LoginPage.getLoginPageUrl();
		if (pageurl.contains("dashboard")) {
			log(Status.PASS, "Login successful",ExtentColor.GREEN);
        } else {
            log(Status.FAIL, "Login failed",ExtentColor.RED);
        }
		
		Assert.assertTrue(pageurl.contains("dashboard"), "URL does not contain 'dashboard'");

		
	}
	
	
	
}