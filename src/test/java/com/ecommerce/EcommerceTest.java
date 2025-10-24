package com.ecommerce;

import org.testng.annotations.Listeners;
import com.ecommerce.listeners.TestListener;
import com.ecommerce.pages.LoginPage;
import com.ecommerce.pages.ProductsPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

@Listeners(TestListener.class)
public class EcommerceTest {

    private WebDriver driver;
    LoginPage login;
    ProductsPage products;

    public WebDriver getDriver() { return driver; }

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        login = new LoginPage(driver);
        products = new ProductsPage(driver);
    }

    @Test(priority = 1)
    public void validLogin() {
        login.open();
        login.login("standard_user", "secret_sauce");
        Assert.assertTrue(products.getAllProducts().size() > 0);
    }

    @Test(priority = 2)
    public void invalidLogin() {
        login.open();
        login.login("wrongUser", "wrongPass");
        Assert.assertTrue(login.getError().contains("Epic sadface"));
    }

    @Test(priority = 3)
    public void addToCartTest() {
        login.open();
        login.login("standard_user", "secret_sauce");
        products.addProductByIndex(0);
        Assert.assertEquals(products.getCartCount(), "1");
    }

    @AfterClass
    public void teardown() {
        if (driver != null) driver.quit();
    }
}
