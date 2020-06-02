package scenarios;

import org.openqa.selenium.*;
import setup.BaseTest;

import static utils.CutImageUtil.cutImage;
import static utils.PropertyDataInitializer.getTestProperty;

import java.io.File;
import java.io.IOException;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class NativeMobileTests extends BaseTest {
    @Test(groups = {"native"}, description = "When user clicks sign in button without entering email and password popup error window is shown")
    public void checkIncorrectEmailAndPasswordMessage() throws IllegalAccessException, NoSuchFieldException, IOException, InterruptedException {
        // Try to sign in without typing email and password
        getPageObject().clickButton("signInBtn");
        // I know that Thread.sleep() is bad practice, but I couldn't find way to handle virtual function 'setError'
        // to set Autocomplete textView on widget.
        // For example, I've tried to make Explicit wait for email field and handle condition when all dynamic actions would finish,
        // but as I say virtual function wasn't handled.
        //So only way to catch textView which I've found is to put Thread.sleep().
        Thread.sleep(5000);
        // get emulator screenshot
        File screenShot = getDriver().getScreenshotAs(OutputType.FILE);
        // cut screenshot to exclude excess text
        cutImage(screenShot);
        // recognize text on image and check if text of error message is in the screen
        try {
            Tesseract tesseract = new Tesseract();
            File file = new File(this.getClass().getClassLoader().getResource("tesseractUtils/").getPath());
            tesseract.setDatapath(file.getAbsolutePath());
            String text = tesseract.doOCR(screenShot);
            assertTrue(text.contains(getTestProperty("incorrectInputMessage")),
                    "actual text = " + text + ", but expected it includes = " + getTestProperty("incorrectInputMessage"));
        } catch (TesseractException e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"native"}, description = "Register new User and test how that user sign in")
    public void simpleNativeTest() throws IllegalAccessException, NoSuchFieldException {
        //go to register page and register new user
        getPageObject().clickButton("registerBtn");
        getPageObject().sendKeyToField("registrationEmailField", getTestProperty("email"));
        getPageObject().sendKeyToField("registrationUsernameField", getTestProperty("username"));
        getPageObject().sendKeyToField("registrationPasswordField", getTestProperty("password"));
        getPageObject().sendKeyToField("registrationConfirmPassField", getTestProperty("password"));
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