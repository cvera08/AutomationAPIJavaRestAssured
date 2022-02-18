package Tests;

import Resources.Utils.FailsManagement;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

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
        Response request = given()
                .pathParam("pageNumber", 2)  //Defined parameterization
                .when()
                .get("https://reqres.in/api/users?page={pageNumber}"); //Use of parameter
        try{
            request
                    .then()
                    .statusCode(200)
                    .body("support.url", equalTo("https://reqres.in/#support-heading"));
        }catch (AssertionError assertionError){
            String failure = "Actual Response: " + request.getBody().asString() + "\n\n" + assertionError.toString();
            FailsManagement.testCaseFailLogs(failure, testName);
        }
    }
}
