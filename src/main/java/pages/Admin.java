package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class Admin extends page {
    WebDriver driver;
    public Admin(WebDriver driver){

        super(driver);
        PageFactory.initElements(driver, this);
    }
    @FindBy(xpath = "//a[@class='oxd-main-menu-item active']")
    WebElement AdminTab;
//الجزء دا خاصب إضافة ادمن جديد
    @FindBy(xpath = "//button[@class='oxd-button oxd-button--medium oxd-button--secondary']")
    WebElement addadminbutton;
    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[1]/div/div[2]/div/div")
    WebElement selectrole;

    @FindBy(xpath = "//div[text()=\"-- Select --\"]")
    WebElement selectstatus;

    @FindBy(xpath = "//input[@placeholder='Type for hints...']")
    WebElement adminname;
    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[2]/div/div[2]/div/div[2]/input")
    WebElement adminusername;
    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[2]/div/div[1]/div/div[2]/input")
    WebElement passwordAdmin;
    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[2]/div/div[2]/div/div[2]/input")
    WebElement confirmpasswordAdmin;
    @FindBy(xpath = "//button[@class='oxd-button oxd-button--medium oxd-button--secondary orangehrm-left-space']")
    WebElement savenewAdmin;

//الجزء دا خاص البحث عن الادمن الموجودين
    @FindBy(xpath = "//i[@class='oxd-icon bi-caret-up-fill']")
    WebElement showSearch;
    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[2]/div/div[2]/input")
    WebElement employeeidSearch;


    @FindBy(xpath = "//button[@class='oxd-icon-button oxd-main-menu-button']")
    WebElement editaadmininfo;

//الجزء دا مخصص لحذف الادمن
    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[1]/aside/nav/div[2]/div/div/button")
    WebElement deleteadmin;
    @FindBy(xpath = "//button[@class='oxd-button oxd-button--medium oxd-button--label-danger orangehrm-button-margin\']")
    WebElement Yesdelete;
    @FindBy(xpath = "//button[@class='oxd-button oxd-button--medium oxd-button--ghost orangehrm-button-margin']")
    WebElement noCancel;



    public void addnewEmployee(){
        addadminbutton.click();
    }
    public void selectrolee(int number){
        selectrole.clear();
        selectrole.click();
//        Select selectstatusdrobdown =new Select(selectrole);
//        selectstatusdrobdown.selectByIndex(number);
    }
    public void selectstatus(int number){
        selectrole.clear();
        selectstatus.click();
//        Select selectstatusdrobdown=new Select(selectstatus);
//        selectstatusdrobdown.selectByIndex(number);
    }
    public void entreemployeename(String name){
        adminname.click();
        adminname.sendKeys(name);
    }
    public void entreusername(String username){
        adminusername.click();
        adminusername.sendKeys(username);
    }
    public void entrepassword(String password){
        passwordAdmin.click();
        passwordAdmin.sendKeys(password);
        confirmpasswordAdmin.click();
        confirmpasswordAdmin.sendKeys(password);
    }
    public void saveAdminbutton(){
        savenewAdmin.click();
    }




}
