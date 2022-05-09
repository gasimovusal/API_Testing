package post_requests;

import base_url.HerOkuAppBaseURL;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.HerOkuAppTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class Post_01 extends HerOkuAppBaseURL {

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

    /*
    NOTE 1: the data you send in the request is called "request body" or "Payload" ==> body before then
    NOTE 2: the data you send in the response is called "response body"  ==> body after then
     */

    @Test
    public void post01(){


        spec.pathParam("first", "booking");

        // 2. set the expected data ==> for some kind of
        HerOkuAppTestData herOkuApp = new HerOkuAppTestData();
        Map<String, String> bookingDatesMap = herOkuApp.bookingDataSetSup("2020-09-09", "2020-09-21");
        Map<String, Object> expectedDataMap = herOkuApp.expectedDataSetUp("Selim", "Ak", 11111, true, bookingDatesMap);

        // 3. send post request and get the response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedDataMap).when().post("/{first}"); // boy method can work with map
        // when you send post request, DATA TYPE which you are sending is very important
        // in or to send request body, we have to out .body()
        // if api accepts xml, body method will be able to convert the xml. it depends on api
        // soap is good for security, but rest assured is open to json and xml

        response.prettyPrint();

        // 4. do assertion
        Map<String, Object> actualDataMap = response.as(HashMap.class); // converting j.......

        assertEquals(expectedDataMap.get("firstname"), ((Map)actualDataMap.get("booking")).get("firstname"));
        assertEquals(expectedDataMap.get("lastname"), ((Map)actualDataMap.get("booking")).get("lastname"));
        assertEquals(expectedDataMap.get("totalprice"), ((Map)actualDataMap.get("booking")).get("totalprice"));
        assertEquals(expectedDataMap.get("depositpaid"), ((Map)actualDataMap.get("booking")).get("depositpaid"));

        assertEquals(bookingDatesMap.get("checkin"), ((Map)((Map)actualDataMap.get("booking")).get("bookingdates")).get("checkin"));
        assertEquals(bookingDatesMap.get("checkout"), ((Map)((Map)actualDataMap.get("booking")).get("bookingdates")).get("checkout"));

        // assert equals method is just checking

        // evey time actualmap in get will give yu Object


    }
}
