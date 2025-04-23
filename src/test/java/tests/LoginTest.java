package tests;

import base.BaseTest;
import org.bouncycastle.crypto.agreement.srp.SRP6Client;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
import pages.Admin;
import pages.Loginpage;


public class LoginTest extends BaseTest {
    WebDriver driver;
    Loginpage loginPage;
    Admin admin;
    BaseTest bse =new BaseTest();
    @BeforeClass
    public void start()  {
        setup();
        loginPage=new Loginpage(getdriver());
        admin=new Admin(getdriver());
    }
    @Test(priority = 1)
    public void testValidLogin() throws InterruptedException {

         loginPage.sendusername("Admin");
         loginPage.sendpassword("admin123");
         loginPage.clicksubmit();
        // Assertion: التأكد إنك دخلت على الـ Dashboard
        Thread.sleep(2000); // انتظر الصفحة تتحمّل
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("dashboard"),
                "Failed to login! Expected URL to contain 'dashboard', but found: " + currentUrl);

    }
    // Test Case 2: تسجيل دخول ببيانات غلط
    @Test(priority = 2)
    public void testInvalidLogin() throws InterruptedException {
        // ارجع لصفحة تسجيل الدخول (لأنك هتكون داخل الـ Dashboard من الاختبار الأول)
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        loginPage.sendusername("WrongUser");
        loginPage.sendpassword("wrongpass");
        loginPage.clicksubmit();

        // Assertion: التأكد إن رسالة الخطأ ظهرت
        Thread.sleep(2000);
        String errorMessage = loginPage.errormassagefield.getText();
        Assert.assertTrue(errorMessage.contains("Invalid credentials"),
                "Error message not displayed! Expected 'Invalid credentials', but found: " + errorMessage);
    }
    // Test Case 3: تسجيل الدخول مرة تانية عشان نكمّل الـ Journey
    @Test(priority = 3, dependsOnMethods = "testInvalidLogin")
    public void loginAgainForJourney() throws InterruptedException {
        loginPage.sendusername("Admin");
        loginPage.sendpassword("admin123");
        loginPage.clicksubmit();

        // Assertion: التأكد إنك دخلت على الـ Dashboard
        Thread.sleep(2000);
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("dashboard"),
                "Failed to login again! Expected URL to contain 'dashboard', but found: " + currentUrl);
    }
    @Test(priority = 4, dependsOnMethods = "loginAgainForJourney")
    public void testAddNewAdmin() throws InterruptedException {
        // الانتقال لصفحة الـ Admin
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/admin/viewSystemUsers");
        Thread.sleep(2000);

        // إضافة Admin جديد
        admin.addnewEmployee();
        Thread.sleep(1000);

        // اختيار الـ Role (مثلاً Admin)
        admin.selectrolee(1); // 1 = Admin role
        Thread.sleep(1000);

        // اختيار الـ Status (مثلاً Enabled)
        admin.selectstatus(1); // 1 = Enabled
        Thread.sleep(1000);

        // إدخال بيانات الـ Admin
        admin.entreemployeename("John Doe");
        admin.entreusername("john.doe");
        admin.entrepassword("Password123!");
        Thread.sleep(1000);

        // حفظ الـ Admin
        admin.saveAdminbutton();

        // Assertion: التأكد إن الـ Admin اتضاف بنجاح
        Thread.sleep(2000);
        WebElement successMessage = driver.findElement(By.xpath("//p[contains(text(), 'Successfully Saved')]"));
        Assert.assertTrue(successMessage.isDisplayed(),
                "Admin was not added! Success message not displayed.");
    }
    @Test(priority = 5, dependsOnMethods = "testAddNewAdmin")
    public void testSearchAdmin() throws InterruptedException {
        // الانتقال لصفحة الـ Admin (في حالة ما اتعملش refresh)
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/admin/viewSystemUsers");
        Thread.sleep(2000);

        // فتح حقل البحث
        admin.showSearch.click();
        Thread.sleep(1000);

        // البحث بالـ Username
        admin.employeeidSearch.sendKeys("john.doe");
        Thread.sleep(1000);

        // الضغط على زر البحث (هنضيف XPath لزر البحث)
        WebElement searchButton = driver.findElement(By.xpath("//button[@type='submit']"));
        searchButton.click();

        // Assertion: التأكد إن الـ Admin موجود في نتايج البحث
        Thread.sleep(2000);
        WebElement searchResult = driver.findElement(By.xpath("//span[contains(text(), 'john.doe')]"));
        Assert.assertTrue(searchResult.isDisplayed(),
                "Admin not found in search results! Expected to find 'john.doe'.");
    }

    // Test Case 6: حذف الـ Admin
    @Test(priority = 6, dependsOnMethods = "testSearchAdmin")
    public void testDeleteAdmin() throws InterruptedException {
        // الضغط على زر التعديل/الحذف (بجانب نتيجة البحث)
        WebElement deleteButton = driver.findElement(By.xpath("//i[@class='oxd-icon bi-trash']"));
        deleteButton.click();
        Thread.sleep(1000);

        // تأكيد الحذف
        admin.Yesdelete.click();

        // Assertion: التأكد إن الـ Admin اتّمسح
        Thread.sleep(2000);
        WebElement successMessage = driver.findElement(By.xpath("//p[contains(text(), 'Successfully Deleted')]"));
        Assert.assertTrue(successMessage.isDisplayed(),
                "Admin was not deleted! Success message not displayed.");
    }



}
