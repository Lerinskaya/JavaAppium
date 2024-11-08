package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;

public class AndroidArticlePageObject extends ArticlePageObject {
    static {
                ARTICLE_ID = "id:Object-oriented programming language";
                FOOTER_ELEMENT = "xpath://android.view.View[@content-desc='View article in browser']";
                OPTION_BUTTON_ID = "id:org.wikipedia:id/page_save";
                LIST_BUTTON_ID ="id:org.wikipedia:id/snackbar_action";
                LIST_INPUT_ID = "id:org.wikipedia:id/text_input";
                OK_BUTTON_ID = "id:android:id/button1";
                BACK_BUTTON = "xpath://android.widget.ImageButton[@content-desc='Navigate up']";
                ARTICLE_DESCRIPTION_ID = "id:pcs-edit-section-title-description";
                SAVE_TAB_ID = "id:org.wikipedia:id/nav_tab_reading_lists";
    }
    public AndroidArticlePageObject(AppiumDriver driver) {
        super(driver);
    }
}
