package testsLayer;


import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;

import baseTest.BaseTestWeb;

public class AdminSystemUserSearch extends BaseTestWeb {
	@Test
	public void test() {
		createTest("Admin System User Search Test"+" - "+getBrowserName());
		getLoginPage().setUsername();
		log(Status.INFO, "Entering Username ");
		getLoginPage().setPassword();
		log(Status.INFO, "Entering Password ");
		getLoginPage().clickLoginButton();
		log(Status.INFO, "Click login Button ");
		getDashboard().ClickAdminSideMenu();
		log(Status.INFO, "Click Admin side menu ");
		getAdminPage().enterUsername("Admin");
		log(Status.INFO, "Enter Uername ");
		getAdminPage().selectUserRoleByText("Admin");
		log(Status.INFO, "Select Role");
		getAdminPage().clickSearchButton();
		log(Status.INFO, "Click Search Button ");
		if (getAdminPage().serachRecordFound().contains("No")) {
			log(Status.FAIL, "Admin System User Search Test Fail",ExtentColor.RED);
            assertTrue(false);
        } else {
        	log(Status.PASS, "Admin System User Search Test Pass",ExtentColor.GREEN);
			assertTrue(true);
        }
	}
}
