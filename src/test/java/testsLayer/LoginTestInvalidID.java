package testsLayer;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;


import baseTest.BaseTestWeb;

public class LoginTestInvalidID  extends BaseTestWeb{
	@Test
	public void loginTestInValidIDPass() {
		try {
		createTest("Login Test Case InValid Credntial"+" - "+getBrowserName());
		log(Status.INFO, "Entering Username ");
		getLoginPage().setUsername();
		log(Status.INFO, "Entering Password ");
		getLoginPage().setPassword();
		getLoginPage().clickLoginButton();
		log(Status.INFO, "Click On Login Button ");
		}
		catch (Exception e) {
			log(Status.FAIL, "Login Test Case with InValid Credntial Fail",ExtentColor.RED);
			assertTrue(false);
		}
		
		String pageurl = getLoginPage().getLoginPageUrl();
		
		if (pageurl.contains("login")) {
			log(Status.PASS, "Login Test Case  with InValid Credntial Pass",ExtentColor.GREEN);
        } else {
            log(Status.FAIL, "Login Test Case with InValid Credntial Fail",ExtentColor.RED);
            assertTrue(false);
        }
			
	}
	}
	
	


