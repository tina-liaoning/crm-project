package testcases;

import enums.ContactBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pagefactory.pageobjects.dashboardmodule.DashBoardPage;
import pagefactory.pageobjects.crmmodule.ContactPersonalProfilePage;
import pagefactory.pageobjects.crmmodule.ContactsPage;
import pagefactory.pageobjects.crmmodule.CreateFirstContactSubPage;


import java.util.List;
import java.util.Random;

@Test(priority = 1)
public class ContactsPageTest extends BaseTest {
    protected ContactsPage contactsPage;
    protected String firstName;
    private int randomNumber = (int) (Math.random() * 100);
    private String email = "contact" + randomNumber + "@gmail.com";
    ;
    protected SoftAssert softAssert = new SoftAssert();

    @BeforeClass
    public void gotoContactPage() {
        login();
        DashBoardPage defaultDashBoardPage = PageFactory.initElements(driver, DashBoardPage.class);
        defaultDashBoardPage.gotoContactsPage();
        contactsPage = PageFactory.initElements(driver, ContactsPage.class);
    }

    @Test(priority = 1)
    public void createContact() {
        firstName = "client" + randomNumber;
        if (contactsPage.isFirstTimeCreateContact()) {
            softAssert.assertTrue(contactsPage.getFirstTimeCreateContactPopupHeaderText().contains("first time"));
            CreateFirstContactSubPage createFirstContactSubPage = PageFactory.initElements(driver, CreateFirstContactSubPage.class);
            createFirstContactSubPage.createFirstContact("Tom", "Nolan", "firstContact@gmail.com");
        } else {
            //Step1
            contactsPage.openCreateContactFormePopUp();
            softAssert.assertEquals(contactsPage.getCreateContactFormHeaderText(), "Create contact", "Verify if create_contact_form is open,the header same as expacted.");

            //Step2
            contactsPage.createNewContact(email, firstName, "Lee");
            ContactPersonalProfilePage contactPersonalProfilePage = PageFactory.initElements(driver, ContactPersonalProfilePage.class);
            System.out.println("tttt:" + contactPersonalProfilePage.getContactProfileHighlightTitleText());
            System.out.println("eee:" + contactPersonalProfilePage.getContactEmailSpan());
            softAssert.assertTrue(contactPersonalProfilePage.getContactProfileHighlightTitleText().contains(firstName), "Verify if newly created contact_name is showing on personal profile page.");

            // Step3
            contactPersonalProfilePage.goBackToContactsPage();
            softAssert.assertEquals(contactsPage.getNewlyContactEmailFromContactsTable(), email, "Verify if newly created contact email is showing in contact list of contact page.");
//            softAssert.assertEquals();
        }
        softAssert.assertAll();
    }

    @Test(priority = 2, dataProvider = "searchContactData")
    public void searchContact(ContactBy phoneOrNameOrPhone, String keywordStr) { //by name ,by phone, by email
        contactsPage.searchContact(keywordStr);
        List<String> resultList = contactsPage.getContactResultAfterSearching(phoneOrNameOrPhone);
        for (String s : resultList) {
            softAssert.assertTrue(s.toLowerCase().contains(keywordStr.toLowerCase()), "Verify if searching result " + s + " contain key words " + keywordStr);
        }
        softAssert.assertAll();
    }

    @DataProvider(name = "searchContactData")
    private Object[][] getSearchContactData() {
        // Provide multiple sets of username and password
        return new Object[][]{
                {ContactBy.NAME, "Zoro"},
                {ContactBy.EMAIL, "fff@gmail.com"},
                {ContactBy.PHONE, "5149991717"},
        };
    }


    @Test(priority = 3)
    public void testEditContactPhoneNumberByFormatMode() {
        String newPhoneNumber = " 51466" + getRandomNumber() + "888" + getRandomNumber();
        contactsPage.searchContact("fff@gmail.com");
        contactsPage.editContactPhoneNumber("canada", newPhoneNumber);
        String phoneFromTableField = contactsPage.getPhoneNumberFromFirstRow();
        Assert.assertTrue(phoneFromTableField.contains(newPhoneNumber.trim()), "Phone number from table "
                +phoneFromTableField+" contains executive " + newPhoneNumber);
    }

//    @Test(priority = 4)
    @Ignore
    public void testEditContactPhoneNumberByNonFormatMode() {
        String newPhoneNumber = " 51466" + getRandomNumber() + "888" + getRandomNumber();
        contactsPage.searchContact("fff@gmail.com");

        contactsPage.editContactPhoneNumber("canada", newPhoneNumber);
//        Assert.assertEquals(contactsPage.getPhoneNumberFromFirstRow(), newPhoneNumber, "Verify new phone number into list after edit");
    }

    @Test(priority = 5)
    public void testDeleteFirstRowFromContactTableView() {
        contactsPage.searchContact(email);
        List<String> resultList = contactsPage.getContactResultAfterSearching(ContactBy.EMAIL);
        if (resultList.size() == 1) {
            contactsPage.delectFirstRowContactByBulkToolBar();
            contactsPage.searchContact(email);
            resultList = contactsPage.getContactResultAfterSearching(ContactBy.EMAIL);
            Assert.assertEquals(resultList.size(), 0, "Error:record didn't delete from the table view");
        } else {
            System.out.println("Error: email must be unique,but the search results of " + email + " are " + resultList.size());
        }
    }

//    //POM way to create a contact
//    @Test(priority = 5)
//    public void testCreateContact() {
//        ContactsPage contactsPage = new ContactsPage(chromedriver);
//        boolean isFirstTime = contactsPage.isFirstTimeCreateContact();
//        if (isFirstTime) {
//            CreateFirstContactSubPage createFirstContactSubPage = new CreateFirstContactSubPage(chromedriver);
//            createFirstContactSubPage.createFirstContact("Tom", "Nolan", "firstContact@gmail.com");
//        } else {
//            contactsPage.createNewContact("secondContact@gmail.com", "Lily", "Lee");
//        }
//    }

    private int getRandomNumber() {
        Random r = new Random();
        int min = 0;
        int max = 9;
        return r.nextInt((max - min) + 1) + min;
    }

}