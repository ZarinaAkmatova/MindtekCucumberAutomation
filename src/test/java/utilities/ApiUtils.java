package utilities;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ApiUtils {
    /*
    .get(endpoint);-->returns response
    .post(endpoint body);-->returns response
    .put(endpoint,body);-->returns response
    .delete(endpoint);--> returns response

     */

    public static String getToken() {
        Response response = given().baseUri(ConfigReader.getProperty("BankApiURL"))
                .and().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body("{\n" +
                        "  \"password\": \"MindtekStudent\",\n" +
                        "  \"username\": \"Mindtek\"\n" +
                        "}")
                .when().post("/authenticate");
        return response.body().jsonPath().getString("jwt");
    }

    public static Response getCall(String endpoint) {
        Response response = given().baseUri(ConfigReader.getProperty("BankApiURL"))
                .and().accept(ContentType.JSON)
                .and().auth().oauth2(getToken())
                .when().get(endpoint);
        response.then().log().all();
        return response;

    }

    public static Response postCall(String endpoint, Object body) {
        Response response = given().baseUri(ConfigReader.getProperty("BankApiURL"))
                .and().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().auth().oauth2(getToken())
                .and().body(body)
                .when().post(endpoint);
        response.then().log().all();
        return response;
    }

    public static Response deleteCall(String endpoint) {
        Response response = given().baseUri(ConfigReader.getProperty("BankApiURL"))
                .and().accept(ContentType.JSON)
                .and().auth().oauth2(getToken())
                .when().get(endpoint);
        response.then().log().all();
        return response;
    }
}