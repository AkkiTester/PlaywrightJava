

package tests;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;

import base.BaseTest;

public class LoginDDT  extends BaseTest{

//	
//	@DataProvider(name = "loginData")
//	public Object[][] loginData() {
//	    return new Object[][]{
//	        {"Admina", "Admina"},
//	        {"Admin", "admin"},
//	        {"Admin", "admin123"},
//	        {"AAAA", "admin123"}
//	    };
//	}
	
	
//	@DataProvider(name="LoginData")
//	public String [][] getdata(){
//		return excel.getUserLoginTestData("LoginData");
//	}
	
	@DataProvider(name="LoginData")
	public Object [][] getdata(){
		return excel.getTestDataDdt("Sheet1");
	}
	
	@Test(dataProvider ="LoginData")
	public void loginTestValidIDPass(String username, String password ,String test,String testname) {
		createTest("Login DDT Test case for "+testname);
		log(Status.INFO, "Entering Username ");
		LoginPage.setUsernameDdt(username);
		log(Status.INFO, "Entering Password ");
		LoginPage.setPasswordDdt(password);
		log(Status.INFO, "Click On Login Button ");
		LoginPage.ClickLoginButton();
		String pageurl = LoginPage.getLoginPageUrl();
		if (pageurl.contains(test)) {
			log(Status.PASS, "Login DDT Test case for "+testname,ExtentColor.GREEN);
        } else {
        	log(Status.FAIL, "Login DDT Test case for "+testname, ExtentColor.RED);
//            log(Status.FAIL,  ("Login DDT Test case for "+testname ));
        }
		Assert.assertTrue(pageurl.contains(test), "URL does not contain 'dashboard'");
		try {
//			System.out.println(test);
//			System.out.println(type(test));
			if ("dashboard".equals(test)) {
             LoginPage.ClickLogout();}
        } catch (Exception e) {
            // Log the exception and fail the test
//            e.printStackTrace();
//            Assert.fail("Logout failed with exception: " + e.getMessage());
        }
	}
	
	
	
}

