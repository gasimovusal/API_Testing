package put_requets;

import base_url.JsonPlaceHolderBaseURL;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Put_01 extends JsonPlaceHolderBaseURL {

    /*
    Given
	        https://jsonplaceholder.typicode.com/todos/198
	        {
                "userId": 21,
                "title": "Wash the dishes",
                "completed": false
            }
        When
	 		I send PUT Request to the Url
	    Then
	   	   Status code is 200
	   	   And response body is like   {
									    "userId": 21,
									    "title": "Wash the dishes",
									    "completed": false
									   }
     */

    @Test
    public void Put01(){

        spec.pathParams("first", "todos", "second", 198);

        // 2. set the payload
        JsonPlaceHolderTestData expectedData = new JsonPlaceHolderTestData();
        Map<String, Object> expectedDataMap = expectedData.expectedDataWithAllKeys(21, "Wash the dishes", false);

        // 3. send the put request
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedDataMap).when().put("/{first}/{second}");
        response.prettyPrint();

        // 4. do assertions
        Map<String, Object> actualDataMap = response.as(HashMap.class);
        assertEquals(expectedDataMap.get("userId"), actualDataMap.get("userId"));
        assertEquals(expectedDataMap.get("completed"), actualDataMap.get("completed"));
        assertEquals(expectedDataMap.get("title"), actualDataMap.get("title"));

    }
}
