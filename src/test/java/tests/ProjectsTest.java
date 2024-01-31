package tests;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.$;

public class ProjectsTest extends BaseTest {
    Faker faker = new Faker();
    String projectName = faker.lorem().word() + faker.number().numberBetween(1, 100);
    String projectCode = faker.number().digits(8);
    String projectDescription = faker.lorem().word();
    @Test (description = "Remove project")
    public void removeProject() {
        loginPage.openPage();
        loginPage.login("zagorik.dasha17@gmail.com", "Switch206med)");
        projectsPage.waitTillOpened();
        String projectName = faker.name().firstName() + faker.name().lastName();
        $(By.xpath("//table//tr[3]//td[8]")).click();
        $(By.xpath("//div//button[text()='Remove']")).click();
        Assert.assertFalse(projectsPage.projectToDelete());
    }

    @Test (description = "Creation of the new public project")
    public void createNewProject() {
        loginPage.openPage();
        loginPage.login("zagorik.dasha17@gmail.com", "Switch206med)");
        projectsPage.waitTillOpened();
        projectsPage.createNewProject(projectName, projectCode, projectDescription);
        projectsPage.waitTillOpen();
        Assert.assertTrue(projectsPage.getTitle().contains(" repository"));
    }

    @Test (description = "Creation of the new suite")
    public void createNewCase() {
        loginPage.openPage();
        loginPage.login("zagorik.dasha17@gmail.com", "Switch206med)");
        projectsPage.createNewProject(projectName, projectCode, projectDescription);
        projectsPage.waitTillOpen();
        projectsPage.fillFieldSuiteName("Web",
                "The mobile app allows you to manage your mobile account anywhere and anytime from your smartphone",
                "With the application, You can 24h / 24h and 7d / 7d:");
        Assert.assertTrue(projectsPage.getSuiteName().contains("Web"));
    }

    @Test (description = "Check button 'Settings' project")
    public void checkSettingButton() {
        loginPage.openPage();
        loginPage.login("zagorik.dasha17@gmail.com", "Switch206med)");
        projectsPage.waitTillOpened();
        projectsPage.clickOnSettings();
        Assert.assertEquals(projectsPage.settingsIsDisplayed(), "Project settings", "Test failed");
    }
}
