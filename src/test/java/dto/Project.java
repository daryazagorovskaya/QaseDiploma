package dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Project {
    String projectName;
    String projectCode;
    String description;
    String projectAccessType;
    public Integer total;
    public Integer filtered;
    public Integer count;
}
