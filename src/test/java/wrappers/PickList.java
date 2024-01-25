package wrappers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Selenide.$;

public class PickList {
    WebDriver driver;
    String label;

    public PickList(WebDriver driver, String label) {
        this.driver = driver;
        this.label = label;
    }
    public void select(String option) {
        $(By.xpath(String.format("//div[@class='Thgbhj euhZGB cfvQxI']/preceding-sibling::label[text()='%s']", label))).click();
        $(By.xpath(String.format("//*[@id='modals']/*)[last()]//*[text()='%s']", option))).click();
        ////div[@class='Thgbhj euhZGB cfvQxI']/preceding-sibling::label[text()='%s']
        ////*[@id='modals']/*)[last()]//*[text()='%s']
        // не подходит для полей Suite и Milestones
    }

}
