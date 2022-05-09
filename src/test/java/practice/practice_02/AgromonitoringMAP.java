package practice.practice_02;

import java.util.HashMap;
import java.util.Map;

public class AgromonitoringMAP {

    public float coordinates [][][] = { { {-121.1958F,37.6683F},
                                        {-121.1779F,37.6687F},
                                        {-121.1773F,37.6792F},
                                        {-121.1958F,37.6792F},
                                        {-121.1958F,37.6683F} } } ;

    public Map<String, Object> geometrySetup(){

        Map<String, Object> geometry = new HashMap<>();
        geometry.put("coordinates", coordinates);
        geometry.put("type", "Polygon");

        return geometry;
    }

    Map<String, Object> properties = new HashMap<>();

    public Map<String, Object> geojsonMap(){

        Map<String, Object> geojson = new HashMap<>();
        geojson.put("geometry", geometrySetup());
        geojson.put("type", "Feature");
        geojson.put("properties", properties);

        return geojson;
    }

    public float center[]={-121.1867F, 37.67385F};

    public Map<String, Object> requestBody(){

        Map<String, Object> requestBodyMap = new HashMap<>();

        requestBodyMap.put("geo_json", geojsonMap());
        requestBodyMap.put("name", "Polygon Sample");
        requestBodyMap.put("center", center);
        requestBodyMap.put("area", 190.9484);

        return requestBodyMap;
    }









}