package tests.api;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import tests.BaseAPITest;

import static io.restassured.RestAssured.given;

public class TestPlanAPITest extends BaseAPITest {
    public static final String URL = "https://api.qase.io";

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
                .get(URL + "/v1/plan/SHARELANE/1")
                .then()
                .log().all()
                .statusCode(200);
    }
}
