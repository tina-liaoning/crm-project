package pagefactory.pageobjects.settingModule;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pagefactory.pageobjects.core.BaseCRMAppPage;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class UserTeamPage extends BaseCRMAppPage {
    @FindBy(css = "div[data-test-id='quick-invite-button']")
    protected WebElement quickInviteTeamDiv;
    @FindBy(id = "email")
    protected WebElement inviteEmailAddressInput;
    @FindBy(css = "button[data-test-id='quick-invite-submit']")
    protected WebElement sendInviteBtn;
    @FindBy(css = "input[name='super-admin'] + span")
    protected WebElement superAdminCheckbox;
    @FindBy(css = "div[role='alert']")
    protected WebElement invitationSentAlert;
    protected String invitationSentMsgText;

    @FindBy(css = "div[data-test-id='QuickInviteModal__close-button']")
    protected WebElement inventTeamPopDialogCloseBtn;
    @FindBy(css = "div[data-test-id='custom-invite-button']")
    protected WebElement customInviteDiv;
    @FindBy(css = "input[class='form-control private-form__control user-detail-inputs']")
    protected WebElement addEmialAddressesInput;
    @FindBy(css = "button[data-wizard-action-name='next']")
    protected WebElement nextBtn;
    @FindBy(css = "div[data-test-id='makeSuperAdmin']")
    protected WebElement makeSuperAdminRadioBtn;
    @FindBy(css = "div[data-test-id='startWithTemplate']")
    protected WebElement startTemplateRadioBtn;
    @FindBy(css = "div[data-test-id='startFromScratch']")
    protected WebElement startScratchRadioBtn;
    @FindBy(css = "div[data-test-id='success-step'] h3 > i18n-string")
    protected WebElement successMegHeader;
    @FindBy(css = " div[direction='row'] div[data-test-id='uiAvatarDisplay-switchComponent'] >  div[data-test-id='content-holder'] > svg")
    protected List<WebElement> invitedTeamMemberIconList;
    @FindBy(css = "button > i18n-string[data-key^='usersManager.templatesAndPermissionSets.successStep']")
//i18n-string[data-key='usersManager.templatesAndPermissionSets.successStep.collapseAll']
    protected WebElement expandAllBtn;
    @FindBy(css = "div[data-open-complete='true']  strong")
    protected WebElement uniqueInvitedTeamMemLinkDiv;

    @FindBy(css = "button[data-wizard-action-name='done']")
    protected WebElement doneBtn;

    List<String> emailStrList;
    @FindBy(id="create-user-button")
    protected WebElement createUserBtn;
    @FindBy(css = "a[data-selenium-id='Users & Teams']")
    protected WebElement usersAndTeamsLink;
    @FindBy(css = "div[role='checkbox']")
    protected WebElement csvFileDiv;
    @FindBy(css = "small[class='private-microcopy']")
    protected WebElement upLoadCsvSpan;
    @FindBy(css = "span[class='private-file-button'] > button")
    protected WebElement chooseAFileBtn;

    public UserTeamPage(WebDriver driver) {
        super(driver);
    }

    public void chooseInviteOption(String quickOrCustom, String email) {
        if (quickOrCustom.equalsIgnoreCase("quick")) {
            doQuickInviteTeam(email);
        } else if (quickOrCustom.equalsIgnoreCase("custom")) {
            doCustomInvite(email);
        }
    }

    //Quick Invite
    public void doQuickInviteTeam(String email) {
        quickInviteTeamDiv.click();
        inviteEmailAddressInput.clear();
        inviteEmailAddressInput.clear();
        inviteEmailAddressInput.sendKeys(email);
        if (superAdminCheckbox.isSelected()) {
            System.out.println("if superadmian checkbox checked:" + superAdminCheckbox.isSelected());
            superAdminCheckbox.click();
        }
        webDriverWait.until(ExpectedConditions.elementToBeClickable(sendInviteBtn)).click();
        webDriverWait.until(ExpectedConditions.visibilityOf(invitationSentAlert));
        if (invitationSentAlert.isDisplayed()) {
            System.out.println("Inside invent send alert");
            invitationSentMsgText = invitationSentAlert.findElement(By.cssSelector("h5 > i18n-string")).getText();
        }
        inventTeamPopDialogCloseBtn.click();
    }

    public String getInvitationSentMsgText() {
        return invitationSentMsgText;
    }

    //Custom Invite
    public void doCustomInvite(String multiEmail) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(customInviteDiv));
        customInviteDiv.click();
        addEmialAddressesInput.sendKeys(multiEmail);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(nextBtn));
        sleepBySeconds(1);
        nextBtn.click();
    }

    public void setUserAccessLevels(String userAccessLevels) {
        if (userAccessLevels.equalsIgnoreCase("super")) {
            makeSuperAdminRadioBtn.click();
        } else if (userAccessLevels.equalsIgnoreCase("template")) {
            startScratchRadioBtn.click();
        } else if (userAccessLevels.equalsIgnoreCase("scratch")) {
            startScratchRadioBtn.click();
        }
        nextBtn.click();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        nextBtn.click();
    }

    public String getSuccessMsgTextByCustomInvite() {
        webDriverWait.until(ExpectedConditions.visibilityOf(successMegHeader));
        return successMegHeader.getText();
    }

    public List<String> returnEmailStrList() {
        getEmailStrListFromInvitedTeamMemIcon();
        doneBtn.click();
        return emailStrList;
    }


    private void getEmailStrListFromInvitedTeamMemIcon() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(expandAllBtn));
        expandAllBtn.click();
        sleepBySeconds(1);
        emailStrList = new ArrayList<>();
        for (WebElement currentIcon : invitedTeamMemberIconList) {
            myAction.moveToElement(currentIcon).build().perform();
            sleepBySeconds(1);
            WebElement hoverEmailDiv = driver.findElement(By.cssSelector("div[id^='uiopenpopup'] div[role='tooltip']"));
            emailStrList.add(hoverEmailDiv.getText());
        }
        System.out.println("email str list:" + emailStrList);
        System.out.println("email str list:" + emailStrList.size());
    }

    //Invite from csv file
    public void kk(){
        webDriverWait.until(ExpectedConditions.visibilityOf(usersAndTeamsLink));
        usersAndTeamsLink.click();

        webDriverWait.until(ExpectedConditions.visibilityOf(createUserBtn));
        jse.executeScript("arguments[0].scrollIntoView(true);", createUserBtn);
        createUserBtn.click();

        webDriverWait.until(ExpectedConditions.elementToBeClickable(customInviteDiv));
        customInviteDiv.click();

//        webDriverWait.until(ExpectedConditions.visibilityOf(csvFileDiv));
        jse.executeScript("arguments[0].scrollIntoView(true);", upLoadCsvSpan);
        upLoadCsvSpan.click();


        webDriverWait.until(ExpectedConditions.visibilityOf(chooseAFileBtn));
        chooseAFileBtn.click();
        uploadFromWindows("C:\\Users\\belle\\Downloads\\hubspotFiles\\example_inviteEmails.csv");

        nextBtn.click();
        superAdminCheckbox.click();  //todo:input[name='Permission creation choice'] + span
        nextBtn.click();
        nextBtn.click();


    }
}
