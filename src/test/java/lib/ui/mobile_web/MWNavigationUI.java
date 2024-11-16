package lib.ui.mobile_web;

import lib.ui.NavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWNavigationUI extends NavigationUI {
    static {
        SAVE_OPTION_ID = "css:a[data-event-name='menu.watchlist']";
        BURGER_BUTTON = "css:#mw-mf-main-menu-button";
        CLOSE_BUTTON = "id:Close";
        NAVIGATE_BUTTON_XPATH = "id:Back";
    }

    public MWNavigationUI(RemoteWebDriver driver) {
        super(driver);
    }
}
