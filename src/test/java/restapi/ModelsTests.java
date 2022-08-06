package restapi;

import models.lombok.DataLoginRequest;
import models.lombok.DataLoginResponse;
import models.lombok.RegistrationDataRequest;
import models.lombok.RegistrationDataResponse;
import models.pojo.UserDataPojo;
import models.pojo.UserDataResponsePojo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static specs.RegistrationSpecs.requestSpecificationRequest;
import static specs.RegistrationSpecs.requestSpecificationResponse;

public class ModelsTests {

    @Test
    @DisplayName("Clean pojo model test")
    void updateUserTest (){
        UserDataPojo userDataPojo = new UserDataPojo();
        userDataPojo.setFirstName("morpheus");
        userDataPojo.setJob("zion resident");


        UserDataResponsePojo userDataResponsePojo = given()
                .contentType(JSON)
                .body(userDataPojo)
                .when()
                .put("https://reqres.in/api/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract()
                .as(UserDataResponsePojo.class);

        assertEquals(userDataResponsePojo.getFirstName(),userDataPojo.getFirstName());
        assertEquals(userDataResponsePojo.getJob(),userDataPojo.getJob());

    }
    @Test
    @DisplayName("Clean lombok model test")
    void LoginSuccessUserTest (){
        DataLoginRequest dataLoginRequest = new DataLoginRequest();
        dataLoginRequest.setEmail("eve.holt@reqres.in");
        dataLoginRequest.setPassword("cityslicka");


        DataLoginResponse dataLoginResponse = given()
                .contentType(JSON)
                .body(dataLoginRequest)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract()
                .as(DataLoginResponse.class);

        assertEquals(dataLoginResponse.getToken(),"QpwL5tke4Pnpja7X4");

    }

    @Test
    @DisplayName("Spec lombok model test")
    void RegistrationSuccessUserTest (){
        RegistrationDataRequest registrationDataRequest = new RegistrationDataRequest();
        registrationDataRequest.setEmail("eve.holt@reqres.in");
        registrationDataRequest.setPassword("pistol");

        RegistrationDataResponse registrationDataResponse = given()
                .spec(requestSpecificationRequest)
                .body(registrationDataRequest)
                .when()
                .post("https://reqres.in/api/register")
                .then()
                .spec(requestSpecificationResponse)
                .extract().as(RegistrationDataResponse.class);

        assertEquals(registrationDataResponse.getId(),"4");
        assertEquals(registrationDataResponse.getToken(),"QpwL5tke4Pnpja7X4");
    }
}
