package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class InventoryPage extends BasePage {

    private final By itemList = By.cssSelector(".inventory_item_name");

    public InventoryPage(WebDriver webDriver) {
        super(webDriver);
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".inventory_list")));
    }

    public InventoryItemPage selectFirstItem() {
        itemList.findElements(webDriver)
                .get(0)
                .click();
        return new InventoryItemPage(webDriver);
    }

    public String getFirstItemName() {
        return itemList.findElements(webDriver).get(0).getText();
    }
}
