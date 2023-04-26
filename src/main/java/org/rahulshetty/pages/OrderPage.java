package org.rahulshetty.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import rahulshettyacademy.AbstractComponents.AbstractComponent;

import java.util.List;

public class OrderPage extends AbstractComponent {

    @FindBy(css = "tr td:nth-child(3)")
    private List<WebElement> orderList;
WebDriver driver;
    public OrderPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public Boolean verifyOrderDisplay(String productName) {
        Boolean match = orderList.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));
        return match;
    }
}
