package POM;

import com.microsoft.playwright.Page;

public class Dashboard {
	Page page;
	
	// Locators
	private String Maintenance = "//span[contains(normalize-space(),'Maintenance')]";
	
	
	//Constructor
	public Dashboard(Page page) {
		this.page = page;
	}
	
	// page methods:
	public void ClickMaintenance() {
		page.locator(Maintenance).click();
	}
}
