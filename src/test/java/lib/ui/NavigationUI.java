package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;

abstract public class NavigationUI extends MainPageObject{
    protected static String
        SAVE_OPTION_ID,
        CLOSE_BUTTON,
            CANCEL_BUTTON,
            NAVIGATE_BUTTON_XPATH;

    public NavigationUI(AppiumDriver driver) {
        super(driver);
    }

    public void clickMyLists() {
        if(Platform.getInstance().isAndroid()){
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
        this.waitForElementAndClick(
                NAVIGATE_BUTTON_XPATH,
                "Cannot find navigation back button",
                10);
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
