package get_requests;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;

public class Get_01 {

    /*
    1) postman is used for manual API testing
    2) we use rest-assured library for api automation testing
    3) to type automation scripts follow the given steps:
        a) understand the requirement
        b) type test cases
            i) to type test cases we use "Gerking Language"
                "Gerking Language" has some keywords to type test cases
                the keywords are Given: it is for prerequisites
                                 When: for actions
                                 Then: for outputs,
                                 And: for multiple given or when or then
        c) start to type the automaton scripts
            i) set the URL
            ii) set the expected data(Post, Put, Patch)
            iii) type code to send request
            iv) do assertions (compare expected result with actual result)
     */

    /* test case:
    Given
            https://restful-booker.herokuapp.com/booking/3
        When
            User sends a GET Request to the url
        Then
            HTTP Status Code should be 200
        And
            Content Type should be JSON
        And
            Status Line should be HTTP/1.1 200 OK
     */

    // test method return type must be void
    @Test //test from junit
    public void get01(){

//        i) set the URL
        String url = "https://restful-booker.herokuapp.com/booking/3";
//        ii) set the expected data(Post, Put, Patch)

//        iii) type code to send request: start with given() -> more action -> import static method -> change given to * (all)
        Response response = given().when().get(url);

        response.prettyPrint(); // prints all

//        iv) do assertions (compare expected result with actual result)
        // assert means verify
        response.then().assertThat().statusCode(200).contentType("application/json").statusLine("HTTP/1.1 200 OK");

        // how to print status code on th e console
        System.out.println("Status code: " + response.getStatusCode());

        // how to print content type on console
        System.out.println("Content type: " + response.getContentType());

        // how to print status line on console
        System.out.println("Status line: " + response.getStatusLine());

        // hoe to print time on console
        System.out.println("Time: " + response.time());

        // how to print header on console
        System.out.println("Connection header: " + response.getHeader("Connection"));

        System.out.println();
        // how to print all headers on console
        //                expected value        actual value
        System.out.println("All header: " + response.getHeaders());

    }



}
