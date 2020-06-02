package pageObjects;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import setup.IPageObject;

import java.lang.reflect.Field;

public class PageObject implements IPageObject {

    Object somePageObject; // it should be set of web page or EPAM Test App WebElements

    public PageObject(String appType, AppiumDriver appiumDriver) throws Exception {

        System.out.println("Current app type: " + appType);
        switch(appType){
            case "web":
                somePageObject = new WebPageObject(appiumDriver);
                break;
            case "native":
                somePageObject = new NativePageObject(appiumDriver);
                break;
            default: throw new Exception("Can't create a page object for "+appType);
        }

    }

    @Override
    public WebElement getWebElement(String weName) throws NoSuchFieldException, IllegalAccessException {
        // use reflection technique
        Field field = somePageObject.getClass().getDeclaredField(weName);
        field.setAccessible(true);
        return (WebElement) field.get(somePageObject);
    }

    public void sendKeyToField(String weName, String key) throws NoSuchFieldException, IllegalAccessException {
        getWebElement(weName).sendKeys(key);
    }

    public void clickButton(String weName) throws NoSuchFieldException, IllegalAccessException {
        getWebElement(weName).click();
    }

    public Boolean checkIfSearchResultIsDisplayed(String weName) throws NoSuchFieldException, IllegalAccessException {
        return getWebElement(weName).isDisplayed();
    }

    public String getTextOfWebElement(String weName) throws NoSuchFieldException, IllegalAccessException {
        return getWebElement(weName).getText();
    }
}