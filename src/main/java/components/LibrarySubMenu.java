package components;

import enums.CRMSubPage;
import enums.LibrarySubPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static enums.CRMSubPage.CONTACTS;
import static enums.LibrarySubPage.DOCUMENTS;

public class LibrarySubMenu extends BaseComponent {

    @FindBy(css = "ul#library-content > li >a")
    List<WebElement> librarySubMenuList;
    protected WebElement documentsBtn;
    protected WebElement productsBtn;

    public LibrarySubMenu(WebDriver driver) {
        super(driver);
    }

    public void goToPage(LibrarySubPage subMenuName) {
        documentsBtn = librarySubMenuList.get(2);
        productsBtn = librarySubMenuList.get(3);
        switch (subMenuName) {
            case DOCUMENTS:
                documentsBtn.click();
                break;
            case PRODUCTS:
                productsBtn.click();
                break;
        }
    }
}
