package practice.test_data;

import java.util.HashMap;
import java.util.Map;

public class HerokuappTESTDATA {


    public Map<String, String> bookingdatesdataseup(String checkin, String checkout){


        Map<String, String> bookingdatesMap = new HashMap<>();
        bookingdatesMap.put("checkin", checkin);
        bookingdatesMap.put("checkout", checkout);

        return bookingdatesMap;

    }

    public Map<String, Object> expecteddataSetup(String firstname, Boolean depositpaid, Map<String, String> bookingdates){

        Map<String, Object> expecteddataMap = new HashMap<>();
        expecteddataMap.put("firstname", firstname);
        expecteddataMap.put("depositpaid", depositpaid);
        expecteddataMap.put("bookingdates", bookingdates);

        return expecteddataMap;
    }


}
