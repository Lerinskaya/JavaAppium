package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

public abstract class ArticlePageObject extends MainPageObject{

    protected static String
        ARTICLE_ID,
            ARTICLE_TITLE,
        FOOTER_ELEMENT,
        OPTION_BUTTON_ID,
        LIST_BUTTON_ID,
            SECOND_LIST_BUTTON_ID,
        LIST_INPUT_ID,
        OPTIONS_REMOVE_FROM_MY_LIST_BUTTON,
        OK_BUTTON_ID,
        BACK_BUTTON,
        ARTICLE_DESCRIPTION_ID,
        SAVE_TAB_ID,
        CREATE_BUTTON,
        CANCEL_BUTTON;

    public ArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }

    public WebElement waitForArticleDescription() {
        return this.waitForElement(ARTICLE_ID, "Cannot find an article", 10);
    }

    public WebElement waitForArticleTitle() {
        return this.waitForElement(ARTICLE_TITLE, "Cannot find an article", 10);
    }

    public String getArticleDescription() {
        WebElement title_description = waitForArticleDescription();
        screenshot(this.takeScreenshot("article_title"));
        if (Platform.getInstance().isAndroid()) {
            return title_description.getAttribute("text");
        } else if(Platform.getInstance().isIOS()) {
            return title_description.getAttribute("name");
        } else {
            return title_description.getText();
        }
    }

    public String getArticleTitle() {
        WebElement title_description = waitForArticleTitle();
        screenshot(this.takeScreenshot("article_title"));
        if (Platform.getInstance().isAndroid()) {
            return title_description.getAttribute("text");
        }  else if(Platform.getInstance().isIOS()) {
            return title_description.getAttribute("name");
        } else {
            return title_description.getText();
        }
    }

    public void swipeToFooter() {
        if (Platform.getInstance().isAndroid()) {
            this.swipeUpToFindElement(FOOTER_ELEMENT, "Cannot find the end of article", 20);
        } else if(Platform.getInstance().isIOS()){
            this.swipeUpTillElementAppear(FOOTER_ELEMENT, "Cannot find the end of article", 20);
        } else {
            this.scrollWebPageTillElementNotVisible(FOOTER_ELEMENT, "Cannot find the end of article", 20);
        }
    }

    public void addArticleToList(String folder_name) {
        this.waitForElementAndClick(
                OPTION_BUTTON_ID,
                "Save option not found",
                5
        );

        this.waitForElementAndClick(
                LIST_BUTTON_ID,
                "List button not found",
                5
        );

        this.waitForElementAndClick(
                LIST_INPUT_ID,
                "Search input not found",
                5
        );

        this.waitForElementAndSendKeys(
                LIST_INPUT_ID,
                folder_name,
                "Search input not found",
                10);

        this.waitForElementAndClick(
                OK_BUTTON_ID,
                "Ok button not found",
                5
        );
    }

    public void addArticlesToMySaved(String folder_name) {
        if(Platform.getInstance().isWEB()){
            this.waitForElementAndClick(
                    OPTION_BUTTON_ID,
                    "Save option not found",
                    5
            );
        } else {
            this.waitForElementAndClick(
                    OPTION_BUTTON_ID,
                    "Save option not found",
                    5
            );

            this.waitForElementAndClick(
                    LIST_BUTTON_ID,
                    "List button not found",
                    5
            );

            this.waitForElementAndClick(
                    CREATE_BUTTON,
                    "Create button not found",
                    5
            );

            this.waitForElementAndSendKeys(
                    LIST_INPUT_ID,
                    folder_name,
                    "Search input not found",
                    10);

            this.waitForElementAndClick(
                    OK_BUTTON_ID,
                    "Ok button not found",
                    5
            );
        }
    }

    public void addArticleToExistingList(String folder_name) {
        if(Platform.getInstance().isWEB()){
            this.removeArticleFromSavedIfItAdded();
        }

        this.waitForElementAndClick(
                OPTION_BUTTON_ID,
                "Save option not found",
                5
        );

        if(Platform.getInstance().isAndroid()){
            this.waitForElementAndClick(
                    LIST_BUTTON_ID,
                    "List button not found",
                    15
            );
        } else {
            this.waitForElementAndClick(
                    SECOND_LIST_BUTTON_ID,
                    "List button not found",
                    15
            );
        }

        if(Platform.getInstance().isAndroid()){
            this.waitForElementAndClick(
                    "xpath://*[@text='"+ folder_name +"']",
                    folder_name + "folder not found",
                    10
            );
        } else {
            this.waitForElementAndClick(
                    "xpath://*[@name='"+ folder_name +"']",
                    folder_name + "folder not found",
                    10
            );
        }
    }

    public void removeArticleFromSavedIfItAdded() {
        if(this.isElementPresent(OPTIONS_REMOVE_FROM_MY_LIST_BUTTON)) {
            this.waitForElementAndClick(
                    OPTIONS_REMOVE_FROM_MY_LIST_BUTTON,
                    "Cannot click button to remove an article from saved",
                    5);
        }
       this.waitForElement(
               OPTION_BUTTON_ID,
               "Cannot find button to add an article to saved",
               5);
    }


    public void closeArticle() {
        if(Platform.getInstance().isWEB()) {
            System.out.println("Method scrollWebPageUp() does nothing for platform" + Platform.getInstance().getPlatformVar());
        } else {
            this.waitForElementAndClick(
                    BACK_BUTTON,
                    "No back button",
                    10
            );

            if(Platform.getInstance().isAndroid()){
                this.waitForElementAndClick(
                        BACK_BUTTON,
                        "No back button",
                        10
                );
            } else {
                this.waitForElementAndClick(
                        CANCEL_BUTTON,
                        "No cancel button",
                        10
                );
            }
        }
    }

    public void checkArticleDescription() {
        screenshot(this.takeScreenshot("article_title"));
        this.assertElementPresent(
                ARTICLE_DESCRIPTION_ID,
                "Title element is not present on the page"
        );
    }

    public void checkArticleTitle() {
        screenshot(this.takeScreenshot("article_title"));
        this.assertElementPresent(
                ARTICLE_TITLE,
                "Title element is not present on the page"
        );
    }

    public void openList(String folder_name) {
        this.waitForElementAndClick(
                SAVE_TAB_ID,
                "Save option not found",
                5
        );

        this.waitForElementAndClick(
                "//*[@text='"+ folder_name +"']",
                "Reading list not found",
                5
        );
    }

}
