package pagefactory.pageobjects.librarymodule;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pagefactory.pageobjects.core.BaseCRMAppPage;

import java.util.List;

public class ProductsPage extends BaseCRMAppPage {

    @FindBy(css = "button[data-test-id='export-view-all']")
    protected WebElement exportBtn;
    @FindBy(css = "div[data-test-id='object-switcher__select'] span[class='private-dropdown__item__label']")
    protected WebElement productsPageHeader;
    @FindBy(css = "div[role='dialog']>header h2")
    protected WebElement exportDialogHeader;
    @FindBy(css = "button[data-selenium-test='ExportDialog-submit-button']")
    protected WebElement exportDialogSubmitBtn;
    @FindBy(css = "div[data-selenium='floating-alert']")
    protected WebElement exportFileDownloadDiv;
    @FindBy(css = "div[aria-atomic='true'] > div > h5 > i18n-string")
    protected WebElement exportSuccessAlertHeader;
    @FindBy(css = "div[aria-label='Close']")
    protected WebElement successDialogCloseBtn;
    @FindBy(css = "div[data-selenium='floating-alert']")
    protected WebElement exportFileDownloadAlertDiv;
    @FindBy(css = "div[data-selenium='floating-alert'] > a")
    protected WebElement exportFileAlertDownloadLink;
    @FindBy(css = "div[data-test-id='payments-enroll-nudge-close']")
    protected WebElement paymentSetupAlertCloseBtn;
    @FindBy(css = "tbody > tr")
    protected List<WebElement> allProductRowList;
    @FindBy(css = "button[data-test-id='index-product-name-cell-action-dropdown-clone-btn']")
    protected WebElement cloneBtn;
    @FindBy(css = "header[class='private-modal__header uiDialogHeader'] h2")
    protected WebElement editProductPopDialogHeader;
    @FindBy(css = "textarea[data-selenium-test='property-input-name']")
    protected WebElement productNameEditInput;
    @FindBy(css = "input[data-selenium-test='product-price-input']")
    protected WebElement productPriceEditInput;
    @FindBy(css = "button[data-selenium-test='save-product-btn']")
    protected WebElement saveProductEditBtn;
    @FindBy(css = "input[data-test-id='index-page-search']")
    protected WebElement searchInput;
    @FindBy(css = "div[aria-live='polite'] h5>i18n-string")
    protected WebElement colonProductEditSuccessToast;
    @FindBy(css = "button[data-test-id='import-button']")
    protected WebElement importButton;
    protected boolean isEditSuccessToastShown;
    @FindBy(css = "div[data-test-id='checkbox-select-all'] input + span")
    protected WebElement selectAllBtn;
    @FindBy(css = "button[data-selenium-test='bulk-action-delete']")
    protected WebElement deleteBtn;
    @FindBy(css = "textarea[data-selenium-test='delete-dialog-match']")
    protected WebElement numberDeleteRecordInput;
    @FindBy(css = "button[data-test-id='delete-dialog-confirm-button']")
    protected WebElement deleteConfirmBtn;
    @FindBy(css = "div[data-test-id='undefined'] h5 > i18n-string")
    protected WebElement deleteSuccessMsgToast;

    @FindBy(css = "i18n-string[data-key='zeroQueryResults.generic.titleText']")
    protected WebElement noProductsMatchInTableMsg;

    ImportPage importPage;

    protected String noProductsMatchInTableMsgStr;
    protected String deleteSuccessToastStr;

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public String getPageHeader() {
        return productsPageHeader.getText();
    }


    //For Export
    public String openExportPopDialog() {
        try {
            paymentSetupAlertCloseBtn.click();
        } catch (NoSuchElementException e) {
            System.out.println("Payment alert is now showing.");
        }
        exportBtn.click();
        webDriverWait.until(ExpectedConditions.visibilityOf(exportDialogHeader));
        return exportDialogHeader.getText();
    }

