package com.ecommerce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class ProductsPage extends BasePage {
    private By productTitles = By.cssSelector(".inventory_item_name");
    private By addToCartButtons = By.cssSelector("button.btn_inventory");
    private By cartBadge = By.cssSelector(".shopping_cart_badge");

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public List<WebElement> getAllProducts() {
        return driver.findElements(productTitles);
    }

    public void addProductByIndex(int index) {
        List<WebElement> buttons = driver.findElements(addToCartButtons);
        if (index < buttons.size()) buttons.get(index).click();
    }

    public String getCartCount() {
        return driver.findElement(cartBadge).getText();
    }
}
