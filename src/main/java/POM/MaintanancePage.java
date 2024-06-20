package POM;

import com.microsoft.playwright.Page;

public class MaintanancePage {
	Page page;
	
	// Locators
		private String PurgeHeader = "//h6[normalize-space()='Purge Employee Records']";
		
		
		//Constructor
		public MaintanancePage(Page page) {
			this.page = page;
		}
		
		// page methods:
		
		public String CheckPurgeHeader() {
			return page.locator(PurgeHeader).innerText();
		}
		
	
}
