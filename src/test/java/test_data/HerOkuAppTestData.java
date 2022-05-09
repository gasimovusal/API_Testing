package test_data;

import java.util.HashMap;
import java.util.Map;

public class HerOkuAppTestData {

    public Map<String, String> bookingDataSetSup(String checkin, String checkout){

        Map<String, String> bookingDataMap = new HashMap<>();

        bookingDataMap.put("checkin", checkin);
        bookingDataMap.put("checkout", checkout);

        return bookingDataMap;

    }

    public Map<String, Object> expectedDataSetUp(String firstname, String lastname, int totalprice, boolean depositpaid, Map<String, String> bookingdates){

        Map<String, Object> expectedDataMap = new HashMap<>();
        expectedDataMap.put("firstname", firstname);
        expectedDataMap.put("lastname", lastname);
        expectedDataMap.put("totalprice", totalprice);
        expectedDataMap.put("depositpaid", depositpaid);
        expectedDataMap.put("bookingdates", bookingdates);

        return expectedDataMap;


    }
}
