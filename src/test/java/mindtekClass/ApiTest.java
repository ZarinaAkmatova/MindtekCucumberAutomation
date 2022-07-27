package mindtekClass;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.post;

public class ApiTest {
    public static void main(String[] args) {


    /*
    GET employee with employeeId 100
    Request:
    1.URL
    2.Method type GET
    3.Headers- Accept="application/json"

    Response:
    1.Status code
    2.Body
     */

        Response response =given().baseUri("https://qeenv-apihr-arslan.herokuapp.com")
                .and().accept("application/json")
                .when().get("/api/employees/100");

        System.out.println(response.statusCode());
        System.out.println(response.body().asString());

        /*
        POST Location

        Request:
        1.URL
        2.Headers- Accept="application/json"; Content-type="application/json"
        3.Body(Payload)
        Response:
        1.Status code 201
        2.Body
         */
       Response postResponse= given().baseUri("https://qeenv-apihr-arslan.herokuapp.com")
               .and().accept("application/json")
               .and().contentType("application/json")
               .and().body("{\n" +
                       "  \"locationCity\": \"Knox\",\n" +
                       "  \"locationCountry\": \"US\",\n" +
                       "  \"locationId\": 1212,\n" +
                       "  \"locationState\": \"Tennessee\"\n" +
                       "}")
               .when().post("/api/location");

        System.out.println(postResponse.statusCode());
        System.out.println(postResponse.body().asString());
        /*
        POST Department
        Request:
        1.URL
        2.Headers-Accept="application/json"; ContentType="application/json";
        3.Body
        4.Method Type POST

        Response
        1.Status code
        2.Body
         */
        Response postDepartment=given().baseUri("https://qeenv-apihr-arslan.herokuapp.com")
                .and().accept("application/json")
                .and().contentType("application/json")
                .and().body("{\n" +
                        "    \"departmentId\": 8525,\n" +
                        "    \"departmentName\": \"QAManagar\",\n" +
                        "    \"location\": {\n" +
                        "        \"locationCountry\": \"USA\",\n" +
                        "        \"locationState\": \"Tennessee\",\n" +
                        "        \"locationCity\": \"Knox\",\n" +
                        "        \"locationId\": 8525\n" +
                        "    }\n" +
                        "}")
                .when().post("/api/departments");

        System.out.println(postDepartment.statusCode());
        System.out.println(postResponse.body().asString());
       /*
       DELETE Department
       Request
       1.URL
       2.Method type DELETE

       Response:
       1.Status code 204

        */
        Response deleteDepartment=given().baseUri("https://qeenv-apihr-arslan.herokuapp.com")
                .when().delete("/api/departments/8525");

        System.out.println(deleteDepartment.statusCode());


    }
}