package listensers;

import io.qameta.allure.Allure;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class CRMTestListener implements ITestListener, ISuiteListener {
    public void onTestStart(ITestResult result) {
    }

    public void onTestSuccess(ITestResult result) {
    }

    public void onTestFailure(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        System.out.println(testName + " failed!");

        ITestContext context = result.getTestContext();
        WebDriver driver = (WebDriver) context.getAttribute("driver");

        System.out.println("Get driver " + driver);

        saveFailureScreenshotInHardDisk(driver, testName);

        // attach screenshots to local hard disk
//        saveFailureScreenshotInHardDisk(driver,testName);

        // attach screenshots to report
        //saveFailureScreenshotInAllureReport(driver);
    }


    public void onTestSkipped(ITestResult result) {

    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    public void onTestFailedWithTimeout(ITestResult result) {
        this.onTestFailure(result);
    }

    public void saveFailureScreenshotInHardDisk(WebDriver driver, String testcaseName) {
        File screenshotOnRAM = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String tempScreenshotName = screenshotOnRAM.getName();
        File destDir = new File("screenshots");
        if (!destDir.exists()) {
            destDir.mkdirs();
        }
        String newScreenshotName = "screenshots/failed_" + testcaseName + "_" + getLocalDateTimeWithFormat() + ".png";


        try {
            FileUtils.copyFile(screenshotOnRAM, new File(newScreenshotName));
        } catch (IOException e) {
            System.out.println(e);
        }

        try {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment("Screenshot", new ByteArrayInputStream(screenshot));
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    private String getLocalDateTimeWithFormat () {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmm");
        return currentDateTime.format(formatter);
    }
//    @Attachment(value = "Screenshot", type = "image/png")
//    public byte[] addFailureScreenshotToAllureReport(WebDriver driver) {
//        byte[] screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
//        Allure.addAttachment("Screenshot",new ByteArrayInputStream(screenshot));
//        return screenshot;
//    }

//        public void takeFullScreenshot (WebDriver driver, String screenshotPath){
//            Screenshot s = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
//            try {
//                ImageIO.write(s.getImage(), "PNG", new File("<< file path>>"));
//            } catch (IOException e) {
//                System.out.println(e);
//            }
//        }

        public void takeElementScreenshotByDriver (WebDriver driver, WebElement ele){
            // Get screenshot of the visible part of the web page
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            // Convert the screenshot into BufferedImage
            BufferedImage fullScreen = null;
            try {
                fullScreen = ImageIO.read(screenshot);
            } catch (IOException e) {
                System.out.println(e);
            }

            //Find location of the webelement logo on the page
            Point location = ele.getLocation();

            //Find width and height of the located element logo
            int width = ele.getSize().getWidth();
            int height = ele.getSize().getHeight();

            //cropping the full image to get only the logo screenshot
            BufferedImage logoImage = fullScreen.getSubimage(location.getX(), location.getY(),
                    width, height);
            try {
                ImageIO.write(logoImage, "png", screenshot);

                //Save cropped Image at destination location physically.
                FileUtils.copyFile(screenshot, new File("particularElementScreenshots\\" + ele.getTagName() + ".PNG"));
            } catch (IOException e) {
                System.out.println(e);
            }
        }

        /*
        * We can capture screenshots of a particular element in Selenium 4.0  with
        * the getScreenshotAs (OutputType.File) method of the WebElement class.*/
        public void takeElementScreenshotByElement(WebElement element) {

            // capture screenshot with getScreenshotAs() of the WebElement class
            File f = element.getScreenshotAs(OutputType.FILE);

            try {
                FileUtils.copyFile(f, new File("particularElementScreenshots\\logoScreeshot.png"));
            } catch (IOException e) {
                System.out.println(e);
            }
        }



    }
