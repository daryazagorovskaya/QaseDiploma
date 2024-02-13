package pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

@Log4j2
public class SuitePage {

    final private By FIELD_DESCRIPTION = By.xpath("//label[text()='Description']/parent::div/following::p");
    final private By BUTTON_CREATE = By.xpath("//span[text()='Create']");
    final private By FIELD_PRECONDITIONS = By.xpath("//label[text()='Preconditions']/parent::div/following::p");
    final private By DELETE_CONFIRM = By.xpath("//button//span[text()='Delete']");
    final private By CLONE_CONFIRM = By.xpath("//span[text()='Clone']");
    final private By SAVE_CONFIRM = By.xpath("//span[text()='Save']");
    final private By CREATE_TEST_CASE_CONFIRM = By.xpath("//a[text()='Create case']");
    private final String SUITE_NAME = "//a[text()='%s']";
    private final String DELETE_BTN = "//span[text()='%s']/following-sibling::div//button[4]";
    private final String CLONE_BTN = "//span[text()='%s']/following-sibling::div//button[3]";
    private final String EDIT_BTN = "//span[text()='%s']/following-sibling::div//button[2]";
    private final String NEW_CASE_BTN = "//span[text()='%s']/following-sibling::div//button[1]";
    final private By CREATE_NEW_SUITE = By.id("create-suite-button");
    private final By TITLE = By.id("title");
    private final By PREFIX = By.id("prefix");

    @Step("Adding information to create a suite")
    public void fillFieldSuiteName(String suiteName, String description, String preconditions) {
        log.info("Adding information to create a suite");
        $(CREATE_NEW_SUITE).click();
        $(TITLE).sendKeys(suiteName);
        $(FIELD_DESCRIPTION).sendKeys(description);
        $(FIELD_PRECONDITIONS).sendKeys(preconditions);
        $(BUTTON_CREATE).click();
    }
    @Step("Checking that the repository page has opened")
    public void waitTillOpenRepositoryPage() {
        log.info("Checking that the repository page has opened");
        $(CREATE_NEW_SUITE).shouldBe(Condition.visible);
    }

    @Step("Check if suite is exists")
    public String suiteShouldExist(String suiteNameAdd) {
        log.info("Check if suite is exists");
        return $(By.xpath(String.format(SUITE_NAME, suiteNameAdd))).shouldHave(text(suiteNameAdd)).getText();
    }

    @Step("Check if suite is deleted")
    public boolean suiteToDelete(String suiteName) {
        log.info("Check if suite is deleted");
        $(By.xpath(String.format(SUITE_NAME, suiteName))).shouldBe(Condition.visible);
        return false;
    }

    @Step("Pick suite to deletion")
    public void clickButtonDelete(String suiteName) {
        log.info("Pick project to deletion");
        $(By.xpath(String.format(DELETE_BTN, suiteName))).click();
    }

    @Step("Suite deletion confirmation")
    public void confirmDeleteSuite() {
        log.info("Suite deletion confirmation");
        $(DELETE_CONFIRM).click();
    }

    @Step("Pick suite to clone")
    public void clickButtonClone(String suiteName) {
        log.info("Pick suite to clone");
        $(By.xpath(String.format(CLONE_BTN, suiteName))).click();
    }

    @Step("Add prefix to suite name")
    public void addPrefixToCloneSuite(String prefix) {
        log.info("Add prefix to suite name");
        $(PREFIX).sendKeys(prefix);
    }

    @Step("Suite clone confirmation")
    public void cloneSuite() {
        log.info("Suite clone confirmation");
        $(CLONE_CONFIRM).click();
    }

    @Step("Pick suite to edit")
    public void clickButtonEdit(String suiteName) {
        log.info("Pick suite to edit");
        $(By.xpath(String.format(EDIT_BTN, suiteName))).click();
    }

    @Step("Clear field 'Suite name'")
    public void clearFieldSuiteName(String suiteName) {
        log.info("Clear field 'Suite name'");
        $(TITLE).clear();
        $(TITLE).sendKeys(suiteName);
    }

    @Step("Click to button 'Save Edit'")
    public void saveEditSuite () {
        log.info("Click to button 'Save Edit'");
        $(SAVE_CONFIRM).click();
    }

    @Step("Creation new test case in suite")
    public void newTestCaseInSuiteButton(String suiteName) {
        log.info("Creation new test case in suite");
        $(By.xpath(String.format(NEW_CASE_BTN, suiteName))).click();
        $(CREATE_TEST_CASE_CONFIRM).click();
    }
}