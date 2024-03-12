package pagefactory.pageobjects.crmmodule;

import enums.ContactBy;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pagefactory.pageobjects.core.BaseCRMAppPage;


import java.util.ArrayList;
import java.util.List;


public class ContactsPage extends BaseCRMAppPage {
    @FindBy(css = "div[data-test-id='data-location-modal-close-button']")
    protected WebElement howToAddContactsPopUpCloseBtn;
    @FindBy(css = "button[data-test-id='new-object-button'] > span >span")
    protected WebElement createContactBtn;
    @FindBy(css = "h2.Heading-sc-19w6eq2-0.H2-sc-2bgr4q-0.eXrpwQ")
    protected WebElement firstCreateContactPopUpHeader;
    @FindBy(css = "button[data-test-id='deal-empty-state-guided-button']")
    protected WebElement helpMeGetStartBtn;
    @FindBy(css = "input[data-test-id='email-input']")
    protected WebElement newContactEmailInput;
    @FindBy(css = "textarea[data-test-id='lastname-input']")
    protected WebElement newContactLastnameInput;
    @FindBy(css = "textarea[data-test-id='firstname-input']")
    protected WebElement newContactFirstnameInput;
    @FindBy(css = "button[data-test-id='create-button'] > span")
    protected WebElement createBtnOnCreateContactForm;
    @FindBy(css = ".private-modal__header__inner > h2")
    protected WebElement createNewContactFormHeader;
    @FindBy(css = "tbody > tr:first-child > td:nth-child(3) a > span")
    protected WebElement newlyContactEmailField;
    @FindBy(css = "input[data-test-id='index-page-search']")
    protected WebElement contactSearchInput;
    @FindBy(css = "tbody")
    protected WebElement contactsTable;
    @FindBy(css = "tbody > tr >td:nth-child(2)")
    protected List<WebElement> searchContactsResultNameList;
    @FindBy(css = "tbody > tr >td:nth-child(3) a > span")
    protected List<WebElement> searchContactsResultEmailList;
    @FindBy(css = "tbody > tr >td:nth-child(4) div[data-test-id='truncated-object-label'] > span")
    protected List<WebElement> searchContactsResultPhoneList;
    @FindBy(css = "tbody > tr >td:nth-child(4)")
    protected WebElement phoneNumberTd;
    @FindBy(css = "div[data-test-id='country-select']")
    protected WebElement countrySelectDropdown;
    @FindBy(css = "input[type='tel']")
    protected WebElement phoneNumberInputWithFormat;
    @FindBy(css = "input[class='form-control private-form__control']")
    protected WebElement phoneNumberInputNonFormat;
    @FindBy(css = "input[aria-autocomplete='list']")
    protected WebElement phoneAreaCountrySearchInput;
    @FindBy(css = "button[data-selenium-test='toggle-input-type-button']")
    //button[data-selenium-test="toggle-input-type-button"
    protected WebElement changePhoneNumFormat;
    @FindBy(css = "button[data-button-use='tertiary'] > i18n-string")
    protected WebElement phoneApplyBtn;
    @FindBy(css = "div[aria-label='Close']")
    private WebElement closeEditPhoneBtn;
    @FindBy(css = "button[data-selenium-test='inline-editing-savebar-save']")
    protected WebElement saveUpdateBtn;
    @FindBy(css = "tbody > tr:first-child input[aria-label='Select row'] + span")
    protected WebElement tableFirstRowCheckBox;
    @FindBy(css = "button[data-selenium-test='bulk-action-delete']")
    protected WebElement bulkDeleteBtn;
    @FindBy(css = "textarea[data-test-id='delete-dialog-match']")
    protected WebElement recordNumToDeleteInput;
    @FindBy(css = "button[data-test-id='delete-dialog-confirm-button']")
    protected WebElement deleteConfirmBtn;
    @FindBy(css = "span[class='private-dropdown__item__label']")
    protected WebElement contactPageHeader;




    public ContactsPage(WebDriver driver) {
        super(driver);
    }

    public String getContactPageHeaderText() {
        return getElementHeaderText(contactPageHeader);
    }

