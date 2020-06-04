package pageObjects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class NativePageObject {
    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/register_button")
    @iOSXCUITFindBy(xpath = "XCUIElementTypeCell[@label='Register new account']")
    WebElement registerBtn;

    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/registration_email")
    @iOSXCUITFindBy(xpath = "XCUIElementTypeTextField[@value='user@example.com']")
    WebElement registrationEmailField;

    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/registration_username")
    @iOSXCUITFindBy(xpath = "XCUIElementTypeSecureTextField[@value='TimApple']")
    WebElement registrationUsernameField;

    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/registration_password")
    @iOSXCUITFindBy(xpath = "XCUIElementTypeSecureTextField[@value='Required']")
    WebElement registrationPasswordField;

    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/registration_confirm_password")
    @iOSXCUITFindBy(xpath = "XCUIElementTypeSecureTextField[@value='Repeat please']")
    WebElement registrationConfirmPassField;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@label='Budget']")
//    @AndroidFindBy(xpath = "")
    WebElement agreement;

    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/register_new_account_button")
    @iOSXCUITFindBy(xpath = "XCUIElementTypeStaticText[@label='Register new account']")
    WebElement registerNewAccBtn;

    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/email_sign_in_button")
    @iOSXCUITFindBy(xpath = "XCUIElementTypeSecureTextField[@label='Sign in button']")
    WebElement signInBtn;

    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/login_email")
    @iOSXCUITFindBy(xpath = "XCUIElementTypeTextField[@value='user@example.com']")
    WebElement signInEmailField;

    @AndroidFindBy(id = "platkovsky.alexey.epamtestapp:id/login_pwd")
    @iOSXCUITFindBy(xpath = "XCUIElementTypeTextField[@value='Required']")
    WebElement signInPasswordField;

    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/" +
            "android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/" +
            "android.view.ViewGroup/android.widget.TextView")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@label='Budget']")
    WebElement titleElement;

//    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.widget.LinearLayout/android.widget.ScrollView/android.widget.LinearLayout/TextInputLayout[2]")
//    @iOSXCUITFindBy(xpath = "XCUIElementTypeStaticText[@value='Email or Username field should be filled.']")
//    WebElement errorMessage;

    public NativePageObject(AppiumDriver appiumDriver) {
        PageFactory.initElements(new AppiumFieldDecorator(appiumDriver), this);
    }
}
