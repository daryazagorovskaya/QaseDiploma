package tests.api;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class TestPlanAPITest {
    public static final String URL = "https://api.qase.io";

    @Test
    public void getAllPlans() {
        given()
                .header("accept", "application/json")
                .header("Token", "98af48f36c4f7fa1049887fa9dc46aa030418493342b90bd217dea38a6eb83aa")
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
                .header("Token", "98af48f36c4f7fa1049887fa9dc46aa030418493342b90bd217dea38a6eb83aa")
                .when()
                .contentType(ContentType.JSON)
                .get(URL + "/v1/plan/SHARELANE/1")
                .then()
                .log().all()
                .statusCode(200);
    }
}
