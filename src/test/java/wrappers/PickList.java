package wrappers;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

@Log4j2
public class PickList {
    private final String PICKLIST = "//label[text()='%s']//following::div";
    private final String PICKLIST_OPTION = "//div[text()='%s']";
    private final String MILESTONE_PICKLIST = "//div[@id='milestoneGroup']//following::div";
    private final String MILESTONE_PICKLIST_OPTION = "//div[@id='modals']/following-sibling::div//*[text()='%s']";
    private final String SUITE_PICKLIST = "//label[text()='Suite']/following-sibling::div";
    private final String SUITE_PICKLIST_OPTION = "//label[text()='Suite']/following-sibling::div//*[text()='%s']";
    private final String STEPS_PICKLIST = "//div[text()='Test Case Steps']/div";
    private final String STEPS_PICKLIST_OPTION = "//div[text()='Test Case Steps']/div//*[text()='%s']";
    private final String TEST_CASE_PICKLIST_OPTION = "//div[text()='%s']/../../following-sibling::td//div//input";
    private  final String ADD_STEP_BUTTON = "gherkin-add-step-btn";


    public void select(String label, String option) {
        log.info("Select '{}' option inside '{}' dropdown", option, label);
        if (option != null) {
            $(By.xpath(String.format(PICKLIST, label))).click();
            $(By.xpath(String.format(PICKLIST_OPTION, option))).click();
        }
    }

    public void setSuite(String option) {
        log.info("Select '{}' option inside suite dropdown", option);
        if (option != null) {
            $(By.xpath(String.format(SUITE_PICKLIST))).click();
            $(By.xpath(String.format(SUITE_PICKLIST_OPTION, option))).click();
        }
    }

    public void setMilestone(String option) {
        log.info("Select '{}' option inside milestone dropdown", option);
        if (option != null) {
            $(By.xpath(String.format(MILESTONE_PICKLIST))).click();
            $(By.xpath(String.format(MILESTONE_PICKLIST_OPTION, option))).click();
        }
    }

    public void setTypeTestCaseSteps(String option) {
        log.info("Select '{}' option inside test case steps dropdown", option);
        if (option != null) {
            $(By.xpath(String.format(STEPS_PICKLIST))).click();
            $(By.xpath(String.format(STEPS_PICKLIST_OPTION, option))).click();
        }
    }

    public void setGherkinSteps(String option, String text) {
        log.info("Select '{}' option inside test case steps dropdown", option);
        if (option != null) {
            $(By.id(ADD_STEP_BUTTON)).click();
            $(By.xpath(String.format(TEST_CASE_PICKLIST_OPTION, option))).sendKeys(text);
        }
    }
}
