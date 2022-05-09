package practice.practice_02;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class AgromonitoringBASEURL {

    protected RequestSpecification spec;

    @Before
    public void setup(){

        spec = new RequestSpecBuilder().setBaseUri("http://api.agromonitoring.com").build();
    }
}
