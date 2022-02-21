package Tests;

import org.apache.http.HttpStatus;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class J_GET_API_KEY {

    /**
     * Full endpoint documentation: https://documenter.getpostman.com/view/631643/JsLs/
     * Postman examples: https://blog.postman.com/how-to-use-api-keys/
     */
    @Test
    public void invalidAPIKey() {
        given()
                .header("X-Api-Key", "<yourPersonalValidApiKey>")
                .get("https://api.getpostman.com/collections")
                //get("https://api.getpostman.com/collections?apikey=<yourPersonalValidApiKey>") //two previous lines is the same than doing this
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_UNAUTHORIZED)
                .body("error.name", equalTo("AuthenticationError"));
    }


    /**
     * Full endpoint documentation: https://documenter.getpostman.com/view/631643/JsLs/
     */
    @Ignore //4a74dc70 Ignoring since it will fail because of: Incomplete api key since this repository is public
    @Test
    public void validAPIKey() {
        given()
                .header("X-Api-Key", "PMAK-6212b64fa33ade003c5b2e97-474e4225d1088a5f118c43ce12") //use here your "postman api key" -
                .get("https://api.getpostman.com/collections")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .body("collections[0].isPublic", is(false));
    }
}
