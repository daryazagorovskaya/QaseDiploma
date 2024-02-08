package adapters;

import dto.Project;
import io.restassured.http.ContentType;
import tests.BaseAPITest;

import static io.restassured.RestAssured.given;

public class ProjectApi extends BaseAPITest {
    public String create(Project project) {
        given()
                .body(project)
                .header("Token", TOKEN)
                .contentType(ContentType.JSON)
                .when()
                .post("https://api.qase.io/v1/project")
                .then()
                .log().all();
        return project.getCode().toUpperCase();
    }

    public Project getProjectByCode(String code) {
        ProjectAPIResponse response =
                given()
                        .header("Token", TOKEN)
                        .contentType(ContentType.JSON)
                        .when()
                        .get("https://api.qase.io/v1/project/" + code.toUpperCase())
                        .then()
                        .log().all()
                        .extract()
                        .body().as(ProjectAPIResponse.class);
        return response.getResult();
    }
}
