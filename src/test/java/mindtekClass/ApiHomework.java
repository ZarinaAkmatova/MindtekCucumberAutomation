package mindtekClass;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ApiHomework {

    /*
    GET employee id 200
    1.URL
    2.Method type GET
    3.Headers- Accept="application/json"

    Response:
    1.Status code
    2.Body
     */
    public static void main(String[] args) {


        Response response = given().baseUri("https://qeenv-apihr-arslan.herokuapp.com") //url
                .and().accept("application/json") //header
                .when().get("/api/employees/200"); //get call

        System.out.println(response.statusCode());
        System.out.println(response.body().asString());

        //Post

        Response postResponse =given().baseUri("https://qeenv-apihr-arslan.herokuapp.com")
                .and().accept("application/json")
                .and().contentType("application/json")
                .and().body("{\n" +
                        "    \"firstName\": \"Jennifer\",\n" +
                        "    \"lastName\": \"Whalen\",\n" +
                        "    \"employeeId\": 200,\n" +
                        "    \"department\": {\n" +
                        "        \"departmentId\": 10,\n" +
                        "        \"departmentName\": \"Administration\",\n" +
                        "        \"location\": {\n" +
                        "            \"locationCountry\": \"US\",\n" +
                        "            \"locationState\": \"Washington\",\n" +
                        "            \"locationCity\": \"Seattle\",\n" +
                        "            \"locationId\": 1700\n" +
                        "        }\n" +
                        "    },\n" +
                        "    \"job\": {\n" +
                        "        \"title\": \"Administration Assistant\",\n" +
                        "        \"jobId\": \"AD_ASST\",\n" +
                        "        \"salary\": 6000.0\n" +
                        "    }\n" +
                        "}")
                .when().post("/api/location");
        System.out.println(postResponse.statusCode());
        System.out.println(postResponse.body().asString());

    }
}