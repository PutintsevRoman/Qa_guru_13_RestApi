package restapi;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RequestTest {


    @Test
    void listUsersTest() {
        String expectedText = "To keep ReqRes free, contributions towards server costs are appreciated!";
        String actualText = given()
                .log().all()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract()
                .path("text");

        assertEquals(expectedText, actualText);
    }

    @Test
    void singleUsersTest() {
        String expectedFirst_name = "Janet";
        String actualFirst_name = given()
                .log().uri()
                .when()
                .get("https://reqres.in/api/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract()
                .path("first_name");

        assertEquals(expectedFirst_name, actualFirst_name);
    }

    @Test
    void singleUsersNotFoundTest() {
        given()
                .log().uri()
                .when()
                .get("https://reqres.in/api/users/23")
                .then()
                .log().status()
                .log().body()
                .statusCode(404);
    }

    @Test
    void loginUnsuccessfulTest() {
        String expectedError = "Missing password";
        String actualError = given()
                .log().uri()
                .body("\"email\": \"peter@klaven\"")
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .extract()
                .path("error");

        assertEquals(expectedError, actualError);
    }

    @Test
    void deleteTest() {
        given()
                .when()
                .delete("https://reqres.in/api/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(204);

    }
}
