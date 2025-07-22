package tests;

import base.BaseTest;
import pages.LoginPage;
import utils.ExcelUtils;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test(dataProvider = "loginData")
    public void loginTest(String username, String password) {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);

        if (loginPage.isLoginSuccessful()) {
            Assert.assertTrue(true, "✅ Login successful for user: " + username);
            loginPage.logout();
        } else {
            Assert.assertTrue(loginPage.isLoginErrorDisplayed(), "❌ Login failed, but error message not shown.");
        }
    }

    @DataProvider(name = "loginData")
    public Object[][] loginDataProvider() {
        try {
            String excelPath = System.getProperty("user.dir") + "/test-data/loginData.xlsx";
            ExcelUtils.setExcelFile(excelPath, "LoginData");
            int rowCount = ExcelUtils.getRowCount();

            Object[][] data = new Object[rowCount - 1][2];
            for (int i = 1; i < rowCount; i++) {
                data[i - 1][0] = ExcelUtils.getCellData(i, 0); // Username
                data[i - 1][1] = ExcelUtils.getCellData(i, 1); // Password
            }
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            return new Object[0][0];
        }
    }
}
