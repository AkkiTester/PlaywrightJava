package tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
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
		createTest("Api Test Case Get");
		Object result = JsonPath.read(responcebody, "books[0].isbn");
		System.out.println(result);
		System.out.println("--------");
		if (apiresponce.status()==200) {
			log(Status.PASS, "Api Test Case Pass",ExtentColor.INDIGO);
        } else {
            log(Status.FAIL, "Api Test Case Fail",ExtentColor.RED);
        }
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
