package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class MyListsTest extends CoreTestCase {

    private static final String folder_name = "Articles";

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

        ArticlePageObject.closeArticle();

        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
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

        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.clickBackButton();

        SearchPageObject.clickArticleWithSubstring("High-level programming language");
        ArticlePageObject.waitForArticleTitle();
        String second_article_title = ArticlePageObject.getArticleTitle();

        ArticlePageObject.addArticleToExistingList(folder_name);

        NavigationUI.clickBackButton();
        if(Platform.getInstance().isAndroid()){
            NavigationUI.clickBackButton();
        } else {
            NavigationUI.clickCancelButton();
        }

        NavigationUI.clickMyLists();

        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);
        if(Platform.getInstance().isAndroid()){
            MyListsPageObject.openFolderByName(folder_name);
        }
        MyListsPageObject.swipeArticleToDelete(first_article_title);
        MyListsPageObject.waitForArticlePresent(second_article_title);
    }
}
