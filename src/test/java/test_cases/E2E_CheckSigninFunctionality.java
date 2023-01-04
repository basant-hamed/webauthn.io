package test_cases;

import org.testng.annotations.Test;

public class E2E_CheckSigninFunctionality extends TestBase{
    @Test(priority = 2, groups = "smoke", description = "Sign in to  WebAuth Web Application")
    public void signIn() {
        homePage.addEmail();
        homePage.clickAuthenticationButton();


    }
}
