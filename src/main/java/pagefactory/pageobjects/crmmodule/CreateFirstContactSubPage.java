package pagefactory.pageobjects.crmmodule;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pagefactory.pageobjects.core.BaseCRMAppPage;


public class CreateFirstContactSubPage extends BaseCRMAppPage {
    WebElement firstNameInput;
    WebElement lastNameInput;
    WebElement emailInput;
    WebElement createYourContactBtn;
    public CreateFirstContactSubPage(WebDriver driver) {
        super(driver);
        firstNameInput = driver.findElement(By.name("first-name"));
        lastNameInput = driver.findElement(By.name("last-name"));
        emailInput = driver.findElement(By.name("email"));
        createYourContactBtn = driver.findElement(By.cssSelector("button[data-test-id='flow-body-next-button']"));
    }

    public void createFirstContact(String firstName,String lastName,String email){
        firstNameInput.sendKeys(firstName);
        lastNameInput.sendKeys(lastName);
        emailInput.sendKeys(email);
        createYourContactBtn.click();
    }
}
