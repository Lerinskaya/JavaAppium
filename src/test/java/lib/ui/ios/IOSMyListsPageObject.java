package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IOSMyListsPageObject extends MyListsPageObject {
    static {
        ARTICLE_TITLE_TPL = "xpath://XCUIElementTypeStaticText[@name='{TITLE}']";
        DELETE_BUTTON="id:swipe action delete";
        UNSAVE_BUTTON="id:Unsave";
    }

    public IOSMyListsPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
