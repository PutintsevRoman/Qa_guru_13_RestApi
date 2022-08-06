package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.with;
import static io.restassured.http.ContentType.JSON;
import static org.apache.commons.lang3.ObjectUtils.isNotEmpty;
import static org.hamcrest.Matchers.notNullValue;

public class RegistrationSpecs {

    public static RequestSpecification requestSpecificationRequest = with()
            .log().uri()
            .log().body()
            .contentType(JSON);

    public static ResponseSpecification requestSpecificationResponse = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .log(LogDetail.STATUS)
            .log(LogDetail.BODY)
            .expectBody("token",notNullValue())
            .build();

}
