package pagefactory.pageobjects.marketingmodule;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FormPage {
    @FindBy(css = "button[data-test-id='illSetItUpMyself']")
    protected WebElement setFormMyselfBtn; //on "Create your first form" popup dialogue

    @FindBy(css = "button[data-test-id='helpMeGetStarted']")
    protected WebElement helpMeGetStartBtn; //on "Create your first form" popup dialogue

    @FindBy(css ="div[role='dialog'] div[aria-label='Close']")
    protected WebElement createFirstFormCloseBtn;

    @FindBy(css ="button[data-action='clone']")
    protected WebElement colonBtn;

    @FindBy(css ="button[data-action='edit']")
    protected WebElement editBtn;
//    @FindBy(css ="button[data-action='clone']")
//    protected WebElement colonBtn;

    public void cloneForm(){
    }

    public void editForm(){

    }
}
