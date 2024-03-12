package pageobjects.loginmodule;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GoogleLoginPage {
    WebDriver driver;

    public GoogleLoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "identifierId")
    WebElement googleEmailOrPhoneInput;

    @FindBy(css = "button[jsname='Cuz2Ue']")
    WebElement forgotEmailBtn;


    @FindBy(css = "div[data-view-id='hm18Ec'] .VfPpkd-RLmnJb") //VfPpkd-RLmnJb
    WebElement nextBtnSingInGoogleEmail;

    @FindBy(className = "XOrBDc button")
    WebElement createAccountBtn;

    @FindBy(css = ".CX6Ruf.ataLTc >.PrDSKc >a:nth-child(1)")
    WebElement privacyPolicyLink;

    @FindBy(css = ".CX6Ruf.ataLTc >.PrDSKc >a:last-child")
    WebElement termsOfService;

    //Element on recovery email sub-page
    @FindBy(id = "recoveryIdentifierId")
    WebElement recoverGoogleEmailInput;

    @FindBy(css = "div[data-view-id='oIjvfc'] .VfPpkd-RLmnJb")
    WebElement NextBtnOnRecoverEmail;

    @FindBy(id = "firstName")
    WebElement firstNameInputRecoverEmail;

    @FindBy(id = "lastName")
    WebElement LastNameInputRecoverEmail;

    @FindBy(css = "div[data-view-id='rcCb1e'] .VfPpkd-RLmnJb")
    WebElement nextBtnGoogleAccountNameRecoverEmail;

    //On No google account fount page
    @FindBy(css = "div[data-view-id='kF7Stb'] .VfPpkd-RLmnJb")
    WebElement tryAgainBtn;


    //Elements on footer
    @FindBy(css =".VfPpkd-TkwUic")
    WebElement languageSelectList;

    @FindBy(css = ".kJOS4 > li:nth-child(1) > .NUwdAb.TrZEUc")
    WebElement helpLink;

    @FindBy(css = ".kJOS4 > li:nth-child(2) > .NUwdAb.TrZEUc")
    WebElement PrivacyLink;

    @FindBy(css = ".kJOS4 > li:nth-child(3) > .NUwdAb.TrZEUc")
    WebElement TermsLink;
}
