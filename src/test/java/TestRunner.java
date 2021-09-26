import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.Login;

import java.io.FileReader;

public class TestRunner extends SetUp {

    Login objLogin;
    @Test(enabled = false)
    public void doLogin() throws Exception {
        driver.get("http://automationpractice.com");
        objLogin = new Login(driver);
        JSONParser jsonParser=new JSONParser();
        Object obj =jsonParser.parse(new FileReader("./src/test/resources/users.json"));
        JSONObject jsonObject=(JSONObject)obj;
        String email=(String) jsonObject.get("email");
        String password=(String) jsonObject.get("password");
        String user=objLogin.doLogin(email,password);
        Assert.assertEquals(user,"Test User");
    }
    @Test(enabled = false)
    public void doLoginwithrongpassword() throws Exception {
        driver.get("http://automationpractice.com");
        objLogin = new Login(driver);
        JSONParser jsonParser=new JSONParser();
        Object obj =jsonParser.parse(new FileReader("./src/test/resources/users.json"));
        JSONObject jsonObject=(JSONObject)obj;
        String email=(String) jsonObject.get("email");
        String password=(String) jsonObject.get("password");
        String autherror=objLogin.doLoginforwrongpassword(email,password);
        Assert.assertEquals(autherror,"Authentication failed.");
    }
    @Test(enabled = true)
    public void doLoginwithrongemail() throws Exception {
        driver.get("http://automationpractice.com");
        objLogin = new Login(driver);
        JSONParser jsonParser=new JSONParser();
        Object obj =jsonParser.parse(new FileReader("./src/test/resources/users.json"));
        JSONObject jsonObject=(JSONObject)obj;
        String email=(String) jsonObject.get("email");
        String password=(String) jsonObject.get("password");
        String error=objLogin.doLoginforwrongemail(email,password);
        Assert.assertEquals(error,"Invalid email address.");
    }
}
