package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import lib.Platform;

import java.time.Duration;
import java.util.List;
import java.util.regex.Pattern;

public class MainPageObject {

    protected RemoteWebDriver driver;

    public MainPageObject(RemoteWebDriver driver) {
    this.driver = driver;
    }

    public WebElement waitForElement(String locator, String errorMessage, long timeout) {
            By by = this.getLocatorByString(locator);
            WebDriverWait wait = new WebDriverWait(driver, timeout);
            wait.withMessage(errorMessage + "\n");
            return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public WebElement waitForElement(String locator, String errorMessage) {
        return waitForElement(locator, errorMessage, 20);
    }

    public WebElement waitForElementAndClick(String locator, String errorMessage, long timeout){
        WebElement element = waitForElement(locator, errorMessage, timeout);
        element.click();
        return element;
    }

    public WebElement waitForElementAndSendKeys(String locator, String value, String errorMessage, long timeout){
        WebElement element = waitForElement(locator, errorMessage);
        element.sendKeys(value);
        return element;
    }

    public boolean waitForElementAbsence(String locator, String errorMessage, long timeout) {

        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.withMessage(errorMessage + "\n");
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    public WebElement waitForElementAndClear(String locator, String errorMessage, long timeout){
        WebElement element = waitForElement(locator, errorMessage);
        element.clear();
        return element;
    }

    public WebElement assertElementHasText(String locator, String expectedText, String errorMessage, long timeout) {
        WebElement element = waitForElement(locator, errorMessage, timeout);
        String actualText = element.getText();
        Assert.assertEquals(errorMessage, expectedText, actualText);
        return element;
    }

    public void swipeUp(int timeOfSwipe){
        if(driver instanceof AppiumDriver) {
            TouchAction action = new TouchAction((AppiumDriver)driver);
            Dimension size = driver.manage().window().getSize();
            int x = size.width/2;
            int start_y = (int) (size.height*0.7);
            int end_y = (int) (size.height*0.3);
            action
                    .press(PointOption.point(x, start_y))
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(timeOfSwipe)))
                    .moveTo(PointOption.point(x, end_y))
                    .release()
                    .perform();
        } else {
            System.out.println("Method rotateScreenPortait() does nothing for platform" + Platform.getInstance().getPlatformVar());
        }
    }

    public void swipeUpQuick(){
        swipeUp(200);
    }

    public void scrollWebPageUp() {
        if (Platform.getInstance().isWEB()) {
            JavascriptExecutor JSExecutor = (JavascriptExecutor) driver;
            JSExecutor.executeScript("window.scrollBy(0, 250)");
        } else {
            System.out.println("Method scrollWebPageUp() does nothing for platform" + Platform.getInstance().getPlatformVar());
        }
    }

    public void scrollWebPageTillElementNotVisible(String locator, String error_message, int max_swipes) {
        int swipes = 0;

        WebElement element = this.waitForElement(locator, error_message);

        while(!this.isElementLocatedOnTheScreen(locator)) {
            scrollWebPageUp();
            ++swipes;
            if(swipes>max_swipes) {
                Assert.assertTrue(error_message, element.isDisplayed());
            }
        }
    }

    public void clickElementToTheRightUpperCorner(String locator, String error_message) {

        if(driver instanceof AppiumDriver) {
            WebElement element = this.waitForElement(locator, error_message);
            int right_x = element.getLocation().getX();
            int upper_y = element.getLocation().getY();
            int lower_y = upper_y + element.getSize().getHeight();
            int middle_y = (upper_y + lower_y)/2;
            int width = element.getSize().getWidth();

            int point_to_click_x = (right_x + width) - 3;
            int point_to_click_y = middle_y;
            new TouchAction((AppiumDriver)driver)
                    .tap(PointOption.point(point_to_click_x, point_to_click_y))
                    .perform();
        } else {
            System.out.println("Method rotateScreenPortait() does nothing for platform" + Platform.getInstance().getPlatformVar());
        }
    }

    public void swipeUpToFindElement(String locator, String errorMessage, int maxSwipes){

        By by = this.getLocatorByString(locator);
        int swipes = 0;
        while (driver.findElements(by).isEmpty()) {
            if (swipes>maxSwipes) {
                waitForElement(locator, "Cannot find element by swiping. " + errorMessage);
                return;
            }
            swipeUpQuick();
            ++swipes;
        }
    }

