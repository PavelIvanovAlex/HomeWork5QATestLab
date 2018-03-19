package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.SkipException;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public  abstract class BaseTest {
    public static WebDriver driver;

public  static WebDriver getDriver (String browser) {


    System.out.println("getDriver================"+browser);
        switch (browser)
        {
            case "chrome":
            case "GoogleChrome": {
                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/driver/chromedriver.exe");
                driver=new ChromeDriver();
                break;
            }
            case "FF":
            case "firefox": {
                System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/driver/geckodriver.exe");
                driver=new FirefoxDriver();
                break;
            }
            case "IE":
            case "InternetExplorer": {
                System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "/driver/IEDriverServer.exe");
                driver=new InternetExplorerDriver();
                break;
            }
            case "mobile": {
                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/driver/chromedriver.exe");
                Map<String,String> mobEmulation=new HashMap<>();
                mobEmulation.put("deviceName","iPhone 5/SE");
                ChromeOptions chromeOptions=new ChromeOptions();
                chromeOptions.setExperimentalOption("mobileEmulation",mobEmulation);
                driver=new ChromeDriver(chromeOptions);
                break;
            }

            default:
                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/driver/chromedriver.exe");
                driver=new ChromeDriver();
        }


        return driver;

    }
    public  static WebDriver getRemoteDriver (String browser) {


        System.out.println("getDriver================"+browser);
        switch (browser)
        {

            case "remote-mobile": {
                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/driver/chromedriver.exe");
                Map<String,String> mobEmulation=new HashMap<>();
                mobEmulation.put("deviceName","iPhone 5/SE");
                ChromeOptions options=new ChromeOptions();
                options.setExperimentalOption("mobileEmulation",mobEmulation);

                try{
                    return new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),options);
                }
                catch (Exception e){
                    e.printStackTrace();
                    throw  new SkipException("Unable to create RemoteWebDriver instance");
                }

            }
            case "remote-chrome":{
                ChromeOptions options=new ChromeOptions();
                try{
                    return new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),options);
                }
                catch (MalformedURLException e){
                    e.printStackTrace();
                }
            }
            case "remote-firefox":{
                FirefoxOptions options=new FirefoxOptions();
                try{
                    return new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),options);
                }
                catch (Exception e){
                    e.printStackTrace();
                    throw  new SkipException("Unable to create RemoteWebDriver instance");
                }
            }
            default:  return null;

        }



    }
    public static void quitDriver(WebDriver driver) {
        driver.quit();
    }
}
