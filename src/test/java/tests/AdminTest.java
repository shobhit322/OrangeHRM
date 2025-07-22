package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

public class AdminTest extends BaseTest {

    @Test
    public void testAdminLoginAndDashboard() {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        LoginPage loginPage = new LoginPage(driver);

        loginPage.login("Admin", "admin123");

        Assert.assertTrue(loginPage.isDashboardDisplayed(), "❌ Dashboard is NOT displayed after login.");

        loginPage.logout();
    }
}
