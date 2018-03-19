package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class ProductPage {
    private WebDriver driver;

    private By name = By.xpath("//div [@class=\"col-md-6\"] / h1[(@class=\"h1\")]");
    private By moreAboutProduct=By.xpath("//a[@class=\"nav-link\"]");

    private By quantity = By.xpath("//div [@class=\"product-quantities\"]//span");
    private By price = By.xpath("//div [@class=\"current-price\"]/span[@itemprop=\"price\"]");
    private By toCartButton =By.xpath("//div [@class=\"add\"]//button [@type=\"submit\"]");
    private String[] productAttributes=new String[3];


    public ProductPage(WebDriver driver){
        this.driver=driver;
    }
    public void productName(){
        //System.out.println(driver.findElement(name).getText());
        productAttributes[0]=driver.findElement(name).getText().toUpperCase();
    }
    public void productPrice(){
        String priceP=driver.findElement(price).getText();
        System.out.println(priceP);
        productAttributes[2]=priceP.substring(0,priceP.indexOf(',')+3);
    }
    public void productQty(){
        driver.findElement(moreAboutProduct).click();
        new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOfElementLocated(quantity));
        String getQuantity=driver.findElement(quantity).getText();
        System.out.println(getQuantity.substring(0,getQuantity.indexOf(' ')));
        productAttributes[1]=getQuantity.substring(0,getQuantity.indexOf(' '));
    }

    public String[] getProductAttributes() {

        return productAttributes;
    }
    public void setProductAttributes(){
        productName();
        productPrice();
        productQty();

    }


    public void toCartButtonClick(){
        new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(toCartButton));
        driver.findElement(toCartButton).click();
    }
}
