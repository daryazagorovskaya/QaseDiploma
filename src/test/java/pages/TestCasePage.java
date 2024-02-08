package pages;

import com.codeborne.selenide.Condition;
import dto.TestCase;
import org.openqa.selenium.By;
import wrappers.CheckBox;
import wrappers.Input;
import wrappers.PickList;

import static com.codeborne.selenide.Selenide.$;

public class TestCasePage {
    final private By CREATE_NEW_CASE = By.id("create-case-button");
    final private By SAVE_BTN = By.xpath("//button//span[text()='Save']");
    final private By EDIT_BTN = By.xpath("//span[text()=' Edit']");


    public void openTestCase() {
        $(CREATE_NEW_CASE).click();
    }

    public void waitTillOpen() {
        $(By.xpath("//h1[text()='Create test case']")).shouldBe(Condition.visible);
    }

    public String isTestCasePageDisplayed() {
        return  $(By.xpath("//h1[text()='Create test case']")).shouldBe(Condition.visible).getText();
    }

    public void waitTillOpenRepositoryPage() {
        $(By.id("create-case-button")).shouldBe(Condition.visible);
    }

    public void pickProjectInProjectPage(String nameOfProject) {
        $(By.xpath(String.format("//div//a[text()='%s']", nameOfProject))).click();
    }

    public void waitTillOpenedCase() {
        $(CREATE_NEW_CASE).shouldBe(Condition.visible);
    }

    public void saveProject() {
        $(SAVE_BTN).click();
    }

    public void saveAndCreateAnotherProject() {
        $(By.xpath("//button//span[text()='Save and create another']")).click();
    }

    public void cancelProject() {
        $(By.xpath("//button//span[text()='Cancel']")).click();
    }

    public void fillInTestCase(TestCase testCase) {
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
        //new PickList().setGherkinSteps("Given", "pampam");
    }

    public void testCaseShouldBeCreated (String title) {
        $(By.xpath(String.format("//div[text()='%s']", title))).shouldBe(Condition.visible);
    }
    public void testCaseCheckDelete (String title) {
        $(By.xpath(String.format("//input[@type='checkbox']/../../following-sibling::div[text()='%s']", title))).click();
        $(By.xpath("//span[text()=' Delete']")).click();
    }

    public boolean testCaseToDelete(String name) {
        $(By.xpath(String.format("//div[text()='%s']", name))).shouldBe(Condition.visible);
        return false;
    }

    public void testCaseCheckEdit (String title) {
        $(By.xpath(String.format("//input[@type='checkbox']/../../following-sibling::div[text()='%s']", title))).click();
        $(EDIT_BTN).click();
    }

    public void testCaseShouldBeOpenEditPage () {
        $(By.xpath(String.format("//h1[text()='Edit test case']"))).shouldBe(Condition.visible);
    }

    public void editFieldChangedField () {
        $(By.xpath("//div[text()='has updated ']")).shouldBe(Condition.visible);
    }

    public void clearField () {
        $(By.id("title")).clear();
    }

    public void repositoryCurrentProject (String title) {
        $(By.xpath(String.format("//h1//div[text()='%s']", title))).shouldBe(Condition.visible);
    }

    public void testCasePageDescription () {
        $(EDIT_BTN).shouldBe(Condition.visible);
    }
}
