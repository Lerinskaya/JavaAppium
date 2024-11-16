package lib.ui;

import org.openqa.selenium.remote.RemoteWebDriver;

public class AuthPageObject extends MainPageObject{
    private static final String
    LOGIN_BUTTON="xpath://a[contains(@class, 'cdx-button--fake-button') and span[text()='Log in']]",
    LOGIN_INPUT="css:#wpName1",
    PASSWORD_INPUT="css:#wpPassword1",
    SUBMIT_BUTTON="css:#wpLoginAttempt";

    public AuthPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    public void clickAuthButton() {
        this.waitForElement(LOGIN_BUTTON, "Cannot find auth button", 5);
        this.waitForElementAndClick(LOGIN_BUTTON, "Cannot click auth button", 5);
    }

    public void enterLoginData(String login, String password) {
        this.waitForElementAndSendKeys(LOGIN_INPUT, login, "Cannot put a login", 5);
        this.waitForElementAndSendKeys(PASSWORD_INPUT, password, "Cannot put a password", 5);
    }

    public void submitForm() {
        this.waitForElementAndClick(SUBMIT_BUTTON, "Cannot find submit button", 5);
    }
}
