package get_requests;

import base_url.JsonPlaceHolderBaseURL;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.Json_PlaceHolder_Pojo;
import test_data.JsonPlaceHolderTestData;
import utils.Json_Util;

import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class Get_14_Object_Mapper extends JsonPlaceHolderBaseURL {

    /*
    Given
	        https://jsonplaceholder.typicode.com/todos/198
        When
	 		I send GET Request to the URL
	 	Then
	 		Status code is 200
	 		And response body is like {
									    "userId": 10,
									    "id": 198,
									    "title": "quis eius est sint explicabo",
									    "completed": true
									  }
     */

    @Test
    public  void get01ObjectMapper(){

        spec.pathParams("first", "todos", "second", 198);
        JsonPlaceHolderTestData jsonPlaceHolderTestData = new JsonPlaceHolderTestData();
        String expectedData = jsonPlaceHolderTestData.expectedDataInString(10, "quis eius est sint explicabo", true);

        HashMap<String, Object> expectedDataMap = Json_Util.convertJsonToJavaObject(expectedData, HashMap.class); // expectedData will get string method and convert to HashMap

        // 3. send the request, get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");

        HashMap<String, Object> actualData = Json_Util.convertJsonToJavaObject(response.asString(), HashMap.class);

        assertEquals(expectedDataMap.get("userId"), actualData.get("userId"));
        assertEquals(expectedDataMap.get("title"), actualData.get("title"));
        assertEquals(expectedDataMap.get("completed"), actualData.get("completed"));

    }

    @Test // this is the best way
    public  void get02ObjectMapper(){

        spec.pathParams("first", "todos", "second", 198);
        JsonPlaceHolderTestData jsonPlaceHolderTestData = new JsonPlaceHolderTestData();
        String expectedData = jsonPlaceHolderTestData.expectedDataInString(10, "quis eius est sint explicabo", true);

        Json_PlaceHolder_Pojo expectedDataPojo = Json_Util.convertJsonToJavaObject(expectedData, Json_PlaceHolder_Pojo.class);

        Response response = given().spec(spec).when().get("/{first}/{second}");

        Json_PlaceHolder_Pojo actualDataPojo = Json_Util.convertJsonToJavaObject(response.asString(), Json_PlaceHolder_Pojo.class);

        assertEquals(200, response.statusCode());
        assertEquals(expectedDataPojo.getTitle(), actualDataPojo.getTitle());
        assertEquals(expectedDataPojo.getCompleted(), actualDataPojo.getCompleted());



    }
}
