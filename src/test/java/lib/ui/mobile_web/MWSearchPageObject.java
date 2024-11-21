package lib.ui.mobile_web;

import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWSearchPageObject extends SearchPageObject{
    static{
        SEARCH_INIT_ELEMENT = "css:button#searchIcon";
        SEARCH_INPUT = "css:form>input[type='search']";
        SEARCH_CANCEL_BUTTON = "css:button.clear";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://div[contains(@class, 'wikidata-description')][text()='{SUBSTRING}']";
        SEARCH_RESULT_BY_SUBSTRING_TITLE_TPL = "xpath://h3[strong[text() = '{SUBSTRING}']]";
        EMPTY_RESULT ="css:p.without-results";
        LIST_ITEM_ID = "css:ul.page-list>li.page-summary";
        }

        public MWSearchPageObject(RemoteWebDriver driver) {
            super(driver);
        }
}
