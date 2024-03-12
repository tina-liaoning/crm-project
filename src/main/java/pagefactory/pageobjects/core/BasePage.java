package pagefactory.pageobjects.core;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.time.Duration;

public abstract class BasePage {

    protected WebDriver driver;
    protected WebDriverWait webDriverWait;
    protected Actions myAction;
    protected JavascriptExecutor jse;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.webDriverWait = new WebDriverWait(this.driver, Duration.ofSeconds(20));
        jse = (JavascriptExecutor) driver;
        myAction = new Actions(driver);
    }

    public void uploadFromWindows(String filePath) {
        try {
            System.out.println("Inside robot:" + filePath);
            Robot rbt = new Robot();
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            StringSelection stringSelection = new StringSelection(filePath);
            clipboard.setContents(stringSelection, null);
            sleepBySeconds(1);
            rbt.keyPress(KeyEvent.VK_CONTROL);
            rbt.keyPress(KeyEvent.VK_V);
            rbt.keyRelease(KeyEvent.VK_CONTROL);
            rbt.keyRelease(KeyEvent.VK_V);
            sleepBySeconds(1);
            rbt.keyPress(KeyEvent.VK_ENTER);
            rbt.keyRelease(KeyEvent.VK_ENTER);
            sleepBySeconds(1);
        } catch (AWTException e) {
            System.out.println("AWTException Exception when creat Robot object" + e.getMessage());
        }
    }


    public boolean waitElementClickable(WebElement element){
        try {
            webDriverWait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (TimeoutException e) {
            System.out.println("Time out, the element of "+element.getTagName()+" is not clickable in the page.");
            return false;
        }
        return true;
    }

    public void sleepBySeconds(int seconds){
        try {
            Thread.sleep(1000*seconds);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    public String getElementHeaderText(WebElement element) {
        try {
            return element.getText();
        } catch (NoSuchElementException e) {
             return "Error:contactPageHeader is null."; //todo
        }
    }

    public void clearInputValueByJS(WebElement ele){
        jse.executeScript("arguments[0].value = ''", ele);
    }

    public void clickByJS(WebElement element) {
        jse.executeScript("arguments[0].click()", element);
    }

    public boolean clickWithTryBlock(WebElement element) {
        try {
            element.click();
            return true;
        } catch (NoSuchElementException e) {
           return false;
        }
    }
}
