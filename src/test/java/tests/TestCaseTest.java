package tests;

import com.github.javafaker.Faker;
import dto.TestCase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestCaseTest extends BaseTest {

    @BeforeMethod
    public void setUp() {
        loginPage.openPage();
        loginPage.login(USER, PASSWORD);
        projectsPage.waitTillOpened();
        testCasePage.pickProjectInProjectPage("ShareLane");
        testCasePage.waitTillOpenedCase();
    }
    Faker faker = new Faker();
    String randomTitle = faker.lorem().sentence();
    String randomTitle2 = faker.lorem().sentence();

    @Test(description = "Creation of the test case without adding conditions and the steps")
    public void createTestCaseInCreatedProject() {
        testCasePage.openTestCase();
        Assert.assertEquals(testCasePage.isTestCasePageDisplayed(), "Create test case", "Test failed");
    }

    @Test
    public void testCaseSuccessfulCreation() {
        testCasePage.openTestCase();
        testCasePage.waitTillOpen();
        TestCase testCase = TestCase.builder().
                title(randomTitle).
                description("pam").
                suite("Login").
                severity("Normal").
                priority("Low").
                type("Smoke").
                layer("API").
                isFlaky("No").
                milestone("Not set").
                behavior("Positive").
                automationStatus("Manual").
                preConditions("pam").
                postConditions("pam").
                parameterTitle("pam").
                parameterValues("pam").
                testCaseSteps("Gherkin").
                addStep("pam").
                build();
        testCasePage.fillInTestCase(testCase);
        testCasePage.saveProject();
        testCasePage.waitTillOpenRepositoryPage();
        testCasePage.testCaseShouldBeCreated(randomTitle);
    }

    @Test
    public void testCaseCheckDeleteTestCase() {
        testCasePage.openTestCase();
        testCasePage.waitTillOpen();
        TestCase testCase = TestCase.builder().
                title(randomTitle).
                build();
        testCasePage.fillInTestCase(testCase);
        testCasePage.saveProject();
        testCasePage.testCaseShouldBeCreated(randomTitle);
        testCasePage.testCaseCheckDelete(randomTitle);
        Assert.assertFalse(testCasePage.testCaseToDelete(randomTitle));
    }

    @Test
    public void testCaseCheckCreationTest() {
        testCasePage.openTestCase();
        testCasePage.waitTillOpen();
        TestCase testCase = TestCase.builder().
                title(randomTitle).
                build();
        testCasePage.fillInTestCase(testCase);
        testCasePage.saveProject();
        testCasePage.waitTillOpenRepositoryPage();
        testCasePage.testCaseShouldBeCreated(randomTitle);
    }

    @Test
    public void testCaseCheckButtonSaveAndCreationAnother() {
        testCasePage.openTestCase();
        testCasePage.waitTillOpen();
        TestCase testCase = TestCase.builder().
                title(randomTitle).
                build();
        testCasePage.fillInTestCase(testCase);
        testCasePage.saveAndCreateAnotherProject();
        testCasePage.waitTillOpen();
    }

    @Test
    public void testCaseCheckEditTestCase() {
        testCasePage.openTestCase();
        testCasePage.waitTillOpen();
        TestCase testCase = TestCase.builder().
                title(randomTitle).
                build();
        testCasePage.fillInTestCase(testCase);
        testCasePage.saveProject();
        testCasePage.testCaseShouldBeCreated(randomTitle);
        testCasePage.testCaseCheckEdit(randomTitle);
        testCasePage.testCaseShouldBeOpenEditPage();
    }

    @Test
    public void testCaseCheckEditField() {
        testCasePage.openTestCase();
        testCasePage.waitTillOpen();
        TestCase testCase = TestCase.builder().
                title(randomTitle).
                build();
        testCasePage.fillInTestCase(testCase);
        testCasePage.saveProject();
        testCasePage.testCaseShouldBeCreated(randomTitle);
        testCasePage.testCaseCheckEdit(randomTitle);
        testCasePage.testCaseShouldBeOpenEditPage();
        testCasePage.clearField();
        testCase = TestCase.builder()
                .title(randomTitle2)
                .build();
        testCasePage.fillInTestCase(testCase);
        testCasePage.saveProject();
        testCasePage.testCasePageDescription();
        testCasePage.repositoryCurrentProject(randomTitle2);
    }
}
