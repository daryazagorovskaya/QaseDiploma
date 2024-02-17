package tests.api;

import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import tests.BaseTest;

import static io.restassured.RestAssured.given;
import static tests.BaseTest.TOKEN;

public class TestPlanAPITest extends BaseTest {
    Faker faker = new Faker();
    int planNumber = faker.number().numberBetween(1, 3);

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
