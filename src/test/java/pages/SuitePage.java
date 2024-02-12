package pages;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

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

    public void fillFieldSuiteName(String suiteName, String description, String preconditions) {
        $(CREATE_NEW_SUITE).click();
        $(TITLE).sendKeys(suiteName);
        $(FIELD_DESCRIPTION).sendKeys(description);
        $(FIELD_PRECONDITIONS).sendKeys(preconditions);
        $(BUTTON_CREATE).click();
    }

    public String getSuiteName(String suiteNameAdd) {
        return $(By.xpath(String.format(SUITE_NAME, suiteNameAdd))).getText();
    }

    public boolean suiteToDelete(String suiteName) {
        $(By.xpath(String.format(SUITE_NAME, suiteName))).shouldBe(Condition.visible);
        return false;
    }

    public void clickButtonDelete(String suiteName) {
        $(By.xpath(String.format(DELETE_BTN, suiteName))).click();
    }

    public void confirmDeleteSuite() {
        $(DELETE_CONFIRM).click();
    }

    public void clickButtonClone(String suiteName) {
        $(By.xpath(String.format(CLONE_BTN, suiteName))).click();
    }

    public void addPrefixToCloneSuite(String prefix) {
        $(PREFIX).sendKeys(prefix);
    }

    public void cloneSuite() {
        $(CLONE_CONFIRM).click();
    }


    public void clickButtonEdit(String suiteName) {
        $(By.xpath(String.format(EDIT_BTN, suiteName))).click();
    }

    public void clearFieldSuiteName1(String suiteName) {
        $(TITLE).clear();
        $(TITLE).sendKeys(suiteName);
    }

    public void saveEditSuite () {
        $(SAVE_CONFIRM).click();
    }

    public void newTestCaseInSuiteButton(String suiteName) {
        $(By.xpath(String.format(NEW_CASE_BTN, suiteName))).click();
        $(CREATE_TEST_CASE_CONFIRM).click();
    }
}