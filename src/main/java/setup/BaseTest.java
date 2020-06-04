package setup;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;
import pageObjects.PageObject;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BaseTest implements IDriver {

    private static AppiumDriver appiumDriver; // singleton
    IPageObject pageObject;

    @Override
    public AppiumDriver getDriver() {
        return appiumDriver;
    }

    public IPageObject getPageObject() {
        return pageObject;
    }

    @Parameters({"platformName", "appType", "deviceName", "udid", "browserName", "app", "appPackage", "appActivity", "bundleId"})
    @BeforeMethod(alwaysRun = true)
    public void setUp(String platformName,
                      String appType,
                      @Optional("") String deviceName,
                      @Optional("") String udid,
                      @Optional("") String browserName,
                      @Optional("") String app,
                      @Optional("") String appPackage,
                      @Optional("") String appActivity,
                      @Optional("") String bundleId
    ) throws Exception {
        System.out.println("Before: app type - " + appType);
        setAppiumDriver(platformName, deviceName, udid, browserName, app, appPackage, appActivity, bundleId);
        setPageObject(appType, appiumDriver);
    }


    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        System.out.println("After");
        appiumDriver.closeApp();
    }

    private void setAppiumDriver(String platformName,
                                 String deviceName,
                                 String udid,
                                 String browserName,
                                 String app,
                                 String appPackage,
                                 String appActivity,
                                 String bundleId) {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        // mandatory Android capabilities
        capabilities.setCapability("platformName", platformName);
        capabilities.setCapability("deviceName", deviceName);
        capabilities.setCapability("udid", udid);
        if (app.endsWith(".apk")) capabilities.setCapability("app", (new File(app)).getAbsolutePath());
        capabilities.setCapability("browserName", browserName);
        capabilities.setCapability("chromedriverDisableBuildCheck", "true");
        // Capabilities for test of Android native app on EPAM Mobile Cloud
        capabilities.setCapability("appPackage", appPackage);
        capabilities.setCapability("appActivity", appActivity);
        // Capabilities for test of iOS native app on EPAM Mobile Cloud
        capabilities.setCapability("bundleId", bundleId);
//        capabilities.setCapability("showIOSLog", "true");
//        capabilities.setCapability("newCommandTimeout", "10000");
//        capabilities.setCapability("launchTimeout", "3000");
//        if(platformName.equals("iOS")) capabilities.setCapability("automationName","XCUITest");
        try {
            System.out.println("url = " + System.getProperty("ts.appium"));
            appiumDriver = new AppiumDriver(new URL(System.getProperty("ts.appium")), capabilities);
//            System.out.println("app is installed = " + appiumDriver.isAppInstalled(bundleId));
        } catch (org.openqa.selenium.SessionNotCreatedException e) {
            System.out.println("Couldn't connect");
            e.printStackTrace();
        } catch (MalformedURLException e) {
            System.out.println("MalformedURLException");
            e.printStackTrace();
        }
        // Timeouts tuning
        appiumDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }


    /*private void setAppiumDriver(String platformName, String appType, String deviceName, String udid, String browserName, String app, String appPackage, String appActivity, String bundleId) {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        //mandatory Android capabilities
        capabilities.setCapability("platformName", platformName);
        capabilities.setCapability("appType", appType);
        capabilities.setCapability("deviceName", deviceName);
        capabilities.setCapability("udid", udid);

        if (app.endsWith(".apk")) capabilities.setCapability("app", (new File(app)).getAbsolutePath());

        // Capabilities for test Android Native app on EPAM Mobile Cloud
        capabilities.setCapability("appPackage", appPackage);
        capabilities.setCapability("appActivity", appActivity);

        // Capabilities for test iOS Native app on EPAM Mobile Cloud
        capabilities.setCapability("bundleId", bundleId);

        // Capabilities for test web app
        capabilities.setCapability("browserName", browserName);
        capabilities.setCapability("chromedriverDisableBuildCheck", "true");

        try {
            appiumDriver = new AppiumDriver(new URL(System.getProperty("ts.appium")), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        // Timeouts tuning
        appiumDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }*/

    private void setPageObject(String appType, AppiumDriver appiumDriver) throws Exception {
        pageObject = new PageObject(appType, appiumDriver);
    }
}
