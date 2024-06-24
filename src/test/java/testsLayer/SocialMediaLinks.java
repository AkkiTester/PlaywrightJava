package testsLayer;

import com.aventstack.extentreports.Status;

import baseTest.BaseTestWeb;
import org.testng.annotations.Test;

public class SocialMediaLinks extends BaseTestWeb {
	
    @Test
    public void SocialMediaLinksCheck(){
        createTest("Admin System User Search");
        log(Status.INFO, "Navigated To login Page");
       
    }
}
