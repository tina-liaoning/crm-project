package ignoretestcases;

import enums.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Ignore;

import java.util.Arrays;
@Ignore
public class TestCookie {

    static WebDriver driver;

    public static void main(String[] args) {
        openBrowserAndHubSpotSite(Browser.CHROME); //.ga_nav_link.nav-utility-login
        WebElement cookingSettingPopupEle;
        try {
            if ((cookingSettingPopupEle = driver.findElement(By.cssSelector("button[aria-labelledby='hs-eu-cookie-close-button']"))).isDisplayed()) {
                cookingSettingPopupEle.click();
            }
        } catch (NoSuchElementException e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
        driver.findElement(By.cssSelector(".ga_nav_link.nav-utility-login")).click();
        if(driver.getTitle().equalsIgnoreCase("HubSpot test_senairios.Login")) {
            sleepWait(1000);
//            driver.findElement(By.id("username")).sendKeys("ajennifer7715+11@gmail.com");
            driver.findElement(By.cssSelector("#remember + span")).click();
            driver.findElement(By.id("current-password")).sendKeys("Ajennifer7715");
//            driver.findElement(By.cssSelector("div[data-test-id='remember-me']")).click();
//            driver.findElement(By.id("loginBtn")).click();
        }else {
            System.out.println("Can not redirect to test_senairios.Login Page.");
            return;
        }
        sleepWait(1000);

        driver.findElement(By.xpath("//*[text()='Log in']")).click();

//        sleepWait(1000);
//
//        driver.close();
    }

    private static void sleepWait(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }

    private static void openBrowserAndHubSpotSite(Browser browser) {
        String browserStr = browser.toString();
        System.out.println(browserStr);
        switch (browser.toString()) {
            case "CHROME":
                ChromeOptions cOptions = new ChromeOptions();
                System.setProperty("webdriver.chrome.driver", "D:\\QA-automation\\selenium\\driver\\chromedriver.exe");
                cOptions.setBinary("D:\\QA-automation\\selenium\\chrome-win64\\chrome.exe");
                cOptions.addArguments("start-maximized");
                cOptions.addArguments("--no-sandbox");
                cOptions.addArguments("user-data-dir=C:\\Users\\belle\\AppData\\Local\\Google\\Chrome for Testing\\User Data");
                cOptions.addArguments(("profile-directory=Default"));
                driver = new ChromeDriver(cOptions);
                break;
            case "FIREFOX":
                driver = new FirefoxDriver();
                break;
            case "EDGE":
                driver = new EdgeDriver();
                break;
        }
        driver.get("https://www.hubspot.com");

    }

//    public static void openBrowserAndWebsite(Browser browser, String url) {
//        String browserStr = browser.toString();
//        System.out.println(browserStr);
//
//        switch (browser.toString()) {
//            case "CHROME":
//             //   System.setProperty("Webdriver.chrome.driver", "D:\\QA-automation\\selenium\\driver\\chromedriver.exe");
//                ChromeOptions cOptions = new ChromeOptions();
//                cOptions.setBinary("D:\\QA-automation\\selenium\\chrome-win64\\chrome.exe");
//                cOptions.addArguments("start-maximized");
//                cOptions.addArguments("user-data-dir=C:\\Users\\belle\\AppData\\Local\\Google\\Chrome for Testing\\User Data");
//                driver = new ChromeDriver(cOptions);
//                driver = new ChromeDriver();
//                break;
//            case "FIREFOX":
//             //   System.setProperty("Webdriver.gecko.driver", "D:\\QA-automation\\selenium\\driver\\geckodriver.exe");
//                driver = new FirefoxDriver();
//                break;
//            case "EDGE":
//            //    System.setProperty("Webdriver.edge.driver", "D:\\QA-automation\\selenium\\driver\\msedgedriver.exe");
//                driver = new EdgeDriver();
//                break;
//        }
//        driver.manage().window().maximize();
//        driver.get(url);
//    }

}
