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
		System.out.println(browser.browserType().name());
		page.navigate("https://the-internet.herokuapp.com/dynamic_loading/1");
		page.locator("//button[normalize-space()='Start']").click();
		Boolean visibility = page.locator("//h4[normalize-space()='Hello World!']").isVisible();
		System.out.println(visibility+"- Befor try");
		//td[normalize-space()='Text Input Field:']
			try {
				page.waitForSelector("//h4[normalize-space()='Hello World!']",new Page.WaitForSelectorOptions().setTimeout(6000).setState(WaitForSelectorState.VISIBLE));
				Boolean visibilitytry = page.locator("//h4[normalize-space()='Hello World!']").isVisible();
				System.out.println(visibilitytry+"- in try");
//				page.locator("//h4[normalize-space()='Hello World!']").hover();
				String text = page.locator("//h4[normalize-space()='Hello World!']").innerText();
				System.out.println(text);
				System.out.println("try");
			} catch (Exception e) {
				System.out.println("catch");
			}
			Boolean visibilityafter = page.locator("//h4[normalize-space()='Hello World!']").isVisible();
			System.out.println(visibilityafter+"- after try");
			
		context.close();
		browser.close();
		pw.close();
	}

}
