package pageObjects;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import setup.BaseTest;
import setup.IPageObject;

import java.lang.reflect.Field;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class PageObject extends BaseTest implements IPageObject {

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



    private WebDriverWait webDriverWait() {
        return new WebDriverWait(appiumDriver, 15);
    }

    protected WebElement waitAndGetElement(WebElement element) {
        return webDriverWait().until(visibilityOf(element));
    }

    public void sendKeyToField(String weName, String key) throws NoSuchFieldException, IllegalAccessException {
        WebElement fieldElement = waitAndGetElement(getWebElement(weName));
        fieldElement.sendKeys(key);
    }

    public void clickButton(String weName) throws NoSuchFieldException, IllegalAccessException {
        WebElement buttonElement = waitAndGetElement(getWebElement(weName));
        buttonElement.click();
    }

    public Boolean checkIfSearchResultIsDisplayed(String weName) throws NoSuchFieldException, IllegalAccessException {
        WebElement searchResult = waitAndGetElement(getWebElement(weName));
        return searchResult.isDisplayed();
    }

    public String getTextOfWebElement(String weName) throws NoSuchFieldException, IllegalAccessException {
        WebElement elementWithText = waitAndGetElement(getWebElement(weName));
        return elementWithText.getText();
    }
}