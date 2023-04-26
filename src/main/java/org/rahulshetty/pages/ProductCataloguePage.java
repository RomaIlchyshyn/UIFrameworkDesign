package org.rahulshetty.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import rahulshettyacademy.AbstractComponents.AbstractComponent;

import java.util.List;

public class ProductCataloguePage extends AbstractComponent {

    WebDriver driver;

    public ProductCataloguePage(WebDriver driver) {

        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    @FindBy(className = "mb-3")
    List<WebElement> products;

    @FindBy(css = ".ng-animating")
    public
    WebElement spinner;

    By productsBy = By.cssSelector(".mb-3");
    By addToCart = By.cssSelector(".card-body button:last-of-type");
    By toastContainer = By.cssSelector("#toast-container");


    public List<WebElement> getProductList() {

        waitElementToAppear(productsBy);
        return products;
    }

    public WebElement getProductByName(String productName) {

        WebElement prod = getProductList().stream().filter(product ->
                product.findElement(By.cssSelector("b"))
                        .getText().equals(productName)).findFirst().orElse(null);
        return prod;

    }

    public void addProductToCart(String productName) {

        WebElement product = getProductByName(productName);
        product.findElement(addToCart).click();
        waitElementToAppear(toastContainer);
        waitElementToDisappear(spinner);
    }

    @Override
    public void waitElementToDisappear(WebElement element) {
        super.waitElementToDisappear(spinner);
    }

}
