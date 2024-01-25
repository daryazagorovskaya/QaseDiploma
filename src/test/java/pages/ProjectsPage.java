package pages;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class ProjectsPage {
    String label;
    private final By PROJECT_NAME = By.id("project-name");
    private final By PROJECT_CODE = By.id("project-code");
    private final By DESCRIPTION = By.id("description-area");
    private final By CREATE_NEW_BTN = By.id("createButton");
    final private By CREATE_BTN = By.xpath("//span[text()='Create project']");
    final private By CREATE_NEW_CASE = By.id("create-case-button");
    final private By CREATE_NEW_SUITE = By.id("create-suite-button");


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
        $(By.xpath("//div//div[text()='Looks like you don’t have any suites and cases yet.']")).shouldBe(Condition.visible);
    }

    public void createNewProject(String name, String code, String description) {
        $(CREATE_NEW_BTN).click();
        $(PROJECT_NAME).sendKeys(name);
        $(PROJECT_CODE).clear();
        $(PROJECT_CODE).sendKeys(code);
        $(DESCRIPTION).sendKeys(description);
        $(CREATE_BTN).click();
    }

    public String getTitle() {
        return $(By.xpath("//h1")).getText();
    }

    public String isAlertDisplayed() {
        return $(By.cssSelector("[role=alert]")).getText();
    }

    public void fillFieldSuiteName(String suiteName, String description, String preconditions) {
        $(CREATE_NEW_SUITE).click();
    }

    public String getSuiteName() {
      return  $(By.xpath("//a[text()='Web']")).getText();
    }

    public void waitTillOpenedAutPage() {
        $(By.xpath("//div//h1[text()='Log in to your account']")).shouldBe(Condition.visible);
    }

    public String isAutPageDisplayed() {
        return $(By.xpath("//div//h1[text()='Log in to your account']")).getText();
    }

    public void logOut() {
        $(By.xpath("//span//img")).click();
        $(By.xpath("//div//span[text()='Sign out']")).click();
    }
}
