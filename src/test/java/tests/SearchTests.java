package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

@Epic("Tests for search")
public class SearchTests extends CoreTestCase {

    @Test
    @Features(value={@Feature(value="Search")})
    @DisplayName("Search an article")
    @Description("We type 'Java' in input and wait for search results")
    @Step("Starting test testSearchResult")
    @Severity(value = SeverityLevel.CRITICAL)
    public void testSearchResult() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
    }

    @Test
    @Features(value={@Feature(value="Search")})
    @DisplayName("Cancel search")
    @Description("We type 'Java' in input and tap cancel button")
    @Step("Starting test testSearchCancel")
    @Severity(value = SeverityLevel.CRITICAL)
    public void testSearchCancel() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelButton();
        SearchPageObject.waitForCancelButtonToDisappear();
    }

    @Test
    @Features(value={@Feature(value="Search")})
    @DisplayName("Search result is not empty")
    @Description("We type 'Linkin Park discography' in input and check search result is not empty")
    @Step("Starting test testNotEmptySearch")
    @Severity(value = SeverityLevel.CRITICAL)
    public void testNotEmptySearch() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        String search_text = "Linkin Park discography";
        SearchPageObject.typeSearchLine(search_text);
        int amount_of_results;
        if(Platform.getInstance().isWEB()) {
            amount_of_results = SearchPageObject.getAmountOfArticlesByTitle(search_text);
        } else {
            amount_of_results = SearchPageObject.getAmountOfArticles(search_text);
        }

        Assert.assertTrue(
                "No search results",
                amount_of_results>0
        );
    }

    @Test
    @Features(value={@Feature(value="Search")})
    @DisplayName("Search result is empty for invalid request")
    @Description("We type 'jnskejnlsjlsk' in input and check search result is empty")
    @Step("Starting test testEmptySearch")
    @Severity(value = SeverityLevel.NORMAL)
    public void testEmptySearch() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        String search_text = "jnskejnlsjlsk";
        SearchPageObject.typeSearchLine(search_text);
        SearchPageObject.waitForEmptyResultsLabel();
        SearchPageObject.noSearchResults(search_text);
    }

      @Test
      @Features(value={@Feature(value="Search")})
      @DisplayName("Clear search input")
      @Description("We type 'Java' in input, clear input and tap back button")
      @Step("Starting test testSearchClear")
      @Severity(value = SeverityLevel.CRITICAL)
      public void testSearchClear() {
          SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

          SearchPageObject.initSearchInput();
          SearchPageObject.typeSearchLine("Java");
          SearchPageObject.clearSearchInput();

          NavigationUI NavigationUI = NavigationUIFactory.get(driver);
          NavigationUI.clickBackButton();
          NavigationUI.backButtonIsAbsent();
      }

    @Test
    @Features(value={@Feature(value="Search")})
    @DisplayName("Check input contains text")
    @Description("We check input contains text 'Search Wikipedia'")
    @Step("Starting test testInputContainsText")
    @Severity(value = SeverityLevel.MINOR)
    public void testInputContainsText() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        if(Platform.getInstance().isWEB()){
            SearchPageObject.initSearchInput();
            SearchPageObject.checkInputText("Search");
        } else {
            SearchPageObject.checkInputText("Search Wikipedia");
        }
    }

    @Test
    @Features(value={@Feature(value="Search")})
    @DisplayName("Cancel search result")
    @Description("We type 'Java' in input, check article title and tap back button")
    @Step("Starting test testSearchAndCancelResult")
    @Severity(value = SeverityLevel.CRITICAL)
    public void testSearchAndCancelResult() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.checkTitleText("Java");

        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.clickBackButton();
        NavigationUI.backButtonIsAbsent();
    }

    @Test
    @Features(value={@Feature(value="Search")})
    @DisplayName("Check search result")
    @Description("We type 'Java' in input and check search results")
    @Step("Starting test testSearchAndVerifyResult")
    @Severity(value = SeverityLevel.CRITICAL)
    public void testSearchAndVerifyResult() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.checkSearchResultsContainText("Java");
    }
}
