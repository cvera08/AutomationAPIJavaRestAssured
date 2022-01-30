package Tests;

import org.testng.annotations.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;


public class B_GET_ResponseTimes extends MainPackage.BaseApiTest {

    @Test
    public void singleUserIsReturnedResponsesTime() throws IOException {
        basePath = "users/2";
        given().get().then().time(lessThan(5L), TimeUnit.SECONDS);
    }

    @Test
    public void singleUserWithDelay() throws IOException {
        basePath = "users?delay=3";
        given().get().then().time(lessThan(3L), TimeUnit.SECONDS);
    }
}