    /*todo: challenge for searchContact using dataprovider
    todo:   challenge reason: pass three times value into search_input , the issue comes in second round ,
            my solution is clear the input before sendkey into it for searching:
            but--->
            after "contactSearchInput.clear()" I get "contactSearchInput.getAttribute("value")" return empty
            and the "contactSearchInput.getDomAttibute("value")" is not clear, I get previous round input value
            so when I sendkey(newKeyword) , in the input it always show "first round keyword + second round keywords"
            I use javascript to clear then this problem is fixed.


        System.out.println("get attribute value after clear" + contactSearchInput.getAttribute("value"));
        System.out.println("keywords before send:" + searchStr);
        System.out.println("get dom attribute value after clear:" + contactSearchInput.getDomAttribute("value"));
        //jse.executeScript("arguments[0].value = ''", contactSearchInput);
        System.out.println("2nd get dom attribute value after clear:" + contactSearchInput.getDomAttribute("value"));
        contactSearchInput.sendKeys(searchStr);
        System.out.println("value after sendkey:" + contactSearchInput.getAttribute("value"));
     */

    public void searchContact(String searchStr) {
        sleepBySeconds(1);
        clearInputValueByJS(contactSearchInput);
        contactSearchInput.sendKeys(searchStr);
    }


    public List<String> getContactResultAfterSearching(ContactBy emailOrNameOrPhone) {
        List<String> searchResultStrList = new ArrayList<>();
        sleepBySeconds(3);
        switch (emailOrNameOrPhone) {
            case NAME:
                System.out.println("contact name list size after searching::" + searchContactsResultNameList.size());
                searchResultStrList = helpLoopElementsList(searchContactsResultNameList);
                break;
            case EMAIL:
                System.out.println("contact email list size after searching::" + searchContactsResultNameList.size());
                searchResultStrList = helpLoopElementsList(searchContactsResultEmailList);
                break;
            case PHONE:
                System.out.println("contact phone list size after searching::" + searchContactsResultNameList.size());
                searchResultStrList = helpLoopPhoneNumberList(searchContactsResultPhoneList);
                break;
        }
        return searchResultStrList;
    }

    public void editContactPhoneNumber(String countryName, String newPhoneNumber) {
        getContactResultAfterSearching(ContactBy.EMAIL);
        if (searchContactsResultEmailList.size() == 1) {
            phoneNumberTd.click();
            //this if condition for: when current phone-number-edit filed is not format mode,need to change back to format mode,
            //otherwise cannot continue next step, which is select country area code

            try {
                phoneNumberInputNonFormat.clear();
            } catch (NoSuchElementException e) {
               //todo
            }

            webDriverWait.until(ExpectedConditions.elementToBeClickable(changePhoneNumFormat));
            if (changePhoneNumFormat.getText().contains("Apply number formatting")) {
                System.out.println("inside Apply number formatting ");
                changePhoneNumFormat.click();
            }

            phoneNumberInputWithFormat.clear();

            sleepBySeconds(1);
            countrySelectDropdown.click();
            phoneAreaCountrySearchInput.sendKeys("cana");
            myAction.keyDown(Keys.ENTER).keyUp(Keys.ENTER).build().perform();

            phoneNumberInputWithFormat.sendKeys(newPhoneNumber);

            sleepBySeconds(1);
            webDriverWait.until(ExpectedConditions.elementToBeClickable(phoneApplyBtn));
            phoneApplyBtn.click();

            sleepBySeconds(1);
            webDriverWait.until(ExpectedConditions.elementToBeClickable(saveUpdateBtn));
            saveUpdateBtn.click();
        }
    }

    public String getPhoneNumberFromFirstRow() {
        phoneNumberTd.click();

        webDriverWait.until(ExpectedConditions.elementToBeClickable(changePhoneNumFormat));
        changePhoneNumFormat.click();

        sleepBySeconds(1);
        String text = phoneNumberInputNonFormat.getAttribute("value");
        closeEditPhoneBtn.click();
        return text;

    }

