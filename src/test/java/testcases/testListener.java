package testcases;

import enums.ContactBy;
import io.qameta.allure.*;
import listensers.CRMTestListener;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pagefactory.pageobjects.crmmodule.ContactsPage;
import pagefactory.pageobjects.dashboardmodule.DashBoardPage;
import pagefactory.pageobjects.loginmodule.LandingPage;
import pagefactory.pageobjects.loginmodule.LoginPage;

import java.io.ByteArrayInputStream;

//@Listeners(CRMTestListener.class)
public class testListener extends BaseTest{
    DashBoardPage defaultDashBoardPage;
    LoginPage loginPage;

    @BeforeClass
    public void gotoContactPage() {
        driver.get("https://www.hubspot.com/");
        LandingPage landingPage = PageFactory.initElements(driver, LandingPage.class);
        landingPage.gotoLoginPage();
        loginPage = PageFactory.initElements(driver, LoginPage.class);

    }


    @Test(priority = 1)
    @Severity(SeverityLevel.CRITICAL)
    public void testDashboard (){
        loginPage.login("ajennifer7715+11@gmail.com", "Ajennifer771");
        defaultDashBoardPage = PageFactory.initElements(driver,DashBoardPage.class);
        System.out.println(driver.getCurrentUrl());
        Assert.assertEquals(driver.getCurrentUrl(),"https://app.hubspot.com/reports-dashboard","Verify if get dashboard url https://app.hubspot.com/reports-dashboard");
    }

//    @AfterMethod
//    public void addScreenshot() {
//        byte[] screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
//        Allure.addAttachment("Screenshot",new ByteArrayInputStream(screenshot));
//    }


}
