package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

@Epic("Tests for lists")
public class MyListsTest extends CoreTestCase {

    private static final String
            folder_name = "Articles",
            login = "Lerinskaya1",
            password = "lolkek123";

    @Test
    @Features(value={@Feature(value="Search"), @Feature(value = "Article List")})
    @DisplayName("Save article to the list")
    @Description("We open 'Java Object-oriented programming language' article and save it to the list")
    @Step("Starting test testSaveArticle")
    @Severity(value = SeverityLevel.CRITICAL)
    public void testSaveArticle() {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);

        ArticlePageObject.waitForArticleDescription();
        String article_title = ArticlePageObject.getArticleDescription();
        System.out.println(article_title);

        if(Platform.getInstance().isAndroid()){
            ArticlePageObject.addArticleToList(folder_name);
        } else {
            ArticlePageObject.addArticlesToMySaved(folder_name);
        }

        if(Platform.getInstance().isWEB()) {
            AuthPageObject Auth= new AuthPageObject(driver);
            Auth.clickAuthButton();
            Auth.enterLoginData(login, password);
            Auth.submitForm();

            ArticlePageObject.waitForArticleDescription();

            Assert.assertEquals("Not the same page", article_title, ArticlePageObject.getArticleDescription());
        }

        ArticlePageObject.closeArticle();

        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.openNavigation();
        NavigationUI.clickMyLists();

        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);
        if(Platform.getInstance().isAndroid()){
            MyListsPageObject.openFolderByName(folder_name);
        }
        MyListsPageObject.swipeArticleToDelete(article_title);
    }

    @Test
    @Features(value={@Feature(value="Search"), @Feature(value = "Article List")})
    @DisplayName("Save 2 article to the list and delete first")
    @Description("We open 2 articles, save them to the list, delete first article and compare title of a second article")
    @Step("Starting test testSaveArticles")
    @Severity(value = SeverityLevel.CRITICAL)
    public void testSaveArticles() {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        String folder_name = "Articles";

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForArticleDescription();
        String first_article_title = ArticlePageObject.getArticleDescription();
        if(Platform.getInstance().isAndroid()){
            ArticlePageObject.addArticleToList(folder_name);
        } else {
            ArticlePageObject.addArticlesToMySaved(folder_name);
        }

        if(Platform.getInstance().isWEB()) {
            AuthPageObject Auth= new AuthPageObject(driver);
            Auth.clickAuthButton();
            Auth.enterLoginData(login, password);
            Auth.submitForm();

            ArticlePageObject.waitForArticleDescription();

            Assert.assertEquals("Not the same page", first_article_title,ArticlePageObject.getArticleDescription());
            SearchPageObject.initSearchInput();
            SearchPageObject.typeSearchLine("Java");
        }


        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.clickBackButton();

        SearchPageObject.clickArticleWithSubstring("High-level programming language");
        ArticlePageObject.waitForArticleTitle();
        String second_article_title = ArticlePageObject.getArticleTitle();

        if(Platform.getInstance().isAndroid() || Platform.getInstance().isIOS()) {
            ArticlePageObject.addArticleToExistingList(folder_name);

            NavigationUI.clickBackButton();
            if(Platform.getInstance().isAndroid()){
                NavigationUI.clickBackButton();
            } else {
                NavigationUI.clickCancelButton();
            }
        } else {
            ArticlePageObject.addArticlesToMySaved(folder_name);
        }
            NavigationUI.openNavigation();
            NavigationUI.clickMyLists();

            MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);
            if(Platform.getInstance().isAndroid()){
                MyListsPageObject.openFolderByName(folder_name);
            }
            MyListsPageObject.swipeArticleToDelete(first_article_title);
            MyListsPageObject.waitForArticlePresent(second_article_title);
    }
}
