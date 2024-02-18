package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

@Log4j2
public class ProjectsPage {
    private final By PROJECT_NAME = By.id("project-name");
    private final By PROJECT_CODE = By.id("project-code");
    private final By DESCRIPTION = By.id("description-area");
    private final By CREATE_NEW_BTN = By.id("createButton");
    final private By CREATE_BTN = By.xpath("//span[text()='Create project']");
    final private By MAIN_MENU_BTN = By.xpath("//span//img");
    final private By SIGN_OUT_BTN = By.xpath("//div//span[text()='Sign out']");
    final private By LOG_IN_MESSAGE = By.xpath("//div//h1[text()='Log in to your account']");
    final private By NAME_OF_PROJECT = By.xpath("//h1[text()=' repository']");
    final private By BTN_ADD_FILTER = By.xpath("//button[text()='Add filter']");
    final private String  BTN_ABOUT_PROJECT = "//a[text()='%s']/parent::div/../../following-sibling::td//button";
    final private By BTN_REMOVE = By.xpath("//div//button[text()='Remove']");
    final private String  SEARCH_PROJECT_BY_NAME = "//a[text()='%s']/parent::div/../../following-sibling::td//button//span";
    final private By ALERT_CREDENTIALS_MESSAGE = By.cssSelector("[role=alert]");

    @Step("Opening a project page")
    public void openPageProjects() {
        log.info("Opening a project page");
        open("/projects");
    }


    @Step("Checking transit to projects page")
    public void waitTillOpened() {
        $(CREATE_NEW_BTN).shouldBe(visible, Duration.ofSeconds(30));
    }

    @Step("Checking that the projects creation page has opened")
    public String waitTillOpenedIsDisplayed() {
        log.info("Checking that the projects creation page has opened");
        return $(CREATE_NEW_BTN).shouldBe(visible).getText();
    }

    @Step("Waiting for the project creation page to open")
    public void waitTillOpen() {
        log.info("Waiting for the project creation page to open");
        $(BTN_ADD_FILTER).shouldBe(visible);
    }

    @Step("Creation new project")
    public void createNewProject(String name, String code, String description) {
        log.info("Creation new project");
        $(CREATE_NEW_BTN).click();
        $(PROJECT_NAME).sendKeys(name);
        $(PROJECT_CODE).clear();
        $(PROJECT_CODE).sendKeys(code);
        $(DESCRIPTION).sendKeys(description);
        $(CREATE_BTN).click();
    }

    @Step("Check if repository is created")
    public String getTitle() {
        log.info("Check if repository is created");
         return $(NAME_OF_PROJECT).getText();
    }

    @Step("Error message for incorrect authorization")
    public String isAlertDisplayed() {
        log.info("Error message for incorrect authorization");
        return $(ALERT_CREDENTIALS_MESSAGE).getText();
    }

    @Step("Log in button when logging out")
    public void waitTillOpenedAutPage() {
        log.info("Log in button when logging out");
        $(LOG_IN_MESSAGE).shouldBe(visible);
    }

    @Step("Log in message when logging out")
    public String isAutPageDisplayed() {
        log.info("Log in message when logging out");
        return $(LOG_IN_MESSAGE).getText();
    }

    @Step("Logout")
    public void logOut() {
        log.info("Logout");
        $(MAIN_MENU_BTN).click();
        $(SIGN_OUT_BTN).click();
    }

    @Step("Check that the project has been deleted")
    public boolean isProjectExist(String name) {
        log.info("Check that the project has been deleted");
        $(By.xpath(String.format(BTN_ABOUT_PROJECT, name))).shouldBe(visible);
        return false;
    }

    @Step("Delete project")
    public void removeProject(String name) {
        log.info("Delete project");
        $(By.xpath(String.format(SEARCH_PROJECT_BY_NAME, name))).click();
        $(BTN_REMOVE).click();
    }
}
