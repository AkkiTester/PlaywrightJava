package POM;

import com.microsoft.playwright.Page;

public class Dashboard {
	Page page;
	
	// Locators
	private String Maintenance = "//span[contains(normalize-space(),'Maintenance')]";
	private String Admin = "//span[text()='Admin']";
	
	
	//Constructor
	public Dashboard(Page page) {
		this.page = page;
	}
	
	// page methods:
	public void ClickMaintenanceSideMenu() {
		page.locator(Maintenance).click();
	}
	
	public void ClickAdminSideMenu() {
		page.locator(Admin).click();
	}
	
	
}
