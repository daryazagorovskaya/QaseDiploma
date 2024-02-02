package tests.api;

import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class TestCaseAPITest {
    Faker faker = new Faker();
    String descripNumber = faker.lorem().word() + faker.number().numberBetween(1, 100);
    public static final String URL = "https://api.qase.io";
    public static final String TOKEN = "98af48f36c4f7fa1049887fa9dc46aa030418493342b90bd217dea38a6eb83aa";

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
                .body("{\"title\":\"Проверка кроссбраузерности\"}")
                .header("accept", "application/json")
                .header("Token", TOKEN)
                .when()
                .contentType(ContentType.JSON)
                .post(URL + "/v1/case/SHARELANE")
                .then()
                .log().all()
                .statusCode(200);
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
                .body(Map.of("title","Проверка кроссбраузерности"))
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
                .delete(URL + "/v1/case/SHARELANE/33")
                .then()
                .log().all()
                .statusCode(200);
    }

    @Test
    public void updateTestCase() {
        given()
                .body(Map.of("description", descripNumber))
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
                .body("result.description", equalTo(descripNumber));
    }

    @Test
    public void createTestCaseInBulk() {
        given()
                .body("{\"cases\":[{\"title\":\"pampam\"}]}")
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
