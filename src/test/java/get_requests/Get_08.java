package get_requests;

import base_url.JsonPlaceHolderBaseURL;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class Get_08 extends JsonPlaceHolderBaseURL {

    /*
        What is the biggest challenge in API testing? data types (communication)
        Our application works with Java. In application, data types are Objects and Primitive data types
        API uses XML or Json as data types
        We will convert Json to Java Object and I will send it to application.
        Application does not understand Jason

        I will get java object, I will convert it to jason, and I will send it to API
        Serialization: To convert Java Object to JSON Data
        De-Serialization: To convert JSON Data to Java Object

        To do De-Serialization and Serialization, we have 2 option:
        1)Gson: Google Created
        2)Object Mapper: More popular
     */

    /*
         Given
            https://jsonplaceholder.typicode.com/todos/2
        When
         I send GET Request to the URL
       Then
         Status code is 200
         And "completed" is false
         And "userId" is 1
         And "title" is "quis ut nam facilis et officia qui"
         And header "Via" is "1.1 Vegur"
         And header "Server" is "cloudflare"
         {
                "userId": 1,
                "id": 2,
                "title": "quis ut nam facilis et officia qui",
                "completed": false
            }
     */
    @Test
    public void get01() {
        //1.Step: Set the URL
        spec.pathParams("first", "todos", "second", 2);

        //2.Step:Set the Expected Data
        Map<String, Object> expectedData = new HashMap<>(); // HashMap is fasts one
        expectedData.put("userId", 1);
        expectedData.put("title", "quis ut nam facilis et officia qui");
        expectedData.put("completed", false);
        expectedData.put("StatusCode", 200);
        expectedData.put("Via", "1.1 vegur");
        expectedData.put("Server", "cloudflare");

        //3.Step: Send the Request and Get the Response
        Response response = given().spec(spec).when().get("/{first}/{second}");

        Map<String, Object> actualData = response.as(HashMap.class);  // converting Response to map
        System.out.println(actualData);

        // separating data from our main code!!!!

        //4.Step: Do Assertions
        assertEquals(expectedData.get("userId"), actualData.get("userId"));
        assertEquals(expectedData.get("title"), actualData.get("title"));
        assertEquals(expectedData.get("completed"), actualData.get("completed"));
        assertEquals(expectedData.get("StatusCode"), response.getStatusCode());
        assertEquals(expectedData.get("Via"), response.getHeader("Via"));
        assertEquals(expectedData.get("Server"), response.getHeader("Server"));
    }

    @Test
    public void get02() {

        //1.Step: Set the URL
        spec.pathParams("first", "todos", "second", 2);

        //2.Step:Set the Expected Data
        JsonPlaceHolderTestData expectedData = new JsonPlaceHolderTestData(); // CREATED OBJECT to access the method which is non-static
        Map<String, Object> expectedDataMap = expectedData.expectedDataWithAllKeys(1, "quis ut nam facilis et officia qui", false);
        expectedDataMap.put("StatusCode", 200);
        expectedDataMap.put("Via", "1.1 vegur");
        expectedDataMap.put("Server", "cloudflare");

        //3.Step: Send the Request and Get the Response
        Response response = given().spec(spec).when().get("/{first}/{second}");

        Map<String, Object> actualData = response.as(HashMap.class);
        System.out.println(actualData);

        //4.Step: Do Assertions
        assertEquals(expectedDataMap.get("userId"), actualData.get("userId"));
        assertEquals(expectedDataMap.get("title"), actualData.get("title"));
        assertEquals(expectedDataMap.get("completed"), actualData.get("completed"));
        assertEquals(expectedDataMap.get("StatusCode"), response.getStatusCode());
        assertEquals(expectedDataMap.get("Via"), response.getHeader("Via"));
        assertEquals(expectedDataMap.get("Server"), response.getHeader("Server"));

    }
}
