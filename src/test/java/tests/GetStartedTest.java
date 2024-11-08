package tests;

import lib.CoreTestCase;
import lib.ui.WelcomePageObject;
import lib.ui.factories.WelcomePageObjectFactory;
import org.junit.Test;

public class GetStartedTest extends CoreTestCase {
    @Test
    public void testPassThroughWelcome() {
        WelcomePageObject WelcomePage = WelcomePageObjectFactory.get(driver);

        WelcomePage.waitForMainTitle();
        WelcomePage.clickNextButton();
        WelcomePage.clickNextButton();
        WelcomePage.clickNextButton();
        WelcomePage.waitForHelpText();
    }
}
