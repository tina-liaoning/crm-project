package pageobjects.loginmodule;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignUpPage {
    WebDriver driver;
    public SignUpPage(WebDriver driver){
       this.driver = driver;
    }

    protected By emailInput = By.id("UIFormControl-1");
    protected By emailSubmitBtn = By.cssSelector("button[type='submit']");

}
