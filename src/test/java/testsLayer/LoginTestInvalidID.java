package testsLayer;
import static org.testng.Assert.assertTrue;

import java.nio.file.Paths;

import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.microsoft.playwright.Page;

import baseTest.BaseTestWeb;

public class LoginTestInvalidID  extends BaseTestWeb{
	@Test
	public void loginTestInValidIDPass() {
		try {
		createTest("Login Test Case InValid Credntial"+" - "+getBrowserName());
		log(Status.INFO, "Entering Username ");
		LoginPage.setUsername();
		log(Status.INFO, "Entering Password ");
		LoginPage.setPassword();
		LoginPage.ClickLoginButton();
		log(Status.INFO, "Click On Login Button ");
		}
		catch (Exception e) {
			String pageurl = LoginPage.getLoginPageUrl();
			log(Status.FAIL, "Failed to Login",ExtentColor.RED);
			assertTrue(pageurl.contains("login"));
		}
		
		String pageurl = LoginPage.getLoginPageUrl();
		
		if (pageurl.contains("login")) {
			log(Status.PASS, "Login Test Case  with InValid Credntial Pass",ExtentColor.GREEN);
        } else {
//        	page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(".\\Screenshot\\"+fileName+".png")).setFullPage(true));
            log(Status.FAIL, "Login Test Case with InValid Credntial Fail",ExtentColor.RED);
            assertTrue(false);
        }
		
		
	}
//		Assert.assertTrue(pageurl.contains("login"), "URL does not contain 'dashboard'");

		
//		
//		LoginPage.setUsername();
//		LoginPage.setPassword();
//		LoginPage.ClickLoginButton();
//		String pageurl = LoginPage.getLoginPageUrl();
//		Assert.assertTrue(pageurl.contains("login"), "URL does not contain 'dashboard'");

		
	}
	
	


