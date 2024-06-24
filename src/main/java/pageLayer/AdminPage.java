package pageLayer;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import Utility.GenericFunction;

public class AdminPage {
	Page page;
	GenericFunction utitlity;
	private String userRoleDropdown = "//label[text()='User Role']/..//following-sibling::div/div";
	private String usernameInputBox = "//label[text()='Username']/../following-sibling::div/input";
	public String searchButton = "button[type='submit']";
	public String recordFoundText = "//div[@class='orangehrm-container']/preceding-sibling::div//span";
	
	public AdminPage (Page page) {
		this.page = page;
	}

	public void selectUserRoleByText(String text) {
		page.locator(userRoleDropdown).click();
		page.locator("xpath=//div[@role='listbox']//span[text()='"+text+"']").click();
//		page.click(String.format("xpath=//div[@role='listbox']//span[text()='%s']", text));
	}
	
	
	public void enterUsername(String text) {
		page.fill(usernameInputBox, text);
	}
	
	public void clickSearchButton() {
		page.locator(searchButton).click();
	}
	
//	@SuppressWarnings("finally")
	public String serachRecordFound() {
		try {
			page.waitForSelector(recordFoundText,new Page.WaitForSelectorOptions().setTimeout(1000).setState(WaitForSelectorState.ATTACHED));
			
		} catch (Exception e) {
			
		}
		finally {
			return page.locator(recordFoundText).innerText();
		}		
	}
	
}

