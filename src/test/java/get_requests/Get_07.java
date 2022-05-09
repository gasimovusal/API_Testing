package get_requests;

import base_url.JsonPlaceHolderBaseURL;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class Get_07 extends JsonPlaceHolderBaseURL {

    /*
    Given
	   	    https://jsonplaceholder.typicode.com/todos
	When
			 I send GET Request to the URL
	Then
			 1)Status code is 200
			 2)Print all ids greater than 190 on the console
			   Assert that there are 10 ids greater than 190
			 3)Print all userIds less than 5 on the console
			   Assert that maximum userId less than 5 is 4
			 4)Print all titles whose ids are less than 5
			   Assert that “delectus aut autem” is one of the titles whose id is less than 5
     */

    @Test
    public void get01(){

        //1.Step:Set the URL
        spec.pathParam("first", "todos");

        //2.Step:Set the Expected Data

        //3.Step:Send the Request and Get the Response
        Response response = given().spec(spec).when().get("/{first}");
        response.prettyPrint();

        //Do Assertions
        //1)Status code is 200
        response.then().assertThat().statusCode(200);

        //2)Print all ids greater than 190 on the console
        JsonPath json = response.jsonPath();

        List<Integer> ids = json.getList("findAll{it.id>190}.id");//Groovy Language: Java Based Programming Language
                                                    // it stands for json, it is similar to this()
                                                    // .id means ==> if ids are greater than 190, give me ids
        // to work with multiple data, we use getList
        System.out.println(ids);

        // assert that there are 10 ids greater then 190
        assertEquals("Number of ids did not match", 10, ids.size());

        //2)Print all ids greater than 190 on the console
        List<Integer> userIds = json.getList("findAll{it.id<5}.userId");
        System.out.println(userIds);

        // Assert that number of userId whose ids are less than 5 is 4
        assertEquals("The number of userIds whose ids are less than 5 is not 4", 4, userIds.size());

        // 4. print all title whose ids are less than 5
        List<String> titles = json.getList("findAll{it.id<5}.title");
        System.out.println(titles);

        // assert that “delectus aut autem” is one of the title whose id is less than 5
        // 1. way
        assertTrue("Expected title is not amon them", titles.contains("delectus aut autem"));

        // 2. way
        assertTrue("Expected title is not amon them", titles.stream().anyMatch(t-> t.equals("delectus aut autem")));
    }

}
