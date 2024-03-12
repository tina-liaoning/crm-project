package components;

import enums.LeftMN;
import enums.LibrarySubPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import enums.CRMSubPage;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;


public class LeftMenu extends BaseComponent {

    protected WebElement workspacesMenuBtn;
    protected WebElement crmMenuBtn;
    protected WebElement markingMenuBtn;
    protected WebElement webContentMenuBtn;
    protected WebElement automationsMenuBtn;
    protected WebElement reportingDataMenuBtn;
    protected WebElement libraryBtn;
    protected WebElement templateMarketplaceSideMenuBtn;
    protected CRMSubMenu crmSubMenu;
    protected LibrarySubMenu librarySubMenu;
    @FindBy(css = "div[data-test-id='hs-vertical-nav'] li")
    protected List<WebElement> leftMenuList;

    public LeftMenu(WebDriver driver) {
        super(driver);
    }

    public List<WebElement> getLeftMenuList() {
        webDriverWait.until(ExpectedConditions.visibilityOfAllElements(leftMenuList));
        return leftMenuList;
    }

    private void initLeftMenu() {
        webDriverWait.until(ExpectedConditions.visibilityOfAllElements(leftMenuList));
        if (leftMenuList != null && !leftMenuList.isEmpty()) {
            System.out.println("Left menu size:" + leftMenuList.size());
            workspacesMenuBtn = leftMenuList.get(0);
            crmMenuBtn = leftMenuList.get(1);
            markingMenuBtn = leftMenuList.get(2);
            webContentMenuBtn = leftMenuList.get(3);
            automationsMenuBtn = leftMenuList.get(4);
            reportingDataMenuBtn = leftMenuList.get(5);
            libraryBtn = leftMenuList.get(6);
//            templateMarketplaceSideMenuBtn = leftMenuList.get(7);//remove from menu
        } else {
            System.out.println("Error: leftMenuList is empty." + leftMenuList.size());
        }
    }

    public void gotoMenu(LeftMN menuName, String subMenuName) {
        initLeftMenu();
        switch (menuName) {
            case CRM:
                crmMenuBtn.click();
                crmSubMenu = PageFactory.initElements(driver, CRMSubMenu.class);
                if (CRMSubPage.CONTACTS.toString().equalsIgnoreCase(subMenuName)) {
                    crmSubMenu.goToPage(CRMSubPage.CONTACTS);
                }
                break;
            case LIBRARY:
                System.out.println("in library");
                libraryBtn.click();
                librarySubMenu = PageFactory.initElements(driver, LibrarySubMenu.class);
                if (LibrarySubPage.DOCUMENTS.toString().equalsIgnoreCase(subMenuName)) {
                    librarySubMenu.goToPage(LibrarySubPage.DOCUMENTS);
                } else if (LibrarySubPage.PRODUCTS.toString().equalsIgnoreCase(subMenuName)) {
                    librarySubMenu.goToPage(LibrarySubPage.PRODUCTS);
                }
                break;
            default:
                System.out.println("Error in left-menu: No matching menu option.");

        }

    }
}


/*
    //Left side Menu-bar section
    By workspacesSideMenuBtn = By.id("workspaces-toggle");
    By crmSideMenuBtn = By.id("crm-toggle");
    By markingSideMenuBtn = By.id("marketing-toggle");
    By webContentSideMenuBtn = By.id("web-content-toggle");
    By automationsSideMenuBtn = By.id("automation-toggle");
    By reportingDataSideMenuBtn = By.id("reports-and-data-toggle");
    By library = By.id("library-toggle");
    By templateMarketplaceSideMenuBtn = By.id("marketplace")


 */