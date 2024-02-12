package tests.api;

import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class TestPlanAPITest extends BaseAPITest {
    public static final String URL = "https://api.qase.io";

    Faker faker = new Faker();
    int planNumber = faker.number().numberBetween(1, 100);

    @Test
    public void getAllPlans() {
        given()
                .header("accept", "application/json")
                .header("Token", TOKEN)
                .when()
                .contentType(ContentType.JSON)
                .get(URL + "/v1/plan/SHARELANE?limit=10&offset=0")
                .then()
                .log().all()
                .statusCode(200);
    }

    @Test
    public void getSpecificPlan() {
        given()
                .header("accept", "application/json")
                .header("Token", TOKEN)
                .when()
                .contentType(ContentType.JSON)
                .get(URL + "/v1/plan/SHARELANE/" + planNumber)
                .then()
                .log().all()
                .statusCode(200);
    }
}
