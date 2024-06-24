package testsLayer;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;

import baseTest.BaseTestWeb;

public class AddEmploy extends BaseTestWeb {
	
	@Test
	public void AddEmployeeTest() {
		createTest("Add Employee Test"+" - "+getBrowserName());
		log(Status.INFO, "Entering Username ");
		LoginPage.setUsername();
		log(Status.INFO, "Entering Password");
		LoginPage.setPassword();
		log(Status.INFO, "Click on Login Button");
		LoginPage.ClickLoginButton();
		log(Status.INFO, "Click PIM side menu ");
		Dashboard.ClickPIMSideMenu();
		log(Status.INFO, "Click Add button ");
		PIMPage.clickAddButton();
		String id = PIMPage.EnterEmployDetails("User", "Name", "Test");
		log(Status.INFO, "Entering Name and get ID " + id);
		System.out.println(id);
		PIMPage.clickSaveButton();
		log(Status.INFO, "Click on save button ");
		String notificationmassage = PIMPage.getNotification();
		log(Status.INFO, "Notification -  " +notificationmassage);
		if (notificationmassage.contains("Success")){
//			assertTrue(" Employee Records".equals(MaintanancePage.CheckPurgeHeader()));
			log(Status.PASS, "Add Employee Pass",ExtentColor.GREEN);
			assertTrue(true);
			
		}
		else {
//			assertEquals("Purge Employee Records",MaintanancePage.CheckPurgeHeader());
			log(Status.FAIL, "Add Employee Fail",ExtentColor.RED);
			assertTrue(false);
			
		}
		
		
	}
	
//	@Test
//	public void SearchEmployeeTest() {
//		createTest("Search Employee Test");
//		log(Status.INFO, "Entering Username ");
//		LoginPage.setUsername();
//		log(Status.INFO, "Entering Password");
//		LoginPage.setPassword();
//		log(Status.INFO, "Click on Login Button");
//		LoginPage.ClickLoginButton();
//		log(Status.INFO, "Click PIM side menu ");
//		Dashboard.ClickPIMSideMenu();
//		
//	}
	

}
