package tests;

import lib.CoreTestCase;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class SearchTests extends CoreTestCase {

    @Test
    public void testSearchResult() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
    }

    @Test
    public void testSearchCancel() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelButton();
        SearchPageObject.waitForCancelButtonToDisappear();
    }

    @Test
    public void testNotEmptySearch() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

//        SearchPageObject.clickSkipButton();
        SearchPageObject.initSearchInput();
        String search_text = "Linkin Park discography";
        SearchPageObject.typeSearchLine(search_text);
        int amount_of_results = SearchPageObject.getAmountOfArticles(search_text);

        assertTrue(
                "No search results",
                amount_of_results>0
        );
    }

    @Test
    public void testEmptySearch() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

//        SearchPageObject.clickSkipButton();
        SearchPageObject.initSearchInput();
        String search_text = "jnskejnlsjlsk";
        SearchPageObject.typeSearchLine(search_text);
        SearchPageObject.waitForEmptyResultsLabel();
        SearchPageObject.noSearchResults(search_text);
    }

      @Test
      public void testSearchClear() {

          SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

//          SearchPageObject.clickSkipButton();
          SearchPageObject.initSearchInput();
          SearchPageObject.typeSearchLine("Java");
          SearchPageObject.clearSearchInput();

          NavigationUI NavigationUI = NavigationUIFactory.get(driver);
          NavigationUI.clickBackButton();
          NavigationUI.backButtonIsAbsent();
      }

    @Test
    public void testInputContainsText() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

//        SearchPageObject.clickSkipButton();
        SearchPageObject.checkInputText("Search Wikipedia");
    }

    @Test
    public void testSearchAndCancelResult() {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.clickSkipButton();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.checkTitleText("Java");

        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.clickBackButton();
        NavigationUI.backButtonIsAbsent();
    }

    @Test
    public void testSearchAndVerifyResult() {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

//        SearchPageObject.clickSkipButton();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.checkSearchResultsContainText("Java");
    }
}
