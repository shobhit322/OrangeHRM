package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class AdminPage {

    private WebDriver driver;

    // Constructor
    public AdminPage(WebDriver driver) {
        this.driver = driver;
    }

    // Locators
    private By leftMenuOptions = By.cssSelector(".oxd-sidepanel-body li a span");
    private By adminTab = By.xpath("//span[text()='Admin']");
    private By usernameSearchBox = By.xpath("//label[text()='Username']/../following-sibling::div//input");
    private By searchButton = By.xpath("//button[normalize-space()='Search']");
    private By resetButton = By.xpath("//button[normalize-space()='Reset']");
    private By userRoleDropdown = By.xpath("//label[text()='User Role']/../following-sibling::div//div[contains(@class,'dropdown')]");
    private By dropdownOptions = By.xpath("//div[@role='listbox']//span");
    private By statusDropdown = By.xpath("//label[text()='Status']/../following-sibling::div//div[contains(@class,'dropdown')]");
    private By resultRows = By.cssSelector(".oxd-table-card");

    // Actions

    public int getLeftMenuCount() {
        List<WebElement> menuItems = driver.findElements(leftMenuOptions);
        return menuItems.size();
    }

    public void clickAdminTab() {
        driver.findElement(adminTab).click();
    }

    public void searchByUsername(String username) {
        driver.findElement(usernameSearchBox).sendKeys(username);
        driver.findElement(searchButton).click();
    }

    public void searchByUserRole(String roleToSelect) throws InterruptedException {
        driver.findElement(userRoleDropdown).click();
        selectFromDropdown(dropdownOptions, roleToSelect);
        driver.findElement(searchButton).click();
    }

    public void searchByStatus(String status) throws InterruptedException {
        driver.findElement(statusDropdown).click();
        selectFromDropdown(dropdownOptions, status);
        driver.findElement(searchButton).click();
    }

    public void resetSearch() {
        driver.findElement(resetButton).click();
    }

    public int getSearchResultCount() {
        return driver.findElements(resultRows).size();
    }

    private void selectFromDropdown(By optionsLocator, String visibleText) throws InterruptedException {
        List<WebElement> options = driver.findElements(optionsLocator);
        for (WebElement option : options) {
            if (option.getText().trim().equalsIgnoreCase(visibleText)) {
                option.click();
                break;
            }
        }
        Thread.sleep(1000); // Optional: add wait if needed for dropdown to close
    }
}
