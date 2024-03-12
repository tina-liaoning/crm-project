package components;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BaseComponent {
    WebDriver driver;
    WebDriverWait webDriverWait;
    Actions myAction;
    JavascriptExecutor jse;

    public BaseComponent(WebDriver driver) {
        this.driver = driver;
        this.webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(15));
        myAction = new Actions(driver);
        jse = (JavascriptExecutor) driver;
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

    public WebElement waitElementAccessible(String cssSelectorString){
        WebElement currentEle = null;
        try {
            currentEle = webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(cssSelectorString)));

        } catch (TimeoutException e) {
            System.out.println("Time out, the element of "+cssSelectorString+" is not appear in the page.");
        }
        return currentEle;
    }

    public void sleepForSeconds(int second) {
        try {
            Thread.sleep(1000L * second);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }

}
