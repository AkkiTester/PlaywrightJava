package base;

import java.nio.file.Paths;
import java.util.Arrays;
import com.microsoft.playwright.*;
import reader.Configreader;
import reader.ExcelFileReader;
import Utility.GenericFunction;
import pageLayer.*;

public class MainClass {
    private static ThreadLocal<Playwright> playwright = new ThreadLocal<>();
    private static ThreadLocal<Browser> browser = new ThreadLocal<>();
    private static ThreadLocal<BrowserContext> browserContext = new ThreadLocal<>();
    private static ThreadLocal<Page> page = new ThreadLocal<>();
    protected GenericFunction genericFunction;
    protected LoginPage loginPage;
    protected AdministratorLogin administratorLogin;
    protected MaintanancePage maintanancePage;
    protected Dashboard dashboard;
    protected AdminPage adminPage;
    protected PIMPage pimPage;
    public Configreader prop;
    public ExcelFileReader excel;

    public Page initBrowser(String browserName) {
        System.out.println("Browser name is: " + browserName);
        playwright.set(Playwright.create());

        switch (browserName.toLowerCase()) {
            case "chromium":
                browser.set(playwright.get().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)));
                break;
            case "firefox":
                browser.set(playwright.get().firefox().launch(new BrowserType.LaunchOptions().setChannel("firefox").setHeadless(false)));
                break;
            case "chrome":
                browser.set(playwright.get().chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false)));
                break;
            case "edge":
                browser.set(playwright.get().chromium().launch(new BrowserType.LaunchOptions().setChannel("msedge").setHeadless(false).setArgs(Arrays.asList("--start-maximized"))));
                break;
            default:
                System.out.println("Please pass the right browser name.....");
        }

        browserContext.set(browser.get().newContext(new Browser.NewContextOptions()
                .setRecordVideoDir(Paths.get("TestVideos/"))
                .setRecordVideoSize(1280, 720)));

        browserContext.get().setDefaultTimeout(80000);

        page.set(browserContext.get().newPage());
        page.get().navigate(prop.getProperty("loginpageurl"));

        return page.get();
    }

    public BrowserContext getBrowserContext() {
        return browserContext.get();
    }

    public void setBrowserContext(BrowserContext context) {
        browserContext.set(context);
    }

    public Page getPage() {
        return page.get();
    }

    public void setPage(Page p) {
        page.set(p);
    }

    public void closeBrowser() {
        if (browserContext.get() != null) {
            browserContext.get().close();
            browserContext.remove();
        }
        if (browser.get() != null) {
            browser.get().close();
            browser.remove();
        }
        if (playwright.get() != null) {
            playwright.get().close();
            playwright.remove();
        }
    }
}
