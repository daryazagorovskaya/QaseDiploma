package tests;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.$;

public class ProjectsTest extends BaseTest {
    Faker faker = new Faker();
    String projectName = faker.lorem().word() + faker.number().numberBetween(1, 100);
    String projectCode = faker.number().digits(9);
    String projectDescription = faker.lorem().word();
    String suiteName = faker.lorem().word();
    @Test (description = "Remove project")
    public void removeProject() {
        loginPage.openPage();
        loginPage.login(USER, PASSWORD);
        projectsPage.waitTillOpened();
        projectsPage.createNewProject(projectName, projectCode, projectDescription);
        projectsPage.waitTillOpen();
        projectsPage.openPageProjects();
        projectsPage.waitTillOpen();
        projectsPage.removeProject(projectName);
        Assert.assertFalse(projectsPage.projectToDelete(projectName));
    }

    @Test (description = "Creation of the new public project")
    public void createNewProject() {
        loginPage.openPage();
        loginPage.login(USER, PASSWORD);
        projectsPage.waitTillOpened();
        projectsPage.createNewProject(projectName, projectCode, projectDescription);
        projectsPage.waitTillOpen();
        Assert.assertEquals(projectsPage.getTitle(), projectCode + " repository");
    }

    @Test (description = "Creation of the new suite")
    public void createNewSuite() {
        loginPage.openPage();
        loginPage.login(USER, PASSWORD);
        projectsPage.createNewProject(projectName, projectCode, projectDescription);
        projectsPage.waitTillOpen();
        projectsPage.fillFieldSuiteName(suiteName,
                "The mobile app allows you to manage your mobile account anywhere and anytime from your smartphone",
                "With the application, You can 24h / 24h and 7d / 7d:");
        Assert.assertEquals(projectsPage.getSuiteName(suiteName), suiteName);
    }

    @Test (description = "Check button 'Settings' project")
    public void checkSettingButton() {
        loginPage.openPage();
        loginPage.login(USER, PASSWORD);
        projectsPage.waitTillOpened();
        projectsPage.clickOnSettings("ShareLane");
        Assert.assertEquals(projectsPage.settingsIsDisplayed(), "Project settings", "Test failed");
    }
}
