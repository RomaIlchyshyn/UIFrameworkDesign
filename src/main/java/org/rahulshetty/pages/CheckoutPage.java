package org.rahulshetty.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {

    WebDriver driver;

    @FindBy(css = "[placeholder='Select Country']")
    WebElement countryInputField;

    @FindBy(xpath = "//div[contains(@class, 'actions')]/a")
    WebElement placeOrderButton;

    By result = By.cssSelector(".ta-results");


    public CheckoutPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void countrySelection(String countryName) {

        Actions actions = new Actions(driver);
        actions.sendKeys(countryInputField, countryName).build().perform();
        waitElementToAppear(result);
        driver.findElement(By.cssSelector(".ta-item")).click();

    }

    public ConfirmationPage submitOrder() {

        placeOrderButton.click();
        ConfirmationPage confirmationPage = new ConfirmationPage(driver);
        return confirmationPage;
    }
}
