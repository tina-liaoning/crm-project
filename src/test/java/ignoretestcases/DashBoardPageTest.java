//package ignoretestcases;
//
//import org.openqa.selenium.support.PageFactory;
//import org.testng.Assert;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.Ignore;
//import org.testng.annotations.Test;
//import org.testng.asserts.SoftAssert;
//import pagefactory.pageobjects.dashboardmodule.DashBoardPage;
//import pagefactory.pageobjects.loginmodule.LandingPage;
//import pagefactory.pageobjects.loginmodule.LoginPage;
//import test_senairios.BaseTest;
//
//@Ignore
//public class DashBoardPageTest extends BaseTest {
//    DashBoardPage defaultDashBoardPage;
//    SoftAssert softAssert = new SoftAssert();
//
//    @BeforeClass
//    public void goToDashboardPage() {
//        LandingPage landingPage = PageFactory.initElements(driver, LandingPage.class);
//        landingPage.gotoLoginPage();
//        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
//        loginPage.login("anennifer7715+11@gmail.com", "Ajennifer7715");
//        defaultDashBoardPage = PageFactory.initElements(driver, DashBoardPage.class);
//    }
//
//    @Test(priority = 0)
//    public void verifyGotoCrmContactsPage() {
//        defaultDashBoardPage.gotoContactsPage();
//        System.out.println(driver.getTitle());
//        Assert.assertTrue(driver.getTitle().contains("Contacts | All contacts"));
//    }
//
//    //After User setting
//
////    @Test(priority = 1)
////    public void verifyDefaultDashboardAppear() {
////        String dashBoardHeader = defaultDashBoardPage.getDefaultDashboardHeaderText();
//////        System.out.println(dashBoardHeader);
////        Assert.assertEquals(dashBoardHeader, "User Guides","Verify if success goto the Dashboard page after login and if text of 'Dashbaord header text match ");
////    }
//
////    @Test(priority = 2)
////    public void verifyUserGuidesMenuMatchingSubSectionHeader() {
////        for (WebElement currentUserGuideMenuBtn : defaultDashBoardPage.getUserGuidesMenuList()) {
////            currentUserGuideMenuBtn.click();
////            String guideMenuBtnText = currentUserGuideMenuBtn.getText();
////            String guideMenuBtnTextAfterRemoveWordGuide = guideMenuBtnText.substring(0, guideMenuBtnText.lastIndexOf(" "));
//////            System.out.println("Menu text without word 'Guide': " + guideMenuBtnTextAfterRemoveWordGuide);
////            String headerText = defaultDashBoardPage.getSubSectionHeaderText();
//////            System.out.println("User guide subsection header:" + headerText);
////            softAssert.assertTrue(headerText.contains(guideMenuBtnTextAfterRemoveWordGuide));
////        }
////        softAssert.assertAll();
////    }
//
//
//
//}
