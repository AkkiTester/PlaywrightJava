package POM;



import java.util.Properties;

import com.microsoft.playwright.Page;

import Factory.PlaywrightFactory;
import reader.Configreader;

public class LoginPage  extends PlaywrightFactory{

	// Locators
	private String username = "input[name='username']";
	private String password = "input[name='password']";
	private String LoginButton = "button[type='submit']";
	
	
	//Constructor
	public LoginPage(Page page) {
		this.page = page;
		prop = new Configreader();
	}
	
	// page methods:
	public String getLoginPageTitle() {
		String title = page.title();
		System.out.println("page title : " + title);
		return title;
	}
	
	public String getLoginPageUrl() {
		return page.url();
	}
	
	public void setUsernameDdt(String name) {
//		String userName = prop.getProperty(name);
		page.fill(username,name);
	}
	
	public void setPasswordDdt(String pass) {
//		String Password = prop.getProperty(pass);
		page.fill(password, pass);
	}
	
	public void setUsername() {
		String userName = prop.getProperty("username").trim();
		page.fill(username,userName);
	}
	
	public void setPassword() {
		String Password = prop.getProperty("password").trim();
		page.fill(password, Password);
	}
	
	public void ClickLoginButton() {
		page.locator(LoginButton).click();
	}
	
	public void ClickLogout() {
		page.locator("//span[@class='oxd-userdropdown-tab']").click();
		page.locator("//a[normalize-space()='Logout']").click();
	}
 
}
