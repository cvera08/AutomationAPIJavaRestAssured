package MainPackage;

import org.testng.annotations.BeforeSuite;

import static io.restassured.RestAssured.baseURI;

public class BaseApiTest {

    public static String responseBodyPath = System.getProperty("user.dir") + "/src/test/java/Resources/JsonResponses/";
    public static String requestBodyPath = System.getProperty("user.dir") + "/src/test/java/Resources/JsonRequests/";

    @BeforeSuite
    public void setup() {
        baseURI = "https://reqres.in/api/";
    }
}
