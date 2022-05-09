package practice.test_data;

import java.util.HashMap;
import java.util.Map;

public class JsonplaceholderTestDATA {

    public Map<String, Object> expecteddatawithKEYS(Integer userId, Boolean completed, String title) {

        Map<String, Object> expecteddata = new HashMap<>();
        expecteddata.put("userId", userId);
        expecteddata.put("completed", completed);
        expecteddata.put("title", "title");

        return expecteddata;
    }
}