package org.rahulshetty.Tests;

import org.rahulshetty.pages.CartPage;
import org.rahulshetty.pages.CheckoutPage;
import org.rahulshetty.pages.ConfirmationPage;
import org.rahulshetty.pages.ProductCataloguePage;
import org.testng.Assert;
import org.testng.annotations.Test;
import rahulshetty.TestComponents.BaseTest;
import rahulshettyacademy.AbstractComponents.AbstractComponent;

import java.io.IOException;


public class ErrorValidations extends BaseTest {
    
        @Test
        public void submitOrderTest() throws IOException {
                ProductCataloguePage productCataloguePage = landingPage.loginApp("barak_obama@gmail.com", "test123");
                Assert.assertEquals("Incorrect email or password.",landingPage.getErrorMessage());
    }
}

