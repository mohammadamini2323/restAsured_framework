package base;

import auth.TokenManager;
import config.Environment;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import utils.ConfigUtils;

public class BaseTest {


    @BeforeEach
    public void setup(){
        Environment currentEnv=Environment.getCurrentEnvironment();
        RestAssured.baseURI=currentEnv.getBaseUrl();
        int timeout= ConfigUtils.getTimeOuts();
        RestAssured.config= RestAssured.config().httpClient(RestAssured.config().getHttpClientConfig().setParam("http.connection.timeout",timeout));
        System.out.println("environment is"+currentEnv);
        System.out.println("base url is"+RestAssured.baseURI);
        System.out.println("time set to"+timeout);

    }
    @AfterEach
    public void tearDown(){
        TokenManager.tearDown();
        System.out.println("Tokens are cleared after test completion.");

    }
}
