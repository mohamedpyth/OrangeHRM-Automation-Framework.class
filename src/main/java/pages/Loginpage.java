package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Loginpage extends page{
     WebDriver driver;

    public Loginpage(WebDriver driver) {
       super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@placeholder='Username']")
    WebElement usernamefield;

    @FindBy(xpath = "//input[@placeholder='Password']")
    WebElement passwordfield;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement loginfield;

    @FindBy(xpath = "//div[@class='orangehrm-login-forgot']")
    WebElement forgetpasswordfield;

    @FindBy(xpath = "//div[@class='oxd-alert-content oxd-alert-content--error']")
    WebElement errormassagefield;


    public void sendusername(String username) throws InterruptedException {
        Thread.sleep(3000);
        usernamefield.clear();
        usernamefield.sendKeys(username);
    }
    public void sendpassword(String password) {

        passwordfield.clear();
        passwordfield.sendKeys(password);
    }
    public void clicksubmit(){

        loginfield.click();
    }
}
