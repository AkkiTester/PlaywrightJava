package testsLayer;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import baseTest.BaseTestWeb;

public class LoginPageTest extends BaseTestWeb {
    String pageUrl;

    @Test
    public void loginTestValidIDPass() {
        try {
            createTest("Login Test Case Valid Credential " + " - " + getBrowserName());
            log(Status.INFO, "Entering Username ");
            getLoginPage().setUsername();
            log(Status.INFO, "Entering Password ");
            getLoginPage().setPassword();
            getLoginPage().clickLoginButton();
            log(Status.INFO, "Click On Login Button ");
        } catch (Exception e) {
            log(Status.FAIL, "Login failed", ExtentColor.RED);
            Assert.fail("Test case failed due to exception: " + e.getMessage());
        }
        pageUrl = getLoginPage().getLoginPageUrl();
        if (pageUrl.contains("dashboard")) {
            log(Status.PASS, "Login successful", ExtentColor.GREEN);
        } else {
            log(Status.FAIL, "Login failed", ExtentColor.RED);
        }

        Assert.assertTrue(pageUrl.contains("dashboard"), "URL does not contain 'dashboard'");
    }
}
