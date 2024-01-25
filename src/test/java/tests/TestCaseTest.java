package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.$;

public class TestCaseTest extends BaseTest {

    @Test (description = "Creation of the test case without adding conditions and the steps")
    public void createTestCaseInCreatedProject() {
        loginPage.openPage();
        loginPage.login("zagorik.dasha17@gmail.com", "Switch206med)");
        projectsPage.waitTillOpened();
        testCasePage.pickProjectInProjectPage("ShareLane");
        testCasePage.waitTillOpen();
        testCasePage.openTestCase();
        Assert.assertEquals(testCasePage.isTestCasePageDisplayed(), "Create test case", "Test failed");
    }

    @Test
    public void createTestCaseInNewProject() {
        loginPage.openPage();
        loginPage.login("zagorik.dasha17@gmail.com", "Switch206med)");
        projectsPage.waitTillOpened();
        testCasePage.pickProjectInProjectPage("ShareLane");
        testCasePage.waitTillOpen();
        testCasePage.openTestCase();
        Assert.assertEquals(testCasePage.isTestCasePageDisplayed(), "Create test case", "Test failed");
    }

    @Test
    public void createTestCaseWithRequiredField() {
        loginPage.openPage();
        loginPage.login("zagorik.dasha17@gmail.com", "Switch206med)");
        projectsPage.waitTillOpened();
        projectsPage.createNewProject("Test56659", "58763", "Test");
        projectsPage.waitTillOpen();
        testCasePage.openTestCase();
        testCasePage.waitTillOpen();
        $(By.xpath("//div[@class='Thgbhj euhZGB cfvQxI']/preceding-sibling::label[text()='Status']")).click();
        $(By.xpath("//*[@id='modals']/*)[last()]//*[text()='Actual']")).click();


    }
}
