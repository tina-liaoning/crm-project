package pageobjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BaseWebPage {
    protected WebDriver driver;
    protected WebDriverWait webDriverWait;

    public BaseWebPage(WebDriver driver) {
        this.driver = driver;
        this.webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean isElementExist(String cssSelectorString) {
        try {
            driver.findElement(By.cssSelector(cssSelectorString));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isElementsListExist(String cssSelectorString) {
        try {
            driver.findElements(By.cssSelector(cssSelectorString));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean waitElementAccessible(String cssSelectorString){
        try {
            webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(cssSelectorString)));

        } catch (TimeoutException e) {
            System.out.println("Time out, the element of "+cssSelectorString+" is not appear in the page.");
            return false;
        }
        return true;
    }

    public boolean waitElementVisible(String cssSelectorString){
        try {
            webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(cssSelectorString)));
        } catch (TimeoutException e) {
            System.out.println("Time out, the element of "+cssSelectorString+" is not appear in the page.");
            return false;
        }
        return true;
    }

    public boolean waitElementClickable(String cssSelectorString){
        try {
            webDriverWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(cssSelectorString)));
        } catch (TimeoutException e) {
            System.out.println("Time out, the element of "+cssSelectorString+" is not cl in the page.");
            return false;
        }
        return true;
    }

}
