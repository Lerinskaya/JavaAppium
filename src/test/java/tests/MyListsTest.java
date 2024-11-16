package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class MyListsTest extends CoreTestCase {

    private static final String
            folder_name = "Articles",
            login = "Lerinskaya1",
            password = "lolkek123";

    @Test
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

            assertEquals("Not the same page", article_title, ArticlePageObject.getArticleDescription());
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
    public void testSaveArticles() throws InterruptedException {

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

            assertEquals("Not the same page", first_article_title,ArticlePageObject.getArticleDescription());
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
