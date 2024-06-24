package tests;

import base.BaseTest;
import com.aventstack.extentreports.Status;

import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SocialMediaLinks extends BaseTest {
	
    @Test
    public void SocialMediaLinksCheck(){
        createTest("Admin System User Search");
        log(Status.INFO, "Navigated To login Page");
       
    }
}
