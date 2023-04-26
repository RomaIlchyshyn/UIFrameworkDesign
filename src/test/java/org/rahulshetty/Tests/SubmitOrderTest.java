package org.rahulshetty.Tests;

import org.rahulshetty.pages.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import rahulshetty.TestComponents.BaseTest;
import rahulshettyacademy.AbstractComponents.AbstractComponent;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;


public class SubmitOrderTest extends BaseTest {
    String productName = "ZARA COAT 3";

        @Test(dataProvider = "getData")
        public void submitOrderTest(HashMap<String, String> input) throws IOException {

        ProductCataloguePage productCataloguePage = landingPage.loginApp(input.get("email"),input.get("password"));

        productCataloguePage.addProductToCart(input.get("product"));

        AbstractComponent abstractComponent = new AbstractComponent(driver);
        abstractComponent.waitElementToDisappear(productCataloguePage.spinner);

        CartPage cartPage = productCataloguePage.goToCartPage();

        Boolean match = cartPage.verifyProductDisplay(input.get("product"));
        Assert.assertTrue(match);
        CheckoutPage checkoutPage = cartPage.goToCheckout();
        checkoutPage.countrySelection("Ukraine");

        ConfirmationPage confirmationPage = checkoutPage.submitOrder();
        String confirmationMessage = confirmationPage.getMessage();
        Assert.assertTrue(confirmationMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

    }

    @Test(dependsOnMethods = {"submitOrderTest"})
    public void orderHistoryTest() {
            ProductCataloguePage productCataloguePage = landingPage.loginApp("barak_obama@gmail.com", "Barak123!");
            OrderPage orderPage = productCataloguePage.goToOrderPage();
            Assert.assertTrue(orderPage.verifyOrderDisplay(productName));
    }

    @DataProvider
    public Object[][] getData() throws IOException {
            List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//rahulshetty//data//PurchaseOrder.json");
            return new Object[][] {{data.get(0)}, {data.get(1)}};
    }
}

