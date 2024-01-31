package tests;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import wrappers.Input;
import wrappers.PickList;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.webdriver;

public class TestCaseTest extends BaseTest {

    Faker faker = new Faker();
    String projectName = faker.lorem().word() + faker.number().numberBetween(1, 100);
    String projectCode = faker.number().digits(8);
    String projectDescription = faker.lorem().word();

    @Test (description = "Creation of the test case without adding conditions and the steps")
    public void createTestCaseInCreatedProject() {
        loginPage.openPage();
        loginPage.login("zagorik.dasha17@gmail.com", "Switch206med)");
        projectsPage.waitTillOpened();
        testCasePage.pickProjectInProjectPage("ShareLane");
        testCasePage.waitTillOpenedCase();
        testCasePage.openTestCase();
        Assert.assertEquals(testCasePage.isTestCasePageDisplayed(), "Create test case", "Test failed");
    }

    @Test
    public void createTestCaseInNewProject() {
        loginPage.openPage();
        loginPage.login("zagorik.dasha17@gmail.com", "Switch206med)");
        projectsPage.waitTillOpened();
        testCasePage.pickProjectInProjectPage("ShareLane");
        testCasePage.waitTillOpenedCase();
        testCasePage.openTestCase();
        Assert.assertEquals(testCasePage.isTestCasePageDisplayed(), "Create test case", "Test failed");
    }

    @Test
    public void createTestCaseWithRequiredField() {
        loginPage.openPage();
        loginPage.login("zagorik.dasha17@gmail.com", "Switch206med)");
        projectsPage.waitTillOpened();
        projectsPage.createNewProject(projectName, projectCode, projectDescription);
        testCasePage.waitTillOpenedCase();
        testCasePage.openTestCase();
        testCasePage.waitTillOpen();
    }

    @Test
    public void fillTestCase() {
        loginPage.openPage();
        loginPage.login("zagorik.dasha17@gmail.com", "Switch206med)");
        projectsPage.waitTillOpened();
        testCasePage.pickProjectInProjectPage("ShareLane");
        testCasePage.waitTillOpenedCase();
        testCasePage.openTestCase();
        testCasePage.waitTillOpen();
        new Input("Title").write("Test 1");
        new PickList("Status").select("Actual");
    }
}