    public void delectFirstRowContactByBulkToolBar() {
        tableFirstRowCheckBox.click();
        bulkDeleteBtn.click();
        recordNumToDeleteInput.sendKeys("1");
        webDriverWait.until(ExpectedConditions.elementToBeClickable(deleteConfirmBtn));
        deleteConfirmBtn.click();
    }

    public List<String> getAllEmailListFromTableView() {
        return helpLoopElementsList(searchContactsResultEmailList);
    }

    public boolean isFirstTimeCreateContact() {
        try {
            createContactBtn.click();
        } catch (ElementClickInterceptedException e) {
            System.out.println("Create contact button 'ClickInterceptedException'");
        }
//        System.out.println("Is h2 first create header exist?:" + isElementExist("h2.Heading-sc-19w6eq2-0.H2-sc-2bgr4q-0.eXrpwQ"));
        try {
            if (!firstCreateContactPopUpHeader.getText().isEmpty())
                helpMeGetStartBtn.click();
            return true;
        } catch (NoSuchElementException e) {
            System.out.println(" It's not the first time create contact,so the First-Contact-PopUp doesn't show.");
            return false;
        }

    }

    public String getFirstTimeCreateContactPopupHeaderText() {
        try {
            return firstCreateContactPopUpHeader.getText();
        } catch (NoSuchElementException e) {
            System.out.println("firstCreateContactPopUpHeader:" + e.getMessage());
            return "Error:firstCreateContactPopUpHeader is null";
        }
    }

    public void openCreateContactFormePopUp() {
        try {
            createContactBtn.click();
        } catch (ElementClickInterceptedException e) {
            System.out.println("create_Contact_Btn ElementClickInterceptedException:" + e.getLocalizedMessage());
        }
    }

    public String getCreateContactFormHeaderText() {
        driver.switchTo().frame("object-builder-ui");
        String formHeaderText = "";
        try {
            formHeaderText = createNewContactFormHeader.getText();
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
            return "Error:createNewContactFormHeader is null";
        }
        driver.switchTo().defaultContent();
        return formHeaderText;
    }

    public void createNewContact(String email, String firstName, String lastName) {
        driver.switchTo().frame("object-builder-ui");
        newContactFirstnameInput.sendKeys(firstName);
        newContactLastnameInput.sendKeys(lastName);
        newContactEmailInput.sendKeys(email);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        if (waitElementClickable(createBtnOnCreateContactForm)) {
            clickByJS(createBtnOnCreateContactForm);
            driver.switchTo().defaultContent();
        } else {
            System.out.println("Error: Create button is non-clickable right now.");
        }
    }

    public void closeHowToAddContactsPopUp() {
        if(!clickWithTryBlock(howToAddContactsPopUpCloseBtn)) {
            System.out.println("How To Add Contacts Pop-up page doesn't show.");
        }
    }

    public String getCreateNewContactFormHeaderText() {
       return getElementHeaderText(createNewContactFormHeader);
    }


    public String getNewlyContactEmailFromContactsTable() {
        try {
            System.out.println("newlyContactEmailField : " + newlyContactEmailField.getText());
            return newlyContactEmailField.getText();
        } catch (NoSuchElementException e) {
            System.out.println("Error: newLyContactEmailField is null" + e.getMessage());
        }
        return "Error: newLyContactEmailField is null";
    }

    //Tool methods
    private List<String> helpLoopElementsList(List<WebElement> elementList) {
        List<String> searchResultStrList = new ArrayList<>();
        for (int i = 0; i < elementList.size(); i++) {
            searchResultStrList.add(elementList.get(i).getText());
        }
        return searchResultStrList;
    }

    private List<String> helpLoopPhoneNumberList(List<WebElement> elementList) {
        List<String> searchResultStrList = new ArrayList<>();
        for (int i = 0; i < elementList.size(); i++) {
            //replace non-numeric characters with an empty string
            String phoneOnlyNumberString = elementList.get(i).getText().replaceAll("[^0-9]", "");
            searchResultStrList.add(phoneOnlyNumberString);
        }
        return searchResultStrList;
    }

}


