package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import pojos.Customer;
import utilities.ApiUtils;
import utilities.ConfigReader;

import java.util.*;

import static io.restassured.RestAssured.given;

public class BankApiSteps {
    Response response;
    String id;

    @Given("user creates customer with data")
    public void user_creates_customer_with_data(io.cucumber.datatable.DataTable dataTable) {
        /*
        POST Customer
        1.URL
        2.Headers
            Accept=application json
            Content=application json
            Authorization= Bearer jdfhjfhfjkjggkhrg
         3. Body=POJO

         POST Authenticate
         1 URL
         2 Headers
         3 Body
         */
        Map<String, Object> data = dataTable.asMap(String.class, Object.class);
        Customer customer = new Customer();
        customer.setAccountOpenDate(data.get("accountOpenDate").toString());
        customer.setActive(Boolean.valueOf(data.get("active").toString()));
        customer.setAddress(data.get("address").toString());
        customer.setFullName(data.get("fullName").toString());
        customer.setIsActive(Boolean.valueOf(data.get("isActive").toString()));

        response = given().baseUri(ConfigReader.getProperty("BankApiURL"))
                .and().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().auth().oauth2(ConfigReader.getProperty("Token"))
                .and().body(customer)
                .when().post("/api/customer");

        id = response.body().jsonPath().getString("id");

    }

    @Then("status code is {int}")
    public void status_code_is(Integer statusCode) {
        response.then().statusCode(statusCode);

    }

    @When("user gets created customer")
    public void user_gets_created_customer() {
        /*
        GET
        1 URL
        2 Headers
         */

        response = ApiUtils.getCall("/api/customers/" + id);

    }

    @Then("user validates get response data matches with created data")
    public void user_validates_get_response_data_matches_with_created_data() {
        response = given().baseUri(ConfigReader.getProperty("BankApiURL"))
                .and().accept(ContentType.JSON)
                .and().get();


    }

    public String getToken() {
        response = given().baseUri(ConfigReader.getProperty("BankApiURL"))
                .and().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body("{\n" +
                        "  \"password\": \"MindtekStudent\",\n" +
                        "  \"username\": \"Mindtek\"\n" +
                        "}")
                .when().post("/authenticate");
        return response.body().jsonPath().getString("jwt");

    }


    @Given("user creates multiple customers with data")
    public void user_creates_multiple_customers_with_data(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, Object>> data = dataTable.asMaps(String.class, Object.class);

        for (int i = 0; i < data.size(); i++) {    //| accountOpenDate | active | address        | fullName        | isActive |
            Customer customer = new Customer();
            customer.setAccountOpenDate(data.get(i).get("accountOpenDate").toString());
            customer.setActive(Boolean.valueOf(data.get(i).get("active").toString()));
            customer.setAddress(data.get(i).get("address").toString());
            customer.setFullName(data.get(i).get("fullName").toString());
            customer.setIsActive(Boolean.valueOf(data.get(i).get("isActive").toString()));
            response = ApiUtils.postCall("/api/customer", customer);
            response.then().statusCode(201);
        }


    }

    @When("user gets customer with limit {int}")
    public void user_gets_customer_with_limit(Integer limit) {
        response = ApiUtils.getCall("/api/customers?limit=" + limit);
        response.then().statusCode(200);


    }

    @Then("user validates response has {int} customers")
    public void user_validates_response_has_customers(int limit) {
        Customer[] customers = response.as(Customer[].class);
        Assert.assertEquals(limit, customers.length);


    }

    @When("user gets customer with order {string}")
    public void user_gets_customer_with_order(String order) {
        response = ApiUtils.getCall("/api/customers?limit=5&order=" + order);
        response.then().statusCode(200);

    }


    @Then("user validates response data in {string} order")
    public void user_validates_response_data_in_order(String order) {
        Customer[] customers = response.as(Customer[].class);
        List<Customer> actualCustomers = new ArrayList<>(Arrays.asList(customers));
        List<Customer> expectedCustomers = new ArrayList<>(Arrays.asList(customers));

        if (order.equalsIgnoreCase("desc")) {
            Collections.sort(expectedCustomers, Collections.reverseOrder(new Customer()));
            Assert.assertEquals(expectedCustomers.toString(), actualCustomers.toString());
        } else {
            Collections.sort(expectedCustomers, new Customer());
            Assert.assertEquals(expectedCustomers.toString(), actualCustomers.toString());
        }
    }

}