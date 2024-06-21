package tests;

import java.util.List;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.WaitForSelectorState;

public class Akash {
	public static void main (String[] args) throws InterruptedException{
		Playwright pw = Playwright.create();
		Browser browser = pw.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
//		Browser browser = pw.chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false));
		// Create a new browser context
        BrowserContext context = browser.newContext();
		Page page = context.newPage();
		page.navigate("https://seleniumbase.io/demo_page");
		//td[normalize-space()='Text Input Field:']
			try {
				page.waitForSelector("//td[normalize-space()='Text Input Field:']",new Page.WaitForSelectorOptions().setTimeout(1000).setState(WaitForSelectorState.ATTACHED));
				System.out.println("try");
			} catch (Exception e) {
				System.out.println("catch");
			}
			
		context.close();
		browser.close();
		pw.close();
	}

}
