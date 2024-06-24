package testsLayer;

import java.util.List;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class dropdown {
	public static void main (String[] args) throws InterruptedException{
		Playwright pw = Playwright.create();
		Browser browser = pw.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
//		Browser browser = pw.chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false));
		// Create a new browser context
        BrowserContext context = browser.newContext();
		Page driver = context.newPage();
		driver.navigate("https://seleniumbase.io/demo_page");
		Thread.sleep(3000);
		System.out.println(driver.url());
		driver.locator("//select[@id='mySelect']").selectOption("100%");
	

		driver.locator("//div[text()='Hover Dropdown']").hover();
		Thread.sleep(3000);
		driver.frameLocator("#myFrame3").locator("//input[@id='checkBox6']").click();
		driver.locator("//input[@id='checkBox1']").check();
		Thread.sleep(3000);
//		BrowserContext newContext = browser.newContext(new Browser.NewContextOptions().setViewportSize(280, 100));
		Page page = context.newPage();
		page.navigate("https://the-internet.herokuapp.com/windows");
//		page.locator("//a[normalize-space()='Click Here']").click();
		Thread.sleep(3000);
		System.out.println(driver.url());
		
		Page newpage = page.waitForPopup(()-> {
			page.locator("//a[normalize-space()='Click Here']").click();
			System.out.println(page.locator("div[class='example'] h3").innerText());
		});
		Thread.sleep(3000);
		System.out.println(newpage.locator("div[class='example'] h3").innerText());
		System.out.println(page.url());
		System.out.println(newpage.url());
		
//		Page List ----------------
		List<Page> pages = context.pages();
		System.out.println(pages);
		Thread.sleep(3000);
		
		
		pages.get(0).bringToFront();
		
		driver.locator("//input[@id='checkBox1']").click();
		Thread.sleep(3000);
		System.out.println(context.pages().size());
		
		for (Page pagef : pages) {
            pagef.bringToFront();
            System.out.println("Performing actions on page with URL: " + pagef.url());
            
            // Example action: print title of each page
            System.out.println("Page title: " + pagef.title());
            
            // You can add more actions here as needed for each page
            Thread.sleep(2000);
        }
		context.close();
		browser.close();
		pw.close();
	}

}
