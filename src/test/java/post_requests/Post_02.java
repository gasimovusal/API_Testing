package post_requests;

import base_url.JsonPlaceHolderBaseURL;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import practice.test_data.JsonplaceholderTestDATA;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class Post_02 extends JsonPlaceHolderBaseURL {

    /*
     Given
            https://jsonplaceholder.typicode.com/todos
            {
            "userId": 55,
            "title": "Tidy your room",
            "completed": false
            }
        When
            I send POST Request to the Url
        Then
            Status code is 201
        And
            response body is like {
                                    "userId": 55,
                                    "title": "Tidy your room",
                                    "completed": false,
                                    "id": 201
                                    }
     */

    @Test
    public void post01(){

        // 1. set the url
        spec.pathParams("first", "todos");

        // 2. set the expected data (request body - payload)
        JsonplaceholderTestDATA expectedData = new JsonplaceholderTestDATA();
        Map<String, Object> expectedDataMap = expectedData.expecteddatawithKEYS(55, false, "Tidy your room");

        // 3. send post request and get the response
        Response response =  given().spec(spec).contentType(ContentType.JSON).body(expectedDataMap).when().post("/{first}");
        response.prettyPrint();

        // 4. do assertion
        Map<String, Object> actualDataMap  = response.as(HashMap.class);
        assertEquals(expectedDataMap.get("userId"), actualDataMap.get("userId"));


    }


}
