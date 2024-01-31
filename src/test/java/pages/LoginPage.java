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

    public void openPage() {
        open("/login");
    }

    @Step("Log in using credentials: '{user}', '{pass}'")
    public void login(String user, String pass) {
        log.info("Log in using credentials: '{user}', '{pass}'");
        $(EMAIL_CSS).sendKeys(user);
        $(PASS_CSS).sendKeys(pass);
        $(SUBMIT_BTN_CSS).click();
    }

    public String isResetPageDisplayed() {
        return $(BTN_REST_PASS).getText();
    }

    public void openPageResetPassword () {
        $(BTN_FORGOT_PASS).click();
    }
}
