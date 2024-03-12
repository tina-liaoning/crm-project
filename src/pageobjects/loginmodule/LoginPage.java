package pageobjects.loginmodule;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pageobjects.BaseWebPage;


public class LoginPage extends BaseWebPage {
    WebElement emailInput;
    WebElement passwordInput;
    WebElement rememberMeBtn;
    WebElement showPasswordBtn;
    WebElement forgotPasswordLink;
    WebElement googleSignInBtn;
    WebElement loginBtn;

    public LoginPage(WebDriver driver) {
        super(driver);
        passwordInput = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[data-test-id='password-input-field']")));
        showPasswordBtn = driver.findElement(By.cssSelector("#current-password-help >button"));
        forgotPasswordLink = driver.findElement(By.cssSelector("i18n-string[data-key='login.form.forgotPassword']"));
        loginBtn = driver.findElement(By.id("loginBtn"));
        googleSignInBtn = driver.findElement(By.cssSelector("button[data-test-id='google-sign-in']"));
    }

    public String getLoginPageTitle() {
        return driver.getTitle();
    }

    public void login(String email, String password) {
        try {
            driver.findElement(By.id("username")).sendKeys(email);
        } catch (NoSuchElementException e) {
            System.out.println("In login remember user version: email input is not exist on this version.");
        }
        passwordInput.sendKeys(password);
        showPasswordBtn.click();
        loginBtn.click();
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