    public void swipeElementToLeft(String locator, String errorMessage) {
        if(driver instanceof AppiumDriver) {
            WebElement element = waitForElement(locator, errorMessage);
            int left_x = element.getLocation().getX();
            int right_x = left_x + element.getSize().getWidth();

            int upper_y = element.getLocation().getY();
            int lower_x = upper_y + element.getSize().getHeight();
            int middle_y = (upper_y + lower_x)/2;

            if(Platform.getInstance().isAndroid()) {
            TouchAction action = new TouchAction((AppiumDriver)driver);
            action.press(PointOption.point(right_x, middle_y));
            action.waitAction(WaitOptions.waitOptions(Duration.ofMillis(300)));
            action.moveTo(PointOption.point(right_x, middle_y));
            action.moveTo(PointOption.point(right_x, middle_y));
            action.release();
            action.perform();
            } else {
                new Actions(driver)
                    .moveToElement(element, right_x, middle_y)
                    .clickAndHold()
                    .moveByOffset(-1 * element.getSize().getWidth(), 0)
                    .release()
                    .perform();
                }
            } else {
            System.out.println("Method rotateScreenPortait() does nothing for platform" + Platform.getInstance().getPlatformVar());
        }
    }

    public void swipeUpTillElementAppear(String locator, String error_message, int max_swipes) {
        int swipes = 0;
        while(!this.isElementLocatedOnTheScreen(locator)) {
            if (swipes>max_swipes){
                Assert.assertTrue(error_message, this.isElementLocatedOnTheScreen(locator));
            }
            swipeUpQuick();
            ++swipes;
        }
    }

    public boolean isElementLocatedOnTheScreen(String locator) {
        int element_location_y = this.waitForElement(locator, "Cannot find element by locator", 10).getLocation().getY();

        if(Platform.getInstance().isWEB()){
            JavascriptExecutor JSExecutor = (JavascriptExecutor) driver;
            Object js_result = JSExecutor.executeScript("return window.pageYOffset");
            element_location_y = Integer.parseInt(js_result.toString());
        }

        int screen_size_y = driver.manage().window().getSize().getHeight();
        return element_location_y<screen_size_y;
    }

    public int getAmountOfElements(String locator) {

        By by = this.getLocatorByString(locator);
        List elements = driver.findElements(by);
        return elements.size();
    }

    public Boolean isElementPresent(String locator) {
        return getAmountOfElements(locator) > 0;
    }

    public void tryClickElement(String locator, String error_message, int amount_of_attempts) {
        int attempts = 0;
        boolean need_more_attempts = true;

        while (need_more_attempts) {
            try{
                this.waitForElementAndClick(locator, error_message, 5);
                need_more_attempts = false;
            } catch (Exception e) {
                if (attempts>amount_of_attempts) {
                    this.waitForElementAndClick(locator, error_message, 5);
                }
            }
            ++attempts;
        }
    }

    public void assertElementNotPresent(String locator, String errorMessage) {

        int amountOfElements = getAmountOfElements(locator);
        if (amountOfElements>0) {
            String defaultMessage = "An element '" + locator +" ' not suppose to be present";
            throw new AssertionError(defaultMessage + " " + errorMessage);
        }
    }

    public void assertElementPresent(String locator, String errorMessage) {
        int amountOfElements = getAmountOfElements(locator);
        if (amountOfElements == 0) {
            String defaultMessage = "An element '" + locator +" ' suppose to be present";
            throw new AssertionError(defaultMessage + " " + errorMessage);
        }
    }

    public String waitForElementAndGetAttribute(String locator, String attribute, String errorMessage, long timeout) {
        WebElement element = waitForElement(locator, errorMessage, timeout);
        return element.getAttribute(attribute);
    }

    private By getLocatorByString(String locator_with_type) {
        String[] exploded_locator = locator_with_type.split(Pattern.quote(":"), 2);
        String by_type = exploded_locator[0];
        String locator = exploded_locator[1];

        if(by_type.equals("xpath")) {
            return By.xpath(locator);
        } else if (by_type.equals("id")) {
            return By.id(locator);
        } else if (by_type.equals("css")) {
            return By.cssSelector(locator);
        } else {
            throw new IllegalArgumentException("Wrong locator type: " + locator_with_type);
        }
    }

}
