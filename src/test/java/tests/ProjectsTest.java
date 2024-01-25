package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.$;

public class ProjectsTest extends BaseTest {

    @Test (description = "Remove project")
    public void removeProject() {
        loginPage.openPage();
        loginPage.login("zagorik.dasha17@gmail.com", "Switch206med)");
        projectsPage.waitTillOpened();
        String projectName = faker.name().firstName() + faker.name().lastName();
        $(By.xpath("//table//tr[2]//td[8]")).click();
        $(By.xpath("//div//button[text()='Remove']")).click();
    }

    @Test (description = "Creation of the new public project")
    public void createNewProject() {
        loginPage.openPage();
        loginPage.login("zagorik.dasha17@gmail.com", "Switch206med)");
        projectsPage.waitTillOpened();
        String projectName = faker.name().firstName() + faker.name().lastName();
        projectsPage.createNewProject("Test3919", "80208610", "Test");
        projectsPage.waitTillOpen();
        Assert.assertTrue(projectsPage.getTitle().contains("80208610 repository"));
    }

    @Test (description = "Creation of the new suite")
    public void createNewCase() {
        loginPage.openPage();
        loginPage.login("zagorik.dasha17@gmail.com", "Switch206med)");
        projectsPage.createNewProject("Test0675", "2366", "Test");
        projectsPage.waitTillOpen();
        projectsPage.fillFieldSuiteName("Web",
                "The mobile app allows you to manage your mobile account anywhere and anytime from your smartphone",
                "With the application, You can 24h / 24h and 7d / 7d:");
        Assert.assertTrue(projectsPage.getSuiteName().contains("Web"));
    }

    @Test
    public void fhdjsk() {
        loginPage.openPage();
        loginPage.login("zagorik.dasha17@gmail.com", "Switch206med)");
        projectsPage.createNewProject("Test1545", "1486", "Test");
        projectsPage.waitTillOpen();
        projectsPage.fillFieldSuiteName("Web",
                "The mobile app allows you to manage your mobile account anywhere and anytime from your smartphone",
                "With the application, You can 24h / 24h and 7d / 7d:");
        testCasePage.waitTillOpen();
        $(By.xpath("//label[text()='Milestone']//../following-sibling::*//input")).click();

    }
}
