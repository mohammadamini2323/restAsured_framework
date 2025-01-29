package auth;

import api.common.EndPoints;
import config.ConfigLoader;
import config.Environment;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class TokenGenerator {
    public static String generateToken(String username,String password){
        Environment currentEnvironment=Environment.getCurrentEnvironment();
        RestAssured.baseURI=currentEnvironment.getBaseUrl();
        Response response= given().contentType("application/json").body("{ \"userName\": \""+username+"\", \"password\": \""+password+"\" }")
                .post(EndPoints.ACCOUNT_GENERATE_TOKEN);
        if (response.statusCode()==200){
        return response.jsonPath().getString("token");
        }else {throw new RuntimeException("filed to generate token for user:"+username);}
    }
    public static String getSupervisorToken(){
        String userName= ConfigLoader.getProperty("supervisor.username");
        String password=ConfigLoader.getProperty("supervisor.password");
       return generateToken(userName,password);
    }
    public static String getGeneralUserToken(){
        String userName= ConfigLoader.getProperty("generalUser.username");
        String password=ConfigLoader.getProperty("generalUser.password");
        return generateToken(userName,password);
    }

}
