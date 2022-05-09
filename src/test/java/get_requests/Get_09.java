package get_requests;

import base_url.HerOkuAppBaseURL;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class Get_09 extends HerOkuAppBaseURL {

    /*
    Given
    https://restful-booker.herokuapp.com/booking/1
    When
    I send GET Request to the url
            Then
    Response body should be like that;
    {
        "firstname": "Eric",
            "lastname": "Smith",
            "totalprice": 456,
            "depositpaid": false,
            "bookingdates": {
                "checkin": "2016-09-09",
                "checkout": "2017-09-21"
    } */

    @Test
    public void get01(){

        // 1. set the urlMap
        spec.pathParams("first", "booking", "second", 1);

        // set the expected data
        Map<String, String> bookingDatesMaps = new HashMap<>();
        bookingDatesMaps.put("checkin", "2020-08-04");
        bookingDatesMaps.put("checkout", "2021-04-30");

        Map<String, Object> expectedDataMap = new HashMap<>();
        expectedDataMap.put("firstname", "Eric");
        expectedDataMap.put("lastname", "Smith");
        expectedDataMap.put("totalprice", 456);
        expectedDataMap.put("depositpaid", false);
        expectedDataMap.put("bookingdates", bookingDatesMaps);

        System.out.println(expectedDataMap);

        // 3. send the request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        Map<String,Object> actualDataMap = response.as(HashMap.class);
        System.out.println(actualDataMap);

        // 4. do assertions
//        assertEquals(expectedDataMap.get("firstname"),actualDataMap.get("firstname"));
//        assertEquals(expectedDataMap.get("lastname"),actualDataMap.get("lastname"));
//        assertEquals(expectedDataMap.get("depositpaid"),actualDataMap.get("depositpaid"));
//        assertEquals(expectedDataMap.get("totalprice"),actualDataMap.get("totalprice"));

        assertEquals(bookingDatesMaps.get("checkin"), ((Map)actualDataMap.get("bookingdates")).get("checkin")); // explicit ... actualDataMap.get("bookingdates")) was object, we converted to Map
        assertEquals(bookingDatesMaps.get("checkout"), ((Map)actualDataMap.get("bookingdates")).get("checkout"));
    }
}
