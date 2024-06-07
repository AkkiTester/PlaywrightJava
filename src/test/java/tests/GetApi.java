package tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;
import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;

import base.BaseApiTest;
import base.BaseTest;

public class GetApi extends BaseApiTest{

	@Test
	public void getUserApiTest() {
		APIResponse apiresponce = requestContext.get("https://bookstore.toolsqa.com/BookStore/v1/Books");
		String responcebody = apiresponce.text();
		Object result = JsonPath.read(responcebody, "books[0].isbn");
		System.out.println(result);
		System.out.println("--------");
		Assert.assertEquals(apiresponce.status(), 200);
		System.out.println(apiresponce.statusText());
		System.out.println(apiresponce.statusText());
		System.out.println(apiresponce.text());
		System.out.println(apiresponce.body().toString());
		Map<String, String> headersMap = apiresponce.headers();
		System.out.println(headersMap);
		System.out.println("----"+headersMap.get("content-type")+"----");
		System.out.println(apiresponce.headers());
	}
}
