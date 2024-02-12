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
        projectsPage.createNewProject(projectName, projectCode, projectDescription);
        projectsPage.waitTillOpen();
    }

    Faker faker = new Faker();
    String projectName = faker.lorem().word() + faker.number().numberBetween(1, 100);
    String projectCode = faker.number().digits(9);
    String projectDescription = faker.lorem().word();
    String suiteName = faker.lorem().word();
    String suiteDescription = faker.lorem().sentence();
    String suitePreCondition = faker.lorem().sentence();
    String randomTitle = faker.lorem().sentence();

    @Test(description = "Creation of the new suite")
    public void createNewSuite() {
        suitePage.fillFieldSuiteName(suiteName,
                suiteDescription,
                suitePreCondition);
        Assert.assertEquals(suitePage.getSuiteName(suiteName), suiteName);
    }

    @Test(description = "Delete suite")
    public void deleteSuite() {
        suitePage.fillFieldSuiteName(suiteName,
                suiteDescription,
                suitePreCondition);
        Assert.assertEquals(suitePage.getSuiteName(suiteName), suiteName);
        suitePage.clickButtonDelete(suiteName);
        suitePage.confirmDeleteSuite();
        Assert.assertFalse(suitePage.suiteToDelete(suiteName));
    }

    @Test(description = "Clone suite")
    public void cloneSuite() {
        suitePage.fillFieldSuiteName(suiteName,
                suiteDescription,
                suitePreCondition);
        Assert.assertEquals(suitePage.getSuiteName(suiteName), suiteName);
        suitePage.clickButtonClone(suiteName);
        suitePage.addPrefixToCloneSuite("1");
        suitePage.cloneSuite();
        Assert.assertEquals(suitePage.getSuiteName(suiteName), suiteName);
    }

    @Test(description = "Edit suite")
    public void editSuite() {
        suitePage.fillFieldSuiteName(suiteName,
                suiteDescription,
                suitePreCondition);
        Assert.assertEquals(suitePage.getSuiteName(suiteName), suiteName);
        suitePage.clickButtonEdit(suiteName);
        suitePage.clearFieldSuiteName1(suiteName);
        suitePage.saveEditSuite();
        Assert.assertEquals(suitePage.getSuiteName(suiteName), suiteName);
    }

    @Test(description = "Creation test case of the new suite")
    public void createNewCaseInSuite() {
        suitePage.fillFieldSuiteName(suiteName,
                suiteDescription,
                suitePreCondition);
        Assert.assertEquals(suitePage.getSuiteName(suiteName), suiteName);
        suitePage.newTestCaseInSuiteButton(suiteName);
        testCasePage.waitTillOpen();
        TestCase testCase = TestCase.builder().
                title(randomTitle).
                build();
        testCasePage.fillInTestCase(testCase);
        testCasePage.saveTestCase();
        testCasePage.waitTillOpenedCase();
        testCasePage.testCaseShouldBeCreated(randomTitle);
    }
}
