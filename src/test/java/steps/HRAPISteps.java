package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.HRAppHomePage;
import pages.HRAppLoginPage;
import pojos.Department;
import pojos.Employee;
import pojos.Job;
import utilities.BrowserUtils;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.JDBCUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class HRAPISteps {
    Response response;
    WebDriver driver = Driver.getDriver();
    HRAppLoginPage loginPage = new HRAppLoginPage();
    HRAppHomePage homePage = new HRAppHomePage();
    String locationId;
    Map<String, Object> locationPostData;
    List<String> firstNames = new ArrayList<>();
    String jobId;
    Job job = new Job();
    Department department = new Department();
    Integer departmentId;

    @Given("user sends GET call for employee with employeeId {int}")
    public void user_sends_GET_Employee_API_Call(Integer employeeId) {
        response = given().baseUri(ConfigReader.getProperty("HRAPIBaseURL"))
                .and().accept("application/json")
                .when().get("/api/employees/" + employeeId);
    }

    @Then("user validates status code is {int}")
    public void user_validates_status_code_is(int statusCode) {
        Assert.assertEquals(statusCode, response.statusCode());
    }

    @When("user navigates to HR App")
    public void user_navigates_to_HR_App() {
        driver.get(ConfigReader.getProperty("HRAPPUrl"));
    }

    @When("user logs in with username {string} password {string}")
    public void user_logs_in_with_username_password(String username, String password) {
        loginPage.username.sendKeys(username);
        loginPage.password.sendKeys(password);
        loginPage.login.click();

    }

    @When("user search for employee with employeeId {int}")
    public void user_search_for_employee_with_employeeId(Integer employeeId) throws InterruptedException {
        homePage.searchBox.sendKeys(employeeId + "" + Keys.ENTER);
        Thread.sleep(2000);
        driver.findElement(By.id("searchButton")).click();

    }

    @Then("validates user data from IU matches with API response")
    public void validates_user_data_from_IU_matches_with_API_response() {
        String iuFirstName = driver.findElement(By.xpath("//td[2]")).getText();
        String iuLastName = driver.findElement(By.xpath("td[3]")).getText();
        String uiDepartmentName = driver.findElement(By.xpath("//td[4]")).getText();

        String apiFirstName = response.jsonPath().getString("firstName");
        String apiLastName = response.jsonPath().getString("lastName");
        String apiDepartmentName = response.jsonPath().getString("department.departmentName");

        Assert.assertEquals(apiFirstName, iuFirstName);
        Assert.assertEquals(apiLastName, iuLastName);
        Assert.assertEquals(apiDepartmentName, uiDepartmentName);

    }

    @Given("user creates location with POST api call with data")
    public void user_creates_location_with_POST_api_call_with_data(io.cucumber.datatable.DataTable dataTable) {
        locationPostData = dataTable.asMap(String.class, Object.class);
        //   locationPostData.put("locationId", new Random().nextInt(10000));
        //POST
        //URL+Headers +Body+ Post method
        response = given().baseUri(ConfigReader.getProperty("HRAPIBaseURL"))
                .and().accept("application/json")
                .and().contentType("application/json")
                .and().body(locationPostData)
                .when().post("/api/location");
        response.then().log().all();
        locationId = response.jsonPath().getString("locationId");


    }

    @When("user gets created location")
    public void user_gets_created_location() {
        //GET
        //URL +headers + GET Method
        response = given().baseUri(ConfigReader.getProperty("HRAPIBaseURL"))
                .and().accept("application/json")
                .when().get("/api/location/" + locationId);
        response.then().log().all();

    }

    @Then("user validates data in get call data matches with created data")
    public void user_validates_data_in_get_call_data_matches_with_created_data() {
        //  locationPostData <-> response

        Assert.assertEquals(locationPostData.get("locationCity").toString(), response.jsonPath().getString("locationCity"));
        Assert.assertEquals(locationPostData.get("locationCountry").toString(), response.jsonPath().getString("locationCountry"));
        Assert.assertEquals(locationPostData.get("locationState").toString(), response.jsonPath().getString("locationState"));
    }

    @When("user selects {string} department")
    public void user_selects_department(String departmentName) {
        BrowserUtils.selectDropdownByText(homePage.departments, departmentName);

    }

    @When("user stores ui data in selected department")
    public void user_stores_ui_data_in_selected_department() {
        List<WebElement> firstNameElements = driver.findElements(By.xpath("//td[2]"));
        for (WebElement element : firstNameElements) {
            firstNames.add(element.getText());
        }

    }

    @When("user sends get call employees api call for {string} department")
    public void user_sends_get_call_employees_api_call_for_department(String string) {
        //GET
        //URL + Headers +GET Method
        response = given().baseUri(ConfigReader.getProperty("HRAPIBaseURL"))
                .and().accept("application/json")
                .get("/api/departments/60/employees");
        response.then().log().all();

    }

    @Then("user validates ui data matches with api data in {string} department employees")
    public void user_validates_ui_data_matches_with_api_data_in_department_employees(String string) {
        //uiDataFirstName
        //apiFirstNames--> response

        List<String> apiFirstName = response.jsonPath().getList("firstName");

        Assert.assertEquals(firstNames, apiFirstName);

    }

    @Given("user creates job with POST job call with data")
    public void user_creates_job_with_POST_job_call_with_data(io.cucumber.datatable.DataTable dataTable) {
        Map<String, Object> postLocationData = dataTable.asMap(String.class, Object.class);

        job.setJobId(postLocationData.get("jobId").toString());
        job.setTitle(postLocationData.get("title").toString());
        job.setSalary(Double.valueOf(postLocationData.get("salary").toString()));
        response = given().baseUri(ConfigReader.getProperty("HRAPIBaseURL"))
                .and().accept("application/json")
                .and().contentType("application/json")
                .and().body(job)
                .when().post("/api/jobs");
        jobId = job.getJobId();

    }

    @When("user gets created job")
    public void user_gets_created_job() {
        response = given().baseUri(ConfigReader.getProperty("HRAPIBaseURL"))
                .and().accept("application/json").when().get("/api/jobs/" + jobId);

    }

    @Then("user validates created data matches with data in get response")
    public void user_validates_created_data_matches_with_data_in_get_response() {
        Job responseJob = response.as(Job.class);
        System.out.println("Data we used to create job : " + job.toString());
        System.out.println("Data we got as response for get call :" + responseJob.toString());
        Assert.assertEquals(job.toString(), responseJob.toString());
    }


    @Given("user creates department with POST call with data")
    public void user_creates_department_with_POST_call_with_data(io.cucumber.datatable.DataTable dataTable) {
        Map<String, Object> departmentPostData = dataTable.asMap(String.class, Object.class);
        //POST
        //URL +Headers+ Body +Post Method
        department.setDepartmentId(Integer.valueOf(departmentPostData.get("departmentId").toString()));
        department.setDepartmentName(departmentPostData.get("departmentName").toString());
        response = given().baseUri(ConfigReader.getProperty("HRAPIBaseURL"))
                .and().accept("application/json")
                .and().contentType("application/json")
                .and().body(department)
                .when().post("/api/departments");
        response.then().log().all();
        //departmentPostData=response.jsonPath().getString("departmentId");
        departmentId = department.getDepartmentId();
    }

    @When("user gets created department")
    public void user_gets_created_department() {
        response = given().baseUri(ConfigReader.getProperty("HRAPIBaseURL"))
                .and().accept("application/json")
                .and().get("/api/department/" + departmentId);
        response.then().log().all();

    }

    @Then("user validates created department matches with data in get call response")
    public void user_validates_created_department_matches_with_data_in_get_call_response() {
        Department responseDepartment = response.as(Department.class); //Converting json format to POJO Object Deserialization
        Assert.assertEquals(department.toString(), responseDepartment.toString());

    }

    //@Then("user validates status code {int}")
    public void user_validates_status_code(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("user validates IU data matches with data in DB for {string} department")
    public void user_validates_IU_data_matches_with_data_in_DB_for_department(String departmentName) throws SQLException {
        //ui first name --> firstNames
        JDBCUtils.establishConnection();
        List<Map<String, Object>> dbData = JDBCUtils.runQuery("select e.employee_id, e.first_name,e.last_name, d.department_name\n" +
                "from employees e join departments d\n" +
                "on e.department_id = d.department_id\n" +
                "where d.department_name='" + departmentName + " '");

        for (int i = 0; i < dbData.size(); i++) {
            Assert.assertEquals(dbData.get(i).get("first_name").toString(), firstNames.get(i));
        }

    }

    @When("user clicks on added button for employee with {int} id")
    public void user_clicks_on_added_button_for_employee_with_id(Integer int1) {
        homePage.editEmployee1.click();

    }

    String updatedName;

    @When("user updates firstName for employee with {string}")
    public void user_updates_firstName_for_employee_with(String updatedName) {
        this.updatedName = updatedName;
        homePage.editFirstNameBox.clear();
        homePage.editFirstNameBox.sendKeys(updatedName);homePage.saveButton.click();

    }

    @Then("user validates that employee name is updated in UI")
    public void user_validates_that_employee_name_is_updated_in_UI() {
        Assert.assertEquals(updatedName, homePage.employee1Name.getText());

    }

    @Then("user validates employee name is updated with API get call")
    public void user_validates_employee_name_is_updated_with_API_get_call() {
        response=given().baseUri(ConfigReader.getProperty("HRAPIBaseURL"))
                .and().accept(ContentType.JSON)
                .when().get("/api/employees/100");
        response.then().statusCode(200);
        //response body====Employee
        Employee employee=response.body().as(Employee.class);

        Assert.assertEquals(updatedName,employee.getFirstName());

    }


}








