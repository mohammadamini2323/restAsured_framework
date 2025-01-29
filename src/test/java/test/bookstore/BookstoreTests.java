package test.bookstore;

import api.account.AccountApi;
import api.bookstore.BookstoreApi;
import base.BaseTest;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import utils.RandomDataGenerator;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookstoreTests extends BaseTest {
    @Test
    public void getAllBooks(){
        Response response= BookstoreApi.getAllBooks();
        System.out.println("RESPONSE"+response.asPrettyString());
        assertEquals(200,response.statusCode(),"filed to get books");
    }
    @Test
    public void getBookByISBN(){
        String isbn = "9781449365035";
        Response response= BookstoreApi.getBookByISBN(isbn);
        System.out.println("RESPONSE"+response.asPrettyString());
    }
    @Test
    public void assignBookToUser(){
        String isbn = "9781449325862";
        final String ISBN = "9781449325862";
        final String UPDATE_ISBN = "9781449337711";
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
        Response responseAssignBook=BookstoreApi.assignBookToUser(userID,isbn,token);
        System.out.println(responseAssignBook);
        assertEquals(201,responseAssignBook.statusCode(),"unable to assign book");
        Response response5=AccountApi.getUserByIdAndToken(userID,token);
        System.out.println("get user by id and token :"+response5.asPrettyString());
        assertEquals(200,response5.statusCode(),"failed to get user by id and  token");

    }
}
