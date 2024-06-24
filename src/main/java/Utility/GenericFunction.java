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
    private Page page;

    public GenericFunction(Page page) {
        this.page = page;
    }

    // Dropdown selection by value
    public void dropdownSelectionByValue(String element, String value) {
        page.locator(element).selectOption(value);
    }

    // Dropdown selection by label
    public void dropdownSelectionByLabel(String element, String label) {
        page.locator(element).selectOption(new SelectOption().setLabel(label));
    }

    // Dropdown selection by index
    public void dropdownSelectionByIndex(String element, int index) {
        page.locator(element).selectOption(new SelectOption().setIndex(index));
    }

    // Implicit wait for element
    public void implicitWaitForElement(String element) {
        page.waitForSelector(element);
    }

    // Explicit wait for element with custom timeout
    public void explicitWaitForElement(String element, int time) {
        page.waitForSelector(element, new Page.WaitForSelectorOptions().setTimeout(time).setState(WaitForSelectorState.ATTACHED));
    }

    // Wait for element to be in visible state
    public void waitForElementState(String element, int time) {
        ElementHandle hrefElement = page.querySelector(element);
        hrefElement.waitForElementState(ElementState.VISIBLE);
        hrefElement.waitForElementState(ElementState.VISIBLE, new ElementHandle.WaitForElementStateOptions().setTimeout(time));
    }

    // Alternative to Thread.sleep()
    public void threadSleep(int time) {
        page.waitForTimeout(time);
    }

    // Hover over an element
    public void hoverElement(String element) {
        page.locator(element).hover();
    }

    // Perform a force click on an element
    public void forceClick(String element) {
        page.locator(element).click(new Locator.ClickOptions().setForce(true));
    }

    // Check if a checkbox element is checked
    public boolean checkboxIsChecked(String element) {
        return page.locator(element).isChecked();
    }

    // Scroll an element into view
    public void scrollIntoView(String element) {
        page.locator(element).scrollIntoViewIfNeeded();
    }

    // Bring a window to front by its index
    public void bringWindowToFrontByIndex(int index) {
        List<Page> pages = getBrowserContext().pages();
        pages.get(index).bringToFront();
    }

    /**
     * Get current timestamp in format yyyy.MM.dd.HH.mm.ss
     * 
     * @return String timestamp
     */
    public String getTimeStamp() {
        return new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
    }
}
