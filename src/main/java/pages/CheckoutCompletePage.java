package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckoutCompletePage extends BasePage {

    private final By successMessageHeader = By.className("complete-header");

    public CheckoutCompletePage(WebDriver webDriver) {
        super(webDriver);
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("checkout_complete_container")));
    }

    public String getSuccessMessageHeader() {
        return webDriver.findElement(successMessageHeader).getText();
    }
}
