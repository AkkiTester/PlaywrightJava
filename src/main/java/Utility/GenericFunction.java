package Utility;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.ElementState;
import com.microsoft.playwright.options.SelectOption;
import com.microsoft.playwright.options.WaitForSelectorState;

import base.MainClass;


public class GenericFunction extends MainClass {
	Page page;
	public GenericFunction(Page page) {
		this.page = page;
	}
	
//	Dropdown
	public void dropdownSelectionByValue(String element ,String value) {
		page.locator(element).selectOption(value);

	}
	
	public void dropdownSelectionByLable(String element ,String lable) {
		page.locator(element).selectOption(new SelectOption().setLabel(lable));
	}
	
	public void dropdownSelectionByIndex(String element ,int index) {
		page.locator(element).selectOption(new SelectOption().setIndex(index));
	}
//	Implicit wait 
	public void implicitWaitForElemnt(String element ) {
		page.waitForSelector(element);
	}
//	Explicit Wait
	public void explicitWaitForElemnt(String element,int time ) {		
		page.waitForSelector(element,new Page.WaitForSelectorOptions().setTimeout(time).setState(WaitForSelectorState.ATTACHED));
	}
	
	public void waitForElemntState(String element,int time ) {
		ElementHandle hrefElement = page.querySelector(element);
		hrefElement.waitForElementState(ElementState.VISIBLE);
		hrefElement.waitForElementState(ElementState.VISIBLE,new ElementHandle.WaitForElementStateOptions().setTimeout(time));
	}
	
//	Alternate Thread.sleep()
	public void Thread_sleep(int time ) {
		page.waitForTimeout(time);
	}
	
	public void hoverElement(String element) {
		page.locator(element).hover();
	}
	
	public void forceClick(String element) {
		page.locator(element).click(new Locator.ClickOptions().setForce(true));
	}
	
	public boolean checkboxIsChecked(String element) {
		return page.locator(element).isChecked();
	}
	
	public void scrollIntoViwe(String element) {
		page.locator(element).scrollIntoViewIfNeeded();
	}
	
	public void windowToVisibleByIndex(int index) {
		List<Page> pages = browserContext.pages();
		pages.get(index).bringToFront();
	}
	
/**
 * TimeStamp
 * @return String
 */
	public String getTimeStamp() {
		return new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
	}
	
	
	
}
