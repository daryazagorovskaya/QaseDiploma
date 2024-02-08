package dto;
import lombok.*;

@Data
@Builder
public class TestCase {
    String title;
    Integer id;
    String status;
    String description;
    String suite;
    String severity;
    String priority;
    String type;
    String layer;
    String isFlaky;
    String milestone;
    String behavior;
    String automationStatus;
    //Conditions
    String preConditions;
    String postConditions;
    //Tags
    String tag;
    //Attachments
    String attachment;
    String addParam;
    String parameterTitle;
    String parameterValues;
    String testCaseSteps;
    String addStep;

}
