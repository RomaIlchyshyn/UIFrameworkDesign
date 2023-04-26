package rahulshettyacademy.AbstractComponents;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.rahulshetty.pages.CartPage;
import org.rahulshetty.pages.OrderPage;

import java.time.Duration;

public class AbstractComponent {

    @FindBy(css = "[routerlink*='cart']")
    WebElement cartHeader;

    @FindBy(css = ".btn[routerlink='/dashboard/myorders']")
    WebElement ordersButton;

    WebDriver driver;
    public AbstractComponent(WebDriver driver) {

        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void waitElementToAppear(By findBy) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
    }

    public CartPage goToCartPage() {

        cartHeader.click();
        CartPage cartPage = new CartPage(driver);
        return cartPage;
    }

    public void waitElementToDisappear(WebElement element) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.invisibilityOf(element));
    }
    public void  waitWebElementToAppear(WebElement findBy) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(findBy));
    }
    public OrderPage goToOrderPage() {
        ordersButton.click();
        OrderPage orderPage = new OrderPage(driver);
        return orderPage;
    }

}