package tests;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class dropdown {
	public static void main (String[] args) throws InterruptedException{
		Playwright pw = Playwright.create();
		Browser browser = pw.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
//		Browser browser = pw.chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false));
		Page driver = browser.newPage();
		driver.navigate("https://seleniumbase.io/demo_page");
		Thread.sleep(3000);
		System.out.println(driver.url());
		driver.locator("//select[@id='mySelect']").selectOption("100%");
		driver.locator("//div[text()='Hover Dropdown']").hover();
		Thread.sleep(3000);
		driver.frameLocator("#myFrame3").locator("//input[@id='checkBox6']").click();
		driver.locator("//input[@id='checkBox1']").check();
		Thread.sleep(3000);
		Page page = browser.newPage();
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
		
		Thread.sleep(3000);
		
		browser.close();
		pw.close();
	}

}
