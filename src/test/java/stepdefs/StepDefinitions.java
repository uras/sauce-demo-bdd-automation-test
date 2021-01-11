package stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.After;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.BasePage;
import pages.CheckoutCompletePage;
import pages.CheckoutStepOnePage;
import pages.CheckoutStepTwoPage;
import pages.InventoryItemPage;
import pages.InventoryPage;
import pages.LoginPage;
import pages.ShoppingCartPage;

import static org.testng.AssertJUnit.assertEquals;

public class StepDefinitions {

    public WebDriver webDriver;
    public String firstItemName;
    public String inventoryItemText;


    @Given("I am on the Login Page of Sauce Demo")
    public void iAmOnTheLoginPageOfSauceDemo() {
        System.setProperty("webdriver.chrome.driver", "chromedriver");
        webDriver = new ChromeDriver();
        webDriver.get("https://www.saucedemo.com/");
    }

    @And("I login as buyer")
    public void iLoginAsBuyer() {
        final LoginPage loginPage = new LoginPage(webDriver);
        loginPage.login("standard_user", "secret_sauce");
    }

    @When("I add first item to the shopping cart")
    public void iAddFirstItemToTheShoppingCart() {
        InventoryPage inventoryPage = new InventoryPage(webDriver);
        firstItemName = inventoryPage.getFirstItemName();
        InventoryItemPage inventoryItemPage = inventoryPage.selectFirstItem();
        inventoryItemPage.addToCart();
        ShoppingCartPage shoppingCartPage = inventoryItemPage.proceedToShoppingCart();
        inventoryItemText = shoppingCartPage.getInventoryItemTexts().get(0);

    }

    @And("I check the item is correct")
    public void iCheckTheItemIsCorrect() {
        assertEquals(firstItemName, inventoryItemText);
    }

    @When("I proceed to checkout")
    public void iProceedToCheckout() {
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(webDriver);
        CheckoutStepOnePage checkoutStepOnePage = shoppingCartPage.goToCheckout();
        checkoutStepOnePage.gotoStepTwo();
    }

    @And("I complete checkout")
    public void iCompleteCheckout() {
        CheckoutStepTwoPage checkoutStepTwoPage = new CheckoutStepTwoPage(webDriver);
        checkoutStepTwoPage.gotoStepTwo();
    }

    @Then("I should be taken to Complete Checkout Page")
    public void iShouldBeTakenToCompleteCheckoutPage() {
        final CheckoutCompletePage checkoutCompletePage = new CheckoutCompletePage(webDriver);
        assertEquals("THANK YOU FOR YOUR ORDER", checkoutCompletePage.getSuccessMessageHeader());

    }

    @And("I logout")
    public void iLogout() {
        BasePage basePage = new BasePage(webDriver);
        basePage.logout();
    }

    @After
    public void tearDown() {
        webDriver.quit();
    }
}
