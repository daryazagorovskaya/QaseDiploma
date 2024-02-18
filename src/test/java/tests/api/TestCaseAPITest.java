package tests.api;

import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import tests.BaseTest;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.testng.AssertJUnit.assertTrue;

public class TestCaseAPITest extends BaseTest {
    Faker faker = new Faker();
    String descriptionNumber = faker.lorem().word() + faker.number().numberBetween(1, 100);
    String randomTitle = faker.lorem().sentence();


    @Test(description = "Checking to retrieve all test cases stored in selected project")
    public void getAllTestCases() {
        given()
                .header("accept", "application/json")
                .header("Token", TOKEN)
                .when()
                .contentType(ContentType.JSON)
                .get(URL + "/v1/project/SHARELANE")
                .then()
                .log().all()
                .statusCode(200);
    }

    @Test(description = "Checking to create a new test case in selected project")
    public void createNewTestCase() {
        Response response = given()
                .body("{\"title\":\"" + randomTitle + "\"}")
                .header("accept", "application/json")
                .header("Token", TOKEN)
                .when()
                .contentType(ContentType.JSON)
                .post(URL + "/v1/case/SHARELANE")
                .then()
                .log().all()
                .statusCode(200)
                .body("title", not(emptyString()))
                .body("status", equalTo(true))
                .extract()
                .response();
        var extractedFieldValue = response.jsonPath().getString("result.id");
        assertTrue("The extracted field value should not be null or empty", extractedFieldValue != null && !extractedFieldValue.isEmpty());
    }

    @Test(description = "Checking to retrieve a specific test case")
    public void getSpecificTestCase() {
        Response response = given()
                .body("{\"title\":\"" + randomTitle + "\"}")
                .header("accept", "application/json")
                .header("Token", TOKEN)
                .when()
                .contentType(ContentType.JSON)
                .post(URL + "/v1/case/SHARELANE")
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .response();
        var extractedFieldValue = response.jsonPath().getString("result.id");
        given()
                .header("accept", "application/json")
                .header("Token", TOKEN)
                .when()
                .contentType(ContentType.JSON)
                .delete(URL + "/v1/case/SHARELANE/" + extractedFieldValue )
                .then()
                .log().all()
                .statusCode(200);
    }

    @Test(description = "Checking deletion a test case from repository")
    public void deleteTestCase() {
        Response response = given()
                .body("{\"title\":\"" + randomTitle + "\"}")
                .header("accept", "application/json")
                .header("Token", TOKEN)
                .when()
                .contentType(ContentType.JSON)
                .post(URL + "/v1/case/SHARELANE")
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .response();
         var extractedFieldValue = response.jsonPath().getString("result.id");
        given()
                .header("accept", "application/json")
                .header("Token", TOKEN)
                .when()
                .contentType(ContentType.JSON)
                .delete(URL + "/v1/case/SHARELANE/" + extractedFieldValue )
                .then()
                .log().all()
                .statusCode(200);
    }

    @Test(description = "Checking updates a test case")
    public void updateTestCase() {
        given()
                .body(Map.of("description", descriptionNumber))
                .header("accept", "application/json")
                .header("Token", TOKEN)
                .when()
                .contentType(ContentType.JSON)
                .patch(URL + "/v1/case/SHARELANE/12")
                .then()
                .log().all()
                .statusCode(200);
        given()
                .header("accept", "application/json")
                .header("Token", TOKEN)
                .when()
                .contentType(ContentType.JSON)
                .get(URL + "/v1/case/SHARELANE/12")
                .then()
                .log().all()
                .statusCode(200)
                .body("result.description", equalTo(descriptionNumber));
    }
}
