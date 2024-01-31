package api;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Projects {
    public static final String URL = "https://api.qase.io";

    @Test
    public void getAllProjects() {
        given()
                .header("accept", "application/json")
                .header("Token", "98af48f36c4f7fa1049887fa9dc46aa030418493342b90bd217dea38a6eb83aa")
                .when()
                .contentType(ContentType.JSON)
                .get(URL + "/v1/project")
                .then()
                .log().all()
                .statusCode(200);
    }

    @Test
    public void getAllProjectsUnauthorized() {
        given()
                .header("accept", "application/json")
                .header("Token", "98af48f36c4f7fa1049887fa9dc46aa030418493342b90bd217dea38a6eb83a")
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
                .body("{\"title\":\"Test37\",\"code\":\"ft4tk\"}")
                .header("accept", "application/json")
                .header("Token", "98af48f36c4f7fa1049887fa9dc46aa030418493342b90bd217dea38a6eb83aa")
                .when()
                .contentType(ContentType.JSON)
                .post(URL + "/v1/project")
                .then()
                .log().all()
                .statusCode(200);
    }

    @Test
    public void getProjectByCode() {
        given()
                .header("accept", "application/json")
                .header("Token", "98af48f36c4f7fa1049887fa9dc46aa030418493342b90bd217dea38a6eb83aa")
                .when()
                .contentType(ContentType.JSON)
                .get(URL + "/v1/project/TEST")
                .then()
                .log().all()
                .statusCode(200);
    }

    @Test
    public void deleteProjectByCode() {
        given()
                .header("accept", "application/json")
                .header("Token", "98af48f36c4f7fa1049887fa9dc46aa030418493342b90bd217dea38a6eb83aa")
                .when()
                .contentType(ContentType.JSON)
                .delete(URL + "/v1/project/TEST1")
                .then()
                .log().all()
                .statusCode(200);
    }
}
