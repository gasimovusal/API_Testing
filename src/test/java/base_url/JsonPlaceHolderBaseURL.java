package base_url;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class JsonPlaceHolderBaseURL {

    protected RequestSpecification spec;

    // if you use @ before annotation at the top of a method, it means the method will be executed before every test method
    @Before
    public void setUp(){

        //how to put base uri in rest assured api
        spec = new RequestSpecBuilder().setBaseUri("https://jsonplaceholder.typicode.com").build();
    }
}

