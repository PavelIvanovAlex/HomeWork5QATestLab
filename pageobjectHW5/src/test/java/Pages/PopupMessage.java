package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class PopupMessage {
    private WebDriver driver;
    private By checkoutButton= By.xpath("//a [contains(@class,\"btn\") and contains(@class,\"btn-primary\")]");

    public PopupMessage(WebDriver driver){
        this.driver=driver;
    }

    public  void goToCheckoutClick(){
        new WebDriverWait(driver,20).until(ExpectedConditions.elementToBeClickable(checkoutButton));
        driver.findElement(checkoutButton).click();
    }

}
