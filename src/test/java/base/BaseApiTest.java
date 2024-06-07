package base;

import org.testng.annotations.BeforeTest;

import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.Playwright;

public class BaseApiTest {

	public Playwright playwright ;
	public APIRequest request ;
	public APIRequestContext requestContext ;
	
	@BeforeTest
	public void setup() {
	playwright = Playwright.create();
	request = playwright.request();
	requestContext =  request.newContext();
	}
	
}
