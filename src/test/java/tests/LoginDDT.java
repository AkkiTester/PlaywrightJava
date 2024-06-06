

package tests;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

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
	public void loginTestValidIDPass(String username, String password ,String test) {
		LoginPage.setUsernameDdt(username);
		LoginPage.setPasswordDdt(password);
		LoginPage.ClickLoginButton();
		String pageurl = LoginPage.getLoginPageUrl();
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

