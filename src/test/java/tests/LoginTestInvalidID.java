package tests;
import static org.testng.Assert.assertTrue;

import java.nio.file.Paths;

import org.testng.Assert;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.microsoft.playwright.Page;

import base.BaseTest;

public class LoginTestInvalidID  extends BaseTest{

	
	
	
	@Test
	public void loginTestInValidIDPass() {
		createTest("Login Test Case InValid Credntial");
		log(Status.INFO, "Entering Username ");
		LoginPage.setUsername();
		log(Status.INFO, "Entering Password ");
		LoginPage.setPassword();
		LoginPage.ClickLoginButton();
		log(Status.INFO, "Click On Login Button ");
		String pageurl = LoginPage.getLoginPageUrl();
		if (pageurl.contains("login")) {
			log(Status.PASS, "Login Test Case InValid Credntial",ExtentColor.GREEN);
        } else {
//        	page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(".\\Screenshot\\"+fileName+".png")).setFullPage(true));
            log(Status.FAIL, "Login Test Case InValid Credntial",ExtentColor.RED);
        }
		assertTrue(false);
//		Assert.assertTrue(pageurl.contains("login"), "URL does not contain 'dashboard'");

		
//		
//		LoginPage.setUsername();
//		LoginPage.setPassword();
//		LoginPage.ClickLoginButton();
//		String pageurl = LoginPage.getLoginPageUrl();
//		Assert.assertTrue(pageurl.contains("login"), "URL does not contain 'dashboard'");

		
	}
	
	
}

