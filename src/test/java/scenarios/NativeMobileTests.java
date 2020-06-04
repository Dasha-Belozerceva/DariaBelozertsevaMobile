package scenarios;

import org.openqa.selenium.*;
import setup.BaseTest;

import static org.testng.Assert.assertEquals;
import static utils.PropertyDataInitializer.getTestProperty;

import org.testng.annotations.Test;

public class NativeMobileTests extends BaseTest {
    @Test(groups = {"native"}, description = "Register new User and test how that user sign in")
    public void simpleNativeTest() throws IllegalAccessException, NoSuchFieldException {
        //go to register page and register new user
//        getPageObject().clickButton("signInBtn");
//        assertEquals(getPageObject().getTextOfWebElement("errorMessage"), getTestProperty("incorrectInputMessage"),
//                "Expected message: " +
//                        getTestProperty("incorrectInputMessage") +
//                        "but actual is " + getPageObject().getTextOfWebElement("errorMessage"));
        getPageObject().clickButton("registerBtn");
        getPageObject().sendKeyToField("registrationEmailField", getTestProperty("email"));
        getPageObject().sendKeyToField("registrationUsernameField", getTestProperty("username"));
        getPageObject().sendKeyToField("registrationPasswordField", getTestProperty("password"));
        getPageObject().sendKeyToField("registrationConfirmPassField", getTestProperty("password"));
//        // for iOS
//        getPageObject().clickButton("agreement");
        getPageObject().clickButton("registerNewAccBtn");
        //sign in as new user
        getPageObject().sendKeyToField("signInEmailField", getTestProperty("email"));
        getPageObject().sendKeyToField("signInPasswordField", getTestProperty("password"));
        getPageObject().clickButton("signInBtn");
        //check if we are on 'Budget Activity' page
        assertEquals(getPageObject().getTextOfWebElement("titleElement"), getTestProperty("title"),
                "Page title is not 'Budget Activity'");
    }
}