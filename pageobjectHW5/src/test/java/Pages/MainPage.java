package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;
import java.util.Random;

public class MainPage {
    private WebDriver driver;

    private  By prodList=By.xpath("//div [@class=\"product-description\"]/h1 [contains(@class,\"h3\") and contains (@class,\"product-title\")]");

    public MainPage(WebDriver driver){
        this.driver=driver;
    }

    public WebElement getRandomProductLink() {
        List<WebElement> productList = driver.findElements(prodList);
        int size = productList.size();
        Random rnd = new Random();
        int randomProductNumber = rnd.nextInt(size);
        WebElement randomProduct = productList.get(randomProductNumber);
        System.out.println(randomProduct.getText());
        return randomProduct;
    }
    public void randomProductLinkClick() {
        getRandomProductLink().click();
    }
}
