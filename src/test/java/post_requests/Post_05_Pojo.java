package post_requests;

import base_url.HerOkuAppBaseURL;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDates_Pojo;
import pojos.Booking_Pojo;
import pojos.Booking_ResponseBody_Pojo;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class Post_05_Pojo extends HerOkuAppBaseURL {

    /*
    Given
         https://restful-booker.herokuapp.com/booking
         {
             "firstname": "Ali",
             "lastname": "Can",
             "totalprice": 999,
             "depositpaid": true,
             "bookingdates": {
                 "checkin": "2021-09-21",
                 "checkout": "2021-12-21"
              }
              "additionalneeds": "Breakfast with white tea, Dragon juice"
          }
     When
    I send POST Request to the URL
   Then
    Status code is 200
And
    Response body is like {
                                "bookingid": 16,
                                "booking" :{
                                    "firstname": "Ali",
                                    "lastname": "Can",
                                    "totalprice": 999,
                                    "depositpaid": true,
                                    "bookingdates": {
                                        "checkin": "2021-09-21",
                                        "checkout": "2021-12-21"
                                    }
                                    "additionalneeds": "Breakfast with white tea, Dragon juice"
                                }
                             }
     */


    @Test
    public void postPojo01(){

        spec.pathParam("first", "booking");

        BookingDates_Pojo bookingDates = new BookingDates_Pojo("2021-09-21", "2021-12-21");
        Booking_Pojo bookingPojo = new Booking_Pojo("Vusal", "Gasimov", 999, true, bookingDates, "Breakfast with white tea, Dragon juice");
        Response response = given().spec(spec).contentType(ContentType.JSON).body(bookingPojo).when().post("/{first}");

        Booking_ResponseBody_Pojo actualPojo = response.as(Booking_ResponseBody_Pojo.class); // converting reponsee to Bokking pojo

        assertEquals(200, response.getStatusCode());
        assertEquals(bookingPojo.getFirstname(), actualPojo.getBooking());
        assertEquals(bookingPojo.getLastname(), actualPojo.getBooking().getLastname());
        assertEquals(bookingPojo.getTotalprice(), actualPojo.getBooking().getTotalprice());
        assertEquals(bookingPojo.getDepositpaid(), actualPojo.getBooking().getDepositpaid());

        //1.Way: Can be used
        assertEquals(bookingPojo.getBookingdates().getCheckin(), actualPojo.getBooking().getBookingdates().getCheckin());
        //assertEquals(bookingPojo.getBookingdates().getCheckout(), actualPojo.getBooking().getBookingdates().getCheckout());

        //2.Way: Recommended
        assertEquals(bookingDates.getCheckin(), actualPojo.getBooking().getBookingdates().getCheckin());
        //assertEquals(bookingDates.getCheckout(), actualPojo.getBooking().getBookingdates().getCheckout());



    }
}
