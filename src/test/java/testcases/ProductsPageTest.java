package testcases;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pagefactory.pageobjects.dashboardmodule.DashBoardPage;
import pagefactory.pageobjects.librarymodule.ImportPage;
import pagefactory.pageobjects.librarymodule.ProductsPage;
import pagefactory.pageobjects.loginmodule.LandingPage;
import pagefactory.pageobjects.loginmodule.LoginPage;

import java.io.File;
import java.time.LocalDate;
@Test(priority = 3)
public class ProductsPageTest extends BaseTest {
    protected SoftAssert softAssert = new SoftAssert();
    protected ProductsPage productPage;
    protected DashBoardPage defaultDashBoardPage;

    @BeforeClass
    public void gotoContactPage() {
        driver.get("https://www.hubspot.com/");
        LandingPage landingPage = PageFactory.initElements(driver, LandingPage.class);
        landingPage.gotoLoginPage();
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.login("anennifer7715+11@gmail.com", "Ajennifer7715");
        defaultDashBoardPage = PageFactory.initElements(driver, DashBoardPage.class);
        defaultDashBoardPage.gotoProductsPage();
        productPage = PageFactory.initElements(driver, ProductsPage.class);
    }

    @Test(priority = 0)
    public void testOpenProductsPage() {
        Assert.assertEquals(productPage.getPageHeader(), "Products", "Verify if navigate to 'Products' page ,should get the page Header. ");
    }

    @Test(priority = 1)
    public void testExportProductsList() {
        String dialogHeader = productPage.openExportPopDialog();
        softAssert.assertEquals(dialogHeader, "Export view", "Verify if the 'Export view' pop-dialog is open,should get the dialog header");
        productPage.clickSubmitExportProductButton();

        String header = productPage.getExportSuccessAlertHeader();

        softAssert.assertEquals(header, "Success.", "Verify if export success toast Alert shown");
        if (!productPage.clickDownLoadExportFileLink()) {
            //todo:
        }
        sleepForSeconds(5);
        String filePath = "C:\\Users\\belle\\Downloads\\hubspotFiles";
        LocalDate currentDate = LocalDate.now();
        String fileNameSubStr = "hubspot-crm-exports-all-records-"+currentDate;
        File downloadFolder = new File(filePath);
        File[] files = downloadFolder.listFiles();
        assert files != null;
        System.out.println("number of files in hubspotFiles folder:" + files.length);
        for (File file : files) {
            if (file.isFile()) {
                softAssert.assertTrue(file.getName().contains(fileNameSubStr), "Verify if export products list exist in computer's 'Downloads folder''");
                System.out.println("File name: " + file.getName());
                break;
            }
        }

        softAssert.assertAll();
    }


    @Test(priority = 2)
    public void testCloneAction() {
        String editProductDialogHeader = productPage.cloneProduct("soup");
        System.out.println(editProductDialogHeader);
        softAssert.assertEquals(editProductDialogHeader, "Edit product");
        String editToastMsg = productPage.saveProductEditForm();
        softAssert.assertEquals(editToastMsg, "Success.");
        //todo:check
        softAssert.assertAll();
    }


    @Test(priority = 3)
    public void testSearchAndDeleteProduct() {
        productPage.searchProductAndDelete("new");
        softAssert.assertEquals(productPage.getDeleteSuccessToastStr(), "Success.");
        softAssert.assertEquals(productPage.getNoProductsMatchInTableMsgStr(), "No Products match the current filters.");
        softAssert.assertAll();
    }

    @Test(priority = 4)
    public void testImportProductList() {
        String fileName = "hubspot-crm-import.xlsx";
        ImportPage importPage = productPage.gotoImportPage();
        System.out.println(importPage.getImportPageHeader());
        softAssert.assertEquals(importPage.getImportPageHeader(), "Imports");
        importPage.startImport("COMPUTER", "SINGLE", "ONE", "PRODUCTS", "CREATE_ONLY", "English", "Canada");
        softAssert.assertEquals(importPage.getImportNoErrorMsg(), "Import completed with no errors");
        softAssert.assertTrue(importPage.getImportReadyAlertStr().contains(fileName), "Expected alert message contains " + fileName + " , but result is " + importPage.getImportReadyAlertStr());
        defaultDashBoardPage.gotoProductsPage();
        softAssert.assertAll();

    }
}
