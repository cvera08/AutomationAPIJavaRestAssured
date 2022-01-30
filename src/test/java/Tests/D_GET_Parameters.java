package Tests;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class D_GET_Parameters extends MainPackage.BaseApiTest {

    @Test
    public void listOfUsersWithParametersVersion1() {
        given()
                .get("https://reqres.in/api/users?page={id}",2) //parameterization - Full url: https://reqres.in/api/users?page=2
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .body("page", equalTo(2));
    }

    @Test
    public void listOfUsersWithParametersVersion2() {
        given()
                .pathParam("pageNumber", 2)  //Defined parameterization
                .when()
                .get("https://reqres.in/api/users?page={pageNumber}") //Use of parameter
                .then()
                .statusCode(200)
                .body("ad.url", equalTo("http://statuscode.org/"));
    }
}
