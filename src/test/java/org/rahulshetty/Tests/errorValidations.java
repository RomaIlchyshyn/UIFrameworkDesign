package org.rahulshetty.Tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import rahulshetty.TestComponents.BaseTest;
import rahulshetty.TestComponents.Retry;


public class errorValidations extends BaseTest {
    
        @Test(retryAnalyzer = Retry.class)
        public void errorValidationTest() {
                landingPage.loginApp("barak_obama@gmail.com", "test123");
                Assert.assertEquals("Incorrect email or password.",landingPage.getErrorMessage());
    }
}

