package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class Platform {
    private static final String
            PLATFORM_IOS = "ios",
            PLATFORM_ANDROID = "android",
            AppiumURL = "http://localhost:4723";

    private static Platform instance;
    private Platform() {}

    public static Platform getInstance() {
        if(instance == null) {
            instance = new Platform();
        }
        return instance;
    }

    public Boolean isAndroid(){
        return isPlatform(PLATFORM_ANDROID);
    }

    public Boolean isIOS(){
        return isPlatform(PLATFORM_IOS);
    }

    public AppiumDriver getDriver() throws Exception{
        URL URL = new URL(AppiumURL);
        if(this.isAndroid()){
            return new AndroidDriver(URL, this.getDesiredAndroidCapabilities());
        } else if(this.isIOS()) {
            return new IOSDriver(URL, this.getDesiredIOSCapabilities());
        } else {
            throw new Exception("Cannot detect driver");
        }
    }

    private DesiredCapabilities getDesiredAndroidCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "8.1");
        capabilities.setCapability("automationName", "UiAutomator2");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "/Users/dzmitrypadolski/Desktop/Tests/apks/org.wikipedia.apk");
        return capabilities;
    }

    private DesiredCapabilities getDesiredIOSCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("deviceName", "iPhone 15");
        capabilities.setCapability("platformVersion", "18.0");
        capabilities.setCapability("app", "/Users/dzmitrypadolski/Desktop/Tests/apks/Wikipedia693.app");
        capabilities.setCapability("automationName", "XCUITest");
        capabilities.setCapability("language", "en");
        return capabilities;
    }

    private Boolean isPlatform(String my_platform){
        String platform = this.getPlatformVar();
        return my_platform.equals(platform);
    }

    private String getPlatformVar() {
        return System.getenv("PLATFORM");
    }
}
