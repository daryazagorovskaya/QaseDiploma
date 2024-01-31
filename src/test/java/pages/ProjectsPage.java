package pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

@Log4j2
public class ProjectsPage {
    String label;
    private final By PROJECT_NAME = By.id("project-name");
    private final By PROJECT_CODE = By.id("project-code");
    private final By DESCRIPTION = By.id("description-area");
    private final By CREATE_NEW_BTN = By.id("createButton");
    final private By CREATE_BTN = By.xpath("//span[text()='Create project']");
    final private By CREATE_NEW_SUITE = By.id("create-suite-button");
    final private By MAIN_MENU_BTN = By.xpath("//span//img");
    final private By SIGN_OUT_BTN = By.xpath("//div//span[text()='Sign out']");
    final private By LOG_IN_MESSAGE = By.xpath("//div//h1[text()='Log in to your account']");
    final private By BTN_ADD_FILTER = By.xpath("//button[text()='Add filter']");
    final private By ALERT_CREDENTIALS_MESSAGE = By.cssSelector("[role=alert]");


    public void openPage() {
        open("/properties");
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
         return $(By.xpath("//h1[text()=' repository']")).getText();
    }

    public String isAlertDisplayed() {
        return $(ALERT_CREDENTIALS_MESSAGE).getText();
    }

    public void fillFieldSuiteName(String suiteName, String description, String preconditions) {
        $(CREATE_NEW_SUITE).click();
        $(By.id("title")).sendKeys(suiteName);
        $(By.xpath("//label[text()='Description']/parent::div/following::p")).sendKeys(description);
        $(By.xpath("//label[text()='Preconditions']/parent::div/following::p")).sendKeys(preconditions);
        $(By.xpath("//span[text()='Create']")).click();
    }

    public String getSuiteName() {
      return  $(By.xpath("//a[text()='Web']")).getText();
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

    public boolean projectToDelete() {
        $(By.xpath("//table//tr[3]//td[8]")).shouldBe(Condition.visible);
        return false;
    }

    public void clickOnSettings() {
        $(By.xpath("//table//tr[3]//td[8]")).click();
        $(By.xpath("//a[text()='Settings']")).click();
    }


    public String settingsIsDisplayed() {
        return $(By.xpath("//h1[text()='Project settings']")).getText();


    }
}
