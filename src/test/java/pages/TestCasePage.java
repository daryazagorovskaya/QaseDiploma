package pages;

import com.codeborne.selenide.Condition;
import dto.TestCase;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import wrappers.CheckBox;
import wrappers.Input;
import wrappers.PickList;

import static com.codeborne.selenide.Selenide.$;

@Log4j2
public class TestCasePage {
    final private By CREATE_NEW_CASE = By.id("create-case-button");
    final private By SAVE_BTN = By.xpath("//button//span[text()='Save']");
    final private By DELETE_BTN = By.xpath("//span[text()=' Delete']");
    final private By EDIT_BTN = By.xpath("//span[text()=' Edit']");
    final private By EDIT_TEST_CASE = By.xpath("//h1[text()='Edit test case']");
    final private By CREATE_CASE_TITLE = By.xpath("//h1[text()='Create test case']");
    final private By SAVE_AND_CREATE_ANOTHER = By.xpath("//button//span[text()='Save and create another']");
    private final By TITLE = By.id("title");
    private final String CHECK_TEST_CASE_EDIT = "//input[@type='checkbox']/../../following-sibling::div[text()='%s']";
    private final String NAME_REPOSITORY_CURRENT_PROJECT = "//h1//div[text()='%s']";
    private final String NAME_OF_PROJECT = "//div//a[text()='%s']";
    private final String PROJECT_NAME = "//div[text()='%s']";

    @Step("Opening a test case page")
    public void openTestCase() {
        log.info("Opening a test case page");
        $(CREATE_NEW_CASE).click();
    }

    @Step("Waiting for the 'Create test case' page to open")
    public void waitTillOpen() {
        log.info("Waiting for the 'Create test case' page to open");
        $(CREATE_CASE_TITLE).shouldBe(Condition.visible);
    }

    @Step("Check the opening of the 'Create test case' page")
    public String isTestCasePageDisplayed() {
        log.info("Check the opening of the 'Create test case' page");
        return $(CREATE_CASE_TITLE).shouldBe(Condition.visible).getText();
    }

    @Step("Waiting for the repository page to open")
    public void waitTillOpenRepositoryPage() {
        log.info("Waiting for the repository page to open");
        $(CREATE_NEW_CASE).shouldBe(Condition.visible);
    }

    @Step("Pick project in the 'Projects Page'")
    public void pickProjectInProjectPage(String nameOfProject) {
        log.info("Pick project in the 'Projects Page'");
        $(By.xpath(String.format(NAME_OF_PROJECT, nameOfProject))).click();
    }

    @Step("Click to button 'Save'")
    public void saveTestCase() {
        log.info("Click to button 'Save'");
        $(SAVE_BTN).click();
    }

    @Step("Click to button 'Save and create another project'")
    public void saveAndCreateAnotherProject() {
        log.info("Click to button 'Save and create another project'");
        $(SAVE_AND_CREATE_ANOTHER).click();
    }

    @Step("Adding information to all fields of the test case")
    public void fillInTestCase(TestCase testCase) {
        log.info("Adding information to all fields of the test case");
        new Input().write("Title", testCase.getTitle());
        new Input().write("Description",testCase.getDescription());
        new PickList().select("Status",testCase.getStatus());
        new PickList().setSuite(testCase.getSuite());
        new PickList().select("Severity",testCase.getSeverity());
        new PickList().select("Priority",testCase.getPriority());
        new PickList().select("Type",testCase.getType());
        new PickList().select("Layer",testCase.getLayer());
        new PickList().select("Is flaky",testCase.getIsFlaky());
        new PickList().setMilestone(testCase.getMilestone());
        new PickList().select("Behavior",testCase.getBehavior());
        new PickList().select("Automation status",testCase.getAutomationStatus());
        new CheckBox().check();
        new Input().write("Pre-conditions",testCase.getPreConditions());
        new Input().write("Post-conditions",testCase.getPostConditions());
        new Input().writeParameter("Parameter title", testCase.getParameterTitle());
        new Input().writeParameterValues("Parameter values", testCase.getParameterValues());
        new PickList().setTypeTestCaseSteps("Gherkin");
    }

    @Step("Check creation test case")
    public void testCaseShouldBeCreated (String title) {
        log.info("Check creation test case");
        $(By.xpath(String.format("//div[text()='%s']", title))).shouldBe(Condition.visible);
    }

    @Step("Deletion test case")
    public void testCaseCheckDelete (String title) {
        log.info("Deletion test case");
        $(By.xpath(String.format(CHECK_TEST_CASE_EDIT, title))).click();
        $(DELETE_BTN).click();
    }

    @Step("Checking deletion test case")
    public boolean testCaseToDelete(String name) {
        log.info("Checking deletion test case");
        $(By.xpath(String.format("//div[text()='%s']", name))).shouldBe(Condition.visible);
        return false;
    }

    @Step("Edit test case")
    public void testCaseCheckEdit (String title) {
        log.info("Edit test case");
        $(By.xpath(String.format(CHECK_TEST_CASE_EDIT, title))).click();
        $(EDIT_BTN).click();
    }

    @Step("Checking edit test case")
    public void testCaseShouldBeOpenEditPage () {
        log.info("Checking edit test case");
        $(EDIT_TEST_CASE).shouldBe(Condition.visible);
    }

    public void clearField () {
        $(TITLE).clear();
    }

    public void repositoryCurrentProject (String title) {
        $(By.xpath(String.format(NAME_REPOSITORY_CURRENT_PROJECT, title))).shouldBe(Condition.visible);
    }

    public void testCasePageDescription () {
        $(EDIT_BTN).shouldBe(Condition.visible);
    }
}
