package Tests;

import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class I_GET_BasicAuthentication {

    /**
     * Full Postman Example: https://www.toolsqa.com/postman/basic-authentication-in-postman/
     * RestAssured Support: https://www.baeldung.com/rest-assured-authentication
     */
    @Test
    public void basicAuthWithUsernameAndPassword() {
        given()
                .auth()
                .basic("postman", "password")
                .get("https://postman-echo.com/basic-auth")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK) //Status code: 200
                .body("authenticated", is(true)); //Response body: {"authenticated": true }
    }


    /**
     * You can use this url to get the username:password in Base64 format https://www.base64encode.org/
     * postman:password is cG9zdG1hbjpwYXNzd29yZA==
     */
    @Test
    public void basicAuthByEncoding() {
        given()
                //.header("Authorization", String.format("Basic %s", authBasic))
                .header("Authorization", "Basic cG9zdG1hbjpwYXNzd29yZA==") //It is possible to make the encoding by code like this Base64.encode(String.format("%s:%s", username, password));
                .get("https://postman-echo.com/basic-auth")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body("authenticated", is(true)); //authenticated : true
    }

}
