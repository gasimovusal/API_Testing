package get_requests;

import base_url.GoRestBaseURL;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.Gorest_Data_Pojo;
import pojos.Gorest_ResponseBody_Pojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class Get_13_Pojo extends GoRestBaseURL {

    /*
    Given
        https://gorest.co.in/public/v1/users/13
    When
        User send GET Request to the URL
    Then
        Status Code should be 200
    And
        Response body should be like
                                   {
                                "meta": null,
                                "data": {
                                    "id": 13,
                                    "name": "Sanya Pandey",
                                    "email": "sanya_pandey@collier.io",
                                    "gender": "female",
                                    "status": "inactive"
                                }
                            }
    */

    @Test
    public void getPojo01(){

        spec.pathParams("first", "users", "second", 13);

        Gorest_Data_Pojo gorest_data_pojo = new Gorest_Data_Pojo(13, "Sanya Pandey", "sanya_pandey@collier.io", "female", "inactive");
        Gorest_ResponseBody_Pojo gorest_responseBody_pojo = new Gorest_ResponseBody_Pojo(null, gorest_data_pojo);

        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        Gorest_ResponseBody_Pojo actualData = response.as(Gorest_ResponseBody_Pojo.class);

        assertEquals(gorest_responseBody_pojo.getMeta(), actualData.getMeta());
        assertEquals(gorest_data_pojo.getId(), actualData.getData().getId());
        assertEquals(gorest_data_pojo.getName(), actualData.getData().getName());
        assertEquals(gorest_data_pojo.getEmail(), actualData.getData().getEmail());
        assertEquals(gorest_data_pojo.getGender(), actualData.getData().getGender());
        assertEquals(gorest_data_pojo.getStatus(), actualData.getData().getStatus());

    }

}

