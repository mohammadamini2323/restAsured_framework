package test.jsonPlaceHolder;

import api.jsonApi.JsonApi;
import auth.TokenManager;
import config.Environment;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.ConfigUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PostTest {
    @BeforeEach
    public void setup(){
        Environment currentEnv=Environment.getCurrentEnvironment();
        RestAssured.baseURI="https://jsonplaceholder.typicode.com";
        int timeout= ConfigUtils.getTimeOuts();
        RestAssured.config= RestAssured.config().httpClient(RestAssured.config().getHttpClientConfig().setParam("http.connection.timeout",timeout));
        System.out.println("environment is"+currentEnv);
        System.out.println("base url is"+RestAssured.baseURI);
        System.out.println("time set to"+timeout);

    }
    @Test
    public void jsonPostTest(){
        Response response= JsonApi.jsonPost();
        System.out.println("response is :"+response.asPrettyString());
        assertEquals(201,response.statusCode(),"failed");

    }




    @AfterEach
    public void tearDown(){
        TokenManager.tearDown();
        System.out.println("Tokens are cleared after test completion.");

    }
}
