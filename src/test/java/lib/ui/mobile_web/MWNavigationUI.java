package lib.ui.mobile_web;

import lib.ui.NavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWNavigationUI extends NavigationUI {
    static {
        SAVE_OPTION_ID = "id:tabbar-save";
        CLOSE_BUTTON = "id:Close";
        NAVIGATE_BUTTON_XPATH = "id:Back";
        CANCEL_BUTTON = "xpath://XCUIElementTypeStaticText[@name=\"Cancel\"]";
    }

    public MWNavigationUI(RemoteWebDriver driver) {
        super(driver);
    }
}