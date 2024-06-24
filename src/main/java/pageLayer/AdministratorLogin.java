package pageLayer;

import com.microsoft.playwright.Page;

public class AdministratorLogin {
	private Page page;
	
	// Locators
	private String PasswordBox = "input[type='password']";
	private String ConfirmButton = "//button[normalize-space()='Confirm']";
	
	//Constructor
	public AdministratorLogin(Page page) {
		this.page = page;
	}
	
	// page methods:
	public void Enterpassword() {
		page.locator(PasswordBox).fill("admin123");
	}
	
	public void ClickConfirmButton() {
		page.locator(ConfirmButton).click();
	}
	
}
