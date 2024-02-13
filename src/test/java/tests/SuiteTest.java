package tests;

import com.github.javafaker.Faker;
import dto.TestCase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SuiteTest extends BaseTest {

    @BeforeMethod
    public void setUp() {
        loginPage.openPage();
        loginPage.login(USER, PASSWORD);
        projectsPage.waitTillOpened();
    }

    Faker faker = new Faker();
    String suiteName = faker.lorem().word();
    String suiteDescription = faker.lorem().sentence();
    String suitePreCondition = faker.lorem().sentence();
    String randomTitle = faker.lorem().sentence();

    @Test(description = "Creation of the new suite")
    public void createNewSuite() {
        String projectName = faker.lorem().word() + faker.number().numberBetween(1, 100);
        String projectCode = faker.number().digits(9);
        String projectDescription = faker.lorem().word();
        projectsPage.createNewProject(projectName, projectCode, projectDescription);
        projectsPage.waitTillOpen();
        suitePage.fillFieldSuiteName(suiteName, suiteDescription, suitePreCondition);
        suitePage.waitTillOpenRepositoryPage();
        String actualSuiteName = suitePage.suiteShouldExist(suiteName);
        Assert.assertEquals(actualSuiteName, suiteName, "Suite creation failed");
    }

    @Test(description = "Delete suite")
    public void deleteSuite() {
        String projectName = faker.lorem().word() + faker.number().numberBetween(1, 100);
        String projectCode = faker.number().digits(9);
        String projectDescription = faker.lorem().word();
        projectsPage.createNewProject(projectName, projectCode, projectDescription);
        projectsPage.waitTillOpen();
        suitePage.fillFieldSuiteName(suiteName,
                suiteDescription,
                suitePreCondition);
        Assert.assertEquals(suitePage.suiteShouldExist(suiteName), suiteName);
        suitePage.clickButtonDelete(suiteName);
        suitePage.confirmDeleteSuite();
        Assert.assertFalse(suitePage.suiteToDelete(suiteName));
    }

    @Test(description = "Clone suite")
    public void cloneSuite() {
        String projectName = faker.lorem().word() + faker.number().numberBetween(1, 100);
        String projectCode = faker.number().digits(9);
        String projectDescription = faker.lorem().word();
        projectsPage.createNewProject(projectName, projectCode, projectDescription);
        projectsPage.waitTillOpen();
        suitePage.fillFieldSuiteName(suiteName,
                suiteDescription,
                suitePreCondition);
        Assert.assertEquals(suitePage.suiteShouldExist(suiteName), suiteName);
        suitePage.clickButtonClone(suiteName);
        suitePage.addPrefixToCloneSuite("1");
        suitePage.cloneSuite();
        Assert.assertEquals(suitePage.suiteShouldExist(suiteName), suiteName);
    }

    @Test(description = "Edit suite")
    public void editSuite() {
        String projectName = faker.lorem().word() + faker.number().numberBetween(1, 100);
        String projectCode = faker.number().digits(9);
        String projectDescription = faker.lorem().word();
        projectsPage.createNewProject(projectName, projectCode, projectDescription);
        projectsPage.waitTillOpen();
        suitePage.fillFieldSuiteName(suiteName,
                suiteDescription,
                suitePreCondition);
        Assert.assertEquals(suitePage.suiteShouldExist(suiteName), suiteName);
        suitePage.clickButtonEdit(suiteName);
        suitePage.clearFieldSuiteName(suiteName);
        suitePage.saveEditSuite();
        Assert.assertEquals(suitePage.suiteShouldExist(suiteName), suiteName);
    }

    @Test(description = "Creation test case of the new suite")
    public void createNewCaseInSuite() {
        String projectName = faker.lorem().word() + faker.number().numberBetween(1, 100);
        String projectCode = faker.number().digits(9);
        String projectDescription = faker.lorem().word();
        projectsPage.createNewProject(projectName, projectCode, projectDescription);
        projectsPage.waitTillOpen();
        suitePage.fillFieldSuiteName(suiteName,
                suiteDescription,
                suitePreCondition);
        Assert.assertEquals(suitePage.suiteShouldExist(suiteName), suiteName);
        suitePage.newTestCaseInSuiteButton(suiteName);
        testCasePage.waitTillOpen();
        TestCase testCase = TestCase.builder().
                title(randomTitle).
                build();
        testCasePage.fillInTestCase(testCase);
        testCasePage.saveTestCase();
        testCasePage.waitTillOpenRepositoryPage();
        testCasePage.testCaseShouldBeCreated(randomTitle);
    }
}
