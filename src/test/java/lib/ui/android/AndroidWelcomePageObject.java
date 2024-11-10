package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.WelcomePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidWelcomePageObject extends WelcomePageObject{

        static {
            SKIP_BUTTON_XPATH = "xpath://*[contains(@text, 'Skip')]";
        }
        public AndroidWelcomePageObject(RemoteWebDriver driver) {
            super(driver);
        }
    }

