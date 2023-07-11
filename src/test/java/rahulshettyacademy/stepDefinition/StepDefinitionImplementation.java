package rahulshettyacademy.stepDefinition;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.rahulshetty.pages.*;
import org.testng.Assert;
import rahulshetty.TestComponents.BaseTest;
import rahulshettyacademy.AbstractComponents.AbstractComponent;

import java.io.IOException;
import java.util.List;

public class StepDefinitionImplementation extends BaseTest {

    public LandingPage landingPage;
    public ProductCataloguePage productCataloguePage;
    public ConfirmationPage confirmationPage;

    @Given("I landed on Ecommerce web site")
    public void I_landed_on_Ecommerce_web_site() throws IOException {

        landingPage = launchApplication();
    }
    @Given("^Logged in with username (.+) and password (.+)$")
    public void logged_in_username_and_password(String username, String password) {

        productCataloguePage = landingPage.loginApp(username, password);
    }
    @When("^I add product (.+) from cart$")
    public void i_add_product_from_cart(String productname) {
        List<WebElement> products = productCataloguePage.getProductList();
        productCataloguePage.addProductToCart(productname);
    }
    @And("^Checkout (.+) and submit order$")
    public void checkout_and_submit_order(String productname) {
        AbstractComponent abstractComponent = new AbstractComponent(driver);
        abstractComponent.waitElementToDisappear(productCataloguePage.spinner);
        CartPage cartPage = productCataloguePage.goToCartPage();

        Boolean match = cartPage.verifyProductDisplay(productname);
        Assert.assertTrue(match);
        CheckoutPage checkoutPage = cartPage.goToCheckout();
        checkoutPage.countrySelection("Ukraine");

        confirmationPage = checkoutPage.submitOrder();
    }
    @Then("{string} message is displayed on ConfirmationPage")
    public void something_message_is_displayed_on_confirmationpage(String string) {
        String confirmMessage = confirmationPage.getMessage();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
    }
    @Then("^\"([^\"]*)\" message is displayed$")
    public void something_message_is_displayed(String string) {
        Assert.assertEquals(string, landingPage.getErrorMessage());
    }


}
