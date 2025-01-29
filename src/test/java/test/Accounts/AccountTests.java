package test.Accounts;

import api.account.AccountApi;
import api.bookstore.BookstoreApi;
import base.BaseTest;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import utils.RandomDataGenerator;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountTests extends BaseTest {
    @Test
    public void createUser(){
        String Username= RandomDataGenerator.generateRandomUserName();
        String Password=RandomDataGenerator.generateRandomPassword();
        String token;
        String userID;
        Response response= AccountApi.createUser(Username,Password);
        System.out.println("RESPONSE"+response.asPrettyString());
        assertEquals(201,response.statusCode(),"filed to get books");
        userID=response.jsonPath().getString("userID");
        System.out.println("this is user ID : "+userID);


        Response response2=AccountApi.generateToken(Username,Password);
        System.out.println("RESPONSE2 "+response2.asPrettyString() );
        assertEquals(200,response2.statusCode(),"failed to generate token");
         token=response2.jsonPath().getString("token");
        System.out.println("Token is : "+token);


        Response response3=AccountApi.checkIfUserIsAuthorized(Username,Password);
        System.out.println(response3.asPrettyString());
        assertEquals(200,response3.statusCode(),"failed to Access Authorized");


        Response response1=AccountApi.getUserById(userID);
        System.out.println("get user by id"+response1);
        assertEquals(401,response1.statusCode(),"failed to get user by id");


        Response response5=AccountApi.getUserByIdAndToken(userID,token);
        System.out.println("get user by id and token :"+response5.asPrettyString());
        assertEquals(200,response5.statusCode(),"failed to get user by id and  token");


        Response response4=AccountApi.delete(userID,token);
        System.out.println( "delete user "+response4.asPrettyString());
        assertEquals(204,response4.statusCode(),"failed to delete user");


    }
//    @Test
//    public void deleteUser(){
//        Response response4=AccountApi.delete("bc7ea975-59b6-47dc-83d4-ea534f70d33a", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyTmFtZSI6InVzZXJAMTczODA4ODM2NDY5MzI2dGltZSIsInBhc3N3b3JkIjoiUGFzc3dvcmRAMTczODA4ODM2NDcyNDIwNXRpbWUhIiwiaWF0IjoxNzM4MDg4MzY5fQ.g6_XVD7b4sHnxGdYMt1YzeMDJvy1E5eHM9ZfDE2YvfE");
//        System.out.println( "delete user "+response4.asPrettyString());
//        assertEquals(200,response4.statusCode(),"failed to delete user");
//
//    }

}
