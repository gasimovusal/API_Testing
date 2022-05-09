package get_requests;

import base_url.JsonPlaceHolderBaseURL;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Get_03 extends JsonPlaceHolderBaseURL {

    /*
    Given
    https://jsonplaceholder.typicode.com/todos/23
    When
    User send GET Request to the URL
    Then
    HTTP Status Code should be 200
    And
    Response format should be "application/json"
    And
		    "title" is "et itaque necessitatibus maxime molestiae qui quas velit", // title is in response body, we use body keyword
    And
		    "completed" is false // completed is in response body, we use body keyword
    And
		    "userId" is 2 // userid is in response body, we use body keyword
     */

    @Test
    public void verifyTitleCompletedUserID(){

        // 1. set the url

        // this method is not recommended because repetition, hard to change url for each method
        //String url = "https://jsonplaceholder.typicode.com/todos/23";

        // you cannot use spec without extend Base_urls class
        // pet Parameters, if you see / after main url, they call path parameters
        spec.pathParams("first", "todos", "second", 23);

        // 2. set the expected data

        // 3. send the request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");

        // 4. do Assertions
        // 1. way
        response.
                then().
                assertThat().
                statusCode(200).
                contentType("application/json").
                body("title", equalTo("et itaque necessitatibus maxime molestiae qui quas velit")).
                body("completed", equalTo(false)). // false is boolean, we dont use ""
                body("userId", equalTo(2));

        // 2. way
        response.
                then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON). // this means "application/json"
                body("title", equalTo("et itaque necessitatibus maxime molestiae qui quas velit"),
                "completed", equalTo(false),
                "userId", equalTo(2)); // we did not write body 3 times, instead we wrote all of them in one body

        /*
        Interview question
        NOTE: when you execute assertions, Java stops execution just after the first failure, it means assertion after the failure were not executed
              If assertions were not executed, you cannot tell anything about their states. they may pass or they may fail
              But the assertions before failure were passed because assertions before failure were executed

        NOTE: if you type your code as execution will stop in the fist failure, this is called "Hard Assertion" (Assertion)
        NOTE: if you type your code as execution will not stop in the any failure, this is called "Soft Assertion" (Verification)
        NOTE: if you use multiple "body()" method, it will work like "Hard Assertion", if you use just single "body()" method with multiple assertion in it, it will work like "Soft Assertion"
         */


    }
}
