package practice.post_Request;

import java.util.HashMap;
import java.util.Map;

public class HEROKUappTestData {

    public Map<String, String> bookingdatesSetUp(String checkin, String checkout){

        Map<String, String> bookingdaseMap = new HashMap<>();
        bookingdaseMap.put("checkin", checkin);
        bookingdaseMap.put("checkout", checkout);

        return bookingdaseMap;
    }

   Map<String, Object> expecteddataMap = new HashMap<>();

    public Map<String, Object> expecteddataSetSup(String firstname, boolean depositpaid, Map<String, String> bookingdates){

        expecteddataMap.put("firstname", firstname);
        expecteddataMap.put("depositpaid", depositpaid);
        expecteddataMap.put("bookingdates", bookingdates);

        return expecteddataMap;

    }

}
