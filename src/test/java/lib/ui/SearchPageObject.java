package lib.ui;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.List;

public abstract class SearchPageObject extends MainPageObject{

    protected static String
            SKIP_BUTTON_XPATH,
            SEARCH_INIT_ELEMENT,
            SEARCH_INPUT,
            SEARCH_CANCEL_BUTTON,
            SEARCH_RESULT_BY_SUBSTRING_TPL,
            SEARCH_RESULT_BY_SUBSTRING_TITLE_TPL,
            EMPTY_RESULT,
            LIST_ITEM_ID;

    public SearchPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    @Step("Choosing search result by substring")
    private static String getResultSearchElement(String substring){
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    };

    @Step("Clicking skip button on welcome page (does nothing for mobile web)")
    public void clickSkipButton() {
        this.waitForElementAndClick(SKIP_BUTTON_XPATH,"Cannot find skip button", 10);
    }

    @Step("Initializing the search field")
    public void initSearchInput() {
        this.waitForElement(SEARCH_INIT_ELEMENT,"Cannot find search input", 10);
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT,"Cannot find search input", 10);
    }

    @Step("Typing '{search_line}' to the search field")
    public void typeSearchLine(String search_line) {
        this.waitForElementAndSendKeys(SEARCH_INPUT, search_line,"Cannot find and type into the search input", 10);
    }

    @Step("Waiting for search result")
    public void waitForSearchResult(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElement(search_result_xpath,"Cannot find search result with " + substring, 10);
    }

    @Step("Waiting for cancel button to appear")
    public void waitForCancelButtonToAppear() {
        this.waitForElement(SEARCH_CANCEL_BUTTON, "Cannot find search cancel button", 10);
    }

    @Step("Waiting for cancel button to disappear")
    public void waitForCancelButtonToDisappear() {
        this.waitForElementAbsence(SEARCH_CANCEL_BUTTON, "Search cancel button is still present", 10);
    }

    @Step("Clicking button to cancel search result")
    public void clickCancelButton() {
        this.waitForElementAndClick(SEARCH_CANCEL_BUTTON, "Cannot find search cancel button", 10);
    }

    @Step("Selecting an article by substring in search results")
    public void clickArticleWithSubstring(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(search_result_xpath,"Cannot find and click search result with " + substring, 10);
    }

    @Step("Getting amount of articles")
    public int getAmountOfArticles(String searchText) {
        String searchTextLocator = SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", searchText);

        this.waitForElement(
                searchTextLocator,
                "Cannot find search result",
                10
        );
        return this.getAmountOfElements(searchTextLocator);
    }

    @Step("Getting amount of articles")
    public int getAmountOfArticlesByTitle(String searchText) {
        String searchTextLocator = SEARCH_RESULT_BY_SUBSTRING_TITLE_TPL.replace("{SUBSTRING}", searchText);

        this.waitForElement(
                searchTextLocator,
                "Cannot find search result",
                10
        );
        return this.getAmountOfElements(searchTextLocator);
    }

    @Step("Waiting for empty results")
    public void waitForEmptyResultsLabel(){
        this.waitForElement(
                EMPTY_RESULT,
                "The result is not empty",
                10
                );
        screenshot(this.takeScreenshot("empty_result"));
    }

    @Step("Making sure there is no search results")
    public void noSearchResults(String searchText){
        String searchTextLocator = SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", searchText);
        this.assertElementNotPresent(
                searchTextLocator,
                "There are should be no results"
        );
    }

    @Step("Clearing search input")
    public void clearSearchInput() {
        this.waitForElementAndClear(
                SEARCH_INPUT,
                "No Search input",
                30);
    }

    @Step("Making sure input has text")
    public void checkInputText(String element_text) {
        this.assertElementHasText(
                SEARCH_INIT_ELEMENT,
                element_text,
                "Text was not found",
                10
        );
    }

    @Step("Checking article title")
    public void checkTitleText(String element_text) {
        this.assertElementHasText(
                LIST_ITEM_ID,
                element_text,
                "Text was not found",
                10
        );
    }

    @Step("Making sure search result is not empty")
    public void checkSearchResultsContainText(String input_text) {
        List<WebElement> searchResults = driver.findElements(By.id(LIST_ITEM_ID));

        Assert.assertFalse("Search results not found", searchResults.isEmpty());

        for (WebElement result : searchResults) {
            String resultText = result.getText().toLowerCase();
            Assert.assertTrue("Search result does not contain '" + input_text + "'", resultText.contains(input_text.toLowerCase()));
        }
    }
}
