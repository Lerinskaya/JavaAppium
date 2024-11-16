package lib.ui.mobile_web;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWArticlePageObject extends ArticlePageObject {
    static {
        ARTICLE_ID = "css:#content h1";
        FOOTER_ELEMENT = "css:footer";
        OPTION_BUTTON_ID = "css:#ca-watch";
        LIST_BUTTON_ID ="id:Add “Java (programming language)” to a reading list?";
        SECOND_LIST_BUTTON_ID ="id:Add “JavaScript” to a reading list?";
        ARTICLE_TITLE = "css:#content h1";
        OPTIONS_REMOVE_FROM_MY_LIST_BUTTON= "css:a[role='button'][title='Remove this page from your watchlist']";
        LIST_INPUT_ID = "xpath://XCUIElementTypeTextField[@value=\"reading list title\"]";
        OK_BUTTON_ID = "xpath://XCUIElementTypeStaticText[@name=\"Create reading list\"]";
        BACK_BUTTON = "xpath://XCUIElementTypeButton[@name=\"Back\"]";
        ARTICLE_DESCRIPTION_ID = "css:#content h1";
        CANCEL_BUTTON = "xpath://XCUIElementTypeStaticText[@name=\"Cancel\"]";
        SAVE_TAB_ID = "id:tabbar-save";
        CREATE_BUTTON = "xpath://XCUIElementTypeStaticText[@name=\"Create a new list\"]";
    }
    public MWArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
