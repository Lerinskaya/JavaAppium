package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public abstract class SearchPageObject extends MainPageObject{

    protected static String
            SKIP_BUTTON_XPATH,
            SEARCH_INIT_ELEMENT,
            SEARCH_INPUT,
            SEARCH_CANCEL_BUTTON,
            SEARCH_RESULT_BY_SUBSTRING_TPL,
            EMPTY_RESULT,
            LIST_ITEM_ID;

    public SearchPageObject(AppiumDriver driver) {
        super(driver);
    }

    private static String getResultSearchElement(String substring){
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    };

    public void clickSkipButton() {
        this.waitForElementAndClick(SKIP_BUTTON_XPATH,"Cannot find skip button", 10);
    }

    public void initSearchInput() {
        this.waitForElement(SEARCH_INIT_ELEMENT,"Cannot find search input", 10);
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT,"Cannot find search input", 10);
    }

    public void typeSearchLine(String search_line) {
        this.waitForElementAndSendKeys(SEARCH_INPUT, search_line,"Cannot find and type into the search input", 10);
    }

    public void waitForSearchResult(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElement(search_result_xpath,"Cannot find search result with " + substring, 10);
    }

    public void waitForCancelButtonToAppear() {
        this.waitForElement(SEARCH_CANCEL_BUTTON, "Cannot find search cancel button", 10);
    }

    public void waitForCancelButtonToDisappear() {
        this.waitForElementAbsence(SEARCH_CANCEL_BUTTON, "Search cancel button is still present", 10);
    }

    public void clickCancelButton() {
        this.waitForElementAndClick(SEARCH_CANCEL_BUTTON, "Cannot find search cancel button", 10);
    }

    public void clickArticleWithSubstring(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(search_result_xpath,"Cannot find and click search result with " + substring, 10);
    }

    public int getAmountOfArticles(String searchText) {
//        String searchTextLocator = "//*[@resource-id='org.wikipedia:id/page_list_item_title' and @text='"+ searchText +"']";
        String searchTextLocator = SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", searchText);

        this.waitForElement(
                searchTextLocator,
                "Cannot find search result",
                10
        );
        return this.getAmountOfElements(searchTextLocator);
    }

    public void waitForEmptyResultsLabel(){
        this.waitForElement(
                EMPTY_RESULT,
                "The result is not empty",
                10
                );
    }

    public void noSearchResults(String searchText){
        String searchTextLocator = SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", searchText);
        this.assertElementNotPresent(
                searchTextLocator,
                "There are should be no results"
        );
    }

    public void clearSearchInput() {
        this.waitForElementAndClear(
                SEARCH_INPUT,
                "No Search input",
                30);
    }

    public void checkInputText(String element_text) {
        this.assertElementHasText(
                SEARCH_INIT_ELEMENT,
                element_text,
                "Text was not found",
                10
        );
    }

    public void checkTitleText(String element_text) {
        this.assertElementHasText(
                LIST_ITEM_ID,
                element_text,
                "Text was not found",
                10
        );
    }

    public void checkSearchResultsContainText(String input_text) {
        List<WebElement> searchResults = driver.findElements(By.id(LIST_ITEM_ID));

        Assert.assertFalse("Search results not found", searchResults.isEmpty());

        for (WebElement result : searchResults) {
            String resultText = result.getText().toLowerCase();
            Assert.assertTrue("Search result does not contain '" + input_text + "'", resultText.contains(input_text.toLowerCase()));
        }
    }
}
