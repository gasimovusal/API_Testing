package practice.practice_03;


import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class P_Get01 extends HerokuappURL{

    /*

        Given
            https://restful-booker.herokuapp.com/booking/2
        When
 		    I send GET Request to the URL
 	    Then
 		    Status code is 200
 		And
 		    Response body is like {
                                    "firstname": "Mary",
                                    "lastname": "Brown",
                                    "totalprice": 227,
                                    "depositpaid": true,
                                    "bookingdates": {
                                        "checkin": "2015-10-21",
                                        "checkout": "2021-08-28"
                                    },
                                    "additionalneeds": "Breakfast"
                                  }
     */

    @Test
    public void get01(){

        spec.pathParams("first", "booking", "second", 2);

        BookingDatesPOJO bookingDatesPOJO = new BookingDatesPOJO("2020-10-31", "2021-01-25");
        HerokuappResponseBodyPOJO expectedData = new HerokuappResponseBodyPOJO("Mary", "Jones", 822, true, bookingDatesPOJO, "Breakfast");

        Response response = given().spec(spec).contentType(ContentType.JSON).when().get("/{first}/{second}");

        response.prettyPrint();
        HerokuappResponseBodyPOJO actualData = response.as(HerokuappResponseBodyPOJO.class);

        assertEquals(expectedData.getFirstname(), actualData.getFirstname());
        assertEquals(expectedData.getBookingdates().getCheckin(), actualData.getBookingdates().getCheckin());
    }

}
