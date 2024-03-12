package pagefactory.pageobjects.librarymodule;

import enums.DocumentLocation;
import enums.DocumentTableColumn;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pagefactory.pageobjects.core.BaseCRMAppPage;

import java.time.Duration;
import java.util.List;


public class DocumentsPage extends BaseCRMAppPage {
    protected Logger logger = LoggerFactory.getLogger(DocumentsPage.class);

    @FindBy(css = "h1.private-header__heading > i18n-string")
    protected WebElement header;
    @FindBy(css = "button[data-selenium-test='index-upload-document-test']")
    protected WebElement uploadDocumentBtn;
    @FindBy(css = "button[data-selenium-test='sales-content-index-new-folder-button']")
    protected WebElement newFolderBtn;
    @FindBy(css = "div[data-test-id='documents-create-panel'] h2 > i18n-string")
    protected WebElement upLoadPopDialogHeader;
    @FindBy(css = "form.private-form h2 > i18n-string")
    protected WebElement createFolderPopDialogHeader;
    @FindBy(css = "ul.uiList.private-list--unstyled > li")
    protected List<WebElement> documentLocationList;
    protected WebElement fromComputerBtn;
    @FindBy(css = "input[data-selenium-test='sales-content-index-new-folder-input']")
    protected WebElement folderNameInput;
    @FindBy(css = "button[data-selenium-test='sales-content-index-new-folder-save-button']")
    protected WebElement createFolderBtn;
    @FindBy(css = "tbody > tr:first-child > td a span.private-truncated-string > span > span")
    protected WebElement lastUploadFileName; //todo: need update solution
    @FindBy(css = "tbody > tr:first-child > td a")
    protected WebElement lastUploadFolderName;
    @FindBy(css = "tbody > tr> td a span.private-truncated-string > span")
    protected List<WebElement> fileslist;
    //    @FindBy(css = "tbody > tr.sales-content-index-folder-row > td:nth-child(2) a")
//protected List<WebElement> folderList;
    @FindBy(css = "tbody > tr.sales-content-index-folder-row")
    protected List<WebElement> allRowsList;
    @FindBy(css = "button[data-selenium-test='index-actions-dropdown']")
    protected WebElement actionsBtn;
    @FindBy(css = "div[id^='dropdown-menu' ]> ul > li:nth-child(3) > button")
    protected WebElement moveBtnOnActionDropDown;
    @FindBy(css = "div[role='dialog'] h2")
    protected WebElement moveToFolderPopDialogHeader;
    @FindBy(css = "div[role='dialog']  input")
    protected WebElement moveToFolderPopDialogSearchBar;
    @FindBy(css = "div[role='dialog'] ul > li")
    protected List<WebElement> movePopDialgFolderList;
    @FindBy(css = "div[role='dialog'] button[data-button-use='primary']")
    protected WebElement moveBtn;
    @FindBy(xpath = "//*[text()='No folder']")
    protected WebElement noFolderBtn;
    @FindBy(css = "button[data-selenium-test='rename-folder-button']")
    protected WebElement renameBtn;
    @FindBy(css = "div[role='dialog'] > form h2 > i18n-string")
    protected WebElement renameFolderPopDialogHeader;
    @FindBy(css = "input[data-selenium-test='sales-content-index-new-folder-input']")
    protected WebElement newFolderNameInput;
    @FindBy(css = "button[data-selenium-test='sales-content-index-new-folder-save-button']")
    protected WebElement saveRenameFolderBtn;

    protected WebElement currentFolderField;
    private int currentSelectFolderIndex;


    public DocumentsPage(WebDriver driver) {
        super(driver);
    }

    public String getDocumentsPageHeader() {
        try {
            return header.getText();
        } catch (NoSuchElementException e) {
            return "Error: Header didn't found.";
        }
    }

    public String openUploadDocumentPop() {
        uploadDocumentBtn.click();
        try {
            return upLoadPopDialogHeader.getText();
        } catch (NoSuchElementException e) {
            return "Error:upLoadPopDialogHeader is null or cannot located";
        }
    }

    public String openCreatedFolderPopup() {
        newFolderBtn.click();
        try {
            webDriverWait.until(ExpectedConditions.visibilityOf(createFolderPopDialogHeader));
            return createFolderPopDialogHeader.getText();
        } catch (NoSuchElementException e) {
            return "Error:upLoadPopDialogHeader is null or cannot located";
        }
    }

