package tests;

import com.github.javafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ProjectsTest extends BaseTest {

    @BeforeMethod
    public void setUp() {
        loginPage.openPage();
        loginPage.login(USER, PASSWORD);
        projectsPage.waitTillOpened();
    }
    Faker faker = new Faker();
    String projectName = faker.lorem().word() + faker.number().numberBetween(1, 100);
    String projectCode = faker.number().digits(9) ;
    String projectDescription = faker.lorem().word();


    @Test (description = "Remove project")
    public void removeProject() {
        projectsPage.createNewProject(projectName, projectCode, projectDescription);
        projectsPage.waitTillOpen();
        projectsPage.openPageProjects();
        projectsPage.waitTillOpen();
        projectsPage.removeProject(projectName);
        Assert.assertFalse(projectsPage.isProjectExist(projectName));
    }

    @Test (description = "Creation of the new public project")
    public void createNewProject() {
        projectsPage.createNewProject(projectName, projectCode, projectDescription);
        projectsPage.waitTillOpen();
        Assert.assertEquals(projectsPage.getTitle(), projectCode + " repository");
        projectsPage.waitTillOpen();
        deleteProjectAfterCreate(projectCode);
    }
}
