package testsLayer;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import baseTest.BaseTestWeb;

public class AddEmploy extends BaseTestWeb {
    String notificationMassage;

    @Test
    public void AddEmployeeTest() {
        try {
            createTest("Add Employee Test" + " - " + getBrowserName());
            log(Status.INFO, "Entering Username ");
            getLoginPage().setUsername();
            log(Status.INFO, "Entering Password");
            getLoginPage().setPassword();
            log(Status.INFO, "Click on Login Button");
            getLoginPage().clickLoginButton();;
            log(Status.INFO, "Click PIM side menu ");
            getDashboard().ClickPIMSideMenu();
            log(Status.INFO, "Click Add button ");
            getPIMPage().clickAddButton();
            String id = getPIMPage().EnterEmployDetails("User", "Name", "Test");
            log(Status.INFO, "Entering Name and get ID " + id);
            System.out.println(id);
            getPIMPage().clickSaveButton();
            log(Status.INFO, "Click on save button ");
            notificationMassage = getPIMPage().getNotification();
            log(Status.INFO, "Notification -  " + notificationMassage);
        } catch (Exception e) {
            log(Status.FAIL, "Add Employee Fail", ExtentColor.RED);
            assertTrue(false);
        }
        if (notificationMassage.contains("Success")) {
            log(Status.PASS, "Add Employee Pass", ExtentColor.GREEN);
            assertTrue(true);
        } else {
            log(Status.FAIL, "Add Employee Fail", ExtentColor.RED);
            assertTrue(false);
        }
    }
}
