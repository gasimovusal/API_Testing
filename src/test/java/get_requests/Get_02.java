package get_requests;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.testng.AssertJUnit.*;

public class Get_02 {

    /*
        Given
            https://restful-booker.herokuapp.com/booking/1001
        When
            User send a GET Request to the url
        Then
            HTTP Status code should be 404
        And
            Status Line should be HTTP/1.1 404 Not Found
        And
            Response body contains "Not Found"
        And
            Response body does not contain "TechProEd"
        And
            Server is "Cowboy"
     */

    @Test
    public void get01(){

        String url = "https://restful-booker.herokuapp.com/booking/1001";

        Response response = given().when().get(url);

        response.prettyPrint(); // print just response body

        // then is for assertions
        response.then().assertThat().statusCode(404).statusLine("HTTP/1.1 404 Not Found");

        // how to assert if response body has a specific text
        // assertTrue(x) method passes if the x is true
        assertTrue(response.asString().contains("Not Found")); // is inside parenthesis is true, it will pass, if parenthesis is false, it will fail

        // assertFalse(x) method passes if the x is false
        assertFalse(response.asString().contains("TechProEd"));

        //asserEquals(x, y); method passes if x equals to y, x is expected value, y is actual value
        assertEquals("Cowboy", response.getHeader("Server"));

    }
}
