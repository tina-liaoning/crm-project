package pagefactory.pageobjects.core;

import components.LeftMenu;
import components.TopMenuToolBar;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
//import org.slf4j.Logger;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class BaseCRMAppPage extends BasePage {

    protected LeftMenu leftMenu;
    protected TopMenuToolBar topMenuToolBar;


    public BaseCRMAppPage(WebDriver driver) {
        super(driver);
        topMenuToolBar = PageFactory.initElements(driver, TopMenuToolBar.class);
        leftMenu = PageFactory.initElements(driver, LeftMenu.class);
    }

    public void setConsoleHandler(Logger logger) {
        // Set up a console handler
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.ALL); // Adjust the log level as needed

        // Add the console handler to the logger
        logger.addHandler(consoleHandler);
    }


}
