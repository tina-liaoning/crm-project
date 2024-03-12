package pagefactory.pageobjects.dashboardmodule;

import components.LeftMenu;
import components.TopMenuToolBar;
import enums.LeftMN;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import pagefactory.pageobjects.core.BaseCRMAppPage;


import java.util.List;

public class DashBoardPage extends BaseCRMAppPage {

//    @FindBy(css = "i18n-string[data-key='app.tabs.title']")
//    protected WebElement defaultDashboardHeader;
//    @FindBy(css = "nav[aria-label='User Guides'] li")
//    protected List<WebElement> userGuidesMenuList;
//    @FindBy(css = "div[id^='UIProgress-label-'] h5 > i18n-string")
//    protected WebElement userGuidesSubsectionHeader;

    public DashBoardPage(WebDriver driver) {
        super(driver);
        topMenuToolBar = PageFactory.initElements(driver, TopMenuToolBar.class);
        leftMenu = PageFactory.initElements(driver, LeftMenu.class);
    }

    public List<WebElement> getLeftMenuList() {
        return leftMenu.getLeftMenuList();
    }

    public TopMenuToolBar getTopMenuToolBar() {
        return topMenuToolBar;
    }

//    public String getDefaultDashboardHeaderText() {
//        return defaultDashboardHeader.getText();
//    }
//
//    public List<WebElement> getUserGuidesMenuList() {
//        System.out.println("user_guide_menu_list size:" + userGuidesMenuList.size());
//        return userGuidesMenuList;
//    }
//
//    public String getSubSectionHeaderText() {
//        return userGuidesSubsectionHeader != null ? userGuidesSubsectionHeader.getText() : "Error: userGuidesSubsectionHeader is null";
//    }

    public void gotoContactsPage() {
        leftMenu.gotoMenu(LeftMN.CRM,"CONTACTS");
    }

    public void gotoDocumentsPage() {
        System.out.println("From dashboard go to 'Documents' page");
        leftMenu.gotoMenu(LeftMN.LIBRARY,"DOCUMENTS");
    }

    public void gotoProductsPage() {
        System.out.println("From dashboard go to 'Products' page");
        leftMenu.gotoMenu(LeftMN.LIBRARY,"PRODUCTS");
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