package practice.practice_02;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertTrue;

public class P_Post01 extends AgromonitoringBASEURL{

    /*
    Given
        "http://api.agromonitoring.com/agro/1.0/polygons?appid=f4ffe3b2ef1fcb3600ab1d7fbc88c2f0&duplicated=true"
            {
               "name":"Polygon Sample",
               "geo_json":{
                  "type":"Feature",
                  "properties":{},
                  "geometry":{
                     "type":"Polygon",
                     "coordinates":[
                        [
                           [-121.1958,37.6683],
                           [-121.1779,37.6687],
                           [-121.1773,37.6792],
                           [-121.1958,37.6792],
                           [-121.1958,37.6683]
                        ]
                     ]
                  }
               }
            }
    When
         I send POST Request to the Url
    Then
        Assert Status Code (201)
        And Response Body should be like {
                                            "id": "5fd8c383714b523b2ce1f154",
                                            "geo_json": {
                                                "geometry": {
                                                    "coordinates": [
                                                        [
                                                            [
                                                                -121.1958,
                                                                37.6683
                                                            ],
                                                            [
                                                                -121.1779,
                                                                37.6687
                                                            ],
                                                            [
                                                                -121.1773,
                                                                37.6792
                                                            ],
                                                            [
                                                                -121.1958,
                                                                37.6792
                                                            ],
                                                            [
                                                                -121.1958,
                                                                37.6683
                                                            ]
                                                        ]
                                                    ],
                                                    "type": "Polygon"
                                                },
                                                "type": "Feature",
                                                "properties": {
                                                }
                                            },
                                            "name": "Polygon Sample",
                                            "center": [
                                                -121.1867,
                                                37.67385
                                            ],
                                            "area": 190.9484,
                                            "user_id": "5fd8c02a3da20c000759e0f8",
                                            "created_at": 1608041347
                                        }
     */

    @Test
    public void post01(){

        spec.pathParams("first", "agro", "second", 1.0, "third", "polygons").
                queryParams("appid", "f4ffe3b2ef1fcb3600ab1d7fbc88c2f0", "duplicated", "true");

        AgromonitoringMAP expectedData = new AgromonitoringMAP();
        Map<String, Object> expecteddataMap = expectedData.requestBody();

        Response response = given().spec(spec).contentType(ContentType.JSON).body(expecteddataMap).when().post("/{first}/{second}/{third}");
        response.prettyPrint();

        JsonPath json = response.jsonPath();

        assertTrue(json.getFloat("geo_json.geometry.coordinates[0][0][0]")==expectedData.coordinates[0][0][0]);




    }



}
