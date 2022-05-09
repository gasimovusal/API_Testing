package patch_request;

import base_url.JsonPlaceHolderBaseURL;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Patch_01 extends JsonPlaceHolderBaseURL {

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
    public void patch01() {

        // 1. set the url
        spec.pathParams("first", "todos", "second", 198);

        // 2. set the reuqest body
        JsonPlaceHolderTestData requestBody = new JsonPlaceHolderTestData();
        Map<String, Object> requestBodyMap = requestBody.expectedDataWithMissingKeys(null, "Wash the dishes", null);

        // 3. send the patch request and get the response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(requestBodyMap).when().patch("/{first}/{second}");
        response.prettyPrint();

        // 4. do assertion
        // 1. way

        response.then().assertThat().statusCode(200).body("title", equalTo(requestBodyMap.get("title")));

        // when you do PATCH Assertion, jts the data you update should be asserted. But if someone insits an assert all parts, do the following
        // when do we need this assertion? maybe patch method will assert other data as well.
        Map<String, Object> mapToAssertAllDetails = requestBody.expectedDataWithAllKeys(10, "Wash the dishes", true);
        //response.then().assertThat().statusCode(200).body("title", equalTo(mapToAssertAllDetails.get("title")),
//                "userId", equalTo(mapToAssertAllDetails.get("userId")),
//                        "completed", equalTo(mapToAssertAllDetails.get("completed"));
        response.then().assertThat().statusCode(200).body("userId", equalTo(mapToAssertAllDetails.get("userId")));
        response.then().assertThat().statusCode(200).body("completed", equalTo(mapToAssertAllDetails.get("completed")));

    }

}
