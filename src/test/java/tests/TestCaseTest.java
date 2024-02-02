package tests;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import wrappers.CheckBox;
import wrappers.Input;
import wrappers.PickList;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.webdriver;

public class TestCaseTest extends BaseTest {

    Faker faker = new Faker();
    String projectName = faker.lorem().word() + faker.number().numberBetween(1, 100);
    String projectCode = faker.number().digits(9);
    String projectDescription = faker.lorem().word();

    @Test (description = "Creation of the test case without adding conditions and the steps")
    public void createTestCaseInCreatedProject() {
        loginPage.openPage();
        loginPage.login(USER, PASSWORD);
        projectsPage.waitTillOpened();
        testCasePage.pickProjectInProjectPage("ShareLane");
        testCasePage.waitTillOpenedCase();
        testCasePage.openTestCase();
        Assert.assertEquals(testCasePage.isTestCasePageDisplayed(), "Create test case", "Test failed");
    }

    @Test
    public void createTestCaseInNewProject() {
        loginPage.openPage();
        loginPage.login(USER, PASSWORD);
        projectsPage.waitTillOpened();
        testCasePage.pickProjectInProjectPage("ShareLane");
        testCasePage.waitTillOpenedCase();
        testCasePage.openTestCase();
        Assert.assertEquals(testCasePage.isTestCasePageDisplayed(), "Create test case", "Test failed");
    }

    @Test
    public void createTestCaseWithRequiredField() {
        loginPage.openPage();
        loginPage.login(USER, PASSWORD);
        projectsPage.waitTillOpened();
        projectsPage.createNewProject(projectName, projectCode, projectDescription);
        testCasePage.waitTillOpenedCase();
        testCasePage.openTestCase();
        testCasePage.waitTillOpen();
    }

    @Test
    public void fillTestCase() {
        loginPage.openPage();
        loginPage.login(USER, PASSWORD);
        projectsPage.waitTillOpened();
        testCasePage.pickProjectInProjectPage("ShareLane");
        testCasePage.waitTillOpenedCase();
        testCasePage.openTestCase();
        testCasePage.waitTillOpen();
        new Input().write("Title", "pampam");
        new Input().write("Description","pampam");
        new PickList().select("Status","Actual");
        new PickList().setSuite("Login");
        new PickList().select("Severity","Normal");
        new PickList().select("Priority","Low");
        new PickList().select("Severity","Minor");
        new PickList().select("Type","Other");
        new PickList().select("Layer","API");
        new PickList().select("Is flaky","No");
        new PickList().setMilestone("Not set");
        new PickList().select("Behavior","Positive");
        new PickList().select("Automation status","Manual");
        new CheckBox().check();
        new Input().write("Pre-conditions","pampam");
        new Input().write("Post-conditions","pampam");
        new Input().writeParameter("Parameter title", "pampam");
        new Input().writeParameterValues("Parameter values", "pampam");
        new PickList().setTypeTestCaseSteps("Gherkin");
        new PickList().setGherkinSteps("Given", "pampam");
        $(By.xpath("//div[text()='Given']"));
        new PickList().setGherkinSteps("When", "pampam");
        //$(By.xpath("//div[text()='Given']/../../following-sibling::td//div//input")).sendKeys("pampan");
        //$(By.xpath("//*[@id='gherkin-add-step-btn']//span//text()")).click();




    }
}
