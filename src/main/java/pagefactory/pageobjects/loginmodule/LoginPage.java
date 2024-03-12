package pagefactory.pageobjects.loginmodule;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pagefactory.pageobjects.core.BasePage;


public class LoginPage extends BasePage {

    @FindBy(id = "username")
    protected WebElement emailInput;   //driver.findElement(By.id("username"))
    @FindBy(css = "input[data-test-id='password-input-field']")
    protected WebElement passwordInputOfRememberEmail;
    @FindBy(id = "password")
    protected WebElement passwordInputOfTypingEmail;
    @FindBy(css = "input#remember + span")
    protected WebElement rememberMeCheckbox;
    @FindBy(css = "#password-help > button ")
    protected WebElement showPasswordCheckBox;
    @FindBy(css = "#current-password-help >button")
    protected WebElement showPasswordOfRememberEmailModeCheckBox;
    @FindBy(css = "i18n-string[data-key='login.form.forgotPassword']")
    protected WebElement forgotPasswordLink;
    @FindBy(css = "button[data-test-id='google-sign-in']")
    protected WebElement googleSignInBtn;
    @FindBy(id = "loginBtn")
    protected WebElement loginBtn;
    @FindBy(css = "button[data-2fa-rememberme='true']")
    protected WebElement rememberMeButton;
    @FindBy(css = "#hs-login > button")
    protected WebElement loginBtnOfEnterCode;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public String getLoginPageTitle() {
        return driver.getTitle();
    }

    public void login(String email, String password) {
        boolean flag = isRememberEmailMode(email);
        if (flag) {
            webDriverWait.until(ExpectedConditions.visibilityOf(passwordInputOfRememberEmail));
            passwordInputOfRememberEmail.sendKeys(password);
            showPasswordOfRememberEmailModeCheckBox.click();
        } else {
            passwordInputOfTypingEmail.sendKeys(password);
            showPasswordCheckBox.click();
            rememberMeCheckbox.click();
        }
        loginBtn.click();

        if (!flag) {
            sleepBySeconds(60);
            loginBtnOfEnterCode.click();
            rememberMeButton.click();
        }
    }

    public boolean isRememberEmailMode(String email) {
        try {
            emailInput.sendKeys(email);
            return false;
        } catch (NoSuchElementException e) {
            System.out.println("In login remember_user version: email input is not exist on this version.");
            return true;
        }
    }

    public void loginWithGoogle() {
        googleSignInBtn.click();
    }
}

/*
*
*   By emailInput = By.id("username");
    By passwordInput = By.id("password");
    By showPasswordBtn = By.cssSelector("#password-help > button");
    By forgotPasswordLink = By.cssSelector("i18n-string[data-key='login.form.forgotPassword']");
    By rememberMeCheckBox = By.cssSelector(".ToggleInputWrapper__CheckboxInnerSpan-sc-1g4e74f-2.KaiQn");

* */


/*
*  //Version1 classic login page-----------------------------------------------------------------
    //By emailInput = By.id("#username");//todo:Why ues id cannot locate?


    //Version2 loginPage-------------------------------------------------------------------
    By signUpBtn = By.cssSelector(".signup-link");
    By classicLoginBtn = By.cssSelector("a[role='button']");
    By changeEmailBtn = By.cssSelector("i18n-string[data-key='login.smartLogin.changeEmail']");
    By v2ShowPasswordBtn = By.cssSelector("i18n-string[data-key='login.form.showPassword']");
    By v2PasswordInput = By.id("current-password");
    By forgetPasswordAlink = By.cssSelector("i18n-string[data-key='login.form.forgotPassword']");



    //same for two versions -----------------------------------------------------------------------------------

    By loginBtn = By.id("loginBtn");
    By googleSignInBtn = By.cssSelector("button[data-test-id='google-sign-in']");
    By microSoftSignInBtn = By.cssSelector("button[data-test-id='microsoft-sign-in']");
    By ssoLoginBtn = By.cssSelector("button[data-test-id='sso-login-button']");
    By privacyPolicyLinc = By.cssSelector("i18n-string[data-key='login.footer.privacy']");
* */