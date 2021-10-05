package TestRunner;

import SetUp.SetUp;
import org.testng.annotations.Test;
import pages.Signup;

public class SignupTestRunner  extends SetUp {
    Signup objSignup;
    @Test(enabled = true) public void doSignup() throws Exception {
        driver.get("http://automationpractice.com");
        objSignup = new Signup(driver);
        objSignup.memberSignUp();
    }

}
