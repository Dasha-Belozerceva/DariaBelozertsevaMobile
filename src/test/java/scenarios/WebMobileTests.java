package scenarios;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import setup.BaseTest;

import static org.testng.Assert.assertTrue;
import static utils.PropertyDataInitializer.getTestProperty;

public class WebMobileTests extends BaseTest {
    private void waitUntilPageLoad(){
        new WebDriverWait(getDriver(), 10).until(
                wd -> ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete")
        );
    }

    @Test(groups = {"web"}, description = "Make searching request in google page and check if results exist")
    public void simpleWebTest() throws IllegalAccessException, NoSuchFieldException, InstantiationException, Exception {
        getDriver().get(getTestProperty("url")); // open google search page
        // Make sure that page has been loaded completely
        waitUntilPageLoad();
        //search keyword in the searchField
        getPageObject().sendKeyToField("searchField", getTestProperty("keyword"));
        getPageObject().clickButton("searchBtn");
        //check if results of search are displayed on the page
        waitUntilPageLoad();
        assertTrue(getPageObject().checkIfSearchResultIsDisplayed("searchResult"), "Result of searching 'EPAM' keyword doesn't exist on the page");
    }
}
