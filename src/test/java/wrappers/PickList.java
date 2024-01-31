package wrappers;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Selenide.$;

@Log4j2
public class PickList {
    String label;

    public PickList(String label) {
        this.label = label;
    }
    public void select(String option) {
        log.info("Selecting '{}' inside picklist '{}'");
        $(By.xpath(String.format("//div[@class='Thgbhj euhZGB cfvQxI']/preceding-sibling::label[text()='%s']", label))).click();
        $(By.xpath(String.format("//*[@id='modals']/*)[last()]//*[text()='%s']", option))).click();
        ////div[@class='Thgbhj euhZGB cfvQxI']/preceding-sibling::label[text()='%s']
        ////*[@id='modals']/*)[last()]//*[text()='%s']
        // не подходит для полей Suite и Milestones
        // $(By.xpath("//label[text()='Milestone']//../following-sibling::*//input")).click();
    }

}
