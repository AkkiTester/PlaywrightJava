package testsLayer;
import static org.testng.Assert.assertTrue;


import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import baseTest.BaseTestWeb;

public class AddEmploy extends BaseTestWeb {
	String notificationmassage;
	@Test
	public void AddEmployeeTest() {
		try {
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
		notificationmassage = PIMPage.getNotification();
		log(Status.INFO, "Notification -  " +notificationmassage);
		}catch (Exception e) {
			log(Status.FAIL, "Add Employee Fail",ExtentColor.RED);
			assertTrue(false);
		}
		if (notificationmassage.contains("Success")){
			log(Status.FAIL, "Add Employee Pass",ExtentColor.GREEN);
			assertTrue(false);
			
		}
		else {
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
