package pagefactory.pageobjects.librarymodule;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pagefactory.pageobjects.core.BaseCRMAppPage;

import java.util.List;

public class ImportPage extends BaseCRMAppPage {
    @FindBy(css = "div[id='react-root'] h1>i18n-string")
    protected WebElement importPageHeader;
    @FindBy(css = "button[data-test-id='start-import-button']")
    protected WebElement startImportBtn;
    @FindBy(css = "div[data-test-id='import-type-multi']")
    protected WebElement importFromComputerOption;
    @FindBy(css = "div[data-test-id='import-type-optOut']")
    protected WebElement importFromCOptOutList;
    @FindBy(css = "data-test-id='import-type-template'")
    protected WebElement importFromRepeatImportTemplate;
    @FindBy(css = "button[data-wizard-action-name='done']")
    protected WebElement nextBtn;
    @FindBy(css = "div[data-test-id='SINGLE_FILE_IMPORT'] input[type='radio'] + span")
    protected WebElement importSingleFileRadioBtn;
    @FindBy(css = "div[data-test-id='MULTI_FILE_IMPORT'] input[type='radio'] + span")
    protected WebElement importMultiFileRadioBtn;
    @FindBy(id = "selectFilesNextButton")
    protected WebElement selectFileNextBtn;
    @FindBy(css = "div[data-test-id='SINGLE_OBJECT_TYPE'] input[type='radio'] + span")
    protected WebElement singleObjectRadioBtn;
    @FindBy(css = "div[data-test-id='SINGLE_OBJECT_TYPE'] input[type='radio'] + span")
    protected WebElement multiObjectRadioBtn;
    @FindBy(css = "button[id='selectNumberOfObjectsNextButton']")
    protected WebElement selectObjectNextBtn;
    @FindBy(css = "div[wrap='wrap'] > div[order]")
    protected List<WebElement> categoryList;
    @FindBy(id = "selectObjectTypesNextButton")
    protected WebElement selectObjectCategoryNextBtn;
    @FindBy(css = "button[data-component-name='UIFileButton']")
    private WebElement chooseFileBtn;
    @FindBy(css = "span[class='private-dropdown__button-label uiDropdown__buttonLabel']")
    protected List<WebElement> selectList;
    @FindBy(css = "div[role='listbox'] > div[class^='Select-option']")
    protected List<WebElement> createORUpdateDropList;
    @FindBy(id = "uploadNextButton")
    protected WebElement uploadFileNextBtn;
    @FindBy(css = "i18n-string[data-key='crm-import-wizard-lib.mapColumnsStepValidationSectionErrorsFound']")
    protected WebElement mappingNoErrorMsg;
    @FindBy(css = "button[id='mapColumnsNextButton']")
    protected WebElement mappingNextBtn;
    @FindBy(css = "div[class='private-form__input-wrapper'] span[class='uiDropdown__buttonContents private-dropdown__button__contents']")
    protected WebElement numberOfFormatDropDown;
    @FindBy(css = "div[class='private-search-control']> input[type='search']")
    protected WebElement formatSearchInput;
    @FindBy(id = "importWizardDoneButton")
    protected WebElement importDoneBtn;
    @FindBy(css = "ul > li > h2")
    protected WebElement fileNameAfterImport;
    protected String importedFileName;
    @FindBy(css = "nav[role='navigation'] > a")
    protected WebElement goBackImportHistoryALink;
    @FindBy(css = "i18n-string[data-key='postImport.noErrorsTitle']")
    protected WebElement importNoErrorMsg;
    @FindBy(css = "div[data-selenium='floating-alert']")
    protected WebElement importReadyAlert;
    @FindBy(css = "i18n-string[data-key='FileManagerLib.fileUploadRenamed']")
    protected WebElement fileUploadedDiv;
    @FindBy(css = "div[class='private-search-control'] > input")
    protected WebElement columnHeaderLanguageSearch;
    private String importReadyAlertStr;

    public ImportPage(WebDriver driver) {
        super(driver);
    }

    public String getImportPageHeader() {
        webDriverWait.until(ExpectedConditions.visibilityOf(importPageHeader));
        return importPageHeader.getText();
    }

