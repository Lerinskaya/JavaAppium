package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

@Epic("Tests for articles")
public class ArticleTests extends CoreTestCase {

    @Test
    @Features(value={@Feature(value="Search"), @Feature(value = "Article")})
    @DisplayName("Compare article title with expected one")
    @Description("We open 'Java Object-oriented programming language' article and make sure title is expected")
    @Step("Starting test testCompareArticle")
    @Severity(value = SeverityLevel.BLOCKER)
    public void testCompareArticleTitle() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);

        ArticlePageObject.waitForArticleDescription();
        String article_description = ArticlePageObject.getArticleDescription();

        if(Platform.getInstance().isWEB()){
            Assert.assertEquals(
                    "Unexpected article description",
                    "Java (programming language)",
                    article_description
            );
        } else {
            Assert.assertEquals(
                    "Unexpected article description",
                    "Object-oriented programming language",
                    article_description
            );
        }
    }

    @Test
    @Features(value={@Feature(value="Search"), @Feature(value = "Article")})
    @DisplayName("Swipe article to the footer")
    @Description("We open an article and swipe it to the footer")
    @Step("Starting test testSwipeArticle")
    @Severity(value = SeverityLevel.NORMAL)
    public void testSwipeArticle() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);

        ArticlePageObject.waitForArticleDescription();
        ArticlePageObject.swipeToFooter();
    }

    @Test
    @Features(value={@Feature(value="Search"), @Feature(value = "Article")})
    @DisplayName("Check article title")
    @Description("We open an article and check the title")
    @Step("Starting test testAssertTitle")
    @Severity(value = SeverityLevel.BLOCKER)
    public void testAssertTitle() {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);

        if(Platform.getInstance().isWEB()) {
            ArticlePageObject.checkArticleTitle();
        } else {
            ArticlePageObject.checkArticleDescription();
        }
    }
}
