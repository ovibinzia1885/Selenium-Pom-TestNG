package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Login {
    WebDriver driver;
    @FindBy(className = "login")
    WebElement linkLogin;
    @FindBy(id = "email")
    WebElement txtemail;
    @FindBy(id = "passwd")
    WebElement txtpassword;
    @FindBy(id = "SubmitLogin")
    WebElement btnsubmitLogin;
    @FindBy(xpath = "//span[contains(text(),'Test User')]")
    WebElement lblUserName;
    @FindBy(xpath = "//li[contains(text(),'Authentication failed.')]")
    WebElement autherror;
    @FindBy(xpath = "//li[contains(text(),'Invalid email address.')]")
    WebElement emailerror;

    public Login(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public  String doLogin(String email,String password) throws InterruptedException {
        linkLogin.click();
        txtemail.sendKeys(email);
        txtpassword.sendKeys(password);
        btnsubmitLogin.click();
        return lblUserName.getText();

    }
    public  String doLoginforwrongpassword(String email,String password) throws InterruptedException {
        linkLogin.click();
        txtemail.sendKeys(email);
        txtpassword.sendKeys(password);
        btnsubmitLogin.click();
        return autherror.getText();

    }
    public  String doLoginforwrongemail(String email,String password) throws InterruptedException {
        linkLogin.click();
        txtemail.sendKeys(email);
        txtpassword.sendKeys(password);
        btnsubmitLogin.click();
        return emailerror.getText();

    }
    }
