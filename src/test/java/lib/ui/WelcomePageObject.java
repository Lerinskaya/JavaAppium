package lib.ui;

import io.appium.java_client.AppiumDriver;

public abstract class WelcomePageObject extends MainPageObject{

    protected static String
    MAIN_TITLE_ID,
    NEXT_BUTTON_XPATH,
            SKIP_BUTTON_XPATH,
    HELP_TEXT_ID;

    public WelcomePageObject(AppiumDriver driver) {
        super(driver);
    }

    public void waitForMainTitle() {
        this.waitForElement(MAIN_TITLE_ID, "Cannot find main page description");
    }

    public void clickNextButton() {
        this.waitForElementAndClick(NEXT_BUTTON_XPATH, "Cannot find next button", 10);
    }

    public void clickSkip() {
        this.waitForElementAndClick(SKIP_BUTTON_XPATH, "Cannot find next button", 10);
    }

    public void waitForHelpText() {
        this.waitForElement(HELP_TEXT_ID, "Cannot find help text");
    }

}
