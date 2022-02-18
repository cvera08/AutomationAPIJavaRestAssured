package Tests;

import MainPackage.BaseApiTest;
import io.restassured.path.json.JsonPath;
import org.apache.commons.io.FileUtils;
import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class C_GET_ValidateResponseBody extends MainPackage.BaseApiTest {

    File respBody = new File(BaseApiTest.responseBodyPath + "users2.json");

    @Test
    public void singleUserIsReturnedSingleComparison() {
        basePath = "users/2"; //Full url: https://reqres.in/api/users/2
        given()
                .when()
                .get()
                .then()
                .assertThat()
                .statusCode(200)
                .body("data.id", is(2))
                .body("ad.company", equalTo("StatusCode Weekly"))
                .body("ad.url", equalTo("http://statuscode.org/"));
    }

    @Test
    public void singleUserIsReturnedNodeComparison() {
        JsonPath singleUserJson = new JsonPath(respBody);
        basePath = "users/2";

        given()
                .when()
                .get()
                .then()
                .body("data.id", equalTo(singleUserJson.getInt("data.id")));
    }

    @Test
    public void singleUserIsReturnedFullComparison() throws IOException, JSONException {
        basePath = "users/2";

        String expectedJson = FileUtils.readFileToString(respBody);
        String actualJson = given().get().then().extract().asString();
        try {
            JSONAssert.assertEquals(expectedJson, actualJson, JSONCompareMode.STRICT);
        }catch (AssertionError assertionError){ //Catching failed tc to print current json output for an easy fix
            System.out.println(assertionError);
            System.out.println("Actual Json: " + actualJson);
            org.testng.Assert.fail("Failed TC: " + testName + ". Please see previous logs to see the on detail reason"); //Still failing test (on purpose)
        }

    }

    @Test
    public void singleUserIsReturnedMatchesJsonSchema() {
        basePath = "users/2";
        given().get().then().assertThat().body(matchesJsonSchema(respBody));
    }

    @Test
    public void singleUserIsReturnedFullContentMatching() throws IOException {
        basePath = "users/2";
        given().get().then().assertThat().body(equalTo(FileUtils.readFileToString(respBody, "us-ascii")));
    }

}

