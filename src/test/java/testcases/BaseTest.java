package testcases;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pagefactory.pageobjects.loginmodule.LandingPage;
import pagefactory.pageobjects.loginmodule.LoginPage;

import java.util.HashMap;
import java.util.Map;

public abstract class BaseTest {
    protected static WebDriver driver;
    protected SoftAssert softAssert;

//    @BeforeTest
//    @Parameters({"testPlatform", "targetBrowser"})
//    public void setUpDriver(String testPlatform, String targetBrowser) {
//
//        System.out.println("Test platform:" + testPlatform);
//        if (testPlatform == null || testPlatform.isEmpty() || testPlatform.equalsIgnoreCase("local")) {
//            System.out.println("Current browser:" + targetBrowser);
//            String downloadPath = "C:\\Users\\belle\\Downloads\\hubspotFiles";
//            Map<String, Object> prefs = new HashMap<>();
//            prefs.put("download.default_directory", downloadPath);
//
//            ChromeOptions cops = new ChromeOptions();
//            cops.setExperimentalOption("prefs", prefs);
//            cops.setBinary("D:\\QA-automation\\selenium\\chrome-win64\\chrome.exe");
//            cops.addArguments("start-maximized");
//            cops.addArguments("user-data-dir=C:\\Users\\belle\\AppData\\Local\\Google\\Chrome for Testing\\User Data");
//
//
//            EdgeOptions edgeOptions = new EdgeOptions();
//            edgeOptions.setExperimentalOption("prefs", prefs);
//            edgeOptions.addArguments("start-maximized");
////        edgeOptions.addArguments("user-data-dir=C:\\Users\\belle\\AppData\\Local\\Microsoft\\Edge\\User Data");
////        edgeOptions.addArguments("profile-directory=Profile 1");
////        System.setProperty("webdriver.chrome.driver", "D:\\QA-automation\\selenium\\driver\\msedgedriver.exe");
//            //"D:\QA-automation\selenium\driver\msedgedriver.exe"
//
//            if (targetBrowser == null || targetBrowser.equalsIgnoreCase("chrome")) {
//                driver = new ChromeDriver(cops);
//            } else if (targetBrowser.equalsIgnoreCase("edge")) {
//                System.out.println("inside edge");
//                driver = new EdgeDriver(edgeOptions);
//            }
//        } else if (testPlatform.equalsIgnoreCase("lambda")) {
//            System.out.println("Inside lambda");
//            String username = "dannazodha";
//            String accesskey = "jvnFEBJHjT6HpRJ5YQO2fmIZtMxwAvVkU81McFqmI2xhjvDVLW";
//            String gridURL = "@hub.lambdatest.com/wd/hub";
//            DesiredCapabilities capabilities = new DesiredCapabilities();
//            capabilities.setCapability("browserName", "chrome");
//            capabilities.setCapability("version", "latest");
//            capabilities.setCapability("platform", "win11"); // If this cap isn't specified, it will just get the any available one
//            capabilities.setCapability("build", "Tina");
//            capabilities.setCapability("name", "HubspotLoginTestInChrome");
//
////            Map<String, Object> lambdaOptions = new HashMap<>();
////            lambdaOptions.put("username", "dannazodha");
////            lambdaOptions.put("accessKey", "jvnFEBJHjT6HpRJ5YQO2fmIZtMxwAvVkU81McFqmI2xhjvDVLW");
//
////            chromeOptions.setCapability("sauce:options", lambdaOptions);
//            try {
//                URL url = new URL("https://" + username + ":" + accesskey + gridURL);
//                System.out.println(url);
//                driver = new RemoteWebDriver(url, capabilities);
//            } catch (Exception e) {
//                System.out.println("Invalid grid URL");
//                System.out.println(e.getMessage());
//            }
//
//        } else if (testPlatform.equalsIgnoreCase("sauce")) {
//            long randomNum = Math.round(Math.random()*100);
//            System.out.println("inside Saucelab");
//            ChromeOptions chromeOptions = new ChromeOptions();
//            chromeOptions.setPlatformName("Windows 11");
//            chromeOptions.setBrowserVersion("latest");
//
//            Map<String, Object> sauceOptions = new HashMap<>();
//            sauceOptions.put("username", "dtina");
//            sauceOptions.put("accessKey", "a4dba342-4d0c-46c2-83bb-29b9639c51ac");
//            sauceOptions.put("strictFileInteractability", true);
//            sauceOptions.put("extendedDebugging", true);
////            sauceOptions.put("screenResolution","1280*1024");
//            sauceOptions.put("build", "Tina-"+randomNum+"-test-run");
//            sauceOptions.put("name", "Tina-test-"+randomNum);
//            chromeOptions.setCapability("sauce:options", sauceOptions);
//            try {
//                URL url = new URL("https://ondemand.us-west-1.saucelabs.com:443/wd/hub");
//                driver = new RemoteWebDriver(url, chromeOptions);
//            } catch (Exception e) {
//                System.out.println("Invalid grid URL");
//                System.out.println(e.getMessage());
//            }
//        }
//
//        driver.get("https://www.hubspot.com/");
//}


    @BeforeTest
    public void setUp(ITestContext context) {
        String downloadPath = "C:\\Users\\belle\\Downloads\\hubspotFiles";

        ChromeOptions cops = new ChromeOptions();

        Map<String, Object> prefs = new HashMap<>();
        prefs.put("download.default_directory", downloadPath);
        cops.setExperimentalOption("prefs", prefs);
        cops.setBinary("D:\\QA-automation\\selenium\\chrome-win64\\chrome.exe");
        cops.addArguments("start-maximized");
        cops.addArguments("user-data-dir=C:\\Users\\belle\\AppData\\Local\\Google\\Chrome for Testing\\User Data");
        driver = new ChromeDriver(cops);
        context.setAttribute("driver", driver);

    }

    @AfterMethod
    public void prepareForNextText(){
        sleepForSeconds(1);
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
//        driver.quit();
        }
    }

    public void login(){
        driver.get("https://www.hubspot.com/");
        LandingPage landingPage = PageFactory.initElements(driver, LandingPage.class);
        landingPage.gotoLoginPage();
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.login("ajennifer7715+11@gmail.com", "Ajennifer7715");
    }

    public void sleepForSeconds(int second) {
        try {
            Thread.sleep(1000L * second);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }

}
