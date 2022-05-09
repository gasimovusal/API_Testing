package utils;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

public class Json_Util {

    private static ObjectMapper mapper;

    static{ // static block is used to initialize static variable
        mapper = new ObjectMapper();
    }

    // 1. method: this method will convert Json Data to Java Object (de-serialization)

    public static <T> T convertJsonToJavaObject(String json, Class<T> cls){ // (converting json to string, I will give you java Class and it can be any type because Class is dynamic)
        //<T> is data type, T is name ==> T stands for TYPE

        T javaResult = null; // we create this convert to store the result ==> convertJsonToJavaObject method will convert json to any data type and javaResult will store it

        try {
            javaResult = mapper.readValue(json, cls); // we create tye catch because maybe java will not able to convert it
        } catch (IOException e) {
            e.printStackTrace();
        }

        return javaResult;

    }

    // 2. method: this method will convert Java Object to Json Data (serialization)
    public static String convertJavaObjectToJson(Object obj){

        String jsonResult = null; // json will be in string format, thats why we used String

        try {
            jsonResult = mapper.writeValueAsString(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return jsonResult;
    }



}
