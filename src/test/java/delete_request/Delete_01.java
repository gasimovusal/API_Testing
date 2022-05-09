package delete_request;

import base_url.JsonPlaceHolderBaseURL;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class Delete_01 extends JsonPlaceHolderBaseURL {

    /*
     Given
            https://jsonplaceholder.typicode.com/todos/198
        When
	 		I send DELETE Request to the Url
	 	Then
		 	Status code is 200
		 	And Response body is { }
     */

    @Test
    public void delete01(){

        spec.pathParams("first", "todos", "second", 198);

        // 2. set the expected data
        Map<String, Object> expectedMap = new HashMap<>(); // response body is empty json, empty json means empty map in java

        // 3. send delete request and get the response
        Response response = given().spec(spec).when().delete("/{first}/{second}"); // after spec we
        response.prettyPrint();

        // 1. way
        Map<String, Object> actualMap = response.as(HashMap.class);
        response.then().assertThat().statusCode(200);
        assertEquals(expectedMap, actualMap);

        // 2. way
        assertTrue(actualMap.size() == 0); // or
        assertTrue(actualMap.isEmpty()); // recommended
        /*
        INTERVIEW QUESTION!!!
        how to automate DELETE REQUEST in API testing?
        1) create new data by using POST REQUEST
        2) use DELETE REQUEST to delete newly created data

        NOTE: do not use delete request for the existing data. Create your own data to delete then delete it


        how to test delete request in API testing? I send delete request, then I will do assertion
                first run you delete 198, what will you delete in second run?
                inside delete test method, at the begging, I use post method, post method will give me new data, and then I will delete
                in every execution of delete method, you will create new data, and you will delete it

         */


    }
}