    public void clickSubmitExportProductButton() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(exportDialogSubmitBtn));
        jse.executeScript("arguments[0].scrollIntoView(true)", exportDialogSubmitBtn);
        exportDialogSubmitBtn.click();
    }

    public String getExportSuccessAlertHeader() {
        try {
            webDriverWait.until(ExpectedConditions.visibilityOf(exportSuccessAlertHeader));
            String headerStr = exportSuccessAlertHeader.getText();
            successDialogCloseBtn.click();
            return headerStr;
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
            return "Error:didn't get the export 'Success' alert header.";
        } catch (TimeoutException e) {
            return "Error:time out to get 'Success' alert header";
        }
    }

    public boolean clickDownLoadExportFileLink() {
        try {
            webDriverWait.until((ExpectedConditions.visibilityOf(exportFileDownloadAlertDiv)));
            exportFileAlertDownloadLink.click();
            return true;
        } catch (NoSuchElementException | TimeoutException e) {
            System.out.println("Export file download toast alert didn't show.");
            return false;
        }
    }


    //For Clone
    public String cloneProduct(String productName) {
        String headerStr = "";
        if (!allProductRowList.isEmpty()) {
            System.out.println("product row list:" + allProductRowList.size());
            for (WebElement currentRow : allProductRowList) {
                WebElement nameColumn = currentRow.findElement(By.cssSelector(
                        "td:nth-child(2) button[data-test-id='index-product-name-cell-link'] >span>span>span>span"));
                if (nameColumn.getText().equalsIgnoreCase(productName)) {
                    myAction.moveToElement(nameColumn).build().perform();
                    WebElement currentActionBtn = currentRow.findElement(By.cssSelector(
                            "button[data-test-id='index-product-name-cell-action-dropdown']"));
                    webDriverWait.until(ExpectedConditions.elementToBeClickable(currentActionBtn));
                    currentActionBtn.click();
                    cloneBtn.click();
                    webDriverWait.until(ExpectedConditions.visibilityOf(editProductPopDialogHeader));
                    headerStr = exportDialogHeader.getText();
                    break;
                }
            }
        }
        return headerStr;
    }

    public String saveProductEditForm() {
        String toastString = "";
        try {
            productNameEditInput.clear();
            productNameEditInput.sendKeys("New product");
            productPriceEditInput.clear();
            productPriceEditInput.sendKeys("59.99");
            sleepBySeconds(2);
            saveProductEditBtn.click();
            try {
                toastString = colonProductEditSuccessToast.getText();
                isEditSuccessToastShown = true;
            } catch (NoSuchElementException e) {
                System.out.println("Edit success toast alert didn't show");
                isEditSuccessToastShown = false;
            }
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }
        return toastString;
    }

    public boolean isEditSuccessToastVisible() {
        return isEditSuccessToastShown;
    }

    //Search
    public void searchProductAndDelete(String searchKeyWords) {
        try {
            searchInput.clear();
            searchInput.sendKeys(searchKeyWords);
            List<WebElement> allRowList = webDriverWait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.cssSelector("tbody>tr"))));
            System.out.println("table list size after reload:" + allRowList.size());
            if (allRowList.isEmpty()) {
                System.out.println("No search result matched with '" + searchKeyWords + "'");
                return;
            } else {
                sleepBySeconds(2);
                selectAllBtn.click();
                deleteBtn.click();
                String deleteNumber = numberDeleteRecordInput.getAttribute("data-match-value");
                numberDeleteRecordInput.sendKeys(deleteNumber);
                deleteConfirmBtn.click();

                webDriverWait.until(ExpectedConditions.visibilityOf(deleteSuccessMsgToast));
                deleteSuccessToastStr = deleteSuccessMsgToast.getText();
                System.out.println(deleteSuccessToastStr);

                webDriverWait.until(ExpectedConditions.visibilityOf(noProductsMatchInTableMsg));
                noProductsMatchInTableMsgStr = noProductsMatchInTableMsg.getText();
                System.out.println(noProductsMatchInTableMsgStr);
            }
        } catch (NoSuchElementException | TimeoutException e) {
            System.out.println(e.getMessage());
        }
    }

    public String getDeleteSuccessToastStr() {
        return deleteSuccessToastStr;
    }

    public String getNoProductsMatchInTableMsgStr() {
        return noProductsMatchInTableMsgStr;
    }

    //import
    public ImportPage gotoImportPage() {
        importButton.click();
        return PageFactory.initElements(driver, ImportPage.class);

    }


}
