package pagefactory.pageobjects.crmmodule;

import components.LeftMenu;
import components.TopMenuToolBar;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pagefactory.pageobjects.core.BaseCRMAppPage;

public class ContactPersonalProfilePage extends BaseCRMAppPage {
    protected TopMenuToolBar topMenuToolBar;
    protected LeftMenu leftMenu;
    protected WebElement contactEmailRecord;
    @FindBy(css = "span[data-selenium-test='highlightTitle'] > span")
    protected WebElement contactNameSpan;
    @FindBy(css = "div[data-selenium-test='property-input-email']  span.private-truncated-string__inner > span ")
    protected WebElement contactEmailSpan;
    @FindBy(linkText = "Contacts")
    protected WebElement backContactsLink;

    public ContactPersonalProfilePage(WebDriver driver) {
        super(driver);
    }

    public String getContactProfileHighlightTitleText() {
        webDriverWait.until(ExpectedConditions.visibilityOf(contactNameSpan));
        String contactName = contactNameSpan.getText();
        return contactName;
    }

    public String getContactEmailSpan(){
        webDriverWait.until(ExpectedConditions.visibilityOf(contactEmailSpan));
        String contactEmail = contactEmailSpan.getText();
        return contactEmail;
    }

    public void goBackToContactsPage() {
        try {
            backContactsLink.click();
        } catch (Exception e) {
            System.out.println("Back to 'Contacts' btn on profile page is not found.");
        }
    }
}
