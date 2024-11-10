package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.WelcomePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IOSWelcomePageObject extends WelcomePageObject {

    static {
        MAIN_TITLE_ID = "id:The free encyclopedia";
        NEXT_BUTTON_XPATH = "xpath://XCUIElementTypeButton[@name=\"Next\"]";
        SKIP_BUTTON_XPATH = "xpath://XCUIElementTypeButton[@name=\"Skip\"]";
        HELP_TEXT_ID = "id:Help make the app better";
    }
    public IOSWelcomePageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
