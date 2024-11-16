package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class NavigationUI extends MainPageObject{
    protected static String
        SAVE_OPTION_ID,
        BURGER_BUTTON,
        CLOSE_BUTTON,
            CANCEL_BUTTON,
            NAVIGATE_BUTTON_XPATH;

    public NavigationUI(RemoteWebDriver driver) {
        super(driver);
    }

    public void openNavigation() {
        if (Platform.getInstance().isWEB()) {
            this.waitForElementAndClick(
                    BURGER_BUTTON,
                    "Cannot find navigation button",
                    10);
        } else {
            System.out.println("Method scrollWebPageUp() does nothing for platform" + Platform.getInstance().getPlatformVar());
        }
    }

    public void clickMyLists() {
        if(Platform.getInstance().isAndroid() || Platform.getInstance().isWEB()){
            this.waitForElementAndClick(
                    SAVE_OPTION_ID,
                    "Save option not found",
                    5
            );
        } else {
            this.waitForElementAndClick(
                    SAVE_OPTION_ID,
                    "Save option not found",
                    5
            );
            this.waitForElementAndClick(
                    CLOSE_BUTTON,
                    "Save option not found",
                    5
            );
        }
    }

    public void clickBackButton() {
        if(Platform.getInstance().isAndroid() || Platform.getInstance().isIOS()) {
            this.waitForElementAndClick(
                    NAVIGATE_BUTTON_XPATH,
                    "Cannot find navigation back button",
                    10);
        } else {
            System.out.println("Method scrollWebPageUp() does nothing for platform" + Platform.getInstance().getPlatformVar());
        }
    }

    public void clickCancelButton() {
        this.waitForElementAndClick(
                CANCEL_BUTTON,
                "No cancel button",
                10
        );
    }

    public void backButtonIsAbsent() {
        this.waitForElementAbsence(
                NAVIGATE_BUTTON_XPATH,
                "Returning to the previous page did not occur",
                10);
    }
}
