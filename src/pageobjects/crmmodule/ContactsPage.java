package pageobjects.crmmodule;

import components.TopMenuToolBar;
import enums.ContactBy;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import components.LeftMenu;
import pageobjects.BaseWebPage;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ContactsPage extends BaseWebPage {
    protected LeftMenu leftMenu;
    protected TopMenuToolBar topMenuToolBar;
    protected WebElement howToAddContactsPopUpCloseBtn;
    protected WebElement createContactBtn;
    protected WebElement firstCreateContactPopUpHeader;
    protected WebElement firstCreateContactPopUpCloseBtn;
    protected WebElement helpMeGetStartBtn;
    protected WebElement newContactEmailInput;
    protected WebElement newContactLastnameInput;
    protected WebElement newContactFirstnameInput;
    protected WebElement createNewContactBtn;
    protected WebElement createNewContactFormHeader;
    protected WebElement searchContactInput;
    protected WebDriver iframeDriver;
    protected WebElement newlyContactEmailField;
    protected WebElement contactSearchInput;
    protected List<WebElement> searchContactsResultList;
    protected WebElement contactPageHeader;
    private JavascriptExecutor jse = (JavascriptExecutor) driver;
    private Actions ac = new Actions(driver);

    public ContactsPage(WebDriver driver) {
        super(driver);
        createContactBtn = webDriverWait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("button[data-test-id='new-object-button']")));
        contactSearchInput = (WebElement)jse.executeScript("return document.querySelector(\"input[data-test-id='index-page-search']\")");
    }

    public String getPageHeaderText() {
        return contactPageHeader != null ? contactPageHeader.getText() : "Error:contactPageHeader is null.";
    }

    public void searchContact(String data) {
        contactSearchInput.sendKeys(data);
    }


    public List<String> getContactSearchResult(ContactBy emailOrNameOrPhone) {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        switch (emailOrNameOrPhone) {
            case ContactBy.NAME:
                searchContactsResultList = driver.findElements(By.cssSelector("tbody > tr >td:nth-child(2)"));
                break;
            case ContactBy.EMAIL:
                searchContactsResultList = driver.findElements(By.cssSelector("tbody > tr >td:nth-child(3) a > span"));
                break;
            case ContactBy.PHONE:
                searchContactsResultList = driver.findElements(By.cssSelector("tbody > tr >td:nth-child(4) div[data-test-id='truncated-object-label'] > span"));
                break;
        }
        List<String> searchResultStrList = new ArrayList<>();
        for (int i = 0; i < searchContactsResultList.size(); i++) {
            searchResultStrList.add(searchContactsResultList.get(i).getText());
        }
        return searchResultStrList;
    }

    public boolean isFirstTimeCreateContact() {
        try {
            createContactBtn.click();
        } catch (ElementClickInterceptedException e) {
            System.out.println("Create contact button 'ClickInterceptedException'");
        }
//        System.out.println("Is h2 first create header exist?:" + isElementExist("h2.Heading-sc-19w6eq2-0.H2-sc-2bgr4q-0.eXrpwQ"));
        if (isElementExist("h2.Heading-sc-19w6eq2-0.H2-sc-2bgr4q-0.eXrpwQ")) {
            firstCreateContactPopUpHeader = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("h2.Heading-sc-19w6eq2-0.H2-sc-2bgr4q-0.eXrpwQ")));
            System.out.println("First contact popup:" + firstCreateContactPopUpHeader.getText());
            helpMeGetStartBtn = driver.findElement(By.cssSelector("button[data-test-id='deal-empty-state-guided-button']"));
            helpMeGetStartBtn.click();
            return true;
        } else {
            return false;
        }
    }

    public String getFirstTimeCreateContactPopupHeaderText() {
        return firstCreateContactPopUpHeader.getText();
    }

    public void openCreateContactFormePopUp() {
        try {
            createContactBtn.click();
        } catch (ElementClickInterceptedException e) {
            System.out.println("create_Contact_Btn ElementClickInterceptedException:" + e.getLocalizedMessage());
        }
        iframeDriver = driver.switchTo().frame("object-builder-ui");
        createNewContactFormHeader = iframeDriver.findElement(By.cssSelector(".private-modal__header__inner > h2"));
        newContactEmailInput = iframeDriver.findElement(By.cssSelector("input[data-test-id='email-input']"));
        newContactFirstnameInput = iframeDriver.findElement(By.cssSelector("textarea[data-test-id='firstname-input']"));
        newContactLastnameInput = iframeDriver.findElement(By.cssSelector("textarea[data-test-id='lastname-input']"));
        createNewContactBtn = iframeDriver.findElement(By.cssSelector("button[data-test-id='create-button'] > span"));

    }

    public String getCreateContactPopupHeaderText() {
        String contactFormHeaderText = "";
        if (createNewContactFormHeader != null) {
            contactFormHeaderText = createNewContactFormHeader.getText();
        }
        return contactFormHeaderText;
    }

    public void createNewContact(String email, String firstName, String lastName) {
        newContactFirstnameInput.sendKeys(firstName);
        newContactLastnameInput.sendKeys(lastName);
        newContactEmailInput.sendKeys(email);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
//        System.out.println(waitElementClickable("button[data-test-id='create-button']"));

        if (waitElementClickable("button[data-test-id='create-button'] > span")) {

            JavascriptExecutor jse = (JavascriptExecutor) iframeDriver;
            jse.executeScript("arguments[0].click()",createNewContactBtn);
            iframeDriver.switchTo().defaultContent();
        } else {
            System.out.println("Error: Create button is unclickable right now.");
        }
    }

    public void closeHowToAddContactsPopUp() {
        try {
            howToAddContactsPopUpCloseBtn = driver.findElement(By.cssSelector("div[data-test-id='data-location-modal-close-button']"));
            howToAddContactsPopUpCloseBtn.click();
        } catch (NoSuchElementException e) {
            System.out.println("How To Add Contacts Pop-up page doesn't show.");
        }
    }

    public String getCreateNewContactFormHeaderText() {
        return (createNewContactFormHeader != null) ? createNewContactFormHeader.getText() : "";
    }
//tbody > tr:first-child > td:nth-child(3)

    public String getNewlyContactEmailFromContactsTable() {
        if (waitElementVisible("tbody > tr:first-child > td:nth-child(3)")) {
            newlyContactEmailField = driver.findElement(By.cssSelector("tbody > tr:first-child > td:nth-child(3) a > span"));
        }
        return newlyContactEmailField != null ? newlyContactEmailField.getText() : "";
    }

}
