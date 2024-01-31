package pages;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class TestCasePage {
    final private By CREATE_NEW_CASE = By.id("create-case-button");


    public void openTestCase() {
        $(CREATE_NEW_CASE).click();
    }

    public void waitTillOpen() {
        $(By.xpath("//h1[text()='Create test case']")).shouldBe(Condition.visible);
    }

    public String isTestCasePageDisplayed() {
        return  $(By.xpath("//h1[text()='Create test case']")).shouldBe(Condition.visible).getText();
    }

    public void pickProjectInProjectPage(String nameOfProject) {
        $(By.xpath(String.format("//div//a[text()='%s']", nameOfProject))).click();
    }

    public void waitTillOpenedCase() {
        $(CREATE_NEW_CASE).shouldBe(Condition.visible);
    }
}
