package test.BookStoreTestsOrganized;

import api.account.AccountApi;
import api.bookstore.BookstoreApi;
import api.common.ErrorMessage;
import api.common.StatusCodes;
import base.BaseTest;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.RandomDataGenerator;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserAndBookstore extends BaseTest {
    String Username;
    String Password;
    String token;
    String userID;
    String ISBN;
    String ISBN2;
    String UPDATE_ISBN ;
    Response response;
    @BeforeEach

    public void setUpEveryThing(){
        Username= RandomDataGenerator.generateRandomUserName();
        Password=RandomDataGenerator.generateRandomPassword();
        Response getAllBooks=BookstoreApi.getAllBooks();
        ISBN = getAllBooks.jsonPath().getString("books[0].isbn");
        ISBN2 = getAllBooks.jsonPath().getString("books[1].isbn");
        UPDATE_ISBN =getAllBooks.jsonPath().getString("books[2].isbn");
//        System.out.println("isbn :"+ISBN);
//        System.out.println("isbn2 :"+ISBN2);
//        System.out.println("UPDATE_ISBN :"+UPDATE_ISBN);
        response= AccountApi.createUser(Username,Password);
        userID=response.jsonPath().getString("userID");
//        System.out.println("this is user ID : "+userID);
        Response response2=AccountApi.generateToken(Username,Password);
        assertEquals(200,response2.statusCode(),"failed to generate token");
        token=response2.jsonPath().getString("token");
//        System.out.println("token :"+token);

    }
    @Test
    public void createUser(){
        assertEquals(201,response.statusCode(),"filed to create user");
    }
    @Test
    public void updateUserTest(){
        Response assignBookToUser = BookstoreApi.assignBookToUser(userID,ISBN,token);
        assertEquals(201,assignBookToUser.statusCode(),"unable to assign book");
        Response updateBookForUser =AccountApi.updateUser(userID,ISBN,UPDATE_ISBN,token);
        assertEquals(StatusCodes.TWO_ZERO_ZERO,updateBookForUser.statusCode(),ErrorMessage.UPDATE_USER_REQUEST_FAILED);
    }
    @Test
    public void deleteBookFromUser(){
        Response assignBookToUser = BookstoreApi.assignBookToUser(userID,ISBN,token);
        assertEquals(201,assignBookToUser.statusCode(),"unable to assign book");
        Response assign2BookToUser = BookstoreApi.assignBookToUser(userID,ISBN2,token);
        assertEquals(201,assign2BookToUser.statusCode(),"unable to assign 2 book");
        Response deleteFirstBook=BookstoreApi.delete_a_book(ISBN,userID,token);
        assertEquals(204,deleteFirstBook.statusCode(),"failed to delete book");

    }

    @Test
    public void doAllTogether(){
        Response assignBookToUser = BookstoreApi.assignBookToUser(userID,ISBN,token);
        assertEquals(201,assignBookToUser.statusCode(),"unable to assign book");
        Response assign2BookToUser = BookstoreApi.assignBookToUser(userID,ISBN2,token);
        assertEquals(201,assign2BookToUser.statusCode(),"unable to assign 2 book");
        Response getUserAfterAssign=AccountApi.getUserByIdAndToken(userID,token);
        System.out.println("after assign "+getUserAfterAssign.asPrettyString());
        Response updateBookForUser =AccountApi.updateUser(userID,ISBN,UPDATE_ISBN,token);
        assertEquals(StatusCodes.TWO_ZERO_ZERO,updateBookForUser.statusCode(),ErrorMessage.UPDATE_USER_REQUEST_FAILED);
        Response getUserAfterUpdate=AccountApi.getUserByIdAndToken(userID,token);
        System.out.println("after update : "+getUserAfterUpdate.asPrettyString());
        Response deleteFirstBook=BookstoreApi.delete_a_book(ISBN2,userID,token);
        assertEquals(204,deleteFirstBook.statusCode(),"failed to delete book");
        Response getUserAfterDeleteBook=AccountApi.getUserByIdAndToken(userID,token);
        System.out.println("after dlelete"+getUserAfterDeleteBook.asPrettyString());


    }


    @AfterEach
    public void tearDown(){
        Response deleteUser=AccountApi.delete(userID,token);
        assertEquals(204,deleteUser.statusCode(), ErrorMessage.REQUEST_FAILED+"TO DELETE USER");
        System.out.println("user deleted after"+deleteUser.statusCode());
    }
}
