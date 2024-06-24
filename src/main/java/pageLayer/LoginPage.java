package pageLayer;

import com.microsoft.playwright.Page;
import base.MainClass;
import reader.Configreader;

public class LoginPage extends MainClass {
    private Page page;
    
    // Locators
    private String username = "input[name='username']";
    private String password = "input[name='password']";
    private String loginButton = "button[type='submit']";
    
    // Constructor
    public LoginPage(Page page) {
        this.page = page;
        prop = new Configreader();
    }
    
    // Page methods:
    public String getLoginPageTitle() {
        String title = page.title();
        System.out.println("page title : " + title);
        return title;
    }
    
    public String getLoginPageUrl() {
        return page.url();
    }
    
    public void setUsernameDdt(String name) {
        page.fill(username, name);
    }
    
    public void setPasswordDdt(String pass) {
        page.fill(password, pass);
    }
    
    public void setUsername() {
        String userName = prop.getProperty("username").trim();
        page.fill(username, userName);
    }
    
    public void setPassword() {
        String passwordValue = prop.getProperty("password").trim();
        page.fill(password, passwordValue);
    }
    
    public void clickLoginButton() {
        page.locator(loginButton).click();
    }
    
    public void clickLogout() {
        page.locator("//span[@class='oxd-userdropdown-tab']").click();
        page.locator("//a[normalize-space()='Logout']").click();
    }
}
