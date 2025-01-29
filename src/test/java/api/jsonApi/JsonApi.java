package api.jsonApi;

import api.common.EndPoints;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class JsonApi {
    public static Response jsonPost(){
        return RestAssured.given().contentType(ContentType.JSON).body("{\"userId\": 1, \"id\": 101, \"title\": \"sunt aut provident occaecati excepturi optio reprehenderit\", \"body\": \"quia et suscipit\\nsuscipit recusandae  et cum\\nreprehenderit quas totam\\nnostrum rerum est  rem eveniet architecto\"}").post(EndPoints.JSON_POST);
    }
}
