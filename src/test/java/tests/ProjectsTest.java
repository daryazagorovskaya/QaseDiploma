package tests;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.$;

public class ProjectsTest extends BaseTest {

    @BeforeMethod
    public void setUp() {
        loginPage.openPage();
        loginPage.login(USER, PASSWORD);
        projectsPage.waitTillOpened();
    }
    Faker faker = new Faker();
    String projectName = faker.lorem().word() + faker.number().numberBetween(1, 100);
    String projectCode = faker.number().digits(9);
    String projectDescription = faker.lorem().word();
    String suiteName = faker.lorem().word();
    String suiteDescription = faker.lorem().sentence();
    String suitePreCondition = faker.lorem().sentence();

    @Test (description = "Remove project")
    public void removeProject() {
        projectsPage.createNewProject(projectName, projectCode, projectDescription);
        projectsPage.waitTillOpen();
        projectsPage.openPageProjects();
        projectsPage.waitTillOpen();
        projectsPage.removeProject(projectName);
        Assert.assertFalse(projectsPage.projectToDelete(projectName));
    }

    @Test (description = "Creation of the new public project")
    public void createNewProject() {
        projectsPage.createNewProject(projectName, projectCode, projectDescription);
        projectsPage.waitTillOpen();
        Assert.assertEquals(projectsPage.getTitle(), projectCode + " repository");
    }

    @Test (description = "Creation of the new suite")
    public void createNewSuite() {
        projectsPage.createNewProject(projectName, projectCode, projectDescription);
        projectsPage.waitTillOpen();
        projectsPage.fillFieldSuiteName(suiteName,
                suiteDescription,
                suitePreCondition);
        Assert.assertEquals(projectsPage.getSuiteName(suiteName), suiteName);
    }

    @Test (description = "Check button 'Settings' project")
    public void checkSettingButton() {
        projectsPage.clickOnSettings("ShareLane");
        Assert.assertEquals(projectsPage.settingsIsDisplayed(), "Project settings", "Test failed");
    }
}
