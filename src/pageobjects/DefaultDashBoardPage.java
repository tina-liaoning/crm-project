package pageobjects;

import components.TopMenuToolBar;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import components.LeftMenu;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class DefaultDashBoardPage extends BaseWebPage {
    protected LeftMenu leftMenu;
    protected TopMenuToolBar topMenuToolBar;
    protected WebElement defaultDashboardHeader;
    protected List<WebElement> userGuidesMenuList;
    protected WebElement userGuidesSubsectionHeader;

    public DefaultDashBoardPage(WebDriver driver) {
        super(driver);
        leftMenu = new LeftMenu(driver);
        topMenuToolBar = new TopMenuToolBar(driver);
        defaultDashboardHeader = webDriverWait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("i18n-string[data-key='app.tabs.title']")));  //nav[aria-label='User Guides']
        if (isElementsListExist("nav[aria-label='User Guides']")) {
            userGuidesMenuList = driver.findElements(By.cssSelector("nav[aria-label='User Guides'] li"));
        }
    }

    public List<WebElement> getLeftMenuList() {
        return leftMenu.getLeftMenuList();
    }

    public TopMenuToolBar getTopMenuToolBar() {
        return topMenuToolBar;
    }

    public String getDefaultDashboardHeaderText() {
        return defaultDashboardHeader.getText();
    }

    public List<WebElement> getUserGuidesMenuList() {
        System.out.println("user guide list size:" + userGuidesMenuList.size());
        return userGuidesMenuList;
    }

    public String getSubSectionHeaderText() {
        String text = "";
        userGuidesSubsectionHeader = webDriverWait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("div[id^='UIProgress-label-'] h5 > i18n-string")));
        text = userGuidesSubsectionHeader.getText();
        return text;
    }

    public void gotoContactsPage() {
        leftMenu.gotoContacts();
    }
}






/*
*  By markingGuideAlink = By.cssSelector("a[data-tab-id='marketing']");
    By salesGuideAlink = By.cssSelector("a[data-tab-id='sales']");
    By customerServiceGuideAlink = By.cssSelector("a[data-tab-id='service']");
    By webContentGuideAlink = By.cssSelector("a[data-tab-id='cms']");
    By viewYourPlanAlink = By.cssSelector("a[data-test-id='products-link']");
    By inviteYourTeamAlink = By.cssSelector("a[data-test-id='side-nav-invite-link']");
    By startOverViewDemoAlink = By.cssSelector("a[data-test-id='overview-demo']");
    By  seeWhatSetupBtn = By.cssSelector("i18n-string[data-key='setup-guide-components.crmTemplateBanner.cta']");
    By  setupBasicAlink = By.cssSelector("a[data-test-id='task-group-link-crm_set_up_the_basics']");

    //subsection of Marking guide button
    By  trackDealsAlink = By.cssSelector("a[data-test-id='task-group-link-sales_track_deals']");
    By  quickConnectLeadsAlink = By.cssSelector("a[data-test-id='task-group-link-sales_connect_with_leads']");
    By  closeDealsAndAnalyzePerformanceAlink = By.cssSelector("a[data-test-id='task-group-link-sales_track_performance']");
    By  salesLearnMoreDemoAlink = By.cssSelector("i18n-string[data-key='sales.learnMore.ctas.sales-demo']");
    By  salesLearnMoreOrganizeSalesDataAlink = By.cssSelector("i18n-string[data-key='sales.learnMore.ctas.academy-sales-data']");
    By  salesLearnMoreMobileAppAlink = By.cssSelector("i18n-string[data-key='sales.learnMore.ctas.mobile-app-sales']");
    By  salesLearnMoreAppFromMarketplaceAlink = By.cssSelector("i18n-string[data-key='sales.learnMore.ctas.app-marketplace-sales']");

    By  turnOffUserGuideAlink = By.cssSelector("div[data-test-id='enrollment'] a");
* */