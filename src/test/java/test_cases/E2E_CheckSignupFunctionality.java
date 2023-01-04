package test_cases;

import org.openqa.selenium.Alert;
import org.testng.annotations.Test;

public class E2E_CheckSignupFunctionality extends TestBase{

    @Test(priority = 2, groups = "smoke", description = "Sign up to  WebAuth Web Application")
    public void signUp() {
        homePage.addEmail();
        homePage.clickRegistrationButton();

    }

}
