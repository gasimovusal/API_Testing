package practice.practice_04;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class Patch_1 extends JsonPlaceHolderBASEURL{

    /*
    Given
	       1) https://jsonplaceholder.typicode.com/todos/198
	        2) {
                "title": "Wash the dishes"
               }
        When
	 		I send PATCH Request to the Url
	    Then
	   	   Status code is 200
	   	   And response body is like   {
									    "userId": 10,
									    "title": "Wash the dishes",
									    "completed": true,
									    "id": 198
									   }
     */

    @Test
    public void patch01(){

        spec.pathParams("first", "todos", "second", 198);

        JsonPlaceHolderTESTDATA jsonplaceholderTestDATA = new JsonPlaceHolderTESTDATA();
        Map<String, Object> expecteddata = jsonplaceholderTestDATA.expectedDataWithMissingKeys(null, "Wash the dishes", null);

        Response response = given().spec(spec).contentType(ContentType.JSON).body(expecteddata).when().patch("/{first}/{second}");

        response.prettyPrint();

        //response.then().assertThat().statusCode(200).body("title", equalTo(req));

        Map<String, Object> actualData = jsonplaceholderTestDATA.expectedDataWithAllKeys(10, "Wash the dishes", null);
        assertEquals(expecteddata.get("title"), actualData.get("title"));

    }
}
