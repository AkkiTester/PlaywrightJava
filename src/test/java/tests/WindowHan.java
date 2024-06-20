package tests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.ElementState;
import com.microsoft.playwright.options.WaitForSelectorState;

public class WindowHan {

	public static void main (String[] args ) throws InterruptedException {
		
		Playwright playwright =Playwright.create();
		
		Browser browser = playwright.chromium().
				launch(new BrowserType.LaunchOptions().
						setHeadless(false));
		BrowserContext context = browser.newContext();
//		context.setDefaultTimeout(40000);
		Page page = context.newPage();
		page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		System.out.println(page.url());
//		page.waitForLoadState();
//		Thread.sleep(2000);
		
//		ElementHandle hrefElement = page.querySelector("text=OrangeHRM OS 5.6.1");
//		hrefElement.waitForElementState(ElementState.VISIBLE);
//		hrefElement.waitForElementState(ElementState.VISIBLE,new ElementHandle.WaitForElementStateOptions().setTimeout(1000));
		
		
//		---------------------  Explicit wait  -------------------------------------------------
		page.waitForSelector("//p[normalize-space()='OrangeHRM OS 5.6.1']");
//		page.locator("//p[normalize-space()='OrangeHRM OS 5.6.1']").highlight();
		page.waitForSelector("//p[normalize-space()='OrangeHRM OS 5.6.1']").waitForElementState(ElementState.STABLE);
//		page.locator("body > div:nth-child(3) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(4) > div:nth-child(1) > a:nth-child(1) > svg:nth-child(1) > g:nth-child(1) > path:nth-child(1)").hover();
		Locator elements = page.locator("//div[@class='orangehrm-login-footer-sm']/a");
		elements.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
		
		int countl = elements.count();
		System.out.println(countl+"Element count");
		List<String> PageName = new ArrayList<>(Arrays.asList("LinkdinPage", "FacebookPage", "TWpage", "YoutubePage"));
		PageName.get(1);
		for (int i = 0; i < countl; i++) {
			Locator element = elements.nth(i);
			String PN =PageName.get(i);
//			element.click();
			Page newpage = page.waitForPopup(new Page.WaitForPopupOptions(),()-> {
				element.click();
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			});

//			System.out.println("Element text: " + element.textContent());
			// Perform any actions you need on the element
		}
		List<Page> pages = context.pages();
		for (int i = 1; i < countl; i++) {
			
			String Name = PageName.get(i);
			
			 Page Linkdin = pages.get(2);
		}
		System.out.println(context.pages()+"direct page");
		// Get the list of open pages
		

		// Print the number of open pages
		System.out.println("Number of open pages: " + pages.size());
		
		
		context.close();
		browser.close();
		playwright.close();
	}

	
	}
	
	

