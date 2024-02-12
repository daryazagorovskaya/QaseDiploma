package tests.api;

import adapters.ProjectApi;
import adapters.ProjectFactory;
import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.$;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.not;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ProjectsAPITest extends BaseAPITest {
    Faker faker = new Faker();
    String randomTitle = faker.book().title();
    String randomCode = faker.code().asin();

    @Test(description = "Checking retrieve all projects available for your account")
    public void getAllProjects() {
       Response response = given()
                .header("accept", "application/json")
                .header("Token", TOKEN)
                .when()
                .contentType(ContentType.JSON)
                .get(URL + "/v1/project?limit=30&offset=0")
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .response();
        var quantityOfProjects = response.jsonPath().getString("result.total");
        System.out.println(quantityOfProjects);
    }

    @Test (description = "Checking retrieve all projects available for your account if authorization data invalid")
    public void getAllProjectsUnauthorized() {
      Response response = given()
                .header("accept", "application/json")
                .header("Token", TOKEN + "fd")
                .when()
                .contentType(ContentType.JSON)
                .get(URL + "/v1/project?limit=10&offset=0")
                .then()
                .log().all()
                .statusCode(401)
                .extract()
                .response();
        var errorMessage = response.jsonPath().getString("error");
        System.out.println(errorMessage);
    }

    @Test(description = "Checking to create a new project")
    public void createNewProject() {
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
        assertEquals(randomCode, response.jsonPath().getString("result.code"));
    }

    @Test(description = "Checking to retrieve a specific project")
    public void getProjectByCode() {
      Response response = given()
                .header("accept", "application/json")
                .header("Token", TOKEN)
                .when()
                .contentType(ContentType.JSON)
                .get(URL + "/v1/project?limit=3&offset=0")
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .response();
        var extractedFieldValue = response.jsonPath().getString("result.entities[0].code");
      given()
                .header("accept", "application/json")
                .header("Token", TOKEN)
                .when()
                .contentType(ContentType.JSON)
                .get(URL + "/v1/project/" + extractedFieldValue)
                .then()
                .log().all()
                .statusCode(200);
    }


    @Test(description = "Checking for deletion a specific project")
    public void deleteProjectByCode() {
        Response response = given()
                .body("{\"title\":\"" + randomTitle + "\",\"code\":\"" + randomCode + "\"}")
                .header("accept", "application/json")
                .header("Token", TOKEN)
                .when()
                .contentType(ContentType.JSON)
                .post(URL + "/v1/project")
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .response();
        var extractedFieldValue = response.jsonPath().getString("result.code");
        given()
                .header("accept", "application/json")
                .header("Token", TOKEN)
                .when()
                .contentType(ContentType.JSON)
                .delete(URL + "/v1/project/" + randomCode)
                .then()
                .log().all()
                .statusCode(200);
        assertEquals(randomCode, extractedFieldValue);
    }
}
