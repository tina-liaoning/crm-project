package pagefactory.pageobjects.loginmodule;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pagefactory.pageobjects.core.BaseCRMAppPage;


public class SignUpPage extends BaseCRMAppPage {
    @FindBy(id="UIFormControl-1")
   protected WebElement emailInput;
    @FindBy(css = "button[type='submit']")
   protected WebElement emailSubmitBtn;

    public SignUpPage(WebDriver driver){
        super(driver);
    }

}

/*
*   protected By emailInput = By.id("UIFormControl-1");
    protected By emailSubmitBtn = By.cssSelector("button[type='submit']");
* */
