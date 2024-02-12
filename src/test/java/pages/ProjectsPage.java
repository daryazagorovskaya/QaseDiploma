package pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

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
    final private By BTN_SETTINGS = By.xpath("//a[text()='Settings']");
    final private By PROJECT_SETTINGS = By.xpath("//h1[text()='Project settings']");
    final private String  BTN_ABOUT_PROJECT = "//a[text()='%s']/parent::div/../../following-sibling::td//button";
    final private By BTN_REMOVE = By.xpath("//div//button[text()='Remove']");
    final private String  SEARCH_PROJECT_BY_NAME = "//a[text()='%s']/parent::div/../../following-sibling::td//button//span";
    final private By ALERT_CREDENTIALS_MESSAGE = By.cssSelector("[role=alert]");


    public void openPage() {
        open("/properties");
    }

    public void openPageProjects() {
        open("/projects");
    }


    public void waitTillOpened() {
        $(CREATE_NEW_BTN).shouldBe(Condition.visible);
    }


    public String waitTillOpenedIsDisplayed() {
        return $(CREATE_NEW_BTN).shouldBe(Condition.visible).getText();
    }

    public void waitTillOpen() {
        $(BTN_ADD_FILTER).shouldBe(Condition.visible);
    }

    public void createNewProject(String name, String code, String description) {
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

    public String isAlertDisplayed() {
        return $(ALERT_CREDENTIALS_MESSAGE).getText();
    }


    public void waitTillOpenedAutPage() {
        $(LOG_IN_MESSAGE).shouldBe(Condition.visible);
    }

    public String isAutPageDisplayed() {
        return $(LOG_IN_MESSAGE).getText();
    }

    public void logOut() {
        $(MAIN_MENU_BTN).click();
        $(SIGN_OUT_BTN).click();
    }

    public boolean projectToDelete(String name) {
        $(By.xpath(String.format(BTN_ABOUT_PROJECT, name))).shouldBe(Condition.visible);
        return false;
    }

    public void clickOnSettings(String name) {
        $(By.xpath(String.format(BTN_ABOUT_PROJECT , name))).click();
        $(BTN_SETTINGS).click();
    }


    public String settingsIsDisplayed() {
        return $(PROJECT_SETTINGS).getText();
    }

    public void removeProject(String name) {
        //$(By.xpath("//span[text()='']")).click();
        $(By.xpath(String.format(SEARCH_PROJECT_BY_NAME, name))).click();
        $(BTN_REMOVE).click();
    }
}
