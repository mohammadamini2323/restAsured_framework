package api.bookstore;

import api.common.EndPoints;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class BookstoreApi {

    public static Response getAllBooks(){
        return RestAssured.given().get(EndPoints.GET_ALL_BOOKS);
    }

   public static Response getBookByISBN(String isbn){
        return RestAssured.given().header("accept","application/json").when().get(EndPoints.GET_BOOK_BY_ISBN+isbn);
   }
//   public static Response assignBookToUser(String uuid, String isbn , String token){
//       String requestBody = "{ \"userId\": \"" + uuid + "\", \"collectionOfIsbns\": [ { \"isbn\": \"" + isbn + "\" } ] }";
//        return RestAssured.given()
//                .header("accept", "application/json")
//                .header("Authorization","Bearer "+token)
//                .contentType(ContentType.JSON)
//                .body(requestBody)
//                .post(EndPoints.POST_BOOK_TO_USER);
//   }
    public static Response assignBookToUser(String uuid, String isbn, String userToken) {

        String requestBody = "{\"userId\": \"" + uuid + "\", \"collectionOfIsbns\": [{\"isbn\": \"" + isbn + "\"}]}";
        return RestAssured
                .given()
                .header("accept", "application/json")
                .header("Authorization", "Bearer " + userToken)
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post(EndPoints.POST_BOOK_TO_USER);
    }
//   public static Response putOrUpdateBook(String uuid,String isbn ,String token ,String old_isbn){
//       String requestBody = "{ \"userId\": \"" + uuid + "\", \"isbn\": \"" + isbn + "\" }";
//        return RestAssured.given().header("Authorization","Bearer "+token).contentType(ContentType.JSON).body(requestBody)
//                .pathParams("ISBN",old_isbn)
//                .put(EndPoints.PUT_A_BOOK);
//   }
   public static Response delete_a_book(String ISBN,String userId,String token){
       String requestBody = "{ \"isbn\": \"" +  ISBN + "\", \"userId\": \"" + userId + "\" }";
        return RestAssured.given().header("Authorization","Bearer "+token).contentType(ContentType.JSON).body(requestBody).delete(EndPoints.DELETE_BOOK_FROM_USER);
   }

//   public static Response putBook(String ISBN){
//
//           return RestAssured.given()
//                   .contentType("application/json")
//                   .body("{ \"userName\": \""+username+"\", \"password\": \""+password+"\" }")
//                   .put(EndPoints.PUT_A_BOOK+ISBN);
//
//   }
}