    public void createFolder(String folderName) {
        webDriverWait.until(ExpectedConditions.visibilityOf(folderNameInput));
        folderNameInput.sendKeys(folderName);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(createFolderBtn));
        createFolderBtn.click();
    }

    public void chooseFileFrom(DocumentLocation location, String filePath) {
        if (!documentLocationList.isEmpty()) {
            switch (location) {
                case COMPUTER:
                    fromComputerBtn = documentLocationList.get(0);
                    fromComputerBtn.click();
                    uploadFromWindows(filePath);
                    break;
                case EXISTING_FILE_IN_HUBSPOT:
                    break;
                case DROPBOX:
                    break;
                case GOOGLE_DRIVE:
                    break;
                case BOX:
                    break;
            }
        } else {
            System.out.println("Error: document-Location-list is empty.");
        }
    }

    public String getLatestDocByColumnName(DocumentTableColumn column) {
        webDriverWait.until(ExpectedConditions.visibilityOf(lastUploadFileName));
        System.out.println("pdf name:" + lastUploadFileName.getText());
        return switch (column) {
            case NAME -> lastUploadFileName.getText();
            case OWNER -> "";
        };
    }

    public String getLatestCreatedFolderByColumnName(DocumentTableColumn column) {
        return switch (column) {
            case NAME -> lastUploadFolderName.getText();
            case OWNER -> "";
        };
    }

    public void hoverOnFileToClickMoveBtn(String fileName) {
        webDriverWait.until(ExpectedConditions.visibilityOfAllElements(fileslist));
        if (!fileslist.isEmpty()) {
            for (WebElement currentFile : fileslist) {
                if (currentFile.getText().equals(fileName)) {
                    jse.executeScript("arguments[0].scrollIntoView();", currentFile);
                    sleepBySeconds(1);
                    myAction.moveToElement(currentFile).build().perform();
                    sleepBySeconds(1);
                    openMoveFilePopupDialog();
                    break;
                }
            }
        } else {
            System.out.println("Error: file list empty");
        }
    }

    public String getMoveToFolderPopDialogHeader() {
        return moveToFolderPopDialogHeader.getText();
    }

    public void chooseFolderToMove(String folderName) {
        webDriverWait.until(ExpectedConditions.visibilityOf(moveToFolderPopDialogSearchBar));
        moveToFolderPopDialogSearchBar.sendKeys(folderName);
        boolean isFolderFind = true;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        System.out.println("size of folder"+movePopDialgFolderList.size());
        for (WebElement currentFolder : movePopDialgFolderList) {
            if (currentFolder.getText().compareToIgnoreCase(folderName) > 0) {
                System.out.println("Find the pdf folder");
                currentFolder.click();
                isFolderFind = true;
                break;
            }
            isFolderFind = false;
        }
        if (!isFolderFind) {
            noFolderBtn.click();
        }
        webDriverWait.until(ExpectedConditions.elementToBeClickable(moveBtn));
        moveBtn.click();
    }

    public void selectFolder(String folderName) {
        for (int i = 0; i < allRowsList.size(); i++) {
            WebElement currentRow = allRowsList.get(i);
            currentFolderField = currentRow.findElement(By.cssSelector("td:nth-child(2) > a"));
            if (currentFolderField.getText().equalsIgnoreCase(folderName)) {
                            currentSelectFolderIndex = i;
                            System.out.println("selected folder:" + currentFolderField.getText());
//                webDriverWait.until(ExpectedConditions.elementToBeClickable(currentRow.findElement((By.cssSelector("td:nth-child(1) input"))))).click();
//                WebElement checkBoxOfCurrentFolder = currentRow.findElement((By.cssSelector("td:nth-child(1) input")));
                jse.executeScript("arguments[0].scrollIntoView();", currentFolderField);
                jse.executeScript("document.querySelectorAll('tbody > tr.sales-content-index-folder-row td:nth-child(1) input')[" + i + "].click()");
            }
        }
    }

    public String openRenameFoldPopDialog() {
        String renameDialogHeaderStr = "Error:did not get rename_dialog Header text";
        try {
            webDriverWait.until(ExpectedConditions.elementToBeClickable(renameBtn));
            renameBtn.click();
            webDriverWait.until(ExpectedConditions.visibilityOf(renameFolderPopDialogHeader));
            renameDialogHeaderStr = renameFolderPopDialogHeader.getText();
        } catch (NoSuchElementException e) {
            System.out.println(e);
        }
        return renameDialogHeaderStr;
    }

    public void performRenameFolder(String newFolderName) {
        newFolderNameInput.clear();
        newFolderNameInput.sendKeys(newFolderName);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(saveRenameFolderBtn));
        saveRenameFolderBtn.click();
    }

    public String getCurrentFolderText() {
//        webDriverWait.until(ExpectedConditions.visibilityOf(currentFolderField));
        sleepBySeconds(2);
        String updatedFolderName = allRowsList.get(currentSelectFolderIndex).getText();
        return updatedFolderName;
    }


    //Helper functions

    private void openMoveFilePopupDialog() {
//        webDriverWait.until(ExpectedConditions.elementToBeClickable(actionsBtn));
        actionsBtn.click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(moveBtnOnActionDropDown));
        moveBtnOnActionDropDown.click();
    }


}
