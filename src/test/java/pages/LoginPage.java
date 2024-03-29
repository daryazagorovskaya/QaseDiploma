package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

@Log4j2
public class
LoginPage  {
    final String EMAIL_CSS = "[name=email]";
    final String PASS_CSS = "[name=password]";
    final String SUBMIT_BTN_CSS = "[type=submit]";

    final private By BTN_REST_PASS = By.xpath("//h1[text()='Reset your password']");
    final private By BTN_FORGOT_PASS = By.xpath("//div//a[text()='Forgot password?']");

    @Step("Opening a login page")
    public void openPage() {
        log.info("Opening a login page");
        open("/login");
    }

    @Step("Log in using credentials: '{user}', '{password}'")
    public void login(String user, String password) {
        log.info("Log in using credentials: '{user}', '{password}'");
        $(EMAIL_CSS).sendKeys(user);
        $(PASS_CSS).sendKeys(password);
        $(SUBMIT_BTN_CSS).click();
    }

    @Step("Check opening 'Reset page'")
    public String isResetPageDisplayed() {
        log.info("Check opening 'Reset page'");
        return $(BTN_REST_PASS).getText();
    }

    @Step("Transit to page 'Reset password'")
    public void openPageResetPassword () {
        log.info("Transit to page 'Reset password'");
        $(BTN_FORGOT_PASS).click();
    }
}
