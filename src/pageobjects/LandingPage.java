package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.NoSuchElementException;


public class LandingPage extends BaseWebPage {
    WebElement loginBtn;
    WebElement cookieAcceptBtn;

    public LandingPage(WebDriver driver) {
        super(driver);
        loginBtn = driver.findElement(By.cssSelector("a[class='ga_nav_link nav-utility-login']"));
        try {
            cookieAcceptBtn = driver.findElement(By.id("hs-eu-cookie-settings-button"));
        } catch (NoSuchElementException e) {
            System.out.println("cookie banner is not showing.");
        }
    }

    public void gotoLoginPage() {
        loginBtn.click();
    }

    public void closeCookieBanner() {
        if (cookieAcceptBtn != null) {
            cookieAcceptBtn.click();
        }
    }

}


//    By manageCookieBannerAcceptAllBtn = By.id("hs-eu-confirmation-button");
//    By manageCookieBannerDeclineAllBtn = By.id("hs-eu-decline-button");
//
//    By cookieSettingBtn = By.id("hs-eu-cookie-settings-button");
//    By byCloseCookieBannerBtn = By.cssSelector("button[aria-labelledby='hs-eu-cookie-close-button']");
//
//    By popUpCloseBtn = By.cssSelector("button[aria-label='Close live chat']");