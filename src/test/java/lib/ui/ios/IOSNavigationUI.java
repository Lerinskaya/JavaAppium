package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.NavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IOSNavigationUI extends NavigationUI {
    static {
        SAVE_OPTION_ID = "id:tabbar-save";
        CLOSE_BUTTON = "id:Close";
        NAVIGATE_BUTTON_XPATH = "id:Back";
        CANCEL_BUTTON = "xpath://XCUIElementTypeStaticText[@name=\"Cancel\"]";
    }

    public IOSNavigationUI(RemoteWebDriver driver) {
        super(driver);
    }
}
