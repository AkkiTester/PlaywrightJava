package POM;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import Factory.PlaywrightFactory;
import reader.Configreader;

import static java.lang.reflect.Array.get;

public class LoginPage  extends PlaywrightFactory{

	// Locators
	private String username = "input[name='username']";
	private String password = "input[name='password']";
	private String LoginButton = "button[type='submit']";
	private String SocialLinks = "//div[@class='orangehrm-login-footer-sm']/a";
	
	
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

	public void SocialMediaLinksList(){
		Locator elements = page.locator(SocialLinks);
		int count = elements.count();
		System.out.println(count);
		List<String> PageName = new ArrayList<>(Arrays.asList("LinkdinPage", "FacebookPage", "TWpage", "YoutubePage"));

		for (int i = 0; i < count; i++) {
			Locator element = elements.nth(i);
			String PN =PageName.get(i);
//			element.click();
			Page newpage = page.waitForPopup(new Page.WaitForPopupOptions(),()-> {
				element.click();
			});

//			System.out.println("Element text: " + element.textContent());
			// Perform any actions you need on the element
		}
		System.out.println(browserContext.pages());
		// Get the list of open pages
		List<Page> pages = browserContext.pages();

		// Print the number of open pages
		System.out.println("Number of open pages: " + pages.size());

	};

//	public void SocialMediaLinksClick(){
//		for (int i = 0; i < count; i++) {
//			Locator element = elements.nth(i);
//			System.out.println("Element text: " + element.textContent());
//			// Perform any actions you need on the element
//		}
//	};
 
}
