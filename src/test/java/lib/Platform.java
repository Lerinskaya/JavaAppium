package lib;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Platform {
    private static final String
            PLATFORM_IOS = "ios",
            PLATFORM_ANDROID = "android",
            PLATFORM_MOBILE_WEB = "mobile_web",
            APPIUM_URL = "http://localhost:4723";

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

    public Boolean isWEB(){
        return isPlatform(PLATFORM_MOBILE_WEB);
    }

    public RemoteWebDriver getDriver() throws Exception{
        URL URL = new URL(APPIUM_URL);
        if(this.isAndroid()){
            return new AndroidDriver(URL, this.getDesiredAndroidCapabilities());
        } else if(this.isIOS()) {
            return new IOSDriver(URL, this.getDesiredIOSCapabilities());
        } else if(this.isWEB()) {
            return new ChromeDriver(this.getMyChromeOptions());
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

    private ChromeOptions getMyChromeOptions() {
        Map<String, Object> deviceMetrics = new HashMap<String, Object>();
        deviceMetrics.put("width", 360);
        deviceMetrics.put("height", 640);
        deviceMetrics.put("pixelRatio", 3.0);

        Map<String, Object> mobileEmulation = new HashMap<String, Object>();
        mobileEmulation.put("deviceMetrics", deviceMetrics);
        mobileEmulation.put("userAgent", "Mozilla/5.0 (Linux; Android 4.2.1; en-us; Nexus 5 Build/JOP40D) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.166 Mobile Safari/535.19");

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("window-size=340,640");

        return chromeOptions;
    }

    private Boolean isPlatform(String my_platform){
        String platform = this.getPlatformVar();
        return my_platform.equals(platform);
    }

    public String getPlatformVar() {
        return System.getenv("PLATFORM");
    }
}
