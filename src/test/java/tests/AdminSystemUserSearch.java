package tests;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;

import base.BaseTest;

public class AdminSystemUserSearch extends BaseTest {

	@Test
	public void test() {
		createTest("Admin System User Search Test");
		LoginPage.setUsername();
		log(Status.INFO, "Entering Username ");
		LoginPage.setPassword();
		log(Status.INFO, "Entering Password ");
		LoginPage.ClickLoginButton();
		log(Status.INFO, "Click login Button ");
		Dashboard.ClickAdminSideMenu();
		log(Status.INFO, "Click Admin side menu ");
		AdminPage.enterUsername("Admin");
		log(Status.INFO, "Enter Uername ");
		AdminPage.selectUserRoleByText("Admin");
		log(Status.INFO, "Select Role");
		AdminPage.clickSearchButton();
		log(Status.INFO, "Click Search Button ");

		System.out.println(AdminPage.serachRecordFound()+"----------------------------");
		if (AdminPage.serachRecordFound().contains("No")) {
			log(Status.FAIL, "Admin System User Search Test Fail",ExtentColor.RED);
            assertTrue(false);
        } else {
        	log(Status.PASS, "Admin System User Search Test Pass",ExtentColor.GREEN);
			assertTrue(true);
        }
	}
}
