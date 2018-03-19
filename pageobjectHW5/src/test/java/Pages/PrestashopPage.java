package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PrestashopPage {
//    private EventFiringWebDriver driver;
    private WebDriver driver;

     private By allProducts=By.cssSelector("a[href*=\"2-home\"]");

    public PrestashopPage(WebDriver driver){
        this.driver=driver;
    }
    public void allProductLinkClick(){
        new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(allProducts));
        driver.findElement(allProducts).click();
    }
}
