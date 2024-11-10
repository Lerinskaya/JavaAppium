package lib;

import io.appium.java_client.AppiumDriver;
import junit.framework.TestCase;
import lib.ui.WelcomePageObject;
import lib.ui.factories.WelcomePageObjectFactory;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.time.Duration;

public class CoreTestCase extends TestCase {

    protected RemoteWebDriver driver;

    @Override
    protected void setUp() throws Exception {

        super.setUp();

        driver = lib.Platform.getInstance().getDriver();
        this.rotateScreenPortrait();
        this.skipWelcomePage();
        this.openWikiPageForMobileWeb();
    }

    @Override
    protected void tearDown() throws Exception {
        if (driver != null) {
            driver.quit();
        }

        super.tearDown();
    }

    protected void rotateScreenPortrait(){
        if(driver instanceof AppiumDriver){
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.rotate(ScreenOrientation.PORTRAIT);
        } else {
            System.out.println("Method rotateScreenPortait() does nothing for platform" + Platform.getInstance().getPlatformVar());
        }
    }

    protected void rotateScreenLandscape(){
        if(driver instanceof AppiumDriver){
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.rotate(ScreenOrientation.LANDSCAPE);
        } else {
            System.out.println("Method rotateScreenPortait() does nothing for platform" + Platform.getInstance().getPlatformVar());
        }
    }

    protected void backgroundApp(int seconds){
        if(driver instanceof AppiumDriver){
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.runAppInBackground(Duration.ofSeconds(seconds));
        } else {
            System.out.println("Method rotateScreenPortait() does nothing for platform" + Platform.getInstance().getPlatformVar());
        }
    }

    protected void openWikiPageForMobileWeb(){
        if(Platform.getInstance().isWEB()) {
            driver.get("https://en.m.wikipedia.org/");
        } else {
            System.out.println("Method rotateScreenPortait() does nothing for platform" + Platform.getInstance().getPlatformVar());
        }
    }

    private void skipWelcomePage(){

        if(Platform.getInstance().isWEB()) {
            System.out.println("Method skipWelcomePage() does nothing for platform" + Platform.getInstance().getPlatformVar());
        } else {
            AppiumDriver driver = (AppiumDriver) this.driver;
            WelcomePageObject WelcomePageObject = WelcomePageObjectFactory.get(driver);
            WelcomePageObject.clickSkip();
        }
    }
}
