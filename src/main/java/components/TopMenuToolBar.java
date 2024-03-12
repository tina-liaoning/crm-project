package components;

import enums.Tool;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;


public class TopMenuToolBar extends BaseComponent {
    @FindBy(css = "a[data-test-id='home-link']")
    protected WebElement iconLogo;
    @FindBy(css = "button[type='submit']")
    protected WebElement searchBtn;
    @FindBy(id = "navSearch-input")
    protected WebElement topSearchInput;
    protected WebElement upGrateToolLink;
    protected WebElement callingToolLink;
    protected WebElement marketplaceToolLink;
    @FindBy(css = "#hs-global-toolbar-icons > div.GlobalToolbarTooltip__StyledDiv-sc-1jrrqmw-0.hpxNsp")
    protected WebElement helpToolLink;
    protected WebElement settingToolLink;
    protected WebElement notificationToolLin;
    @FindBy(css = "#hs-global-toolbar-icons >li")
    protected List<WebElement> toolBarList;
    @FindBy(css = "#hs-global-toolbar-accounts > span")
    protected WebElement personalAccountToolLink;
    @FindBy(id = "navInviteLink")
    protected WebElement inviteYourTeamLink;


    public TopMenuToolBar(WebDriver driver) {
        super(driver);
    }

    public String getPersonalAccountLinkText() {
        webDriverWait.until(ExpectedConditions.visibilityOf(personalAccountToolLink));
        return personalAccountToolLink.getText();
    }

    public void hubSpotSearch(String searchInfo) {
        topSearchInput.sendKeys(searchInfo);
        searchBtn.click();
    }

    public void goTo(Tool toolName) {
        if(toolBarList.isEmpty()){
            webDriverWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("#hs-global-toolbar-icons >li")));
        }
        System.out.println("Tool bar list size:" + toolBarList.size());
        switch (toolName) {
            case UP_GRADE:
                upGrateToolLink = toolBarList.get(0);
                System.out.println(upGrateToolLink.getText());
                upGrateToolLink.click();
                break;
            case CALLING:
                callingToolLink = toolBarList.get(1);
                callingToolLink.click();
                break;
            case MARKETPLACE:
                marketplaceToolLink = toolBarList.get(2);
                marketplaceToolLink.click();
                break;
            case HELP:
                helpToolLink.click();
                break;
            case SETTING:
                settingToolLink = toolBarList.get(3);
                System.out.println("Inside setting");
                System.out.println(settingToolLink);
                webDriverWait.until(ExpectedConditions.visibilityOf(settingToolLink));
                settingToolLink.click();
                break;
            case NOTIFICATION:
                notificationToolLin = toolBarList.get(4);
                notificationToolLin.click();
                break;
            case PERSONAL_ACCOUNT:
                webDriverWait.until(ExpectedConditions.elementToBeClickable(personalAccountToolLink));
                personalAccountToolLink.click();
                break;
        }
    }
    public void startInviteTeam() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(inviteYourTeamLink));
        inviteYourTeamLink.click();
    }
 }




/*
*   //Toolbar section
    By upGrateToolBtn = By.id("hs-global-toolbar-suite-starter-list-item");
    By callToolBtn = By.cssSelector("button[aria-label='call dropdown']");
    By marketplaceToolBtn = By.id("hs-global-toolbar-marketplace-list-item");
    By helpToolBtn = By.cssSelector("button[aria-label='Help']");
    By settingToolBtn = By.id("hs-global-toolbar-settings-list-item");
    By notificationToolBtn = By.id("hs-global-toolbar-notifications-list-item");
    By personalAccountToolBtn = By.id("hs-global-toolbar-accounts");
* */