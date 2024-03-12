package testcases;

import enums.DocumentLocation;
import enums.DocumentTableColumn;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pagefactory.pageobjects.dashboardmodule.DashBoardPage;
import pagefactory.pageobjects.librarymodule.DocumentsPage;

@Test(priority = 2)
public class DocumentsPageTest extends BaseTest {
    protected SoftAssert softAssert = new SoftAssert();
    protected DocumentsPage documentsPage;
    String folderName = "Folder" + Math.round(Math.random() * 10);
    String filePath = "C:\\Users\\belle\\Downloads\\";
    String fileName = "generated.pdf"; //"C:\Users\belle\Downloads\generated.pdf"

    @BeforeClass
    public void gotoContactPage() {
        login();
        DashBoardPage defaultDashBoardPage = PageFactory.initElements(driver, DashBoardPage.class);
        defaultDashBoardPage.gotoDocumentsPage();
        documentsPage = PageFactory.initElements(driver, DocumentsPage.class);
    }


    @Test(priority = 1)
    public void testUploadDocument() {
        softAssert.assertEquals(documentsPage.getDocumentsPageHeader(), "Documents"
                , "Verify if the 'Documents' page open,and can get page 'Header' text as Expected");
        String uploadPopDialogHeader = documentsPage.openUploadDocumentPop();
        //todo:Assert System.out.println(uploadPopDialogHeader); //Upload document
        documentsPage.chooseFileFrom(DocumentLocation.COMPUTER, filePath + fileName);
        String lastedUploadFileName = documentsPage.getLatestDocByColumnName(DocumentTableColumn.NAME);
        //todo:Assert System.out.println(lastedUploadFileName);
        softAssert.assertAll();
    }

    @Test(priority = 2)
    public void testCreatFolder() {
        documentsPage.openCreatedFolderPopup();
        //todo Assert: System.out.println(documentsPage.openCreatFolderPopup()); //Create new folder
        documentsPage.createFolder(folderName);
        //todo Assert: System.out.println(documentsPage.getLatestUploadFolderByColumn(DocumentTableColumn.NAME));
    }

    @Test(priority = 3)
    public void testRenameFolder() {
        String newFolderName = "Pdfs";
        documentsPage.selectFolder(folderName);
        String headerText = documentsPage.openRenameFoldPopDialog();
        System.out.println("Get header:" + headerText);
        softAssert.assertEquals(headerText, "Edit folder", "verify is 'Rename pop-dialog' is open,and if can get the Header of it");
        documentsPage.performRenameFolder(newFolderName);
        softAssert.assertTrue(documentsPage.getCurrentFolderText().contains(newFolderName), "verify if the new folder name is update in table list");
        softAssert.assertAll();
    }

    @Test(priority = 4)
    public void testMoveFileToFolderThroughActions() {
        String folderName = "pdf";
        documentsPage.hoverOnFileToClickMoveBtn(fileName);
        softAssert.assertEquals(documentsPage.getMoveToFolderPopDialogHeader(),"Find the pdf folder");

        documentsPage.chooseFolderToMove(folderName);


        //todo verify if file move into this folder

    }
}
