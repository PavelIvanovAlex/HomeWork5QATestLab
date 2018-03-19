package Pages;

import com.sun.javafx.image.ByteToIntPixelConverter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.Random;

public class OrderPage {
    private WebDriver driver;
    private By firstName= By.xpath("//input [@name=\"firstname\"]");
    private By lastName= By.xpath("//input [@name=\"lastname\"]");
    private By email=By.name("email");
    private By nextToAddressButton=By.xpath("//button [@name=\"continue\"]");
    private By address=By.xpath("//input [@name=\"address1\"]");
    private By city=By.xpath("//input [@name=\"city\"]");
    private By postcode=By.xpath("//input [@name=\"postcode\"]");
    private By nextToDeliveryButton=By.xpath("// button [@name=\"confirm-addresses\"]");
    private By nextToPaymentButton=By.xpath("// button [@name=\"confirmDeliveryOption\"]");
    private By paymentOption=By.xpath("// input [@id=\"payment-option-1\"]");
    private By approveConditions=By.xpath("// input [@id=\"conditions_to_approve[terms-and-conditions]\"]");
    private By orderButton=By.xpath("//div [@class=\"ps-shown-by-js\"]// button [@type=\"submit\"]");

    public OrderPage(WebDriver driver){
        this.driver=driver;
    }

    public void inputFirstName(String firstName){
        driver.findElement(this.firstName).sendKeys(firstName);
    }
    public void inputLastName(String lastName){
        driver.findElement(this.lastName).sendKeys(lastName);
    }
    public void inputEmail(){
        Random rnd=new Random();
        driver.findElement(email).sendKeys("email"+rnd.nextInt(1000)+"@qatest.com");
    }
    public void nextToAddressButtonClick(){
        driver.findElement(nextToAddressButton).click();
    }
    public void inputAddress(String address){
        driver.findElement(this.address).sendKeys(address);
    }
    public void inputCity(String city){
        driver.findElement(this.city).sendKeys(city);
    }
    public void inputPostcode(String postcode){
        driver.findElement(this.postcode).sendKeys(postcode);
    }
    public void nextToDeliveryButtonClick(){
        driver.findElement(nextToDeliveryButton).click();
    }
    public void nextToPaymentButtonClick(){
        driver.findElement(nextToPaymentButton).click();
    }
    public void paymentOptionChoose(){
        driver.findElement(paymentOption).click();
    }
    public void approveConditionsCheck(){
        driver.findElement(approveConditions).click();
    }
    public void orderButtonClick(){
        driver.findElement(orderButton).click();
    }


}
