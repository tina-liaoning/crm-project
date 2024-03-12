package pageobjects.loginmodule;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class startedForFreePageVersion1 {
    WebDriver driver;

    public startedForFreePageVersion1(WebDriver driver){
        this.driver = driver;
    }

    protected WebElement marketingAndAdvertisingIndustryBtn = driver.findElement(By.id("bsb-2"));
    protected WebElement techSoftwareServiceIndustryBtn = driver.findElement(By.id("bsb-3"));
    protected WebElement realEstateIndustryBtn = driver.findElement(By.id("bsb-4"));
    protected WebElement financialIndustryBtn = driver.findElement(By.id("bsb-5"));
    protected WebElement healthWellnessFitnessIndustryBtn = driver.findElement(By.id("bsb-6"));
    protected WebElement educationIndustryBtn = driver.findElement(By.id("bsb-7"));


}
