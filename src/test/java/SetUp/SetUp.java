package SetUp;

import Utils.Utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class SetUp {
    public WebDriver driver;
    // only we use group only for smoke test
    //@BeforeTest(groups={"login_positive","login_negative"}) for run all test case
    //@BeforeTest(groups={"login_negative"}) for run  negative test case
    //@BeforeTest(groups ={"login_positive","login_negative"})// we can not run multiple group at same time we run  separate test case run
   // @BeforeTest(groups = {"login","purchase_product"})
    @BeforeTest(groups = "purchase_product")
    public void setUp(){
        ChromeOptions ops = new ChromeOptions();
        ops.addArguments("--headed");
        System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver.exe");
        driver = new ChromeDriver(ops);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @AfterMethod //AfterMethod annotation - This method executes after every test execution
    public void screenShot(ITestResult result) throws IOException {
        if (ITestResult.FAILURE == result.getStatus()) {
            try {
                Utils util = new Utils(driver);
                util.takeScreenShot();
            } catch (Exception exception) {
                System.out.println(exception.toString());
            }
        }
    }

    @AfterTest(groups = "purchase_product")
    public void Logout() throws InterruptedException {
        driver.quit();
    }
}
