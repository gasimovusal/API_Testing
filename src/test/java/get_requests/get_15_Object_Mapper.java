package get_requests;

import base_url.HerOkuAppBaseURL;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;
import pojos.Booking_Pojo;
import utils.Json_Util;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class get_15_Object_Mapper extends HerOkuAppBaseURL {

    /*
    Given
	            https://restful-booker.herokuapp.com/booking/2
        When
		 		I send GET Request to the URL
		Then
		 		Status code is 200
                            {
                                "firstname": "Jim",
                                "lastname": "Smith",
                                "totalprice": 649,
                                "depositpaid": false,
                                "bookingdates": {
                                    "checkin": "2015-09-16",
                                    "checkout": "2018-04-09"
                                },
                                "additionalneeds": "Breakfast"
                            }
     */

    @Test
    public void ge01(){

        // 1. set the url
        spec.pathParams("first", "booking", "second", 2);

        String expectedData = "{\n" +
                "\"firstname\": \"Mark\",\n" +
                "\"lastname\": \"Jones\",\n" +
                "\"totalprice\": 802,\n" +
                "\"depositpaid\": false,\n" +
                "\"bookingdates\": {\n" +
                "\"checkin\": \"2017-02-18\",\n" +
                "   \"checkout\": \"2019-10-26\"\n" +
                " },\n" +
                "   \"additionalneeds\": \"Breakfast\"\n" +
                "  }";

        Booking_Pojo expectedDataPojo = Json_Util.convertJsonToJavaObject(expectedData, Booking_Pojo.class);

        Response response = given().spec(spec).when().get("/{first}/{second}");

        Booking_Pojo actualDataPojo = Json_Util.convertJsonToJavaObject(response.asString(), Booking_Pojo.class);

        //Hard assertion
//        assertEquals(200, response.statusCode());
//        assertEquals(expectedDataPojo.getFirstname(), actualDataPojo.getFirstname());
//        assertEquals(expectedDataPojo.getLastname(), actualDataPojo.getLastname());
//        assertEquals(expectedDataPojo.getTotalprice(), actualDataPojo.getTotalprice());
//        assertEquals(expectedDataPojo.getDepositpaid(), actualDataPojo.getDepositpaid());
//        assertEquals(expectedDataPojo.getBookingdates().getCheckin(), actualDataPojo.getBookingdates().getCheckin());
//        assertEquals(expectedDataPojo.getBookingdates().getCheckout(), actualDataPojo.getBookingdates().getCheckout());

        // soft assertion
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(actualDataPojo.getFirstname(), expectedDataPojo.getFirstname()," Firstname did not match");
        softAssert.assertEquals(actualDataPojo.getTotalprice(), expectedDataPojo.getTotalprice(),"Total price did not match");
        softAssert.assertEquals(actualDataPojo.getBookingdates().getCheckin(), expectedDataPojo.getBookingdates().getCheckin(), "checkin paid did not match");
        softAssert.assertEquals(actualDataPojo.getBookingdates().getCheckout(), expectedDataPojo.getBookingdates().getCheckout(), "checkout paid did not match");


        softAssert.assertAll();

    }
}
