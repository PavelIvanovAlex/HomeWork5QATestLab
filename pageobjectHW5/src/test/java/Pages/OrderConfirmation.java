package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Assert;

import java.util.List;

public class OrderConfirmation {
    private WebDriver driver;
    private By orderConfirmationLabel= By.xpath("//div [@class=\"col-md-12\"] // h3 [ contains(@class,\"h1\") and contains(@class,\"card-title\")]");
    private By prodItems=By.xpath("//div [contains(@class, \"order-line\") and contains(@class,\"row\")]");
    private By prodName=By.xpath("//div [contains(@class, \"col-sm-4\") and contains(@class,\"col-xs-9\") and contains(@class,\"details\")]// span");
    private  By prodPice=By.xpath("//div [contains(@class, \"col-xs-5\") and contains(@class,\"text-sm-right\") and contains(@class,\"text-xs-left\")]");

    public OrderConfirmation(WebDriver driver){
        this.driver=driver;
    }
    public boolean orderConfirmationLabelisPresent() {
        boolean present;
        try {
            driver.findElement(orderConfirmationLabel);
            present = true;
        } catch (NoSuchElementException e) {
            present = false;
        }
        return present;
    }
    public int productListSize(){
        List<WebElement> cartList=driver.findElements(prodItems);
       return cartList.size();
    }
    public String getProductName(){
        String productName=driver.findElement(prodName).getText();
        productName=productName.substring(0,productName.indexOf("Size")-3);
        return productName;
    }
    public String getProductPrice(){
        String productPrice=driver.findElement(prodPice).getText();
        productPrice=productPrice.substring(0,productPrice.length()-2);
        return productPrice;
    }

}
