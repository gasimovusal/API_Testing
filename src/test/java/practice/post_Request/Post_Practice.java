package practice.post_Request;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class Post_Practice extends HEROKUappBaseUrl{

    /*
    Given
	        https://restful-booker.herokuapp.com/booking
	        {
                "firstname": "Selim",
                "lastname": "Ak",
                "totalprice": 11111,
                "depositpaid": true,
                "bookingdates": {
                    "checkin": "2021-09-09",
                    "checkout": "2021-09-21"
                 }
              }
        When
	 		I send POST Request to the Url
	 	Then
	 		Status code is 200
	 		And response body should be like:
	 		{
				"bookingid": 11,
				"booking": {
					"firstname": "Selim",
					"lastname": "Ak",
					"totalprice": 11111,
					"depositpaid": true,
					"bookingdates": {
						"checkin": "2020-09-09",
						"checkout": "2020-09-21"
					}
				}
			}
     */

///////// GIVING ERROR

    @Test
    public void put01(){

        spec.pathParam("first", "booking");

        HEROKUappTestData herokuapp = new HEROKUappTestData();

        Map<String, String> bookingdateMap = herokuapp.bookingdatesSetUp("2021-09-09", "2021-09-21");
        Map<String, Object> expecteddataMap = herokuapp.expecteddataSetSup("Selim", true, bookingdateMap);

        Response response = given().spec(spec).contentType(ContentType.JSON).body(expecteddataMap).when().post("/{first}");
        response.prettyPrint();

        Map<String, Object> actualData = response.as(HashMap.class);

        assertEquals(expecteddataMap.get("firstname"), ((Map)actualData.get("booking")).get("firstname"));
        assertEquals(expecteddataMap.get("depositpaid"), ((Map)actualData.get("booking")).get("depositpaid"));
        assertEquals(bookingdateMap.get("checkin"), ((Map)((Map)actualData.get("booking")).get("bookingdates")).get("checkin"));
        assertEquals(bookingdateMap.get("checkout"), ((Map)((Map)actualData.get("booking")).get("bookingdates")).get("checkout"));

    }



}
