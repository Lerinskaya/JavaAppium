package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

@Epic("App conditions tests")
public class ChangeAppConditionTests extends CoreTestCase {

    @Test
    @Features(value={@Feature(value="App conditions")})
    @DisplayName("Check screen orientation changes")
    @Description("We open 'Java' article, rotate device and check article description")
    @Step("Starting test testChangeScreenOrientation")
    @Severity(value = SeverityLevel.CRITICAL)
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

        Assert.assertEquals(
                "Title descriptions aren't equal after rotation",
                title_before_rotation,
                title_after_rotation
        );

        this.rotateScreenPortrait();
        String title_after_second_rotation = ArticlePageObject.getArticleDescription();

        Assert.assertEquals(
                "Title descriptions aren't equal after rotation",
                title_before_rotation,
                title_after_second_rotation
        );
    }

    @Test
    @Features(value={@Feature(value="App conditions")})
    @DisplayName("Check app background")
    @Description("We open 'Java' article, background app, then open and check article description")
    @Step("Starting test testSearchAfterBackground")
    @Severity(value = SeverityLevel.CRITICAL)
    public void testSearchAfterBackground() {
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
