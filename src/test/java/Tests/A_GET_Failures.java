package Tests;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class A_GET_Failures {
    @Test
    public void verify404() {
        given()
                .when()
                .get("https://reqres.in/api/users/23")
                .then()
                .assertThat()
                .statusCode(404);
    }

}
