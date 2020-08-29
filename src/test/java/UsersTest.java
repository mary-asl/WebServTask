import io.restassured.RestAssured;
import io.restassured.response.Response;
import objects.User;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class UsersTest {

    @BeforeTest
    public void getBaseURI() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    @Test(description = "Validation: status code of the obtained response is 200 OK")
    public void checkStatusCode() {
        Response response = given().get("/users").andReturn();
        int actualStatusCode = response.getStatusCode();
        Assert.assertEquals(actualStatusCode, 200, "status code of the response isn't 200");
    }

    @Test(description = "the value of the content-type header is is application/json; charset=utf-8")
    public void checkContentType() {
        Response response = given().get("/users").andReturn();
        String actualContentType = response.getContentType();
        Assert.assertTrue(actualContentType.contains("application/json"), "unexpected value of the content-type header");
    }

    @Test(description = "the content of the response body is the array of 10 users")
    public void checkResponseBody() {
        Response response = given().get("/users").andReturn();
        User[] users = response.as(User[].class);
        Assert.assertEquals(users.length, 10);
    }

}