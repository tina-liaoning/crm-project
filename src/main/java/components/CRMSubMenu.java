package components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import enums.CRMSubPage;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static enums.CRMSubPage.CONTACTS;

public class CRMSubMenu extends BaseComponent {
    protected WebElement contactsBtn;
    @FindBy(css = "ul#crm-content > li")
    List<WebElement> crmSubMenuList;

    public CRMSubMenu(WebDriver driver) {
        super(driver);
    }


    public void goToPage(CRMSubPage subMenuName) {
        contactsBtn = crmSubMenuList.get(0);
        if (subMenuName.equals(CONTACTS)) {
            contactsBtn.click();
        }
    }

}
