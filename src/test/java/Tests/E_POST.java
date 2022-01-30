package Tests;

import MainPackage.BaseApiTest;
import org.apache.commons.io.FileUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;

public class E_POST extends MainPackage.BaseApiTest {

    @Test
    public void createUser201() {
        basePath = "users";
        String payload = "{ \"name\": \"morpheus\", \"job\": \"leader\" }";

        given()
                .contentType("application/json")
                .body(payload)
                .post()
                .then()
                .statusCode(201)
                .and()
                .body("$", hasKey("id")) //has any "id" tag in the responseBody
                .body("$", hasKey("createdAt"));
    }

    @Test
    public void registrationWithJsonRequestBody() throws JSONException {
        basePath = "register";

        JSONObject requestParams = new JSONObject();
        requestParams.put("email", "eve.holt@reqres.in");
        requestParams.put("password", "Pass12");

        given()
                .contentType("application/json")
                .body(requestParams.toString())
                .post()
                .then()
                .statusCode(200)
                .body("$", hasKey("token"));

        System.out.println("Response <registrationWithJsonRequestBody>:" +
                given().contentType("application/json").body(requestParams.toString())
                        .post().getBody().asString());

    }

    @Test
    public void registrationFromJsonFile() throws IOException {
        basePath = "register";

        File reqBody = new File(BaseApiTest.requestBodyPath + "registerRequestBody.json");
        String requestBodyFromJson = FileUtils.readFileToString(reqBody, "us-ascii");

        File respBody = new File(BaseApiTest.responseBodyPath + "registerResponseBody.json");
        String responseBodyFromJson = FileUtils.readFileToString(respBody, "us-ascii");

        given()
                .contentType("application/json")
                .body(requestBodyFromJson)
                .post()
                .then()
                .statusCode(400)
                .body(equalTo(responseBodyFromJson));

        System.out.println("Response <registrationFromJsonFile>:" +
                given().contentType("application/json").body(requestBodyFromJson)
                        .post().getBody().asString());

    }


}
