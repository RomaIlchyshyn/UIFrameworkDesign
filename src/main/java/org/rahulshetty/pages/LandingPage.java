package org.rahulshetty.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {

    WebDriver driver;

    public LandingPage(WebDriver driver) {

        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "userEmail")
    WebElement userName;

    @FindBy(id = "userPassword")
    WebElement userPassword;

    @FindBy(id = "login")
    WebElement loginButton;

    @FindBy(css = "[class*='flyInOut']")
    WebElement errorMessage;

    public void goTo() {
        driver.get("https://rahulshettyacademy.com/client");
    }

    public ProductCataloguePage loginApp(String email, String password) {
        userName.sendKeys(email);
        userPassword.sendKeys(password);
        loginButton.click();
        ProductCataloguePage productCataloguePage = new ProductCataloguePage(driver);
        return productCataloguePage;
    }

    public String getErrorMessage() {
        waitWebElementToAppear(errorMessage);
        return errorMessage.getText();
    }
}
