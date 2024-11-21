package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.WelcomePageObject;
import lib.ui.factories.WelcomePageObjectFactory;
import org.junit.Test;

@Epic("Welcome page tests")
public class GetStartedTest extends CoreTestCase {
    @Test
    @Features(value={@Feature(value="Welcome page")})
    @DisplayName("Skip Welcome page")
    @Description("We tap skip button")
    @Step("Starting test testPassThroughWelcome")
    @Severity(value = SeverityLevel.BLOCKER)
    public void testPassThroughWelcome() {
        if(Platform.getInstance().isWEB()) {
            return;
        }

        WelcomePageObject WelcomePage = WelcomePageObjectFactory.get(driver);

        WelcomePage.waitForMainTitle();
        WelcomePage.clickNextButton();
        WelcomePage.clickNextButton();
        WelcomePage.clickNextButton();
        WelcomePage.waitForHelpText();
    }
}
