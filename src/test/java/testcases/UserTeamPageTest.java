package testcases;

import components.TopMenuToolBar;
import enums.Tool;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pagefactory.pageobjects.dashboardmodule.DashBoardPage;
import pagefactory.pageobjects.loginmodule.LandingPage;
import pagefactory.pageobjects.loginmodule.LoginPage;
import pagefactory.pageobjects.settingModule.UserTeamPage;

import java.util.List;
@Test(priority = 4)
public class UserTeamPageTest extends BaseTest {
    SoftAssert softAssert = new SoftAssert();
    DashBoardPage defaultDashBoardPage;
    TopMenuToolBar topMenuToolBar;
    UserTeamPage userTeamPage;
    long randomNum = Math.round(Math.random() * 100);
    String inviteTeamEmail = "teamMember" + Math.round(Math.random() * 100) + "@gmail.com";
    String inviteMultiEmail = "email" + randomNum + "@hotmail.com,email" + (randomNum + 1) + "@gmail.com,email" + (randomNum + 2) + "@yahoo.com";


    @BeforeClass
    public void gotoContactPage() {
        driver.get("https://www.hubspot.com/");
        LandingPage landingPage = PageFactory.initElements(driver, LandingPage.class);
        landingPage.gotoLoginPage();
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.login("anennifer7715+11@gmail.com", "Ajennifer7715");
        defaultDashBoardPage = PageFactory.initElements(driver, DashBoardPage.class);
        topMenuToolBar = defaultDashBoardPage.getTopMenuToolBar();
        userTeamPage = PageFactory.initElements(driver,UserTeamPage.class);

    }

    @Test(priority = 1)
    public void testQuickInviteYourTeamWithValidEmail() {
        topMenuToolBar.goTo(Tool.PERSONAL_ACCOUNT);
        topMenuToolBar.startInviteTeam();
        userTeamPage.chooseInviteOption("quick", inviteTeamEmail);
        String msgText = userTeamPage.getInvitationSentMsgText();
        Assert.assertEquals(msgText, "Invitation sent.");
    }

    @Test(dependsOnMethods = "testQuickInviteYourTeamWithValidEmail")
    public void testQuickInviteYourTeamWithDuplicatedEmail() {
        topMenuToolBar.goTo(Tool.PERSONAL_ACCOUNT);
        topMenuToolBar.startInviteTeam();
        userTeamPage.chooseInviteOption("quick", inviteTeamEmail);
        String msgText = userTeamPage.getInvitationSentMsgText();
        Assert.assertEquals(msgText, "This email address already belongs to a user on this account.");
    }

    @Test(priority = 3)
    public void testCustomInviteTeamWithValidMultiEmailFromScratch() {
        topMenuToolBar.goTo(Tool.PERSONAL_ACCOUNT);
        topMenuToolBar.startInviteTeam();
        userTeamPage.chooseInviteOption("custom", inviteMultiEmail);
        userTeamPage.setUserAccessLevels("scratch");
        String successText = userTeamPage.getSuccessMsgTextByCustomInvite();
        List<String> addedEmailListFromPage = userTeamPage.returnEmailStrList();
        for (String s : addedEmailListFromPage) {
            softAssert.assertTrue(inviteMultiEmail.contains(s),"Verify if "+ inviteMultiEmail + " contains "+ s);
        }
        softAssert.assertEquals(successText,"Success! You've invited "+addedEmailListFromPage.size()+" users.");
       //todo:assert all adding email in table
        softAssert.assertAll();
    }

    @Test(priority = 4)
    public void testInviteTeamFromCSVFile(){
        topMenuToolBar.goTo(Tool.SETTING);
        userTeamPage.kk();
    }


}
