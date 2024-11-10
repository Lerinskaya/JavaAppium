package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class ChangeAppConditionTests extends CoreTestCase {

    @Test
    public void testChangeScreenOrientation() {
        if(Platform.getInstance().isWEB()) {
            return;
        }

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);

        String title_before_rotation = ArticlePageObject.getArticleDescription();
        this.rotateScreenLandscape();
        String title_after_rotation = ArticlePageObject.getArticleDescription();

        assertEquals(
                "Title descriptions aren't equal after rotation",
                title_before_rotation,
                title_after_rotation
        );

        this.rotateScreenPortrait();
        String title_after_second_rotation = ArticlePageObject.getArticleDescription();

        assertEquals(
                "Title descriptions aren't equal after rotation",
                title_before_rotation,
                title_after_second_rotation
        );
    }

    @Test
    public void testSearchAfterBackground() throws InterruptedException {
        if(Platform.getInstance().isWEB()) {
            return;
        }

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");

        this.backgroundApp(5);

        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        if(Platform.getInstance().isAndroid()) {
            NavigationUI.clickBackButton();
        }

        SearchPageObject.waitForSearchResult("Object-oriented programming language");
    }
}
