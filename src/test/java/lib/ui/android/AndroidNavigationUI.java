package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.NavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidNavigationUI extends NavigationUI {
    static {
        SAVE_OPTION_ID = "id:org.wikipedia:id/nav_tab_reading_lists";
        NAVIGATE_BUTTON_XPATH = "xpath://android.widget.ImageButton[@content-desc=\"Navigate up\"]";
    }

    public AndroidNavigationUI(RemoteWebDriver driver) {
        super(driver);
    }
}
