package pagefactory.pageobjects.loginmodule;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pagefactory.pageobjects.core.BasePage;


public class LandingPage extends BasePage {
    @FindBy(css = "a[class='ga_nav_link nav-utility-login']")
    protected WebElement loginBtn;
    @FindBy(id = "hs-eu-cookie-settings-button")
    protected WebElement acceptCookieBtn;
    @FindBy(id = "hs-eu-decline-button")
    protected WebElement declineCookieBtn;


    public LandingPage(WebDriver driver) {
        super(driver);
    }

    public void gotoLoginPage() {
        try {
            declineCookieBtn.click();
        } catch (NoSuchElementException e) {
            System.out.println("Accept Cookie is not showing");
        }
        loginBtn.click();

    }


}


//    By manageCookieBannerAcceptAllBtn = By.id("hs-eu-confirmation-button");
//    By manageCookieBannerDeclineAllBtn = By.id("hs-eu-decline-button");
//
//    By cookieSettingBtn = By.id("hs-eu-cookie-settings-button");
//    By byCloseCookieBannerBtn = By.cssSelector("button[aria-labelledby='hs-eu-cookie-close-button']");
//
//    By popUpCloseBtn = By.cssSelector("button[aria-label='Close live chat']");