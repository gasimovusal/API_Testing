package get_requests;

import base_url.HerOkuAppBaseURL;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class Get_05 extends HerOkuAppBaseURL {

    /*
    Given
            https://restful-booker.herokuapp.com/booking
        When
            User send a request to the URL
        Then
            Status code is 200
	And
	   Among the data there should be someone whose firstname is "Eric" and last name is "Smith"
     */

    @Test

    public void get01(){

        // 1. set the URL
        spec.pathParams("first", "booking").
                queryParams("firstname", "Eric", "lastname", "Jackson"); // we use query params oto filter the content

        // 2. set the expected data

        // 3. send the request and get the response
        Response response = given().spec(spec).when().get("/{first}");
        response.prettyPrint();

        // 4. do assertion
        response.then().
                assertThat().
                statusCode(200);

        assertTrue(response.asString().contains("bookingid"));
    }
}
