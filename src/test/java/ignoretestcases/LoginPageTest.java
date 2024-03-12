//package ignoretestcases;
//
//import customized_annotations.Environment;
//import org.openqa.selenium.support.PageFactory;
//import org.testng.Assert;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.Ignore;
//import org.testng.annotations.Test;
//import enums.Browser;
//import enums.OperatingSystem;
//import enums.Platform;
//import pagefactory.pageobjects.dashboardmodule.DashBoardPage;
//import pagefactory.pageobjects.loginmodule.LandingPage;
//import pagefactory.pageobjects.loginmodule.GoogleLoginPage;
//import pagefactory.pageobjects.loginmodule.LoginPage;
//import test_senairios.BaseTest;
//
//
//@Environment(browser = Browser.CHROME, browserVersion = "121 Chrome for Testing (64-bit)",
//        operatingSystem = OperatingSystem.WINDOWS, osVersion = 11,
//        platform = Platform.DESKTOP)
//@Ignore
//public class LoginPageTest extends BaseTest {
//    LoginPage loginPage;
//
//    @BeforeClass
//    public void gotoLoginPage() {
//        LandingPage landingPage = PageFactory.initElements(driver, LandingPage.class);
//        landingPage.gotoLoginPage();
//    }
//
//    @Test(priority = 1)
//    public void testLoginWithValidCredentials() {
//        loginPage = PageFactory.initElements(driver, LoginPage.class);
//        loginPage.login("ajennifer7715+11@gmail.com", "Ajennifer7715");
//        DashBoardPage defaultDashBoardPage = PageFactory.initElements(driver, DashBoardPage.class);
////        System.out.println("Link text: "+defaultDashBoardPage.getTopMenuToolBar().getPersonalAccountLinkText());
//        Assert.assertEquals(defaultDashBoardPage.getTopMenuToolBar().getPersonalAccountLinkText(),"Heathy food store","Test Login success: verify if account name is showing after login");
//    }
//
//    public void testLoginByWithValidGoogleAccount() {
//        GoogleLoginPage googleLoginPage = PageFactory.initElements(driver,GoogleLoginPage.class);
//        loginPage.loginWithGoogle();//todo:not finished
//    }
//
//
//}
