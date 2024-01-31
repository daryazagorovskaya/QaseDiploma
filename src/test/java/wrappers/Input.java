package wrappers;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Selenide.$;

@Log4j2
public class Input {
    String label;

    public Input(String label) {
        this.label = label;
    }

    public void write(String text) {
        if(text != null)
            log.info("Writing {} into {}, text", label);
            $(By.xpath(String.format("//label[text()='%s']/following-sibling::div", label)))
                    .sendKeys(text);
    }

}
