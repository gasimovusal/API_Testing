package get_requests;

import base_url.HerOkuAppBaseURL;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDates_Pojo;
import pojos.Booking_Pojo;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class Get_12_Pojo extends HerOkuAppBaseURL {

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
    public void get01Pojo(){

        spec.pathParams("first", "booking", "second", 2);

        BookingDates_Pojo bookingDatesPojo = new BookingDates_Pojo("2017-08-24", "2017-12-21");
        Booking_Pojo bookingPojo = new Booking_Pojo("Susan", "Brown", 444, true, bookingDatesPojo, "Breakfast");

        Response response = given().spec(spec).when().get("/{first}/{second}");
        Booking_Pojo actualPojo = response.as(Booking_Pojo.class);

//        assertEquals(bookingPojo.getFirstname(), actualPojo.getFirstname());
//        assertEquals(bookingPojo.getLastname(), actualPojo.getLastname());
//        assertEquals(bookingPojo.getTotalprice(), actualPojo.getTotalprice());
        //1.Way:
        assertEquals(bookingPojo.getBookingdates().getCheckin(), actualPojo.getBookingdates().getCheckin());
        assertEquals(bookingPojo.getBookingdates().getCheckout(), actualPojo.getBookingdates().getCheckout());
        //2.Way:Recommended
        assertEquals(bookingDatesPojo.getCheckin(), actualPojo.getBookingdates().getCheckin());
        assertEquals(bookingDatesPojo.getCheckout(), actualPojo.getBookingdates().getCheckout());




    }
}
