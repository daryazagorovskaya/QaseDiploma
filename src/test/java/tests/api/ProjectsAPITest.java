package tests.api;

import adapters.ProjectApi;
import adapters.ProjectFactory;
import com.codeborne.selenide.Selenide;
import com.github.javafaker.Faker;
import com.google.gson.Gson;
import dto.Project;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import tests.BaseAPITest;

import java.util.Map;

import static com.codeborne.selenide.Selenide.$;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.emptyString;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ProjectsAPITest extends BaseAPITest {
    Faker faker = new Faker();
    String randomTitle = faker.book().title();
    String randomCode = faker.code().asin();

    @Test
    public void getAllProjects() {
        given()
                .header("accept", "application/json")
                .header("Token", TOKEN)
                .when()
                .contentType(ContentType.JSON)
                .get(URL + "/v1/project?limit=3&offset=0")
                .then()
                .log().all()
                .statusCode(200);
    }

    @Test
    public void getAllProjects2() {
        String response = given()
                .header("accept", "application/json")
                .header("Token", TOKEN)
                .when()
                .contentType(ContentType.JSON)
                .get(URL + "/v1/project?limit=3&offset=0")
                .then()
                .extract()
                .body().asString();
        System.out.println(response);
        Gson gson = new Gson();
        Project projects = gson.fromJson(response, Project.class);

    }

    @Test
    public void getAllProjects3() {
        Project response = given()
                .header("accept", "application/json")
                .header("Token", TOKEN)
                .when()
                .contentType(ContentType.JSON)
                .get(URL + "/v1/project?limit=3&offset=0")
                .then()
                .extract()
                .body().as(Project.class);
        System.out.println(response);
    }

    @Test
    public void getAllProjectsUnauthorized() {
        given()
                .header("accept", "application/json")
                .header("Token", TOKEN + "fd")
                .when()
                .contentType(ContentType.JSON)
                .get(URL + "/v1/project?limit=10&offset=0")
                .then()
                .log().all()
                .statusCode(401);
    }

    @Test
    public void createNewProject() {
        given()
                .body("{\"title\":\"" + randomTitle + "\",\"code\":\"" + randomCode + "\"}")
                .header("accept", "application/json")
                .header("Token", TOKEN)
                .when()
                .contentType(ContentType.JSON)
                .post(URL + "/v1/project")
                .then()
                .log().all()
                .statusCode(200);
    }

    @Test
    public void createNewProject2() {
        Response response = given()
                .body("{\"title\":\"" + randomTitle + "\",\"code\":\"" + randomCode + "\"}")
                .header("accept", "application/json")
                .header("Token", TOKEN)
                .when()
                .contentType(ContentType.JSON)
                .post(URL + "/v1/project");
        response.then()
                .log().all()
                .statusCode(200);
        assertEquals(randomTitle, response.jsonPath().get("title"));
        // assertEquals(randomCode, response.jsonPath().get("code"));
    }

    @Test
    public void createNewProject3() {
        given()
                .body("{\"title\":\"" + randomTitle + "\",\"code\":\"" + randomCode + "\"}")
                .header("accept", "application/json")
                .header("Token", TOKEN)
                .contentType(ContentType.JSON)
                .when()
                .post(URL + "/v1/project")
                .then()
                .log().all()
                .statusCode(200)
                .body("title", not(emptyString()))
                .body("code", not(emptyString()))
                .body("status", equalTo(true));
        //should be vis
    }

    @Test
    public void getProjectByCode(String code) {
        given()
                .header("accept", "application/json")
                .header("Token", TOKEN)
                .when()
                .contentType(ContentType.JSON)
                .get(URL + "/v1/project/" + code.toUpperCase())
                .then()
                .log().all()
                .statusCode(200);
    }

    @Test
    public void deleteProjectByCode() {
        given()
                .header("accept", "application/json")
                .header("Token", TOKEN)
                .when()
                .contentType(ContentType.JSON)
                .delete(URL + "/v1/project/TEST1")
                .then()
                .log().all()
                .statusCode(200);
    }

    @Test
    public void projectApi() {
        Project project = ProjectFactory.getRandom();
        new ProjectApi().create(project);
        System.out.println(new ProjectApi().getProjectByCode(project.getCode()));
    }

    @Test
    public void projectApi2() {
        Project project = Project.builder()
                .code("qwerty")
                .title("amazing qase" +
                        "")
                .build();
        given()
                .body(project)
                .header("accept", "application/json")
                .header("Token", TOKEN)
                .when()
                .contentType(ContentType.JSON)
                .post(URL + "/v1/project")
                .then()
                .log().all();
    }
}
