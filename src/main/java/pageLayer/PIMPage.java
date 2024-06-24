package pageLayer;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

public class PIMPage {
	private Page page;
	
	public PIMPage (Page page) {
		this.page = page;
	}
	
	private String AddButoon = "//button[text()=' Add ']";
	private String firstNameInputBox = "input[name='firstName']";
	private String middleNameInputBox = "input[name='middleName']";
	private String lastNameInputBox = "input[name='lastName']";
	private String employeeIDInputBox = "//label[text()='Employee Id']/../following-sibling::div/input";
	private String saveButton = "button[type='submit']";
	private String cancelButton= "//button[@type='submit']/preceding-sibling::button";
	
	public void clickAddButton() {
		page.click(AddButoon);
	}
	
	public String EnterEmployDetails(String firstName , String middleName, String lastName) {
		page.fill(firstNameInputBox, firstName);
		page.fill(middleNameInputBox, middleName);
		page.fill(lastNameInputBox, lastName);
		return page.inputValue(employeeIDInputBox);
	}
	
	public void clickSaveButton() {
		page.click(saveButton);
	}
	
	public void clickCancelButton() {
		page.click(cancelButton);
	}
	
	public String getNotification() {
		//div[@id='oxd-toaster_1']/div/div/div[2]/p[1]
		String textss;
		try {
			page.waitForSelector("//div[@id='oxd-toaster_1']/div/div/div[2]/p[1]",new Page.WaitForSelectorOptions().setTimeout(5000).setState(WaitForSelectorState.VISIBLE));
			textss = page.locator("//div[@id='oxd-toaster_1']/div/div/div[2]/p[1]").innerText();
//			System.out.println(textss);
		} catch (Exception e) {
//			System.out.println("Catch");
			textss = "Not get notification";
		}
		return textss;
		
		
	}
	
	

}
