package practice.post_Request;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class HEROKUappBaseUrl {


    protected RequestSpecification spec;

    @Before
    public void setUo(){

        spec = new RequestSpecBuilder().setBaseUri("https://restful-booker.herokuapp.com").build();
    }

}
