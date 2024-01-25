package wrappers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Selenide.$;

public class Input {

    WebDriver driver;
    String label;

    public Input(WebDriver driver, String label) {
        this.driver = driver;
        this.label = label;
    }

    public void write(String text) {
        if(text != null)
            // log.info("Writing {} into {}, text", label);
            $(By.xpath(String.format("//*[text()='%s']/ancestor::lightning-input//input", label)))
                    .sendKeys(text);
    }

}
