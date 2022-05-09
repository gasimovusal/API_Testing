package get_requests;

import base_url.HerOkuAppBaseURL;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class Get_06 extends HerOkuAppBaseURL {

    /*
    Given
    https://restful-booker.herokuapp.com/booking/5
    When
    User send a GET request to the URL
    Then
    HTTP Status Code should be 200
    And
    Response content type is "application/json"
    And
    Response body should be like;
    {
        "firstname": "Mary",
            "lastname": "Jackson",
            "totalprice": 111,
            "depositpaid": false,
            "bookingdates": { "checkin": "2017-05-23",
            "checkout":"2019-07-02" }
    }
     */

    @Test
    public void get01(){

        // 1. set the url
        spec.pathParams("first", "booking", "second", 5);

        // 2. set the expected data

        // 3. send the request and the the response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        // 4. do assertions
        // 1. way
        response.then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                body("firstname", equalTo("Sally"),
                        "lastname", equalTo("Jackson"),
                        "totalprice", equalTo(949),
                        "depositpaid", equalTo(false),
                        "bookingdates.checkin", equalTo("2016-09-04"), // we are going inner Json, Json inside of Json
                        "bookingdates.checkout", equalTo("2017-04-17"));

        // 2. way: we will use JsonPath Class
        /*
        JsonPath json = response.jsonPath(); // convert response object to json path object
        assertEquals("Sally", json.getString("fistname"));
        assertEquals("Jackson", json.getString("lastname"));
        assertEquals("949", json.getInt("totalprice"));
        assertEquals(false, json.getBoolean("depositpaid"));
        assertEquals("2016-09-04", json.getString("bookingdates.checkin"));
        assertEquals("2017-04-17", json.getString("bookingdates.checkout"));
        */

        // 3. way: use Soft Assertion
        // to use soft assertion, follow given 3 step:
        //  1. create soft assert object
        SoftAssert softAssert = new SoftAssert();

        // 2. by using soft assert object, do assertion
        JsonPath json = response.jsonPath();
        softAssert.assertEquals(json.getString("fistname"), "Sally", "Firstname did not match");
        softAssert.assertTrue(json.getString("lastname").equals("Jackson"), "Lastname did not match");
        softAssert.assertEquals(json.getInt("totalprice"), 949, "Total price did not match");
        softAssert.assertFalse(json.getBoolean("depositpaid"), "Deposit paid is not false");
        softAssert.assertEquals(json.getString("bookingdates.checkin"), "2016-09-04", "Check in data did not match");
        softAssert.assertEquals(json.getString("bookingdates.checkout"),"2017-04-17", "Checkout data did not match");

        // 3. use assertAll() method, otherwise you will pass every time, but it will not be meaningful
        softAssert.assertAll();
    }
}
