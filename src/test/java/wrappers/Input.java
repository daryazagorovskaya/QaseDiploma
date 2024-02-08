package wrappers;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

@Log4j2
public class Input {

    private static final String INPUT = "//label[text()='%s']/following-sibling::*/input";
    private static final String PARAMETER_BUTTON = "//button/span[text()='Add parameter']";
    private static final String PARAMETER = "//label[text()='%s']/following::input";


    public void write(String label, String text) {
        log.info("Write '{}' into '{}' input field", text, label);
        if (text != null) {
            $(By.xpath(String.format(INPUT, label))).sendKeys(text);
        }
    }

    public void writeParameter(String label, String text) {
        log.info("Write '{}' into '{}' input field", text, label);
        if (text != null) {
            $(By.xpath(PARAMETER_BUTTON)).click();
            $(By.xpath(String.format(PARAMETER, label))).sendKeys(text);
        }
    }

    public void writeParameterValues(String label, String text) {
        log.info("Write '{}' into '{}' input field", text, label);
        if (text != null) {
            $(By.xpath(String.format(PARAMETER, label))).sendKeys(text);
        }
    }
    }
