package api.account;

import api.common.EndPoints;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.codehaus.groovy.syntax.Token;

import static io.restassured.RestAssured.given;

public class AccountApi {

    public static Response createUser(String username, String password ){
        return RestAssured.given()
                .contentType("application/json")
                .body("{ \"userName\": \""+username+"\", \"password\": \""+password+"\" }")
                .post(EndPoints.CREATE_USER);
    }
    public static Response generateToken(String username,String password){
    return RestAssured.given().contentType("application/json")
            .body("{ \"userName\": \""+username+"\", \"password\": \""+password+"\" }").post(EndPoints.ACCOUNT_GENERATE_TOKEN);
    }
    public static Response getUserById(String id){
        return RestAssured.given().get(EndPoints.GET_USER_BY_ID+id);
    }
    public static Response getUserByIdAndToken(String id,String token){
        return RestAssured.given().header("Authorization","Bearer "+token).get(EndPoints.GET_USER_BY_ID+id);
    }
    public static Response checkIfUserIsAuthorized(String username,String password){
        return RestAssured.given().contentType("application/json")
                .body("{ \"userName\": \""+username+"\", \"password\": \""+password+"\" }")
                .post(EndPoints.ACCOUNT_AUTHORIZED);
    }
    public static Response updateUser(String uuid, String oldIsbn, String newIsbn, String token) {
        String requestBody = "{ \"userId\": \"" + uuid + "\", \"isbn\": \"" + newIsbn + "\" }";
        return RestAssured
                .given()
                .header("accept", "application/json")
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .body(requestBody)
                .pathParam("OLD_ISBN", oldIsbn)
                .put(EndPoints.UPDATE_BOOK_BY_ISBN);
    }

    public static Response delete(String id ,String token){
        return RestAssured.given().header("Authorization","Bearer "+token).delete(EndPoints.DELETE_USER_BY_ID+id);
    }
//    public static Response deleteUser(String uuid, String token) {
//        return RestAssured
//                .given()
//                .header("accept", "application/json")
//                .header("Authorization", "Bearer " + token)
//                .pathParam("UUID", uuid)
//                .delete(EndPoints.DELETE_USER_BY_UUID);
//    }



}
