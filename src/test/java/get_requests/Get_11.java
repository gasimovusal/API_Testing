package get_requests;

import base_url.GoRestBaseURL;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertTrue;

public class Get_11 extends GoRestBaseURL {

    /*
    Given
            https://gorest.co.in/public/v1/users
        When
            User send GET Request
        Then
            The value of "pagination limit" is 20
        And
            The "current link" should be "https://gorest.co.in/public/v1/users?page=1"
        And
            The number of users should  be 20
        And
            We have at least one "active" status
        And
            "Indra Ganaka", "Sarada Mehrotra", "Jagathi Chopra" are among the users
        And
            The female users are more than male users
     */


    @Test
    public void get01(){

        //1 . set the URL
        spec.pathParams("first", "users");

        //2. set the expected data

        // 3. send the request and et the response
        Response response = given().spec(spec).when().get("/{first}");
        response.prettyPrint();

        // 4. do assertions
        // 1. way: using body() method
        // instead of creating inner p=ma, we can use body Map, it easier, when we have json inside json inside json, creating map is hard
        response.then().assertThat().statusCode(200).
                body("meta.pagination.limit", equalTo(20),
                "meta.pagination.links.current", equalTo("https://gorest.co.in/public/v1/users?page=1"),
                "data.id", hasSize(20), // data.id will give you all ids ; data[0] will give you first id
                "data.status", hasItem("active"), // has.Item is similar to ... .  data.status will give us all "active"
                "data.name", hasItems("Aadrika Khatri", "Charvi Bhattathiri", "Ekaling Butt"));

        /* I will compare the number of female and male users in 2 ways:
        1) I will get all genders then I will count the females than I will calculate males then I will check which one is more
         */

        JsonPath json = response.jsonPath(); // jsonpath method has a lot of useful method to naviagte inside json path
        List<String> genders = json.getList("data.gender");
        System.out.println(genders);
        int numOfFemales = 0;

        for(String w : genders){

            if (w.equals("female")){

                numOfFemales++;
            }
        }
        System.out.println(numOfFemales);

        assertTrue(numOfFemales>(genders.size()-numOfFemales));

        //1) I will get all females by using GROOVY, I iwll get all males by using GROOVY then compare them
        List<String> femaleList = json.getList("data.findAll{it.gender='female'}.gender");
        System.out.println(femaleList); // [female, female, female, .... ]

        List<String> maleList = json.getList("data.findAll{it.gender='male'}.gender");
        System.out.println(maleList); // [male, male, male, .... ]

        assertTrue(femaleList.size()>maleList.size());

    }
}
