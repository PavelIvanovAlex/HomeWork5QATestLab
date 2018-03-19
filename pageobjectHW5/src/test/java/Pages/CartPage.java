package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

public class CartPage {
    private WebDriver driver;
    private By cartItems=By.xpath("//li [@class=\"cart-item\"]");
    private By pName=By.xpath("//div [@class=\"product-line-info\"]//a [@class=\"label\"]");
    private By pPrice=By.xpath("//div [@class=\"product-line-info\"]//span [@class=\"value\"]");
    private By orderingButton=By.xpath("//div [@class=\"text-xs-center\"] // a [contains(@class,\"btn\") and contains(@class,\"btn-primary\")]");

    public CartPage(WebDriver driver){
        this.driver=driver;
    }
    public  void productAttributesAssertion(String [] attributes){
        new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOfElementLocated(cartItems));
        List<WebElement> cartList=driver.findElements(cartItems);
        Assert.assertEquals(cartList.size(),1,"Not only one item in the cart");
        String productName=driver.findElement(pName).getText();
        String productPrice=driver.findElement(pPrice).getText();
        productPrice=productPrice.substring(0,productPrice.indexOf(' '));
        Assert.assertEquals(productName.toUpperCase(),attributes[0],"Product names are not equals");
        Assert.assertEquals(productPrice,attributes[2],"Product prices are not equals");
    }
    public void orderingButtonClick(){
        driver.findElement(orderingButton).click();
    }
}
