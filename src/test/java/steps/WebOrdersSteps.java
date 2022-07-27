package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import pages.WebOrdersHomePage;
import pages.WebOrdersLoginPage;
import pages.WebOrdersOrderPage;
import utilities.BrowserUtils;
import utilities.ConfigReader;
import utilities.Driver;
import java.util.List;
import java.util.Map;

public class WebOrdersSteps {

    WebDriver driver = Driver.getDriver();
    WebOrdersLoginPage webOrdersLoginPage = new WebOrdersLoginPage();
    WebOrdersHomePage webOrdersHomePage = new WebOrdersHomePage();
    WebOrdersOrderPage webOrdersOrderPage=new WebOrdersOrderPage();
    int numberOfRows;
    List<Map<String,Object>> data;


    @Given("user navigates to the weborders application")
    public void user_navigates_to_the_weborders_application() {
        driver.get(ConfigReader.getProperty("WebOrdersURL"));
    }

    @When("user provides username {string} and password {string}")
    public void user_provides_username_Tester_and_password(String username, String password) {
        webOrdersLoginPage.username.sendKeys(username);
        webOrdersLoginPage.password.sendKeys(password);
        webOrdersLoginPage.loginButton.click();
    }

    @Then("user validates application is logged in")
    public void user_validates_application_is_logged_in() {
        String actualTitle = driver.getTitle();
        String expectedTitle = "Web Orders";

        Assert.assertEquals(expectedTitle, actualTitle);
        driver.quit();
    }

    @Then("user validates error message {string}")
    public void userValidatesErrorMessage(String errorMessage) {
        String actualMessage = webOrdersLoginPage.errorMessage.getText();
        String expectedMessage = "Invalid login or Password";
        Assert.assertEquals(errorMessage, actualMessage);
    }

    @When("user navigates on Order module")
    public void user_navigates_on_Order_module() {
        webOrdersHomePage.orderModule.click();

    }

    @When("user selects {string} product with {int}")
    public void user_selects_product_with(String product, Integer quantity) {
        BrowserUtils.selectDropdownByValue(webOrdersOrderPage.orderProductDropdown,product);
        webOrdersOrderPage.quantityBox.sendKeys(Keys.BACK_SPACE); //clear the page
        webOrdersOrderPage.quantityBox.sendKeys(quantity+""+Keys.ENTER);
    }

    @Then("user validates total is calculated properly for quantity {int}")
    public void user_validates_total_is_calculated_properly_for_quantity(Integer quantity) {

        String pricePerUnit=webOrdersOrderPage.pricePerUnit.getAttribute("value");
        int expectedTotal=0; //creating an empty container for total in order to use it later
        String discountAmount=webOrdersOrderPage.discountBox.getAttribute("value"); //works with String Only
        int discountAmountInt=Integer.parseInt(discountAmount); //making String pricePerUnit as Integer discount to perform math collections
        int pricePerUnitInt=Integer.parseInt(pricePerUnit);//making String discount as Integer discount to perform math


        expectedTotal=quantity*pricePerUnitInt;//expected total is 1000
        if (discountAmountInt != 0) {
            expectedTotal = expectedTotal - expectedTotal * discountAmountInt / 100; //  1000-(1000*8/100)
        }
        String actualTotalAmount=webOrdersOrderPage.totalBox.getAttribute("value");
        int actualTotal=Integer.parseInt(actualTotalAmount);
        Assert.assertEquals(expectedTotal,actualTotal);



    }
    @When("user counts number of orders in table")
    public void user_counts_number_of_orders_in_table() {
        numberOfRows=webOrdersHomePage.numberOfRows.size();

    }

    @When("user creates order with data")
    public void user_creates_order_with_data(DataTable dataTable) {
        //Convert dataTable to list of maps
        // For automatic transformation, change DataTable to one of
        // List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or Map<K, List<V>>.
        data = dataTable.asMaps(String.class,Object.class);
        BrowserUtils.selectDropdownByValue(webOrdersOrderPage.orderProductDropdown, data.get(0).get("product").toString());
        webOrdersOrderPage.quantityBox.sendKeys(Keys.BACK_SPACE);
        webOrdersOrderPage.quantityBox.sendKeys(data.get(0).get("quantity").toString());
        webOrdersOrderPage.name.sendKeys(data.get(0).get("name").toString());
        webOrdersOrderPage.street.sendKeys(data.get(0).get("street").toString());
        webOrdersOrderPage.city.sendKeys(data.get(0).get("city").toString());
        webOrdersOrderPage.state.sendKeys(data.get(0).get("state").toString());
        webOrdersOrderPage.zip.sendKeys(data.get(0).get("zip").toString());
        webOrdersOrderPage.visaCheckBox.click();
        webOrdersOrderPage.cardNumber.sendKeys(data.get(0).get("cc").toString());
        webOrdersOrderPage.expireDate.sendKeys(data.get(0).get("expire date").toString());
        webOrdersOrderPage.processButton.click();

    }

    @Then("user validates success message {string}")
    public void user_validates_success_message(String expectedMessage) {
        String actualMessage=webOrdersOrderPage.successMessage.getText();
        Assert.assertEquals(expectedMessage,actualMessage);

    }

    @And("user validates order added to list of Orders")
    public void userValidatesOrderAddedToListOfOrders() {
        webOrdersHomePage.viewAllOrders.click();
       int numberOfRowsAfterOrderCreation= webOrdersHomePage.numberOfRows.size();
       Assert.assertEquals(numberOfRowsAfterOrderCreation-numberOfRows,1);

       String actualName=webOrdersHomePage.firstName.getText();
       Assert.assertEquals(data.get(0).get("name").toString(),actualName);


    }
}

