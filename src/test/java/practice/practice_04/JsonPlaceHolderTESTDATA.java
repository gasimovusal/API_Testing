package practice.practice_04;

import java.util.HashMap;
import java.util.Map;

public class JsonPlaceHolderTESTDATA {

    public Map<String, Object>  expectedDataWithAllKeys(Integer userId, String title, Boolean completed){

        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("userId", userId);
        expectedData.put("title", title);
        expectedData.put("completed", completed);

        return expectedData;

        /*
        when i use expectedDataWithAllKeys, if  value of userId is 1, "userId" keyword will be 1
         */
    }

    public Map<String, Object> expectedDataWithMissingKeys(Integer userId, String title, Boolean completed){

        Map<String, Object> expectedData = new HashMap<>();

        if(title !=null) {
            expectedData.put("title", title);
        }
        if(completed !=null) {
            expectedData.put("title", completed);
        }
        if(userId !=null) {
            expectedData.put("title", userId);
        }
        return expectedData;
    }
}
