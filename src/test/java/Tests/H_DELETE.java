package Tests;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class H_DELETE extends MainPackage.BaseApiTest {

    @Test
    public void deleteUser200() {
        given()
                .delete("https://jsonplaceholder.typicode.com/todos/1")
                .then()
                .statusCode(200)
                .and()
                .body("isEmpty()", Matchers.is(true)); //Validate empty response {}
    }

    @Test
    public void deleteUser204() {
//        RestAssured.reset(); //standard baseURI ("" / localhost), basePath (empty), etc

        baseURI = "https://reqres.in/api/"; //new baseUrl (insted of "https://jsonplaceho.....")
        basePath = "users/2";

        given()
                .delete()
                .then()
                .statusCode(204)
                .and()
                .body(equalTo("")); //empty response ""
    }


}
