package setup;

import org.openqa.selenium.WebElement;

public interface IPageObject {

    WebElement getWebElement(String weName) throws NoSuchFieldException, IllegalAccessException, InstantiationException;

    public void sendKeyToField(String weName, String key) throws NoSuchFieldException, IllegalAccessException;

    public void clickButton(String weName) throws NoSuchFieldException, IllegalAccessException;

    public Boolean checkIfSearchResultIsDisplayed(String weName) throws NoSuchFieldException, IllegalAccessException;

    public String getTextOfWebElement(String weName) throws NoSuchFieldException, IllegalAccessException;
}
