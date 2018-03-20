import Pages.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import test.BaseTest;

import java.util.concurrent.TimeUnit;

public class MyTestNGTest extends BaseTest {
    private static WebDriver driver;
    private final String[] productData=new String[3];


    @BeforeTest
    @Parameters("browser")
    public  void initDriverTest(@Optional("chrome") String browser) {
        if(browser.startsWith("remote"))
            driver = getRemoteDriver(browser);
            else driver = getDriver(browser);

        driver.get("http://prestashop-automation.qatestlab.com.ua/");
        driver.manage().window().maximize();
        correctSiteVersion(browser);
    }
    public void correctSiteVersion(String browser){
        if (browser=="mobile") Assert.assertTrue(driver.findElements(By.className("div.mobile")).size()>0,"No mobile elements");
            else
            if (browser=="remote-mobile") Assert.assertTrue(driver.findElements(By.className("div.mobile")).size()>0,"No mobile elements");
                else Assert.assertTrue(driver.findElements(By.className("div.mobile")).size()==0,"Mobile elements in decktop version");

    }

    @Test
    public  void shopTest() {
        PrestashopPage shopMainPage=new PrestashopPage(driver);
        shopMainPage.allProductLinkClick();

        MainPage mainPage=new MainPage(driver);
        mainPage.randomProductLinkClick();

        ProductPage productPage=new ProductPage(driver);
        String productPageURL=driver.getCurrentUrl();
        productPage.setProductAttributes();
        this.productData[0]=productPage.getProductAttributes()[0];
        this.productData[1]=productPage.getProductAttributes()[1];
        this.productData[2]=productPage.getProductAttributes()[2];
        productPage.toCartButtonClick();

        PopupMessage popupMessage=new PopupMessage(driver);
        popupMessage.goToCheckoutClick();

        CartPage cartPage=new CartPage(driver);
        System.out.println(productData[0]+" "+productData[1]+" "+productData[2]);
        cartPage.productAttributesAssertion(productData);
        cartPage.orderingButtonClick();

        OrderPage orderPage=new OrderPage(driver);
        orderPage.inputFirstName("FirstName");
        orderPage.inputLastName("LastName");
        orderPage.inputEmail();
        orderPage.nextToAddressButtonClick();
        orderPage.inputAddress("Address");
        orderPage.inputPostcode("12345");
        orderPage.inputCity("City");
        orderPage.nextToDeliveryButtonClick();
        orderPage.nextToPaymentButtonClick();
        orderPage.approveConditionsCheck();
        orderPage.paymentOptionChoose();
        orderPage.orderButtonClick();

        OrderConfirmation orderConfirmation=new OrderConfirmation(driver);
        Assert.assertTrue(orderConfirmation.orderConfirmationLabelisPresent(),"Confirmation is absent");
        Assert.assertEquals(orderConfirmation.productListSize(),1,"Not only one item");
        Assert.assertEquals(orderConfirmation.getProductName().toUpperCase(),this.productData[0],"Product names do not mismatch");
        Assert.assertEquals(orderConfirmation.getProductPrice(),this.productData[2],"Product prices do not mismatch");

        driver.get(productPageURL);
        ProductPage productPage1=new ProductPage(driver);
        productPage.setProductAttributes();
        Assert.assertEquals(Integer.parseInt(productPage.getProductAttributes()[1])+1,Integer.parseInt(this.productData[1]),"Product quantity did not decrease by one");

    }
  @AfterTest
    public  void tearDown(){
      quitDriver(driver);
  }



}
