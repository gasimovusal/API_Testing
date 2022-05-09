package post_requests;

import base_url.JsonPlaceHolderBaseURL;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.Json_PlaceHolder_Pojo;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class Post_04_Pojo extends JsonPlaceHolderBaseURL {

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
    public void postPojo01(){

        // 1. set the rul
        spec.pathParam("first","todos");

        // 2.
        Json_PlaceHolder_Pojo requestBody = new Json_PlaceHolder_Pojo(55, "Tidy your room", false);

        // 3. send post request and get the response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(requestBody).when().post("/{first}");
        response.prettyPrint();

        // 4. do assertion
        Json_PlaceHolder_Pojo actualBody = response.as(Json_PlaceHolder_Pojo.class);

        assertEquals(requestBody.getUserId(), actualBody.getUserId());
        assertEquals(requestBody.getTitle(), actualBody.getTitle());
        assertEquals(requestBody.getCompleted(), actualBody.getCompleted());



    }


}
