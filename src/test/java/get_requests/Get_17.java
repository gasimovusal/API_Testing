package get_requests;

import base_url.Dummy_RestAPI_Base_URL;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.Dummy_API_Data_Pojo;
import pojos.ResponseBodyPojo;
import utils.Json_Util;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class Get_17 extends Dummy_RestAPI_Base_URL {

    /*
    Given
            https://dummy.restapiexample.com/api/v1/employee/1
        When
            User send GET Request
        Then
            Status code is 200
        And
            "employee_name" is "Tiger Nixon"
        And
            "employee_salary" is 320800
        And
            "employee_age" is 61
        And
            "status" is "success"
        And
            "message" is "Successfully! Record has been fetched."
     */

    @Test
    public void get01(){

        spec.pathParams("first", "employee", "second", 1);

        Dummy_API_Data_Pojo dataPojo = new Dummy_API_Data_Pojo("Tiger Nixon", 320800, 61, "");
        ResponseBodyPojo responsePojo = new ResponseBodyPojo("success", dataPojo, "Successfully! Record has been fetched.");

        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        response.then().assertThat().statusCode(200);

        ResponseBodyPojo responseBodyPojo1 = Json_Util.convertJsonToJavaObject(response.asString(), ResponseBodyPojo.class);
        System.out.println(responseBodyPojo1);

        JsonPath json = response.jsonPath();
        assertEquals(responsePojo.getStatus(), responseBodyPojo1.getStatus());
        assertEquals(responsePojo.getMessage(), responseBodyPojo1.getMessage());
        assertEquals(responsePojo.getData().getEmployeeName(), json.getString("data.employee_name"));
        assertEquals(responsePojo.getData().getEmployeeSalary(), (Integer)json.getInt("data.employee_salary"));
        assertEquals(responsePojo.getData().getProfileImage(), json.getString("data.profile_image"));

    }
}
