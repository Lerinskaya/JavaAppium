package lib;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import junit.framework.TestCase;
import lib.ui.WelcomePageObject;
import lib.ui.factories.WelcomePageObjectFactory;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.FileOutputStream;
import java.time.Duration;
import java.util.Properties;

public class CoreTestCase {

    protected RemoteWebDriver driver;

    @Before
    @Step("Run driver and session")
    public void setUp() throws Exception {
        driver = lib.Platform.getInstance().getDriver();
        this.createAllurePropertyFile();
        this.rotateScreenPortrait();
        this.skipWelcomePage();
        this.openWikiPageForMobileWeb();
    }

    @After
    @Step("Remove driver and session")
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Step("Rotate screen to portait mode")
    protected void rotateScreenPortrait(){
        if(driver instanceof AppiumDriver){
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.rotate(ScreenOrientation.PORTRAIT);
        } else {
            System.out.println("Method rotateScreenPortait() does nothing for platform" + Platform.getInstance().getPlatformVar());
        }
    }

    @Step("Rotate screen to landscape mode")
    protected void rotateScreenLandscape(){
        if(driver instanceof AppiumDriver){
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.rotate(ScreenOrientation.LANDSCAPE);
        } else {
            System.out.println("Method rotateScreenPortait() does nothing for platform" + Platform.getInstance().getPlatformVar());
        }
    }

    @Step("Send mobile app to background (This method does nothing for mobile web)")
    protected void backgroundApp(int seconds){
        if(driver instanceof AppiumDriver){
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.runAppInBackground(Duration.ofSeconds(seconds));
        } else {
            System.out.println("Method rotateScreenPortait() does nothing for platform" + Platform.getInstance().getPlatformVar());
        }
    }

    @Step("Open Wikipedia website for mobile web (does nothing for ios/android)")
    protected void openWikiPageForMobileWeb(){
        if(Platform.getInstance().isWEB()) {
            driver.get("https://en.m.wikipedia.org/");
        } else {
            System.out.println("Method rotateScreenPortait() does nothing for platform" + Platform.getInstance().getPlatformVar());
        }
    }

    @Step("Skip welcome page (does nothing for mobile web)")
    private void skipWelcomePage(){

        if(Platform.getInstance().isWEB()) {
            System.out.println("Method skipWelcomePage() does nothing for platform" + Platform.getInstance().getPlatformVar());
        } else {
            AppiumDriver driver = (AppiumDriver) this.driver;
            WelcomePageObject WelcomePageObject = WelcomePageObjectFactory.get(driver);
            WelcomePageObject.clickSkip();
        }
    }

    private void createAllurePropertyFile() {
        String path = System.getProperty("allure.results.directory");
        try {
            Properties props = new Properties();
            FileOutputStream fos = new FileOutputStream(path + "/environment.properties");
            props.setProperty("Environment", Platform.getInstance().getPlatformVar());
            props.store(fos, "See https://docs.qameta.io/allure/#_environment");
            fos.close();
        } catch (Exception e) {
            System.err.println("IO problem when writing allure properties file");
            e.printStackTrace();
        }
    }
}
