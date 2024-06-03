package POM;

import com.microsoft.playwright.Page;

public class LoginPage  {
	
	Page page;
	
	// Locators
	private String username = "input[name='username']";
	private String password = "input[name='password']";
	private String LoginButton = "button[type='submit']";
	
	
	//Constructor
	public LoginPage(Page page) {
		this.page = page;
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
	
	public void setUsername(String Username) {
		page.fill(username, Username);
	}
	
	public void setPassword(String Password) {
		page.fill(password, Password);
	}
	
	public void ClickLoginButton() {
		page.locator(LoginButton).click();
	}
 
}
