package lib.ui.mobile_web;

import lib.ui.MyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWMyListsPageObject extends MyListsPageObject {
    static {
        ARTICLE_TITLE_TPL = "xpath://XCUIElementTypeStaticText[@name='{TITLE}']";
        DELETE_BUTTON="id:swipe action delete";
        UNSAVE_BUTTON="id:Unsave";
    }
    public MWMyListsPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
