package org.rahulshetty.Tests;

import com.google.auto.common.Visibility;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.rahulshetty.pages.LandingPage;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class StandAloneTest {

    public static void main(String[] args) {

        String productName = "ZARA COAT 3";

        ChromeOptions option = new ChromeOptions();
        option.addArguments("--remote-allow-origins=*");
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver(option);
        LandingPage landingPage = new LandingPage(driver);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://rahulshettyacademy.com/client");

        List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

        WebElement ZaraCoat = products.stream().filter(product ->
                product.findElement(By.cssSelector("b"))
                .getText().equals("ZARA COAT 3")).findFirst().orElse(null);

        ZaraCoat.findElement(By.cssSelector(".card-body button:last-of-type")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("#toast-container")));

        driver.findElement(By.xpath("//li/button[@routerlink='/dashboard/cart']")).click();

        List<WebElement> productsInCart = driver.findElements(By.cssSelector(".cartSection h3"));

        Assert.assertTrue(productsInCart.stream().anyMatch(cartProducts -> cartProducts.getText().equals(productName)));

        driver.findElement(By.cssSelector(".totalRow button")).click();

        Actions actions = new Actions(driver);
        actions.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")),"Ukraine").build().perform();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-item"))).click();
//        driver.findElement(By.className("ta-item")).click();

        driver.findElement(By.xpath("//div[contains(@class, 'actions')]/a")).click();

        String finalMessage = driver.findElement(By.cssSelector("h1")).getText();
        Assert.assertEquals(finalMessage, "THANKYOU FOR THE ORDER.");


    }
}
