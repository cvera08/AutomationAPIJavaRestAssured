package Tests;

import MainPackage.BaseApiTest;
import org.apache.commons.io.FileUtils;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;

public class G_PATCH extends MainPackage.BaseApiTest {

    @Test
    public void updateUserFromJsonFilePATCH() throws IOException {
        basePath = "users/2";

        File reqBody = new File(BaseApiTest.requestBodyPath + "updateUserRequestBody.json");
        String requestBodyFromJson = FileUtils.readFileToString(reqBody, "us-ascii");

        given()
                .contentType("application/json")
                .body(requestBodyFromJson)
                .patch()
                .then()
                .statusCode(200)
                .body("$", hasKey("updatedAt"))
                .body("$", hasKey("name"))
                .body("$", hasKey("job"))
                .body("name", equalTo("morpheus"));
    }


}
