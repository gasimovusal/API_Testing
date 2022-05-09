package practice.practice_03;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class HerokuappURL {

    protected RequestSpecification spec;

    @Before
    public void setup(){

        spec = new RequestSpecBuilder().setBaseUri("https://restful-booker.herokuapp.com").build();
    }
}
