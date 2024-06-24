package testsLayer;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;

import baseTest.BaseTestWeb;

public class LoginDDT  extends BaseTestWeb{
	String pageurl;
	@DataProvider(name="LoginData")
	public Object [][] getdata(){
		return excel.getTestDataDdt("Sheet1");
	}	
	@Test(dataProvider ="LoginData")
	public void loginTestValidIDPass(String username, String password ,String test,String testname) {
		try {
		createTest("Login DDT Test case for "+testname+" - "+getBrowserName());
		log(Status.INFO, "Entering Username ");
		getLoginPage().setUsernameDdt(username);
		log(Status.INFO, "Entering Password ");
		getLoginPage().setPasswordDdt(password);
		log(Status.INFO, "Click On Login Button ");
		getLoginPage().clickLoginButton();
		pageurl = getLoginPage().getLoginPageUrl();}
		catch (Exception e) {
			log(Status.FAIL, "Login DDT Test case for "+testname, ExtentColor.RED);
		}
		if (pageurl.contains(test)) {
			log(Status.PASS, "Login DDT Test case for "+testname,ExtentColor.GREEN);
        } else {
        	log(Status.FAIL, "Login DDT Test case for "+testname, ExtentColor.RED);
        }
		Assert.assertTrue(pageurl.contains(test), "URL does not contain 'dashboard'");
		try {
			if ("dashboard".equals(test)) {
             getLoginPage().clickLogout();}
        } catch (Exception e) {
            
        }
	}
	
	
	
}