    public void startImport(String importOption, String numberOfFile, String numberOfObject, String fileCategory,
                            String createOrUpdate, String language, String numberFormat) {
        startImportBtn.click();

        //Import way option sub-page
        switch (importOption) {
            case "COMPUTER":
                importFromComputerOption.click();
                break;
            case "OPT_OUT_LIST":
                importFromCOptOutList.click();
                break;
            case "PAST_IMPORT":
                importFromRepeatImportTemplate.click();
                break;
        }
        nextBtn.click();

        //select number of file sub-page
        switch (numberOfFile) {
            case "SINGLE":
                importSingleFileRadioBtn.click();
                break;
            case "MULTI":
                importMultiFileRadioBtn.click();
                break;
        }
        selectFileNextBtn.click();

        //Select object sub-page
        switch (numberOfObject) {
            case "ONE":

                singleObjectRadioBtn.click();
                break;
            case "MULTIPLE":
                multiObjectRadioBtn.click();
                break;
        }
        selectObjectNextBtn.click();

        //Select category type sub-page
        System.out.println("Category List size:" + categoryList.size());
        if (!categoryList.isEmpty()) {
            for (WebElement element : categoryList) {
                String categoryNameSpanCss = "img + div > span[class^='UISelectableButton__Title-sc-']:first-child";
                String categoryName = webDriverWait.until(ExpectedConditions.visibilityOf(element.findElement(By.cssSelector(categoryNameSpanCss)))).getText();
                ;
//                 element.findElement(By.cssSelector(categoryNameSpanCss))
                if (fileCategory.equalsIgnoreCase(categoryName)) {
                    webDriverWait.until(ExpectedConditions.elementToBeClickable(element.findElement(By.cssSelector(
                            "input[name='object-type-selection'] + span")))).click();

                    break;
                }
            }
        }
        webDriverWait.until(ExpectedConditions.elementToBeClickable(selectObjectCategoryNextBtn));
        selectObjectCategoryNextBtn.click();

        //Upload file sub-page
        webDriverWait.until(ExpectedConditions.elementToBeClickable(chooseFileBtn));
        chooseFileBtn.click();
        uploadFromWindows("C:\\Users\\belle\\Downloads\\hubspot-crm-import.xlsx");
        webDriverWait.until(ExpectedConditions.visibilityOf(fileUploadedDiv));

        if (fileUploadedDiv.getText().contains("File uploaded")) {
            if (!selectList.isEmpty()) {
                System.out.println("Select-list size:" + selectList.size());
                selectList.get(0).click();
                howToImportProducts(createOrUpdate);

                jse.executeScript("arguments[0].scrollIntoViewIfNeeded(true);", selectList.get(1));
                selectList.get(1).click();
                columnHeaderLanguageSearch.sendKeys(language);
                myAction.click().build().perform();

                uploadFileNextBtn.click();
            }
        }

        //Mapping sub-page
        webDriverWait.until(ExpectedConditions.visibilityOf(mappingNoErrorMsg));
        System.out.println("Get mapping no error text:" + mappingNoErrorMsg.getText());
        if (mappingNoErrorMsg.getText().contains("0 errors found")) {
            webDriverWait.until(ExpectedConditions.elementToBeClickable(mappingNextBtn));
            mappingNextBtn.click();
        }

        //Format sub-page
        numberOfFormatDropDown.click();
        formatSearchInput.sendKeys(numberFormat);
        formatSearchInput.click();
        importDoneBtn.click();

        //Confirmed sub-page
        importedFileName = webDriverWait.until(ExpectedConditions.visibilityOf(fileNameAfterImport)).getText();
        importReadyAlertStr = webDriverWait.until(ExpectedConditions.visibilityOf(importReadyAlert)).getText();

    }



    private void howToImportProducts(String createOrUpdate) {
        if (!createORUpdateDropList.isEmpty()) {
            System.out.println("create-or-Update-DropList size:" + createORUpdateDropList.size());
            switch (createOrUpdate) {
                case "CREATE_AND_UPDATE":
                    createORUpdateDropList.get(0).click();
                    break;
                case "CREATE_ONLY":
                    System.out.println("Inside Create-Only");
                    createORUpdateDropList.get(1).click();
                    break;
                case "UPDATE_ONLY":
                    createORUpdateDropList.get(2).click();
                    break;

            }
        }
    }

    public String getImportReadyAlertStr() {
        return importReadyAlertStr;
    }

    public String getImportNoErrorMsg() {
        WebElement div = driver.findElement(By.cssSelector("div[direction='column']"));
        jse.executeScript("arguments[0].scrollIntoView();", div);
        webDriverWait.until(ExpectedConditions.visibilityOf(importNoErrorMsg));
        String msgStr = "";
        try {
            msgStr = importNoErrorMsg.getText();
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
            msgStr = "Error:cannot located 'Import-No-Error-Title' element";
        }
        return msgStr;
    }
}
