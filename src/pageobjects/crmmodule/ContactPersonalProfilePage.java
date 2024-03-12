package pageobjects.crmmodule;

import components.LeftMenu;
import components.TopMenuToolBar;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageobjects.BaseWebPage;

public class ContactPersonalProfilePage extends BaseWebPage {
    protected TopMenuToolBar topMenuToolBar;
    protected LeftMenu leftMenu;
    protected WebElement contactEmailRecord;
    protected WebElement contactProfileHighlightTitle;
    protected final String contactProfileHighlightTitleSelectorString = "span[data-selenium-test='highlightTitle'] > span";
    protected WebElement backContactsLink;

    public ContactPersonalProfilePage(WebDriver driver) {
        super(driver);
        if (waitElementVisible(contactProfileHighlightTitleSelectorString)) {
            contactProfileHighlightTitle = driver.findElement(By.cssSelector(contactProfileHighlightTitleSelectorString));
        }else{
            System.out.println("Error: contact_Profile_Highlight_Title cannot be located.");
        }
        backContactsLink = driver.findElement(By.linkText("Contacts"));
    }

    public String getContactProfileHighlightTitleText() {
        String text = contactProfileHighlightTitle.getText();
        System.out.println("Contact_profile_title" + text);
        return text;
    }

    public void goBackToContactsPage(){
        try {
            backContactsLink.click();
        }catch (Exception e){
        System.out.println("Back to 'Contacts' btn on profile page is not found.");
    }
    }
}
