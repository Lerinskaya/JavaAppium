package lib.ui.mobile_web;

import lib.ui.MyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWMyListsPageObject extends MyListsPageObject {
    static {
        ARTICLE_TITLE_TPL = "xpath://li[contains(@class, 'with-watchstar')]//h3[contains(text(), '{TITLE}')]";
//        DELETE_BUTTON="id:swipe action delete";
        UNSAVE_BUTTON="xpath://li[@title='{TITLE}']//a[contains(@class, 'cdx-button--icon-only')]";
    }
    public MWMyListsPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
