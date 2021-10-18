package TestRunner;

import SetUp.SetUp;
import Utils.Utils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import pages.Login;

import java.io.FileReader;

public class LogInTestRunner extends SetUp {

    Login objLogin;
    Utils utils;

    // @Test(enabled =true,priority = 1,description = "login with valid email and password",groups = "login_positive")
    @Test(enabled = true, groups = "login", description = "Login with valid email and password")
    public void doLogin() throws Exception {
        driver.get("http://automationpractice.com");
        objLogin = new Login(driver);
        utils =new Utils(driver);
        utils.readJSONArray(0);
        String email=utils.getEmail();
        String password=utils.getPassword();

        String user = objLogin.doLogin(email, password);
        Assert.assertEquals(user, "Test User");
        driver.findElement(By.xpath("//a[@class='logout']")).click();
        utils.addDescription("User can logged in successfully with valid user and password\"");
    }
    @Test(enabled = false)
    public void doLoginwithrongpassword() throws Exception {
        driver.get("http://automationpractice.com");
        objLogin = new Login(driver);
        JSONParser jsonParser=new JSONParser();
        Object obj =jsonParser.parse(new FileReader("./src/test/resources/user.json"));
        JSONObject jsonObject=(JSONObject)obj;
        String email=(String) jsonObject.get("email");
        String password=(String) jsonObject.get("password");
        String autherror=objLogin.doLoginforwrongpassword(email,password);
        Assert.assertEquals(autherror,"Authentication failed.");
    }
    @Test(enabled = false)
    public void doLoginwithrongemail() throws Exception {
        driver.get("http://automationpractice.com");
        objLogin = new Login(driver);
        JSONParser jsonParser=new JSONParser();
        Object obj =jsonParser.parse(new FileReader("./src/test/resources/user.json"));
        JSONObject jsonObject=(JSONObject)obj;
        String email=(String) jsonObject.get("email");
        String password=(String) jsonObject.get("password");
        String error=objLogin.doLoginforwrongemail(email,password);
        Assert.assertEquals(error,"Invalid email address.");
    }
    @Test(enabled = true, description = "Login with wrong password", groups = "login")
    public void doLoginForWrongPassword() throws Exception {
        driver.get("http://automationpractice.com");
        objLogin = new Login(driver);
        utils =new Utils(driver);
        utils.readJSONArray(1);
        String email=utils.getEmail();
        String password=utils.getPassword();
        String authError = objLogin.doLoginforwrongpassword(email, password);
        Assert.assertEquals(authError, "Authentication failed.");
        utils.addDescription("Authentication error shows when user provides wrong Password");
    }
    @Test(enabled = true, description = "Login with invalid email", groups = "login")
    public void doLoginForInvalidEmail() throws Exception {
        driver.get("http://automationpractice.com");
        objLogin = new Login(driver);
        utils =new Utils(driver);
        utils.readJSONArray(2);
        String email=utils.getEmail();
        String password=utils.getPassword();
        String error = objLogin.doLoginforwrongemail(email, password);
        Assert.assertEquals(error, "Invalid email address.");
        utils.addDescription("Error message shows when user provides invalid email");

    }


}
