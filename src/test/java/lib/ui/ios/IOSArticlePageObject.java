package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IOSArticlePageObject extends ArticlePageObject {
    static {
        ARTICLE_ID = "id:Object-oriented programming language";
        FOOTER_ELEMENT = "xpath://XCUIElementTypeStaticText[@name='View article in browser']";
        OPTION_BUTTON_ID = "id:Save for later";
        LIST_BUTTON_ID ="id:Add “Java (programming language)” to a reading list?";
        SECOND_LIST_BUTTON_ID ="id:Add “JavaScript” to a reading list?";
        ARTICLE_TITLE = "id:JavaScript";
        LIST_INPUT_ID = "xpath://XCUIElementTypeTextField[@value=\"reading list title\"]";
        OK_BUTTON_ID = "xpath://XCUIElementTypeStaticText[@name=\"Create reading list\"]";
        BACK_BUTTON = "xpath://XCUIElementTypeButton[@name=\"Back\"]";
        ARTICLE_DESCRIPTION_ID = "id:Object-oriented programming language";
        CANCEL_BUTTON = "xpath://XCUIElementTypeStaticText[@name=\"Cancel\"]";
        SAVE_TAB_ID = "id:tabbar-save";
        CREATE_BUTTON = "xpath://XCUIElementTypeStaticText[@name=\"Create a new list\"]";
    }
    public IOSArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
