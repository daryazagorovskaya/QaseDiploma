package wrappers;

import com.codeborne.selenide.Condition;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Selenide.$;

@Log4j2
public class Input {

    private static final String INPUT = "//label[text()='%s']/following-sibling::*/input";
    private static final String TC_ADD_ATTACHMENT_BUTTON_XPATH = "//button[text()='Add attachment']";
    private static final String TC_INPUT_FILE_XPATH = "//div[@data-react-modal-body-trap][2]/following-sibling::input[@type='file']";
    private static final String PARAMETER_BUTTON = "//button/span[text()='Add parameter']";
    private static final String PARAMETER = "//label[text()='%s']/following::input";
    private static final String TC_GHERKIN_INPUT_XPATH = "//div[text()='%s']/following::tr//div[text()='%s']/following::input[3]";


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

    public void writeSteps(String label, String inputNumber, String text) {
        log.info("Write '{}' into gherkin input field", text);
        if (text != null) {
            $(By.xpath(String.format(TC_GHERKIN_INPUT_XPATH, label, inputNumber))).sendKeys(text);
        }
    }

    //_________________________________________TEST CASE DETAILS PAGE__________________________________________________\\

    private static final String TC_DETAILS_TITLE_XPATH = "//div[text()='%s']";
    private static final String TC_DETAILS_LOADED_ATTACHMENT_XPATH = "//h3[text()='%s']/..//*[text()='%s']/ancestor::a";
    private static final String TC_DETAILS_GENERAL_XPATH = "//%s[text()='%s']/..//*[text()='%s']";

    public void validateTestCaseDetailsTitle(String text) {
        log.info("Test case title is'{}'", text);
        if (text != null) {
            $(By.xpath(String.format(TC_DETAILS_TITLE_XPATH, text))).shouldBe(Condition.visible);
        }
    }

    public void validateGeneralTabFields(String tag, String value, String text) {
        log.info("'{}' field contains '{}' text", value, text);
        if (text != null) {
            $(By.xpath(String.format(TC_DETAILS_GENERAL_XPATH, tag, value, text))).shouldBe(Condition.visible);
        }
    }

    public void validateUploadedAttachment(String value, String attachmentTitle) {
        log.info("'{}' field contains '{}' title", value, attachmentTitle);
        if (attachmentTitle != null) {
            $(By.xpath(String.format(TC_DETAILS_LOADED_ATTACHMENT_XPATH, value, attachmentTitle))).shouldBe(Condition.visible);
        }
    }


    public void validatePropertiesTabFields(String tag, String value, String text) {
        log.info("'{}' field contains '{}' text", value, text);
        if (text != null) {
            $(By.xpath(String.format(TC_DETAILS_GENERAL_XPATH, tag, value, text))).shouldBe(Condition.visible);
        }
    }

//    public void uploadFile(File file) {
//        log.info("Add attachment");
//        if (file != null) {
//            $(By.xpath(TC_ADD_ATTACHMENT_BUTTON_XPATH)).click();
//            $(By.xpath(TC_INPUT_FILE_XPATH)).uploadFile(file);
//        }
//    }

}
