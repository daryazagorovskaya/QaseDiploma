package tests.api;

import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import tests.BaseAPITest;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class TestCaseAPITest extends BaseAPITest {
    Faker faker = new Faker();
    String descriptionNumber = faker.lorem().word() + faker.number().numberBetween(1, 100);
    String randomTitle = faker.lorem().sentence();

    @Test
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

    @Test
    public void createNewTestCase() {
        given()
                .body("{\"title\":\"" + randomTitle + "\"}")
                .header("accept", "application/json")
                .header("Token", TOKEN)
                .when()
                .contentType(ContentType.JSON)
                .post(URL + "/v1/case/SHARELANE")
                .then()
                .log().all()
                .statusCode(200)
                //.body("title", equalTo(randomTitle))
                .body("title", not(emptyString()));
    }

    @Test
    public void getSpecificTestCase() {
        given()
                .header("accept", "application/json")
                .header("Token", TOKEN)
                .when()
                .contentType(ContentType.JSON)
                .get(URL + "/v1/case/SHARELANE/4")
                .then()
                .log().all()
                .statusCode(200);
    }

    @Test
    public void deleteTestCase() {
        given()
                .body("{\"title\":\"" + randomTitle + "\"}")
                .header("accept", "application/json")
                .header("Token", TOKEN)
                .when()
                .contentType(ContentType.JSON)
                .post(URL + "/v1/case/SHARELANE")
                .then()
                .log().all()
                .statusCode(200);
        given()
                .header("accept", "application/json")
                .header("Token", TOKEN)
                .when()
                .contentType(ContentType.JSON)
                .delete(URL + "/v1/case/SHARELANE/87" )
                .then()
                .log().all()
                .statusCode(200);
        // тут разобраться с индексом
    }

    @Test
    public void updateTestCase() {
        given()
                .body(Map.of("description", descriptionNumber))
                .header("accept", "application/json")
                .header("Token", TOKEN)
                .when()
                .contentType(ContentType.JSON)
                .patch(URL + "/v1/case/SHARELANE/32")
                .then()
                .log().all()
                .statusCode(200);
        given()
                .header("accept", "application/json")
                .header("Token", TOKEN)
                .when()
                .contentType(ContentType.JSON)
                .get(URL + "/v1/case/SHARELANE/32")
                .then()
                .log().all()
                .statusCode(200)
                .body("result.description", equalTo(descriptionNumber));
    }

    @Test
    public void createTestCaseInBulk() {
        given()
                .body("{\"title\":\"" + randomTitle + "\"}")
                .header("accept", "application/json")
                .header("Token", TOKEN)
                .when()
                .contentType(ContentType.JSON)
                .post(URL + "/v1/case/SHARELANE/bulk")
                .then()
                .log().all()
                .statusCode(200);
    }
}
